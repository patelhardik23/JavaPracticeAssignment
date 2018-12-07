package ausAssignment.assignment3;

public class Nutrient {
	private String nutrientName;
	private Float nutrientAmount;

	public Nutrient(String nutrientName, Float nutrientAmount) {
		super();
		this.nutrientName = nutrientName;
		this.nutrientAmount = nutrientAmount;
	}

	public String getNutrientName() {
		return nutrientName;
	}

	public void setNutrientName(String nutrientName) {
		this.nutrientName = nutrientName;
	}

	public Float getNutrientAmount() {
		return nutrientAmount;
	}

	public void setNutrientAmount(Float nutrientAmount) {
		this.nutrientAmount = nutrientAmount;
	}

	@Override
	public String toString() {
		return "Nutrient [nutrientName=" + nutrientName + ", nutrientAmount=" + nutrientAmount + "]";
	}

}
