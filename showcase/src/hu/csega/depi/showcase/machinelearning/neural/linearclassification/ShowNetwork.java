package hu.csega.depi.showcase.machinelearning.neural.linearclassification;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseWindow;
import hu.csega.depi.showcase.machinelearning.common.ComputingAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.ComputingWithGeneticAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.DistanceMinimumError;
import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.TrainingData;
import hu.csega.depi.showcase.machinelearning.common.TrainingItem;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.RandomCrossOverStrategy;

public class ShowNetwork extends ShowcaseWindow {

	protected ShowNetwork() {
		super(TITLE);
	}

	@Override
	protected void init() {
		calculated = false;
		if(trainingData == null) {
			 trainingData = new NetworkTrainingData();
			 trainingData.init();

			 error = new DistanceMinimumError(machine, trainingData);
		}
	}

	@Override
	protected void clear() {
		machine = new NetworkMachine();
		count = 0;
	}

	@Override
	protected void doOneRound() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		TrainingItem item = trainingData.getItems()[count++];
		item.x = e.getX() - 400;
		item.y = e.getY() - 300;
		item.v = (((e.getButton() & MouseEvent.BUTTON3) == MouseEvent.BUTTON3) ? 1 : 0);

		calculated = false;
		repaintCanvas();
	}

	@Override
	protected long waitingTime() {
		return 100l;
	}

	@Override
	protected void paint2d(Graphics2D g) {
		g.translate(400, 300);

		if(active && !calculated) {
			error.setCount(count);
			algorythm.calculateMachine(machine, error, crossOverStrategy);
			calculated = true;
		}

		if(active)
			machine.paint(g);

		g.setStroke(new BasicStroke(3f));
		TrainingItem[] items = trainingData.getItems();

		for(int i = 0; i < count; i++) {
			TrainingItem item = items[i];
			g.setColor(item.v == 0 ? Color.green : Color.red);
			g.drawLine(item.x - 10, item.y - 10, item.x + 10, item.y + 10);
			g.drawLine(item.x - 10, item.y + 10, item.x + 10, item.y - 10);
		}

		g.translate(-400, -300);

		g.setColor(Color.BLACK);
		g.drawString("Select your own dots! Left: green Right: red", 30, 30);

	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("index.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowNetwork();
	}

	public TrainingData trainingData;
	public DistanceMinimumError error;
	public Machine machine = new NetworkMachine();
	public ComputingAlgorythm algorythm = new ComputingWithGeneticAlgorythm();
	public RandomCrossOverStrategy crossOverStrategy = new RandomCrossOverStrategy();

	private int count = 0;
	private boolean calculated;

	private static final String TITLE = "Neural Network";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
