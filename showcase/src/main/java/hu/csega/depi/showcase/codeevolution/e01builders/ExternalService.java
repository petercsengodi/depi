package hu.csega.depi.showcase.codeevolution.e01builders;

public class ExternalService {

	public ExternalService(String arg0, String arg1, int arg2, String arg3, String arg4, String arg5) {
		// initialization
	}

	private ExternalService(Builder builder) {
		// initialization
	}

	public Object doCall() {
		// call itself
		return null;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		public Builder host(String arg0) {
			this.host = arg0;
			return this;
		}

		public Builder path(String arg0) {
			this.path = arg0;
			return this;
		}

		public Builder port(int arg0) {
			this.port = arg0;
			return this;
		}

		public Builder databaseName(String arg0) {
			this.databaseName = arg0;
			return this;
		}

		public Builder userName(String arg0) {
			this.userName = arg0;
			return this;
		}

		public Builder password(String arg0) {
			this.password = arg0;
			return this;
		}

		public ExternalService build() {
			return new ExternalService(this);
		}

		String host;
		String path;
		int port;
		String databaseName;
		String userName;
		String password;
	}

}
