package ausAssignment.assignment3_1;

import java.util.List;

/*
 * Inherited class Item in this class
 */
public class ProcessedFood extends Item {

	private String brandName;
	private Integer serveSize;
	private String unit;
	private List<Nutrient> nutrient;

	public ProcessedFood() {
	}
/*
 * Parameterize constructor that includes parameters of super class Item as well
 */
	public ProcessedFood(String itemName, String category, String brandName, Integer serveSize, String unit,
			List<Nutrient> nutrient) {
		super(itemName, category);
		this.brandName = brandName;
		this.serveSize = serveSize;
		this.unit = unit;
		this.nutrient = nutrient;
	}
/*
 * Copy constructor to create copy of object
 */
	public ProcessedFood(ProcessedFood processedFood) {
		System.out.println("Copy Constructor called");
		brandName = processedFood.brandName;
		serveSize = processedFood.serveSize;
		unit = processedFood.unit;
		nutrient = processedFood.nutrient;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getServeSize() {
		return serveSize;
	}

	public void setServeSize(Integer serveSize) {
		this.serveSize = serveSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<Nutrient> getNutrient() {
		return nutrient;
	}

	public Float getSelectedNutrient(List<Nutrient> nutrientList,String nutrientName)
	{
		for(Nutrient nutrient:nutrientList)
		{
			if(nutrient.getNutrientName().equalsIgnoreCase(nutrientName))
			{
				return nutrient.getNutrientAmount();
			}
		}
		return 0.0f;
	}

	public String getSelectedNutrientInString(List<Nutrient> nutrientList,String nutrientName)
    {
        return String.format("%.2f",getSelectedNutrient(nutrientList,nutrientName));
    }

	public void setNutrient(List<Nutrient> nutrient) {
		this.nutrient = nutrient;
	}

	public String toString() {
		return "ProcessedFood [brandName=" + brandName + ", serveSize=" + serveSize + ", unit=" + unit + ", nutrient="
				+ nutrient + "]";
	}
}
