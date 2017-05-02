package hu.csega.depi.showcase.codeevolution.e01builders;

public class CallExternalServiceV2 {

	public static void main(String[] args) {

		ExternalService service = ExternalService.builder()
				.host(null)
				.path(null)
				.port(0)
				.databaseName(null)
				.userName(null)
				.password(null)
				.build();

		service.doCall();

	}

}
