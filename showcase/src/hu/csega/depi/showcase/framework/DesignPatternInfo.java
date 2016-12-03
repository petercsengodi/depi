package hu.csega.depi.showcase.framework;

public class DesignPatternInfo {

	public static DesignPatternInfo title(String title) {
		DesignPatternInfo ret = new DesignPatternInfo();
		ret.title = title;
		return ret;
	}

	public DesignPatternInfo resourceClass(Class<?> resourceClass) {
		this.resourceClass = resourceClass;
		return this;
	}

	public DesignPatternInfo resourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}

	public void show(ShowcaseWindow parent) {
		ShowcaseExplanation.showDesignPattern(parent, this);
	}

	private DesignPatternInfo() {
	}

	public String title;
	public Class<?> resourceClass;
	public String resourceName;

}
