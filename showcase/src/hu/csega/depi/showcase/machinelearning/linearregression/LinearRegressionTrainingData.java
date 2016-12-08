package hu.csega.depi.showcase.machinelearning.linearregression;

import java.util.Random;

import hu.csega.depi.showcase.machinelearning.common.TrainingData;
import hu.csega.depi.showcase.machinelearning.common.TrainingItem;

public class LinearRegressionTrainingData extends TrainingData {

	@Override
	public void init() {
		Random rnd = new Random(System.currentTimeMillis());
		for(int i = 0; i < items.length; i++) {
			int x = rnd.nextInt(800) - 400;
			int e = rnd.nextInt(maxError*2) - maxError;
			int value = (int)(C + (m * x) + e);
			TrainingItem item = new TrainingItem(x, value, value);
			addItem(item);
		}

		count = 0;
	}

	private static final int maxError = 50;
	private static final double C = 30;
	private static final double m = 0.25;

}
