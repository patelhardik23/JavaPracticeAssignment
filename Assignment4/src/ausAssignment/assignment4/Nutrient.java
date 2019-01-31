package ausAssignment.assignment4;

/*
 * This class stores the nutrient name and nutrient amount.
 */
public class Nutrient
{
    /*
     * Constants has been declared to maintain the list of different types of
     * nutrients
     */
    public static final String ENERGY_KG = "energyKg";
    public static final String PROTEIN_GM = "proteinGm";
    public static final String FAT_GM = "fatGm";
    public static final String CARBOHYDRATE_GM = "carbohydrateGm";
    public static final String SUGAR_GM = "sugarGm";
    public static final String DIETARY_FIBRE = "dietaryFibre";
    public static final String SODIUM_MG = "sodiumMg";

    /*
     * variable declaration
     */
    private String nutrientName;
    private Float nutrientAmount;

    // Default constructor
    public Nutrient()
    {
    }

    // Parameterised constructor
    public Nutrient(String nutrientName, Float nutrientAmount)
    {
        super();
        this.nutrientName = nutrientName;
        this.nutrientAmount = nutrientAmount;
    }

    // Accessor and mutator methods for given variables
    public String getNutrientName()
    {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName)
    {
        this.nutrientName = nutrientName;
    }

    public Float getNutrientAmount()
    {
        return nutrientAmount;
    }

    public void setNutrientAmount(Float nutrientAmount)
    {
        this.nutrientAmount = nutrientAmount;
    }

    // to String method for class
    @Override
    public String toString()
    {
        return "Nutrient [nutrientName=" + nutrientName + ", nutrientAmount="
               + nutrientAmount + "]";
    }

}
