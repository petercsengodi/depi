package hu.csega.depi.showcase.machinelearning.neural;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class NetworkFromGenes {

	public void fillFromChromosome(Chromosome chromosome) {
		int counter = 0;
		byte[] genes = chromosome.getGenes();

		if(genes.length != length()) {
			throw new RuntimeException("Gene must be " + length() + " long!");
		}

		for(int i = 0; i < NODES; i++) {
			for(int j = 0; j < NODES; j++) {
				firstParameters[i][j] = byteToDouble(genes[counter++]);
			}
		}

		for(int i = 0; i < NODES; i++) {
			for(int j = 0; j < NODES; j++) {
				secondParameters[i][j] = byteToDouble(genes[counter++]);
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < NODES; j++) {
				thirdParameters[i][j] = byteToDouble(genes[counter++]);
			}
		}
	}

	public Chromosome toChromosome() {
		byte[] genes = new byte[length()];

		int counter = 0;

		for(int i = 0; i < NODES; i++) {
			for(int j = 0; j < NODES; j++) {
				genes[counter++] = doubleToByte(firstParameters[i][j]);
			}
		}

		for(int i = 0; i < NODES; i++) {
			for(int j = 0; j < NODES; j++) {
				genes[counter++] = doubleToByte(secondParameters[i][j]);
			}
		}

		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < NODES; j++) {
				genes[counter++] = doubleToByte(thirdParameters[i][j]);
			}
		}

		return new Chromosome(genes);
	}

	public double output(double[] input) {
		if(input.length != NODES) {
			throw new RuntimeException(NODES + " input variables expected");
		}

		// clear layers
		thirdLayer[0] = 0.0;
		for(int i = 0; i < NODES; i++) {
			firstLayer[i] = 0.0;
			secondLayer[i] = 0.0;
		}

		// fill first layer
		for(int i = 0; i < NODES; i++) {
			for(int j = 0; j < NODES; j++) {
				firstLayer[i] += firstParameters[i][j] * input[j];
			}
		}

		// fill second layer
		for(int i = 0; i < NODES; i++) {
			for(int j = 0; j < NODES; j++) {
				secondLayer[i] += secondParameters[i][j] * firstLayer[j];
			}
		}

		// third second layer
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < NODES; j++) {
				thirdLayer[i] += thirdParameters[i][j] * secondLayer[j];
			}
		}

		// return output
		return thirdLayer[0];
	}

	private int length() {
		return NODES*NODES * 2 + NODES;
	}

	private double byteToDouble(byte input) {
		return (double)(input / 10.0);
	}

	private byte doubleToByte(double input) {
		return (byte)(10 * input);
	}

	private double[][] firstParameters = new double[NODES][NODES];
	private double[][] secondParameters = new double[NODES][NODES];
	private double[][] thirdParameters = new double[1][NODES];

	private double[] firstLayer = new double[NODES];
	private double[] secondLayer = new double[NODES];
	private double[] thirdLayer = new double[1];

	public static final int NODES = 4;
}
