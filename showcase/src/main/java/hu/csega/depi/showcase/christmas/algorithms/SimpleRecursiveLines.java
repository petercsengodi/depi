package hu.csega.depi.showcase.christmas.algorithms;

import java.awt.Color;
import java.awt.Graphics2D;

public class SimpleRecursiveLines implements ChristmasDrawingStrategy {

	@Override
	public void draw(Graphics2D g, int minx, int miny, int maxx, int maxy) {
		g.setColor(Color.green);

		double maxlength = maxy - miny - 20.0;
		double minlength = MIN_LENGTH * 5;

		draw(g, minlength, maxlength, 0, maxy - 10.0, 0, miny + 10.0);
	}

	private void draw(Graphics2D g, double minlength, double maxlength, double x1, double y1, double x2, double y2) {
		double length = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
		if(length < minlength){
			g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			return;
		}

//		double green = 0.5 + ((maxlength - length) / (maxlength - minlength)) / 2.0;
//
//		Color c = new Color(0, (float)green, 0);
//		g.setColor(c);
//		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

		double sx = (x1 * 2.5 + x2 * 1.5) / 4.0;
		double sy = (y1 * 2.5 + y2 * 1.5) / 4.0;
		g.drawLine((int)x1, (int)y1, (int)sx, (int)sy);

		// main part
		// draw(g, minlength, maxlength, sx, sy, x2, y2);

		// side part
		double x3 = (x2 - sx) /* * 3.0 / 4.0 */;
		double y3 = (y2 - sy) /* * 3.0 / 4.0 */;

		double xl = x3 * Math.cos(LEFT_ANGLE) + y3 * Math.sin(LEFT_ANGLE);
		double yl = -x3 * Math.sin(LEFT_ANGLE) + y3 * Math.cos(LEFT_ANGLE);

		draw(g, minlength, maxlength, sx, sy, sx + xl, sy + yl);

		double xr = x3 * Math.cos(RIGHT_ANGLE) + y3 * Math.sin(RIGHT_ANGLE);
		double yr = -x3 * Math.sin(RIGHT_ANGLE) + y3 * Math.cos(RIGHT_ANGLE);

		draw(g, minlength, maxlength, sx, sy, sx + xr, sy + yr);
	}

	private static final double LEFT_ANGLE = Math.PI / 6.0;
	private static final double RIGHT_ANGLE = -Math.PI / 6.0;
	private static final double MIN_LENGTH = 0.01;
}
