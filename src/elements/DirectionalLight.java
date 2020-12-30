/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class DirectionalLight
 * 
 * @author Hodaya and Osnat
 *
 */
public class DirectionalLight extends Light implements LightSource{
	
	private Vector _direction;

	/**
	 * DirectionalLight constructor
	 * @param _intensity
	 * @param _direction
	 */
	public DirectionalLight(Color _intensity, Vector _direction) {
		super(_intensity, 0);
		this._direction = new Vector(_direction);
	}
	/**
	 * DirectionalLight constructor
	 * @param _intensity
	 * @param _direction
	 * @param _radius always 0
	 */
	public DirectionalLight(Color _intensity, Vector _direction, double _radius) {
		super(_intensity, 0);
		this._direction = new Vector(_direction);
	}
	/**
	 * @param p point
	 * @return the _intensity of the color in p point
	 */
	@Override
	public Color getIntensity(Point3D p) {
		//Il = Io
		return this._intensity;
	}
	
	/**
	 * @param p point
	 * @return L normal Vector in p point 
	 */
	@Override
	public Vector getL(Point3D p) {
		return _direction.normalize();
	}
	/**
	 * 
	 * @param point Point3D
	 * @return the distance between point to the Directional Light
	 */
	@Override
	public double getDistance(Point3D point)
	{
		return Double.POSITIVE_INFINITY;
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
	public Point3D get_position()
	{
		return null;
	}

}
