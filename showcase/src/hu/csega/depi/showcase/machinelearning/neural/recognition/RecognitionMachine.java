package hu.csega.depi.showcase.machinelearning.neural.recognition;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
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
				firstParameters[i][j] = byteToDouble(genes[counter++]);
			}
		}

		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				secondParameters[i][j] = byteToDouble(genes[counter++]);
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				thirdParameters[i][j] = byteToDouble(genes[counter++]);
			}
		}
	}

	@Override
	public Chromosome toChromosome() {
		byte[] genes = new byte[length()];

		int counter = 0;

		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < INPUT_PARAMETERS + 1; j++) {
				genes[counter++] = doubleToByte(firstParameters[i][j]);
			}
		}

		for(int i = 0; i < HIDDEN_LAYER_NODES; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				genes[counter++] = doubleToByte(secondParameters[i][j]);
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < HIDDEN_LAYER_NODES + 1; j++) {
				genes[counter++] = doubleToByte(thirdParameters[i][j]);
			}
		}

		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != RecognitionCharacter.INPUTS) {
			throw new RuntimeException(RecognitionCharacter.INPUTS + " input variables expected");
		}

		// clear layers
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++)
			firstLayer[i] = 0.0;
		firstLayer[HIDDEN_LAYER_NODES] = 1;
		for(int i = 0; i < HIDDEN_LAYER_NODES; i++)
			secondLayer[i] = 0.0;
		secondLayer[HIDDEN_LAYER_NODES] = 1;
		thirdLayer[0] = 0.0;

		// fill input layer
		for(int j = 0; j < INPUT_PARAMETERS; j++)
			inputLayer[j] = input[j];
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
		double intputValue = input[2];
		double calculatedValue = (thirdLayer[0] > 0 ? 1 : 0);
		return Math.pow(calculatedValue-intputValue, 2);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(300, -300, 200, 600);

		// draw NN



		// draw MAP

		g.setColor(Color.darkGray);

		double[] input = new double[3];

		for(int x = -299; x <= 299; x += 5) {
			for(int y = -299; y <= 299; y += 5) {
				input[0] = x;
				input[1] = y;

				if(output(input) < 0.5)
					g.fillRect(x - 2, y - 2, 5, 5);
			}
		}
	}

	private int length() {
		return (INPUT_PARAMETERS + 1) * HIDDEN_LAYER_NODES + (HIDDEN_LAYER_NODES + 1) * HIDDEN_LAYER_NODES + HIDDEN_LAYER_NODES + 1;
	}

	private double byteToDouble(byte input) {
		return (double)(input / 10.0);
	}

	private byte doubleToByte(double input) {
		return (byte)(10 * input);
	}

	private double[][] firstParameters = new double[HIDDEN_LAYER_NODES][INPUT_PARAMETERS + 1];
	private double[][] secondParameters = new double[HIDDEN_LAYER_NODES][HIDDEN_LAYER_NODES + 1];
	private double[][] thirdParameters = new double[1][HIDDEN_LAYER_NODES + 1];

	private double[] inputLayer = new double[INPUT_PARAMETERS + 1];
	private double[] firstLayer = new double[HIDDEN_LAYER_NODES + 1];
	private double[] secondLayer = new double[HIDDEN_LAYER_NODES + 1];
	private double[] thirdLayer = new double[1];

	public static final int INPUT_PARAMETERS = RecognitionCharacter.INPUTS;
	public static final int HIDDEN_LAYER_NODES = 6;

}
