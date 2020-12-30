package primitives;
import primitives.Point3D;

/**
 * Vector class
 * 
 * @author Osnat and Hodaya
 */

public class Vector {
	
Point3D _head;

/**
 * Vector constructor
 * @param head of vector
 */

public Vector(Point3D head) {
	super();
	if(head.equals(Point3D._zero))
	{
		throw new IllegalArgumentException("vector can't be vector0"); 
	}
	this._head = new Point3D(head);
}

/**
 * Vector constructor
 * @param x coordinate of vector _head point
 * @param y coordinate of vector _head point
 * @param z coordinate of vector _head point
 */

public Vector(Coordinate x, Coordinate y, Coordinate z) {
	super();
	Point3D head = new Point3D(x, y, z);
	if(head.equals(Point3D._zero))
	{
		throw new IllegalArgumentException("vector can't be vector0"); 
	}
	this._head = new Point3D(head);
}

/**
 * Vector constructor
 * @param x coordinate of vector _head point
 * @param y coordinate of vector _head point
 * @param z coordinate of vector _head point
 */

public Vector(double x, double y, double z) {
	super();
	Point3D head = new Point3D(x, y, z);
	if(head.equals(Point3D._zero))
	{
		throw new IllegalArgumentException("vector can't be vector0"); 
	}
	this._head = new Point3D(head);
}

/**
 * Copy constructor
 * @param vec vector
 */

public Vector(Vector vec) {
	super();
	this._head = new Point3D(vec._head);
}

/**
 * @return _head of the vector
 */

public Point3D get_head() {
	return _head;
}

/**
 * @param head point of the ray
 */

public void set_head(Point3D head)
{
	_head = new Point3D(head);
}

/**
 * @param obj - a vector object
 * @return if two vector object are equals
 */

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Vector other = (Vector) obj;
	if (_head == null) {
		if (other._head != null)
			return false;
	} else if (!_head.equals(other._head))
		return false;
	return true;
}

/**
 * print the details of the vector
 */
@Override
public String toString() {
	return "Vector [_head=" + _head + "]";
}

/**
 * @param other point
 * @return the vector of sub between this and other
 */

public Vector subtract(Point3D other)
{
	return _head.subtract(other);
}

/**
 * @param other vector
 * @return the vector of add between this and other
 */

public Vector add(Vector other)
{
	return new Vector(_head.add(other));
}

/**
 * @param scale double
 * @return the vector of the scale multiplier this 
 */

public Vector scale(double scale)
{
	return new Vector(_head._x.get()*scale, _head._y.get()*scale, _head._z.get()*scale );
}

/**
 * @param other vector
 * @return dotProduct between this and other
 */

public double dotProduct(Vector other)
{
	double x = _head._x.get()*other._head._x.get();
	double y = _head._y.get()*other._head._y.get();
	double z = _head._z.get()*other._head._z.get();
	return (x + y + z);
}

/**
 * @param other vector
 * @return crossProduct between this and other
 */

public Vector crossProduct(Vector other) 
{
	double u1 = _head._x.get();
	double u2 = _head._y.get();
	double u3 = _head._z.get();
	double v1 = other._head._x.get();
	double v2 = other._head._y.get();
	double v3 = other._head._z.get();
	return new Vector(u2*v3 - u3*v2, u3*v1 - u1*v3, u1*v2 - u2*v1);
}

/**
 * @return the lengthSquared of vector
 */

public double lengthSquared() 
{
	double x = _head._x.get();
	double y = _head._y.get();
	double z = _head._z.get();
	return (x*x + y*y + z*z);
}

/**
  * @return the length of vector
 */

public double length()
{
	return Math.sqrt(this.lengthSquared());
}

/**
  * @return the vector after normalize (change this)
 */

public Vector normalize()
{
	double vectorlen = this.length();
	double x = _head._x.get() / vectorlen; 
	double y = _head._y.get() / vectorlen; 
	double z = _head._z.get() / vectorlen; 
	this.set_head(new Point3D(x, y, z));
	return this;	
}

/**
 * @return the normalized of the vector (don't change this)
 */

public Vector normalized() 
{
	Vector myVec = new Vector(this);
	return myVec.normalize();
}
/**
 * 
 * @param ray
 * @return vector that orthogonal to the ray
 */
public Vector findOrthogonal(Ray ray)
{
	Vector v = ray.get_direction();
    // create x and y axis for the circle, which means vectorUp and vectorRight.
    Vector vRight = new Vector(-v.get_head().get_z().get(), 0, v.get_head().get_x().get()).normalized();
    // the reason we entered 0 to y coordinate is to prevent from creating vector zero.
    // y need to have the minimum value of the three.
    Vector vUp = v.crossProduct(vRight); 
    return vUp;
}

}
