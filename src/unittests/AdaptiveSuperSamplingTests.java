/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
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
 * @author Osnat and Hodaya
 */
public class AdaptiveSuperSamplingTests {

	 /**
     * Produce a picture of a two triangles lighted by a directional light with adaptiveSuperSampling enhancement
     */
    @Test
    public void trianglesDirectional() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("adaptiveSuperSampling1", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene, true).setMultithreading(3) //
				.setDebugPrint();

        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by all types of sources light with adaptiveSuperSampling enhancement
     */
    @Test
    public void trianglesAllLights() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 300),
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 300),
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new SpotLight(new Color(500, 250, 250), new Point3D(50, 50, 130), new Vector(-2, 2, 1), 1, 0.0001, 0.000005),
                        new PointLight(new Color(500, 250, 250), new Point3D(-50, -50, 130), 1, 0.0005, 0.0005),
        	            new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("adaptiveSuperSampling2", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene, true).setMultithreading(3) //
				.setDebugPrint();

        render.renderImage();
        render.writeToImage();
    }
    /**
	 * Sphere-Triangle soft shading - move triangle up-right with adaptiveSuperSampling enhancement
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

		ImageWriter imageWriter = new ImageWriter("adaptiveSuperSampling3", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene, true) //
				.setMultithreading(3) //
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}


}
