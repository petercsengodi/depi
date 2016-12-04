package hu.csega.depi.showcase.machinelearning.neural.recognition;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;

public class RecognitionDistanceMinimum implements DistanceFromOptimum {

	public RecognitionDistanceMinimum(RecognitionMachine machine, RecognitionTrainingData data) {
		this.machine = machine;
		this.data = data;
		this.count = data.items.length;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public double calculate(Chromosome chromosome) {
		machine.fillFromChromosome(chromosome);

		RecognitionCharacter[] items = data.items;

		double sum = 0.0;
		for(int i = 0; i < count; i++) {
			sum += machine.output(items[i].data);
		}

		return sum;
	}

	private int count;
	private RecognitionTrainingData data;
	private RecognitionMachine machine;

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}
