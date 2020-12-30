/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
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
 * @author User
 *
 */
public class ThreadTest {

	@Test
	public void test() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				new Point3D(0, 0, 200), 60), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));
		
		ImageWriter imageWriter = new ImageWriter("teapot", 200, 200, 800, 800);
		Render render = new Render(imageWriter, scene) //
				.setMultithreading(3) //
				.setDebugPrint();

		render.renderImage();
		// render.printGrid(50, java.awt.Color.YELLOW);
		render.writeToImage();
	}

}
