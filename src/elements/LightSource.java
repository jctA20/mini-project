/**
 * 
 */
package elements;

import primitives.*;

/**
 * interface LightSource
 * 
 * @author Hodaya and Osnat
 *
 */
public interface LightSource {
	
	/**
	 * @param p point
	 * @return the _intensity of the color
	 */
	public Color getIntensity(Point3D p);
	
	/**
	 * @param p point
	 * @return L normal Vector in p point
	 */
	public Vector getL(Point3D p);
	/**
	 * 
	 * @param point Point3D
	 * @return the distance between point to the LightSource
	 */
	public double getDistance(Point3D point);
	/**
	 * @return the _radius of the Light Source
	 */
	public double get_radius();
	/**
	 * @return the _position of PointLight and SpotLight
	 */
	public Point3D get_position();


}
