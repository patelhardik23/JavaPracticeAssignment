package ausAssignment.assignment3;

public class ProcessedFood extends Item{

	private String brandName;
	private int serveSize;
	private String unit;
	//private List nutrient;
	
	public ProcessedFood() {
	}
	
	public ProcessedFood(String name, String category, String brandName, int serveSize, String unit) {
		super(name,category);
		this.brandName = brandName;
		this.serveSize = serveSize;
		this.unit = unit;
	}
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	@Override
	public String toString() {
		return "ProcessedFood [brandName=" + brandName + ", serveSize=" + serveSize + ", unit=" + unit + "]";
	}

}
