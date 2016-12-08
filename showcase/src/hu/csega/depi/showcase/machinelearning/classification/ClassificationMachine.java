package hu.csega.depi.showcase.machinelearning.classification;

import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.VALUE_SIZE;
import static hu.csega.depi.showcase.machinelearning.common.MachineUtil.bytesToFloat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.Sigmoid;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class ClassificationMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		byte[] genes = chromosome.getGenes();
		for(int i = 0; i < params.length; i++)
			params[i] = bytesToFloat(genes, i * VALUE_SIZE);
	}

	@Override
	public Chromosome adamAndEve() {
		byte[] genes = new byte[params.length * VALUE_SIZE];
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 2) {
			throw new RuntimeException(2 + " input variables expected");
		}

		double calculatedValue = Sigmoid.of(v(input[0], input[1]));
		return calculatedValue;
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

	private float[] params = new float[5];
}
