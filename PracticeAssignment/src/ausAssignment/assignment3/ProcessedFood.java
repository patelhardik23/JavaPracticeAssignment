package ausAssignment.assignment3;

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

	public ProcessedFood(String itemName,String category,String brandName, Integer serveSize, String unit, List<Nutrient> nutrient) {
		super(itemName,category);
		this.brandName = brandName;
		this.serveSize = serveSize;
		this.unit = unit;
		this.nutrient = nutrient;
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

	public void setNutrient(List<Nutrient> nutrient) {
		this.nutrient = nutrient;
	}

	@Override
	public String toString() {
		return "ProcessedFood [brandName=" + brandName + ", serveSize=" + serveSize + ", unit=" + unit + ", nutrient="
				+ nutrient + "]";
	}
}
