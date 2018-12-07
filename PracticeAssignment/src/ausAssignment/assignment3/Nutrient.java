package ausAssignment.assignment3;

public class Nutrient {
	private int energy;
	private float protin;
	private float fat;
	private float carbohydrate;
	private float sugar;
	private float dietaryFiber;
	private float sodium;

	public Nutrient(int energy, float protin, float fat, float carbohydrate, float sugar, float dietaryFiber,
			float sodium) {
		super();
		this.energy = energy;
		this.protin = protin;
		this.fat = fat;
		this.carbohydrate = carbohydrate;
		this.sugar = sugar;
		this.dietaryFiber = dietaryFiber;
		this.sodium = sodium;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public float getProtin() {
		return protin;
	}

	public void setProtin(float protin) {
		this.protin = protin;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public float getSugar() {
		return sugar;
	}

	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	public float getDietaryFiber() {
		return dietaryFiber;
	}

	public void setDietaryFiber(float dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
	}

	public float getSodium() {
		return sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	@Override
	public String toString() {
		return "Nutrient [energy=" + energy + ", protin=" + protin + ", fat=" + fat + ", carbohydrate=" + carbohydrate
				+ ", sugar=" + sugar + ", dietaryFiber=" + dietaryFiber + ", sodium=" + sodium + "]";
	}

}
