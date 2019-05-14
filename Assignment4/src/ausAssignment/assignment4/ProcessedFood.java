package ausAssignment.assignment4;

import java.util.Comparator;
import java.util.List;

/*
 * This class extends the Item class, and Inherited all properties of super
 * class in it. It also has additional property in it.
 */
public class ProcessedFood extends Item
{

    // declaration of variable and an ArrayList of Nutrient objects to store the
    // nutrient details.
    private Integer foodId;
    private String brandName;
    private Integer serveSize;
    private String serveUnit;
    private List<Nutrient> nutrient;

    // Default constructor
    public ProcessedFood()
    {
        super();
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

    // accessor and mutator methods
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

    public void setNutrient(List<Nutrient> nutrient)
    {
        this.nutrient = nutrient;
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

    // This method will give value of selected nutrient name
    public String getSelectedNutrientInString(List<Nutrient> nutrientList,
            String nutrientName)
    {
        return String.format("%.2f",
                getSelectedNutrient(nutrientList, nutrientName));
    }

    /*
     * comparator has been used to compare two records , we are comparing values
     * of sugar to sort data as per user preference High protien .
     */
    public static Comparator<ProcessedFood> sortByHighProtien = new Comparator<ProcessedFood>()
    {
        @Override
        public int compare(ProcessedFood o1, ProcessedFood o2)
        {
            Float o1Float = o1.getSelectedNutrient(o1.getNutrient(),
                    Nutrient.PROTEIN_GM);
            Float o2Float = o2.getSelectedNutrient(o2.getNutrient(),
                    Nutrient.PROTEIN_GM);

            if (o1Float > o2Float)
            {
                return -1;
            }
            if (o1Float < o2Float)
            {
                return 1;
            }
            return 0;
        }
    };

    /*
     * comparator has been used to compare two records , we are comparing values
     * of sugar to sort data as per user preference low suger .
     */
    public static Comparator<ProcessedFood> sortByLowSugar = new Comparator<ProcessedFood>()
    {
        @Override
        public int compare(ProcessedFood o1, ProcessedFood o2)
        {

            Float o1Float = o1.getSelectedNutrient(o1.getNutrient(),
                    Nutrient.SUGAR_GM);
            Float o2Float = o2.getSelectedNutrient(o2.getNutrient(),
                    Nutrient.SUGAR_GM);

            if (o1Float > o2Float)
            {
                return 1;
            }
            if (o1Float < o2Float)
            {
                return -1;
            }
            return 0;
        }
    };

    // toString() method of class.
    @Override
    public String toString()
    {
        return "ProcessedFood{" + "foodId=" + foodId + ", brandName='"
               + brandName + '\'' + ", serveSize=" + serveSize + ", serveUnit='"
               + serveUnit + '\'' + ", nutrient=" + nutrient + "} "
               + super.toString();
    }
}
