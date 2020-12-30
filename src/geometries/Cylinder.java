package geometries;

import static primitives.Util.isZero;

import java.util.ArrayList;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class
 * 
 * @author Osnat and Hodaya
 */

public class Cylinder extends Tube {
	
	final double _height;
	
	/**
     * constructor for a new Cylinder object
     *
     * @param _radius of cylinder
     * @param _ray of cylinder
     * @param _height of cylinder
     */
	
	public Cylinder(double _radius, Ray _ray, double _height) {
		super(_radius, _ray);
		this._height = _height;
	}
	
	/**
     * @return _height
     */
	
	public double get_height() {
		return _height;
	}
	
	/**
    * @param p of cylinder
    * @return the normal to this cylinder in a given point
    */
	@Override
	public Vector getNormal(Point3D p)
	{
		Point3D p0 = _axisRay.get_POO();
		Vector v = _axisRay.get_direction();
		//t = v (P – P0)
		double t = p.subtract(p0).dotProduct(v); 
		// O = P0 + tv
	    Point3D o = null;
	    if (!isZero(t))// if it's close to 0, we'll get ZERO vector exception
		      o = p0.add(v.scale(t));
		Vector n = p.subtract(o);
		return n.normalize();
	}
	
	/**
	 * print the details of the cylinder
	 */
	@Override
	public String toString() {
		return "Cylinder [_height=" + _height + "] " + super.toString();
	}

	/**
	 * @param ray 
	 * @return the intersections of the ray with the cylinder
	 */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
