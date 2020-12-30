package geometries;


import java.util.ArrayList;
import java.util.List;
import primitives.Ray;
import primitives.Point3D;

/**
 * interface Intersectable 
 *
 * @author Osnat and Hodaya
 */

public interface Intersectable {
	
	ArrayList<GeoPoint> findIntersections(Ray ray);
	
	/**
	 * GeoPoint helper class
	 * 
	 * @author Osnat and Hodaya
	 */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point3D point;
		/**
		 * GeoPoint constructor
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			super();
			this.geometry = geometry;
			this.point = point;
		}
		/**
		 * @param obj - a Intersectable object
		 * @return if two Intersectable object are equals
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!(geometry.getClass().equals(other.geometry.getClass())))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
		  
	}
}
