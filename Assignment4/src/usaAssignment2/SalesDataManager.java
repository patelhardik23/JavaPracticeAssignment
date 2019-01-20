package usaAssignment2;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesDataManager {
	static final int N = 3;

	static String[] salesPerson = new String[N];
	static int[] salesAmmount = new int[N];
	static double[] commissionRate = new double[N];

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		int choice = 0;
		do {

			System.out.println("Sales & Commissions Data Manager!!!");
			System.out.println("-----------------------------------");
			System.out.println("1. Input & validate data");
			System.out.println("2. Display");
			System.out.println("3. Sort by name");
			System.out.println("4. Sort by sales");
			System.out.println("5. Search by name");
			System.out.println("6. Search by sales");
			System.out.println("7. Display statistics");
			System.out.println("8. Exit");
			System.out.print("\n Enter you choice : ");
			choice = in.nextInt();

			switch (choice) {
			case 1:
				inputData();
				break;
			case 2:
				displayMenu();
				break;
			case 3:
				sortByName();
				displayMenu();
				break;
			case 4:
				sortBySales();
				displayMenu();
				break;
			case 5:
				System.out.print("Search by name ");
				searchByName();
				break;
			case 6:
				System.out.print("Search by sales ");
				searchBySales();
				break;
			case 7:
				displayStatistics();
				break;
			case 8:
				System.out.print("Student 123456789. Thnak you.");
				exitFromApplication();
				break;
			}
		} while (choice != 8);
		in.close();
	}

	public static void sortByName() {
		for (int i = 0; i < N - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < N; j++) {
				if (salesPerson[j].compareTo(salesPerson[min_idx]) <0) {
					min_idx = j;
				}
			}
			int temp = salesAmmount[min_idx];

			salesAmmount[min_idx] = salesAmmount[i];
			salesAmmount[i] = temp;

			double temp1 = commissionRate[min_idx];
			commissionRate[min_idx] = commissionRate[i];
			commissionRate[i] = temp1;

			String temp2 = salesPerson[min_idx];
			salesPerson[min_idx] = salesPerson[i];
			salesPerson[i] = temp2;

		}

	}

	public static void sortBySales() {
		for (int i = 0; i < N - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < N; j++) {
				if (salesAmmount[j] < salesAmmount[min_idx]) {
					min_idx = j;
				}
			}
			int temp = salesAmmount[min_idx];

			salesAmmount[min_idx] = salesAmmount[i];
			salesAmmount[i] = temp;

			double temp1 = commissionRate[min_idx];
			commissionRate[min_idx] = commissionRate[i];
			commissionRate[i] = temp1;

			String temp2 = salesPerson[min_idx];
			salesPerson[min_idx] = salesPerson[i];
			salesPerson[i] = temp2;

		}
	}

	public static void searchBySales() {
		int saleAmount;
		System.out.print("Please enter a sales Amount: ");
		saleAmount = in.nextInt();
		for (int i = 0; i < N; i++) {
			if (salesAmmount[i] == saleAmount)
				System.out.println(salesPerson[i] + "  $" + salesAmmount[i]);
		}
	}

	public static void searchByName() {
		String name;
		System.out.print("Please enter a sales person name: ");
		name = in.next();
		for (int i = 0; i < N; i++) {
			if (salesPerson[i].equals(name))
				System.out.println(name + " - sales amount: $"
						+ salesAmmount[i] + " With commission received:$"
						+ commissionRate[i]);
		}

	}

	public static void inputData() {
		String name;
		int amount;
		double commission;
		for (int i = 0; i < N; i++) {
			System.out
					.print("Please Enter name,then sales amount for a salesperson  "
							+ (i + 1) + " : ");
			name = in.next();
			salesPerson[i] = name;
			System.out.print("Please enter sales amount : ");
			amount = in.nextInt();
			salesAmmount[i] = amount;
			commission = calculateCommission(amount);
			commissionRate[i] = commission;

		}
	}

	public static void displayMenu() {
		System.out.println("Sales & Commissions Data Manager!!!");
		System.out
				.println("-------------------------------------------------------");
		System.out
				.println("-------------------------------------------------------");
		System.out.println("Salesperson			Sales Amount			Commissions");
		System.out
				.println("-------------------------------------------------------");

		for (int i = 0; i < N; i++) {
			System.out.println(salesPerson[i] + "			" + salesAmmount[i] + "			"
					+ commissionRate[i]);
		}

	}

	public static double calculateCommission(int salesAmount) {
		double commissionValue;
		if (salesAmount >= 10000 && salesAmount <= 25000) {
			commissionValue = (salesAmount * 5) / 100;
		} else if (salesAmount >= 25001 && salesAmount <= 50000) {
			commissionValue = (salesAmount * 6) / 100;
		} else if (salesAmount >= 50001 && salesAmount <= 75000) {
			commissionValue = (salesAmount * 8) / 100;
		} else {
			commissionValue = (salesAmount * 10) / 100;
		}
		return commissionValue;
	}

	public static void displayStatistics() {
		sortBySales();
		int medianIndex = 0, medianValue = 0;
		if (N % 2 == 0) {
			medianIndex = (N - 1) / 2;
			System.out.println("medianIndex:::" + medianIndex);
			medianValue = (salesAmmount[medianIndex] + salesAmmount[medianIndex + 1]) / 2;
			System.out.println("medianValue:::" + medianValue);
		} else {
			medianIndex = N / 2;
			System.out.println("medianIndex odd:::" + medianIndex);
			medianValue = salesAmmount[medianIndex];
			System.out.println("medianValue odd:::" + medianValue);
		}
		System.out.println("A simple statistics");
		System.out.println("===================");
		System.out.println("The sales person has lowest sales amount is "
				+ salesPerson[0] + ", $" + salesAmmount[0]);
		System.out.println("The sales person has highest sales amount is "
				+ salesPerson[N - 1] + ", $" + salesAmmount[N - 1]);
		System.out.println("The median sales amount is $" + medianValue);

	}

	private static void exitFromApplication() {
		System.out.println("Exit from the application");
		System.exit(0);

	}

	/*
	 * 
	 * public SalesDataManager(){ //constructor }
	 * 
	 * 
	 * 
	 * 
	 * public boolean isValidName(String name){ return true; }
	 * 
	 * //helper methods here if any
	 */
}
