package hu.csega.depi.showcase.machinelearning.common;

public class Sigmoid {

	public static double of(double x) {
		if(x < 0) {
			return 1.0 - of(-x);
		}

		if(x >= 10) {
			return 1.0;
		}

		int i = indexOf(x);
		return table[i];
	}

	private static double y(double x) {
		return 1.0 / (1.0 + Math.exp(-x));
	}

	private static double[] table = new double[1000];

	private static int indexOf(double x) {
		return ((int)(x * 100));
	}

	static {
		int i;
		for(double x = 0; x < 10; x += 0.01) {
			i = indexOf(x);
			table[i] = y(x);
		}
	}

	public static void main(String[] args) throws Exception {
		print(0.0);
		print(0.01);
		print(0.1);
		print(1);
		print(5.5);
		print(9.9999);
		print(-5.5);
	}

	public static void print(double x) {
		System.out.println("x: " + x + " index: " + indexOf(x) + " estimated: " + of(x) + " real: " + y(x));
	}

}
