/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author Hodaya Cohen and Osnat Orenstain
 *
 */
public class SpotLight extends PointLight{
	private Vector _direction;

	/**
	 * SpotLight constructor
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _direction
	 * @param _radius
	 */
	public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ, double _radius) {
		super(_intensity, _position, _kC, _kL, _kQ, _radius);
		this._direction = new Vector(_direction);
	}
	/**
	 * SpotLight constructor
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _direction
	 */
	public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
		super(_intensity, _position, _kC, _kL, _kQ, 0);
		this._direction = new Vector(_direction);
	}
	/**
	 * @param p point
	 * @return the _intensity of the color in p point
	 */
	@Override
	public Color getIntensity(Point3D p) {
		//Il = Io * max(0, dir * l) / (kc + kl * d + kq * (d * d))
		Vector dir = new Vector (_direction);
		Vector l = new Vector(this.getL(p));
		dir.normalize();
		l.normalize();
		double d = _position.distance(p);
		double scalar = Math.max(0,(dir.dotProduct(l))) / (_kC + _kL * d + _kQ * d * d);
		return _intensity.scale(scalar);
	}
	

	
	

}
