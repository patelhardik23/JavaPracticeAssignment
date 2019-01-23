package ausAssignment.assignment3;

import java.util.List;

/*
 * Inherited class Item in this class
 */
public class ProcessedFood extends Item
{

    private Integer foodId;
    private String brandName;
    private Integer serveSize;
    private String serveUnit;
    private List<Nutrient> nutrient;

    public ProcessedFood()
    {
    }

    /*
     * Parameterize constructor that includes parameters of super class Item as
     * well
     */
    public ProcessedFood(String itemName, String category, String brandName,
            Integer serveSize, String unit, List<Nutrient> nutrient)
    {
        super(itemName, category);
        this.brandName = brandName;
        this.serveSize = serveSize;
        this.serveUnit = unit;
        this.nutrient = nutrient;
    }

    /*
     * Copy constructor to create copy of object
     */
    public ProcessedFood(ProcessedFood processedFood)
    {
        System.out.println("Copy Constructor called");
        brandName = processedFood.brandName;
        serveSize = processedFood.serveSize;
        serveUnit = processedFood.serveUnit;
        nutrient = processedFood.nutrient;
    }

    public Integer getFoodId()
    {
        return foodId;
    }

    public void setFoodId(Integer foodId)
    {
        this.foodId = foodId;
    }

    public String getServeUnit()
    {
        return serveUnit;
    }

    public void setServeUnit(String serveUnit)
    {
        this.serveUnit = serveUnit;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public Integer getServeSize()
    {
        return serveSize;
    }

    public void setServeSize(Integer serveSize)
    {
        this.serveSize = serveSize;
    }

    public List<Nutrient> getNutrient()
    {
        return nutrient;
    }

    public Float getSelectedNutrient(List<Nutrient> nutrientList,
            String nutrientName)
    {
        for (Nutrient nutrient : nutrientList)
        {
            if (nutrient.getNutrientName().equalsIgnoreCase(nutrientName))
            {
                return nutrient.getNutrientAmount();
            }
        }
        return 0.0f;
    }

    public String getSelectedNutrientInString(List<Nutrient> nutrientList,
            String nutrientName)
    {
        return String.format("%.2f",
                getSelectedNutrient(nutrientList, nutrientName));
    }

    public void setNutrient(List<Nutrient> nutrient)
    {
        this.nutrient = nutrient;
    }

    @Override
    public String toString()
    {
        return "ProcessedFood [foodId=" + foodId + ", brandName=" + brandName
               + ", serveSize=" + serveSize + ", serveUnit=" + serveUnit
               + ", nutrient=" + nutrient + "]";
    }

}
