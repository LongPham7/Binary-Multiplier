/**
 * This class lets us submit two non-negative integers; it converts them into binaries
 * and returns their product in binary.
 * 
 * In this program, '1' is represented by 'true' and '0' is represented by 'false'.
 */

public class MultiplicationCalculator {
	
	private AppFrame app = new AppFrame();

	// This is the main method that starts execution.
	public static void main(String[] args) {
		MultiplicationCalculator gui = new MultiplicationCalculator();
		gui.activate();
	}

	// This method adds widgets to a window.
	public void activate() {
		app.activate();
	}

}
