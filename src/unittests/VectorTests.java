/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;
import org.junit.Test;

import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author Hodaya and Osnat
 *
 */
public class VectorTests {
	
	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3); 
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(-2, 0, 0), new Vector(-1, 2, 3).subtract(p1));
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(-2, -4, -6), new Vector(-1, -2, -3).subtract(p1)); 
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(0, -4, -6), new Vector(1, -2, -3).subtract(p1));
		Point3D p2 = new Point3D(-1, 2, 3); 
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(0, -4, -6), new Vector(-1, -2, -3).subtract(p2));
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(2, -4, -6), new Vector(1, -2, -3).subtract(p2));
		Point3D p3 = new Point3D(-1, -2, -3);
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(2, 0, 0), new Vector(1, -2, -3).subtract(p3));
		// =============== Boundary Values Tests ==================
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(-1, -1, -1), new Vector(1, 2, 3).subtract(new Point3D(2, 3, 4)));
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(1, -1, -1), new Vector(-1, 2, 3).subtract( new Point3D(-2, 3, 4))); 
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(1, 1, 1), new Vector(-1, -2, -3).subtract( new Point3D(-2, -3, -4)));
		assertEquals("ERROR: Vector - Point does not work correctly", new Vector(-1, 0, 0), new Vector(1, -2, -3).subtract( new Point3D(2, -2, -3)));
		try {
			new Point3D(1, 2, 3).subtract(new Point3D(1, 2, 3));
            fail("resulte of sub is vector 0");
        } catch (IllegalArgumentException e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		        Vector v1 = new Vector(1, 2, 3); 
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(0, 4, 6), new Vector(-1, 2, 3).add(v1));
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(-1, 0, 0), new Vector(-2, -2, -3).add(v1)); 
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(2, 0, 0), new Vector(1, -2, -3).add(v1));
				Vector v2 = new Vector(-1, 2, 3); 
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(-2, 0, 0), new Vector(-1, -2, -3).add(v2));
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(1, 0, 0), new Vector(2, -2, -3).add(v2));
				Vector v3 = new Vector(-1, -2, -3);
				assertEquals("ERROR: Point + Vector does not work correctly", new Vector(0, -4, -6), new Vector(1, -2, -3).add(v3));
				// =============== Boundary Values Tests ==================
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(3, 5, 7), new Vector(1, 2, 3).add(new Vector(2, 3, 4)));
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(-3, 5, 7), new Vector(-1, 2, 3).add( new Vector(-2, 3, 4))); 
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(-3, -5, -7), new Vector(-1, -2, -3).add( new Vector(-2, -3, -4)));
				assertEquals("ERROR: Vector + Vector does not work correctly", new Vector(3, -4, -6), new Vector(1, -2, -3).add( new Vector(2, -2, -3)));
				try {
					new Vector(1, 2, 3).add(new Vector(-1, -2, -3));
		            fail("resulte of add is vector 0");
		        } catch (IllegalArgumentException e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		assertTrue ("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
		assertTrue ("ERROR: dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
		
		
		Vector v1 = new Vector(1, 2, 3); 
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(-1, 2, 3).dotProduct(v1) - 12));
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(-2, -2, -3).dotProduct(v1) + 15));
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(1, -2, -3).dotProduct(v1) + 12));
		Vector v2 = new Vector(-1, 2, 3); 
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(-1, -2, -3).dotProduct(v2) + 12));
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(2, -2, -3).dotProduct(v2)+15));
		Vector v3 = new Vector(-1, -2, -3);
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(1, -2, -3).dotProduct(v3) - 12));
		// =============== Boundary Values Tests ==================
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(1, 2, 3).dotProduct(new Vector(2, 3, 4)) - 20));
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(-1, 2, 3).dotProduct( new Vector(-2, 3, 4)) - 20));
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(-1, -2, -3).dotProduct( new Vector(-2, -3, -4)) - 20 ));
		assertTrue ("ERROR: dotProduct() does not work correctly", isZero(new Vector(1, -2, -3).dotProduct( new Vector(2, -2, -3)) - 15));
		assertTrue ("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(new Vector(1, 2, 3).dotProduct(new Vector(0, 3, -2))));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		
		 Vector v1 = new Vector(1, 2, 3); 
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, 6, -4), new Vector(-1, 2, 3).crossProduct(v1));
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, 3, -2), new Vector(-2, -2, -3).crossProduct(v1)); 
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, -6, 4), new Vector(1, -2, -3).crossProduct(v1));
			Vector v2 = new Vector(-1, 2, 3); 
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, 6, -4), new Vector(-1, -2, -3).crossProduct(v2));
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, -3, 2), new Vector(2, -2, -3).crossProduct(v2));
			Vector v3 = new Vector(-1, -2, -3);
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, 6, -4), new Vector(1, -2, -3).crossProduct(v3));
			// =============== Boundary Values Tests ==================
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(-1, 2, -1), new Vector(1, 2, 3).crossProduct(new Vector(2, 3, 4)));
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(-1, -2, 1), new Vector(-1, 2, 3).crossProduct( new Vector(-2, 3, 4))); 
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(-1, 2, -1), new Vector(-1, -2, -3).crossProduct( new Vector(-2, -3, -4)));
			assertEquals("ERROR: crossProduct() does not work correctly", new Vector(0, -3, 2), new Vector(1, -2, -3).crossProduct( new Vector(2, -2, -3)));
			try {// test zero vector 
				new Vector(1, 2, 3).crossProduct(new Vector(-2, -4, -6));
	            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
	        } catch (Exception e) {}
		
		
		// ============ Equivalence Partitions Tests ==============
	        Vector vr = new Vector(1, 2, 3).crossProduct(new Vector(0, 3, -2));
	        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
	        assertTrue ("ERROR: crossProduct() wrong result length", isZero(vr.length() - new Vector(1, 2, 3).length() * new Vector(0, 3, -2).length()));
	        // Test cross-product result orthogonality to its operands
	        assertTrue ("ERROR: crossProduct() result is not orthogonal to its operands", isZero(vr.dotProduct(new Vector(1, 2, 3))) && isZero(vr.dotProduct(new Vector(0, 3, -2))));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		assertTrue ("ERROR: lengthSquared() wrong value", isZero(new Vector(1, 2, 3).lengthSquared() - 14));
		assertTrue ("ERROR: lengthSquared() wrong value", isZero(new Vector(-1, 2, 3).lengthSquared() - 14));
		assertTrue ("ERROR: lengthSquared() wrong value", isZero(new Vector(-1, -2, -3).lengthSquared() - 14));
		assertTrue ("ERROR: lengthSquared() wrong value", isZero(new Vector(1, -2, -3).lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
		assertTrue ("ERROR: length() wrong value", isZero(new Vector(2, 2, 1).length() - 3));
		assertTrue ("ERROR: length() wrong value", isZero(new Vector(-1, 2, 2).length() - 3));
		assertTrue ("ERROR: length() wrong value", isZero(new Vector(-2, -1, -2).length() - 3));
		assertTrue ("ERROR: length() wrong value", isZero(new Vector(1, -2, -2).length() - 3));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		
		assertEquals("ERROR: normalize() does not work correctly", new Vector(2d/3, 2d/3, 1d/3), new Vector(2, 2, 1).normalize());
		assertEquals("ERROR: normalize() does not work correctly", new Vector(-1d/3, 2d/3, 2d/3), new Vector(-1, 2, 2).normalize()); 
		assertEquals("ERROR: normalize() does not work correctly", new Vector(-2d/3, -1d/3, -2d/3), new Vector(-2, -1, -2).normalize());
		assertEquals("ERROR: normalize() does not work correctly", new Vector(1d/3, -2d/3, -2d/3), new Vector(1, -2, -2).normalize());
		
		Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        assertTrue ("ERROR: normalize() function creates a new vector", vCopy == vCopyNormalize);
        assertTrue ("ERROR: normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		assertEquals("ERROR: normalized() does not work correctly", new Vector(2d/3, 2d/3, 1d/3), new Vector(2, 2, 1).normalized());
		assertEquals("ERROR: normalized() does not work correctly", new Vector(-1d/3, 2d/3, 2d/3), new Vector(-1, 2, 2).normalized()); 
		assertEquals("ERROR: normalized() does not work correctly", new Vector(-2d/3, -1d/3, -2d/3), new Vector(-2, -1, -2).normalized());
		assertEquals("ERROR: normalized() does not work correctly", new Vector(1d/3, -2d/3, -2d/3), new Vector(1, -2, -2).normalized());
		
		 Vector v = new Vector(1, 2, 3);
	     Vector u = v.normalized();
	     assertFalse ("ERROR: normalizated() function does not create a new vector", u == v);
	}
	
	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
		assertEquals("ERROR: scale() does not work correctly", new Vector(4, 4, 2), new Vector(2, 2, 1).scale(2));
		assertEquals("ERROR: scale() does not work correctly", new Vector(-2, 4, 4), new Vector(-1, 2, 2).scale(2)); 
		assertEquals("ERROR: scale() does not work correctly", new Vector(-4, -2, -4), new Vector(-2, -1, -2).scale(2));
		assertEquals("ERROR: scale() does not work correctly", new Vector(2, -4, -4), new Vector(1, -2, -2).scale(2));
		
		assertEquals("ERROR: scale() does not work correctly", new Vector(-4, -4, -2), new Vector(2, 2, 1).scale(-2));
		assertEquals("ERROR: scale() does not work correctly", new Vector(2, -4, -4), new Vector(-1, 2, 2).scale(-2)); 
		assertEquals("ERROR: scale() does not work correctly", new Vector(4, 2, 4), new Vector(-2, -1, -2).scale(-2));
		assertEquals("ERROR: scale() does not work correctly", new Vector(-2, 4, 4), new Vector(1, -2, -2).scale(-2));
		
		try {// test zero scale 
			new Vector(1, 2, 3).scale(0);
            fail("ERROR: scale() with zero does not throw an exception");
        } catch (Exception e) {}
	}

}
