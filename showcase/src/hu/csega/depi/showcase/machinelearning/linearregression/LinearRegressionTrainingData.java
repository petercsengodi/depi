package hu.csega.depi.showcase.machinelearning.linearregression;

import java.util.Random;

import hu.csega.depi.showcase.machinelearning.common.TrainingData;
import hu.csega.depi.showcase.machinelearning.common.TrainingItem;

public class LinearRegressionTrainingData extends TrainingData {

	@Override
	protected void initialize(TrainingItem item, Random rnd) {
		item.x = rnd.nextInt(800) - 400;
		int e = rnd.nextInt(maxError*2) - maxError;
		int value = (int)(C + (m * item.x) + e);
		item.y = value;
		item.v = value;
	}

	private static final int maxError = 50;
	private static final double C = 30;
	private static final double m = 0.25;

}
