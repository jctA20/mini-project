package geometries;

import static java.awt.Color.BLACK;
import primitives.*;
/**
 * abstract class Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 * @author Osnat and Hodaya
 */

public abstract class Geometry implements Intersectable {
	
	protected Color _emmission;
	protected Material _material; 
	
	/**
	 * Geometry constructor
	 * @param _emmission
	 * @param _material
	 */
	public Geometry(Color _emmission, Material _material) {
		this._emmission = _emmission;
		this._material = _material;
	}

	/**
	 * Geometry constructor
	 * @param _emmission
	 */
	public Geometry(Color _emmission) {
		this(_emmission, new Material(0, 0, 0));
	}

	/**
	 * Geometry constructor
	 */
	public Geometry() {
		this(new Color(BLACK), new Material(0, 0, 0));	
	}

	/**
	 * @return the _emmission
	 */
	public Color get_emmission() {
		return _emmission;
	}
	
	/**
	 * @return the _material
	 */
	public Material get_material() {
		return _material;
	}

	/**
    *
    * @param p of geometry
    * @return the normal to this geometry in a given point p
    */
	
	public abstract Vector getNormal(Point3D p);
}

