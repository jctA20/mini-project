package primitives;

import java.lang.Math;

/**
 * Class Point3D is the basic class representing a point in a
 * 3D system.
 *
 * @author Hodaya and Osnat
 */

public class Point3D {
	
	final Coordinate _x;
	final Coordinate _y;
	final Coordinate _z;
	
    public static final Point3D _zero = new Point3D(0,0,0);
    
    /**
     * constructor for a new Point3D object
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     * @param z coordinate on the Z axis
     */
    
	public Point3D(double x, double y, double z) {
		super();
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}
	
	/**
     * constructor for a new Point3D object
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     * @param z coordinate on the Z axis
     */
    
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super();
		_x = x;
		_y = y;
		_z = z;
	}
	
	/**
     * copy constructor 
     * @param myPoint point
     */
	public Point3D(Point3D myPoint) {
		super();
		_x = myPoint._x;
		_y = myPoint._y;
		_z = myPoint._z;
	}
	

	/**
     * @return _x a coordinate
     */
	
	public Coordinate get_x() {
		return _x;
	}
	

	/**
     * @return _y a coordinate
     */
	
	public Coordinate get_y() {
		return _y;
	}
	

	/**
     * @return _z a coordinate
     */
	
	public Coordinate get_z() {
		return _z;
	}
	/**
     * Point3D value getter
     * 
     * @return Point3D value
     */
	@Override
	public String toString() {
		return "Point3D [_x=" + _x + ", _y=" + _y + ", _z=" + _z + "]";
	}
	
	/**
	 * @param obj - a Point3D object
	 * @return if two Point3D object are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other._x))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other._y))
			return false;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other._z))
			return false;
		return true;
	}
	
	/**
	 * @param other point
	 * @return the vector of sub between this and other
	 */
	
	public Vector subtract(Point3D other)
	{
		Vector myVec = new Vector(_x.get()-other._x.get(), _y.get()-other._y.get(), _z.get()-other._z.get());
		return myVec;
	}
	
	/**
	 * @param other vector
	 * @return the vector of add between this and other
	 */
	
	public Point3D add(Vector other)
	{
		Point3D myPoint = new Point3D(_x.get() + other.get_head()._x.get(), _y.get() + other.get_head()._y.get(), _z.get() + other.get_head()._z.get());
		return myPoint;
	}
	
	/**
	 * @param other point
	 * @return distanceSquared between this and other
	 */
	
	public double distanceSquared(Point3D other)
	{
		double x = _x.get() - other._x.get();
		double y = _y.get() - other._y.get();
		double z = _z.get() - other._z.get();
		return x*x + y*y +z*z;
	}
	
	/**
	 * @param other point
	 * @return distance between this and other
	 */
	
	public double distance(Point3D other) 
	{
		return Math.sqrt(this.distanceSquared(other));
	}
}
