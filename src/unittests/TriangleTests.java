/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Polygon;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		// ============ Equivalence Partitions Tests ==============
        
		Triangle t = new Triangle(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        // The points is the vertices of the triangle
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(0, 0, 0)));
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(0, 1, 0)));
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(1, 0, 0)));
        // The points is on the edges of the triangle
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(1d/2, 1d/2, 0)));
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(0, 1d/2, 0)));
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(1d/2, 0, 0)));
        // The points is in the triangle
        assertEquals("Bad normal to trinagle", new Vector(0, 0, 1), t.getNormal(new Point3D(0.1, 1d/2, 0)));
	}
	 /**
		 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
		 */
		@Test
		public void findIntsersections() {
			Triangle triangle = new Triangle(new Point3D(-1, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));

	        // ============ Equivalence Partitions Tests ==============
			// TC01: Point inside the triangle (1 points)
			Point3D p1 = new Point3D(0, 0.41, 0);
	        List<GeoPoint> result1 = triangle.findIntersections(new Ray(new Point3D(0, 0.41, 2), new Vector(0, 0, -1)));
	        assertEquals("Wrong number of points", 1, result1.size());
	        assertEquals("Point inside the triangle", p1, result1.get(0).point);

	        // TC02: Point outside against edge (0 points)
	        List<GeoPoint> result2 = triangle.findIntersections(new Ray(new Point3D(-1, 1, 2), new Vector(0, 0, -1)));
	        assertEquals("Point outside against edge", null, result2);
	    
	        // TC03: Point outside against vertex (0 points)
	        List<GeoPoint> result3 = triangle.findIntersections(new Ray(new Point3D(-1.56017,-0.40906,2), new Vector(0, 0, -1)));
	        assertEquals("Point outside against vertex", null, result3);
	        
	        // =============== Boundary Values Tests ==================
	        // **** Group: Ray begins "before" the plane
	        
	        // TC04: Point on edge (0 points)
	        List<GeoPoint> result4 = triangle.findIntersections(new Ray(new Point3D(-0.38, 0.62, 2), new Vector(0, 0, -1)));
	     	assertEquals("Point on edge", null, result4);
	     	
	     	// TC05: Point in vertex (0 points)
	        List<GeoPoint> result5 = triangle.findIntersections(new Ray(new Point3D(-1, 0, 2), new Vector(0, 0, -1)));
	     	assertEquals("Point in vertex", null, result5);
	     	
	        // TC06: Point on edge's continuation (0 points)
	        List<GeoPoint> result6 = triangle.findIntersections(new Ray(new Point3D(2, 0, 2), new Vector(0, 0, -1)));
	     	assertEquals("Point on edge's continuation", null, result6);
			
		}

}
