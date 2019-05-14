package ausAssignment.assignment3_1;

public class Item {
	private String name;
	private String category;

	public Item() {
		super();
	}
	
	public Item(String name, String category) {
		
		this.name = name;
		this.category = category;
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

	public String toString() {
		return "Item [name=" + name + ", category=" + category + "]";
	}

}
