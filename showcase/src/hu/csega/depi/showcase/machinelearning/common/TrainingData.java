package hu.csega.depi.showcase.machinelearning.common;

import java.util.Random;

public abstract class TrainingData {

	public void init() {
		Random rnd = new Random(System.currentTimeMillis());
		for(int i = 0; i < items.length; i++) {
			items[i] = new TrainingItem(0, 0, 0);
			initialize(items[i], rnd);
		}
	}

	public TrainingItem[] getItems() {
		return items;
	}

	protected abstract void initialize(TrainingItem item, Random rnd);

	private TrainingItem[] items = new TrainingItem[100];
}
