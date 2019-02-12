package hu.csega.depi.showcase.codeevolution.e01builders;

public class CallExternalServiceV1 {

	public static void main(String[] args) {

		ExternalService service = new ExternalService(null, null, 0, null, null, null);
		service.doCall();

	}

}
