package ausAssignment.assignment3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI extends JFrame implements ActionListener {

	private static final Insets WEST_INSETS = new Insets(10, 0, 5, 15);
	private static final Insets EAST_INSETS = new Insets(10, 20, 5, 0);

	static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();
	static List<ProcessedFood> selectedFoodList = new ArrayList<ProcessedFood>();

	private JPanel topPanel;
	private JTextField userNameTxt;
	private JLabel userNameLbl;
	private JLabel cerealsLbl;
	private JRadioButton highProtinRbtn;
	private JRadioButton lowSugurRbtn;
	private JScrollPane cerealsScrollList;
	private JList<String> cerealsListBox;
	private JScrollPane beveragesScrollList;
	static JList<String> beveragesListBox;
	private JLabel beveragesLbl;
	ButtonGroup group;

	private JPanel middlePanel;
	private JTextArea textAreaForMsg;
	private JScrollPane displayDataScroll;

	private JPanel bottomPanel;
	private JLabel commandLbl;
	private JButton displayChoicesBtn;
	private JButton saveSelectionsBtn;
	private JButton clearDisplayBtn;
	private JButton quitBtn;
	private JTable dataTable;
	private Font textFont = new Font("", 0, 15);

	String userMessage = "";
	String userName = "";
	List<String> cerealsValue;
	List<String> beveragesValue;

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
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
	//	this.setLayout(new FlowLayout());
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
		userNameTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				welcomeMessage();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				welcomeMessage();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				welcomeMessage();
			}
		});

		highProtinRbtn = new JRadioButton("High Protin");
		gbc = createGbc(1, 1, 1);
		topPanel.add(highProtinRbtn, gbc);

		lowSugurRbtn = new JRadioButton("Low Sugur");
		gbc = createGbc(3, 1, 1);
		topPanel.add(lowSugurRbtn, gbc);

		group = new ButtonGroup();
		group.add(highProtinRbtn);
		group.add(lowSugurRbtn);

		cerealsLbl = new JLabel("Cereals ");
		gbc = createGbc(0, 2, 1);
		topPanel.add(cerealsLbl, gbc);

		cerealsListBox = new JList<>(fillComboBox(foodList, "cereals").toArray(new String[0]));
		cerealsListBox.setVisibleRowCount(3);
		cerealsScrollList = new JScrollPane(cerealsListBox);
		gbc = createGbc(1, 2, 1);
		cerealsLbl.setLabelFor(cerealsScrollList);
		cerealsScrollList.setVisible(true);
		cerealsScrollList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		cerealsScrollList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		topPanel.add(cerealsScrollList, gbc);

		beveragesLbl = new JLabel("Beverages   ");
		gbc = createGbc(2, 2, 1);
		topPanel.add(beveragesLbl, gbc);

		beveragesListBox = new JList<>(fillComboBox(foodList, "beverage").toArray(new String[0]));
		beveragesListBox.setVisibleRowCount(3);
		beveragesScrollList = new JScrollPane(beveragesListBox);
		gbc = createGbc(3, 2, 1);
		beveragesLbl.setLabelFor(beveragesScrollList);
		topPanel.add(beveragesScrollList, gbc);
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

		userMessage = "Hello User!!!\n\n" + "Welcome to Processed Food Assessor System\n\n" + "> Enter your name.\n\n"
				+ "> Select your preference of 'High Protein' or 'Low Sugar'\n\n"
				+ "> Use ctl+click to select multiple items from the displayed List\n\n"
				+ "> Use the DisplayChoices button to view details of your choices";

		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		middlePanel.setBorder(border);
		middlePanel.setBackground(Color.WHITE);

		textAreaForMsg = new JTextArea(userMessage, 15, 70);
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

		displayChoicesBtn = new JButton("Display Choices");

		saveSelectionsBtn = new JButton("Save Selections");
		saveSelectionsBtn.setEnabled(false);
		clearDisplayBtn = new JButton("Clear Display");
		clearDisplayBtn.setEnabled(false);
		quitBtn = new JButton("Quit");

		displayChoicesBtn.addActionListener(this::actionPerformed);
		saveSelectionsBtn.addActionListener(this::actionPerformed);
		clearDisplayBtn.addActionListener(this::actionPerformed);
		quitBtn.addActionListener(this::actionPerformed);

		bottomPanel.add(commandLbl);
		bottomPanel.add(displayChoicesBtn);
		bottomPanel.add(saveSelectionsBtn);
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
		if (e.getSource() == userNameTxt) {
			welcomeMessage();

		} else if (e.getSource() == quitBtn) {

			quitButton();

		} else if (e.getSource() == clearDisplayBtn) {

			clearDisplayBtnFun();

		} else if (e.getSource() == displayChoicesBtn) {

			displayChoiceBtnClickedAction();

		} else if (e.getSource() == saveSelectionsBtn) {

			saveSelectionBtnClickedAction();

		}
	}

	private void saveSelectionBtnClickedAction() {
		// TODO Auto-generated method stub
		System.out.println("Save Slected Button Clicket");
		errorMessage("User Data Stored Successfully!!!");
	}

	private void welcomeMessage() {
		// TODO Auto-generated method stub
		userName = userNameTxt.getText().trim();
		userMessage = "Hello " + userName + " Welcome to Processed Food Assessor System\n\n"
				+ "> Select your preference of 'High Protein' or 'Low Sugar'\n\n"
				+ "> Use ctl+click to select multiple items from the displayed List\n\n"
				+ "> Use the DisplayChoices button to view details of your choices";

		textAreaForMsg.setText(userMessage);
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
		group.clearSelection();
		cerealsListBox.clearSelection();
		beveragesListBox.clearSelection();

		setMiddlePanel();

		displayChoicesBtn.setEnabled(true);
		saveSelectionsBtn.setEnabled(false);
		clearDisplayBtn.setEnabled(false);
		this.pack();

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
	private void displayChoiceBtnClickedAction() {

		selectedFoodList.clear();
		saveSelectionsBtn.setEnabled(true);

		userName = userNameTxt.getText().trim();
		cerealsValue = cerealsListBox.getSelectedValuesList();
		beveragesValue = beveragesListBox.getSelectedValuesList();
		System.out.println(cerealsValue.toString());
		System.out.println(beveragesValue.toString());
		String errorMsg = "";

		// validate data input by user

		ProcessedFood selectedIteam = null;

		if (userName.equals(null) || userName.equals("")) {
			errorMsg = "Please Enter User Name!!!";
			errorMessage(errorMsg);
		} else if (!(highProtinRbtn.isSelected() || lowSugurRbtn.isSelected())) {
			errorMsg = "Please select Preferences!!!";
			errorMessage(errorMsg);
		} else if (cerealsValue.isEmpty()) {
			errorMsg = "Please select Cereals!!!";
			errorMessage(errorMsg);
		} else if (beveragesValue.isEmpty()) {
			errorMsg = "Please Enter beverages!!!";
			errorMessage(errorMsg);
		} else {
			for (String cerealValue : cerealsValue) {
				selectedIteam = getSelectedProcessedFood(foodList, cerealValue, "cereals");
				selectedFoodList.add(selectedIteam);
				System.out.println("Cereals :::" + cerealValue);
			}
			for (String beverageValue : beveragesValue) {
				selectedIteam = getSelectedProcessedFood(foodList, beverageValue, "beverage");
				selectedFoodList.add(selectedIteam);
				System.out.println("userName : " + userName + ":::Beverages :::" + beverageValue);
				displayData();
			}
		}
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
	private void displayData() {

		clearDisplayBtn.setEnabled(true);
		displayChoicesBtn.setEnabled(false);
		userNameTxt.setEditable(false);
		middlePanel.removeAll();

		String dataHeader[] = { "Food Type", "Item Name", "Brand", "Serve Size", "Unit", "Energy", "Protein", "Fat",
				"Carb", "Sugar", "Fibre", "Sodium" };

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
		displayDataScroll.getViewport().setBackground(Color.white);
		
		middlePanel.add(displayDataScroll);
		middlePanel.revalidate();
		middlePanel.repaint();
		this.pack();
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
		dataList.add(pf.getServeUnit());
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
