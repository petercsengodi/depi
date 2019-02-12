package hu.csega.depi.showcase.patterns.memento.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Originator {

	public Graphics getGraphics() {
		return state.getGraphics();
	}

	public BufferedImage getCurrentImage() {
		return state;
	}

	public Memento createMemento() {
		Memento memento = new Memento();
		BufferedImage img = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
		img.getGraphics().drawImage(state, 0, 0, null);
		memento.setState(img);
		return memento;
	}

	public void restoreFrom(Memento m) {
		state.getGraphics().drawImage(m.getState(), 0, 0, null);
	}

	private BufferedImage state = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);

	public Originator() {
		Graphics g = state.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 600);
	}
}
