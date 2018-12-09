package ausAssignment.assignment3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import com.sun.javafx.scene.control.skin.TableHeaderRow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProcessFoodGUI extends JFrame implements ActionListener {

	private static final Insets WEST_INSETS = new Insets(10, 0, 5, 15);
	private static final Insets EAST_INSETS = new Insets(10, 20, 5, 0);

	static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();

	private JPanel topPanel;
	private JTextField userNameTxt;
	private JLabel userNameLbl;
	private JLabel cerealsLbl;
	private JComboBox<String> cerealsComboBox;
	private JComboBox<String> beveragesComboBox;
	private JLabel beveragesLbl;

	private JPanel middlePanel;
	private JTextArea textArea;
	private JScrollPane textAreaScroll;

	private JPanel bottomPanel;
	private JLabel commandLbl;
	private JButton enterDataBtn;
	private JButton displayChoiceBtn;
	private JButton clearDisplayBtn;
	private JButton quitBtn;
	private JTable table;
	String userMessage = "";
	String welcomeMessage = "";
	String userName = "";
	String cerealsValue = "";
	String beveragesValue = "";

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

	private void setTopPanel() {

		topPanel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
		topPanel.setBorder(border);

		GridBagConstraints gbc = new GridBagConstraints();

		userNameLbl = new JLabel("User Name");
		gbc = createGbc(0, 0, 1);
		topPanel.add(userNameLbl, gbc);

		userNameTxt = new JTextField(10);
		gbc = createGbc(1, 0, 3);
		userNameTxt.setEditable(true);
		userNameLbl.setLabelFor(userNameTxt);
		topPanel.add(userNameTxt, gbc);

		cerealsLbl = new JLabel("cereals");
		gbc = createGbc(0, 1, 1);
		topPanel.add(cerealsLbl, gbc);

		cerealsComboBox = new JComboBox<String>(fillComboBox(foodList, "cereals").toArray(new String[0]));
		gbc = createGbc(1, 1, 1);
		cerealsLbl.setLabelFor(cerealsComboBox);
		cerealsComboBox.setSelectedIndex(-1);
		topPanel.add(cerealsComboBox, gbc);

		beveragesLbl = new JLabel("beverages");
		gbc = createGbc(2, 1, 1);
		topPanel.add(beveragesLbl, gbc);

		beveragesComboBox = new JComboBox<String>(fillComboBox(foodList, "beverage").toArray(new String[0]));
		gbc = createGbc(3, 1, 1);
		beveragesLbl.setLabelFor(beveragesComboBox);
		beveragesComboBox.setSelectedIndex(-1);
		topPanel.add(beveragesComboBox, gbc);

	}

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

		Font textAreaFont = new Font("SansSerif", 0, 15);
		textArea = new JTextArea(userMessage, 20, 20);
		textArea.setEditable(false);
		textArea.setFont(textAreaFont);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		middlePanel.add(textArea);
		middlePanel.revalidate();
		middlePanel.repaint();
	}

	private void dipalyUserChoiceData() {

		middlePanel.removeAll();

		String dataHeader[] = { "Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy" };

		DefaultTableModel tableModel = new DefaultTableModel(dataHeader, 0);

		table = new JTable(tableModel);
		tableModel.addRow(getDataOfUserChoice(foodList, "cereals", cerealsValue).toArray(new String[0]));
		tableModel.addRow(getDataOfUserChoice(foodList, "beverage", beveragesValue).toArray(new String[0]));

		table.setShowGrid(false);
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		/*
		 * Adjust the width of the specified column in the table
		 */
		for (int column = 0; column < table.getColumnCount(); column++) {
			TableColumn tableColumn = table.getColumnModel().getColumn(column);
			tableColumn.setHeaderRenderer(new HeaderRenderer());
			int columnHeaderWidth = getColumnHeaderWidth(column);
			int columnDataWidth = getColumnDataWidth(column);
			int preferredWidth = Math.max(columnHeaderWidth, columnDataWidth);
			tableColumn.setPreferredWidth(preferredWidth+10);
		}
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		textAreaScroll = new JScrollPane(table);
		textAreaScroll.setVisible(true);
		textAreaScroll.setBackground(Color.WHITE);
		textAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textAreaScroll.getHorizontalScrollBar();
		textAreaScroll.getViewport().setBackground(Color.white);
		middlePanel.add(textAreaScroll);
		middlePanel.revalidate();
		middlePanel.repaint();
	}

	/*
	 * Calculated the width based on the column name
	 */
	private int getColumnHeaderWidth(int column) {
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		Object value = tableColumn.getHeaderValue();
		TableCellRenderer renderer = tableColumn.getHeaderRenderer();

		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}

		Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
		return c.getPreferredSize().width;
	}

	/*
	 * Calculate the width based on the widest cell renderer for the given column.
	 */
	private int getColumnDataWidth(int column) {
		int preferredWidth = 0;
		int maxWidth = table.getColumnModel().getColumn(column).getMaxWidth();

		for (int row = 0; row < table.getRowCount(); row++) {
			preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));

			// We've exceeded the maximum width, no need to check other rows

			if (preferredWidth >= maxWidth)
				break;
		}

		return preferredWidth;
	}

	/*
	 * Get the preferred width for the specified cell
	 */
	private int getCellDataWidth(int row, int column) {
		// Inovke the renderer for the cell to calculate the preferred width

		TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		Component c = table.prepareRenderer(cellRenderer, row, column);
		int width = c.getPreferredSize().width + table.getIntercellSpacing().width;

		return width;
	}

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

	private void enterDataBtnClickedAction() {

		userName = userNameTxt.getText().trim();
		cerealsValue = String.valueOf(cerealsComboBox.getSelectedItem()).trim();
		beveragesValue = String.valueOf(beveragesComboBox.getSelectedItem()).trim();

		String errorMsg = "";

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
			System.out.println(
					"userName : " + userName + "::: Cereals :::" + cerealsValue + ":::Beverages :::" + beveragesValue);
		}
	}

	public void errorMessage(String errorMsg) {

		JOptionPane optionPane = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Data Validation");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}

	public static List<String> fillComboBox(List<ProcessedFood> processedFoodList, String category) {
		List<String> stringList = new ArrayList<>();

		for (ProcessedFood processedFood : processedFoodList) {
			if (processedFood.getCategory().equalsIgnoreCase(category)) {
				// cerealsComboBox.addItem(processedFood.getName());
				stringList.add(processedFood.getName());
			}
		}

		return stringList;
		// cerealsComboBox = new JComboBox<String>((String[]) cerealsList.toArray());
		// beveragesComboBox = new JComboBox<String>((String[]) beverageList.toArray());

		// topPanel.revalidate();
		// topPanel.repaint();
	}

	public static List<String> getDataOfUserChoice(List<ProcessedFood> processedFoodList, String category,
			String foodName) {
		List<String> dataList = new ArrayList<>();

		for (ProcessedFood pf : processedFoodList) {
			if (pf.getCategory().equalsIgnoreCase(category) && pf.getName().equalsIgnoreCase(foodName)) {
				dataList.add(pf.getName());
				dataList.add(pf.getBrandName());
				dataList.add(pf.getCategory());
				dataList.add(pf.getUnit());
				dataList.add(pf.getServeSize().toString());
				dataList.add(pf.getNutrient().toString());
			}
		}
		return dataList;
	}

	ProcessFoodGUI() {
		// String userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quitBtn) {
			System.exit(0);
		} else if (e.getSource() == clearDisplayBtn) {

			userName = null;
			cerealsValue = null;
			beveragesValue = null;
			userNameTxt.setText(null);
			userNameTxt.setEditable(true);
			cerealsComboBox.setSelectedIndex(-1);
			beveragesComboBox.setSelectedIndex(-1);
			setMiddlePanel();
			enterDataBtn.setEnabled(true);
			displayChoiceBtn.setEnabled(false);
			clearDisplayBtn.setEnabled(false);
		} else if (e.getSource() == enterDataBtn) {
			textArea.setText(welcomeMessage);
			enterDataBtnClickedAction();
			displayChoiceBtn.setEnabled(true);
			textArea.setText(userMessage);
		} else if (e.getSource() == displayChoiceBtn) {
			dipalyUserChoiceData();
			clearDisplayBtn.setEnabled(true);
			enterDataBtn.setEnabled(false);
		}
	}
/*
 * Removes header gird from the table
 */
	class HeaderRenderer extends JLabel implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean hasFocus, boolean isSelected,
				int row, int col) {
			setText(value.toString());
			setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			return this;
		}
	}

	public static void main(String args[]) {

		String fileName = "ausAssignment3_data.csv";

		ProcessFoodGUI processFoodGUI = new ProcessFoodGUI();
		new DataFile(fileName, foodList);
		processFoodGUI.intializeGUI();
		// processFoodGUI.fillComboBox(foodList);
	}
}
