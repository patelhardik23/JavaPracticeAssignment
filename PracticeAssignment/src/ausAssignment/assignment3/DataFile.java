package ausAssignment.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile {

	/*
	 * Default Constructor that will take file name as parameter and list to store data.
	 */
	public DataFile(String fileName, List<ProcessedFood> itemList) {
		super();
		// TODO Auto-generated constructor stub
		readBooksFromCSV(fileName, itemList);
		System.out.println("File Loaded in Object");
	}

	private static void readBooksFromCSV(String fileName, List<ProcessedFood> itemList) {

		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;

		// Exception handling
		try {

			// Reading the CSV file
			br = new BufferedReader(new FileReader(fileName));

			String line = "";

			// Read to skip the header
			br.readLine();

			// Reading from the second line
			while ((line = br.readLine()) != null) {
				String[] productDetails = line.split(COMMA_DELIMITER);

				if (productDetails.length > 0) {
					// Save the product details in product object
					String itemName = productDetails[0].trim();
					String category = productDetails[1].trim();
					String brand = productDetails[2].trim();
					Integer serveSize = Integer.parseInt(productDetails[3].trim());
					String unit = productDetails[4].trim();

					List<Nutrient> nutrients = new ArrayList<>();

					nutrients.add(new Nutrient("energyKg", Float.parseFloat(productDetails[5].trim())));
					nutrients.add(new Nutrient("proteinGm", Float.parseFloat(productDetails[6].trim())));
					nutrients.add(new Nutrient("fatGm", Float.parseFloat(productDetails[7].trim())));
					nutrients.add(new Nutrient("carbohydrateGm", Float.parseFloat(productDetails[8].trim())));
					nutrients.add(new Nutrient("sugarGm", Float.parseFloat(productDetails[9].trim())));
					nutrients.add(new Nutrient("dietaryFibre", Float.parseFloat(productDetails[10].trim())));
					nutrients.add(new Nutrient("sodiumMg", Float.parseFloat(productDetails[11].trim())));

					ProcessedFood itemInfo = new ProcessedFood(itemName, category, brand, serveSize, unit, nutrients);

					itemList.add(itemInfo);
				}
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException ie) {
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}
	}
}