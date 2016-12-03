package hu.csega.depi.showcase.machinelearning.common;

import hu.csega.depi.showcase.machinelearning.common.genetic.framework.CrossOverStrategy;
import hu.csega.depi.showcase.machinelearning.common.genetic.framework.DistanceFromOptimum;

public interface ComputingAlgorythm {

	void calculateMachine(Machine machine, DistanceFromOptimum distance, CrossOverStrategy crossOverStrategy);

}
