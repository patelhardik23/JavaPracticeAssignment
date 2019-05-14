package ausAssignment.assignment4;

/*
 * This is a generic class that stores the food item name and its category.
 */
public class Item
{
    // Filed to to store item name and category
    private String itemName;
    private String category;

    // Default constructor
    public Item()
    {
        super();
    }

    // Parameterised constructor
    public Item(String name, String category)
    {

        this.itemName = name;
        this.category = category;
    }

    // accessor and mutator methods
    public String getName()
    {
        return itemName;
    }

    public void setName(String name)
    {
        this.itemName = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    // toString method that return object in string format
    public String toString()
    {
        return "Item [name=" + itemName + ", category=" + category + "]";
    }
}
