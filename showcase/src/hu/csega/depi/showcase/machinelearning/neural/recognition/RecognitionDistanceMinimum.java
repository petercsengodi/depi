package hu.csega.depi.showcase.machinelearning.neural.recognition;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;

public class RecognitionDistanceMinimum implements DistanceFromOptimum {

	public RecognitionDistanceMinimum(RecognitionMachine machine) {
		this.machine = machine;
	}

	public void setCount(RecognitionTrainingData data) {
		this.data = data;
		this.count = data.length;
	}

	@Override
	public double calculate(Chromosome chromosome) {
		machine.fillFromChromosome(chromosome);

		RecognitionCharacter[] items = data.items;

		double sum = 0.0;
		for(int i = 0; i < count; i++) {
			sum += Math.pow(machine.output(items[i].data) - (items[i].accepted ? 1 : 0), 2);
		}

		return sum;
	}

	private int count;
	private RecognitionTrainingData data;
	private RecognitionMachine machine;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}