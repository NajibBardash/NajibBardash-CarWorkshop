package se.yrgo.carworkshop.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.yrgo.carworkshop.domain.Customer;
import se.yrgo.carworkshop.domain.Vehicle;

import java.util.List;

/**
 * This interface looks up vehicles for customers in a car-workshop
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     *
     * @param name to find the id for
     * @return a customerId that belongs to the customer's name
     */
    Customer findByName(String name);

    /**
     *
     * @param customerId of the customer to find the vehicles for
     * @return a list of vehicles for a specific customer
     */
    @Query("SELECT c.vehicles FROM Customer c WHERE c.id = :customerId")
    List<Vehicle> findVehiclesByCustomerId(@Param("customerId") Long customerId);

    /**
     *
     * @return a list of objects that contains both a customer and a vehicle
     */
    @Query("SELECT c, v FROM Customer c LEFT JOIN c.vehicles v")
    List<Object[]> findAllCustomersWithVehicles();
}
