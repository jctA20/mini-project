package geometries;

import java.util.ArrayList;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
public class Sphere extends RadialGeometry {
	
	 /**
     * The center of the sphere
     * 
     * @author Osnat and Hodaya
     */
	
	final Point3D _center;
	
	/**
     * constructor for a new sphere object.
     * @param _radius the radius of the sphere
     * @param _center the center point of the sphere
     */

	
	public Sphere(Point3D _center,double _radius) {
		super(_radius);
		this._center = new Point3D(_center);
	}
	
	/**
     * constructor for a new sphere object.
     * @param _emmission the color of the sphere
     * @param _radius the radius of the sphere
     * @param _center the center point of the sphere
     */

	
	public Sphere(Color _emmission, Point3D _center,double _radius) {
		this(_center, _radius);
		this._emmission = _emmission;
	}
	
	/**
     * constructor for a new sphere object.
     * @param _material the material of the sphere
     * @param _emmission the color of the sphere
     * @param _radius the radius of the sphere
     * @param _center the center point of the sphere
     */
	public Sphere( Color _emmission, Material _material, Point3D _center,double _radius) {
		this(_emmission, _center, _radius);
		this._material = _material;
	}
	
	/**
     * constructor for a new sphere object.
     * @param _material the color of the sphere
     * @param _radius the radius of the sphere
     * @param _center the center point of the sphere
     */
	public Sphere(Material _material, Point3D _center,double _radius) {
		this(_center, _radius);
		this._material = _material;
	}
	
	/**
     * getter for the center property
     * @return the center of the sphere
     */
	
	public Point3D get_center() {
		return _center;
	}
	
	/**
     * @return the normal to this sphere in a given point
     */
	
	public Vector getNormal(Point3D p)
	{
		Vector n = new Vector(p.subtract(_center));
		return n.normalize();
	}
	/**
	 * print the details of the sphere
	 */
	@Override
	public String toString() {
		return "Sphere [_center=" + _center + "] " + super.toString();
	}
	/**
	 * @param ray to find intersections points with the sphere
     * @return list of the intersections points with the sphere
     */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		Point3D p0 = new Point3D(ray.get_POO());
		//if the ray starts at the center add epsilon 
		if(_center.equals(ray.get_POO()))
			p0 = new Point3D(ray.get_POO().get_x().get() + 0.1111111115,ray.get_POO().get_y().get(), ray.get_POO().get_z().get());
		//now we need new ray because of we add epsilon to _POO
		Ray myRay = new Ray(p0,ray.get_direction());
		//u = o - p0
		Vector u = _center.subtract(p0);
		//t_m = v * u
		double t_m = myRay.get_direction().dotProduct(u);
		//d = sqrt(|u|^2 - t_m^2)
		double d = Math.sqrt(u.lengthSquared() - t_m*t_m);
		//there are no intersections
		if(d>_radius)		
		   return null;
		//t_h = sqrt(r^2 - d^2)
		double t_h = Math.sqrt(_radius*_radius - d*d);
		//t1,2 = t_m +- t_h
		double t1 = t_m + t_h;
		double t2 = t_m - t_h;
		Point3D p1 = null;
		Point3D p2 = null;
		//if the ray tangent to the sphere - t_h=0
				if(t1 == t2)
					t2 = -1; //that`s for that it will not return the same point twice
		//only if t1>0
		if(!isZero(t1) && t1>0)
			//p1 = p0 + t1*v
			p1 = myRay.getPoint(t1);
		//only if t2>0
		if(!isZero(t2) && t2>0)
			//p2 = p0 + t2*v
			p2 = myRay.getPoint(t2);
		//if it is no intersections points
		if(p1 == null && p2 == null)
			return null;
		ArrayList<GeoPoint> intsersection = new ArrayList<GeoPoint>();
		if(p1 != null)
			intsersection.add(new GeoPoint(this, p1));;
		if(p2 != null)
			intsersection.add(new GeoPoint(this, p2));;
		return intsersection;
			
	}

	
	
	

}
