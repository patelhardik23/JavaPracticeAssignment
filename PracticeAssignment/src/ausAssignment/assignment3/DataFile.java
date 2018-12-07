package ausAssignment.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile {

	public DataFile(String fileName) {
		super();
		// TODO Auto-generated constructor stub
		readBooksFromCSV(fileName);
		System.out.println("File Loaded in Object");
	}

	private static void readBooksFromCSV(String fileName) {

		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		List<ProcessedFood> itemList = new ArrayList<ProcessedFood>();
		List<Nutrient> nutrientList = new ArrayList<Nutrient>();
		try {

			// Reading the CSV file
			br = new BufferedReader(new FileReader(fileName));
			// Create list for holding data object

			String line = "";
			// Read to skip the header
			br.readLine();
			// Reading from the second line
			while ((line = br.readLine()) != null) {
				String[] productDetails = line.split(COMMA_DELIMITER);

				if (productDetails.length > 0) {
					// Save the product details in product object
					ProcessedFood itemInfo = new ProcessedFood(productDetails[0], productDetails[1], productDetails[2],
							Integer.parseInt(productDetails[3]), productDetails[4]);
					Nutrient nutrientInfo = new Nutrient(Integer.parseInt(productDetails[5]),
							Float.parseFloat(productDetails[6]), Float.parseFloat(productDetails[7]),
							Float.parseFloat(productDetails[8]), Float.parseFloat(productDetails[9]),
							Float.parseFloat(productDetails[10]), Float.parseFloat(productDetails[11]));
					itemList.add(itemInfo);

					nutrientList.add(nutrientInfo);
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