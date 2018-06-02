package hu.csega.depi.showcase.machinelearning.clusterization;

import java.awt.Graphics2D;

import hu.csega.depi.showcase.machinelearning.common.Machine;
import hu.csega.depi.showcase.machinelearning.common.TrainingItem;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;

public class ClusterizationMachine implements Machine {

	@Override
	public void fillFromChromosome(Chromosome chromosome) {
		byte[] genes = chromosome.getGenes();
		params[0] = (double)(genes[0]) * 2;
		params[1] = (double)(genes[1]) * 2;
		params[2] = (double)(genes[2]) * 2;
		params[3] = (double)(genes[3]) * 2;
		params[4] = (double)(genes[4]) * 2;
		params[5] = (double)(genes[5]) * 2;
	}

	@Override
	public Chromosome adamAndEve() {
		byte[] genes = new byte[length()];
		return new Chromosome(genes);
	}

	@Override
	public double output(double[] input) {
		if(input.length != 2) {
			throw new RuntimeException(2 + " input variables expected");
		}

		double dist1 = d1(input[0], input[1]);
		double dist2 = d2(input[0], input[1]);
		double dist3 = d3(input[0], input[1]);

		return Math.min(dist1, Math.min(dist2, dist3));
	}

	public int output(TrainingItem item) {

		double dist1 = d1(item.x, item.y);
		double dist2 = d2(item.x, item.y);
		double dist3 = d3(item.x, item.y);
		double dist = Math.min(dist1, Math.min(dist2, dist3));

		if(dist == dist1)
			return 1;
		else if(dist == dist2)
			return 2;
		else
			return 3;
	}

	@Override
	public void paint(Graphics2D g) {
	}

	private double d1(double x, double y) {
		return (params[0] - x) * (params[0] - x) + (params[1] - y) * (params[1] - y);
	}

	private double d2(double x, double y) {
		return (params[2] - x) * (params[2] - x) + (params[3] - y) * (params[3] - y);
	}

	private double d3(double x, double y) {
		return (params[4] - x) * (params[4] - x) + (params[5] - y) * (params[5] - y);
	}

	private int length() {
		return params.length;
	}

	private double[] params = new double[6];
}
