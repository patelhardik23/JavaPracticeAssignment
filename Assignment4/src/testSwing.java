import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

class HeaderRenderer extends JLabel implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean hasFocus,
                                                   boolean isSelected,
                                                   int row,
                                                   int col)
    {
        setText(value.toString());
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        return this;
    }
}
public class testSwing {
	Button buttons[];
	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	private JTable table;

	public testSwing() {

		JFrame f = new JFrame("Panel Example");
		buttons = new Button[5];
		JPanel topPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		topPanel.setBorder(border);
		middlePanel.setBorder(border);
		bottomPanel.setBorder(border);
		// Border btmBorder = BorderFactory.createLineBorder(Color.BLUE, 5);
		// bottomPanel.setLayout(new GridBagLayout());
		// bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout layout = new GridBagLayout();

		bottomPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc = createGbc(0, 0, 1, 0);
		bottomPanel.add(new JLabel("User Name"), gbc);

		gbc = createGbc(1, 0, 2, 1);
		bottomPanel.add(new JTextField("Enter Data"), gbc);

		gbc = createGbc(0, 1, 1, 1);
		bottomPanel.add(new JButton("Display Choices"), gbc);

		gbc = createGbc(1, 1, 1, 0);
		bottomPanel.add(new JButton("Clear Display"), gbc);

		gbc = createGbc(2, 1, 1, 1);
		bottomPanel.add(new JButton("Quit"), gbc);

		topPanel = new JPanel();

		// topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		topPanel.setLayout(layout);
		JLabel userNameLbl = new JLabel("User Name");
		gbc = createGbc(0, 0, 1, 1);
		topPanel.add(userNameLbl, gbc);

		JTextField userNameTxt = new JTextField();
		userNameTxt.setEditable(true);
		gbc = createGbc(1, 0, 2, 0);
		topPanel.add(userNameTxt, gbc);

		userNameLbl.setLabelFor(userNameTxt);

		JLabel cerealsLbl = new JLabel("cereals");
		gbc = createGbc(0, 1, 1, 1);
		topPanel.add(cerealsLbl, gbc);

		String country[] = { "India", "Aus", "U.S.A", "England", "Newzealand" };

		JComboBox<String> cerealsComboBox = new JComboBox<String>(country);
		gbc = createGbc(1, 1, 1, 1);
		topPanel.add(cerealsComboBox, gbc);

		cerealsLbl.setLabelFor(cerealsComboBox);
		cerealsComboBox.setSelectedItem(null);

		JLabel beveragesLbl = new JLabel("beverages");
		gbc = createGbc(2, 1, 1, 1);
		topPanel.add(beveragesLbl, gbc);

		String state[] = { "Gujarat", "Rajasthan", "M.P.", "Mumbai", "JnK" };
		JComboBox<String> beveragesComboBox = new JComboBox<String>(state);
		gbc = createGbc(3, 1, 1, 0);
		topPanel.add(beveragesComboBox, gbc);

		beveragesLbl.setLabelFor(beveragesComboBox);
		beveragesComboBox.setSelectedItem(null);
		
		
//		JTextArea jt = new JTextArea(10,10);
	    String data[][]={ {"Food Type", "Item NameItem NameItem NameItem NameItem NameItem Name", "BrandItem Name", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
			"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium"}, {"Food Type", "Item NameItem NameItem NameItem NameItem NameItem NameItem NameItem Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium"}};    
	    String colu[]={"Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium"};
		String dataHeader[] = { "Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium" };
		String cereal[] = { "Food Type", "Item NameItem NameItem NameItem NameItem NameItem Name", "BrandItem Name", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium" };
		String beverage[] = { "Food Type", "Item NameItem NameItem NameItem NameItem NameItem NameItem NameItem Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
				"Carbohydrate", "Sugar", "Dietary Fibre", "Sodium" };
		
		DefaultTableModel tableModel = new DefaultTableModel(dataHeader, 0);
		//table = new JTable(data,colu);
		table = new JTable(tableModel);
		tableModel.addRow(dataHeader);
		tableModel.addRow(cereal);
		tableModel.addRow(beverage);
		
		table.setBackground(Color.WHITE);
		table.setShowGrid(false);
		table.setBorder(BorderFactory.createEmptyBorder(20, 25,25 , 25));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);

		for (int column = 0; column < table.getColumnCount(); column++) {
			TableColumn tableColumn = table.getColumnModel().getColumn(column);
			tableColumn.setHeaderRenderer(new HeaderRenderer());
			int columnHeaderWidth = getColumnHeaderWidth( column );
			int columnDataWidth   = getColumnDataWidth( column );
			int preferredWidth	= Math.max(columnHeaderWidth, columnDataWidth);
			//updateTableColumn(column, preferredWidth);
			tableColumn.setPreferredWidth(preferredWidth);
			
		}

		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.WHITE);
	    //header.setBorder(null);
	    
	    
	    
	    JScrollPane sp=new JScrollPane(table);
	    
		
		
		middlePanel.add(sp);
		f.add(topPanel);
		f.add(middlePanel);
		f.add(bottomPanel);

		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		// f.setSize(400, 400);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
	}
	
	
	/*
	 *  Calculated the width based on the column name
	 */
	private int getColumnHeaderWidth(int column)
	{
	//	if (! isColumnHeaderIncluded) return 0;

		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		
		Object value = tableColumn.getHeaderValue();
		TableCellRenderer renderer = tableColumn.getHeaderRenderer();

		if (renderer == null)
		{
			renderer = table.getTableHeader().getDefaultRenderer();
		}

		Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
		return c.getPreferredSize().width;
	}

	/*
	 *  Calculate the width based on the widest cell renderer for the
	 *  given column.
	 */
	private int getColumnDataWidth(int column)
	{
//		if (! isColumnDataIncluded) return 0;

		int preferredWidth = 0;
		int maxWidth = table.getColumnModel().getColumn(column).getMaxWidth();

		for (int row = 0; row < table.getRowCount(); row++)
		{
			preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));

			//  We've exceeded the maximum width, no need to check other rows

			if (preferredWidth >= maxWidth)
				break;
		}

		return preferredWidth;
	}

	/*
	 *  Get the preferred width for the specified cell
	 */
	private int getCellDataWidth(int row, int column)
	{
		//  Inovke the renderer for the cell to calculate the preferred width

		TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		Component c = table.prepareRenderer(cellRenderer, row, column);
		int width = c.getPreferredSize().width + table.getIntercellSpacing().width;

		return width;
	}


	private GridBagConstraints createGbc(int x, int y, int gridwidth, int side) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = 1;

		gbc.anchor = (side == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
		gbc.weightx = (x == 0) ? 0.1 : 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}

	public static void main(String args[]) {
		testSwing b = new testSwing();
	}
}