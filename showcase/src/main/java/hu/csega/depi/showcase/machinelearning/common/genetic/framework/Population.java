package hu.csega.depi.showcase.machinelearning.common.genetic.framework;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Population implements Iterable<Map.Entry<PopulationKey, Chromosome>>, Serializable {

	public static class Builder {

		public Builder adamAndEve(Chromosome chromosome) {
			double distance = population.distanceStrategy.calculate(chromosome);
			population.chromosomes.put(new PopulationKey(distance, chromosome.getGenes()), chromosome);
			return this;
		}

		public Builder randomGenes(int numberOfRandomGenes, int length) {
			population.createRandomGenes(numberOfRandomGenes, length);
			return this;
		}

		public Population build() {
			return population;
		}

		private Population population;
	}

	public static Builder builder(DistanceFromOptimum distance) {
		Builder builder = new Builder();
		builder.population = new Population();
		builder.population.distanceStrategy = distance;
		return builder;
	}

	public void createRandomGenes(int numberOfRandomGenes, int length) {
		for(int i = 0; i < numberOfRandomGenes; i++) {
			Chromosome chromosome = Chromosome.createRandomGene(length);
			double distance = distanceStrategy.calculate(chromosome);
			chromosomes.put(new PopulationKey(distance, chromosome.getGenes()), chromosome);
		}
	}

	public void mutate(int numberOfMutations) {
		mutate(numberOfMutations, 1);
	}

	public void mutate(int numberOfMutations, int maxMutatedBytes) {
		TreeMap<PopulationKey, Chromosome> result = new TreeMap<>();
		result.putAll(chromosomes);

		Iterator<Chromosome> it = chromosomes.values().iterator();
		if(!it.hasNext())
			throw new RuntimeException("No genoms to mutate!");

		for(int i = 0; i < numberOfMutations; i++) {

			if(!it.hasNext()) {
				it = chromosomes.values().iterator();
			}

			Chromosome genomToMutate = it.next();
			Chromosome mutated = Chromosome.mutate(genomToMutate, maxMutatedBytes);
			double distance = distanceStrategy.calculate(mutated);
			result.put(new PopulationKey(distance, mutated.getGenes()), mutated);
		}

		chromosomes = result;
	}

	// FIXME csega: this is sh*t
	public void initCrossOverStrategy(CrossOverStrategy crossOverStrategy) {
		crossOverStrategy.setChromosomes(chromosomes.values());
	}

	public void crossOverSameLength(int numberOfCrossOvers) {
		crossOverSameLength(numberOfCrossOvers, new RandomCrossOverStrategy());
	}

	public void crossOverSameLength(int numberOfCrossOvers, CrossOverStrategy crossOverStrategy) {
		TreeMap<PopulationKey, Chromosome> result = new TreeMap<>();
		result.putAll(chromosomes);

		ChromosomePair children;
		double distance;

		for(int i = 0; i < numberOfCrossOvers; i++) {
			ChromosomePair pair = crossOverStrategy.select();

			if(pair == null) {
				System.out.println("Algorithm could select only " + i + " pairs, exiting loop.");
				break;
			}

			children = Chromosome.crossOverSameLength(pair.chromosome1, pair.chromosome2);

			// TODO csega: if new chromosomes already exist in map, shouldn't re-calculate them

			distance = distanceStrategy.calculate(children.chromosome1);
			result.put(new PopulationKey(distance, children.chromosome1.getGenes()), children.chromosome1);

			distance = distanceStrategy.calculate(children.chromosome2);
			result.put(new PopulationKey(distance, children.chromosome2.getGenes()), children.chromosome2);
		}

		chromosomes = result;
	}

	public void crossOverVariateLength(int numberOfCrossOvers) {
		crossOverSameLength(numberOfCrossOvers, new RandomCrossOverStrategy());
	}

	public void crossOverVariateLength(int numberOfCrossOvers, CrossOverStrategy crossOverStrategy) {
		results.putAll(chromosomes);

		ChromosomePair children;
		double distance;

		for(int i = 0; i < numberOfCrossOvers; i++) {
			ChromosomePair pair = crossOverStrategy.select();

			if(pair == null) {
				System.out.println("Algorithm could select only " + i + " pairs, exiting loop.");
				break;
			}

			children = Chromosome.crossOverVariateLength(pair.chromosome1, pair.chromosome2);
			distance = distanceStrategy.calculate(children.chromosome1);
			results.put(new PopulationKey(distance, children.chromosome1.getGenes()), children.chromosome1);
			distance = distanceStrategy.calculate(children.chromosome2);
			results.put(new PopulationKey(distance, children.chromosome2.getGenes()), children.chromosome2);
		}

		chromosomes.clear();
		chromosomes.putAll(results);
		results.clear();
	}

	public void keep(int numberOfGenomsToKeep) {
		TreeMap<PopulationKey, Chromosome> result = new TreeMap<>();
		Iterator<Map.Entry<PopulationKey, Chromosome>> it = chromosomes.entrySet().iterator();
		while(it.hasNext() && numberOfGenomsToKeep-- > 0) {
			Entry<PopulationKey, Chromosome> e = it.next();
			result.put(e.getKey(), e.getValue());
		}

		chromosomes = result;
	}

	public Iterator<Map.Entry<PopulationKey, Chromosome>> iterator() {
		return chromosomes.entrySet().iterator();
	}

	public int size() {
		return chromosomes.size();
	}

	private TreeMap<PopulationKey, Chromosome> chromosomes = new TreeMap<>();
	private TreeMap<PopulationKey, Chromosome> results = new TreeMap<>();
	private DistanceFromOptimum distanceStrategy;

	private static final long serialVersionUID = 1L;
}
