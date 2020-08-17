package hotelReservation.controller;

import java.lang.String;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotelReservation.domain.*;
import hotelReservation.service.*;
//import service;


@Controller
public class hotelReservationController {
	
	// Service used to save, display all reservation related features
	@Autowired
	private ReservationService reservationService;
	
	// Index page
	@GetMapping("/hotelReservation")
	public String homePage(){
		
		return "index";
	}
	
	// Rendering customer info form page
	@GetMapping("/hotelReservation/customerInfoForm")
	public String customerForm(Model model) {
		
		Customer customerInfo = new Customer();
		model.addAttribute("customerInfo", customerInfo);
		
		return "customerInfo";
	} 
	
	// Takes customer info and saves to customer table in MySQL
	// Then renders hotel form to pick checkIn date, checkOut date, and city
	@PostMapping("/hotelReservation/dateCityForm")
	public String createCustomer (@Valid Customer customerInfo, BindingResult result, Model model)
	{
		if (result.hasErrors()) {
			System.out.println("ERROR binding results from customer form");
			return "redirect:/hotelReservation/customerInfo";
		} else {
			
			// Converting customer name to all lower case 
			// Also trimming whitespaces on both sides of leading and trailing
			customerInfo.setFirstName(customerInfo.getFirstName().toLowerCase().trim());
			customerInfo.setLastName(customerInfo.getLastName().toLowerCase().trim());
			
			System.out.println("<! ------- NO ERRORS FOUND IN RESULT ------- >");
			System.out.println("User Information");
			System.out.println("First name : " + customerInfo.getFirstName());
			System.out.println("Last name : " + customerInfo.getLastName());
			System.out.println("Email : " + customerInfo.getEmail());
			
			System.out.println(reservationService.isCustomerExist(customerInfo.getFirstName(), customerInfo.getLastName(), customerInfo.getEmail()));
			
			// Checks if customer already exists in the database
			if(!reservationService.isCustomerExist(customerInfo.getFirstName(), customerInfo.getLastName(), customerInfo.getEmail())) {
				// if current customer does not exist, add to the database
				System.out.println("Customer does not exist. Saving to the database.");
				reservationService.saveCustomer(customerInfo); 
				System.out.println("customerinfo saved");
			} else {
				// bring out customer info from existing database
				customerInfo = reservationService.getCustomerByNameEmail(customerInfo.getFirstName(), customerInfo.getLastName(), customerInfo.getEmail());
				
				System.out.println("Customer ID : " + customerInfo.getCustomerID() + " already exists.");
			}

			// Setting minimum value to today's date
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateNow = formatter.format(new Date());
			
			// Passes the customerInfo object to hotelForm to choose dates and city
			model.addAttribute("customerInfo", customerInfo);
			model.addAttribute("dateNow", dateNow);
			return "dateCityForm";
		}
	}

	// Renders list of hotels accordingly to users choices of checkIn, checkout dates and city
	@PostMapping("/hotelReservation/listHotels")
	public String pickDateCity(
			@RequestParam("customerID") String customerID, 
			@RequestParam("checkIn") String checkIn, 
			@RequestParam("checkOut") String checkOut, 
			@RequestParam("city") String city, 
			Model model) {
		
		checkIn = reformatDate(checkIn);
		checkOut = reformatDate(checkOut);
		
		model.addAttribute("customerID", customerID);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("city", city);
		
		Iterable<Hotel> hotels = reservationService.getHotelsByCity(city);
		model.addAttribute("hotels", hotels);
		
		return "listHotels";

	}
	
