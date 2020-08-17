package hotelReservation.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.decimal4j.util.DoubleRounder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import hotelReservation.domain.*;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Service
public class ReservationService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	public ReservationService(CustomerRepository customerRepository, HotelRepository hotelRepository,
			ReservationRepository reservationRepository, RoomRepository roomRepository) {
		this.customerRepository = customerRepository;
		this.hotelRepository = hotelRepository;
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
	}
	
	public List<Customer> getAllCustomers(){

		return customerRepository.getAllCustomers();
	}
	
	// Insert customer into database
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer); 
	}
	
	
	// Find customer information by ID
	public Customer getCustomerByID(long customerID) {
		return customerRepository.findByID(customerID);
	}
	
	public Customer getCustomerByNameEmail(String firstName, String lastName, String email) {
		
		return customerRepository.findByNameEmail(firstName, lastName, email);
	}
	
	
	public long getCustomerIDByResID(long resID) {

		Reservation reservation = reservationRepository.findByID(resID);
		long customerID = reservation.getCustomerID();
		
		return customerID;
	}
	
	// Get all hotels
	public List<Hotel> getAllHotels(){
		return hotelRepository.getAllHotels();
	}
	
	
	
	// Get list of hotels by city
	public List<Hotel> getHotelsByCity(String city){
		return hotelRepository.findHotels(city);
	}
	
	// Find one hotel by hotelID
	public Hotel getHotelByID(long hotelID) {
		return hotelRepository.findByID(hotelID);
	}
	
	// Get rooms based on hotelID and type
	public Room getRoom(long hotelID, String type){
		return roomRepository.getRoom(hotelID, type);
	}
	
	// Get one room by roomID
	public Room getRoom(long roomID) {
		return roomRepository.findByID(roomID);
	}


	// Get all reservations
	public List<Reservation> getAllReservations(){
		return reservationRepository.getAllReservations();
	}

	// Get reservation by ID
	public Reservation getReservationByID(long reservationID) {
		
		return reservationRepository.findByID(reservationID);
	}
	
	// Get list of reservations by customerID
	public List<Reservation> getReservationByCustomerID(long customerID) {
		
		return reservationRepository.findByCustomerID(customerID);
	}
	// Get list of reservations by customer email
	public List<Reservation> getReservationsByEmail(String customerEmail){
		
		return reservationRepository.findByCustomerEmail(customerEmail);

	}

	
	// Creating reservation
	public Reservation createReservation(long hotelID, long customerID, 
			String checkIn, String checkOut, long [] roomID, int [] numberOfRooms) {
		
		int totalNumberRooms = 0;
		int numStandardRoom = 0;
		int numIntermediateRoom = 0;
		int numLuxuryRoom = 0;
		
		int totalPrice = 0;
		int priceStandardRoom = 0;
		int priceIntermediateRoom = 0;
		int priceLuxuryRoom = 0;
		String customerEmail = customerRepository.findByID(customerID).getEmail();
		Hotel hotel = hotelRepository.findByID(hotelID);
		String hotelName = hotel.getName();
		String address = hotel.getAddress();
		
		// Calculate total number of rooms
		for (int i=0; i < numberOfRooms.length; i++) {
			totalNumberRooms += numberOfRooms[i];
			System.out.println(numberOfRooms[i]);
		}
		
		// Calculating number of rooms per type
		for (int i = 0; i < roomID.length; i++) {
			Room roomInfo = roomRepository.findByID(roomID[i]);
			switch (roomInfo.getType())
			{
				case "Standard":
					numStandardRoom = numberOfRooms[i];
					priceStandardRoom = roomInfo.getPrice() * numStandardRoom;
					break;
				case "Intermediate":
					numIntermediateRoom = numberOfRooms[i];
					priceIntermediateRoom = roomInfo.getPrice() * numIntermediateRoom;
					break;
				case "Luxury":
					numLuxuryRoom = numberOfRooms[i];
					priceLuxuryRoom = roomInfo.getPrice() * numLuxuryRoom;
					break;
				default :
					System.out.println("Type mismatch from database");
			}
		}

		int diffInDays = getNumDays(checkIn, checkOut);
		
		// Calculate total price
		totalPrice = (int) ((diffInDays * priceStandardRoom) + (diffInDays * priceIntermediateRoom) + (diffInDays * priceLuxuryRoom));

		Reservation reservation = new Reservation(hotelID, customerID, hotelName, address, customerEmail, checkIn, checkOut, 
				diffInDays, numStandardRoom, numIntermediateRoom, numLuxuryRoom, totalNumberRooms,
				priceStandardRoom, priceIntermediateRoom,  priceLuxuryRoom, 
				totalPrice, 0);

		return reservation;

	}
	
	// Create reservation for REST
	public Reservation createReservationREST(long hotelID, long customerID, String hotelName, String address, String customerEmail, String checkIn, String checkOut,
			int numDays, int numStandardRoom, int numIntermediateRoom, int numLuxuryRoom,
			int totalNumberRooms, int priceStandardRoom, int priceIntermediateRoom, int priceLuxuryRoom, int totalPrice , int cancelled) {
		
		Reservation reservation = new Reservation(hotelID, customerID, hotelName, address, customerEmail, checkIn, checkOut, 
				numDays, numStandardRoom, numIntermediateRoom, numLuxuryRoom, totalNumberRooms,
				priceStandardRoom, priceIntermediateRoom,  priceLuxuryRoom, 
				totalPrice, 0);
		
		return reservation;
		
	}
	
	// Save Reservation to Reservation Repository
	public void saveReservation(Reservation reservation) {
		
		reservationRepository.save(reservation);
	}
	
	public String cancelReservation(long resID) {
		
		Reservation reservation =reservationRepository.findByID(resID);
		if (reservation.getCancelled() == 0) {
			reservationRepository.cancelReservationByID(resID);
			return ("Reservation ID : "+ resID + " cancelled.");
		} 
		else {
			return ("Reservation ID : " + resID + " is already cancelled.");
		}
	}
	
	public void deleteReservation(long resID) {
		reservationRepository.deleteByResID(resID);
		System.out.println("DELETE successful");
	}
	

	public void updateReservation(Reservation reservation){
		reservationRepository.updateReservation(reservation.getResID(), reservation.getHotelID(), reservation.getCustomerID(),
				reservation.getHotelName(), reservation.getAddress(), reservation.getCustomerEmail(), reservation.getCheckIn(), 
				reservation.getCheckOut(), reservation.getNumDays(), reservation.getNumStandardRoom(),
				reservation.getNumIntermediateRoom(), reservation.getNumLuxuryRoom(), reservation.getTotalRooms(), 
				reservation.getPriceStandardRoom(),reservation.getPriceIntermediateRoom(), reservation.getPriceLuxuryRoom(), 
				reservation.getTotalPrice(), reservation.getCancelled());
		
	}

	// Check if customer exists by name and email
	public boolean isCustomerExist(String firstName, String lastName, String email) {
		
		List<Customer> customers = getAllCustomers();
		
		// If customer list is not empty
		if(customers.size() != 0) {
			for (Customer customer  : customers) {
				if (customer.getFirstName().equalsIgnoreCase(firstName) &&
						customer.getLastName().equalsIgnoreCase(lastName) &&
						customer.getEmail().equalsIgnoreCase(email)) {
				
					return true;
				}
			}
		} 
		// if customer list is empty
		return false;
	}
	
	public int getNumDays(String checkIn, String checkOut) {
		// Parsing date to determine difference between two dates
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date checkInDate = null;
		Date checkOutDate = null;
		
		try {	
				checkInDate = format.parse(checkIn);
		
			} catch (ParseException e) {
					
				e.printStackTrace();		
			}
		try {
				checkOutDate = format.parse(checkOut);
					
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
				
		long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
		
		return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
}

