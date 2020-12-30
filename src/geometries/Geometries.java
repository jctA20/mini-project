package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	final List<Geometry>  _geometries;
	
	/**
	 * default constructor
	 */
	public Geometries() {
		super();
		this._geometries = new ArrayList<Geometry>();
	}
	
	/**
	 * constructor
	 * @param _geometries list 
	 */
	public Geometries(Geometry... _geometries) {
		super();
		this._geometries = Arrays.asList(_geometries);
	}

	/**
	 * @param ray to find intersections points with all the geometries in the list
     * @return list of the intersections points with all the geometries in the list
     */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		ArrayList<GeoPoint> intersections = new ArrayList<GeoPoint>();
		//temp - for save the points in the i geometry
		ArrayList<GeoPoint> temp = new ArrayList<GeoPoint>();
		for(Geometry i: _geometries)
		{
		temp = i.findIntersections(ray);
		if(temp != null)
			//if there is intersections points with i geometry - copy all this points to intersections list
			for(GeoPoint j: temp)
				intersections.add(j);
		}
		if(intersections.isEmpty())
			return null;
		return intersections;
	}
	/**
	 * @param geometries add to list 
	 * add a geometry/ies
	 */
	public void add(Geometry... geometries)
	{
		_geometries.addAll(Arrays.asList(geometries));
	}
	

}
