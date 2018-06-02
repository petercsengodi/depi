package hu.csega.depi.showcase.machinelearning.common;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hu.csega.depi.showcase.framework.ShowcaseCanvas;
import hu.csega.depi.showcase.framework.ShowcaseWindow;

public abstract class MachineLearningWindow extends ShowcaseWindow {

	protected MachineLearningWindow(String title) {
		super(title);
	}

	protected void initComponents(Container container, ShowcaseCanvas canvas) {
		container.setLayout(new BorderLayout());
		JPanel lowerPanel = new JPanel();
		container.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.add(canvas);

		JMenuBar menuBar = new JMenuBar();
		container.add(menuBar, BorderLayout.NORTH);

		JMenuItem training = new JMenuItem("Training");
		menuBar.add(training);
		training.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				training();
				calculated = true;
				repaintCanvas();
			}
		});

		JMenuItem reset = new JMenuItem("Reset");
		menuBar.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				repaintCanvas();
			}
		});
	}

	@Override
	protected void doOneRound() {
	}

	protected void resetCalculated() {
		calculated = false;
		repaintCanvas();
	}

	protected boolean isCalculated() {
		return calculated;
	}

	protected void training() {}

	private boolean calculated = false;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1l;
}
