package renderer;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * @author Hodaya and Osnat
 *
 */
public class Render {
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	final ImageWriter _imageWriter;
	final Scene _scene;
	final int _numRays;
	final boolean adaptiveSuperSampling;
	
	//for the thread
	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;
	/**
	 * Render Constructor
	 * @param _imageWriter
	 * @param _scene
	 */
	public Render(ImageWriter _imageWriter, Scene _scene) {
		super();
		this._imageWriter = _imageWriter;
		this._scene = _scene;
		this._numRays = 81;//for the soft Shadows
		this.adaptiveSuperSampling = false;
	}
	/**
	 * Render Constructor
	 * @param _imageWriter
	 * @param _scene
	 * @param adaptiveSuperSampling true if we want to apply the adaptiveSuperSampling enhancement, else false
	 */
	public Render(ImageWriter _imageWriter, Scene _scene, boolean adaptiveSuperSampling) {
		super();
		this._imageWriter = _imageWriter;
		this._scene = _scene;
		this._numRays = 81;//for the soft Shadows
		this.adaptiveSuperSampling = adaptiveSuperSampling;
	}
	/**
	 * Render Constructor
	 * @param _imageWriter
	 * @param _scene
	 * @param _numRays for the soft Shadows
	 */
	public Render(ImageWriter _imageWriter, Scene _scene, int _numRays) {
		super();
		this._imageWriter = _imageWriter;
		this._scene = _scene;
		this._numRays = _numRays;
		this.adaptiveSuperSampling = false;
	}
	/**
	 * Render Constructor
	 * @param _imageWriter
	 * @param _scene
	 * @param _numRays for the soft Shadows
	 * @param adaptiveSuperSampling true if we want to apply the adaptiveSuperSampling enhancement, else false
	 */
	public Render(ImageWriter _imageWriter, Scene _scene, int _numRays, boolean adaptiveSuperSampling) {
		super();
		this._imageWriter = _imageWriter;
		this._scene = _scene;
		this._numRays = _numRays;
		this.adaptiveSuperSampling = adaptiveSuperSampling;
	}
	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_print&&_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				target.row = this.row;
				target.col = this.col;
				if (_print&&_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}

