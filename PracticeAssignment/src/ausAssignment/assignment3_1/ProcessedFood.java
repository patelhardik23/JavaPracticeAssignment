package ausAssignment.assignment3_1;

import java.util.List;

/*
 * Inherited class Item in this class
 */
public class ProcessedFood extends Item {

	private String nameOfBrand;
	private Integer servingSize;
	private String servingUnit;
	private List<Nutrient> listOfNutrient;

	public ProcessedFood() {
	}

	/*
	 * Parameterize constructor that includes parameters of super class Item as well
	 */
	public ProcessedFood(String name, String category, String nameOfBrand, Integer servingSize, String servingUnit,
			List<Nutrient> listOfNutrient) {
		super(name, category);
		this.nameOfBrand = nameOfBrand;
		this.servingSize = servingSize;
		this.servingUnit = servingUnit;
		this.listOfNutrient = listOfNutrient;
	}

	/*
	 * Copy constructor to create copy of object
	 */
	public ProcessedFood(ProcessedFood processedFood) {
		System.out.println("Copy Constructor called");
		nameOfBrand = processedFood.nameOfBrand;
		servingSize = processedFood.servingSize;
		servingUnit = processedFood.servingUnit;
		listOfNutrient = processedFood.listOfNutrient;
	}

	public String getBrandName() {
		return nameOfBrand;
	}

	public void setBrandName(String brandName) {
		this.nameOfBrand = brandName;
	}

	public Integer getServeSize() {
		return servingSize;
	}

	public void setServeSize(Integer serveSize) {
		this.servingSize = serveSize;
	}

	public String getUnit() {
		return servingUnit;
	}

	public void setUnit(String unit) {
		this.servingUnit = unit;
	}

	public List<Nutrient> getNutrient() {
		return listOfNutrient;
	}

	public Float getSingleNutrientFromList(List<Nutrient> nutrientList, String nutrientName) {
		Float data = 0.0f;
		for (Nutrient nutrient : nutrientList) {
			if (nutrient.getName().equalsIgnoreCase(nutrientName)) {
				data = nutrient.getQuantity();
			}
		}
		return data;
	}

	public void setNutrient(List<Nutrient> nutrient) {
		this.listOfNutrient = nutrient;
	}

	public String toString() {
		return "ProcessedFood [brandName=" + nameOfBrand + ", serveSize=" + servingSize + ", unit=" + servingUnit
				+ ", nutrient=" + listOfNutrient + "]";
	}
}
