/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Integration Testing between Camera Rays and Calculation of Ray Cuts with Geometric bodies
 * @author Hodaya Cohen and Osnat Orenstein
 */
public class RayIntersectionTests {
	/**
	 * Integration Testing between Camera Rays and Calculation of Ray Cuts with Sphere
	 */
	@Test
	public void SphereIntegrationTesting() {
        
        // TC01: view plane 3X3, Sphere radius = 1
		Camera camera1 = new Camera(Point3D._zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
		Sphere sphere1 = new Sphere(new Point3D(0, 0, 3), 1);
		ArrayList<GeoPoint> intersections1 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp1 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp1 = sphere1.findIntersections(camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp1 != null)
					intersections1.addAll(temp1);
			}
		assertEquals("view plane 3X3, Sphere radius = 1, Wrong number of points", 2, intersections1.size());
		
		// TC02: view plane 3X3, Sphere radius = 2.5
		Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		Sphere sphere2 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
		ArrayList<GeoPoint> intersections2 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp2 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp2 = sphere2.findIntersections(camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp2 != null)
					intersections2.addAll(temp2);
			}
		assertEquals("view plane 3X3, Sphere radius = 2.5, Wrong number of points", 18, intersections2.size());
		
		// TC03: view plane 3X3, Sphere radius = 2
		Camera camera3 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		Sphere sphere3 = new Sphere(new Point3D(0, 0, 2), 2);
		ArrayList<GeoPoint> intersections3 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp3 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp3 = sphere3.findIntersections(camera3.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp3 != null)
					intersections3.addAll(temp3);
			}
		assertEquals("view plane 3X3, Sphere radius = 2, Wrong number of points", 10, intersections3.size());
		
		// TC04: view plane 3X3, Sphere radius = 4
		Camera camera4 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		Sphere sphere4 = new Sphere(new Point3D(0, 0, 0), 4);
		ArrayList<GeoPoint> intersections4 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp4 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp4 = sphere4.findIntersections(camera4.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp4 != null)
					intersections4.addAll(temp4);
			}
		assertEquals("view plane 3X3, Sphere radius = 4, Wrong number of points", 9, intersections4.size());
		
		// TC05: view plane 3X3, Sphere radius = 0.5
		Camera camera5 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		Sphere sphere5 = new Sphere(new Point3D(0, 0, -1), 0.5);
		ArrayList<GeoPoint> intersections5 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp5 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp5 = sphere5.findIntersections(camera5.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp5 != null)
					intersections5.addAll(temp5);
			}
		assertTrue("view plane 3X3, Sphere radius = 0.5, Wrong number of points", intersections5.isEmpty());
	}
	
	/**
	 * Integration Testing between Camera Rays and Calculation of Ray Cuts with Plane
	 */
	@Test
	public void PlaneIntegrationTesting()
	{
		// TC01: view plane 3X3, Plane parallel to view plane
		Camera camera1 = new Camera(Point3D._zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
		Plane plane1 = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1));
		ArrayList<GeoPoint> intersections1 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp1 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp1 = plane1.findIntersections(camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp1 != null)
					intersections1.addAll(temp1);
			}
		assertEquals("view plane 3X3, Plane parallel to view plane, Wrong number of points", 9, intersections1.size());
		
		// TC02: view plane 3X3, Plane is not parallel to view plane
		Camera camera2 = new Camera(Point3D._zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
		Plane plane2 = new Plane(new Point3D(0, 0, 5), new Vector(0, -1, 2));
		ArrayList<GeoPoint> intersections2 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp2 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp2 = plane2.findIntersections(camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp2 != null)
					intersections2.addAll(temp2);
			}
		assertEquals("Plane is not parallel to view plane, Wrong number of points", 9, intersections2.size());
		
		// TC03: view plane 3X3, Plane is not parallel to view plane
		Camera camera3 = new Camera(Point3D._zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
		Plane plane3 = new Plane(new Point3D(0, 0, 5), new Vector(0, -1, 1));
		ArrayList<GeoPoint> intersections3 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp3 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp3 = plane3.findIntersections(camera3.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp3 != null)
					intersections3.addAll(temp3);
			}
		assertEquals("Plane is not parallel to view plane, Wrong number of points", 6, intersections3.size());
			
	}
	
	/**
	 * Integration Testing between Camera Rays and Calculation of Ray Cuts with Triangle
	 */
	@Test
	public void TringleIntegrationTesting()
	{
		// TC01: view plane 3X3, small Triangle parallel to view plane
		Camera camera1 = new Camera(Point3D._zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
		Triangle triangle1 = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		ArrayList<GeoPoint> intersections1 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp1 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp1 = triangle1.findIntersections(camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp1 != null)
					intersections1.addAll(temp1);
			}
		assertEquals("view plane 3X3, small Triangle parallel to view plane, Wrong number of points", 1, intersections1.size());
		
		// TC02: view plane 3X3, big Triangle parallel to view plane
		Camera camera2 = new Camera(Point3D._zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
		Triangle triangle2 = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		ArrayList<GeoPoint> intersections2 = new ArrayList<GeoPoint>();
		ArrayList<GeoPoint> temp2 = new ArrayList<GeoPoint>();
		for(int j = 0; j < 3; j++)
			for(int i = 0; i < 3; i++)
			{
				temp2 = triangle2.findIntersections(camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(temp2 != null)
					intersections2.addAll(temp2);
			}
		assertEquals("view plane 3X3, big Triangle parallel to view plane, Wrong number of points", 2, intersections2.size());
	}

}
