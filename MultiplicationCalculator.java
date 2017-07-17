/**
 * This class creates a GUI where users can submit two integers.
 * After they click a button, the program converts the two given numbers to
 * binaries and returns their product in binary.
 */

public class MultiplicationCalculator {

	private AppFrame app = new AppFrame();

	public static void main(String[] args) {
		MultiplicationCalculator gui = new MultiplicationCalculator();
		gui.activate();
	}

	public void activate() {
		app.activate();
	}

}
