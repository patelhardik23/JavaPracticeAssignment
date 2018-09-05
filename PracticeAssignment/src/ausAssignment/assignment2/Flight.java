package ausAssignment.assignment2;
import java.util.Comparator;

public class Flight {
	
	//fields
	//get and set methods
	String flightDepartCity;
	int flightNo , flightDistance;
	double flightPrice, discountedFlightPrice;
	
	public String getFlightDepartCity() {
		return flightDepartCity;
	}
	public void setFlightDepartCity(String flightDepartCity) {
		this.flightDepartCity = flightDepartCity;
	}
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	public int getFlightDistance() {
		return flightDistance;
	}
	public void setFlightDistance(int flightDistance) {
		this.flightDistance = flightDistance;
	}
	public double getFlightPrice() {
		return flightPrice;
	}
	public void setFlightPrice(double flightPrice) {
		this.flightPrice = flightPrice;
	}
	public double getDiscountedFlightPrice() {
		return discountedFlightPrice;
	}
	public void setDiscountedFlightPrice(double discountedFlightPrice) {
		this.discountedFlightPrice = discountedFlightPrice;
	}
	
	public static Comparator<Flight> nameComparator = new Comparator<Flight>() {

		@Override
		public int compare(Flight f1, Flight f2) {
			return (int) (f2.getFlightDepartCity().compareTo(f1.getFlightDepartCity()));
		}
	};
	
	public static Comparator<Flight> priceComparator = new Comparator<Flight>() {
		@Override
		public int compare(Flight fp1, Flight fp2) {
			return (fp1.getFlightPrice() < fp2.getFlightPrice() ? -1 : (fp1.getFlightPrice() == fp2.getFlightPrice() ? 0 : 1));
		}
	};
	
	public static Comparator<Flight> minimumPrice = new Comparator<Flight>() {
		@Override
		public int compare(Flight mp1, Flight mp2) {
			return Double.compare(mp1.getFlightPrice(),mp2.getFlightPrice());
		}
	};
		
	@Override
	public String toString() {
		return "Flight [flightDepartCity=" + flightDepartCity + ", flightNo="
				+ flightNo + ", flightDistance=" + flightDistance
				+ ", flightPrice=" + flightPrice + ", discountedFlightPrice="
				+ discountedFlightPrice + "]";
	}
	
	public Flight verifyFlightNumber(int flightNo)
	{
		return ((this.flightNo == flightNo)? this : null);
	}
}
