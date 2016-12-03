package hu.csega.depi.showcase.machinelearning.common.genetic.framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RandomCrossOverStrategy implements CrossOverStrategy {

	public void setChromosomes(Collection<Chromosome> chromosomes) {
		this.list.clear();
		this.list.addAll(chromosomes);
		this.listSize = list.size();
	}

	@Override
	public ChromosomePair select() {
		ChromosomePair pair = new ChromosomePair();
		pair.chromosome1 = list.get(Chromosome.RND.nextInt(listSize));
		pair.chromosome2 = list.get(Chromosome.RND.nextInt(listSize));
		return pair;
	}

	private List<Chromosome> list = new ArrayList<>();
	private int listSize;
}
