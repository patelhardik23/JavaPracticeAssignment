package ausAssignment.assignment1;

import java.util.Scanner;

public class LawnTest 
{
	public static void main(String[] args)
	{	
		Scanner in = new Scanner(System.in);
		int choice;
		do
		{
			Lawn lawn = new Lawn();
			int highFeeNumber = 0;
			double lawnArea, lawnFee, totalFee = 0;
			
			System.out.println("welcome to Lawn Mowing Weekly Fee Report System!!!");
			System.out.println("Press 1 to calculate.");
			System.out.println("Press 2 to exit.");
			System.out.print("Please enter your choice : ");
			
			choice = in.nextInt();
			
			System.out.println();
			
			switch(choice)
			{
				case 1: 
					System.out.print("Enter the number of Lawns : ");
					
					int N = in.nextInt();
					
					for (int i =1 ; i<=N ; i++)
					{	
						System.out.print("Enter the area for lawn " + i + " : ");
						lawnArea = in.nextDouble();
						
						System.out.print("The weekly fee for lawn " + i + " is $");
						lawnFee = lawn.lawnMowingFee(lawnArea);
						
						System.out.print(lawnFee);
						System.out.println("\n");
						
						totalFee += lawnFee;
						
						highFeeNumber = lawn.lawnNumber(lawnFee);
					}
						
					System.out.println("-------------------Report-------------------");
					System.out.println("Total weekly lawn mowing fee: $"+totalFee);
					System.out.println("Lowest weekly lawn mowing fee: $"+lawn.lowFees);
					System.out.println("Highest weekly lawn mowing fee: $"+lawn.highFees);
					System.out.println("Average weekly lawn mowing fee: $"+(totalFee/N));
					System.out.println("Lawn number with highest monthly fee: "+highFeeNumber);
					System.out.println("--------------------------------------------\n");
					break;
					
				case 2:
					System.out.println("Thank you for using System. Good Bye!!!");
					break;
					
				default:
					System.out.println("Please choose valid option!!! \n");
			}
			
		}while(choice != 2);
		
		in.close();
	}
}