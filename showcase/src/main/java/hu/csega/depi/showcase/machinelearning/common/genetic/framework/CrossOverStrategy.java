package hu.csega.depi.showcase.machinelearning.common.genetic.framework;

import java.util.Collection;

public interface CrossOverStrategy {

	void setChromosomes(Collection<Chromosome> chromosomes);

	/**
	 * @return null, if this current strategy is out of choices for now.
	 */
	ChromosomePair select();

}
