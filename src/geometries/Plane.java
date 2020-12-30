package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Plane class
 * 
 * @author Osnat and Hodaya
 */

public class Plane extends Geometry {
	final Point3D _p;
	final Vector _normal;
	
	/**
     * constructor for a new Plane object
     *
     * @param point of plane
     * @param normal vector of plane
     */
	
	public Plane(Point3D point, Vector normal) {
		super();
		this._p = new Point3D(point);
		this._normal = new Vector(normal.normalize());
	}
	
	/**
     * constructor for a new Plane object
     *
     * @param material of Plane
     * @param point of plane
     * @param normal vector of plane
     */
	
	public Plane(Material material, Point3D point, Vector normal) {
		this(point, normal);
		this._material = material;
	}
	
	/**
     * constructor for a new Plane object
     *
     * @param emmission the color of the plane
     * @param point of plane
     * @param normal vector of plane
     */
	
	public Plane(Color emmission, Point3D point, Vector normal) {
		this(point, normal);
		this._emmission = emmission;
	}
	
	/**
     * constructor for a new Plane object
     *
     * @param emmission of Plane
     * @param material of Plane
     * @param point of plane
     * @param normal vector of plane
     */
	
	public Plane(Color emmission, Material material, Point3D point, Vector normal) {
		this(emmission, point, normal);
		this._material = material;
	}
	
	/**
     * constructor for a new Plane object
     *
     * @param myPoint1 of plane
     * @param myPoint2 of plane
     * @param myPoint3 of plane
     */
	
	public Plane(Point3D myPoint1, Point3D myPoint2, Point3D myPoint3) {
		super();
		Vector myVec1 = new Vector(myPoint2.subtract(myPoint3));
		Vector myVec2 = new Vector(myPoint3.subtract(myPoint1));
		this._p = new Point3D(myPoint1);
		this._normal = new Vector(myVec1.crossProduct(myVec2).normalize());
	}

	
	/**
    *
    * @return the normal vector to the plane
    */
	
	public Vector getNormal()
	{
		return this._normal;
	}
	
	/**
    *
    * @param p of plane
    * @return the normal to this plane in a given point p
    */
	
	public Vector getNormal(Point3D p)
	{
		return this._normal;
	}
	
	/**
	 * print the details of the plane
	 */
	@Override
	public String toString() {
		return "Plane [point=" + _p + ", normal=" + _normal + "]";
	}
	/**
    * @return returns _p point of the plane
    */
	
	public Point3D getPoint() {
		return _p;
	}
	/**
	 * @param ray to find intersections points with the plane
     * @return list of the intersections points with the plane
     */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {

		//N*v
		double t_denominator = _normal.dotProduct(ray.get_direction());
		//if the ray is parallel to the plane - there is no intersections points
		if(t_denominator == 0)
			return null;
		//if the ray start in the normal point - there is no intersections points (q0 -p0 is vector 0 , ERROR)
		if(_normal.get_head().equals(ray.get_POO()))
			return null;
		// (N * (q0 - p0)) / (N*v)
		double t = _normal.dotProduct(_p.subtract(ray.get_POO())) / t_denominator;
		Point3D p;
		//only if t>0
		if(!isZero(t) && t>0)
			//p = p0 + t*v
			p = ray.getPoint(t);
		else
			//if t<=0 there is no intersections points
			return null;
		ArrayList<GeoPoint> intsersection = new ArrayList<GeoPoint>();
		intsersection.add(new GeoPoint(this, p));
		return intsersection;
	}
		
}

