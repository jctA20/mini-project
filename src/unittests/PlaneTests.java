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
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		Plane plane = new Plane(new Point3D(4, 5, 6), new Vector(1, 0, 0));
		
		// ============ Equivalence Partitions Tests ==============
	
		assertEquals("ERROR: Norma does not work correctly", plane.getNormal(new Point3D(1, 2, 3)), new Vector(1, 0, 0));
		assertEquals("ERROR: Norma does not work correctly", plane.getNormal(new Point3D(-1, 2, 3)), new Vector(1, 0, 0));
		assertEquals("ERROR: Norma does not work correctly", plane.getNormal(new Point3D(1, -2, -3)), new Vector(1, 0, 0));
		assertEquals("ERROR: Norma does not work correctly", plane.getNormal(new Point3D(-1, -2, -3)), new Vector(1, 0, 0));
	}
	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void findIntsersections() {
		Plane plane = new Plane(new Point3D(1, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 points)
		Point3D p1 = new Point3D(1, 0.6666666666666667, 1.3333333333333333);
        List<GeoPoint> result1 = plane.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 2)));
        assertEquals("Wrong number of points", 1, result1.size());
        assertEquals("Ray intersects the plane", p1, result1.get(0).point);

        // TC02: Ray does not intersects the plane (0 points)
        assertEquals("Ray does not intersects the plane", null, plane.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(-3, 1, 0))));

        // =============== Boundary Values Tests ==================
        // **** Group: Ray is parallel to the plane
        // TC03: The ray included in the plane (0 points)
        assertEquals("The ray included in the plane", null, plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0))));

        // TC04: The ray not included in the plane (0 points)
        assertEquals("The ray not included in the plane", null, plane.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 1, 0))));
       
        // **** Group: Ray is orthogonal to the plane
        // TC05: The ray before the plane (1 points)
        Point3D p5 = new Point3D(1, 2, 0);
        List<GeoPoint> result5 = plane.findIntersections(new Ray(new Point3D(-1, 2, 0), new Vector(9, 0, 0)));
        assertEquals("Wrong number of points", 1, result5.size());
        assertEquals("Ray intersects the plane", p5, result5.get(0).point);
	
        // TC06: The ray in the plane (0 points)
        assertEquals("The ray not included in the plane", null, plane.findIntersections(new Ray(new Point3D(1, 3, 0), new Vector(2, 0, 0))));
        
        // TC07: The ray after the plane (0 points)
        assertEquals("The ray not included in the plane", null, plane.findIntersections(new Ray(new Point3D(6, 2, 0), new Vector(6, 0, 0))));
       
        // **** Group: Ray is neither orthogonal nor parallel to the plane
        // TC08: Ray begins at the plane(0 points)
        assertEquals("Ray begins at the plane", null, plane.findIntersections(new Ray(new Point3D(1, 4, 0), new Vector(-3, 1, 0))));

        // TC09: Ray begins in the same point which appears as reference point in the plane(0 points)
        assertEquals("Ray begins in the same point which appears as reference point in the plane", null, plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-3, 1, 0))));
        
        
	}

}
