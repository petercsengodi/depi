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
		for(int i = 0; i < params.length; i++)
			params[i] = (float)(genes[i] / Math.pow(100f,i));
	}

	@Override
	public Chromosome adamAndEve() {
		byte[] genes = new byte[params.length];
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 2) {
			throw new RuntimeException(2 + " input variables expected");
		}

		double calculatedValue = v(input[0]);
		return calculatedValue;
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
		double x2 = x * x;
		double x3 = x2 * x;
		return (params[0] + params[1] * x + params[2] * x2 + params[3] * x3);
	}

	private float[] params = new float[4];
}
