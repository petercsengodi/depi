package hu.csega.depi.showcase.codeevolution.ex0process.v3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModuleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		final Printer dummyPrinter = new Printer() {

			@Override
			public void print(String s) {
				resultMsg = s;
			}
		};

		PrinterFactory dummyFactory = new PrinterFactory() {

			@Override
			public Printer createPrinter() {
				return dummyPrinter;
			}
		};

		Module module = ModuleBuilder.builder()
				.printerFactory(dummyFactory)
				.build();

		module.addNumbers(2, 5);

		Assert.assertEquals("7", resultMsg);
	}

	public static String resultMsg;

}
