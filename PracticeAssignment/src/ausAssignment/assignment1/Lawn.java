package ausAssignment.assignment1;

public class Lawn {
	//defined member variable.  
	double highFees,lowFees;
	int lawnNumber, count;
	public Lawn()
	{
		//default constructor will initialize the variables.
		highFees = Double.MIN_VALUE;
		lowFees = Double.MAX_VALUE;
		count = 1;
	}
	public double lawnMowingFee(double area)
	{
		//This method will calculate lawn mowing fees
		
		double weeklyFee = 0;
		if(area < 500)
		{
			weeklyFee = 40;
		}
		else if(area >= 500 && area < 900)
		{
			weeklyFee = 60;
		}
		else if(area >= 900 && area < 1500)
		{
			weeklyFee = 75;
		}
		else if (area >= 1500)
		{
			weeklyFee = 100;
		}
		return weeklyFee;
	}
	public int lawnNumber(double fee)
	{
		//This method will calculate highest fees and lowest fees and find highest fees lawn number.
		if(fee > highFees)
		{
			highFees = fee;
			lawnNumber = count;
		}
		if(fee < lowFees)
		{
			lowFees = fee;
		}
		count +=1;
		return lawnNumber;		
	}
}