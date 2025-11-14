package se.yrgo.carworkshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.carworkshop.data.CustomerRepository;
import se.yrgo.carworkshop.data.VehicleRepository;
import se.yrgo.carworkshop.domain.Customer;
import se.yrgo.carworkshop.domain.Vehicle;
import se.yrgo.carworkshop.dtos.CustomerDTO;
import se.yrgo.carworkshop.dtos.VehicleDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This controller handles customers and vehicles in a car-workshop.
 *
 */
@RestController
public class CustomerVehicleController {

    private CustomerRepository customerRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public CustomerVehicleController(CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     *
     * @param customer to be added to the workshop
     * @return a message when the creation is completed
     */
    @PostMapping("/customer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer created");
    }

    /**
     *
     * @param vehicle to be added to the workshop
     * @return a message when the creation is completed
     */
    @PostMapping("/vehicle")
    public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok("Vehicle created");
    }

    /**
     *
     * @param customerId is the id of the customer to get a vehicle
     * @param vehicleId is te id of the vehicle to get a customer
     * @return success-message if both entities exist, else failure-message
     */
    @PostMapping("/addvehicletocustomer")
    public ResponseEntity<String> addVehicleToCustomer(@RequestParam Long customerId, @RequestParam Long vehicleId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (customer != null && vehicle != null) {
            customer.addVehicle(vehicle);
            customerRepository.save(customer);
            return ResponseEntity.ok("Vehicle added to customer");
        }

        return ResponseEntity.badRequest().body("No such customer och vehicle");
    }

    /**
     * We are convering the domain-entities to DTO's to prevent infinite recursion.
     * Also to modify output to exclude unnecessary data.
     * @return all customers together with their vehicles
     */
    @GetMapping("/customers")
    public List<CustomerDTO> allCustomersWithVehicles() {
        List<Object[]> all = customerRepository.findAllCustomersWithVehicles();
        Map<Long, CustomerDTO> customerMap = new HashMap<>();

        for (Object[] result : all) {
            Customer customer = (Customer) result[0];
            Vehicle vehicle = (Vehicle) result[1];

            CustomerDTO customerDTO = customerMap.getOrDefault(customer.getId(), new CustomerDTO());
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());

            if (vehicle != null) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                vehicleDTO.setId(vehicle.getId());
                vehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());
                vehicleDTO.setBrand(vehicle.getBrand());
                vehicleDTO.setModel(vehicle.getModel());
                vehicleDTO.setProductionYear(vehicle.getProductionYear());
                vehicleDTO.setCustomerId(customer.getId());

                customerDTO.getVehicles().add(vehicleDTO);
            }
            customerMap.put(customer.getId(), customerDTO);
        }
        return new ArrayList<>(customerMap.values());
    }

    /**
     * Again we are convering the domain-entities to DTO's to prevent infinite recursion.
     * Also to modify output to exclude unnecessary data.
     * @return all vehicles
     */
    @GetMapping("/vehicles")
    public List<VehicleDTO> allVehicles() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        List<VehicleDTO> dtos = new ArrayList<>();

        for (Vehicle v : allVehicles) {
            VehicleDTO dto = new VehicleDTO();
            dto.setId(v.getId());
            dto.setRegistrationNumber(v.getRegistrationNumber());
            dto.setBrand(v.getBrand());
            dto.setModel(v.getModel());
            dto.setProductionYear(v.getProductionYear());
            if (v.getCustomer() != null) {
                dto.setCustomerId(v.getCustomer().getId());
            }
            dtos.add(dto);
        }

        return dtos;
    }

    /**
     *
     * @param brand of vehicles to be listed
     * @return all vehicles of the parametrized brand
     */
    @GetMapping("/brand")
    public ResponseEntity<List<Vehicle>> allVehiclesByBrand(@RequestParam String brand) {
        List<Vehicle> vehiclesByBrand = vehicleRepository.findAllVehiclesByBrand(brand);

        if (vehiclesByBrand != null) {
            return ResponseEntity.ok(vehiclesByBrand);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param customerName to find id for
     * @return id of the customer by name
     */
    @GetMapping("get-customer-id")
    public ResponseEntity<Long> getCustomerId(@RequestParam String customerName) {
        Customer customer = customerRepository.findByName(customerName);

        if (customer != null) {
            return ResponseEntity.ok(customer.getId());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param customerId to find related vehicles to
     * @return all vehicles for the customer with matching id
     */
    @GetMapping("get-vehicles-for-customer")
    public List<Vehicle> getVehiclesForCustomer(@RequestParam Long customerId) {
        return customerRepository.findVehiclesByCustomerId(customerId);
    }
}
