package ausAssignment.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataFile
{

    /*
     * Default Constructor that will take file name as parameter and list to
     * store data.
     */
    public DataFile(String fileName, List<ProcessedFood> itemList)
            throws SQLException
    {
        super();
        // TODO Auto-generated constructor stub
        readBooksFromCSV(fileName, itemList);
        System.out.println("File Loaded in Object");
    }

    private static void readBooksFromCSV(String fileName,
                                         List<ProcessedFood> itemList) throws SQLException
    {

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
                String[] productDetails = line.split(COMMA_DELIMITER);

                if (productDetails.length > 0)
                {
                    // Save the product details in product object
                    String itemName = productDetails[0].trim();
                    String category = productDetails[1].trim();
                    String brand = productDetails[2].trim();
                    Integer serveSize = Integer
                            .parseInt(productDetails[3].trim());
                    String unit = productDetails[4].trim();

                    List<Nutrient> nutrients = new ArrayList<>();

                    nutrients.add(new Nutrient(Nutrient.ENERGY_KG,
                                               Float.parseFloat(productDetails[5].trim())));
                    nutrients.add(new Nutrient(Nutrient.PROTEIN_GM,
                                               Float.parseFloat(productDetails[6].trim())));
                    nutrients.add(new Nutrient(Nutrient.FAT_GM,
                                               Float.parseFloat(productDetails[7].trim())));
                    nutrients.add(new Nutrient(
                            Nutrient.CARBOHYDRATE_GM,
                            Float.parseFloat(productDetails[8].trim())));
                    nutrients.add(new Nutrient(Nutrient.SUGAR_GM,
                                               Float.parseFloat(productDetails[9].trim())));
                    nutrients.add(new Nutrient(
                            Nutrient.DIETARY_FIBRE,
                            Float.parseFloat(productDetails[10].trim())));
                    nutrients.add(new Nutrient(Nutrient.SODIUM_MG,
                                               Float.parseFloat(productDetails[11].trim())));

                    ProcessedFood itemInfo = new ProcessedFood(itemName,
                                                               category, brand, serveSize, unit, nutrients);

                    itemList.add(itemInfo);
                }
            }

            DatabaseUtility dbConn = new DatabaseUtility();
            dbConn.insertDataInProcessedFood(itemList);

        }
        catch (IOException e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
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
