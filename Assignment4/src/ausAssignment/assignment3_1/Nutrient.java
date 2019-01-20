package ausAssignment.assignment3_1;

public class Nutrient {
	private String name;
	private Float quantity;

	public Nutrient(String name, Float quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Nutrient [name=" + name + ", quantity=" + quantity + "]";
	}

}
