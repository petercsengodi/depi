package hu.csega.depi.tests;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class TestScreenshot {
 public static void main(String[] args) throws Exception {
     Robot robot = new Robot();
     Rectangle size = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
     BufferedImage buf = robot.createScreenCapture(size);
     long now = System.currentTimeMillis();
     ImageIO.write(buf, "png", new File("/tmp/screenshot-" + now + ".png"));
 }
}
