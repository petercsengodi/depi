package hu.csega.depi.showcase.machinelearning.classification;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class ClassificationMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		byte[] genes = chromosome.getGenes();
		params[0] = (int)(genes[0]);
		params[1] = byteToDouble(genes[1]);
		params[2] = byteToDouble(genes[2]) / 100.0;
		params[3] = byteToDouble(genes[3]);
		params[4] = byteToDouble(genes[4]) / 100.0;
	}

	@Override
	public Chromosome toChromosome() {
		byte[] genes = new byte[length()];
		genes[0] = (byte)(params[0]);
		genes[1] = doubleToByte(params[1]);
		genes[2] = doubleToByte(params[2] * 100.0);
		genes[3] = doubleToByte(params[3]);
		genes[4] = doubleToByte(params[4] * 100.0);
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 3) {
			throw new RuntimeException(3 + " input variables expected");
		}

		double intputValue = input[2];
		double calculatedValue = v(input[0], input[1]) > 0 ? 1 : 0;
		return Math.pow(calculatedValue-intputValue, 2);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(2f));
		g.setColor(Color.darkGray);

		for(int x = -399; x <= 399; x += 5) {
			for(int y = -299; y <= 299; y += 5) {
				if(v(x, y) < 0)
					g.fillRect(x - 2, y - 2, 5, 5);
			}
		}
	}

	private double v(double x, double y) {
		return (params[0] + params[1] * x + params[2] * x * x + params[3] * y + params[4] * y * y);
	}

	private int length() {
		return params.length;
	}

	private double byteToDouble(byte input) {
		return (double)(input / 10.0);
	}

	private byte doubleToByte(double input) {
		return (byte)(10 * input);
	}

	private double[] params = new double[5];
}
