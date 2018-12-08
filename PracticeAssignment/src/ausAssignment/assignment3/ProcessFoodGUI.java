package ausAssignment.assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProcessFoodGUI implements ActionListener
{
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

    public void intializeGUI()
    {
        f = new JFrame("Panel Example");

        setTopPanel();
        setMiddlePanel();
        setBottomPanel();

        f.add(topPanel);
        f.add(middlePanel);
        f.add(bottomPanel);

        f.setSize(610, 600);
        // f.setLayout(new FlowLayout(FlowLayout.RIGHT));
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setTopPanel()
    {
        topPanel = new JPanel();

        topPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        topPanel.setBounds(0, 0, 600, 100);

        userNameTxt = new JTextField(45);
        userNameTxt.setEditable(true);

        userNameLbl = new JLabel("User Name");
        userNameLbl.setLabelFor(userNameTxt);

        String country[] = {"India", "Aus", "U.S.A", "England", "Newzealand"};

        cerealsComboBox = new JComboBox<String>(country);
        cerealsLbl = new JLabel("cereals");
        cerealsLbl.setLabelFor(cerealsComboBox);
        cerealsComboBox.setBounds(10, 30, 10, 10);
        cerealsComboBox.setSelectedItem(null);

        String state[] = {"Gujarat", "Rajasthan", "M.P.", "Mumbai", "JnK"};
        beveragesComboBox = new JComboBox<String>(state);
        beveragesLbl = new JLabel("beverages");
        beveragesLbl.setLabelFor(beveragesComboBox);
        beveragesComboBox.setBounds(50, 30, 10, 10);
        beveragesComboBox.setSelectedItem(null);

        topPanel.add(userNameLbl);
        topPanel.add(userNameTxt);
        topPanel.add(cerealsLbl);

        topPanel.add(cerealsComboBox);

        topPanel.add(beveragesLbl);
        topPanel.add(beveragesComboBox);

    }

    private void setMiddlePanel()
    {
        middlePanel = new JPanel();
        middlePanel.setBackground(Color.gray);
        middlePanel.setBounds(0, 100, 600, 400);

        textArea = new JTextArea("Type here...", 26, 53);
        // TA1.setBounds(10,210,600,200);
        middlePanel.add(textArea);

    }

    private void setBottomPanel()
    {
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 500, 600, 100);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

    private void enterDataBtnClickedAction()
    {
        // TA1.setText(null);
        // userNameField.setText(null);
        // userNameField.setEditable(false);
        String userName = userNameTxt.getText().trim();
        String cerealsValue = String.valueOf(cerealsComboBox.getSelectedItem()).trim();
        String beveragesValue = String.valueOf(beveragesComboBox.getSelectedItem()).trim();

        String errorMsg = "";
        boolean validation = false;

        if (userName.equals(null) || userName.equals(""))
        {
            errorMsg = "Please Enter User Name!!!";
            validation = false;
        }
        else if (cerealsValue.equals(null) || cerealsValue.equals("null"))
        {
            errorMsg = "Please select Cereals!!!";
            validation = false;
        }
        else if (beveragesValue.equals(null) || beveragesValue.equals("null"))
        {
            errorMsg = "Please Enter beverages!!!";
            validation = false;
        }
        else
        {
            validation = true;
            JOptionPane optionPane = new JOptionPane("Data Inserted Successfully!!", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Conngratulation..");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }

        if (validation == false)
        {
            JOptionPane optionPane = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Data Validation");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }

        if (validation == true)
        {

            System.out.println("userName : " + userName + "::: Cereals :::" + cerealsValue + ":::Beverages :::"
                                       + beveragesValue);
        }
    }

    ProcessFoodGUI()
    {
        // String userName;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == quitBtn)
        {
            System.exit(0);
        }
        else if (e.getSource() == clearDisplayBtn)
        {
            textArea.setText(null);
            userNameTxt.setText(null);
            userNameTxt.setEditable(true);
            cerealsComboBox.setSelectedItem(null);
            beveragesComboBox.setSelectedItem(null);
        }
        else if (e.getSource() == enterDataBtn)
        {
            enterDataBtnClickedAction();
        }
        else if (e.getSource() == displayChoiceBtn)
        {

        }
    }

    public static void main(String args[])
    {

        String fileName = "ausAssignment3_data.csv";
        new DataFile(fileName);

        System.out.println("list created");
        for (ProcessedFood pf : foodList)
        {
            System.out.println(pf);
            System.out.println("done");
        }
        for (Nutrient nl : nutrientList)
        {
            System.out.println(nl);
            System.out.println("doneok");
        }
        ProcessFoodGUI processFoodGUI = new ProcessFoodGUI();
        processFoodGUI.intializeGUI();

    }
}