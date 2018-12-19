package ausAssignment.assignment3_1;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI extends JFrame implements ActionListener
{

    static List<ProcessedFood> foodList = new ArrayList<ProcessedFood>();

    private JPanel panel1;
    private JTextField userNameTxt;
    private JLabel userLbl;
    private JLabel cerealsLbl;
    private JComboBox<String> cerealsJCB;
    private JComboBox<String> beveragesJCB;
    private JLabel beveragesLbl;

    private JPanel panel2;
    private JTextArea textAreaForMsg;
    private JScrollPane displayJSP;

    private JPanel panel3;
    private JLabel commandLbl;
    private JButton enterDataBtn;
    private JButton displayDataBtn;
    private JButton clearDataBtn;
    private JButton quitApplicationBtn;
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
    ProcessFoodGUI()
    {

        /*--- Create Middle Panel -- START */
        panel1 = new JPanel();


        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        panel1.setBorder(border);
        panel1.setBackground(Color.WHITE);

        JPanel userNamePannel = new JPanel();

        userNamePannel.setBackground(Color.white);
        userLbl = new JLabel();
        userLbl.setIcon(new ImageIcon(new ImageIcon("images/userNameImg.png")
                                              .getImage().getScaledInstance(200, 20, Image.SCALE_SMOOTH)));
        userNamePannel.add(userLbl);
        userNamePannel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        userNameTxt = new JTextField(83);
        userLbl.setLabelFor(userNameTxt);
        userNamePannel.add(userNameTxt);

        panel1.add(userNamePannel);

        JPanel comboBoxPannel = new JPanel();
        comboBoxPannel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        comboBoxPannel.setBackground(Color.white);
        cerealsLbl = new JLabel();//
        cerealsLbl.setIcon(new ImageIcon(new ImageIcon("images/cereals-logo.jpg")
                                                 .getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));

        comboBoxPannel.add(cerealsLbl);

        cerealsJCB = new JComboBox<String>(fillDataInDropDownList(foodList, "cereals").toArray(new String[0]));
        cerealsLbl.setLabelFor(cerealsJCB);
        cerealsJCB.setSelectedIndex(-1);
        cerealsJCB.setPreferredSize(new Dimension(350, 25));
        cerealsJCB.setBackground(Color.white);
        comboBoxPannel.add(cerealsJCB);

        beveragesLbl = new JLabel();
        beveragesLbl.setIcon(new ImageIcon(new ImageIcon("images/beverages-logo.png")
                                                   .getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));

        comboBoxPannel.add(beveragesLbl);

        beveragesJCB = new JComboBox<String>(fillDataInDropDownList(foodList, "beverage").toArray(new String[0]));
        beveragesLbl.setLabelFor(beveragesJCB);
        beveragesJCB.setSelectedIndex(-1);
        beveragesJCB.setPreferredSize(new Dimension(350, 25));
        beveragesJCB.setBackground(Color.white);
        comboBoxPannel.add(beveragesJCB);
        panel1.add(comboBoxPannel);
        /*--- Create Top Panel -- END */


        /*--- Create Middle Panel -- START */
        panel2 = new JPanel();
        panel2.removeAll();
        userMessage = "Hello User!!!\n\n" + "Welcome to Processed Food Assessor System\n\n"
                + "Please follow below mentione steps.\n" + "1. Enter your name.\n"
                + "2. Select cereals and Beverages of you choice.\n"
                + "3. Click the 'Enter Data' Button to enter you choice.\n\n" + "Thank you.";

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        panel2.setBorder(border);
        panel2.setBackground(Color.WHITE);


        textAreaForMsg = new JTextArea();
        textAreaForMsg.setEditable(false);
        textAreaForMsg.setText(userMessage);
        textAreaForMsg.setFont(textFont);
        textAreaForMsg.setMargin(new Insets(10, 10, 10, 10));
        textAreaForMsg.setLineWrap(true);
        textAreaForMsg.setWrapStyleWord(true);
        textAreaForMsg.setBackground(new Color(0, 38, 77));
        textAreaForMsg.setForeground(Color.white);
        panel2.add(textAreaForMsg);
        panel2.revalidate();
        panel2.repaint();
        /*--- Create Middle Panel -- END */


        /*--- Create Bottom Panel -- START */
        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        panel3.setBorder(border);

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
        displayDataBtn = new JButton(
                new ImageIcon(new ImageIcon("images/displaydata.png").getImage()
                                      .getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
        displayDataBtn.setBorder(border);
        displayDataBtn.setEnabled(false);
        clearDataBtn = new JButton(new ImageIcon(new ImageIcon("images/clear.png")
                                                         .getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));

        clearDataBtn.setBorder(border);
        quitApplicationBtn = new JButton(new ImageIcon(new ImageIcon("images/quit1.jpg")
                                                               .getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH)));
        quitApplicationBtn.setBorder(border);
        enterDataBtn.addActionListener(this::actionPerformed);
        displayDataBtn.addActionListener(this::actionPerformed);
        clearDataBtn.addActionListener(this::actionPerformed);
        quitApplicationBtn.addActionListener(this::actionPerformed);

        btm2.add(enterDataBtn);
        btm2.add(displayDataBtn);
        btm2.add(clearDataBtn);
        btm2.add(quitApplicationBtn);

        panel3.add(btm1);
        panel3.add(btm2);
        /*--- Create Bottom Panel -- END */


        this.add(panel1);
        this.add(panel2);
        this.add(panel3);

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
     * Generate list for given category of food
     */
    public static List<String> fillDataInDropDownList(List<ProcessedFood> processedFoodList, String category)
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
     * (non-Javadoc)
     *
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * List of action to be performed based on source of action
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == quitApplicationBtn)
        {
            quitApplicationBtnAction();
        }
        else if (e.getSource() == clearDataBtn)
        {
            clearDataBtnAction();
        }
        else if (e.getSource() == enterDataBtn)
        {
            enterDataBtnAction();
        }
        else if (e.getSource() == displayDataBtn)
        {
            displayDataBtnClickedAction();
        }
    }

    /*
     * This function close the application
     */
    private void quitApplicationBtnAction()
    {
        // TODO Auto-generated method stub
        System.exit(0);

    }

    /*
     * This function will clear all the data from middle panel and set to same when
     * application started. Combo boxes cereals and beverages will still have data
     * loaded.
     */
    private void clearDataBtnAction()
    {
        userName = null;
        cerealsValue = null;
        beveragesValue = null;

        userNameTxt.setText(null);
        cerealsJCB.setSelectedIndex(-1);
        beveragesJCB.setSelectedIndex(-1);
        displayDataBtn.setEnabled(false);

        /*Reset Middle Panel -- START */
        panel2.removeAll();
        userMessage = "Hello User!!!\n\n" + "Welcome to Processed Food Assessor System\n\n"
                + "Please follow below mentione steps.\n" + "1. Enter your name.\n"
                + "2. Select cereals and Beverages of you choice.\n"
                + "3. Click the 'Enter Data' Button to enter you choice.\n\n" + "Thank you.";

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        panel2.setBorder(border);
        panel2.setBackground(Color.WHITE);

        textAreaForMsg = new JTextArea();
        textAreaForMsg.setEditable(false);
        textAreaForMsg.setText(userMessage);
        textAreaForMsg.setFont(textFont);
        textAreaForMsg.setMargin(new Insets(10, 10, 10, 10));
        textAreaForMsg.setLineWrap(true);
        textAreaForMsg.setWrapStyleWord(true);
        textAreaForMsg.setBackground(new Color(0, 38, 77));
        textAreaForMsg.setForeground(Color.white);
        panel2.add(textAreaForMsg);
        panel2.revalidate();
        panel2.repaint();
        /*Reset Middle Panel -- END */

    }

    private ProcessedFood getSelectedProcessedFood(List<ProcessedFood> processedFoodList, String itemName,
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
     * list of events to be done when "Enter Data" button is selected gets user name
     * and choices of different food category cereals and beverages and display
     * message to user accordingly.
     */
    private void enterDataBtnAction()
    {

//		selectedFoodList.clear();

        String errorMsg = "";

        userName = userNameTxt.getText().trim();

        if (userName.equals(null) || userName.equals(""))
        {
            errorMsg = "Please Enter User Name!!!";
            displayErrorDialogBox(errorMsg);
            return;
        }
        if (cerealsJCB.getSelectedIndex() == -1)
        {
            errorMsg = "Please select Cereals!!!";
            displayErrorDialogBox(errorMsg);
            return;
        }
        if (beveragesJCB.getSelectedIndex() == -1)
        {
            errorMsg = "Please Enter beverages!!!";
            displayErrorDialogBox(errorMsg);
            return;
        }

        cerealsValue = String.valueOf(cerealsJCB.getSelectedItem());
        beveragesValue = String.valueOf(beveragesJCB.getSelectedItem());

        // validate data input by user

        ProcessedFood selectedIteam = null;

        userMessage = "Hello  " + userName + "\n\n" + "Welcome to Processed Food Assessor System\n\n"
                + "You have selected '" + cerealsValue + "' in cereals and '" + beveragesValue
                + "' in beverages.\n\n" + "Click the 'Display Choices' button to view details of your choices.\n\n"
                + "Thank you.";

        System.out.println(
                "userName : " + userName + "::: Cereals :::" + cerealsValue + ":::Beverages :::" + beveragesValue);
        displayDataBtn.setEnabled(true);
        textAreaForMsg.setText(userMessage);

    }

    /*
     * generate error dialog box according to validation failure
     */
    public void displayErrorDialogBox(String errorMsg)
    {

        JOptionPane optionPane = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Data Validation");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    /*
     * gets all the details given by user and fetch information accordingly and
     * display it in user readable format. here we have use tabular format.
     */
    private void displayDataBtnClickedAction()
    {

        panel2.removeAll();

        String columnName[] = {"Food Type", "Item Name", "Brand", "Serve Size ", "Unit", "Energy", "Protein", "Fat",
                "Carbohydrate", "Sugar", "Dietary Fibre", "Sodium"};
        
        ProcessedFood processedFoodCereals = getSelectedProcessedFood(foodList, cerealsValue, "cereals");
        ProcessedFood processedFoodBeverage = getSelectedProcessedFood(foodList, beveragesValue, "beverage");

        String rowData[][] = {createTableRowData(processedFoodCereals).toArray(new String[0]),
        		createTableRowData(processedFoodBeverage).toArray(new String[0]),
        		createTotalRowData(processedFoodCereals, processedFoodBeverage).toArray(new String[0])};

        dataTable = new JTable(rowData,columnName);
        
        dataTable.setRowHeight(25);
        dataTable.setBackground(new Color(0, 38, 77));
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

        displayJSP = new JScrollPane(dataTable);
        displayJSP.getViewport().setBackground(new Color(0, 38, 77));

        panel2.add(displayJSP);
        panel2.revalidate();
        panel2.repaint();
    }

    /*
     * get all the information of the given food item and category
     */
    private List<String> createTableRowData(ProcessedFood pf)
    {

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

    private List<String> createTotalRowData(ProcessedFood processedFoodCereals, ProcessedFood processedFoodBeverages)
    {
        List<String> dataList = new ArrayList<>();
        dataList.add("Total");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.ENERGY) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.ENERGY)));
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.PROTEIN) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.PROTEIN)));
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.FAT) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.FAT)));
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.CARBOHYDRATE) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.CARBOHYDRATE)));
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.SUGAR) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.SUGAR)));
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.DIETARY_FIBER) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.DIETARY_FIBER)));
        dataList.add(String.format("%.2f", processedFoodCereals.getSingleNutrientFromList(processedFoodCereals.getNutrient(), DataFile.SODIUM) + processedFoodBeverages.getSingleNutrientFromList(processedFoodBeverages.getNutrient(), DataFile.SODIUM)));

        return dataList;
    }

    /*
     * main method to execute application
     */
    public static void main(String args[])
    {

        String dataFileName = "processedFoodData.csv";
        new DataFile(dataFileName, foodList);
        new ProcessFoodGUI();
    }
}
