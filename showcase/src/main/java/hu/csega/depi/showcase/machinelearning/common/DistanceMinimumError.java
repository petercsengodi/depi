package hu.csega.depi.showcase.machinelearning.common;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;

public class DistanceMinimumError implements DistanceFromOptimum {

	public DistanceMinimumError(Machine machine) {
		this.machine = machine;
	}

	public void setTrainingData(TrainingData data) {
		this.data = data;
	}

	@Override
	public double calculate(Chromosome chromosome) {
		machine.fillFromChromosome(chromosome);

		double sum = 0.0;
		for(TrainingItem item : data) {
			input[0] = item.x;
			input[1] = item.y;

			double diff = machine.output(input) - item.v;
			sum += diff * diff;
		}

		return sum / (2.0 * data.getCount());
	}

	private double[] input = new double[2];
	private TrainingData data;
	private Machine machine;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}
