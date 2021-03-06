package ausAssignment.assignment3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI extends JFrame  implements ActionListener {

	private static final Insets WEST_INSETS = new Insets(10, 0, 5, 15);
	private static final Insets EAST_INSETS = new Insets(10, 20, 5, 0);

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

		topPanel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		topPanel.setBorder(border);

		GridBagConstraints gbc = new GridBagConstraints();

		userNameLbl = new JLabel("User Name  ");
		gbc = createGbc(0, 0, 1);
		topPanel.add(userNameLbl, gbc);

		userNameTxt = new JTextField(10);
		gbc = createGbc(1, 0, 3);
		userNameLbl.setLabelFor(userNameTxt);
		topPanel.add(userNameTxt, gbc);

		cerealsLbl = new JLabel("Cereals ");
		gbc = createGbc(0, 1, 1);
		topPanel.add(cerealsLbl, gbc);

		cerealsComboBox = new JComboBox<String>(fillComboBox(foodList, "cereals").toArray(new String[0]));
		gbc = createGbc(1, 1, 1);
		cerealsLbl.setLabelFor(cerealsComboBox);
		cerealsComboBox.setSelectedIndex(-1);
		topPanel.add(cerealsComboBox, gbc);

		beveragesLbl = new JLabel("Beverages   ");
		gbc = createGbc(2, 1, 1);
		topPanel.add(beveragesLbl, gbc);

		beveragesComboBox = new JComboBox<String>(fillComboBox(foodList, "beverage").toArray(new String[0]));
		gbc = createGbc(3, 1, 1);
		beveragesLbl.setLabelFor(beveragesComboBox);
		beveragesComboBox.setSelectedIndex(-1);
		topPanel.add(beveragesComboBox, gbc);
	}

	/*
	 * set constraints for grid bag layout.
	 */
	private GridBagConstraints createGbc(int x, int y, int gridwidth) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = 1;
		gbc.anchor = (x == 0 || x == 2) ? GridBagConstraints.EAST : GridBagConstraints.WEST;
		gbc.fill = (x == 2 && y == 1) ? GridBagConstraints.FIRST_LINE_START : GridBagConstraints.HORIZONTAL;
		gbc.insets = (x == 0 || x == 2) ? EAST_INSETS : WEST_INSETS;
		gbc.weightx = (x == 0) ? 0.1 : 1.0;
		gbc.weighty = (x == 0) ? 0.1 : 1.0;
		return gbc;
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

		textAreaForMsg = new JTextArea(userMessage, 20, 20);
		textAreaForMsg.setEditable(false);
		textAreaForMsg.setFont(textFont);
		textAreaForMsg.setMargin(new Insets(10, 10, 10, 10));
		textAreaForMsg.setLineWrap(true);
		textAreaForMsg.setWrapStyleWord(true);
		middlePanel.add(textAreaForMsg);
		middlePanel.revalidate();
		middlePanel.repaint();
	}

	/*
	 * Set all components of bottom panel along with action listeners
	 */
	private void setBottomPanel() {

		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		bottomPanel.setBorder(border);
		commandLbl = new JLabel("Command Buttons");
		commandLbl.setLabelFor(bottomPanel);

		enterDataBtn = new JButton("Enter Data");

		displayChoiceBtn = new JButton("Display Choices");
		displayChoiceBtn.setEnabled(false);
		clearDisplayBtn = new JButton("Clear Display");
		clearDisplayBtn.setEnabled(false);
		quitBtn = new JButton("Quit");

		enterDataBtn.addActionListener(this::actionPerformed);
		displayChoiceBtn.addActionListener(this::actionPerformed);
		clearDisplayBtn.addActionListener(this::actionPerformed);
		quitBtn.addActionListener(this::actionPerformed);

		bottomPanel.add(commandLbl);
		bottomPanel.add(enterDataBtn);
		bottomPanel.add(displayChoiceBtn);
		bottomPanel.add(clearDisplayBtn);
		bottomPanel.add(quitBtn);
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

		userNameTxt.setEditable(true);

		userNameTxt.setText(null);
		cerealsComboBox.setSelectedIndex(-1);
		beveragesComboBox.setSelectedIndex(-1);

		setMiddlePanel();

		enterDataBtn.setEnabled(true);
		displayChoiceBtn.setEnabled(false);
		clearDisplayBtn.setEnabled(false);

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
		displayChoiceBtn.setEnabled(true);

		userName = userNameTxt.getText().trim();
		cerealsValue = String.valueOf(cerealsComboBox.getSelectedItem());
		beveragesValue = String.valueOf(beveragesComboBox.getSelectedItem());

		String errorMsg = "";

		// validate data input by user

		ProcessedFood selectedIteam = null;

		if (userName.equals(null) || userName.equals("")) {
			errorMsg = "Please Enter User Name!!!";
			errorMessage(errorMsg);
		} else if (cerealsValue.equals(null) || cerealsValue.equals("null")) {
			errorMsg = "Please select Cereals!!!";
			errorMessage(errorMsg);
		} else if (beveragesValue.equals(null) || beveragesValue.equals("null")) {
			errorMsg = "Please Enter beverages!!!";
			errorMessage(errorMsg);
		} else {
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

		clearDisplayBtn.setEnabled(true);
		enterDataBtn.setEnabled(false);
		userNameTxt.setEditable(false);
		middlePanel.removeAll();

		String dataHeader[] = { "Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium" };

		// Allow to create dynamic table

		DefaultTableModel tableModel = new DefaultTableModel(dataHeader, 0);

		dataTable = new JTable(tableModel);
		for (ProcessedFood selectedFood : selectedFoodList) {
			tableModel.addRow(convertProcessedFoodToTableRow(selectedFood).toArray(new String[0]));
		}
		tableModel.addRow(setTotal(selectedFoodList).toArray(new String[0]));

		dataTable.setShowGrid(false);
		dataTable.setRowHeight(25);
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JTableHeader header = dataTable.getTableHeader();
		header.setBackground(Color.WHITE);

		// Adjust the width of the specified column in the table

		for (int columnNumber = 0; columnNumber < dataTable.getColumnCount(); columnNumber++) {
			TableColumn columnOfTable = dataTable.getColumnModel().getColumn(columnNumber);
			columnOfTable.setHeaderRenderer(new HeaderRenderer());
			int columnHeaderWidth = getColumnHeaderWidth(columnNumber);
			int columnDataWidth = getColumnDataWidth(columnNumber);
			int preferredWidth = Math.max(columnHeaderWidth, columnDataWidth);
			columnOfTable.setPreferredWidth(preferredWidth + 10);
		}

		displayDataScroll = new JScrollPane(dataTable);
		displayDataScroll.setVisible(true);
		displayDataScroll.setBackground(Color.WHITE);

		displayDataScroll.setFont(textFont);

		displayDataScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		displayDataScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		displayDataScroll.getHorizontalScrollBar();
		displayDataScroll.getViewport().setBackground(Color.white);

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
	 * Removes header gird from the table
	 */
	class HeaderRenderer extends JLabel implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable dataTable, Object value, boolean hasFocus,
				boolean isSelected, int row, int col) {
			setText(value.toString());
			setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			return this;
		}
	}

	/*
	 * Calculated the width based on the column name
	 */
	private int getColumnHeaderWidth(int columnNumber) {
		TableColumn columnOfTable = dataTable.getColumnModel().getColumn(columnNumber);
		Object value = columnOfTable.getHeaderValue();
		TableCellRenderer renderer = columnOfTable.getHeaderRenderer();

		if (renderer == null) {
			renderer = dataTable.getTableHeader().getDefaultRenderer();
		}

		Component c = renderer.getTableCellRendererComponent(dataTable, value, false, false, -1, columnNumber);
		return c.getPreferredSize().width;
	}

	/*
	 * Calculate the width based on the widest cell renderer for the given column.
	 */
	private int getColumnDataWidth(int columnNumber) {
		int preferredWidth = 0;
		int maxWidth = dataTable.getColumnModel().getColumn(columnNumber).getMaxWidth();

		for (int row = 0; row < dataTable.getRowCount(); row++) {
			preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, columnNumber));

			// We've exceeded the maximum width, no need to check other rows

			if (preferredWidth >= maxWidth)
				break;
		}

		return preferredWidth;
	}

	/*
	 * Get the preferred width for the specified cell
	 */
	private int getCellDataWidth(int row, int columnNumber) {
		// Invoke the renderer for the cell to calculate the preferred width

		TableCellRenderer cellRenderer = dataTable.getCellRenderer(row, columnNumber);
		Component c = dataTable.prepareRenderer(cellRenderer, row, columnNumber);
		int columnWidth = c.getPreferredSize().width + dataTable.getIntercellSpacing().width;

		return columnWidth;
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
