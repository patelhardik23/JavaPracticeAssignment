package ausAssignment.assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FlightTest {

	static List<Flight> flights;

	static final int N = 9;

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		int choice = 0;
		do {

			System.out
					.println("\n----------------------------------------------------------------------------------");
			System.out
					.println("Welcome to Flight Management System. Enter your choice from below listed options!!!");
			System.out
					.println("1. Read, validate and store data for N flights");
			System.out
					.println("2. Calculate and store discounted price for all flights");
			System.out.println("3. Display all flights");
			System.out.println("4. Search a flight by number");
			System.out
					.println("5. Display all flights with the lowest flight price");
			System.out.println("6. Sort and display sorted flights");
			System.out.println("7. Exit from the application");
			System.out.print("Enter you choice : ");
			choice = in.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Read ");
				flights = new ArrayList<Flight>();
				//writeFlightData();
				 readFlightData();
				break;
			case 2:
				System.out.print("Enter Discount % on Flight : ");
				double discountPercentage = in.nextDouble();
				calculateDiscountedPrice(discountPercentage);
				break;
			case 3:
				displayFlightData();
				break;
			case 4:
				System.out.print("Search ");
				System.out.print("Enter Flight Number : ");
				int flightNumber = in.nextInt();
				if (searchFlightByNumber(flightNumber) == false)
					System.out.println("flight is not found");
				break;
			case 5:
				System.out.print("Display lowest price ");
				displayLowestFlightPrice();
				break;
			case 6:
				System.out.print("Sort and display ");
				displaySortedFlights();
				break;
			case 7:
				System.out.print("Student 123456789. Thnak you.");
				exitFromApplication();
				break;
			}
		} while (choice != 7);
		in.close();
	}

	private static void exitFromApplication() {
		// TODO Auto-generated method stub
		System.out.println("Exit from the application");
		System.exit(0);

	}

	private static void displayLowestFlightPrice() {
		System.out.println("Flights sorted by Departure city!!");

		Collections.sort(flights, Flight.priceComparator);

		System.out
				.println("Flight Departure City | Flight Number | Flight Distance | Flight Price | Discounted Flight Price ");

		for (int i = 0; i < flights.size(); i++) {
			Flight f = flights.get(i);
			double minvalue = flights.get(0).getFlightPrice();
			if (minvalue == f.getFlightPrice()) {
				System.out.println(f.getFlightDepartCity() + " | "
						+ f.getFlightNo() + " | " + f.getFlightDistance() + "|"
						+ f.getFlightPrice() + "|"
						+ f.getDiscountedFlightPrice());
			}
		}
	}

	private static void displaySortedFlights() {
		System.out.println("Flights sorted by Departure city!!");

		// Collections.sort(flights, Flight.nameComparator);
		int n = flights.size();

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((flights.get(i).getFlightDepartCity()).compareTo(flights
						.get(j).getFlightDepartCity()) < 0) {
					Flight temp = flights.get(i);
					flights.add(i, flights.get(j));
					flights.remove(i + 1);
					flights.add(j, temp);
					flights.remove(j + 1);
				}
			}
		}

		System.out
				.println("Flight Departure City | Flight Number | Flight Distance | Flight Price | Discounted Flight Price ");

		for (Flight f : flights) {
			System.out.println(f.getFlightDepartCity() + " | "
					+ f.getFlightNo() + " | " + f.getFlightDistance() + "|"
					+ f.getFlightPrice() + "|" + f.getDiscountedFlightPrice());
		}
	}

	private static void calculateDiscountedPrice(double discountPercentage) {
		double discountedPrice;
		for (int i = 0; i < N; i++) {
			Flight f = flights.get(i);
			discountedPrice = f.getFlightPrice()
					- (f.getFlightPrice() * discountPercentage) / 100;
			f.setDiscountedFlightPrice(discountedPrice);
		}
	}

	private static boolean searchFlightByNumber(int flightNumber) {
		System.out
				.println("Flight Departure City | Flight Number | Flight Distance | Flight Price | Discounted Flight Price ");
		for (Flight f : flights) {
			if (f.verifyFlightNumber(flightNumber) != null) {
				System.out.println(f.getFlightDepartCity() + " | "
						+ f.getFlightNo() + " | " + f.getFlightDistance() + "|"
						+ f.getFlightPrice() + "|"
						+ f.getDiscountedFlightPrice());

				return true;
			}
		}
		return false;
	}

	private static void displayFlightData() {
		System.out
				.println("Flight Departure City | Flight Number | Flight Distance | Flight Price | Discounted Flight Price ");

		for (int i = 0; i < N; i++) {
			Flight f = flights.get(i);
			System.out.println(f.getFlightDepartCity() + " | "
					+ f.getFlightNo() + " | " + f.getFlightDistance() + "|"
					+ f.getFlightPrice() + "|" + f.getDiscountedFlightPrice());
		}
	}

	private static void writeFlightData() {
		Flight flight = new Flight();

		flight.setFlightNo(1212);
		flight.setFlightDepartCity("XYZ");
		flight.setFlightDistance(120);
		flight.setFlightPrice(150);
		flights.add(flight);

		flight = new Flight();

		flight.setFlightNo(1312);
		flight.setFlightDepartCity("ABC");
		flight.setFlightDistance(160);
		flight.setFlightPrice(190);
		flights.add(flight);

	}

	private static void readFlightData() {
		System.out.println("Please Enter Flight " + N + " Details ");
		boolean flag = false;
		int no;
		double price;
		for (int i = 0; i < N; i++) {
			System.out.println("Flight : " + (i + 1));
			Flight f = new Flight();
			System.out.print("Please Enter Flight Number : ");
			do {
				no = in.nextInt();
				if (no >= 1111 && no <= 9999) {
					f.setFlightNo(no);
					flag = true;
				} else {
					System.out
							.println("Please enter value between 1111 to 9999");
					flag = false;
				}
			} while (flag == false);

			System.out.print("Please Enter Flight Departure City : ");
			f.setFlightDepartCity(in.next());

			System.out.print("Please Enter Flight Travel Distance : ");
			f.setFlightDistance(in.nextInt());

			System.out.print("Please Enter Flight Ticket Price : ");

			do {
				price = in.nextDouble();
				if (price >= 9 && price <= 900) {
					f.setFlightPrice(price);
					flag = true;
				} else {
					System.out.println("Please enter value between 9 to 900");
					flag = false;
				}
			} while (flag == false);
			flights.add(f);
		}
	}
}
