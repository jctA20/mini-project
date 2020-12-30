/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	 @Test
	  public void getNormalTest() {
		 
	    Sphere s1 = new Sphere(new Point3D(0,0,0), 4);
	    Sphere s2 = new Sphere(new Point3D(1,1,1), 1);
	    
	    // ============ Equivalence Partitions Tests ==============
	    
	    assertTrue(s1.getNormal(new Point3D(0,0,4)).equals(new Vector(new Point3D(0,0,1))));
	    assertTrue(s1.getNormal(new Point3D(0,0,-4)).equals(new Vector(new Point3D(0,0,-1))));
	    assertTrue(s1.getNormal(new Point3D(0,4,0)).equals(new Vector(new Point3D(0,1,0))));
	    assertTrue(s1.getNormal(new Point3D(0,-4,0)).equals(new Vector(new Point3D(0,-1,0))));
	    assertTrue(s1.getNormal(new Point3D(4,0,0)).equals(new Vector(new Point3D(1,0,0))));
	    assertTrue(s1.getNormal(new Point3D(-4,0,0)).equals(new Vector(new Point3D(-1,0,0))));
	    
	    assertTrue(s2.getNormal(new Point3D(1,1,0)).equals(new Vector(new Point3D(0,0,-1))));
	    assertTrue(s2.getNormal(new Point3D(0,1,1)).equals(new Vector(new Point3D(-1,0,0))));
	    assertTrue(s2.getNormal(new Point3D(1,0,1)).equals(new Vector(new Point3D(0,-1,0))));
	}
	 /**
	     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
		 */
		@Test
			public void findIntersections() {
				Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

		        // ============ Equivalence Partitions Tests ==============

		        // TC01: Ray's line is outside the sphere (0 points)
		        assertEquals("Ray's line out of sphere", null,sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		        // TC02: Ray starts before and crosses the sphere (2 points)
		        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		        List<GeoPoint> result1 = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		        assertEquals("Wrong number of points", 2, result1.size());
		        if (result1.get(0).point.get_x().get() > result1.get(1).point.get_x().get())
		        	result1 = Arrays.asList(result1.get(1), result1.get(0));
		        assertEquals("Ray crosses sphere", Arrays.asList(p1, p2),Arrays.asList(result1.get(0).point, result1.get(1).point));

		        // TC03: Ray starts inside the sphere (1 point)
		        Point3D p3 = new Point3D(1.8867496997597595, 0.4622498999199199, 0);
		        List<GeoPoint> result2 = sphere.findIntersections(new Ray(new Point3D(1d/2, 0, 0), new Vector(3, 1, 0)));
		        assertEquals("Wrong number of points", 1, result2.size());
		        assertEquals("Ray inside sphere", p3, result2.get(0).point);
		        
		        // TC04: Ray starts after the sphere (0 points)
		        List<GeoPoint> result3 = sphere.findIntersections(new Ray(new Point3D(2.5, 0, 0), new Vector(3, 1, 0)));
		        assertEquals("Ray after sphere", null, result3);

		        // =============== Boundary Values Tests ==================

		        // **** Group: Ray's line crosses the sphere (but not the center)
		        // TC11: Ray starts at sphere and goes inside (1 points)
		        Point3D p4 = new Point3D(1.7999999999999998, 0.6, 0);
		        List<GeoPoint> result4 = sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(3, 1, 0)));
		        assertEquals("Wrong number of points", 1, result4.size());
		        assertEquals("Ray starts at sphere and goes inside", p4, result4.get(0).point);
		       
		        // TC12: Ray starts at sphere and goes outside (0 points)
		        List<GeoPoint> result5 = sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(-3, 1, 0)));
		        assertEquals("Ray starts at sphere and goes outside", null, result5);

		        // **** Group: Ray's line goes through the center
		        // TC13: Ray starts before the sphere (2 points)
		        Point3D p5 = new Point3D(0, 0, 0);
		        Point3D p6 = new Point3D(2, 0, 0);
		        List<GeoPoint> result6 = sphere.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(3, 0, 0)));
		        assertEquals("Wrong number of points", 2, result6.size());
		        if (result6.get(0).point.get_x().get() > result6.get(1).point.get_x().get())
		        	result6 = Arrays.asList(result6.get(1), result6.get(0));
		        assertEquals("Ray starts before sphere", Arrays.asList(p5, p6), Arrays.asList(result6.get(0).point, result6.get(1).point));
		       
		        // TC14: Ray starts at sphere and goes inside (1 points)
		        Point3D p7 = new Point3D(2, 0, 0);
		        List<GeoPoint> result7 = sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(3, 0, 0)));
		        assertEquals("Wrong number of points", 1, result7.size());
		        assertEquals("Ray starts at sphere and goes inside", p7, result7.get(0).point);
		       
		        // TC15: Ray starts inside (1 points)
		        Point3D p8 = new Point3D(2, 0, 0);
		        List<GeoPoint> result8 = sphere.findIntersections(new Ray(new Point3D(1d/2, 0, 0), new Vector(3, 0, 0)));
		        assertEquals("Wrong number of points", 1, result8.size());
		        assertEquals("Ray starts inside", p8, result8.get(0).point);
		       
		        // TC16: Ray starts at the center (1 points)
		        Point3D p9 = new Point3D(2, 0, 0);
		        List<GeoPoint> result9 = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(3, 0, 0)));
		        assertEquals("Wrong number of points", 1, result9.size());
		        assertEquals("Ray starts at the center", p9, result9.get(0).point);
		       
		        // TC17: Ray starts at sphere and goes outside (0 points)
		        List<GeoPoint> result10 = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(3, 0, 0)));
		        assertEquals("Ray starts at sphere and goes outside", null, result10);
		        
		        // TC18: Ray starts after sphere (0 points)
		        List<GeoPoint> result11 = sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(7, 0, 0)));
		        assertEquals("Ray starts after sphere", null, result11);

		        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		        // TC19: Ray starts before the tangent point
		        Point3D p12 = new Point3D(0, 0, 0);
		        List<GeoPoint> result12 = sphere.findIntersections(new Ray(new Point3D(0, -4, 0), new Vector(0, 3, 0)));
		        assertEquals("Wrong number of points", 1, result12.size());
		        assertEquals("Ray starts before the tangent point", p12, result12.get(0).point);
		        
		        // TC20: Ray starts at the tangent point(0 points)
		        List<GeoPoint> result13 = sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(0, 3, 0)));
		        assertEquals("Ray starts at the tangent point", null, result13);

		        // TC21: Ray starts after the tangent point(0 points)
		        List<GeoPoint> result14 = sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(0, 3, 0)));
		        assertEquals("Ray starts after the tangent point", null, result14);

		        // **** Group: Special cases
		        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line(0 points)
		        List<GeoPoint> result15 = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 3, 0)));
		        assertEquals("Ray's line is outside, ray is orthogonal to ray start to sphere's center line", null, result15);

			}
}
