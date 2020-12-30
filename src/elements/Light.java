/**
 * 
 */
package elements;

import primitives.Color;

/**
 * abstract class Light
 * 
 * @author Hodaya and Osnat
 *
 */
abstract class Light {
	protected Color _intensity;
	double _radius;//the radius of the light source

	/**
	 * Light constructor 
	 * @param _intensity
	 */
	public Light(Color _intensity) {
		super();
		this._intensity = _intensity;
	}
	/**
	 * Light constructor 
	 * @param _intensity
	 * @param radius
	 */
	public Light(Color _intensity, double radius) {
		super();
		this._intensity = _intensity;
		this._radius = radius;
	}

	/**
	 * @return the _intensity of the color
	 */
	public Color get_intensity() {
		return _intensity;
	}
	
	/**
	 * @return the _radius of the light source
	 */
	public double get_radius() {
		return _radius;
	}
}
