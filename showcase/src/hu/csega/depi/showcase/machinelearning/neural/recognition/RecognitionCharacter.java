package hu.csega.depi.showcase.machinelearning.neural.recognition;

import java.awt.image.BufferedImage;

public class RecognitionCharacter {

	public void fillFromImage(BufferedImage image, int cubeWidth, int cubeHeight) {
		int color;
		int count;
		double avg;
		double num = cubeWidth * cubeHeight;

		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				avg = 0.0;
				count = 0;

				for(int xx = 0; xx < cubeWidth; xx++){
					for(int yy = 0; yy < cubeHeight; yy++) {
						color = image.getRGB(x * cubeWidth + xx, y * cubeHeight + yy);
						if(Math.abs(color) > 1000)
							count ++;
					}
				}

				avg = count / num;
				data[y * WIDTH + x] = (avg > 0.1 ? 1 : 0);
			} // end for y
 		} // end for x

	} // end of fillFromImage

	public static final int WIDTH = 8;
	public static final int HEIGHT = 12;
	public static final int INPUTS = WIDTH * HEIGHT;

	public double[] data = new double[INPUTS];
	public boolean accepted;
}
