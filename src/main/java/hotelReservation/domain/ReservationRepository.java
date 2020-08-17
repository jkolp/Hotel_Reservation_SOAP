package hotelReservation.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	@Query(value = "SELECT * FROM u7a99i86l8a63osd.reservations where resID = :resID", nativeQuery = true)
	Reservation findByID(@Param("resID") long resID);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.reservations where customer_email = :customerEmail", nativeQuery = true)
	List<Reservation> findByCustomerEmail(@Param("customerEmail") String customerEmail);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.reservations where customerID = :customerID", nativeQuery = true)
	List<Reservation> findByCustomerID(@Param("customerID") long customerID);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.reservations", nativeQuery = true)
	List<Reservation> getAllReservations();
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM u7a99i86l8a63osd.reservations WHERE resID = :resID", nativeQuery = true)
	void deleteByResID(@Param("resID") long resID);
	
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE u7a99i86l8a63osd.reservations SET cancelled = 1 WHERE resID = :resID", nativeQuery = true)
	void cancelReservationByID(@Param("resID") long resID);
	
	
	//
	@Modifying
	@Transactional
	@Query(value ="UPDATE u7a99i86l8a63osd.reservations " +
			"SET hotelID= :hotelID, customerID= :customerID," + 
			"cancelled = :cancelled, hotel_name= :hotel_name," + 
			"address= :address, customer_email= :customer_email," + 
			"check_in= :check_in, check_out= :check_out, num_days= :num_days, " +
			"num_standard_room= :num_standard_room, num_intermediate_room= :num_intermediate_room," + 
			"num_luxury_room = :num_luxury_room, total_rooms = :total_rooms," +
			"price_standard_room = :price_standard_room, price_intermediate_room = :price_intermediate_room," + 
			"price_luxury_room = :price_luxury_room, total_price = :total_price, cancelled = :cancelled " +
			"WHERE resID = :resID", nativeQuery = true)
	void updateReservation(@Param("resID") long resID, @Param("hotelID") long hotelID, @Param("customerID") long customerID, 
			@Param("hotel_name") String hotel_name, @Param("address") String address, @Param("customer_email") String customer_email, 
			@Param("check_in") String checkIn, @Param("check_out") String checkOut, @Param("num_days") int num_days, @Param("num_standard_room") int num_standard_room, 
			@Param("num_intermediate_room") int num_intermediate_room, @Param("num_luxury_room") int num_luxury_room, @Param("total_rooms") int total_rooms, 
			@Param("price_standard_room") int price_standard_room, @Param("price_intermediate_room") int price_intermediate_room, 
			@Param("price_luxury_room") int price_luxury_room, @Param("total_price") int total_price,@Param("cancelled")  int cancelled);
			
}
 