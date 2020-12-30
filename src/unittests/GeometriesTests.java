package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class GeometriesTests {
	
	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {

        
        //build a list of geometries 
		Sphere sphere = new Sphere(new Point3D(1,0,0), 4);
		Triangle triangle = new Triangle(new Point3D(-1, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		Plane plane = new Plane(new Point3D(1, 0, 0), new Vector(0, 0, 1));
		Geometries geometries1 = new Geometries(sphere, triangle, plane);
		
		
		// ============ Equivalence Partitions Tests ==============    
		// TC01: Some (but not all) geometries are cut (3 points)
        List<GeoPoint> result1 = geometries1.findIntersections(new Ray(new Point3D(0, 2, 5), new Vector(0, 0, -1)));
        assertEquals("Some (but not all) geometries are cut", 3, result1.size());	

        
		// =============== Boundary Values Tests ==================
        // TC02: Empty list of geometries (0 points)
		Geometries geometries2 = new Geometries();
        List<GeoPoint> result2 = geometries2.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 2)));
        assertEquals("Empty list of geometries", null, result2);
        
        // TC03: No geometry is cut (0 points)  
        List<GeoPoint> result3 = geometries1.findIntersections(new Ray(new Point3D(-4, 6, 2), new Vector(1, 0, 0)));
        assertEquals("No geometry is cut", null, result3);
        
        // TC04: Only one geometry is cut (2 points)
        List<GeoPoint> result4 = geometries1.findIntersections(new Ray(new Point3D(-4, 0, 2), new Vector(1, 0, 0)));
        assertEquals("Only one geometry is cut", 2, result4.size());
        
        // TC05: All geometries are cut (4 points)
        List<GeoPoint> result5 = geometries1.findIntersections(new Ray(new Point3D(0, 1d/2, 5), new Vector(0, 0, -1)));
        assertEquals("All geometries are cut", 4, result5.size());
	}

}
