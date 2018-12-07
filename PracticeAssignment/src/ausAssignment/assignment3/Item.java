package ausAssignment.assignment3;

public class Item {
	private String itemName;
	private String category;

	public Item() {
		super();
	}
	public Item(String name, String category) {
		
		this.itemName = name;
		this.category = category;
	}

	public String getName() {
		return itemName;
	}

	public void setName(String name) {
		this.itemName = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [name=" + itemName + ", category=" + category + "]";
	}

}