	/**
	 * @return the _imageWriter
	 */
	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}

	/**
	 * @return the _scene
	 */
	public Scene get_scene() {
		return _scene;
	}

	/**
	* render the Image
	*/
	public void renderImage()
	{
		java.awt.Color background = _scene.get_background().getColor();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		final double dist = _scene.get_distance();
		final double width = _imageWriter.getWidth();
		final double height = _imageWriter.getHeight();
		final Camera camera = _scene.get_camera();

		final Pixel thePixel = new Pixel(nY, nX);
		

		
		
		//for each point (i,j) in the view plane // i is pixel row number and j is pixel in the row number
		// Generate threads
				Thread[] threads = new Thread[_threads];
				for (int i = _threads - 1; i >= 0; --i) {
					threads[i] = new Thread(() -> {
						Pixel pixel = new Pixel();
						while (thePixel.nextPixel(pixel)) {
							if(this.adaptiveSuperSampling == true)//if we want to apply the adaptiveSuperSampling enhancement
								{
								_imageWriter.writePixel(pixel.col, pixel.row, adaptiveSuperSampling(pixel.col, pixel.row).getColor());
								}
							else//if we don't want to apply the adaptiveSuperSampling enhancement
							{
								Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, //
										dist, width, height);
								GeoPoint closestPoint = findCLosestIntersection(ray);
								if(closestPoint == null)
									_imageWriter.writePixel(pixel.col, pixel.row, background);
								else
								{ 
									_imageWriter.writePixel(pixel.col, pixel.row, calcColor(closestPoint, ray).getColor());
								}

							}
						}
					});
				}

				// Start threads
				for (Thread thread : threads) thread.start();

				// Wait for all threads to finish
				for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
				if (_print) System.out.printf("\r100%%\n");
	}
	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}
	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}
	/**
	 * Shell function of calcColor
	 * @param p point
	 * @param ray 
	 * @return color of point when we throw the ray
	 */
	private Color calcColor(GeoPoint p, Ray ray)
	{
		return calcColor(p, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.get_ambientLight().get_intensity());
	}
	/**
	 * @param p point
	 * @param ray
	 * @param level of the recursion
	 * @param k 
	 * @return color of point when we throw the ray
	 */
	public Color calcColor(GeoPoint p, Ray ray, int level, double k)
	{	
		Color iE = p.geometry.get_emmission();
		Color color = iE;
		double kD, kS;
		int nSH;
		Vector l, n, v;
		n = p.geometry.getNormal(p.point);
		Color iL;
		for (LightSource i: _scene.get_lights())
		{
			kD = p.geometry.get_material().get_kD();
			kS = p.geometry.get_material().get_kS();
			nSH = p.geometry.get_material().get_nShininess();
			l = i.getL(p.point);
			v = p.point.subtract(_scene.get_camera().get_p0());
			v.normalize();
			//sign of l*n = sign of v*n
			if ((l.dotProduct(n)>0 && v.dotProduct(n)>0)||(l.dotProduct(n)<0 && v.dotProduct(n)<0))
			{ 
				double ktr = transparency(i, l, n, p);
				if (ktr * k > MIN_CALC_COLOR_K) 
				{
					iL = i.getIntensity(p.point).scale(ktr);				
					//IP = kA * iA + iE + (kD * | l * n | + kS *( -v * r)^nSH) * iL				
					color = color.add(calcDiffusive(kD, l, n, iL),calcSpecular(kS, l, n, v, nSH, iL));			
				}
			}
		}
		if (level == 1) return Color.BLACK;
		double kr = p.geometry.get_material().get_kR(), kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K)
		{
			Ray reflectedRay = constructReflectedRay(p.point, ray, n);
			GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		double kt = p.geometry.get_material().get_kT(), kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) 
		{
			Ray refractedRay = constructRefractedRay(p.point, ray, n) ;
			GeoPoint refractedPoint = findCLosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}
	/**
	 * @param kD the diffuse
	 * @param l Vector from the light source 
	 * @param n a normal vector of the point
	 * @param iL color of point
	 * @return diffusive 
	 */
	public Color calcDiffusive(double kD, Vector l, Vector n, Color iL) 
	{
	//kD * | l * n | * iL
		double abs = Math.abs(l.dotProduct(n));
		return iL.scale(kD * abs);		
	}
	/**
	 * @param kS the specular
	 * @param l Vector from the light source 
	 * @param n a normal vector of the point
	 * @param v Vector from the camera
	 * @param nSH the Shininess
	 * @param iL color of point
	 * @return specular 
	 */
	public Color calcSpecular(double kS, Vector l, Vector n, Vector v, double nSH, Color iL)
	{
	//kS *( -v * r)^nSH * iL
		//r = l - 2 * (l * n) * n
		Vector r = l.add(n.scale(-2 * (l.dotProduct(n))));
		r.normalize();
		double pow =  Math.pow(((v.scale(-1)).dotProduct(r)), nSH);
		return iL.scale(kS * pow);
	}
	/** 
	 * @param p point
	 * @param ray a ray from the camera
	 * @param n a normal in point
	 * @return reflected ray
	 */
	public Ray constructReflectedRay(Point3D p, Ray ray, Vector n) 
	{
		//r = v - 2 * (v*n) * n
		Vector v = ray.get_direction();
		double vn = v.dotProduct(n);
		if(vn == 0)
			return null;
		Vector r = v.add(n.scale(-2*vn));
		return new Ray(p, r, n);
	}
	/**
	 * @param p point
	 * @param ray a ray from the camera
	 * @param n a normal in point
	 * @return Refracted Ray
	 */
	public Ray constructRefractedRay(Point3D p, Ray ray, Vector n) 
	{
		return new Ray(p, ray.get_direction(), n);
	}
	/**
	 * In the intersectionPoints - find the point with minimal distance from the ray begin point and return it
	 * @param points list of the intersectionPoints
	 * @return close point
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> points)
	{
		GeoPoint close = points.get(0) ;
		double dist;
		for(GeoPoint p : points)
		{
			dist = _scene.get_camera().get_p0().distance(p.point);
			if(dist < _scene.get_camera().get_p0().distance(close.point))
				close = p;	
		}
		return close;
	}
	/**
	 * In the intersectionPoints - find the point with minimal distance from the ray begin point and return it
	 * @param interval of every square in grid
	 * @param color of grid
	 */
	public void printGrid(int interval, java.awt.Color color)
	{
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy(); 
		//for all pixels of imageWriter
		for(int i = 0; i < nY; i++)
			for(int j = 0; j < nX; j++)
				if(j % interval == 0 || i % interval == 0)
					_imageWriter.writePixel(j, i, color);	
		_imageWriter.writeToImage();
	}
	/**
	 * write To Image
	 */
	public void writeToImage() 
	{
		_imageWriter.writeToImage();
	}
	/**
	 * @param light LightSource
	 * @param l vector
	 * @param n vector
	 * @param geopoint GeoPoint
	 * @return if the point unshaded
	 */
	 private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	        Vector lightDirection = l.scale(-1); // from point to light source
	        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
	        Point3D pointGeo = geopoint.point;

	        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
	        if (intersections == null) {
	            return true;
	        }
	        double lightDistance = light.getDistance(pointGeo);
	        for (GeoPoint gp : intersections) {
	            if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0 && gp.geometry.get_material().get_kT() == 0) {
	                return false;
	            }
	        }
	        return true;
	    }
	 /**
		 * make a circle with radius of the light source and make rays to throw in his direction
		 * @param geopoint point
		 * @param light the light source
		 * @param vUp to the circle
		 * @param vRight to the circle
		 * @param n normal vector in the geopoint point
		 * @return return a list of rays - beam of shadow rays from the geopoint in the radius of the light source
		 */
		public ArrayList<Ray> makeRays(GeoPoint geopoint, LightSource light, Vector vUp,Vector vRight, Vector n)
		{
			Ray lightRay;
			ArrayList<Ray> rays = new ArrayList<Ray>();
			for (int i = 1; i < _numRays; ++i)//Since we want at least one ray in the center of the circle, the loop starts from 1 and this ray adds in the function transparency
	        {
	            double cosTetah = -1 + (Math.random() * 2); //random returns a double that  equal or bigger than 0.0 and less than 1.0// min = -1, max = 1// located (between 0 to Pie)
	            double sinTetah = Math.sqrt(1 - cosTetah * cosTetah); 
	            double d = -light.get_radius() + (Math.random() * (2 * light.get_radius()));// min = -radius, max = +radius
	            // Move from polar to Cartesian system:
	            double x_move = d * cosTetah;
	            double y_move = d * sinTetah;
	            Point3D pointMove = light.get_position();
	            if (!isZero(x_move))
	                pointMove = pointMove.add(vRight.scale(x_move));
	            if (!isZero(y_move))
	            	pointMove = pointMove.add(vUp.scale(y_move));
	            Vector lightDirection = new Vector(pointMove.subtract(geopoint.point));
	            lightRay = new Ray(geopoint.point, lightDirection, n);
	            rays.add(lightRay); // toward the circle surface
	        }
			return rays;
		}
	/**
	 * @param light LightSource
	 * @param l vector
	 * @param n vector
	 * @param geopoint GeoPoint
	 * @return the point transparency
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay1 = new Ray(geopoint.point, lightDirection, n);
        Point3D pointGeo = geopoint.point;
        if(light.get_radius() == 0)
        {
        	List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay1);
        	if (intersections == null) {
        		return 1d;
        	}
        	double lightDistance = light.getDistance(pointGeo);
        	double ktr = 1d;
        	for (GeoPoint gp : intersections) {
        		if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0) {
        			ktr *= gp.geometry.get_material().get_kT();
        			if (ktr < MIN_CALC_COLOR_K) {
        				return 0.0;
        			}
        		}
        	}
        	return ktr;
        }
        else
        {
        	Vector v = lightRay1.get_direction();
            // create x and y axis for the circle, which means vectorUp and vectorRight.
            Vector vRight = new Vector(-v.get_head().get_z().get(), 0, v.get_head().get_x().get()).normalized();
            // the reason we entered 0 to y coordinate is to prevent from creating vector zero.
            // y need to have the minimum value of the three.
            Vector vUp = v.crossProduct(vRight); // do not need normalize because already v and vUp normalized.
            ArrayList<Ray> lightRays = makeRays(geopoint, light, vUp, vRight, n);
            lightRays.add(lightRay1);//to make sure we have minimum one ray in the middle of the light source
            double ktr;
            double sumKtr = 0;//To calculate the intensity of the shadow according to the point distance from the center of the circle
            double sumDistance = 0;//sum the distance from the center of the circle
            double distance;//the distance from the center of the circle
            for (Ray lightRay2 : lightRays) {
            	distance = lightRay2.get_POO().distance(lightRay1.get_POO());
            	sumDistance += (light.get_radius() - distance);//Because as you move away from the center of the circle, there is less shadow
            	List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay2);
            	if (intersections == null) {
            		ktr = 1d;
            	}
            	else
            		{
            		double lightDistance = light.getDistance(pointGeo);
            		ktr = 1d;
            		for (GeoPoint gp : intersections) {
            			if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0) {
            				ktr *= gp.geometry.get_material().get_kT();
            				if (ktr < MIN_CALC_COLOR_K) {
            					ktr = 0.0;
            				}
            			}
            		}
            	}
                sumKtr += ktr * (light.get_radius() - distance);//Because as you move away from the center of the circle, there is less shadow
            }
            return sumKtr/sumDistance;//Weighted average of shadow intensity
         
        }
    }
	
	/**
	 * In the intersectionPoints - find the point with minimal distance from the ray begin point and return it
	 * @param points list of the intersectionPoints
	 * @return close point
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> points, Ray ray)
	{
		GeoPoint close = points.get(0);
		double dist;
		for(GeoPoint p : points)
		{
			dist = ray.get_POO().distance(p.point);
			if(dist < ray.get_POO().distance(close.point))
				close = p;	
		}
		return close;
	}
/**
 * @param ray
 * @return close intersection point
 */
