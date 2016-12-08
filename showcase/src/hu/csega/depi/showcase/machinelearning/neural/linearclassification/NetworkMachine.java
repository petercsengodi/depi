package hu.csega.depi.showcase.machinelearning.neural.linearclassification;

import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.FLOAT_SIZE;
import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.bytesToFloat;
import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.floatToBytes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.Sigmoid;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class NetworkMachine implements Machine {

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
				counter += FLOAT_SIZE;
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				secondParameters[i][j] = bytesToFloat(genes, counter);
				counter += FLOAT_SIZE;
			}
		}
	}

	@Override
	public Chromosome toChromosome() {
		byte[] genes = new byte[length()];

		int counter = 0;

		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < INPUT_PARAMETERS + 1; j++) {
				floatToBytes(firstParameters[i][j], genes, counter);
				counter += FLOAT_SIZE;
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				floatToBytes(secondParameters[i][j], genes, counter);
				counter += FLOAT_SIZE;
			}
		}

		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 2) {
			throw new RuntimeException(2 + " input variables expected");
		}

		// clear layers
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++)
			firstLayer[i] = 0.0f;
		firstLayer[HIDDEN_LAYER_NODES] = 1;
		secondLayer[0] = 0.0f;

		// fill input layer
//		for(int j = 0; j < INPUT_PARAMETERS; j++)
//			inputLayer[j] = (float)input[j];
		float x = (float)input[0];
		float y = (float)input[1];

		inputLayer[0] = x;
		inputLayer[1] = x * x;
		inputLayer[2] = y;
		inputLayer[3] = y * y;

		inputLayer[INPUT_PARAMETERS] = 1;

		// fill first layer
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < INPUT_PARAMETERS + 1; j++) {
				firstLayer[i] += firstParameters[i][j] * inputLayer[j];
			}
		}
		firstLayer[HIDDEN_LAYER_NODES] = 1;

		// third second layer
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				secondLayer[i] += secondParameters[i][j] * firstLayer[j];
			}
		}

		// return output
		double calculatedValue = Sigmoid.of(secondLayer[0]);
		return calculatedValue;
	}

	@Override
	public void paint(Graphics2D g) {
		// draw MAP

		g.setStroke(new BasicStroke(2f));
		g.setColor(Color.darkGray);

		double[] input = new double[3];

		for(int x = -399; x <= 399; x += 5) {
			for(int y = -299; y <= 299; y += 5) {
				input[0] = x;
				input[1] = y;

				if(output(input) < 0.5)
					g.fillRect(x - 2, y - 2, 5, 5);
			}
		}
	}

	private int length() {
		int numberOfFloats = (INPUT_PARAMETERS + 1) * HIDDEN_LAYER_NODES + HIDDEN_LAYER_NODES + 1;
		return numberOfFloats * FLOAT_SIZE;
	}

	private float[][] firstParameters = new float[HIDDEN_LAYER_NODES][INPUT_PARAMETERS + 1];
	private float[][] secondParameters = new float[HIDDEN_LAYER_NODES][HIDDEN_LAYER_NODES + 1];

	private float[] inputLayer = new float[INPUT_PARAMETERS + 1];
	private float[] firstLayer = new float[HIDDEN_LAYER_NODES + 1];
	private float[] secondLayer = new float[1];

	public static final int INPUT_PARAMETERS = 4;
	public static final int HIDDEN_LAYER_NODES = 10;

}
