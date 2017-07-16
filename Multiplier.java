import java.util.ArrayList;

public class Multiplier {
	
	private AppFrame app;
	
	public Multiplier(AppFrame app) {
		this.app = app;
	}
	
	public void evaluate() {
		String input1 = app.getField1();
		String input2 = app.getField2();
		String binary1 = changeForm(convert(input1));
		String binary2 = changeForm(convert(input2));
		
		if (findNegative(input1) == true) {
			binary1 = "-" + binary1;
		}
		if (findNegative(input2) == true) {
			binary2 = "-" + binary2;
		}
		app.setLabel2(binary1 + "*" + binary2);

		String result;
		if (findNegative(input1) == findNegative(input2)) {
			result = changeForm(multiplication(convert(input1), convert(input2)));
		}
		// If the 2 integers have the different sign, display negative sign in front of
		// the result.
		else {
			result = "-" + changeForm(multiplication(convert(input1), convert(input2)));
		}
		app.setField3("=" + result);
	}
	
	// This converts the string representation of an integer to an arrayList of booleans.
	// @param String
	// @return ArrayList<Boolean>
	private ArrayList<Boolean> convert(String n) {
		int input = 0;
		// If the string contains negative sign, exclude the sign and convert the rest
		// of the string.
		if (findNegative(n) == true) {
			input = Integer.valueOf(n.substring(1)); // convert the string to integer
		} else {
			input = Integer.valueOf(n);
		}
		boolean digit = false;
		ArrayList<Boolean> list = new ArrayList<Boolean>();

		for (;;) {// the for loop to make the result ArrayList
			if (input % 2 == 1) {
				digit = true;// Binary 1 is converted into boolean true.
			} else {
				digit = false;
			}
			input = input / 2;
			list.add(digit);
			if (input < 2) {// the end of this loop
				if (input == 1) {
					list.add(true);// add another '1' to the end of the array
				}
				break;
			}
		}
		return list;
	}

	// This method multiplies 2 binaries stored in arrayLists.
	// @param ArrayList<Boolean> multiplicand, ArrayList<Boolean> multiplier
	// @return ArrayList<Boolean> product
	private ArrayList<Boolean> multiplication(ArrayList<Boolean> multiplicand, ArrayList<Boolean> multiplier) {
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		result.add(false);
		/**
		 * Multiply one digit of the multiplier by the multiplicand. If the digit is 0,
		 * do nothing. If the digit is 1, shift the multiplicand to the left by i
		 * characters, and add it to the previous sum.
		 **/
		for (int i = 0; i < multiplier.size(); i++) {
			if (multiplier.get(i) == true) {
				result = addition(result, multiplicand, i);
			}
		}
		return result;
	}

	// This method converts an ArrayList<Boolean> to a binary String
	// in the reversed order.
	// @param ArrayList<Boolean>
	// @return String
	private String changeForm(ArrayList<Boolean> n) {
		String result = "";
		for (int i = 0; i < n.size(); i++) {
			if (n.get(n.size() - i - 1) == true) {
				result = result + "1";
			} else {
				result = result + "0";
			}
		}
		return result;
	}

	// This method finds whether a string contains negative sign.
	// @param String
	// @return boolean
	private boolean findNegative(String n) {
		for (int i = 0; i < n.length(); i++) {
			if (n.charAt(i) == '-') {
				return true;
			}
		}
		return false;
	}

	// This method adds 2 binaries stored in arrayLists.
	// @param ArrayList<boolean> add1, ArrayList<Boolean> add2, int n: the number of
	// indentation
	// @return ArrayList<Boolean> result
	private ArrayList<Boolean> addition(ArrayList<Boolean> add1, ArrayList<Boolean> add2, int n) {
		int len1 = add1.size();
		int len2 = add2.size();

		boolean carry = false;
		boolean sum = false;
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		boolean d1 = false;
		boolean d2 = false;
		for (int i = 0; i < Math.max(len1, len2 + n); i++) {
			/**
			 * Look at each element of the ArrayList and add. This loop will stop when
			 * parameter 'i' reaches the longer length of two ArrayList.
			 **/
			if (i >= len1) {
				/**
				 * if the parameter 'i' gets bigger than the size of the ArrayList, set the
				 * boolean d1 to false, which is represented by 0.
				 **/
				d1 = false;
			} else {
				/**
				 * set the boolean d1 to the 'i+1'th digit of the first ArrayList
				 **/
				d1 = add1.get(i);
			}
			if (i < n) {
				/**
				 * For the first n times, set d2 = 0 in order to shift the arrayList ad2 n
				 * characters to the left.
				 **/
				d2 = false;
			} else if (i >= len2 + n) {
				/**
				 * if the parameter 'i' gets bigger than the length of the string, set the
				 * boolean d1 is 0 so that the addition process can continue.
				 **/
				d2 = false;
			} else {
				d2 = add2.get(i - n);
			}

			if (d1 == d2) {
				sum = carry;
				carry = d1;
			} else {
				sum = !carry;
			}
			result.add(sum);
			/**
			 * if the final carry is true, which is represented by 1, add 'true' to the end
			 * of the result ArrayList
			 **/
			if (i == Math.max(len1, len2) - 1 && carry == true) {
				result.add(carry);
			}
		}
		return result;
	}
}
