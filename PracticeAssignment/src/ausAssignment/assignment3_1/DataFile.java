package ausAssignment.assignment3_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile {

	public static final String ENERGY = "energy";
	public static final String PROTEIN = "protein";
	public static final String FAT = "fat";
	public static final String CARBOHYDRATE = "carbohydrate";
	public static final String SUGAR = "sugar";
	public static final String DIETARY_FIBER = "dietary_fibre";
	public static final String SODIUM = "sodium";

	/*
	 * Default Constructor that will take file name as parameter and list to store
	 * data.
	 */
	public DataFile(String fileName, List<ProcessedFood> itemList) {
		super();
		getDataFromFile(fileName, itemList);
	}

	private static void getDataFromFile(String fileName, List<ProcessedFood> processedFoodList) {

		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String s = "";
			bufferedReader.readLine();
			while ((s = bufferedReader.readLine()) != null) {
				String[] productDetails = s.split(",");
				if (productDetails.length > 0) {
					String foodItemName = productDetails[0].trim();
					String foodCategory = productDetails[1].trim();
					String foodBrand = productDetails[2].trim();
					Integer serveSizeOfFood = Integer.parseInt(productDetails[3].trim());
					String servingUnit = productDetails[4].trim();
					List<Nutrient> listOfNutrients = new ArrayList<>();
					listOfNutrients.add(new Nutrient(ENERGY, Float.parseFloat(productDetails[5].trim())));
					listOfNutrients.add(new Nutrient(PROTEIN, Float.parseFloat(productDetails[6].trim())));
					listOfNutrients.add(new Nutrient(FAT, Float.parseFloat(productDetails[7].trim())));
					listOfNutrients.add(new Nutrient(CARBOHYDRATE, Float.parseFloat(productDetails[8].trim())));
					listOfNutrients.add(new Nutrient(SUGAR, Float.parseFloat(productDetails[9].trim())));
					listOfNutrients.add(new Nutrient(DIETARY_FIBER, Float.parseFloat(productDetails[10].trim())));
					listOfNutrients.add(new Nutrient(SODIUM, Float.parseFloat(productDetails[11].trim())));
					ProcessedFood foodItemList = new ProcessedFood(foodItemName, foodCategory, foodBrand,
							serveSizeOfFood, servingUnit, listOfNutrients);
					processedFoodList.add(foodItemList);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException ie) {
				System.out.println("Exception on buffer close");
				ie.printStackTrace();
			}
		}
	}
}