/**
 * 
 */
package unittests;
import static primitives.Util.isZero;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(1, 2, 3); 
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(-2, 0, 0), new Point3D(-1, 2, 3).subtract(p1));
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(-2, -4, -6), new Point3D(-1, -2, -3).subtract(p1)); 
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(0, -4, -6), new Point3D(1, -2, -3).subtract(p1));
		Point3D p2 = new Point3D(-1, 2, 3); 
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(0, -4, -6), new Point3D(-1, -2, -3).subtract(p2));
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(2, -4, -6), new Point3D(1, -2, -3).subtract(p2));
		Point3D p3 = new Point3D(-1, -2, -3);
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(2, 0, 0), new Point3D(1, -2, -3).subtract(p3));
		// =============== Boundary Values Tests ==================
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(-1, -1, -1), new Point3D(1, 2, 3).subtract(new Point3D(2, 3, 4)));
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(1, -1, -1), new Point3D(-1, 2, 3).subtract( new Point3D(-2, 3, 4))); 
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(1, 1, 1), new Point3D(-1, -2, -3).subtract( new Point3D(-2, -3, -4)));
		assertEquals("ERROR: Point - Point does not work correctly", new Vector(-1, 0, 0), new Point3D(1, -2, -3).subtract( new Point3D(2, -2, -3)));
		try {
			new Point3D(1, 2, 3).subtract(new Point3D(1, 2, 3));
            fail("resulte of sub is vector 0");
        } catch (IllegalArgumentException e) {}		
	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		
		// ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3); 
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(0, 4, 6), new Point3D(-1, 2, 3).add(v1));
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(-1, 0, 0), new Point3D(-2, -2, -3).add(v1)); 
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(2, 0, 0), new Point3D(1, -2, -3).add(v1));
		Vector v2 = new Vector(-1, 2, 3); 
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(-2, 0, 0), new Point3D(-1, -2, -3).add(v2));
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(1, 0, 0), new Point3D(2, -2, -3).add(v2));
		Vector v3 = new Vector(-1, -2, -3);
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(0, -4, -6), new Point3D(1, -2, -3).add(v3));
		// =============== Boundary Values Tests ==================
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(3, 5, 7), new Point3D(1, 2, 3).add(new Vector(2, 3, 4)));
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(-3, 5, 7), new Point3D(-1, 2, 3).add( new Vector(-2, 3, 4))); 
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(-3, -5, -7), new Point3D(-1, -2, -3).add( new Vector(-2, -3, -4)));
		assertEquals("ERROR: Point + Vector does not work correctly", new Point3D(3, -4, -6), new Point3D(1, -2, -3).add( new Vector(2, -2, -3)));
	}

	/**
	 * Test method for {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {
		// ============ Equivalence Partitions Tests ==============
		Point3D v1 = new Point3D(1, 2, 3); 
        assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(-1, 2, 3).distanceSquared(v1) - 4));
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(-2, -2, -3).distanceSquared(v1) - 61)); 
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(1, -2, -3).distanceSquared(v1) - 52));
		Point3D v2 = new Point3D(-1, 2, 3); 
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(-1, -2, -3).distanceSquared(v2) - 52));
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(2, -2, -3).distanceSquared(v2) - 61));
		Point3D v3 = new Point3D(-1, -2, -3);
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(1, -2, -3).distanceSquared(v3) - 4));
		// =============== Boundary Values Tests ==================
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(1, 2, 3).distanceSquared(new Point3D(2, 3, 4)) - 3));
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(-1, 2, 3).distanceSquared( new Point3D(-2, 3, 4)) - 3)); 
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(-1, -2, -3).distanceSquared( new Point3D(-2, -3, -4)) - 3));
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(1, -2, -3).distanceSquared( new Point3D(2, -2, -3)) - 1));
	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		Point3D v1 = new Point3D(1, 2, 3); 
        assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(-1, 2, 3).distance(v1) - Math.sqrt(4)));
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(-2, -2, -3).distance(v1) - Math.sqrt(61))); 
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(1, -2, -3).distance(v1) - Math.sqrt(52)));
		Point3D v2 = new Point3D(-1, 2, 3); 
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(-1, -2, -3).distance(v2) - Math.sqrt(52)));
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(2, -2, -3).distance(v2) - Math.sqrt(61)));
		Point3D v3 = new Point3D(-1, -2, -3);
		assertTrue("ERROR: distanceSquared() does not work correctly", isZero(new Point3D(1, -2, -3).distance(v3) - Math.sqrt(4)));
		// =============== Boundary Values Tests ==================
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(1, 2, 3).distance(new Point3D(2, 3, 4)) - Math.sqrt(3)));
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(-1, 2, 3).distance( new Point3D(-2, 3, 4)) - Math.sqrt(3))); 
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(-1, -2, -3).distance( new Point3D(-2, -3, -4)) - Math.sqrt(3)));
		assertTrue("ERROR: distance() does not work correctly", isZero(new Point3D(1, -2, -3).distance( new Point3D(2, -2, -3)) - Math.sqrt(1)));
	}

}
