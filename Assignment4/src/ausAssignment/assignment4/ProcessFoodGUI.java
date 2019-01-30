package ausAssignment.assignment4;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessFoodGUI extends JFrame implements ActionListener
{
    ProcessedFood selectedIteam = null;
    static DatabaseUtility dbConn = new DatabaseUtility();
    private static final Insets WEST_INSETS = new Insets(10, 0, 5, 15);
    private static final Insets EAST_INSETS = new Insets(10, 20, 5, 0);

    static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();
    static List<ProcessedFood> selectedFoodList = new ArrayList<ProcessedFood>();

    private JPanel topPanel;
    private JTextField userNameTxt;
    private JLabel userNameLbl;
    private JLabel cerealsLbl;
    private JLabel PreferenceLbl;
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
    String Preference = "";
    List<String> cerealsValue;
    List<String> beveragesValue;

    /*
     * default constructor
     */
    ProcessFoodGUI()
    {
        intializeGUI();
    }

    /*
     * Load all component in frame and set constraints on frame
     */
    public void intializeGUI()
    {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // this.setLayout(new FlowLayout());
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
    private void setTopPanel()
    {

        topPanel.setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15),
                8, true);
        topPanel.setBorder(border);

        GridBagConstraints gbc = new GridBagConstraints();

        userNameLbl = new JLabel("User Name  ");
        gbc = createGbc(0, 0, 1);
        topPanel.add(userNameLbl, gbc);

        userNameTxt = new JTextField(10);
        gbc = createGbc(1, 0, 3);
        userNameLbl.setLabelFor(userNameTxt);
        topPanel.add(userNameTxt, gbc);
        userNameTxt.getDocument().addDocumentListener(new DocumentListener()
        {

            @Override
            public void removeUpdate(DocumentEvent arg0)
            {
                welcomeMessage();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0)
            {
                welcomeMessage();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0)
            {
                welcomeMessage();
            }
        });

        PreferenceLbl = new JLabel("Preferences");
        gbc = createGbc(0, 1, 1);
        topPanel.add(PreferenceLbl, gbc);

        highProtinRbtn = new JRadioButton("High Protin");
        gbc = createGbc(1, 1, 1);
        topPanel.add(highProtinRbtn, gbc);
        highProtinRbtn.setActionCommand("protinGm");
        highProtinRbtn.addActionListener(this);

        lowSugurRbtn = new JRadioButton("Low Sugur");
        gbc = createGbc(2, 1, 1);
        topPanel.add(lowSugurRbtn, gbc);
        lowSugurRbtn.setActionCommand("sugarGm");
        lowSugurRbtn.addActionListener(this);

        group = new ButtonGroup();
        group.add(highProtinRbtn);
        group.add(lowSugurRbtn);

        cerealsLbl = new JLabel("Cereals ");
        gbc = createGbc(0, 2, 1);
        topPanel.add(cerealsLbl, gbc);

        cerealsScrollList = new JScrollPane();
        gbc = createGbc(1, 2, 1);
        cerealsLbl.setLabelFor(cerealsScrollList);
        cerealsScrollList.setVisible(true);
        cerealsScrollList.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        cerealsScrollList.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        topPanel.add(cerealsScrollList, gbc);

        beveragesLbl = new JLabel("Beverages   ");
        gbc = createGbc(2, 2, 1);
        topPanel.add(beveragesLbl, gbc);

        beveragesScrollList = new JScrollPane();

        gbc = createGbc(3, 2, 1);
        beveragesLbl.setLabelFor(beveragesScrollList);
        topPanel.add(beveragesScrollList, gbc);
        reFillScrollLists();
    }

    /*
     * set constraints for grid bag layout.
     */
    private GridBagConstraints createGbc(int x, int y, int gridwidth)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = 1;
        gbc.anchor = (x == 0 || x == 2) ? GridBagConstraints.EAST
                : GridBagConstraints.WEST;
        gbc.fill = (x == 2 && y == 1) ? GridBagConstraints.FIRST_LINE_START
                : GridBagConstraints.HORIZONTAL;
        gbc.insets = (x == 0 || x == 2) ? EAST_INSETS : WEST_INSETS;
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        gbc.weighty = (x == 0) ? 0.1 : 1.0;
        return gbc;
    }

    /*
     * Generate list for given category of food
     */
    public static List<String> fillComboBox(
            List<ProcessedFood> processedFoodList, String category)
    {
        List<String> foodNameList = new ArrayList<>();

        for (ProcessedFood processedFood : processedFoodList)
        {
            if (processedFood.getCategory().equalsIgnoreCase(category))
            {
                foodNameList.add(processedFood.getName());
            }
        }
        return foodNameList;
    }

    /*
     * Set all components and user guidelines to use application
     */
    private void setMiddlePanel()
    {
        middlePanel.removeAll();

        userMessage = "Hello User!!!\n\n"
                      + "Welcome to Processed Food Assessor System\n\n"
                      + "> Enter your name.\n\n"
                      + "> Select your preference of 'High Protein' or 'Low Sugar'\n\n"
                      + "> Use ctl+click to select multiple items from the displayed List\n\n"
                      + "> Use the DisplayChoices button to view details of your choices";

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15),
                8, true);
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
    private void setBottomPanel()
    {

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15),
                8, true);
        bottomPanel.setBorder(border);
        commandLbl = new JLabel("Command Buttons");
        commandLbl.setLabelFor(bottomPanel);

        displayChoicesBtn = new JButton("Display Choices");

        saveSelectionsBtn = new JButton("Save Selections");

        clearDisplayBtn = new JButton("Clear Display");

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
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == userNameTxt)
        {
            welcomeMessage();
        }
        else if (e.getSource() == lowSugurRbtn)
        {
            /*
             * System.out.println("Before Sorting"); foodList.stream().forEach(a
             * -> System.out.println(a.getName() + " : " +
             * a.getSelectedNutrient(a.getNutrient(),
             * DataFile.CONSTANTS.PROTEIN_GM.getValue())));
             */

            Collections.sort(foodList, ProcessedFood.sortByLowSugar);
            reFillScrollLists();

            /*
             * System.out.println("After Sorting"); foodList.stream().forEach(a
             * -> System.out.println(a.getName() + " : " +
             * a.getSelectedNutrient(a.getNutrient(),
             * DataFile.CONSTANTS.PROTEIN_GM.getValue())));
             */
        }
        else if (e.getSource() == highProtinRbtn)
        {
            Collections.sort(foodList, ProcessedFood.sortByHighProtien);
            reFillScrollLists();
        }
        else if (e.getSource() == quitBtn)
        {
            quitButton();
        }
        else if (e.getSource() == clearDisplayBtn)
        {

            clearDisplayBtnFun();

        }
        else if (e.getSource() == displayChoicesBtn)
        {

            displayData();

        }
        else if (e.getSource() == saveSelectionsBtn)
        {

            try
            {
                saveSelectionBtnClickedAction();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }

        }
    }

    private void reFillScrollLists()
    {
        cerealsListBox = new JList<>(
                fillComboBox(foodList, "cereals").toArray(new String[0]));
        cerealsListBox.setVisibleRowCount(3);
        cerealsScrollList.setViewportView(cerealsListBox);
        beveragesListBox = new JList<>(
                fillComboBox(foodList, "beverage").toArray(new String[0]));
        beveragesListBox.setVisibleRowCount(3);
        beveragesScrollList.setViewportView(beveragesListBox);
        revalidate();
        repaint();
        this.pack();
    }

    private void saveSelectionBtnClickedAction() throws SQLException
    {
        if (validateUserData() && selectedIteam != null)
        {
            errorMessage("User Data Stored Successfully!!!");
            dbConn.insertDataInUserFood(userName, selectedFoodList);
        }
        else {
            errorMessage("Press Display Data Button!!!");
        }
    }

    private void welcomeMessage()
    {
        userName = userNameTxt.getText().trim();
        userMessage = "Hello " + userName
                      + " Welcome to Processed Food Assessor System\n\n"
                      + "> Select your preference of 'High Protein' or 'Low Sugar'\n\n"
                      + "> Use ctl+click to select multiple items from the displayed List\n\n"
                      + "> Use the DisplayChoices button to view details of your choices";

        textAreaForMsg.setText(userMessage);
    }

    /*
     * This function close the application
     */
    private void quitButton()
    {
        System.exit(0);
    }

    /*
     * This function will clear all the data from middle panel and set to same
     * when application started. Combo boxes cereals and beverages will still
     * have data loaded.
     */
    private void clearDisplayBtnFun()
    {
        userName = null;
        cerealsValue = null;
        beveragesValue = null;

        userNameTxt.setEditable(true);
        userNameTxt.setText(null);
        group.clearSelection();
        cerealsListBox.clearSelection();
        beveragesListBox.clearSelection();

        setMiddlePanel();

        this.pack();

    }

    private ProcessedFood getSelectedProcessedFood(
            List<ProcessedFood> processedFoodList, String itemName,
            String category)
    {
        for (ProcessedFood processedFood : processedFoodList)
        {
            if (processedFood.getName().equalsIgnoreCase(itemName)
                && processedFood.getCategory().equalsIgnoreCase(category))
            {
                return processedFood;
            }
        }
        return null;
    }

    /*
     * list of events to be done when "Enter Data" button is selected gets user
     * name and choices of different food category cereals and beverages and
     * display message to user accordingly.
     */
    private Boolean validateUserData()
    {
        userName = userNameTxt.getText().trim();
        cerealsValue = cerealsListBox.getSelectedValuesList();
        beveragesValue = beveragesListBox.getSelectedValuesList();
        String errorMsg = "";

        // validate data input by user

        if (userName.equals(null) || userName.equals(""))
        {
            errorMsg = "Please Enter User Name!!!";
            errorMessage(errorMsg);
            return false;
        }
        if (!(highProtinRbtn.isSelected() || lowSugurRbtn.isSelected()))
        {
            errorMsg = "Please select Preferences!!!";
            errorMessage(errorMsg);
            return false;
        }
        if (cerealsValue.isEmpty())
        {
            errorMsg = "Please select Cereals!!!";
            errorMessage(errorMsg);
            return false;
        }
        if (beveragesValue.isEmpty())
        {
            errorMsg = "Please Enter beverages!!!";
            errorMessage(errorMsg);
            return false;
        }

        return true;
    }

    /*
     * generate error dialog box according to validation failure
     */
    public void errorMessage(String errorMsg)
    {

        JOptionPane optionPane = new JOptionPane(errorMsg,
                JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Data Validation");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    /*
     * gets all the details given by user and fetch information accordingly and
     * display it in user readable format. here we have use tabular format.
     */
    private void displayData()
    {
        if (validateUserData())
        {
            selectedFoodList.clear();
            for (String cerealValue : cerealsValue)
            {
                selectedIteam = getSelectedProcessedFood(foodList, cerealValue,
                        "cereals");
                selectedFoodList.add(selectedIteam);
            }
            for (String beverageValue : beveragesValue)
            {
                selectedIteam = getSelectedProcessedFood(foodList,
                        beverageValue, "beverage");
                selectedFoodList.add(selectedIteam);
            }

            try
            {
                dbConn.insertDataInUserData(userName);
                System.out.println("UserName:::" + userName + ":::Preference:::"
                                   + Preference);

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            middlePanel.removeAll();

            String dataHeader[] = {
                    "Food Type", "Item Name", "Brand", "Serve Size", "Unit",
                    "Energy", "Protein", "Fat", "Carb", "Sugar", "Fibre",
                    "Sodium"
            };

            // Allow to create dynamic table

            DefaultTableModel tableModel = new DefaultTableModel(dataHeader, 0);

            dataTable = new JTable(tableModel);
            for (ProcessedFood selectedFood : selectedFoodList)
            {
                tableModel.addRow(convertProcessedFoodToTableRow(selectedFood)
                        .toArray(new String[0]));

                System.out.println("SelectedFood::::" + selectedFood);
            }
            tableModel
                    .addRow(setTotal(selectedFoodList).toArray(new String[0]));

            dataTable.setShowGrid(false);
            dataTable.setRowHeight(25);
            dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JTableHeader header = dataTable.getTableHeader();
            header.setBackground(Color.WHITE);

            // Adjust the width of the specified column in the table

            for (int columnNumber = 0; columnNumber < dataTable
                    .getColumnCount(); columnNumber++)
            {
                TableColumn columnOfTable = dataTable.getColumnModel()
                        .getColumn(columnNumber);

                int columnHeaderWidth = getColumnHeaderWidth(columnNumber);
                int columnDataWidth = getColumnDataWidth(columnNumber);
                int preferredWidth = Math.max(columnHeaderWidth,
                        columnDataWidth);
                columnOfTable.setPreferredWidth(preferredWidth + 10);
            }

            displayDataScroll = new JScrollPane(dataTable);
            displayDataScroll.setVisible(true);
            displayDataScroll.setBackground(Color.WHITE);

            displayDataScroll.setFont(textFont);

            displayDataScroll.setVerticalScrollBarPolicy(
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            displayDataScroll.setHorizontalScrollBarPolicy(
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            displayDataScroll.getViewport().setBackground(Color.white);

            middlePanel.add(displayDataScroll);
            middlePanel.revalidate();
            middlePanel.repaint();
            this.pack();
        }
    }

    /*
     * get all the information of the given food item and category
     */
    private List<String> convertProcessedFoodToTableRow(ProcessedFood pf)
    {

        List<String> dataList = new ArrayList<>();

        dataList.add(pf.getCategory());
        dataList.add(pf.getName());
        dataList.add(pf.getBrandName());
        dataList.add(pf.getServeSize().toString());
        dataList.add(pf.getServeUnit());
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.ENERGY_KG));
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.PROTEIN_GM));
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.FAT_GM));
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.CARBOHYDRATE_GM));
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.SUGAR_GM));
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.DIETARY_FIBRE));
        dataList.add(pf.getSelectedNutrientInString(pf.getNutrient(),
                Nutrient.SODIUM_MG));
        return dataList;
    }

    private List<String> setTotal(List<ProcessedFood> processedFoodList)
    {
        List<String> dataList = new ArrayList<>();
        dataList.add("Total");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add(makeTotal(processedFoodList, Nutrient.ENERGY_KG));
        dataList.add(makeTotal(processedFoodList, Nutrient.PROTEIN_GM));
        dataList.add(makeTotal(processedFoodList, Nutrient.FAT_GM));
        dataList.add(makeTotal(processedFoodList, Nutrient.CARBOHYDRATE_GM));
        dataList.add(makeTotal(processedFoodList, Nutrient.SUGAR_GM));
        dataList.add(makeTotal(processedFoodList, Nutrient.DIETARY_FIBRE));
        dataList.add(makeTotal(processedFoodList, Nutrient.SODIUM_MG));
        return dataList;
    }

    private String makeTotal(List<ProcessedFood> processedFoodList,
            String nutrientType)
    {
        Float total = 0.0f;
        for (ProcessedFood processedFood : processedFoodList)
        {
            total += processedFood.getSelectedNutrient(
                    processedFood.getNutrient(), nutrientType);
        }
        return String.format("%.2f", total);
    }

    /*
     * Calculated the width based on the column name
     */
    private int getColumnHeaderWidth(int columnNumber)
    {
        TableColumn columnOfTable = dataTable.getColumnModel()
                .getColumn(columnNumber);
        Object value = columnOfTable.getHeaderValue();
        TableCellRenderer renderer = columnOfTable.getHeaderRenderer();

        if (renderer == null)
        {
            renderer = dataTable.getTableHeader().getDefaultRenderer();
        }

        Component c = renderer.getTableCellRendererComponent(dataTable, value,
                false, false, -1, columnNumber);
        return c.getPreferredSize().width;
    }

    /*
     * Calculate the width based on the widest cell renderer for the given
     * column.
     */
    private int getColumnDataWidth(int columnNumber)
    {
        int preferredWidth = 0;
        int maxWidth = dataTable.getColumnModel().getColumn(columnNumber)
                .getMaxWidth();

        for (int row = 0; row < dataTable.getRowCount(); row++)
        {
            preferredWidth = Math.max(preferredWidth,
                    getCellDataWidth(row, columnNumber));

            // We've exceeded the maximum width, no need to check other rows

            if (preferredWidth >= maxWidth)
                break;
        }

        return preferredWidth;
    }

    /*
     * Get the preferred width for the specified cell
     */
    private int getCellDataWidth(int row, int columnNumber)
    {
        // Invoke the renderer for the cell to calculate the preferred width

        TableCellRenderer cellRenderer = dataTable.getCellRenderer(row,
                columnNumber);
        Component c = dataTable.prepareRenderer(cellRenderer, row,
                columnNumber);
        int columnWidth = c.getPreferredSize().width
                          + dataTable.getIntercellSpacing().width;

        return columnWidth;
    }

    /*
     * main method to execute application
     */
    public static void main(String args[]) throws SQLException
    {

        String dataFileName = "processedFoodData.csv";

        foodList = dbConn.getListoffood();
        if (foodList.isEmpty())
        {
            new DataFile(dataFileName, foodList);
        }
        new ProcessFoodGUI();
        new DatabaseUtility();
    }
}
