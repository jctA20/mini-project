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
public class PointLight extends Light implements LightSource  
{
	
	protected Point3D _position;
	protected double _kC;
	protected double _kL;
	protected double _kQ;
	
	/**
	 * PointLight constructor
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _radius
	 */
	public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ, double _radius) {
		super(_intensity, _radius);
		this._position = _position;
		this._kC = _kC;
		this._kL = _kL;
		this._kQ = _kQ;
	}
	/**
	 * PointLight constructor
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 */
	public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ) {
		super(_intensity, 0);
		this._position = _position;
		this._kC = _kC;
		this._kL = _kL;
		this._kQ = _kQ;
	}

	/**
	 * @param p point
	 * @return the _intensity of the color in p point
	 */
	@Override
	public Color getIntensity(Point3D p) {
		//Il = Io / (kc + kl * d + kq * (d * d))
		double d = _position.distance(p);
		double scalar = 1 / (_kC + _kL * d + _kQ * d * d);
		return _intensity.scale(scalar);
	}
	
	/**
	 * @param p point
	 * @return L normal Vector in p point
	 */
	@Override
	public Vector getL(Point3D p) {
		return p.subtract(_position).normalize();
	}
	/**
	 * 
	 * @param point Point3D
	 * @return the distance between point to the Point Light
	 */
	@Override
	public double getDistance(Point3D point)
	{
		return _position.distance(point);
	}
	/**
	 * @return the _radius of the Light Source
	 */
	public double get_radius() {
		return super.get_radius();
	}
	/**
	 * @return the _position of the light source
	 */
	public Point3D get_position() {
		return _position;
	}

}