private GeoPoint findCLosestIntersection(Ray ray)
{
	List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
	if(intersections == null)
		return null;
	return getClosestPoint(intersections, ray);
}

/**
 * Shell function for adaptive Super Sampling to improve the algorithm 
 * @param j index of pixel j
 * @param i index of pixel i
 * @return the color of the pixel j,i
 */
public Color adaptiveSuperSampling(int j, int i)
{
	int level = 1;
	int place = 0;
	return adaptiveSuperSampling(j, i, level, place);
}

/**
 * adaptive Super Sampling to improve the algorithm 
 * @param j index of pixel j
 * @param i index of pixel i
 * @param level of adaptive Super Sampling - we used 4
 * @param place witch part and place in the pixel
 * @return the color of the pixel j,i
 */
public Color adaptiveSuperSampling(int j, int i, int level, int place)
{
	Color background = _scene.get_background();
	int nX = _imageWriter.getNx();
	int nY = _imageWriter.getNy();
	final double dist = _scene.get_distance();
	final double width = _imageWriter.getWidth();
	final double height = _imageWriter.getHeight();
	//flag - to know if are no intersection points to the 4 rays
	boolean flag = true;
	//To know how to divide the X axis and the Y axis
	double power = Math.pow(2, level);
	power = (int)power;
	//dividing the pixel appropriate to the level and make 4 rays in the 4 corners of the pixel
	ArrayList<Ray> rays = this._scene.get_camera().constructRaysThroughPixel(nX, nY, j, i, level, place, dist, width, height);
	ArrayList<Color> raysColor = new ArrayList<Color>();
	//create a list of the colors of the 4 rays
	for (Ray ray: rays)
	{
		GeoPoint closestPoint = findCLosestIntersection(ray);
		//if intersection points to the ray
		if(closestPoint != null)
		{
			flag = false;
			raysColor.add(calcColor(closestPoint, ray));
		}
		else//if no intersection points to the ray
			raysColor.add(background);				
	}
	//if are no intersection points to the 4 rays return the background color
	if(flag)
		return background;
	//if its the last call to the function
	if(level == 5)
	{
		//return the average color between the 4 rays
		return averageOfColors(raysColor);
	}
	//if all the colors are equals
	if(isEqualsColors(raysColor))
	{
		return raysColor.get(0);
	}
	
	//go in to the recursion and calculate the color of the j, i pixels 
	//0.25 * color of ray1 + 0.25 * color of ray2 + 0.25 * color of ray3 + 0.25 * color of ray4
	return adaptiveSuperSampling(j, i, level+1, (int)(0 + power * place)).scale(0.25).add(adaptiveSuperSampling(j, i, level+1, (int)(1 + power * place)).scale(0.25).add(adaptiveSuperSampling(j, i, level+1, (int)(2 + power * place)).scale(0.25).add(adaptiveSuperSampling(j, i, level+1, (int)(3 + power * place)).scale(0.25))));
	
}
/**
 * 
 * @param raysColor a list of 4 Color objects
 * @return the average of the colors 
 * */
public Color averageOfColors(ArrayList<Color> raysColor)
{
	double sum_r = 0;
	double sum_g = 0;
	double sum_b = 0;
	for(Color color: raysColor)
	{
		sum_r += color.get_r();
		sum_g += color.get_g();
		sum_b += color.get_b();
	}
	return new Color(sum_r / 4, sum_g / 4, sum_b / 4);
}
/**
 * 
 * @param raysColor a list of 4 Color objects
 * @return if all the colors are equals 
 * */
public boolean isEqualsColors(ArrayList<Color> raysColor)
{
	for(int i = 0; i < 3; i++)
	{
		if(!raysColor.get(i).equals(raysColor.get(i + 1)))
				return false;
	}
	return true;
}

}
