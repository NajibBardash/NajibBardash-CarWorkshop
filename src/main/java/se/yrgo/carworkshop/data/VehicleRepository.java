package se.yrgo.carworkshop.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.yrgo.carworkshop.domain.Vehicle;

import java.util.List;

/**
 * This interface looks up vehicles in a car-workshop
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    /**
     *
     * @param brand to be listed all vehicles for
     * @return a list of all vehicles with the same brand
     */
    @Query("SELECT v FROM Vehicle v WHERE v.brand = :brand")
    List<Vehicle> findAllVehiclesByBrand(String brand);
}
