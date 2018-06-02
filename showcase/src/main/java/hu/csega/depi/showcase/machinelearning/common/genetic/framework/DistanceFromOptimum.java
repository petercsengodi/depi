package hu.csega.depi.showcase.machinelearning.common.genetic.framework;

import java.io.Serializable;

public interface DistanceFromOptimum extends Serializable {

	double calculate(Chromosome chromosome);

}
