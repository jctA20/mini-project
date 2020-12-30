package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * @author Hodaya Cohen and Osnat Orenstain
 *
 */
public class softShadow {

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing soft shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
					 new Point3D(60, -50, 50),30));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7, 8));

		ImageWriter imageWriter = new ImageWriter("soft shadow 1", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene) //
				.setMultithreading(3) //
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing soft shadow
	 */
	@Test
	public void trianglesTransparent() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries(new Triangle(new Color(70, 70, 70), new Material(0.5, 0.5, 60), //
				new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(new Color(70, 70, 70), new Material(0.5, 0.5, 60), //
				new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0, 0), // )
					 new Point3D(60, -50, 50),30));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7, 12),
				new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

		ImageWriter imageWriter = new ImageWriter("soft Shadow 2", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene) //
				.setMultithreading(3) //
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Sphere-Triangle soft shading - move triangle up-right 
	 */
	@Test
	public void SphereTriangleMove1() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				new Point3D(0, 0, 200), 60), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-62, 32, 0), new Point3D(-32, 62, 0), new Point3D(-60, 60, 4)));
		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7, 12));

		ImageWriter imageWriter = new ImageWriter("soft Shadow 3", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene) //
				.setMultithreading(3) //
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

}
