package hu.csega.depi.showcase.patterns.strategy;

public interface Strategy {

	boolean needsSwitching(DataItem first, DataItem second);

}
