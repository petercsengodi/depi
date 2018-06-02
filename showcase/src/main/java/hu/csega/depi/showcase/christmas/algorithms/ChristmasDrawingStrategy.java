package hu.csega.depi.showcase.christmas.algorithms;

import java.awt.Graphics2D;

public interface ChristmasDrawingStrategy {

	void draw(Graphics2D g, int minx, int miny, int maxx, int maxy);
}
