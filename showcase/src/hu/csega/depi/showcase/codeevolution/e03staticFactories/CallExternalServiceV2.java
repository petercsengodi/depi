package hu.csega.depi.showcase.codeevolution.e03staticFactories;

public class CallExternalServiceV2 {

	public static void main(String[] args) {

		ExternalServiceFactoryV2 factory = new ExternalServiceFactoryV2Impl();
		CallExternalServiceV2 caller = new CallExternalServiceV2(factory);
		caller.run();

	}

	private void run() {
		ExternalService service = factory.createExternalService();
		service.call(null);
	}

	public CallExternalServiceV2(ExternalServiceFactoryV2 factory) {
		this.factory = factory;
	}

	private ExternalServiceFactoryV2 factory;
}