	@PostMapping("/hotelReservation/roomChoices")
	public String roomChoices(
			@RequestParam("city") String city,
			@RequestParam("checkIn") String checkIn, 
			@RequestParam("checkOut") String checkOut,
			@RequestParam("hotelID") long hotelID,
			@RequestParam("customerID") String customerID,
			Model model){
		
		// Getting standard, intermediate, luxury room objects.
		Room standardRoom = reservationService.getRoom(hotelID, "Standard");
		Room intermediateRoom = reservationService.getRoom(hotelID, "Intermediate");
		Room luxuryRoom = reservationService.getRoom(hotelID, "Luxury");		
		Hotel hotel = reservationService.getHotelByID(hotelID); // hotel object corresponding to rooms
		
		// Consolidate all rooms to a list for thymeleaf th:each method
		List<Room> rooms = new ArrayList<>();
		rooms.addAll(Arrays.asList(standardRoom, intermediateRoom, luxuryRoom));
		
		// Passing information about reservation
		model.addAttribute("city", city);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("customerID", customerID);
		model.addAttribute("hotel", hotel);
		model.addAttribute("rooms", rooms);

		return "roomChoice";
	}
	
	
	@PostMapping("/hotelReservation/confirmation")
	public String consolidateInformation(
			@RequestParam("city") String city,
			@RequestParam("checkIn") String checkIn, 
			@RequestParam("checkOut") String checkOut,
			@RequestParam("hotelID") long hotelID,
			@RequestParam("customerID") long customerID,
			@RequestParam("roomID") long [] roomID,
			@RequestParam("numRooms") int [] numberOfRooms,
			Model model) {

		// Create reservation
		Reservation reservation = reservationService.createReservation(hotelID, customerID, checkIn, checkOut, roomID, numberOfRooms);
		int totalNumberRooms =0; 

		System.out.println("Total Price : $" + reservation.getTotalPrice());

		// Calculate total number of rooms
		for (int i=0; i < numberOfRooms.length; i++) {
			totalNumberRooms += numberOfRooms[i];
		}

		// If no rooms were chosen display zeroRoomsPage.html
		if(totalNumberRooms == 0) {
			System.out.println("0 rooms were selected");
			model.addAttribute("city", city);
			model.addAttribute("checkIn", checkIn);
			model.addAttribute("checkOut", checkOut);
			model.addAttribute("customerID", customerID);
			model.addAttribute("hotelID", hotelID);
			return "zeroRoomsPage";
		}
		else {
			reservationService.saveReservation(reservation);
			
			Customer customerInfo = reservationService.getCustomerByID(reservation.getCustomerID());
			Hotel hotelInfo = reservationService.getHotelByID(reservation.getHotelID());
			
			model.addAttribute("hotel", hotelInfo);
			model.addAttribute("customer", customerInfo);
			model.addAttribute("reservation", reservation);
		
			return "reservationConfirmation";
		}
	}

	// Find Reservation By ID
	@GetMapping("/hotelReservation/findReservation")
	public String findReservation(Model model) {
		
		return "lookUpReservation";
	}
	
	@PostMapping("/hotelReservation/displayReservation")
	public String displayReservation(@RequestParam(defaultValue = "0") long reservationID,
			@RequestParam(defaultValue = "abcdefghijk@lmnopqrstuvwzyz.com") String reservationEmail,
			Model model) {
		
		List<Reservation> reservations = new ArrayList<>();
		
		// if user did not provide reservation ID use email to look up reservation
		if (reservationID == 0) {
		// If user did not provide reservation ID Look  up by Email
			reservations = reservationService.getReservationsByEmail(reservationEmail);
			
			if (reservations.size() == 0){
			// If no reservation found
				System.out.println("No reservation found by this Email : "+ reservationEmail);
				model.addAttribute("reservationEmail", reservationEmail);
				return "noReservationFound";
			} 
			else {

				model.addAttribute("reservations", reservations);
			}
		} 
		else {
		// Look up by ID
			//reservations = reservationService.getReservationByID(reservationID);

			Reservation reservation = reservationService.getReservationByID(reservationID);
			System.out.println(reservation);
			if (reservation == null){
			// If no reservation found
				System.out.println("No reservation found by this reservation ID : "+ reservationID);
				model.addAttribute("reservationID", reservationID);
				return "noReservationFound";
			} else {
				
				model.addAttribute("reservations", reservation);
			}
		}
		return "displayReservations";
	}
	
	@PostMapping("/hotelReservation/cancelOrUpdate")
	public String cancelOrEdit(
			@RequestParam("resID") long resID,
			@RequestParam("action") String action,
			Model model) {

		//System.out.println(Long.parseLong(resID) + " " + action);
		System.out.println(action);
		
		if (action.equalsIgnoreCase("Cancel")) {
			
			System.out.println(reservationService.cancelReservation(resID));
			model.addAttribute("action", action);
			
			return "cancelComplete";
			
		} else {
			long customerID = reservationService.getCustomerIDByResID(resID);
			Customer customer = reservationService.getCustomerByID(customerID);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateNow = formatter.format(new Date());
			System.out.println(dateNow);
			
			model.addAttribute("dateNow", dateNow);
			model.addAttribute("customerInfo", customer);
			model.addAttribute("resID", resID);

			return "updateDateAndCity";
		}	
	}
	
	
	@PostMapping("/hotelReservation/updateHotel")
	public String updateHotel(
			@RequestParam("resID") long resID,
			@RequestParam("customerID") String customerID,
			@RequestParam("checkIn") String checkIn,
			@RequestParam("checkOut") String checkOut,
			@RequestParam("city") String city,
			Model model) {
		
		Iterable<Hotel> hotels = reservationService.getHotelsByCity(city);
		
		checkIn = reformatDate(checkIn);
		checkOut = reformatDate(checkOut);
		
		model.addAttribute("hotels", hotels);
		model.addAttribute("customerID", customerID);
		model.addAttribute("resID", resID);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("city", city);
		
		return "updateHotel";
			
	}
	
