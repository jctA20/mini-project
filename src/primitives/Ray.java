package primitives;
import static primitives.Util.*;

/**
 * Ray class
 * 
 * @author Osnat and Hodaya
 */

public class Ray {
	private static final double DELTA = 0.1;
	
	/**
     * The point from which the ray starts.
     */
	
	final Point3D _POO;
	
	/**
     * The direction of the ray.
     */
	
	final Vector _direction;
	
	/**
     * Constructor for creating a new Ray of this class
     * @param point the start of the ray.
     * @param vector the direction of the ray.
     */
	
	public Ray(Point3D point, Vector vector) {
		super();
		this._POO = new Point3D(point);
		this._direction = new Vector(vector.normalize());
	}
	
	/**
     * Copy constructor for a deep copy of an Ray object.
     * @param other the object that being copied
     */
	
	public Ray(Ray other) {
		super();
		this._POO = new Point3D(other._POO);
		this._direction = new Vector(other._direction);
	}
	/**
	 * constructor a ray
	 * @param point
	 * @param direction
	 * @param normal
	 */
	public Ray (Point3D point, Vector direction, Vector normal)
	{
		this._direction = new Vector(direction).normalized();
		double nv = normal.dotProduct(direction);
		if(nv == 0)
			_POO = new Point3D(point);
		else
		{
			Vector normalDelta = normal.scale((nv > 0 ? DELTA: -DELTA));
			_POO = point.add(normalDelta);
		}
	}
	
	/**
     * Getter for the point from which the ray starts.
     * @return A new Point3D that represents the
     * point from which the ray starts.
     */
	
	public Point3D get_POO() {
		return _POO;
	}
	
	/**
     * @return _direction of the ray
     */
	
	public Vector get_direction() {
		return _direction;
	}
	/**
	 * @param obj - a ray object
	 * @return if two ray object are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_POO == null) {
			if (other._POO != null)
				return false;
		} else if (!_POO.equals(other._POO))
			return false;
		if (_direction == null) {
			if (other._direction != null)
				return false;
		} else if (!_direction.equals(other._direction))
			return false;
		return true;
	}
	/**
	 * print the details of the ray
	 */
	@Override
	public String toString() {
		return "Ray [_POO=" + _POO + ", _direction=" + _direction + "]";
	}
   

	/**
	 * @param t length
     * @return p = p0 + tv
     */
	public Point3D getPoint(double t) {
         return isZero(t) ? _POO : new Point3D(_POO).add(_direction.scale(t));
    }
	
}
