package hu.csega.depi.showcase.machinelearning.linearregression;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.machinelearning.common.ComputingAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.ComputingWithGeneticAlgorythm;
import hu.csega.depi.showcase.machinelearning.common.DistanceMinimumError;
import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.MachineLearningWindow;
import hu.csega.depi.showcase.machinelearning.common.TrainingData;
import hu.csega.depi.showcase.machinelearning.common.TrainingItem;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.RandomCrossOverStrategy;

public class ShowLinearRegressionThirdDegree extends MachineLearningWindow {

	protected ShowLinearRegressionThirdDegree() {
		super(TITLE);
	}

	@Override
	protected void init() {
		if(trainingData == null) {
			 trainingData = new TrainingData();
			 trainingData.init();

			 error = new DistanceMinimumError(new LinearRegressionThirdDegreeMachine());
		}
	}

	@Override
	protected void clear() {
		trainingData.clear();
		resetCalculated();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		TrainingItem item = new TrainingItem(e.getX() - 400, e.getY() - 300, e.getY() - 300);
		trainingData.addItem(item);
		resetCalculated();
	}

	@Override
	protected void training() {
		error.setTrainingData(trainingData);
		algorythm.calculateMachine(machine, error, crossOverStrategy);
	}

	@Override
	protected void paint2d(Graphics2D g) {

		g.translate(400, 300);
		g.setStroke(new BasicStroke(3f));
		g.setColor(Color.blue);

		for(TrainingItem item: trainingData) {
			g.drawLine(item.x - 10, item.y - 10, item.x + 10, item.y + 10);
			g.drawLine(item.x - 10, item.y + 10, item.x + 10, item.y - 10);
		}

		if(isCalculated())
			machine.paint(g);

		g.translate(-400, -300);

		g.setColor(Color.BLACK);
		g.drawString("Select your own dots!", 30, 30);

	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("index.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowLinearRegressionThirdDegree();
	}

	public TrainingData trainingData;
	public DistanceMinimumError error;
	public Machine machine = new LinearRegressionThirdDegreeMachine();
	public ComputingAlgorythm algorythm = new ComputingWithGeneticAlgorythm(3, 1000);
	public RandomCrossOverStrategy crossOverStrategy = new RandomCrossOverStrategy();

	private static final String TITLE = "Linear Regression with more degrees";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
