package ausAssignment.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile
{

    public DataFile(String fileName)
    {
        super();
        // TODO Auto-generated constructor stub
        readBooksFromCSV(fileName);
        System.out.println("File Loaded in Object");
    }

    private static void readBooksFromCSV(String fileName)
    {

        final String COMMA_DELIMITER = ",";
        BufferedReader br = null;
        List<ProcessedFood> itemList = new ArrayList<ProcessedFood>();
//		List<Nutrient> nutrientList = new ArrayList<Nutrient>();
        try
        {

            // Reading the CSV file
            br = new BufferedReader(new FileReader(fileName));
            // Create list for holding data object

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
                    String itemName = productDetails[0];
                    String category = productDetails[1];
                    String brand = productDetails[2];
                    Integer serveSize = Integer.parseInt(productDetails[3]);
                    String unit = productDetails[4];

                    List<Nutrient> nutrients = new ArrayList<>();

                    nutrients.add(new Nutrient("energyKg", Float.parseFloat(productDetails[5])));
                    nutrients.add(new Nutrient("proteinGm", Float.parseFloat(productDetails[6])));
                    nutrients.add(new Nutrient("fatGm", Float.parseFloat(productDetails[7])));
                    nutrients.add(new Nutrient("carbohydrateGm", Float.parseFloat(productDetails[8])));
                    nutrients.add(new Nutrient("sugarGm", Float.parseFloat(productDetails[9])));
                    nutrients.add(new Nutrient("dietaryFibre", Float.parseFloat(productDetails[10])));
                    nutrients.add(new Nutrient("sodiumMg", Float.parseFloat(productDetails[11])));

                    ProcessedFood itemInfo = new ProcessedFood(itemName, category, brand, serveSize, unit, nutrients);

				/*	Nutrient nutrientInfo = new Nutrient(Integer.parseInt(productDetails[5]),
							Float.parseFloat(productDetails[6]), Float.parseFloat(productDetails[7]),
							Float.parseFloat(productDetails[8]), Float.parseFloat(productDetails[9]),
							Float.parseFloat(productDetails[10]), Float.parseFloat(productDetails[11]));
					nutrientList.add(nutrientInfo);
				*/
                    itemList.add(itemInfo);
                }
            }
		/*System.out.println("=================================================================");
			for (ProcessedFood df : itemList) {
				System.out.println(df);
			}
			System.out.println("=================================================================");
			for (Nutrient nl : nutrientList) {
				System.out.println(nl);
			}
			System.out.println("=================================================================");
		*/
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
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
    }
}