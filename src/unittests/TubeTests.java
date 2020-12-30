/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Cylinder;
import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Tube t = new Tube(1, new Ray(new Point3D(1, 1, 1), new Vector(1, 0, 0)));
		Point3D p = new Point3D(2, 0, 1);
		assertEquals("ERROR: not the correct normal", t.getNormal(p), new Vector(0, -1, 0).normalize());
	}
	
}
