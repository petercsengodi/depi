package hu.csega.depi.showcase.codeevolution.e04debugging;

public class DebugThisV3 {

	public static void main(String[] args) {

		DebugThisV3 instance = new DebugThisV3();
		instance.run();

	}

	private void run() {
		String msg = this.getClass().getSimpleName() + ": " + Integer.valueOf(4).equals(6);
		print(msg);
	}


	private void print(String line) {
		System.out.println(line);
	}
}
