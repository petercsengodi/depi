package hu.csega.depi.showcase.patterns.strategy.impl;

public class SortingByRedness implements SortingStrategy {

	@Override
	public boolean needsSwitching(DataItem first, DataItem second) {
		return first.r > second.r;
	}

}
