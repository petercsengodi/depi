package hu.csega.depi.showcase.framework;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public abstract class ShowcaseWindow extends JFrame implements MouseListener, MouseMotionListener {

	public static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);

	protected ShowcaseWindow(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.canvas = new ShowcaseCanvas(this);
		initComponents(getContentPane(), canvas);

		pack();
		setVisible(true);

		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					while(true) {
						if(active) {
							doOneRound();
							ShowcaseWindow.this.canvas.repaint();
						}

						Thread.sleep(ShowcaseWindow.this.waitingTime());
					}

				} catch(InterruptedException ex) {
				}
			}
		});

		this.thread.start();
	}

	protected void initComponents(Container container, ShowcaseCanvas canvas) {
		container.setLayout(new BorderLayout());
		JPanel lowerPanel = new JPanel();
		container.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.add(canvas);

		JMenuBar menuBar = new JMenuBar();
		container.add(menuBar, BorderLayout.NORTH);

		JMenuItem start = new JMenuItem("Start");
		menuBar.add(start);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		JMenuItem stop = new JMenuItem("Stop");
		menuBar.add(stop);
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});

		JMenuItem reset = new JMenuItem("Reset");
		menuBar.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				ShowcaseWindow.this.canvas.repaint();
			}
		});

		JMenuItem explanation = new JMenuItem("Explanation");
		menuBar.add(explanation);
		explanation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				info().show(ShowcaseWindow.this);
			}
		});
	}

	protected void start() {
		active = true;
	}

	protected void stop() {
		active = false;
	}

	protected void reset() {
		boolean oldActive = active;
		active = false;
		clear();
		init();
		active = oldActive;
	}

	protected abstract void paint2d(Graphics2D g);

	protected abstract DesignPatternInfo info();

	protected abstract void init();
	protected abstract void clear();
	protected abstract void doOneRound();

	protected long waitingTime() {
		return 100;
	}

	protected boolean inBox(MouseEvent e, int x1, int y1, int x2, int y2) {
		if(x1 > e.getX() || x2 < e.getX())
			return false;
		if(y1 > e.getY() || y2 < e.getY())
			return false;
		return true;
	}

	protected void repaintCanvas() {
		this.canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void ensureInited() {
		if(!inited) {
			init();
			inited = true;
		}
	}

	private ShowcaseCanvas canvas;
	protected boolean active;
	private Thread thread;
	private boolean inited = false;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1l;
}
