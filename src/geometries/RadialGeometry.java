package geometries;

/**
 * @author Hodaya and Osnat
 * RadialGeometry is an abstract class that defines
 * all radial geometries.
 */

public abstract class RadialGeometry extends Geometry {
	
final double _radius;

/**
* RadialGeometry constructor
* @param _radius of a RadialGeometry
*/

public RadialGeometry(double _radius) {
	super();
	this._radius = _radius;
}

/**
 * RadialGeometry Copy constructor
 * 
 * @param other a RadialGeometry
 */

public RadialGeometry(RadialGeometry other) {
	super();
	this._radius = other.get_radius();
}

/**
 * @return _radius of the geometry
 */

public double get_radius() {
	return _radius;
}
/**
 * print the details of the RadialGeometry
 */
@Override
public String toString() {
	return "RadialGeometry [_radius=" + _radius + "] " + super.toString();
}


}

