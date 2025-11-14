package se.yrgo.carworkshop.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.yrgo.carworkshop.data.CustomerRepository;
import se.yrgo.carworkshop.domain.Customer;
import se.yrgo.carworkshop.domain.Vehicle;

/**
 * This class just adds some dummy data.
 * Same names cannot be added here but this is just a test to see that the service works.
 * Otherwise we would also need to check if vehicles with same registration-number also exist.
 */
@Component
public class DemoCustomerAndVehicleCreator implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public DemoCustomerAndVehicleCreator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.findByName("Lars") == null
                && customerRepository.findByName("Eva") == null
                && customerRepository.findByName("Gustav") == null) {
        Customer lars = new Customer("Lars", "0732425235");
        Customer eva = new Customer("Eva", "0766425235");
        Customer gustav = new Customer("Gustav", "0777425235");

        Vehicle saab = new Vehicle("WNL108", "Saab", "95", 1999);
        Vehicle volvo1 = new Vehicle("SSL508", "Volvo", "S80", 2010);
        Vehicle volvo2 = new Vehicle("G7L708", "Volvo", "V90", 2020);
        Vehicle ford = new Vehicle("HBO645", "Ford", "Fiesta", 2015);

        lars.addVehicle(saab);
        eva.addVehicle(volvo1);
        eva.addVehicle(volvo2);
        gustav.addVehicle(ford);

            customerRepository.save(lars);
            customerRepository.save(eva);
            customerRepository.save(gustav);
        }

    }
}