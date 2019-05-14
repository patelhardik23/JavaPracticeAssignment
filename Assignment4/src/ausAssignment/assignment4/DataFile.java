package ausAssignment.assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * This class reads data from the given csv file and stores the records in
 * database.
 */
public class DataFile
{
    /*
     * parameterised Constructor that will take file name as parameter and list
     * to read and load data.
     */
    public DataFile(String fileName) throws SQLException
    {
        super();
        readDataFromCSV(fileName);
        System.out.println("File Loaded in Object");
    }

    /*
     * This method read and load data from CSV and store it in database. If
     * there are any issues then it will throw an exception that has been
     * handled with try catch
     */
    private static void readDataFromCSV(String fileName) throws SQLException
    {
        List<ProcessedFood> itemList = new ArrayList<>();
        final String COMMA_DELIMITER = ",";
        BufferedReader br = null;

        // Exception handling
        try
        {
            // Reading the CSV file
            br = new BufferedReader(new FileReader(fileName));

            String line = "";

            // Read to skip the header
            br.readLine();

            // Reading from the second line
            while ((line = br.readLine()) != null)
            {
                String[] foodItemDetails = line.split(COMMA_DELIMITER);

                if (foodItemDetails.length > 0)
                {
                    // Save the product details in product object
                    String itemName = foodItemDetails[0].trim();
                    String category = foodItemDetails[1].trim();
                    String brand = foodItemDetails[2].trim();
                    Integer serveSize = Integer
                            .parseInt(foodItemDetails[3].trim());
                    String unit = foodItemDetails[4].trim();

                    List<Nutrient> nutrients = new ArrayList<>();

                    nutrients.add(new Nutrient(Nutrient.ENERGY_KG,
                            Float.parseFloat(foodItemDetails[5].trim())));
                    nutrients.add(new Nutrient(Nutrient.PROTEIN_GM,
                            Float.parseFloat(foodItemDetails[6].trim())));
                    nutrients.add(new Nutrient(Nutrient.FAT_GM,
                            Float.parseFloat(foodItemDetails[7].trim())));
                    nutrients.add(new Nutrient(Nutrient.CARBOHYDRATE_GM,
                            Float.parseFloat(foodItemDetails[8].trim())));
                    nutrients.add(new Nutrient(Nutrient.SUGAR_GM,
                            Float.parseFloat(foodItemDetails[9].trim())));
                    nutrients.add(new Nutrient(Nutrient.DIETARY_FIBRE,
                            Float.parseFloat(foodItemDetails[10].trim())));
                    nutrients.add(new Nutrient(Nutrient.SODIUM_MG,
                            Float.parseFloat(foodItemDetails[11].trim())));

                    ProcessedFood itemInfo = new ProcessedFood(itemName,
                            category, brand, serveSize, unit, nutrients);

                    itemList.add(itemInfo);
                }
            }

            DatabaseUtility dbConn = new DatabaseUtility();
            dbConn.insertDataInProcessedFood(itemList);
        }
        // If file not found on given path then it will give below exception
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        // If there are an issues while reading data then it will give below
        // exception
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // If there are any database related issue then it will give below
        // exception
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        /*
         * This block will be executed after try or catch
         */
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException ie)
            {
                System.out.println(
                        "Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
    }
}
