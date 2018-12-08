package ausAssignment.assignment3;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI implements ActionListener {
	
	private static final Insets WEST_INSETS = new Insets(10, 0, 5, 15);
	private static final Insets EAST_INSETS = new Insets(10, 20, 5, 0);
	
	static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();
	static List<Nutrient> nutrientList = new ArrayList<Nutrient>();

	private JFrame f;
	private JPanel topPanel;
	private JTextField userNameTxt;
	private JLabel userNameLbl;
	private JLabel cerealsLbl;
	private JComboBox<String> cerealsComboBox;
	private JComboBox<String> beveragesComboBox;
	private JLabel beveragesLbl;

	private JPanel middlePanel;
	private JTextArea textArea;

	private JPanel bottomPanel;
	private JLabel commandLbl;
	private JButton enterDataBtn;
	private JButton displayChoiceBtn;
	private JButton clearDisplayBtn;
	private JButton quitBtn;

	public void intializeGUI() {
		f = new JFrame("Panel Example");

		setTopPanel();
		setMiddlePanel();
		setBottomPanel();

		f.add(topPanel);
		f.add(middlePanel);
		f.add(bottomPanel);

		f.setSize(500, 500);
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
	}

	private void setTopPanel() {
		topPanel = new JPanel();
		
		topPanel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		topPanel.setBorder(border);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		userNameLbl = new JLabel("User Name");
		gbc = createGbc(0, 0, 1);
		topPanel.add(userNameLbl,gbc);
		
		userNameTxt = new JTextField(10);
		gbc = createGbc(1, 0, 3);
		userNameTxt.setEditable(true);
		userNameLbl.setLabelFor(userNameTxt);
		topPanel.add(userNameTxt,gbc);
		
		cerealsLbl = new JLabel("cereals");
		gbc = createGbc(0, 1, 1);
		topPanel.add(cerealsLbl,gbc);
        
		String country[] = { "India", "Aus", "U.S.A", "England", "Newzealand" };

		cerealsComboBox = new JComboBox<String>(country);
		gbc = createGbc(1, 1, 1);
		cerealsLbl.setLabelFor(cerealsComboBox);
		cerealsComboBox.setSelectedItem(null);
		topPanel.add(cerealsComboBox,gbc);

		beveragesLbl = new JLabel("beverages");
		gbc = createGbc(2, 1, 1);
		topPanel.add(beveragesLbl,gbc);
		
		String state[] = { "Gujarat", "Rajasthan", "M.P.", "Mumbai", "JnK" };
		beveragesComboBox = new JComboBox<String>(state);
		gbc = createGbc(3, 1, 1);
		beveragesLbl.setLabelFor(beveragesComboBox);
		beveragesComboBox.setSelectedItem(null);
		topPanel.add(beveragesComboBox,gbc);

	}
	
	private GridBagConstraints createGbc(int x, int y, int gridwidth) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = 1;
		gbc.anchor = (x == 0 || x == 2) ? GridBagConstraints.EAST:GridBagConstraints.WEST ;
		gbc.fill = (x == 2 && y == 1)?GridBagConstraints.FIRST_LINE_START:GridBagConstraints.HORIZONTAL;
		gbc.insets = (x == 0 || x == 2) ? EAST_INSETS:WEST_INSETS;
		gbc.weightx = (x == 0) ? 0.1 : 1.0;
		gbc.weighty = (x == 0) ? 0.1 : 1.0;
		return gbc;
	}

	private void setMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		middlePanel.setBorder(border);
		String testData = "TestData TestData TestData TestData TestData TestData TestData TestData TestData TestData TestData TestData ";
		textArea = new JTextArea(testData, 20, 20);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane textAreaScroll = new JScrollPane(textArea);
		middlePanel.add(textAreaScroll);

	}

	private void setBottomPanel() {
		bottomPanel = new JPanel();

		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		bottomPanel.setBorder(border);

		commandLbl = new JLabel("Command Buttons");
		commandLbl.setLabelFor(bottomPanel);

		enterDataBtn = new JButton("Enter Data");

		displayChoiceBtn = new JButton("Display Choices");
		clearDisplayBtn = new JButton("Clear Display");
		quitBtn = new JButton("Quit");

		enterDataBtn.addActionListener(this::actionPerformed);
		clearDisplayBtn.addActionListener(this::actionPerformed);
		quitBtn.addActionListener(this::actionPerformed);

		bottomPanel.add(commandLbl);
		bottomPanel.add(enterDataBtn);
		bottomPanel.add(displayChoiceBtn);
		bottomPanel.add(clearDisplayBtn);
		bottomPanel.add(quitBtn);
	}

	private void enterDataBtnClickedAction() {

		String userName = userNameTxt.getText().trim();
		String cerealsValue = String.valueOf(cerealsComboBox.getSelectedItem()).trim();
		String beveragesValue = String.valueOf(beveragesComboBox.getSelectedItem()).trim();

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

			System.out.println(
					"userName : " + userName + "::: Cereals :::" + cerealsValue + ":::Beverages :::" + beveragesValue);
		}
	}

	ProcessFoodGUI() {
		// String userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quitBtn) {
			System.exit(0);
		} else if (e.getSource() == clearDisplayBtn) {
			textArea.setText(null);
			userNameTxt.setText(null);
			userNameTxt.setEditable(true);
			cerealsComboBox.setSelectedItem(null);
			beveragesComboBox.setSelectedItem(null);
		} else if (e.getSource() == enterDataBtn) {
			enterDataBtnClickedAction();
		} else if (e.getSource() == displayChoiceBtn) {

		}
	}

	public static void main(String args[]) {

		String fileName = "ausAssignment3_data.csv";
		new DataFile(fileName);

		System.out.println("list created");
		for (ProcessedFood pf : foodList) {
			System.out.println(pf);
			System.out.println("done");
		}
		for (Nutrient nl : nutrientList) {
			System.out.println(nl);
			System.out.println("doneok");
		}
		ProcessFoodGUI processFoodGUI = new ProcessFoodGUI();
		processFoodGUI.intializeGUI();

	}
}