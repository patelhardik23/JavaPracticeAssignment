import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFile {

	public DataFile(String fileName) {
		super();
		// TODO Auto-generated constructor stub
		List<DataFileBean> dataFile = readBooksFromCSV(fileName);
	/*	for (DataFileBean df : dataFile) {
			System.out.println(df);
		}*/
		System.out.println("File Loaded in Object");
	}

	/*public static void main(String... args) {

		String fileName = "/home/hardikpatel/TEST/Assignment_1_data.csv";
		new DataFile(fileName);

	}*/

	private static List<DataFileBean> readBooksFromCSV(String fileName) {

		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		List<DataFileBean> dataList = new ArrayList<DataFileBean>();
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
					DataFileBean prodInfo = new DataFileBean(productDetails[0], productDetails[1], productDetails[2],
							Integer.parseInt(productDetails[3]), productDetails[4], Integer.parseInt(productDetails[5]),
							Float.parseFloat(productDetails[6]), Float.parseFloat(productDetails[7]),
							Float.parseFloat(productDetails[8]), Float.parseFloat(productDetails[9]),
							Float.parseFloat(productDetails[10]), Float.parseFloat(productDetails[11]));

					dataList.add(prodInfo);
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

		return dataList;
	}
}

class DataFileBean {
	private String name;
	private String category;
	private String brand;
	private int serveSize;
	private String unit;
	private int energyKg;
	private float protinGm;
	private float fatGm;
	private float carbohydrateGm;
	private float sugarGm;
	private float dietaryFiber;
	private float sodiumMg;

	public DataFileBean(String name, String category, String brand, int serveSize, String unit, int energyKg,
			float protinGm, float fatGm, float carbohydrateGm, float sugarGm, float dietaryFiber, float sodiumMg) {
		super();
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.serveSize = serveSize;
		this.unit = unit;
		this.energyKg = energyKg;
		this.protinGm = protinGm;
		this.fatGm = fatGm;
		this.carbohydrateGm = carbohydrateGm;
		this.sugarGm = sugarGm;
		this.dietaryFiber = dietaryFiber;
		this.sodiumMg = sodiumMg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getServeSize() {
		return serveSize;
	}

	public void setServeSize(int serveSize) {
		this.serveSize = serveSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getEnergyKg() {
		return energyKg;
	}

	public void setEnergyKg(int energyKg) {
		this.energyKg = energyKg;
	}

	public float getProtinGm() {
		return protinGm;
	}

	public void setProtinGm(float protinGm) {
		this.protinGm = protinGm;
	}

	public float getFatGm() {
		return fatGm;
	}

	public void setFatGm(float fatGm) {
		this.fatGm = fatGm;
	}

	public float getCarbohydrateGm() {
		return carbohydrateGm;
	}

	public void setCarbohydrateGm(float carbohydrateGm) {
		this.carbohydrateGm = carbohydrateGm;
	}

	public float getSugarGm() {
		return sugarGm;
	}

	public void setSugarGm(float sugarGm) {
		this.sugarGm = sugarGm;
	}

	public float getDietaryFiber() {
		return dietaryFiber;
	}

	public void setDietaryFiber(float dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
	}

	public float getSodiumMg() {
		return sodiumMg;
	}

	public void setSodiumMg(float sodiumMg) {
		this.sodiumMg = sodiumMg;
	}

	@Override
	public String toString() {
		return "DataFileBean [name=" + name + ", category=" + category + ", brand=" + brand + ", serveSize=" + serveSize
				+ ", unit=" + unit + ", energyKg=" + energyKg + ", protinGm=" + protinGm + ", fatGm=" + fatGm
				+ ", carbohydrateGm=" + carbohydrateGm + ", sugarGm=" + sugarGm + ", dietaryFiber=" + dietaryFiber
				+ ", sodiumMg=" + sodiumMg + "]";
	}
}