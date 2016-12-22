package hu.csega.depi.showcase.framework;

import static hu.csega.depi.showcase.framework.ShowcaseWindow.DEFAULT_WINDOW_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ShowcaseCanvas extends JPanel {

	public ShowcaseCanvas(ShowcaseWindow window) {
		this.window = window;
		setPreferredSize(DEFAULT_WINDOW_SIZE);
		addMouseListener(window);
		addMouseMotionListener(window);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		window.ensureInited();

		Graphics2D g2d = (Graphics2D) buffer.getGraphics();
		g2d.setColor(background);
		g2d.fillRect(0, 0, DEFAULT_WINDOW_SIZE.width, DEFAULT_WINDOW_SIZE.height);
		g2d.setColor(Color.black);

		window.paint2d(g2d);

		g.drawImage(buffer, 0, 0, null);
	}

	protected void setCanvasBackground(Color bgColor) {
		this.background = bgColor;
	}

	private ShowcaseWindow window;
	private BufferedImage buffer = new BufferedImage(DEFAULT_WINDOW_SIZE.width, DEFAULT_WINDOW_SIZE.height, BufferedImage.TYPE_3BYTE_BGR);

	private Color background = Color.LIGHT_GRAY;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}
