package hotelReservation.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.Rooms WHERE hotelID = :hotelID AND type = :type", nativeQuery = true)
	Room getRoom(@Param("hotelID") long hotelID, @Param("type") String type);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.Rooms where roomID = :roomID", nativeQuery = true)
	Room findByID(@Param("roomID") long roomID);
}
