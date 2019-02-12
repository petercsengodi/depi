package hu.csega.depi.showcase.codeevolution.ex0process.v2;

import org.junit.After;
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
		Module module = ModuleBuilder.builder()
				.build();

		module.addNumbers(2, 5);
	}

}
