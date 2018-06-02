package hu.csega.depi.showcase.machinelearning.linearregression;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseWindow;
import hu.csega.depi.showcase.machinelearning.common.ComputingAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.ComputingWithGeneticAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.DistanceMinimumError;
import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.TrainingData;
import hu.csega.depi.showcase.machinelearning.common.TrainingItem;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.RandomCrossOverStrategy;

public class ShowLinearRegression extends ShowcaseWindow {

	protected ShowLinearRegression() {
		super(TITLE);
	}

	@Override
	protected void init() {
		if(trainingData == null) {
			 trainingData = new LinearRegressionTrainingData();
			 trainingData.init();

			 error = new DistanceMinimumError(new LinearRegressionMachine());
		}
	}

	@Override
	protected void clear() {
		trainingData.clear();
		repaintCanvas();
	}

	@Override
	protected void doOneRound() {
		trainingData.increaseCounter();
		error.setTrainingData(trainingData);
		algorythm.calculateMachine(machine, error, crossOverStrategy);
	}

	@Override
	protected long waitingTime() {
		return 100l;
	}

	@Override
	protected void paint2d(Graphics2D g) {
		g.translate(400, 300);
		g.setStroke(new BasicStroke(3f));
		g.setColor(Color.blue);

		for(TrainingItem item : trainingData) {
			g.drawLine(item.x - 10, item.y - 10, item.x + 10, item.y + 10);
			g.drawLine(item.x - 10, item.y + 10, item.x + 10, item.y - 10);
		}

		machine.paint(g);

		g.translate(-400, -300);

		g.setColor(Color.black);
		g.drawString("Hit start!", 30, 30);
	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("index.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowLinearRegression();
	}

	public TrainingData trainingData;
	public DistanceMinimumError error;
	public Machine machine = new LinearRegressionMachine();
	public ComputingAlgorythm algorythm = new ComputingWithGeneticAlgorythm();
	public RandomCrossOverStrategy crossOverStrategy = new RandomCrossOverStrategy();

	private static final String TITLE = "Linear Regression";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
