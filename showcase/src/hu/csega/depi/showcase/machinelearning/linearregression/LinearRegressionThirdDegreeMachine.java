package hu.csega.depi.showcase.machinelearning.linearregression;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class LinearRegressionThirdDegreeMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		byte[] genes = chromosome.getGenes();
		params[0] = (int)(genes[0]);
		params[1] = byteToDouble(genes[1]);
		params[2] = byteToDouble(genes[2]) / 100.0;
		params[3] = byteToDouble(genes[3]) / 10000.0;
	}

	@Override
	public Chromosome toChromosome() {
		byte[] genes = new byte[length()];
		genes[0] = (byte)(params[0]);
		genes[1] = doubleToByte(params[1]);
		genes[2] = doubleToByte(params[2] * 100.0);
		genes[3] = doubleToByte(params[3] * 10000.0);
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 3) {
			throw new RuntimeException(3 + " input variables expected");
		}

		double intputValue = input[1];
		double calculatedValue = v(input[0]);
		return Math.pow(calculatedValue-intputValue, 2);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(2f));
		g.setColor(Color.black);

		int oldy = (int)v(-399);

		for(int x = -399; x <= 399; x += 10) {
			int y = (int)(v(x));
			g.drawLine(x-10, oldy, x, y);
			oldy = y;
		}
	}

	private double v(double x) {
		return (params[0] + params[1] * x + params[2] * x * x + params[3] * x * x * x);
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

	private double[] params = new double[4];
}
