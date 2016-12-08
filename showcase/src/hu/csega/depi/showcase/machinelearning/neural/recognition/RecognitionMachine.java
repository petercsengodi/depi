package hu.csega.depi.showcase.machinelearning.neural.recognition;

import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.VALUE_SIZE;
import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.bytesToFloat;
import java.awt.Graphics2D;
import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.Sigmoid;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class RecognitionMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		int counter = 0;
		byte[] genes = chromosome.getGenes();

		if(genes.length != length()) {
			throw new RuntimeException("Gene must be " + length() + " long!");
		}

		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < INPUT_PARAMETERS + 1; j++) {
				firstParameters[i][j] = bytesToFloat(genes, counter);
				counter += VALUE_SIZE;
			}
		}

		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				secondParameters[i][j] = bytesToFloat(genes, counter);
				counter += VALUE_SIZE;
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				thirdParameters[i][j] = bytesToFloat(genes, counter);
				counter += VALUE_SIZE;
			}
		}
	}

	@Override
	public Chromosome adamAndEve() {
		byte[] genes = new byte[length()];
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != RecognitionCharacter.INPUTS) {
			throw new RuntimeException(RecognitionCharacter.INPUTS + " input variables expected");
		}

		// clear layers
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++)
			firstLayer[i] = 0.0f;
		firstLayer[HIDDEN_LAYER_NODES] = 1;
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++)
			secondLayer[i] = 0.0f;
		secondLayer[HIDDEN_LAYER_NODES] = 1;
		thirdLayer[0] = 0.0f;

		// fill input layer
		for(int j = 0; j < INPUT_PARAMETERS; j++)
			inputLayer[j] = (float)input[j];
		inputLayer[INPUT_PARAMETERS] = 1;

		// fill first layer
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < INPUT_PARAMETERS + 1; j++) {
				firstLayer[i] += firstParameters[i][j] * inputLayer[j];
			}
		}
		firstLayer[HIDDEN_LAYER_NODES] = 1;

		// fill second layer
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				secondLayer[i] += secondParameters[i][j] * firstLayer[j];
			}
		}
		secondLayer[HIDDEN_LAYER_NODES] = 1;

		// third second layer
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				thirdLayer[i] += thirdParameters[i][j] * secondLayer[j];
			}
		}

		// return output
		double calculatedValue = Sigmoid.of(thirdLayer[0]);
		return calculatedValue;
	}

	@Override
	public void paint(Graphics2D g) {
	}

	private int length() {
		int numberOfFloats = (INPUT_PARAMETERS + 1) * HIDDEN_LAYER_NODES + (HIDDEN_LAYER_NODES + 1) * HIDDEN_LAYER_NODES + HIDDEN_LAYER_NODES + 1;
		return numberOfFloats * VALUE_SIZE;
	}

	private float[][] firstParameters = new float[HIDDEN_LAYER_NODES][INPUT_PARAMETERS + 1];
	private float[][] secondParameters = new float[HIDDEN_LAYER_NODES][HIDDEN_LAYER_NODES + 1];
	private float[][] thirdParameters = new float[1][HIDDEN_LAYER_NODES + 1];

	private float[] inputLayer = new float[INPUT_PARAMETERS + 1];
	private float[] firstLayer = new float[HIDDEN_LAYER_NODES + 1];
	private float[] secondLayer = new float[HIDDEN_LAYER_NODES + 1];
	private float[] thirdLayer = new float[1];

	public static final int INPUT_PARAMETERS = RecognitionCharacter.INPUTS;
	public static final int HIDDEN_LAYER_NODES = 6;

}
