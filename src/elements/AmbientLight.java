package elements;

import primitives.Color;

public class AmbientLight extends Light {
	
	/**
	 * AmbientLight constructor 
	 * @param _iA color 
	 * @param _kA double
	 */
	public AmbientLight(Color _iA, double _kA) {
		//Ip = Ka * Ia
		super(_iA.scale(_kA)); 
	}
	
}
