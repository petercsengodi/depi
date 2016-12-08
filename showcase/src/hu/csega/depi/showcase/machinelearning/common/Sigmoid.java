package hu.csega.depi.showcase.machinelearning.common;

public class Sigmoid {

	public static double of(double x) {
		return 1.0 / (1.0 + Math.exp(-x));
	}

}
