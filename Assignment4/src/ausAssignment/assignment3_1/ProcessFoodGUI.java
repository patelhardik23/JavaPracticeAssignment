package ausAssignment.assignment3_1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI extends JFrame implements ActionListener {

	static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();

	private JPanel panel1JP;
	private JPanel panel2JP;
	private JPanel panel3JP;

	private JTextField nameJTF;
	private JTextArea userMessageJTA;

	private JComboBox<String> foodCerealsJCB;
	private JComboBox<String> foodBeveragesJCB;

	private JButton enterDataBTN;
	private JButton displayDataBTN;
	private JButton clearDataBTN;
	private JButton quitApplicationBTN;

	private Font font = new Font("", 0, 20);

	private Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
	String message = "";
	String nameOfUser = "";
	String selectedCereals = "";
	String selectedBeverages = "";

	ProcessFoodGUI() {

		/*--- Create Middle Panel -- START */

		panel1JP = new JPanel();
		panel1JP.setLayout(new BoxLayout(panel1JP, BoxLayout.Y_AXIS));
		panel1JP.setBorder(border);
		panel1JP.setBackground(Color.WHITE);

		JPanel userNamePannel = new JPanel();
		userNamePannel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		userNamePannel.setBackground(Color.white);

		JLabel nameJLBL = new JLabel();
		nameJLBL.setIcon(new ImageIcon(
				new ImageIcon("images/userNameImg.png").getImage().getScaledInstance(200, 20, Image.SCALE_SMOOTH)));

		nameJTF = new JTextField(83);
		nameJLBL.setLabelFor(nameJTF);

		userNamePannel.add(nameJLBL);
		userNamePannel.add(nameJTF);

		JPanel comboBoxPannel = new JPanel();
		comboBoxPannel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		comboBoxPannel.setBackground(Color.white);

		JLabel foodCerealsLBL = new JLabel();
		foodCerealsLBL.setIcon(new ImageIcon(
				new ImageIcon("images/cereals-logo.jpg").getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));

		foodCerealsJCB = new JComboBox<String>(fillDataInDropDownList(foodList, "cereals").toArray(new String[0]));
		foodCerealsLBL.setLabelFor(foodCerealsJCB);
		foodCerealsJCB.setSelectedIndex(-1);
		foodCerealsJCB.setPreferredSize(new Dimension(350, 25));
		foodCerealsJCB.setBackground(Color.white);

		JLabel foodBeveragesLBL = new JLabel();
		foodBeveragesLBL.setIcon(new ImageIcon(
				new ImageIcon("images/beverages-logo.png").getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));

		foodBeveragesJCB = new JComboBox<String>(fillDataInDropDownList(foodList, "beverage").toArray(new String[0]));
		foodBeveragesLBL.setLabelFor(foodBeveragesJCB);
		foodBeveragesJCB.setSelectedIndex(-1);
		foodBeveragesJCB.setPreferredSize(new Dimension(350, 25));
		foodBeveragesJCB.setBackground(Color.white);

		comboBoxPannel.add(foodCerealsLBL);
		comboBoxPannel.add(foodCerealsJCB);
		comboBoxPannel.add(foodBeveragesLBL);
		comboBoxPannel.add(foodBeveragesJCB);

		panel1JP.add(userNamePannel);
		panel1JP.add(comboBoxPannel);

		/*--- Create Top Panel -- END */

		/*--- Create Middle Panel -- START */

		panel2JP = new JPanel();
		panel2JP.setLayout(new BoxLayout(panel2JP, BoxLayout.X_AXIS));
		panel2JP.setBorder(border);
		panel2JP.setBackground(Color.WHITE);

		message = "\n   Processed Food Suitability Assessor(PFSA)\n\n\t"
				+ "# Place your order and press Enter Data Button \n\n\t"
				+ "# We are Happy to help. \n\n\t# Have a Grate Day!!!\n\n\n\n\n\n\n";

		userMessageJTA = new JTextArea();
		userMessageJTA.setEditable(false);
		userMessageJTA.setText(message);
		userMessageJTA.setFont(font);
		userMessageJTA.setMargin(new Insets(10, 10, 10, 10));
		userMessageJTA.setLineWrap(true);
		userMessageJTA.setWrapStyleWord(true);
		userMessageJTA.setBackground(new Color(0, 38, 77));
		userMessageJTA.setForeground(Color.white);

		panel2JP.add(userMessageJTA);

		/*--- Create Middle Panel -- END */

		/*--- Create Bottom Panel -- START */

		panel3JP = new JPanel();
		panel3JP.setLayout(new BoxLayout(panel3JP, BoxLayout.Y_AXIS));
		panel3JP.setBorder(border);

		JPanel btm1 = new JPanel();
		btm1.setBackground(Color.WHITE);

		JLabel commandButtonLBL = new JLabel(new ImageIcon(
				new ImageIcon("images/command.png").getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH)));

		btm1.add(commandButtonLBL);

		JPanel btm2 = new JPanel();
		btm2.setBackground(Color.WHITE);
		btm2.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

		enterDataBTN = new JButton(new ImageIcon(
				new ImageIcon("images/enterdata.png").getImage().getScaledInstance(160, 68, Image.SCALE_SMOOTH)));
		enterDataBTN.setBorder(null);

		displayDataBTN = new JButton(new ImageIcon(
				new ImageIcon("images/displaydata.png").getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
		displayDataBTN.setBorder(border);
		displayDataBTN.setEnabled(false);

		clearDataBTN = new JButton(new ImageIcon(
				new ImageIcon("images/clear.png").getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
		clearDataBTN.setBorder(border);

		quitApplicationBTN = new JButton(new ImageIcon(
				new ImageIcon("images/quit1.jpg").getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
		quitApplicationBTN.setBorder(border);

		enterDataBTN.addActionListener(this::actionPerformed);
		displayDataBTN.addActionListener(this::actionPerformed);
		clearDataBTN.addActionListener(this::actionPerformed);
		quitApplicationBTN.addActionListener(this::actionPerformed);

		btm2.add(enterDataBTN);
		btm2.add(displayDataBTN);
		btm2.add(clearDataBTN);
		btm2.add(quitApplicationBTN);

		panel3JP.add(btm1);
		panel3JP.add(btm2);
		/*--- Create Bottom Panel -- END */

		this.add(panel1JP);
		this.add(panel2JP);
		this.add(panel3JP);

		this.setSize(800, 600);
		this.setTitle("PFSA-Processed Food Suitability Assessor System");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setVisible(true);
		// this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

	/*
	 * Generate list for given category of food
	 */
	public static List<String> fillDataInDropDownList(List<ProcessedFood> foodList, String foodCategory) {
		List<String> listOfFoodName = new ArrayList<>();
		for (ProcessedFood processedFood : foodList) {
			if (processedFood.getCategory().equalsIgnoreCase(foodCategory)) {
				listOfFoodName.add(processedFood.getName());
			}
		}
		return listOfFoodName;
	}

	// Action listener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quitApplicationBTN) {
			quitApplicationBtnAction();
		} else if (e.getSource() == clearDataBTN) {
			clearDataBtnAction();
		} else if (e.getSource() == enterDataBTN) {
			enterDataBtnAction();
		} else if (e.getSource() == displayDataBTN) {
			displayDataBtnClickedAction();
		}
	}

	// Exit from application by clicking quit button
	private void quitApplicationBtnAction() {
		System.exit(0);
	}

// Clear display by clicking clear data button
	private void clearDataBtnAction() {
		nameOfUser = null;
		selectedCereals = null;
		selectedBeverages = null;

		nameJTF.setText(null);
		foodCerealsJCB.setSelectedIndex(-1);
		foodBeveragesJCB.setSelectedIndex(-1);
		displayDataBTN.setEnabled(false);

		/* Reset Middle Panel -- START */
		panel2JP.removeAll();
		panel2JP.setLayout(new BoxLayout(panel2JP, BoxLayout.X_AXIS));
		panel2JP.setBorder(border);
		panel2JP.setBackground(Color.WHITE);

		message = "\n   Processed Food Suitability Assessor(PFSA)\n\n\t"
				+ "# Place your order and press Enter Data Button \n\n\t"
				+ "# We are Happy to help. \n\n\t# Have a Grate Day!!!\n\n\n\n\n\n\n";

		userMessageJTA = new JTextArea();
		userMessageJTA.setEditable(false);
		userMessageJTA.setText(message);
		userMessageJTA.setFont(font);
		userMessageJTA.setMargin(new Insets(10, 10, 10, 10));
		userMessageJTA.setLineWrap(true);
		userMessageJTA.setWrapStyleWord(true);
		userMessageJTA.setBackground(new Color(0, 38, 77));
		userMessageJTA.setForeground(Color.white);

		panel2JP.add(userMessageJTA);
		panel2JP.revalidate();
		panel2JP.repaint();
		/* Reset Middle Panel -- END */

	}

	private ProcessedFood getSelectedProcessedFood(List<ProcessedFood> foodList, String foodName, String foodCategory) {
		for (ProcessedFood foodSelected : foodList) {
			if (foodSelected.getName().equalsIgnoreCase(foodName)
					&& foodSelected.getCategory().equalsIgnoreCase(foodCategory)) {
				return foodSelected;
			}
		}
		return null;
	}

	// get user input and validate it
	private void enterDataBtnAction() {
		String errorMsg = "";

		nameOfUser = nameJTF.getText().trim();

		if (nameOfUser.equals(null) || nameOfUser.equals("")) {
			errorMsg = "Enter Your Name";
			displayErrorDialogBox(errorMsg);
			return;
		}
		if (foodCerealsJCB.getSelectedIndex() == -1) {
			errorMsg = "Select Cereals";
			displayErrorDialogBox(errorMsg);
			return;
		}
		if (foodBeveragesJCB.getSelectedIndex() == -1) {
			errorMsg = "Select Beverages";
			displayErrorDialogBox(errorMsg);
			return;
		}

		selectedCereals = String.valueOf(foodCerealsJCB.getSelectedItem());
		selectedBeverages = String.valueOf(foodBeveragesJCB.getSelectedItem());

		// validate data input by user

		message = "Hey  " + nameOfUser + "!!!\n\n" + "\tYou Ordered\n\n" + "\t# Cereals :  " + selectedCereals + "\n\n"
				+ "\t# Beverages :  " + selectedBeverages + "\n\n" + "\t# Review your Order. \n\n"
				+ "\t# Press 'Display Choices' button to view Nutrients value of ordered food. \n\n"
				+ "\t# Happy Eating.";

		displayDataBTN.setEnabled(true);
		userMessageJTA.setText(message);

	}

	// error message
	public void displayErrorDialogBox(String errorMsg) {

		JOptionPane notificationBox = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
		JDialog dialogBox = notificationBox.createDialog("Food Assessor System");
		dialogBox.setAlwaysOnTop(true);
		dialogBox.setVisible(true);
	}

//Display data selected by user
	private void displayDataBtnClickedAction() {

		panel2JP.removeAll();

		String columnName[] = { "Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium" };

		ProcessedFood processedFoodCereals = getSelectedProcessedFood(foodList, selectedCereals, "cereals");
		ProcessedFood processedFoodBeverage = getSelectedProcessedFood(foodList, selectedBeverages, "beverage");

		String rowData[][] = { createTableRowData(processedFoodCereals).toArray(new String[0]),
				createTableRowData(processedFoodBeverage).toArray(new String[0]),
				createTotalRowData(processedFoodCereals, processedFoodBeverage).toArray(new String[0]) };

		JTable displayDataJTBL = new JTable(rowData, columnName);

		displayDataJTBL.setRowHeight(25);
		displayDataJTBL.setBackground(new Color(0, 38, 77));
		displayDataJTBL.setForeground(Color.white);
		displayDataJTBL.getColumnModel().getColumn(0).setPreferredWidth(78);
		displayDataJTBL.getColumnModel().getColumn(1).setPreferredWidth(350);
		displayDataJTBL.getColumnModel().getColumn(2).setPreferredWidth(95);
		displayDataJTBL.getColumnModel().getColumn(3).setPreferredWidth(75);
		displayDataJTBL.getColumnModel().getColumn(4).setPreferredWidth(45);
		displayDataJTBL.getColumnModel().getColumn(6).setPreferredWidth(60);
		displayDataJTBL.getColumnModel().getColumn(7).setPreferredWidth(40);
		displayDataJTBL.getColumnModel().getColumn(8).setPreferredWidth(98);
		displayDataJTBL.getColumnModel().getColumn(9).setPreferredWidth(55);
		displayDataJTBL.getColumnModel().getColumn(10).setPreferredWidth(92);
		displayDataJTBL.getColumnModel().getColumn(11).setPreferredWidth(60);

		JScrollPane displayJSP = new JScrollPane(displayDataJTBL);
		displayJSP.getViewport().setBackground(new Color(0, 38, 77));

		panel2JP.add(displayJSP);
		panel2JP.revalidate();
		panel2JP.repaint();
	}

	// get data of the given food items
	private List<String> createTableRowData(ProcessedFood pf) {

		List<String> data = new ArrayList<>();

		data.add(pf.getCategory());
		data.add(pf.getName());
		data.add(pf.getBrandName());
		data.add(pf.getServeSize().toString());
		data.add(pf.getUnit());
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.ENERGY)));
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.PROTEIN)));
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.FAT)));
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.CARBOHYDRATE)));
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.SUGAR)));
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.DIETARY_FIBER)));
		data.add(String.format("%.2f", pf.getSingleNutrientFromList(pf.getNutrient(), DataFile.SODIUM)));
		return data;
	}

	private List<String> createTotalRowData(ProcessedFood processedFoodCereals, ProcessedFood processedFoodBeverages) {
		List<String> dataList = new ArrayList<>();
		dataList.add("Total");
		dataList.add("");
		dataList.add("");
		dataList.add("");
		dataList.add("");
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.ENERGY)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.ENERGY)));
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.PROTEIN)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.PROTEIN)));
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.FAT)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.FAT)));
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(),
						DataFile.CARBOHYDRATE)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.CARBOHYDRATE)));
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.SUGAR)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.SUGAR)));
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(),
						DataFile.DIETARY_FIBER)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.DIETARY_FIBER)));
		dataList.add(String.format("%.2f",
				processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.SODIUM)
						+ processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(),
								DataFile.SODIUM)));

		return dataList;
	}

	// main method
	public static void main(String args[]) {

		String dataFileName = "processedFoodData.csv";
		new DataFile(dataFileName, foodList);
		new ProcessFoodGUI();
	}
}
