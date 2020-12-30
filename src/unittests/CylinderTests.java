/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;
import org.junit.Test;
import geometries.Cylinder;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Cylinder c = new Cylinder(1, new Ray(new Point3D(1, 1, 1), new Vector(1, 0, 0)), 3);
		Point3D p = new Point3D(2, 0, 1);
		assertEquals("ERROR: not the correct normal", c.getNormal(p), new Vector(0, -1, 0).normalize());
	}


}
