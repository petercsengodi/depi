package hu.csega.depi.showcase.codeevolution.e03staticFactories;

public class ExternalServiceFactoryV2Impl implements ExternalServiceFactoryV2 {

	@Override
	public ExternalService createExternalService() {
		return new ExternalServiceImpl();
	}

}
