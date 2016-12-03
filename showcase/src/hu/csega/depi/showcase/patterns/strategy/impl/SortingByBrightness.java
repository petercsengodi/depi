package hu.csega.depi.showcase.patterns.strategy.impl;

public class SortingByBrightness implements SortingStrategy {

	@Override
	public boolean needsSwitching(DataItem first, DataItem second) {
		int lightFirst = first.r + first.g + first.b;
		int lightSecond = second.r + second.g + second.b;
		return lightFirst > lightSecond;
	}

}
