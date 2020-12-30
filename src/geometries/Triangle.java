package geometries;

import java.util.ArrayList;

import static primitives.Util.*;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Triangle class
 * 
 * @author Osnat and Hodaya
 */

public class Triangle extends Polygon {
	
	/**
     * constructor for a new Triangle object
     *
     * @param myPoint1 vertice of the triangle
     * @param myPoint2 vertice of the triangle
     * @param myPoint3 vertice of the triangle
     */
	
	public Triangle (Point3D myPoint1, Point3D myPoint2, Point3D myPoint3)
	{
		super(new Point3D[]{myPoint1, myPoint2, myPoint3});
	}
	
	/**
     * constructor for a new Triangle object
     *
     *@param _emmission color of the triangle
     * @param myPoint1 vertice of the triangle
     * @param myPoint2 vertice of the triangle
     * @param myPoint3 vertice of the triangle
     */
	
	public Triangle (Color _emmission, Point3D myPoint1, Point3D myPoint2, Point3D myPoint3)
	{
		this(myPoint1, myPoint2, myPoint3);
		this._emmission = _emmission;
		
	}
	
	/**
     * constructor for a new Triangle object
     *
     *@param _material material of the triangle
     *@param _emmission color of the triangle
     * @param myPoint1 vertice of the triangle
     * @param myPoint2 vertice of the triangle
     * @param myPoint3 vertice of the triangle
     */
	
	public Triangle (Color _emmission, Material _material, Point3D myPoint1, Point3D myPoint2, Point3D myPoint3)
	{
		this(_emmission, myPoint1, myPoint2, myPoint3);
		this._material = _material;
		
	}
	
	/**
     * constructor for a new Triangle object
     *
     *@param _material material of the triangle
     * @param myPoint1 vertice of the triangle
     * @param myPoint2 vertice of the triangle
     * @param myPoint3 vertice of the triangle
     */
	
	public Triangle (Material _material, Point3D myPoint1, Point3D myPoint2, Point3D myPoint3)
	{
		this(myPoint1, myPoint2, myPoint3);
		this._material = _material;
		
	}
	/**
	 * print the details of the triangle
	 */
	@Override
	public String toString() {
		return "Triangle [_vertices=" + _vertices + ", _plane=" + _plane + "]";
	}
	/**
	 * @param ray to find intersections points with the triangle
     * @return list of the intersections points with the triangle
     */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		//v1 = p1 - p0
		Vector v1 = _vertices.get(0).subtract(ray.get_POO());
		//v2 = p2 - p0
		Vector v2 = _vertices.get(1).subtract(ray.get_POO());
		//v3 = p3 - p0
		Vector v3 = _vertices.get(2).subtract(ray.get_POO());
		//N1 = normalize(v1 x v2)
		Vector N1 = v1.crossProduct(v2).normalize();
		//N1 = normalize(v2 x v3)
		Vector N2 = v2.crossProduct(v3).normalize();
		//N1 = normalize(v3 x v1)
		Vector N3 = v3.crossProduct(v1).normalize();
		//v*N1
		double sign1 = ray.get_direction().dotProduct(N1);
		//v*N2
		double sign2 = ray.get_direction().dotProduct(N2);
		//v*N3
		double sign3 = ray.get_direction().dotProduct(N3);
        // if one or more are 0.0 – no intersection
		alignZero(sign1);
		alignZero(sign2);
		alignZero(sign3);
		//The point is inside if all v*Ni have the same sign
		ArrayList<GeoPoint> intsersection = new ArrayList<GeoPoint>();
		if((sign1 > 0 && sign2 > 0 && sign3 > 0) || (sign1 < 0 && sign2 < 0 && sign3 < 0))
		{
			intsersection = this._plane.findIntersections(ray);
			if (intsersection != null) //to change the geometry type to this
				for (GeoPoint i: intsersection)
					i.geometry = this;
			return intsersection;
		}
		return null;
	}

}
