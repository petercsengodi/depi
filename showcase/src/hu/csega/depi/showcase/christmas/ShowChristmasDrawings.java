package hu.csega.depi.showcase.christmas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hu.csega.depi.showcase.christmas.algorithms.ChristmasDrawingStrategy;
import hu.csega.depi.showcase.christmas.algorithms.Cubes;
import hu.csega.depi.showcase.christmas.algorithms.SimpleRecursiveLines;
import hu.csega.depi.showcase.christmas.algorithms.TreeRecursiveLines;
import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseCanvas;
import hu.csega.depi.showcase.framework.ShowcaseWindow;

public class ShowChristmasDrawings extends ShowcaseWindow {

	protected ShowChristmasDrawings() {
		super(TITLE);
	}

	protected void initComponents(Container container, ShowcaseCanvas canvas) {
		container.setLayout(new BorderLayout());
		JPanel lowerPanel = new JPanel();
		container.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.add(canvas);

		JMenuBar menuBar = new JMenuBar();
		container.add(menuBar, BorderLayout.NORTH);

		JMenu mDrawings = new JMenu("Drawings");
		menuBar.add(mDrawings);

		JMenuItem save = new JMenuItem("Simple recursive lines");
		mDrawings.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				strategy = new SimpleRecursiveLines();
				repaintCanvas();
			}
		});

		JMenuItem tree = new JMenuItem("Tree lines");
		mDrawings.add(tree);
		tree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				strategy = new TreeRecursiveLines();
				repaintCanvas();
			}
		});

		JMenuItem cubes = new JMenuItem("Present");
		mDrawings.add(cubes);
		cubes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				strategy = new Cubes();
				repaintCanvas();
			}
		});

		JMenuItem bad = new JMenuItem("Exit");
		menuBar.add(bad);
		bad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

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

		if(strategy != null)
			strategy.draw(g, -400, -300, 399, 299);

		g.translate(-400, -300);
	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("index.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowChristmasDrawings();
	}

	public ChristmasDrawingStrategy strategy;


	private static final String TITLE = "Christmas Drawings";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
