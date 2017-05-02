package hu.csega.depi.showcase.codeevolution.e04debugging;

public class DebugThisV2 {

	public static void main(String[] args) {

		DebugThisV2 instance = new DebugThisV2();
		instance.run();

	}

	private void run() {
		print(
			this.getClass()
				.getSimpleName() +
					": " +
						Integer.valueOf(4)
							.equals(6));
	}


	private void print(String line) {
		System.out.println(line);
	}
}
