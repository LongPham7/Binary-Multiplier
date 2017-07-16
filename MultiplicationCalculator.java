/**
 * Title:                         Multiplication Calculator
 *                                                                @author Pham.LongThanh
 *                                                              Date: September 23, 2013
 * 
 * This class lets us submit 2 non-negative integers; it converts them into binaries
 * and returns their product in binary.
 * 
 * In this program, '1' is represented by 'true' and '0' is represented by 'false'.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MultiplicationCalculator 
{
	JFrame frame;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JButton button1;
	JLabel label1;
	JLabel label2;
	JTextField field1;
	JTextField field2;
	JTextField field3;

	// This is the main method that starts execution.
	public static void main(String[] args) 
	{
		MultiplicationCalculator gui = new MultiplicationCalculator();
		gui.go();
	}

	// This method adds widgets to a window.
	public void go() 
	{
		frame = new JFrame("Binary Multiplication");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		button1 = new JButton("Multiply");
		label1 = new JLabel(
				"Please enter 2 non-negative integers you want to multiply.");
		label2 = new JLabel("");
		field1 = new JTextField(20);
		field2 = new JTextField(20);
		field3 = new JTextField(50);

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);
		frame.getContentPane().add(BorderLayout.SOUTH, panel3);

		panel1.add(BorderLayout.NORTH, label1);
		panel1.add(BorderLayout.EAST, field1);
		panel1.add(BorderLayout.WEST, field2);
		panel2.add(BorderLayout.CENTER, button1);
		panel3.add(BorderLayout.NORTH, label2);
		panel3.add(BorderLayout.CENTER, field3);

		button1.addActionListener(new button1Listener());

		field3.setText("Result:");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 300);
		frame.setVisible(true);

	}
	
	class button1Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				String binary1 = changeForm(convert(field1.getText()));
				String binary2 = changeForm(convert(field2.getText()));
				if(findNegative(field1.getText())==true)
				{
					binary1 = "-"+binary1;
				}
				if(findNegative(field2.getText())==true)
				{
					binary2 = "-"+binary2;
				}
				label2.setText(binary1+"*"+binary2);
				
				String result;
				if(findNegative(field1.getText()) == findNegative(field2.getText()))
				{
				    result = changeForm(multiplication(convert(field1.getText()), convert(field2.getText())));
				}
				//If the 2 integers have the different sign, display negative sign in front of the result.
				else
				{
					result = "-"+changeForm(multiplication(convert(field1.getText()), convert(field2.getText())));
					
				}
				field3.setText("="+result);
			}
			catch (Exception e)
			{
				label2.setText("");
				field3.setText("Error");
			}
		}
	}
	
	// This converts a string of integer to an arrayList of binary.
	// @param String
	// @return ArrayList<Boolean>
	public ArrayList<Boolean> convert(String n) 
	{
		int input = 0;
		//If the string contains negative sign, exclude the sign and convert the rest of the string.
		if(findNegative(n)==true)
		{
		    input = Integer.valueOf(n.substring(1)); // convert the string to integer
		}
		else
		{
			input = Integer.valueOf(n);
		}
		boolean digit = false;
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		
		for (;;) 
		{// the for loop to make the result ArrayList
			if (input % 2==1)
			{
			    digit = true;//Binary 1 is converted into boolean true. 
			}
			else
			{
				digit = false;
			}
			input = input / 2;
			list.add(digit);
			if (input < 2) 
			{// the end of this loop
				if (input == 1) 
				{
					list.add(true);// add another '1' to the end of the array
				}
				break;// stop the loop
			}
		}
		return list;
	}
	
	//This method multiplies 2 binaries stored in arrayLists.
	//@param ArrayList<Boolean> multiplicand, ArrayList<Boolean> multiplier
	//@return ArrayList<Boolean> product
	public ArrayList<Boolean> multiplication(ArrayList<Boolean> multiplicand, ArrayList<Boolean> multiplier)
	{
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		result.add(false);
		/**
		Multiply one digit of the multiplier by the multiplicand.
		If the digit is 0, do nothing.
		If the digit is 1, shift the multiplicand to the left by i characters,
		and add it to the previous sum. 
		**/
		for (int i=0; i<multiplier.size();i++)
		{
			if(multiplier.get(i)==true) 
			{
				result = addition(result, multiplicand, i);
			}
		}
		
		return result;
	}
	
	//This method converts an ArrayList<Boolean> to a binary String 
	//in the reversed order.
	//@param ArrayList<Boolean>
	//@return String
	public String changeForm(ArrayList<Boolean> n)
	{
		String result = "";
		for (int i = 0; i<n.size(); i++)
		{
			if (n.get(n.size()-i-1)==true)
			{
				result = result+"1";
			}
			else
			{
				result = result+"0";
			}
		}
		return result;
	}
		
	// This method finds whether a string contains negative sign.
	// @param String
	// @return boolean
	public boolean findNegative(String n) 
	{
		for (int i = 0; i < n.length(); i++) 
		{
			if (n.charAt(i) == '-') 
			{
				return true;
			}
		}
		return false;
	}
	
	//This method adds 2 binaries stored in arrayLists.
    //@param ArrayList<boolean> add1, ArrayList<Boolean> add2, int n: the number of indentation
	//@return ArrayList<Boolean> result
	public ArrayList<Boolean> addition(ArrayList<Boolean> add1, ArrayList<Boolean> add2, int n) 
	{
		int len1 = add1.size();
		int len2 = add2.size();

		boolean carry = false;
		boolean sum = false;
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		boolean d1 = false;
		boolean d2 = false;
		for (int i = 0; i < Math.max(len1, len2+n); i++) 
		{
			/** 
			Look at each element of the ArrayList and add. This loop will
			stop when
			parameter 'i' reaches the longer length of two ArrayList.
			**/
			if (i >= len1) 
			{
			    /** 
			    if the parameter 'i' gets bigger than the size
			    of the ArrayList,
			    set the boolean d1 to false, which is represented by 0.
			    **/
				d1 = false;
			} 
			else 
			{
				/**
				set the boolean d1 to the 'i+1'th digit of the first
				ArrayList
	     		**/
				d1 = add1.get(i);
			}
			if (i<n)
			{
				/**
				For the first n times, set d2 = 0 in order to 
				shift the arrayList ad2 n characters to the left.
				**/
				d2 = false;
			}
			else if (i >=len2+n) 
			{
				/**
				if the parameter 'i' gets bigger than the length of the
				string,
				set the boolean d1 is 0 so that the addition process can
				continue.
				**/
				d2 = false;
			} 
			else 
			{
				d2 = add2.get(i-n);
			}

			if (d1 == d2) 
			{
				sum = carry;
				carry = d1;
			} 
			else 
			{
				sum = !carry;
			}
			result.add(sum);
				/**
				if the final carry is true, which is represented by 1, add 'true' to the end of the result
			    ArrayList
				**/
			if (i == Math.max(len1, len2) - 1 && carry == true) 
			{
			result.add(carry);
			}

		}
		return result;
	}
}
