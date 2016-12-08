package hu.csega.depi.showcase.machinelearning.linearregression;

import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.FLOAT_SIZE;
import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.bytesToFloat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.MachineUtil;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class LinearRegressionMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		byte[] genes = chromosome.getGenes();
		for(int i = 0; i < params.length; i++)
			params[i] = bytesToFloat(genes, i * FLOAT_SIZE);
	}

	@Override
	public Chromosome toChromosome() {
		byte[] genes = new byte[params.length * FLOAT_SIZE];
		for(int i = 0; i < params.length; i++)
			 MachineUtil.floatToBytes(params[i], genes, i * FLOAT_SIZE);
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 2) {
			throw new RuntimeException(2 + " input variables expected");
		}

		double calculatedValue = params[0] + params[1] * input[0];
		return calculatedValue;
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

	private float[] params = new float[2];
}
