package hu.csega.depi.showcase.codeevolution.ex0process.v3;

import hu.csega.depi.showcase.codeevolution.ex0process.v3.Module;

public class ModuleImpl implements Module {

	public ModuleImpl(ModuleBuilder moduleBuilder) {
		this.printerFactory = moduleBuilder.getPrinterFactory();
	}

	@Override
	public void addNumbers(int a, int b) {
		Printer printer = printerFactory.createPrinter();
		String msg = String.valueOf((int)Math.pow(a, b));
		printer.print(msg);
	}

	private PrinterFactory printerFactory;
}
