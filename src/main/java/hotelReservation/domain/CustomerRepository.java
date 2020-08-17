package hotelReservation.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	/*
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO u7a99i86l8a63osd.customers (last_name, first_name, email) VALUES (:last_name , :first_name , :email);", nativeQuery = true)
	Customer saveCustomer(@Param("last_name") String last_name, @Param("first_name") String first_name, @Param("email") String email);
	*/
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.customers where customerID = :customerID", nativeQuery = true)
	Customer findByID(@Param("customerID") long customerID);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.customers where first_name = :first_name AND last_name = :last_name AND email = :email", nativeQuery = true)
	Customer findByNameEmail(@Param("first_name") String firstName,
			@Param("last_name") String lastName,
			@Param("email") String email);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.customers where email = :email", nativeQuery = true)
	List<Customer> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM u7a99i86l8a63osd.customers", nativeQuery = true)
	List<Customer> getAllCustomers();
}
 