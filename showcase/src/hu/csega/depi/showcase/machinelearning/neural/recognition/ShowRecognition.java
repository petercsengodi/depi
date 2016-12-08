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
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
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

		JMenu mTrainingData = new JMenu("Training Data");
		menuBar.add(mTrainingData);

		JMenuItem train = new JMenuItem("Train machine");
		mTrainingData.add(train);
		train.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				error.setCount(trainingData);
				algorythm.calculateMachine(machine, error, crossOverStrategy);
				message = "Machine trained.";
				calculated = true;
				repaintCanvas();
			}
		});

		JMenuItem load = new JMenuItem("Load");
		mTrainingData.add(load);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(ShowRecognition.this) == JFileChooser.APPROVE_OPTION) {
				  File file = fileChooser.getSelectedFile();

				  if(file.exists()) {
						try {
							trainingData = RecognitionTrainingData.load(file);
							message = "Loaded.";
						} catch (Exception ex) {
							message = "Loading failed :-(";
						}
				  } else {
						message = "Can't read file :-(";
				  }
				}

				repaintCanvas();
			}
		});

		JMenuItem save = new JMenuItem("Save");
		mTrainingData.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(ShowRecognition.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						RecognitionTrainingData.save(file, trainingData);
						message = "Saved.";
					} catch (Exception ex) {
						message = "Saving failed :-(";
					}
				}

				repaintCanvas();
			}
		});

		JMenuItem reset = new JMenuItem("Reset");
		mTrainingData.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				repaintCanvas();
			}
		});

	}

	@Override
	protected void init() {
		if(trainingData == null) {
			 trainingData = new RecognitionTrainingData();

			 error = new RecognitionDistanceMinimum(machine);
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
		trainingData.length = 0;
		clearImage();
	}

	@Override
	protected void doOneRound() {
	}

	public void pushExample(boolean accepted) {
		if(trainingData.length >= trainingData.items.length) {
			message = "Training data is full!";
			repaintCanvas();
			return;
		}

		RecognitionCharacter c = new RecognitionCharacter();
		c.fillFromImage(input, CUBE_WIDTH, CUBE_HEIGHT);
		c.accepted = accepted;

		trainingData.items[trainingData.length++] = c;
		message = "Training data count: " + trainingData.length;

		clearImage();
		repaintCanvas();
	}

	public void hint() {
		if(!calculated) {
			message = "Machine not trained, yet!";
			return;
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

		message = "";
		repaintCanvas();
	}

	@Override
	protected void paint2d(Graphics2D g) {
		// input panel
		g.drawImage(input, 0, 0, null);


		// cubes
		toShow.fillFromImage(input, CUBE_WIDTH, CUBE_HEIGHT);
		for(int x = 0; x < RecognitionCharacter.WIDTH; x++) {
			for(int y = 0; y < RecognitionCharacter.HEIGHT; y++) {
				double d = toShow.data[y * RecognitionCharacter.WIDTH + x];
				if(d > 0.5)
					g.setColor(Color.black);
				else
					g.setColor(Color.white);

				g.fillRect(200 + x * 25, 20 + y * 25, 20, 20);
			}
		}

		// everything else
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

	private boolean pressed;
	private boolean calculated;
	private String message;

	private static final int CUBE_WIDTH = 5;
	private static final int CUBE_HEIGHT = 5;

	private BufferedImage input = new BufferedImage(RecognitionCharacter.WIDTH * CUBE_WIDTH,
			RecognitionCharacter.HEIGHT * CUBE_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private RecognitionCharacter toShow = new RecognitionCharacter();

	private static final String TITLE = "Neural Network 2";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
