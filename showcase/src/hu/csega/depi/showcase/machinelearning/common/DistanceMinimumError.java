package hu.csega.depi.showcase.machinelearning.common;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;

public class DistanceMinimumError implements DistanceFromOptimum {

	public DistanceMinimumError(Machine machine, TrainingData data) {
		this.machine = machine;
		this.data = data;
		this.count = data.getItems().length;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public double calculate(Chromosome chromosome) {
		machine.fillFromChromosome(chromosome);

		TrainingItem[] items = data.getItems();

		double sum = 0.0;
		for(int i = 0; i < count; i++) {
			TrainingItem item = items[i];
			input[0] = item.x;
			input[1] = item.y;

			sum += Math.pow(machine.output(input) - item.v, 2);
		}

		return sum;
	}

	private double[] input = new double[2];
	private int count;
	private TrainingData data;
	private Machine machine;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}
