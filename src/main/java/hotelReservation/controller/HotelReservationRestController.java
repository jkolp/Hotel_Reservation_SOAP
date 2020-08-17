package hotelReservation.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hotelReservation.service.ReservationService;
import hotelReservation.domain.*;


@RestController
public class HotelReservationRestController {
	@Autowired
	private ReservationService reservationService;

	
	// Look up reservation by reservationID
	@GetMapping("/api/hotelReservation/reservations/{resID}")
	public ResponseEntity<Reservation> getReservationByResID(@PathVariable("resID") long resID) {
		Reservation reservation = reservationService.getReservationByID(resID);
		
		// If reservation is not found.
		if (reservation == null) {
			System.out.println("No reservations were found by the reservationID : " + resID);
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		else {
			// If the reservation is not cancelled
			if(reservation.getCancelled() == 0) {
				return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
			}
			else {
				// If the reservation is cancelled respond with Not Found
				System.out.println("This reservationID : " + resID + " had been cancelled.");
				return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
			}
		}	
	}
	
	// Look up all reservations by customerID
	@GetMapping("/api/hotelReservation/customerID/{customerID}")
	public ResponseEntity<List<Reservation>> getReservationsByCustomerID(@PathVariable("customerID") long customerID)
	{
		List<Reservation> reservations = reservationService.getReservationByCustomerID(customerID);
		
		if (reservations.size() == 0) {
			System.out.println("No reservations were found by the customerID : " + customerID);
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
		else {
			List<Reservation> activeReservations = new ArrayList<>();
			
			// If a reservation is not cancelled, add it to activeReservations list
			for (Reservation reservation : reservations) {
				if(reservation.getCancelled() == 0) {
					activeReservations.add(reservation);
				}
			}
			
			if (activeReservations.size() == 0) {
				// If there is no active reservation, then respond with Not_FOUND
				System.out.println("Reservations found by the customerID : " + customerID + ", are all cancelled.");
				return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
			} 
			else {
				System.out.println("Reservation found by the customerID : " + customerID);
				return new ResponseEntity<List<Reservation>>(activeReservations, HttpStatus.OK);
			}
		}	
	}
	
	
	// Look up all reservations by customer email
	@GetMapping("/api/hotelReservation/customerEmail/{customerEmail}")
	public ResponseEntity<List<Reservation>> getReservationsByCustomerEmail(@PathVariable("customerEmail") String customerEmail)
	{
		List<Reservation> reservations = reservationService.getReservationsByEmail(customerEmail);

		if (reservations.size() == 0) {
			System.out.println("No reservations were found by the email : " + customerEmail);
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
		else {
			List<Reservation> activeReservations = new ArrayList<>();
			
			// If a reservation is not cancelled, add it to activeReservations list
			for (Reservation reservation : reservations) {
				if(reservation.getCancelled() == 0) {
					activeReservations.add(reservation);
				}
			}
			
			if (activeReservations.size() == 0) {
				// If there is no active reservation, then respond with Not_FOUND
				System.out.println("Reservations found by the customer email : " + customerEmail + ", are all cancelled.");
				return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
			} 
			else {
				// Send all active reservations
				System.out.println("Reservation found by the customer email : " + customerEmail);
				return new ResponseEntity<List<Reservation>>(activeReservations, HttpStatus.OK);
			}
		}	
	}

	@GetMapping("/api/hotelReservation/getAllReservations")
	public ResponseEntity<List<Reservation>> getAllReservations(){
		
		List<Reservation> reservations = reservationService.getAllReservations();
		
		if (reservations.size() == 0) {
			System.out.println("No reservation found.");
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
		}
	}
	
	// Cancel reservation method returns String
	@PostMapping("/api/hotelReservation/cancelByReservationID/{reservationID}")
	public ResponseEntity<String> cancelReservation(@PathVariable("reservationID") long resID){
		
			return new ResponseEntity<String>(reservationService.cancelReservation(resID), HttpStatus.OK);
	}
	
	
	//Get all hotels
	@GetMapping("/api/hotelReservation/getAllHotels")
	public ResponseEntity<List<Hotel>> getAllHotels(){
		
		return new ResponseEntity<List<Hotel>>(reservationService.getAllHotels(), HttpStatus.OK);
	}
	
	//Get all customers
	@GetMapping("/api/hotelReservation/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		
		return new ResponseEntity<List<Customer>>(reservationService.getAllCustomers(), HttpStatus.OK);
	}
	
	// CreateReservation
	@GetMapping("/api/hotelReservation/createReservation/{hotelID}/{firstName}/{lastName}/{email}/{checkInMonth}/{checkInDay}/{checkInYear}/{checkOutMonth}/{checkOutDay}/{checkOutYear}/{roomType}/{numRooms}")
	public ResponseEntity<String> createReservation(
			@PathVariable("hotelID") long hotelID, 
			@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName,
			@PathVariable("email") String email,
			@PathVariable("checkInMonth") String checkInMonth,
			@PathVariable("checkInDay") String checkInDay,
			@PathVariable("checkInYear") String checkInYear,
			@PathVariable("checkOutMonth") String checkOutMonth,
			@PathVariable("checkOutDay") String checkOutDay,
			@PathVariable("checkOutYear") String checkOutYear,
			@PathVariable("roomType") String roomType,
			@PathVariable("numRooms") int numRooms
			){
		
		String checkIn = checkInMonth + "/" + checkInDay + "/" + checkInYear;
		String checkOut = checkOutMonth + "/" + checkOutDay + "/" + checkOutYear;;
		
		firstName = firstName.toLowerCase().trim();
		lastName = lastName.toLowerCase().trim();
		Customer customer;
		// Check if customer already exists
		if(!reservationService.isCustomerExist(firstName, lastName, email)) {
			// if current customer does not exist, add to the database
			customer = new Customer(firstName, lastName, email);
			
			System.out.println("Customer does not exist. Saving to the database.");
			reservationService.saveCustomer(customer); 
			System.out.println("customerinfo saved");
		} 
		else {
			// bring out customer info from existing database
			customer = reservationService.getCustomerByNameEmail(firstName, lastName, email);
			
			System.out.println("Customer ID : " + customer.getCustomerID() + " already exists.");
		}
		
		// Get hotelName
		Hotel hotel = reservationService.getHotelByID(hotelID);
		String hotelName = hotel.getName();
		// Get number of days staying
		int numDays = reservationService.getNumDays(checkIn, checkOut);
		
		// Get room info
		Room roomInfo = reservationService.getRoom(hotel.getHotelID(), roomType);
		
		int numStandardRoom = 0;
		int numIntermediateRoom = 0;
		int numLuxuryRoom = 0;
		int priceStandardRoom = 0;
		int priceIntermediateRoom = 0;
		int priceLuxuryRoom = 0;

		switch(roomType)
		{
			case "Standard" :
				System.out.println("Standard room chosen");
				numStandardRoom = numRooms;
				numIntermediateRoom = 0;
				numLuxuryRoom = 0;
				
				priceStandardRoom = roomInfo.getPrice() * numStandardRoom;
				priceIntermediateRoom = 0;
				priceLuxuryRoom = 0;
				
				break;
			case "Intermediate" :
				System.out.println("Intermediate room chosen");
				numStandardRoom = 0;
				numIntermediateRoom = numRooms;
				numLuxuryRoom = 0;
				
				priceStandardRoom = 0;
				priceIntermediateRoom = roomInfo.getPrice() * numIntermediateRoom;
				priceLuxuryRoom = 0;
				
				break;
			case "Luxury" :
				System.out.println("Luxury room chosen");
				numStandardRoom = 0;
				numIntermediateRoom = 0;
				numLuxuryRoom = numRooms;
				
				priceStandardRoom = 0;
				priceIntermediateRoom = 0;
				priceLuxuryRoom = roomInfo.getPrice() * numLuxuryRoom;
				
				break;
			default:
				System.out.println("Invalid input");
		}
		
		int totalPrice = priceStandardRoom + priceIntermediateRoom + priceLuxuryRoom;
		
		Reservation reservation = reservationService.createReservationREST(hotelID, customer.getCustomerID(), 
				hotelName, hotel.getAddress(), customer.getEmail(), checkIn, checkOut, numDays, numStandardRoom, numIntermediateRoom, numLuxuryRoom,
				numRooms, priceStandardRoom, priceIntermediateRoom, priceLuxuryRoom, totalPrice ,0);
		
		reservationService.saveReservation(reservation);
		
		System.out.println("Successfully created reservation.");
		return new ResponseEntity<String>("Successfully created reservation.", HttpStatus.OK);

	}
	
}
