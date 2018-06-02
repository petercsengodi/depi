package hu.csega.depi.showcase.codeevolution.e03staticFactories;

public class CallExternalServiceV1 {

	public static void main(String[] args) {

		CallExternalServiceV1 caller = new CallExternalServiceV1();
		caller.run();

	}

	private void run() {
		ExternalServiceFactoryV1.createExternalService().call(null);
	}

}
