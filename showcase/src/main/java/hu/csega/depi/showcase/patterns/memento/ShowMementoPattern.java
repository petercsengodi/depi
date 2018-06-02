package hu.csega.depi.showcase.patterns.memento;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseWindow;
import hu.csega.depi.showcase.patterns.memento.impl.Caretaker;

/**
 * In this example the user can draw on the canvas, where every change is logged
 * via a snapshot of the canvas into a queue. The undo-redo feature is implemented
 * by moving around in these snapshot queues.
 */
public class ShowMementoPattern extends ShowcaseWindow {

	protected ShowMementoPattern() {
		super(TITLE);
	}

	@Override
	protected void init() {
		caretaker = new Caretaker();
	}

	@Override
	protected void clear() {
	}

	@Override
	protected void doOneRound() {
		// does nothing
	}

	@Override
	protected long waitingTime() {
		return 10l;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(inBox(e, 610, 20, 690, 70)) {
			synchronized (caretaker) {
				caretaker.undo();
			}

			repaintCanvas();
		}

		if(inBox(e, 710, 20, 790, 70)) {
			synchronized (caretaker) {
				caretaker.redo();
			}

			repaintCanvas();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if((e.getButton() & MouseEvent.BUTTON1) > 0 && inBox(e, 0, 0, 599, 599)) {
			synchronized (caretaker) {
				caretaker.save();
				pressed = true;

				Graphics graphics = caretaker.getGraphics();
				graphics.setColor(Color.blue);
				graphics.fillOval(e.getX() - 10, e.getY() - 10, 20, 20);
			}

			repaintCanvas();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(pressed && inBox(e, 0, 0, 599, 599)) {
			synchronized (caretaker) {
				Graphics graphics = caretaker.getGraphics();
				graphics.setColor(Color.blue);
				graphics.fillOval(e.getX() - 10, e.getY() - 10, 20, 20);
			}

			repaintCanvas();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if((e.getButton() & MouseEvent.BUTTON1) > 0) {
			pressed = false;
		}
	}

	@Override
	protected void paint2d(Graphics2D g) {
		g.drawImage(caretaker.getCurrentImage(), 0, 0, null);
		g.setColor(Color.black);

		g.drawRect(620, 20, 70, 50);
		g.drawString("Press", 630, 40);
		g.drawString("to undo", 630, 60);
		g.drawRect(620, 80, 70, 510);

		int numberOfUndos = Math.min(caretaker.undoLength(), 51);
		for(int i = 0; i < numberOfUndos; i++) {
			g.fillRect(622, 80 + i*10 + 2, 66, 6);
		}

		g.drawRect(710, 20, 70, 50);
		g.drawString("Press", 720, 40);
		g.drawString("to redo", 720, 60);
		g.drawRect(710, 80, 70, 510);

		int numberOfRedos = Math.min(caretaker.redoLength(), 51);
		for(int i = 0; i < numberOfRedos; i++) {
			g.fillRect(712, 80 + i*10 + 2, 66, 6);
		}
	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("memento.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowMementoPattern();
	}

	private Caretaker caretaker;
	private boolean pressed;

	private static final String TITLE = "Memento Pattern";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
