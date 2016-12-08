package hu.csega.depi.showcase.machinelearning.common;

import java.util.Map;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.CrossOverStrategy;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Population;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.PopulationKey;

public class ComputingWithGeneticAlgorythm implements ComputingAlgorythm {

	public ComputingWithGeneticAlgorythm() {
		this.scale = 1;
		this.rounds = 1000;
	}

	public ComputingWithGeneticAlgorythm(int scale, int rounds) {
		this.scale = scale;
		this.rounds = rounds;
	}

	@Override
	public void calculateMachine(Machine machine, DistanceFromOptimum distance, CrossOverStrategy crossOverStrategy) {

		long start = System.currentTimeMillis();

		Chromosome adamAndEve = machine.adamAndEve();
		int length = adamAndEve.getGenes().length;
		System.out.println("Initial value: " + distance.calculate(adamAndEve));

		Population population = Population.builder(distance)
				.adamAndEve(adamAndEve)
				.randomGenes(100, length)
				.build();

		int DIV = rounds / 10;
		System.out.print("[");

		for(int round = 0; round < rounds; round++) {
			population.initCrossOverStrategy(crossOverStrategy);
			population.crossOverSameLength(100*scale, crossOverStrategy);
			population.mutate(100*scale);
			population.createRandomGenes(100*scale, length);
			population.keep(200*scale);

			if((round + 1) % DIV == 0)
				System.out.print(".");
		}

		System.out.println("]");

		population.keep(1);

		Chromosome result = null;

		for(Map.Entry<PopulationKey, Chromosome> chromosome : population) {
			System.out.println("Final: " + chromosome);
			result = chromosome.getValue();
		}

		long end = System.currentTimeMillis();
		System.out.println("Duration: " + ((end - start) / 1000.0) + " sec.");

		machine.fillFromChromosome(result);
	}

	private int scale;
	private int rounds;
}
