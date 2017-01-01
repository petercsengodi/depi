package hu.csega.depi.showcase.christmas.algorithms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Cubes implements ChristmasDrawingStrategy {

	@Override
	public void draw(Graphics2D g, int minx, int miny, int maxx, int maxy) {
		g.setStroke(new BasicStroke(2f));

		g.setColor(Color.red);
		draw2(g, minx + 10.0, miny + 200.0, maxx - 10.0, maxy - 10.0);

		g.setColor(Color.blue);
		bow(g, -10.0, miny + 200.0, 10.0, miny + 200.0, LEFT_ANGLE, RIGHT_ANGLE, REDUCTION_RATE, 1.0);
		bow(g, -10.0, miny + 200.0, 10.0, miny + 200.0, LEFT_ANGLE, RIGHT_ANGLE, 1.0, REDUCTION_RATE);

		bow2(g, -30.0, miny + 200.0, 30.0, miny + 200.0, -Math.PI / 2.0);

		bow3(g, minx * 0.8, miny + 200.0, miny + 20.0, 0.95);
		bow3(g, maxx * 0.8, miny + 200.0, miny + 20.0, 0.95);
	}

	public void draw2(Graphics2D g, double x1, double y1, double x2, double y2) {
		g.drawRect((int)x1, (int)y1, (int)(x2 - x1), (int)(y2 - y1));
		draw(g, x1, y1, x2, y2);
	}

	private void draw(Graphics2D g, double x1, double y1, double x2, double y2) {
		g.drawRect((int)x1, (int)y1, (int)(x2 - x1), (int)(y2 - y1));

		if(x2 - x1 < MIN_LENGTH || y2 - y1 < MIN_LENGTH) {
//			g.drawRect((int)x1, (int)y1, (int)(x2 - x1), (int)(y2 - y1));
			return;
		}

		double xhalf = (x2 - x1) * 0.5;
		double xdiff = Math.max((x2 - x1) * DIFF, 2);
		double yhalf = (y2 - y1) * 0.5;
		double ydiff = Math.max((y2 - y1) * DIFF, 2);

		draw(g, x1, y1, x1 + xhalf - xdiff, y1 + yhalf - ydiff);
		draw(g, x1, y1 + yhalf + ydiff, x1 + xhalf - xdiff, y2);
		draw(g, x1 + xhalf + xdiff, y1, x2, y1 + yhalf - ydiff);
		draw(g, x1 + xhalf + xdiff, y1 + yhalf + ydiff, x2, y2);
	}

	private void bow(Graphics2D g, double x1, double y1, double x2, double y2,
			double leftAngle, double rightAngle, double leftReduction, double rightReduction) {

		double length = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
		if(length < MIN_LENGTH){
			return;
		}

		// rotate x2;y2 around x1;y1 with 90 degrees, and reduce it a bit to do the curve

		double x0 = x2 - x1, y0 = y2 - y1;

		double xl = x0 * Math.cos(leftAngle) + y0 * Math.sin(leftAngle);
		double yl = -x0 * Math.sin(leftAngle) + y0 * Math.cos(leftAngle);

		double x12 = x1 + xl * leftReduction, y12 = y1 + yl * leftReduction;

		// rotate x1;y1 around x2;y2 with 90 degrees, and reduce it a bit to do the curve

		x0 = x1 - x2;
		y0 = y1 - y2;

		double xr = x0 * Math.cos(rightAngle) + y0 * Math.sin(rightAngle);
		double yr = -x0 * Math.sin(rightAngle) + y0 * Math.cos(rightAngle);

		double x22 = x2 + xr * rightReduction, y22 = y2 + yr * rightReduction;

		// lines
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g.drawLine((int)x1, (int)y1, (int)x12, (int)y12);
		g.drawLine((int)x22, (int)y22, (int)x2, (int)y2);

		// recursive call

		bow(g, x12, y12, x22, y22, leftAngle, rightAngle, leftReduction, rightReduction);
	}

	private void bow2(Graphics2D g, double x1, double y1, double x2, double y2, double leftAngle) {

		double length = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
		if(length < MIN_LENGTH){
			return;
		}

		// rotate x2;y2 around sx;sy with 90 degrees

		double sx = (x2 + x1) / 2, sy = (y2 + y1) / 2;
		double x0 = x2 - sx, y0 = y2 - sy;

		double x3v = x0 * Math.cos(-leftAngle) + y0 * Math.sin(-leftAngle);
		double y3v = -x0 * Math.sin(-leftAngle) + y0 * Math.cos(-leftAngle);

		double x3 = x3v + sx, y3 = y3v + sy;

		// lines
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g.drawLine((int)x1, (int)y1, (int)x3, (int)y3);
		g.drawLine((int)x3, (int)y3, (int)x2, (int)y2);

		// recursive call

		bow2(g, x1, y1, x3, y3, leftAngle);
		bow2(g, x3, y3, x2, y2, leftAngle);
	}

	private void bow3(Graphics2D g, double x, double y1, double y2, double reductionRate) {

		double length = Math.sqrt((y2-y1) * (y2-y1));
		if(length < MIN_LENGTH){
			return;
		}

		// rotate x2;y2 around sx;sy with 90 degrees

		double length2 = length * reductionRate;
		double y21 = y1, y22 = y1 -length2, x2 = x * reductionRate;

		// lines
		g.drawLine((int)x, (int)y1, (int)x, (int)y2);
		g.drawLine((int)x, (int)y1, (int)x2, (int)y21);
		g.drawLine((int)x, (int)y2, (int)x2, (int)y22);

		// recursive call

		bow3(g, x2, y21, y22, reductionRate);
	}

	private static final double REDUCTION_RATE = 0.9;
	private static final double ROTATION_RATE = 0.01;

	private static final double LEFT_ANGLE = -Math.PI / 2.0 + 0.01;
	private static final double RIGHT_ANGLE = Math.PI / 2.0 - 0.01;

	private static final double DIFF = 0.04;
	private static final double MIN_LENGTH = 5.0;
}
