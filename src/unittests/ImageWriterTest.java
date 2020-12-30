/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import renderer.ImageWriter;

import static java.awt.Color.*;

/**
 * @author User
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for ImageWriter class
	 */
	@Test
	public void test() {
		ImageWriter imageWriter = new ImageWriter("ImageWriterTest", 1600, 1000, 800, 500);
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy(); 
		//for all pixels of imageWriter
		for(int i = 0; i < nY; i++)
			for(int j = 0; j < nX; j++)
				imageWriter.writePixel(j, i, j % 50 == 0 || i % 50 == 0 ? RED : BLUE);	
		imageWriter.writeToImage();
	}

}
