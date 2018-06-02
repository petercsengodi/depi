package hu.csega.depi.showcase.machinelearning.common;

import java.util.Iterator;

public class TrainingData implements Iterable<TrainingItem> {

	public class It implements Iterator<TrainingItem> {

		@Override
		public boolean hasNext() {
			return pos < count;
		}

		@Override
		public TrainingItem next() {
			return items[pos++];
		}

		@Override
		public void remove() {
		}

		private int pos = 0;
	}

	public Iterator<TrainingItem> iterator() {
		return new It();
	}

	public void addItem(TrainingItem item) {
		items[count++] = item;
	}

	public TrainingItem[] getItems() {
		return items;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increaseCounter() {
		if(count < items.length)
			count++;
	}

	public void clear() {
		count = 0;
	}

	public void init() {}

	protected TrainingItem[] items = new TrainingItem[100];
	protected int count;
}
