package hu.csega.depi.showcase.patterns.strategy.impl;

public class SortingByLength implements SortingStrategy {

	@Override
	public boolean needsSwitching(DataItem first, DataItem second) {
		return first.length > second.length;
	}

}
