package hotelReservation.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.hotels where city = :city", nativeQuery = true)
	List<Hotel> findHotels(@Param("city") String city);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.hotels where hotelID = :hotelID", nativeQuery = true)
	Hotel findByID(@Param("hotelID") long hotelID);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.hotels", nativeQuery = true)
	List<Hotel> getAllHotels();
}
 