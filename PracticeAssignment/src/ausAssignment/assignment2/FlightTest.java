package ausAssignment.assignment2;

import java.util.Scanner;

public class FlightTest {

	/*
	Method to Read, validate and store data for N flights
	Method to Calculate and store discounted price for all flights
	Method to Display all flights
	Method to Search a flight by number
	Method to Display all flights with the lowest flight price
	Method to Sort and display sorted flights
	Method to Exit from the application*/
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		final int N = 9;
		String flightDepartCity = new String();
		int [] flightNo = new int[N];
		int [] flightDistance = new int[N];
		double flightPrice = 0, discountedFlightPrice =0;
		int choice =0; 
		do{
			
			System.out.println("Welcome to Flight Management System. Enter your choice from below listed options!!!");
			System.out.println("1. Read, validate and store data for N flights");
			System.out.println("2. Calculate and store discounted price for all flights");
			System.out.println("3. Display all flights");
			System.out.println("4. Search a flight by number");
			System.out.println("5. Display all flights with the lowest flight price");
			System.out.println("6. Sort and display sorted flights");
			System.out.println("7. Exit from the application");
			choice = in.nextInt();
			
			switch(choice){
			case 1:
				System.out.print("Read ");
				break;
			case 2:
				System.out.print("Calculate ");
				break;
			case 3:
				System.out.print("Display ");
				break;
			case 4:
				System.out.print("Search ");
				break;
			case 5:
				System.out.print("Display lowest price ");
				break;
			case 6:
				System.out.print("Sort and display ");
				break;
			case 7:
				System.out.print("exit");
				break;
			}
		}while(choice != 7);		
	}
}
