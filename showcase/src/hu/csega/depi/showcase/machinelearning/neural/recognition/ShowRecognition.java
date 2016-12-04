package hu.csega.depi.showcase.machinelearning.neural.recognition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseCanvas;
import hu.csega.depi.showcase.framework.ShowcaseWindow;
import hu.csega.depi.showcase.machinelearning.common.ComputingAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.ComputingWithGeneticAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.RandomCrossOverStrategy;

public class ShowRecognition extends ShowcaseWindow {

	protected ShowRecognition() {
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

		JMenuItem good = new JMenuItem("Good Example");
		menuBar.add(good);
		good.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pushExample(true);
			}
		});

		JMenuItem bad = new JMenuItem("Bad Example");
		menuBar.add(bad);
		bad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pushExample(false);
			}
		});

		JMenuItem hint = new JMenuItem("Guess it!");
		menuBar.add(hint);
		hint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hint();
			}
		});

		JMenuItem resetCanvas = new JMenuItem("Reset Input Panel");
		menuBar.add(resetCanvas);
		resetCanvas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearImage();
				message = "";
				repaintCanvas();
			}
		});

		JMenuItem reset = new JMenuItem("Reset Training Data");
		menuBar.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				repaintCanvas();
			}
		});

		JMenuItem explanation = new JMenuItem("Explanation");
		menuBar.add(explanation);
		explanation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				info().show(ShowRecognition.this);
			}
		});
	}

	@Override
	protected void init() {
		if(trainingData == null) {
			 trainingData = new RecognitionTrainingData();

			 error = new RecognitionDistanceMinimum(machine, trainingData);
		}

		clearImage();
	}

	private void clearImage() {
		Graphics g = input.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, input.getWidth(), input.getHeight());
	}

	@Override
	protected void clear() {
		machine = new RecognitionMachine();
		trainingData = new RecognitionTrainingData();
		count = 0;
		clearImage();
	}

	@Override
	protected void doOneRound() {
	}

	public void pushExample(boolean accepted) {
		if(count >= trainingData.items.length) {
			message = "Training data is full!";
			repaintCanvas();
			return;
		}

		RecognitionCharacter c = new RecognitionCharacter();
		c.fillFromImage(input, CUBE_WIDTH, CUBE_HEIGHT);
		c.accepted = accepted;

		trainingData.items[count++] = c;
		message = "Training data count: " + count;

		clearImage();
		calculated = false;
		repaintCanvas();
	}

	public void hint() {
		if(!calculated) {
			error.setCount(count);
			algorythm.calculateMachine(machine, error, crossOverStrategy);
			calculated = true;
		}

		RecognitionCharacter c = new RecognitionCharacter();
		c.fillFromImage(input, CUBE_WIDTH, CUBE_HEIGHT);
		double output = machine.output(c.data);
		message = "Guessed: " + (output > 0.5 ? "YES" : "NO") + " (Output value: " + output + ")";
		repaintCanvas();
	}

	@Override
	protected long waitingTime() {
		return 100l;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if((e.getButton() & MouseEvent.BUTTON1) > 0 && inBox(e, 0, 0, input.getWidth()-1, input.getHeight()-1)) {
			synchronized (this) {
				pressed = true;

				Graphics graphics = input.getGraphics();
				graphics.setColor(Color.black);
				graphics.fillOval(e.getX() - 4, e.getY() - 4, 9, 9);
			}

			repaintCanvas();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(pressed && inBox(e, 0, 0, input.getWidth()-1, input.getHeight()-1)) {
			synchronized (this) {
				Graphics graphics = input.getGraphics();
				graphics.setColor(Color.black);
				graphics.fillOval(e.getX() - 4, e.getY() - 4, 9, 9);
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
		g.drawImage(input, 0, 0, null);
		g.setColor(Color.black);

		if(message != null)
			g.drawString(message, 20, 580);
	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("index.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowRecognition();
	}

	public RecognitionTrainingData trainingData;
	public RecognitionDistanceMinimum error;
	public RecognitionMachine machine = new RecognitionMachine();
	public ComputingAlgorythm algorythm = new ComputingWithGeneticAlgorythm();
	public RandomCrossOverStrategy crossOverStrategy = new RandomCrossOverStrategy();

	private int count = 0;
	private boolean pressed;
	private boolean calculated;
	private String message;

	private static final int CUBE_WIDTH = 5;
	private static final int CUBE_HEIGHT = 5;

	private BufferedImage input = new BufferedImage(RecognitionCharacter.WIDTH * CUBE_WIDTH,
			RecognitionCharacter.HEIGHT * CUBE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	private static final String TITLE = "Neural Network 2";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
