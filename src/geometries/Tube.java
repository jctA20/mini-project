package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

import java.util.ArrayList;

/**
 * Represents an infinite tube in the 3D space.
 * That is, the cylinder does not have a length.
 * 
 * @author Osnat and Hodaya
 */

public class Tube extends RadialGeometry {
	
	 /**
     *  represents the direction and the reference point
     */
	
	final Ray _axisRay;
	
	/**
     * constructor for a new Tube object
     *
     * @param _radius the radius of the tube
     * @param _ray    the direction of the tube from a center point
     */
	public Tube(double _radius, Ray _ray) {
		super(_radius);
		_axisRay = new Ray(_ray);	
	}
	
	 /**
    *
    * @return ray of the tube
    */
	
	public Ray get_axisRay() {
		return _axisRay;
	}
	
	/**
    *
    * @param p point to calculate the normal
    * @return the normal to this tube in a given point
    */
	
	@Override
	public Vector getNormal(Point3D p)
	{
		Point3D p0 = _axisRay.get_POO();
		Vector v = _axisRay.get_direction();
		//t = v (P – P0)
		double t = p.subtract(p0).dotProduct(v); 
		// O = P0 + tv
	    Point3D o=null;
	    if (!isZero(t))// if it's close to 0, we'll get ZERO vector exception
		      o = p0.add(v.scale(t));
		Vector n = p.subtract(o);
		return n.normalize();
	}
	/**
	 * print the details of the tube
	 */
	@Override
	public String toString() {
		return "Tube [_axisRay=" + _axisRay + "] "+ super.toString();
	}
	/**
	 * @param ray 
	 * @return the intersections of the ray with the tube
	 */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
