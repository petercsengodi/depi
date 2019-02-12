package hu.csega.depi.showcase.patterns.strategy.impl;

public interface SortingStrategy {

	boolean needsSwitching(DataItem first, DataItem second);

}