	@PostMapping("/hotelReservation/updateRooms")
	public String updateRooms(
			@RequestParam("resID") long resID,
			@RequestParam("customerID") long customerID,
			@RequestParam("hotelID") long hotelID,
			@RequestParam("checkIn") String checkIn,
			@RequestParam("checkOut") String checkOut,
			@RequestParam("city") String city,
			Model model) {
		
		// Getting standard, intermediate, luxury room objects.
		Room standardRoom = reservationService.getRoom(hotelID, "Standard");
		Room intermediateRoom = reservationService.getRoom(hotelID, "Intermediate");
		Room luxuryRoom = reservationService.getRoom(hotelID, "Luxury");		
		Hotel hotel = reservationService.getHotelByID(hotelID); // hotel object corresponding to rooms
				
		// Consolidate all rooms to a list for thymeleaf th:each method
		List<Room> rooms = new ArrayList<>();
		rooms.addAll(Arrays.asList(standardRoom, intermediateRoom, luxuryRoom));
				
		// Passing information about reservation
		model.addAttribute("city", city);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("customerID", customerID);
		model.addAttribute("resID", resID);
		model.addAttribute("hotel", hotel);
		model.addAttribute("rooms", rooms);
		
	
		return "updateRooms";		
	}

	@PostMapping("/hotelReservation/updateConfirmation")
	public String updateConfirmation(
			@RequestParam("resID") long resID,
			@RequestParam("city") String city,
			@RequestParam("checkIn") String checkIn, 
			@RequestParam("checkOut") String checkOut,
			@RequestParam("hotelID") long hotelID,
			@RequestParam("customerID") long customerID,
			@RequestParam("roomID") long [] roomID,
			@RequestParam("numRooms") int [] numberOfRooms,
			Model model) {

		// Create reservation
		Reservation reservation = reservationService.createReservation(hotelID, customerID, checkIn, checkOut, roomID, numberOfRooms);
		
		int totalNumberRooms =0; 

		// Calculate total number of rooms
		for (int i=0; i < numberOfRooms.length; i++) {
			totalNumberRooms += numberOfRooms[i];
		}

		// If no rooms were chosen display zeroRoomsPage.html
		if(totalNumberRooms == 0) {
			System.out.println("0 rooms were selected");
			model.addAttribute("city", city);
			model.addAttribute("checkIn", checkIn);
			model.addAttribute("checkOut", checkOut);
			model.addAttribute("customerID", customerID);
			model.addAttribute("hotelID", hotelID);
			return "zeroRoomsPage";
		}
		else {
			reservation.setResID(resID);
			reservationService.updateReservation(reservation);
			
			Customer customerInfo = reservationService.getCustomerByID(reservation.getCustomerID());
			Hotel hotelInfo = reservationService.getHotelByID(reservation.getHotelID());
			
			System.out.println(reservation.getCheckOut());
			model.addAttribute("hotel", hotelInfo);
			model.addAttribute("customer", customerInfo);
			model.addAttribute("reservation", reservation);
		
			return "updateConfirmation";
		}
	}
	
	public String reformatDate(String date) {
		LocalDate currentDateFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String reformatted_date = currentDateFormat.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
		
		return reformatted_date;
	}
/*	
	public boolean isCustomerExist(String firstName, String lastName, String email) {
		
		List<Customer> customers = reservationService.getAllCustomers();
		
		// If customer list is not empty
		if(customers.size() != 0) {
			for (Customer customer  : customers) {
				if (customer.getFirstName().equalsIgnoreCase(firstName) &&
						customer.getLastName().equalsIgnoreCase(lastName) &&
						customer.getEmail().equalsIgnoreCase(email)) {
				
					return true;
				}
				else {
					// if customer is not found
					return false;
				}
			}
		} 
		// if customer list is empty
		return false;
	}
*/
}