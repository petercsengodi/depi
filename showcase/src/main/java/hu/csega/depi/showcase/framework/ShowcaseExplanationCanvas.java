package hu.csega.depi.showcase.framework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ShowcaseExplanationCanvas extends JPanel {

	public ShowcaseExplanationCanvas(BufferedImage img) {
		this.img = img;
		setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	private BufferedImage img;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}
