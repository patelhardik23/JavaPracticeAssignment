package ausAssignment.assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProcessFoodGUI {
	ProcessFoodGUI() {

		// String userName;
		JFrame f = new JFrame("Panel Example");

		JPanel panel1 = new JPanel();

		panel1.setLayout(new FlowLayout(FlowLayout.TRAILING));

		panel1.setBounds(0, 0, 600, 100);

		JTextField userNameField = new JTextField(45);
		userNameField.setEditable(true);
		JLabel UserName = new JLabel("User Name");
		UserName.setLabelFor(userNameField);

		String country[] = { "India", "Aus", "U.S.A", "England", "Newzealand" };
		JComboBox<String> cb1 = new JComboBox<String>(country);
		JLabel label2 = new JLabel("cereals");
		label2.setLabelFor(cb1);
		cb1.setBounds(10, 30, 10, 10);
		cb1.setSelectedItem(null);

		String state[] = { "Gujarat", "Rajasthan", "M.P.", "Mumbai", "JnK" };
		JComboBox<String> cb2 = new JComboBox<String>(state);
		JLabel label3 = new JLabel("beverages");
		label3.setLabelFor(cb2);
		cb2.setBounds(50, 30, 10, 10);
		cb2.setSelectedItem(null);

		panel1.add(UserName);
		panel1.add(userNameField);
		panel1.add(label2);

		panel1.add(cb1);

		panel1.add(label3);
		panel1.add(cb2);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.gray);
		panel2.setBounds(0, 100, 600, 400);

		JTextArea TA1 = new JTextArea("Type here...", 26, 53);
		// TA1.setBounds(10,210,600,200);
		panel2.add(TA1);

		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 500, 600, 100);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel pannel3Label = new JLabel("Command Buttons");
		pannel3Label.setLabelFor(panel3);

		JButton enterData = new JButton("Enter Data");

		ActionListener enterDataAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TA1.setText(null);
				// userNameField.setText(null);
				// userNameField.setEditable(false);
				String userName = userNameField.getText().trim();
				String cerealsValue = String.valueOf(cb1.getSelectedItem()).trim();
				String beveragesValue = String.valueOf(cb2.getSelectedItem()).trim();

				String errorMsg = "";
				boolean validation = false;

				if (userName.equals(null) || userName.equals("")) {
					errorMsg = "Please Enter User Name!!!";
					validation = false;
				} else if (cerealsValue.equals(null) || cerealsValue.equals("null")) {
					errorMsg = "Please select Cereals!!!";
					validation = false;
				} else if (beveragesValue.equals(null) || beveragesValue.equals("null")) {
					errorMsg = "Please Enter beverages!!!";
					validation = false;
				} else {
					validation = true;
					JOptionPane optionPane = new JOptionPane("Data Inserted Successfully!!", JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Conngratulation..");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}

				if (validation == false) {
					JOptionPane optionPane = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Data Validation");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}

				if (validation == true) {

					System.out.println("userName : " + userName + "::: Cereals :::" + cerealsValue + ":::Beverages :::"
							+ beveragesValue);
				}
			}
		};

		enterData.addActionListener(enterDataAction);

		JButton b2 = new JButton("Display Choices");

		JButton clearDisplay = new JButton("Clear Display");

		ActionListener clearAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TA1.setText(null);
				userNameField.setText(null);
				userNameField.setEditable(true);
				cb1.setSelectedItem(null);
				cb2.setSelectedItem(null);
			}
		};

		clearDisplay.addActionListener(clearAction);

		JButton quit = new JButton("Quit");

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};

		quit.addActionListener(al);

		panel3.add(pannel3Label);
		panel3.add(enterData);
		panel3.add(b2);
		panel3.add(clearDisplay);
		panel3.add(quit);

		f.add(panel1);
		f.add(panel2);
		f.add(panel3);

		f.setSize(600, 600);
		// f.setLayout(new FlowLayout(FlowLayout.RIGHT));
		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new ProcessFoodGUI();
		String fileName = "/home/hardikpatel/TEST/Assignment_1_data.csv";
		new DataFile(fileName);
	}
}