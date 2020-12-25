package hu.csega.depi.showcase.tessellation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Random;

import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseWindow;

public class TessellationPoints2 extends ShowcaseWindow {

	protected TessellationPoints2() {
		super(TITLE);
	}

	@Override
	protected void init() {
		setCanvasBackground(Color.black);
	}


	@Override
	protected void clear() {
	}

	@Override
	protected void doOneRound() {
		long now = System.currentTimeMillis();
		double t = (now - lastTime) / 1000.0;
		if(t > MAX_TIME_SECONDS)
			t = MAX_TIME_SECONDS;

		lastTime = now;

		for(TessellationPointData p : points) {
			// Clearing forces
			p.fx = 0;
			p.fy = 0;
			p.ax = 0;
			p.ay = 0;

			// Loss of velocity
			p.vx *= 0.9;
			p.vy *= 0.9;
		}

		// Collect force data -- All particles "hate" each other
		double mx, my, d, d2, dx, dy, f, fx, fy;
		TessellationPointData p0, p1;
		for(int i = 0; i < NUMBER_OF_POINTS; i++) {
			p0 = points[i];
			for(int j = i + 1; j < NUMBER_OF_POINTS; j++) {
				p1 = points[j];
				dx = p0.x - p1.x;
				dy = p0.y - p1.y;

				// F = FORCE_SCALAR / d^2
				if(dx < EPSILON && dy < EPSILON) {
					// they are in the same spot
					fx = -0.001;
					fy = 0;
					/*				} else if (dx < EPSILON) {
					// they are on the same vertical axis
					fx = 0;
					fy = FORCE_SCALAR / (dy * dy);
				} else if (dy < EPSILON) {
					// they are on the same horizontal axis
					fx = FORCE_SCALAR / (dx * dx);
					fy = 0; */
				} else {
					d2 = dx * dx + dy * dy;
					d = Math.sqrt(d2);
					f = FORCE_SCALAR / d2;
					mx = Math.abs(dx / d);
					my = Math.abs(dy / d);
					fx = Math.signum(dx) * f * mx;
					fy = Math.signum(dy) * f * my;
				}

				p0.fx += fx;
				p0.fy += fy;
				p1.fx -= fx;
				p1.fy -= fy;
			}
		}

		// Collect force data -- All particles are drawn to the center
		for(TessellationPointData p : points) {
			if(Math.abs(p.x) > EPSILON && Math.abs(p.y) > EPSILON) {
				dx = -p.x;
				dy = -p.y;
				d2 = dx * dx + dy * dy;
				d = Math.sqrt(d2);

				mx = Math.abs(dx / d);
				my = Math.abs(dy / d);

				fx = Math.signum(dx) * FORCE_CENTER;
				fy = Math.signum(dy) * FORCE_CENTER;

				p.fx += fx;
				p.fy += fy;
			}
		}

		// Calculate acceleration
		for(TessellationPointData p : points) {
			p.ax = p.fx;
			p.ay = p.fy;
			p.fx = 0;
			p.fy = 0;
		}

		// Calculate velocity
		for(TessellationPointData p : points) {
			p.vx += p.ax * t;
			p.vy += p.ay * t;
			p.ax = 0;
			p.ay = 0;
		}

		// Anticipate movement & clearing force data
		for(TessellationPointData p : points) {
			p.x += p.vx * t;
			p.y += p.vy * t;
		}


		// Correction on the borders
		for(TessellationPointData p : points) {
			if(p.x < -400) {
				p.x = -400;
				if(p.vx < 0)
					p.vx = 0;
			}

			if(p.y < -300) {
				p.y = -300;
				if(p.vy < 0)
					p.vy = 0;
			}

			if(p.x > 399.999) {
				p.x = 399.999;
				if(p.vx > 0)
					p.vx = 0;
			}

			if(p.y > 299.999) {
				p.y = 299.999;
				if(p.vy > 0)
					p.vy = 0;
			}
		}

		// Create triangulation
		// FIXME ????
	}

	@Override
	protected long waitingTime() {
		return 100l;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	protected void paint2d(Graphics2D g) {
		g.translate(400, 300);

		g.setColor(Color.WHITE);

		for(int i = 0; i < NUMBER_OF_POINTS; i++) {
			int x = (int)Math.ceil(points[i].x);
			int y = (int)Math.ceil(points[i].y);
			g.drawRect(x-1, y-1, 3, 3);
		}

		g.translate(-400, -300);
	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("index.png");
	}

	public static void main(String[] args) throws Exception {
		new TessellationPoints2();
	}

	private static final double EPSILON = 0.00000001;
	private static final double FORCE_SCALAR = 1000.0;
	private static final double MAX_TIME_SECONDS = 0.1;
	private static final double FORCE_CENTER = 1.0;

	private static final int NUMBER_OF_POINTS = 100;
	private TessellationPointData[] points = new TessellationPointData[NUMBER_OF_POINTS];

	{
		Random rnd = new Random(System.currentTimeMillis());

		for(int i = 0; i < NUMBER_OF_POINTS; i++) {
			points[i] = new TessellationPointData();
			points[i].x = rnd.nextDouble() * 800 - 400;
			points[i].y = rnd.nextDouble() * 600 - 300;
			points[i].fx = 0;
			points[i].fy = 0;
		}
	}

	private long lastTime = 0;

	private static final String TITLE = "Tessellation – Points";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
