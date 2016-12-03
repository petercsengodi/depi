package hu.csega.depi.showcase.machinelearning.common;

import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public interface Machine {

	void fillFromChromosome(Chromosome chromosome);

	Chromosome toChromosome();

	double output(double[] input);

	void paint(Graphics2D g);

}
