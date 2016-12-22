package hu.csega.depi.showcase.christmas.algorithms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class TreeRecursiveLines implements ChristmasDrawingStrategy {

	private static final double MIN_LENGTH = 0.1;
	private static final double REDUCTION_RATE = 0.7;
	private static final double ROTATION_RATE = 0.6;

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
//			g.setColor(new Color(0.5f, 1f, 0.5f));
//			g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			return;
		}

		double rate = (maxlength - length) / (maxlength - minlength);

		double colorRate = 0.75 * Math.pow(rate, 4) + 0.25;

		Color c;
		if(colorRate < 0.5)
			c = new Color(0, (float)(0.5 + colorRate), 0);
		else
			c = new Color((float)(colorRate - 0.5), 1f, (float)(colorRate - 0.5));

		double sx = (x1 * 2.5 + x2 * 1.5) / 4.0;
		double sy = (y1 * 2.5 + y2 * 1.5) / 4.0;
		// g.drawLine((int)x1, (int)y1, (int)sx, (int)sy);

		// main part
		draw(g, minlength, maxlength, sx, sy, x2, y2);

		// side part
		double x3 = (x2 - sx) * REDUCTION_RATE;
		double y3 = (y2 - sy) * REDUCTION_RATE;

		double xl = x3 * Math.cos(LEFT_ANGLE) + y3 * Math.sin(LEFT_ANGLE);
		double yl = -x3 * Math.sin(LEFT_ANGLE) + y3 * Math.cos(LEFT_ANGLE);

		draw(g, minlength, maxlength, sx, sy, sx + xl, sy + yl);

		double xr = x3 * Math.cos(RIGHT_ANGLE) + y3 * Math.sin(RIGHT_ANGLE);
		double yr = -x3 * Math.sin(RIGHT_ANGLE) + y3 * Math.cos(RIGHT_ANGLE);

		draw(g, minlength, maxlength, sx, sy, sx + xr, sy + yr);

		g.setColor(c);
		g.setStroke(new BasicStroke((float)(5*(1-rate*rate))));

		double xx = x1*0.1 + x2 * 0.2;
		double yy = y1*0.1 + y2 * 0.2;
		g.drawLine((int)x1, (int)y1, (int)sx, (int)sy);
	}

	private static final double LEFT_ANGLE = ROTATION_RATE * Math.PI;
	private static final double RIGHT_ANGLE = -ROTATION_RATE * Math.PI;
}
