/**
 * 
 */
package primitives;

/**
 * class Material
 * @author Hodaya and Osnat
 *
 */
public class Material {
	
	final double _kD;// diffuse פיזור
	final double _kS;// specular החזרה
	final double _kT;// refraction שקיפות
	final double _kR;//reflection השתקפות ,מראתיות
	final int _nShininess;// מבריקות 
	/**
	 * Material constructor
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 */
	public Material(double _kD, double _kS, int _nShininess) {
		super();
		this._kD = _kD;
		this._kS = _kS;
		this._kT = 0;
		this._kR = 0;
		this._nShininess = _nShininess;
	}
	/**
	 * Material constructor
	 * @param _kD
	 * @param _kS
	 * @param _kT
	 * @param _kR
	 * @param _nShininess
	 */
	public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
		super();
		this._kD = _kD;
		this._kS = _kS;
		this._kT = _kT;
		this._kR = _kR;
		this._nShininess = _nShininess;
	}
	/**
	 * @return the _kD
	 */
	public double get_kD() {
		return _kD;
	}
	/**
	 * @return the _kS
	 */
	public double get_kS() {
		return _kS;
	}
	/**
	 * @return the _kT
	 */
	public double get_kT() {
		return _kT;
	}
	/**
	 * @return the _kR
	 */
	public double get_kR() {
		return _kR;
	}
	/**
	 * @return the _nShininess
	 */
	public int get_nShininess() {
		return _nShininess;
	}
}
