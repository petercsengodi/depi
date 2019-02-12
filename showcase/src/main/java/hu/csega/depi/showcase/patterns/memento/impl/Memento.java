package hu.csega.depi.showcase.patterns.memento.impl;

import java.awt.image.BufferedImage;

public class Memento {



	public BufferedImage getState() {
		return state;
	}

	public void setState(BufferedImage state) {
		this.state = state;
	}

	private BufferedImage state;
}
