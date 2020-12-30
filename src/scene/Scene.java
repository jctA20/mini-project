package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Scene {
	final String _name;
	Color _background;
	AmbientLight _ambientLight;
	final Geometries _geometries;
	Camera _camera;
	double _distance;
	List<LightSource> _lights;
	
	/**
	 * Scene Constructor
	 * @param _name
	 */
	public Scene(String _name) {
		super();
		this._name = _name;
		_geometries = new Geometries();
		this._lights = new LinkedList<LightSource>();
	}

	/**
	 * @return the color of the background of the scene
	 */
	public Color get_background() {
		return _background;
	}

	/**
	 * @param _background - set the color of the _background
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}

	/**
	 * @return the _ambientLight of the scene
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}

	/**
	 * @param _ambientLight - set the ambientLight of the scene 
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	/**
	 * @return the _camera of the scene
	 */
	public Camera get_camera() {
		return _camera;
	}

	/**
	 * @param _camera - set the camera of the scene
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}

	/**
	 * @return the _distance between the camera to the view plane
	 */
	public double get_distance() {
		return _distance;
	}

	/**
	 * @param _distance - set the _distance between the camera to the view plane
	 */
	public void set_distance(double _distance) {
		this._distance = _distance;
	}

	/**
	 * @return the _name of the scene
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @return the _geometries in the scene
	 */
	public Geometries get_geometries() {
		return _geometries;
	}
	/**
	 * @return the _lights in the scene
	 */
	public List<LightSource> get_lights() {
		return _lights;
	}
	
	/**
	 * add a Geometry/geometries to the _geometries list
	 * @param geometries list of geometries 
	 */
	public void addGeometries(Geometry... geometries) 
	{
		for(Geometry i: geometries)
		    _geometries.add(i);
	}
	
	/**
	 * add a LightSource/LightSources to the _lights list
	 * @param lights list of LightSource 
	 */
	public void addLights(LightSource... lights)
	{
		for(LightSource i: lights)
			_lights.add(i);
	}
	

}
