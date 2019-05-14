package Assignment1Ext;
public class Calculator
{
    double mapvalue = 0;
    String catogary = "";

    public Calculator()
    {
        //default constructor
    }

    public double value(double sbp, double dbp)
    {
        mapvalue = 1.0 / 3.0 * sbp + 2.0 / 3.0 * dbp;
        return mapvalue;
    }

    public String category(double map)
    {
        // return one of “high”, “normal” or “low”
        if (map > 70 && map < 100)
        {
            catogary = "Normal";
        }
        else if (map < 70)
        {
            catogary = "Low";
        }
        else
        {
            catogary = "High";
        }
        return catogary;
    }

}
