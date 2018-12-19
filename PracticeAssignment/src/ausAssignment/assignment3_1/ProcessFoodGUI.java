package ausAssignment.assignment3_1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI extends JFrame implements ActionListener {

	static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();
	static List<ProcessedFood> selectedFoodList = new ArrayList<ProcessedFood>();

	private JPanel topPanel;
	private JTextField userNameTxt;
	private JLabel userNameLbl;
	private JLabel cerealsLbl;
	private JComboBox<String> cerealsComboBox;
	private JComboBox<String> beveragesComboBox;
	private JLabel beveragesLbl;

	private JPanel middlePanel;
	private JTextArea textAreaForMsg;
	private JScrollPane displayDataScroll;

	private JPanel bottomPanel;
	private JLabel commandLbl;
	private JButton enterDataBtn;
	private JButton displayChoiceBtn;
	private JButton clearDisplayBtn;
	private JButton quitBtn;
	private JTable dataTable;
	private Font textFont = new Font("", 0, 15);
	String userMessage = "";
	String welcomeMessage = "";
	String userName = "";
	String cerealsValue = "";
	String beveragesValue = "";

	/*
	 * default constructor
	 */
	ProcessFoodGUI() {
		intializeGUI();
	}

	/*
	 * Load all component in frame and set constraints on frame
	 */
	public void intializeGUI() {

		topPanel = new JPanel();
		middlePanel = new JPanel();
		bottomPanel = new JPanel();

		setTopPanel();
		setMiddlePanel();
		setBottomPanel();

		this.add(topPanel);
		this.add(middlePanel);
		this.add(bottomPanel);

		this.setSize(800, 500);
		this.setName("Processed Food Assessor System");
		this.setTitle("Processed Food Assessor System");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

	/*
	 * Set all component of top pannel.
	 */
	private void setTopPanel() {

		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		topPanel.setBorder(border);
		topPanel.setBackground(Color.WHITE);

		JPanel userNamePannel = new JPanel();

		userNamePannel.setBackground(Color.white);
		userNameLbl = new JLabel();
		userNameLbl.setIcon(new ImageIcon(new ImageIcon("images/userNameImg.png")
				.getImage().getScaledInstance(200, 20, Image.SCALE_SMOOTH)));
		userNamePannel.add(userNameLbl);
		userNamePannel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		userNameTxt = new JTextField(83);
		userNameLbl.setLabelFor(userNameTxt);
		userNamePannel.add(userNameTxt);

		topPanel.add(userNamePannel);

		JPanel comboBoxPannel = new JPanel();
		comboBoxPannel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		comboBoxPannel.setBackground(Color.white);
		cerealsLbl = new JLabel();//
		cerealsLbl.setIcon(new ImageIcon(new ImageIcon("images/cereals-logo.jpg")
				.getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));

		comboBoxPannel.add(cerealsLbl);

		cerealsComboBox = new JComboBox<String>(fillComboBox(foodList, "cereals").toArray(new String[0]));
		cerealsLbl.setLabelFor(cerealsComboBox);
		cerealsComboBox.setSelectedIndex(-1);
		cerealsComboBox.setPreferredSize(new Dimension(350, 25));
		cerealsComboBox.setBackground(Color.white);
		comboBoxPannel.add(cerealsComboBox);

		beveragesLbl = new JLabel();
		beveragesLbl.setIcon(new ImageIcon(new ImageIcon("images/beverages-logo.png")
				.getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));

		comboBoxPannel.add(beveragesLbl);

		beveragesComboBox = new JComboBox<String>(fillComboBox(foodList, "beverage").toArray(new String[0]));
		beveragesLbl.setLabelFor(beveragesComboBox);
		beveragesComboBox.setSelectedIndex(-1);
		beveragesComboBox.setPreferredSize(new Dimension(350, 25));
		beveragesComboBox.setBackground(Color.white);
		comboBoxPannel.add(beveragesComboBox);
		topPanel.add(comboBoxPannel);
	}

	/*
	 * Generate list for given category of food
	 */
	public static List<String> fillComboBox(List<ProcessedFood> processedFoodList, String category) {
		List<String> foodNameList = new ArrayList<>();

		for (ProcessedFood processedFood : processedFoodList) {
			if (processedFood.getCategory().equalsIgnoreCase(category)) {
				foodNameList.add(processedFood.getName());
			}
		}
		return foodNameList;
	}

	/*
	 * Set all components and user guidelines to use application
	 */
	private void setMiddlePanel() {
		middlePanel.removeAll();
		userMessage = "Hello User!!!\n\n" + "Welcome to Processed Food Assessor System\n\n"
				+ "Please follow below mentione steps.\n" + "1. Enter your name.\n"
				+ "2. Select cereals and Beverages of you choice.\n"
				+ "3. Click the 'Enter Data' Button to enter you choice.\n\n" + "Thank you.";

		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		middlePanel.setBorder(border);
		middlePanel.setBackground(Color.WHITE);

		textAreaForMsg = new MyTextArea(20, 20);
		textAreaForMsg.setEditable(false);
		textAreaForMsg.setText(userMessage);
		textAreaForMsg.setFont(textFont);
		textAreaForMsg.setMargin(new Insets(10, 10, 10, 10));
		textAreaForMsg.setLineWrap(true);
		textAreaForMsg.setWrapStyleWord(true);
		textAreaForMsg.setBackground(new Color(1, 1, 1, (float) 0.01));
		textAreaForMsg.setForeground(Color.white);
		middlePanel.add(textAreaForMsg);
		middlePanel.revalidate();
		middlePanel.repaint();
	}

	public class MyTextArea extends JTextArea {

		private Image img;

		public MyTextArea(int a, int b) {
			super(a, b);
			try {
				img = ImageIO.read(new File("images/welcomepage.jpg"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, 1200, 400, null);
			super.paintComponent(g);
		}
	}

	/*
	 * Set all components of bottom panel along with action listeners
	 */
	private void setBottomPanel() {

		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		bottomPanel.setBorder(border);

		JPanel btm1 = new JPanel();
		btm1.setBackground(Color.WHITE);
		commandLbl = new JLabel(new ImageIcon(new ImageIcon("images/command.png")
				.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
		btm1.add(commandLbl);

		JPanel btm2 = new JPanel();
		btm2.setBackground(Color.WHITE);
		btm2.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		enterDataBtn = new JButton(new ImageIcon(new ImageIcon("images/enterdata.png")
				.getImage().getScaledInstance(160, 68, Image.SCALE_SMOOTH)));
		enterDataBtn.setBorder(null);
		displayChoiceBtn = new JButton(
				new ImageIcon(new ImageIcon("images/displaydata.png").getImage()
						.getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
		displayChoiceBtn.setBorder(border);
		displayChoiceBtn.setEnabled(false);
		clearDisplayBtn = new JButton(new ImageIcon(new ImageIcon("images/clear.png")
				.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));

		clearDisplayBtn.setBorder(border);
		quitBtn = new JButton(new ImageIcon(new ImageIcon("images/quit1.jpg")
				.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
		quitBtn.setBorder(border);
		enterDataBtn.addActionListener(this::actionPerformed);
		displayChoiceBtn.addActionListener(this::actionPerformed);
		clearDisplayBtn.addActionListener(this::actionPerformed);
		quitBtn.addActionListener(this::actionPerformed);

		btm2.add(enterDataBtn);
		btm2.add(displayChoiceBtn);
		btm2.add(clearDisplayBtn);
		btm2.add(quitBtn);

		bottomPanel.add(btm1);
		bottomPanel.add(btm2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * List of action to be performed based on source of action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitBtn) {

			quitButton();

		} else if (e.getSource() == clearDisplayBtn) {

			clearDisplayBtnFun();

		} else if (e.getSource() == enterDataBtn) {

			enterDataBtnClickedAction();

		} else if (e.getSource() == displayChoiceBtn) {

			enterDataBtnClickedAction();
			displayChoiceBtnClickedAction();

		}
	}

	/*
	 * This function close the application
	 */
	private void quitButton() {
		// TODO Auto-generated method stub
		System.exit(0);

	}

	/*
	 * This function will clear all the data from middle panel and set to same when
	 * application started. Combo boxes cereals and beverages will still have data
	 * loaded.
	 */
	private void clearDisplayBtnFun() {
		userName = null;
		cerealsValue = null;
		beveragesValue = null;

		userNameTxt.setText(null);
		cerealsComboBox.setSelectedIndex(-1);
		beveragesComboBox.setSelectedIndex(-1);
		displayChoiceBtn.setEnabled(false);

		setMiddlePanel();

	}

	private ProcessedFood getSelectedProcessedFood(List<ProcessedFood> processedFoodList, String itemName,
			String category) {
		for (ProcessedFood processedFood : processedFoodList) {
			if (processedFood.getName().equalsIgnoreCase(itemName)
					&& processedFood.getCategory().equalsIgnoreCase(category)) {
				return processedFood;
			}
		}
		return null;
	}

	/*
	 * list of events to be done when "Enter Data" button is selected gets user name
	 * and choices of different food category cereals and beverages and display
	 * message to user accordingly.
	 */
	private void enterDataBtnClickedAction() {

		selectedFoodList.clear();

		userName = userNameTxt.getText().trim();
		cerealsValue = String.valueOf(cerealsComboBox.getSelectedItem());
		beveragesValue = String.valueOf(beveragesComboBox.getSelectedItem());

		String errorMsg = "";

		// validate data input by user

		ProcessedFood selectedIteam = null;

		if (userName.length() > 0 && cerealsValue != "null" && beveragesValue != "null") {
			userMessage = "Hello  " + userName + "\n\n" + "Welcome to Processed Food Assessor System\n\n"
					+ "You have selected '" + cerealsValue + "' in cereals and '" + beveragesValue
					+ "' in beverages.\n\n" + "Click the 'Display Choices' button to view details of your choices.\n\n"
					+ "Thank you.";

			selectedIteam = getSelectedProcessedFood(foodList, cerealsValue, "cereals");
			selectedFoodList.add(selectedIteam);

			selectedIteam = getSelectedProcessedFood(foodList, beveragesValue, "beverage");
			selectedFoodList.add(selectedIteam);

			System.out.println(
					"userName : " + userName + "::: Cereals :::" + cerealsValue + ":::Beverages :::" + beveragesValue);
			displayChoiceBtn.setEnabled(true);
		} else {

			if (userName.equals(null) || userName.equals("")) {
				errorMsg = "Please Enter User Name!!!";
				errorMessage(errorMsg);
			} else if (cerealsComboBox.getSelectedIndex() == -1) {
				errorMsg = "Please select Cereals!!!";
				errorMessage(errorMsg);
			} else if (beveragesComboBox.getSelectedIndex() == -1) {
				errorMsg = "Please Enter beverages!!!";
				errorMessage(errorMsg);
			}
		}
		textAreaForMsg.setText(userMessage);

	}

	/*
	 * generate error dialog box according to validation failure
	 */
	public void errorMessage(String errorMsg) {

		JOptionPane optionPane = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Data Validation");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	/*
	 * gets all the details given by user and fetch information accordingly and
	 * display it in user readable format. here we have use tabular format.
	 */
	private void displayChoiceBtnClickedAction() {

		middlePanel.removeAll();

		String dataHeader[] = { "Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium" };

		// Allow to create dynamic table

		DefaultTableModel tableModel = new DefaultTableModel(dataHeader, 0);

		dataTable = new JTable(tableModel);

		tableModel.addRow(convertProcessedFoodToTableRow(getSelectedProcessedFood(foodList, cerealsValue, "cereals"))
				.toArray(new String[0]));
		tableModel.addRow(convertProcessedFoodToTableRow(getSelectedProcessedFood(foodList, beveragesValue, "beverage"))
				.toArray(new String[0]));
		tableModel.addRow(setTotal(selectedFoodList).toArray(new String[0]));

		dataTable.setRowHeight(25);
		dataTable.setBackground(new Color(26, 13, 0));
		dataTable.setForeground(Color.white);

		// Adjust the width of the specified column in the table

		dataTable.getColumnModel().getColumn(0).setPreferredWidth(78);
		dataTable.getColumnModel().getColumn(1).setPreferredWidth(350);
		dataTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		dataTable.getColumnModel().getColumn(3).setPreferredWidth(75);
		dataTable.getColumnModel().getColumn(4).setPreferredWidth(45);
		dataTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		dataTable.getColumnModel().getColumn(7).setPreferredWidth(40);
		dataTable.getColumnModel().getColumn(8).setPreferredWidth(98);
		dataTable.getColumnModel().getColumn(9).setPreferredWidth(55);
		dataTable.getColumnModel().getColumn(10).setPreferredWidth(92);
		dataTable.getColumnModel().getColumn(11).setPreferredWidth(60);

		displayDataScroll = new JScrollPane(dataTable);
		displayDataScroll.getViewport().setBackground(new Color(26, 13, 0));

		middlePanel.add(displayDataScroll);
		middlePanel.revalidate();
		middlePanel.repaint();
	}

	/*
	 * get all the information of the given food item and category
	 */
	private List<String> convertProcessedFoodToTableRow(ProcessedFood pf) {

		List<String> dataList = new ArrayList<>();

		dataList.add(pf.getCategory());
		dataList.add(pf.getName());
		dataList.add(pf.getBrandName());
		dataList.add(pf.getServeSize().toString());
		dataList.add(pf.getUnit());
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.ENERGY_KG.getValue()));
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.PROTEIN_GM.getValue()));
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.FAT_GM.getValue()));
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.CARBOHYDRATE_GM.getValue()));
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.SUGAR_GM.getValue()));
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.DIETARY_FIBRE.getValue()));
		dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(), DataFile.CONSTANTS.SODIUM_MG.getValue()));
		return dataList;
	}

	private List<String> setTotal(List<ProcessedFood> processedFoodList) {
		List<String> dataList = new ArrayList<>();
		dataList.add("Total");
		dataList.add("");
		dataList.add("");
		dataList.add("");
		dataList.add("");
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.ENERGY_KG.getValue()));
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.PROTEIN_GM.getValue()));
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.FAT_GM.getValue()));
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.CARBOHYDRATE_GM.getValue()));
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.SUGAR_GM.getValue()));
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.DIETARY_FIBRE.getValue()));
		dataList.add(makeTotal(processedFoodList, DataFile.CONSTANTS.SODIUM_MG.getValue()));
		return dataList;
	}

	private String makeTotal(List<ProcessedFood> processedFoodList, String nutrientType) {
		Float total = 0.0f;
		for (ProcessedFood processedFood : processedFoodList) {
			total += processedFood.getSelectedNutrient(processedFood.getNutrient(), nutrientType);
		}
		return String.format("%.2f", total);
	}

	/*
	 * main method to execute application
	 */
	public static void main(String args[]) {

		String dataFileName = "processedFoodData.csv";
		new DataFile(dataFileName, foodList);
		new ProcessFoodGUI();
	}
}
