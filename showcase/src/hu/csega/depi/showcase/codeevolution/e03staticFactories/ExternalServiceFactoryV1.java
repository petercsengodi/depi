package hu.csega.depi.showcase.codeevolution.e03staticFactories;

public class ExternalServiceFactoryV1 {

	public static ExternalService createExternalService() {
		return new ExternalServiceImpl();
	}

}
