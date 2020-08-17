package hotelReservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.*;

@Entity
@Table(name="reservations")
public class Reservation {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "resID")
	private long resID;
	private long hotelID;
	private long customerID;
	private String hotelName;
	private String address;
	private String customerEmail;
	private String checkIn;
	private String checkOut;
	private int numDays;
	private int numStandardRoom;
	private int numIntermediateRoom;
	private int numLuxuryRoom;
	private int totalRooms;
	private int priceStandardRoom;
	private int priceIntermediateRoom;
	private int priceLuxuryRoom;
	private int totalPrice;
	private int cancelled;
	
	
	public Reservation() {
		
	}


	public Reservation(long hotelID, long customerID, String hotelName, String address,
			String customerEmail, String checkIn, String checkOut, int numDays, int numStandardRoom,
			int numIntermediateRoom, int numLuxuryRoom, int totalRooms, int priceStandardRoom,
			int priceIntermediateRoom, int priceLuxuryRoom, int totalPrice, int cancelled) {
		super();
		this.hotelID = hotelID;
		this.customerID = customerID;
		this.hotelName = hotelName;
		this.address = address;
		this.customerEmail = customerEmail;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numDays = numDays;
		this.numStandardRoom = numStandardRoom;
		this.numIntermediateRoom = numIntermediateRoom;
		this.numLuxuryRoom = numLuxuryRoom;
		this.totalRooms = totalRooms;
		this.priceStandardRoom = priceStandardRoom;
		this.priceIntermediateRoom = priceIntermediateRoom;
		this.priceLuxuryRoom = priceLuxuryRoom;
		this.totalPrice = totalPrice;
		this.cancelled = cancelled;
	}


	public long getResID() {
		return resID;
	}


	public void setResID(long resID) {
		this.resID = resID;
	}


	public long getHotelID() {
		return hotelID;
	}


	public void setHotelID(long hotelID) {
		this.hotelID = hotelID;
	}


	public long getCustomerID() {
		return customerID;
	}


	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}


	public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}


	public String getCheckOut() {
		return checkOut;
	}


	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}


	public int getNumDays() {
		return numDays;
	}


	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}


	public int getNumStandardRoom() {
		return numStandardRoom;
	}


	public void setNumStandardRoom(int numStandardRoom) {
		this.numStandardRoom = numStandardRoom;
	}


	public int getNumIntermediateRoom() {
		return numIntermediateRoom;
	}


	public void setNumIntermediateRoom(int numIntermediateRoom) {
		this.numIntermediateRoom = numIntermediateRoom;
	}


	public int getNumLuxuryRoom() {
		return numLuxuryRoom;
	}


	public void setNumLuxuryRoom(int numLuxuryRoom) {
		this.numLuxuryRoom = numLuxuryRoom;
	}


	public int getTotalRooms() {
		return totalRooms;
	}


	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}


	public int getPriceStandardRoom() {
		return priceStandardRoom;
	}


	public void setPriceStandardRoom(int priceStandardRoom) {
		this.priceStandardRoom = priceStandardRoom;
	}


	public int getPriceIntermediateRoom() {
		return priceIntermediateRoom;
	}


	public void setPriceIntermediateRoom(int priceIntermediateRoom) {
		this.priceIntermediateRoom = priceIntermediateRoom;
	}


	public int getPriceLuxuryRoom() {
		return priceLuxuryRoom;
	}


	public void setPriceLuxuryRoom(int priceLuxuryRoom) {
		this.priceLuxuryRoom = priceLuxuryRoom;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getCancelled() {
		return cancelled;
	}


	public void setCancelled(int cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cancelled != other.cancelled)
			return false;
		if (checkIn == null) {
			if (other.checkIn != null)
				return false;
		} else if (!checkIn.equals(other.checkIn))
			return false;
		if (checkOut == null) {
			if (other.checkOut != null)
				return false;
		} else if (!checkOut.equals(other.checkOut))
			return false;
		if (customerEmail == null) {
			if (other.customerEmail != null)
				return false;
		} else if (!customerEmail.equals(other.customerEmail))
			return false;
		if (customerID != other.customerID)
			return false;
		if (hotelID != other.hotelID)
			return false;
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (numDays != other.numDays)
			return false;
		if (numIntermediateRoom != other.numIntermediateRoom)
			return false;
		if (numLuxuryRoom != other.numLuxuryRoom)
			return false;
		if (numStandardRoom != other.numStandardRoom)
			return false;
		if (priceIntermediateRoom != other.priceIntermediateRoom)
			return false;
		if (priceLuxuryRoom != other.priceLuxuryRoom)
			return false;
		if (priceStandardRoom != other.priceStandardRoom)
			return false;
		if (resID != other.resID)
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		if (totalRooms != other.totalRooms)
			return false;
		return true;
	}

	public Boolean isReservationPassed (String checkOut) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		Date currentDate = null;
		Date checkOutDate = null;
		
		String today = formatter.format(new Date());
		
		System.out.println("TODAY : " + today);
		System.out.println("Check Out : " + checkOut);
		try {
			currentDate = formatter.parse(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			checkOutDate = formatter.parse(checkOut);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (currentDate.getTime() > checkOutDate.getTime()) {
			return true;
		}else {
			return false;
		}
	}
}
