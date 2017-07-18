import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates a GUI where users can type in integers that they want to
 * multiply. This serves as a view in the MVC architecture.
 */
public class AppFrame {

	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton button1;
	private JLabel label1;
	private JLabel label2;
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;

	private Multiplier mul = new Multiplier(this);

	public void activate() {
		frame = new JFrame("Binary Multiplier");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		button1 = new JButton("Multiply");
		label1 = new JLabel("Enter two integers that you want to multiply.");
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
		frame.setSize(800, 200);
		frame.setVisible(true);
	}

	// Accessors

	public String getField1() {
		return field1.getText();
	}

	public String getField2() {
		return field2.getText();
	}

	// Modifiers

	public void setLabel2(String s) {
		label2.setText(s);
	}

	public void setField3(String s) {
		field3.setText(s);
	}

	class button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				mul.evaluate();
			} catch (Exception e) {
				label2.setText("");
				field3.setText("Error");
			}
		}
	}
}
