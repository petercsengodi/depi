package hu.csega.depi.showcase.patterns.strategy.impl;

public class DataSorter {

	public DataSorter(DataItem[] items) {
		this.items = new DataItem[items.length];
		System.arraycopy(items, 0, this.items, 0, items.length);
	}

	/** True, if finished. */
	public boolean switchFirstItemsInIncorrectOrder() {
		if(strategy == null)
			return true;

		for(int i = 0; i < items.length - 1; i++) {
			if(strategy.needsSwitching(items[i], items[i+1])) {
				DataItem tmp = items[i];
				items[i] = items[i + 1];
				items[i + 1] = tmp;
				return false;
			}
		}

		return true;
	}

	public DataItem get(int index) {
		return items[index];
	}

	public int length() {
		return items.length;
	}

	public void setStrategy(SortingStrategy strategy) {
		this.strategy = strategy;
	}

	private SortingStrategy strategy;
	private DataItem[] items;
}
