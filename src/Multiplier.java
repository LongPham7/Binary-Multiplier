import java.util.ArrayList;

/**
 * This class converts the two decimal integers given by users to binaries and
 * multiplies these using their binary representation. Their product is also
 * displayed in binary.
 * 
 * Here 'true' denotes '1' and 'false' denotes '0'.
 */
public class Multiplier {

	private AppFrame app;

	public Multiplier(AppFrame app) {
		this.app = app;
	}

	public void evaluate() {
		String input1 = app.getField1();
		String input2 = app.getField2();
		boolean isNegative1 = findNegative(input1);
		boolean isNegative2 = findNegative(input2);

		ArrayList<Boolean> absoluteValue1;
		ArrayList<Boolean> absoluteValue2;
		String binary1;
		String binary2;

		if (isNegative1) {
			absoluteValue1 = convertToBinary(input1.substring(1));
			binary1 = "-" + reverse(absoluteValue1);
		} else {
			absoluteValue1 = convertToBinary(input1);
			binary1 = reverse(absoluteValue1);
		}
		if (isNegative2) {
			absoluteValue2 = convertToBinary(input2.substring(1));
			binary2 = "-" + reverse(absoluteValue2);
		} else {
			absoluteValue2 = convertToBinary(input2);
			binary2 = reverse(absoluteValue2);
		}

		app.setLabel2(binary1 + "*" + binary2);

		String result;
		if (isNegative1 == isNegative2) {
			result = reverse(multiplication(absoluteValue1, absoluteValue2));
		} else {
			result = "-" + reverse(multiplication(absoluteValue1, absoluteValue2));
		}
		app.setField3("=" + result);
	}

	// Converts an input string of a non-negative decimal integer to an arrayList of
	// booleans.
	private ArrayList<Boolean> convertToBinary(String n) {
		int input = Integer.valueOf(n);
		boolean digit = false; // Boolean representing the current digit
		ArrayList<Boolean> list = new ArrayList<Boolean>();

		while (input != 0) {
			if (input % 2 == 1) {
				digit = true;
			} else {
				digit = false;
			}
			input = input / 2;
			list.add(digit);
		}
		return list;
	}

	// Multiplies two binaries stored in arrayLists. The result arrayList is
	// always non-empty.
	private ArrayList<Boolean> multiplication(ArrayList<Boolean> multiplicand, ArrayList<Boolean> multiplier) {
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		result.add(false);
		for (int i = 0; i != multiplier.size(); i++) {
			if (multiplier.get(i) == true) {
				result = addition(result, multiplicand, i);
			}
		}
		return result;
	}

	// Converts an ArrayList<Boolean> to a binary string in the reversed order.
	private String reverse(ArrayList<Boolean> n) {
		String result = "";
		for (int i = 0; i != n.size(); i++) {
			if (n.get(n.size() - i - 1) == true) {
				result = result + "1";
			} else {
				result = result + "0";
			}
		}
		return result;
	}

	// Figures out whether an input string contains a negative sign.
	private boolean findNegative(String n) {
		return (n != "" && n.charAt(0) == '-');
	}

	// Adds two binaries stored in arrayLists, shifting the second binary by n
	// digits.
	private ArrayList<Boolean> addition(ArrayList<Boolean> add1, ArrayList<Boolean> add2, int n) {
		int len1 = add1.size();
		int len2 = add2.size();

		boolean carryOut = false;
		boolean sum = false;
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		boolean d1 = false; // Boolean from the first binary
		boolean d2 = false; // Boolean from the second binary
		for (int i = 0; i != Math.max(len1, len2 + n); i++) {
			if (i >= len1) {
				d1 = false;
			} else {
				d1 = add1.get(i);
			}
			if (i < n || len2 + n < i) {
				d2 = false;
			} else {
				d2 = add2.get(i - n);
			}

			if (d1 == d2) {
				sum = carryOut;
				carryOut = d1;
			} else {
				sum = !carryOut;
			}
			result.add(sum);
		}
		if (carryOut == true) {
			result.add(true);
		}
		return result;
	}
}
