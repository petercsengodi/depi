package hu.csega.depi.showcase.codeevolution.ex0process.v3;

public class ModuleBuilder {

	public static ModuleBuilder builder() {
		return new ModuleBuilder();
	}

	public Module build() {
		return new ModuleImpl(this);
	}

	public ModuleBuilder printerFactory(PrinterFactory printerFactory) {
		this.printerFactory = printerFactory;
		return this;
	}

	public PrinterFactory getPrinterFactory() {
		return printerFactory;
	}

	private PrinterFactory printerFactory;

}
