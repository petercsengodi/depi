package hu.csega.depi.showcase.machinelearning.common.genetic.test;

import java.util.Map;
import java.util.TreeMap;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Chromosome;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.Population;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.PopulationKey;

public class GeneticTest {

	private static final Map<Double, Double> PATTERN = new TreeMap<Double, Double>();

	static {
		PATTERN.put(0.0, 3.0);
		PATTERN.put(1.0, 1.0);
		PATTERN.put(2.0, 1.33);
		PATTERN.put(3.0, 3.0);
		PATTERN.put(4.0, 2.0);
	}

	public static final DistanceFromOptimum DISTANCE = new DistanceFromOptimum() {

		@Override
		public double calculate(Chromosome chromosome) {
			byte[] genes = chromosome.getGenes();
			double C0 = genes[0] / 10.0;
			double C1 = genes[1] / 10.0;
			double C2 = genes[2] / 10.0;
			double C3 = genes[3] / 10.0;
			double C4 = genes[4] / 10.0;

			double sum = 0.0;
			for(Map.Entry<Double, Double> e : PATTERN.entrySet())
				sum += calc(C0, C1, C2, C3, C4, e.getKey(), e.getValue());

			return sum;
		}

		private double calc(double C0, double C1, double C2, double C3, double C4, double x, double y) {
			double result = C0 + C1 * x + C2 * x * x + C3 * x * x * x + C4 * x * x * x * x;
			return Math.pow((result-y), 2);
		}


		private static final long serialVersionUID = 1L;
	};

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		Chromosome adamAndEve = new Chromosome(5);
		System.out.println("Initial value: " + DISTANCE.calculate(adamAndEve));

		Population population = Population.builder(DISTANCE)
				.adamAndEve(adamAndEve)
				.build();

		population.mutate(100);
		population.keep(5);

		for(Map.Entry<PopulationKey, Chromosome> chromosome : population) {
			System.out.println("Mutated: " + chromosome);
		}

		population.crossOverSameLength(100);
		population.keep(5);

		for(Map.Entry<PopulationKey, Chromosome> chromosome : population) {
			System.out.println("Crossover: " + chromosome);
		}

		for(int rounds = 0; rounds < 10000; rounds++) {
			population.mutate(500);
			population.crossOverSameLength(500);
			population.keep(1000);
		}

		population.keep(1);

		for(Map.Entry<PopulationKey, Chromosome> chromosome : population) {
			System.out.println("Final: " + chromosome);
		}

		long end = System.currentTimeMillis();
		System.out.println("Duration: " + ((end - start) / 1000.0) + " sec.");
	}

}
