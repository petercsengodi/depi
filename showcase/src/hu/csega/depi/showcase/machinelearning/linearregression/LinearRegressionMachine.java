package hu.csega.depi.showcase.machinelearning.linearregression;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class LinearRegressionMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		byte[] genes = chromosome.getGenes();
		params[0] = (int)(genes[0]);
		params[1] = byteToDouble(genes[1]);
	}

	@Override
	public Chromosome toChromosome() {
		byte[] genes = new byte[length()];
		genes[0] = (byte)(params[0]);
		genes[1] = doubleToByte(params[1]);
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 3) {
			throw new RuntimeException(3 + " input variables expected");
		}

		double intputValue = input[1];
		double calculatedValue = params[0] + params[1] * input[0];
		return Math.pow(calculatedValue-intputValue, 2);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(2f));
		g.setColor(Color.black);

		int x1 = -399;
		int y1 = (int)(params[0] + params[1] * x1);

		int x2 = 399;
		int y2 = (int)(params[0] + params[1] * x2);

		g.drawLine(x1, y1, x2, y2);
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

	private double[] params = new double[2];
}
