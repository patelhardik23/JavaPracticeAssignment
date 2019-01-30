package ausAssignment.assignment4;

public class Nutrient
{
    public static final String ENERGY_KG ="energyKg";
    public static final String PROTEIN_GM = "proteinGm";
    public static final String FAT_GM = "fatGm";
    public static final String CARBOHYDRATE_GM = "carbohydrateGm";
    public static final String SUGAR_GM = "sugarGm";
    public static final String DIETARY_FIBRE = "dietaryFibre";
    public static final String SODIUM_MG = "sodiumMg";

    private String nutrientName;
    private Float nutrientAmount;

    public Nutrient()
    {
        // TODO Auto-generated constructor stub
    }

    public Nutrient(String nutrientName, Float nutrientAmount)
    {
        super();
        this.nutrientName = nutrientName;
        this.nutrientAmount = nutrientAmount;
    }

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

    @Override
    public String toString()
    {
        return "Nutrient [nutrientName=" + nutrientName + ", nutrientAmount="
               + nutrientAmount + "]";
    }

}
