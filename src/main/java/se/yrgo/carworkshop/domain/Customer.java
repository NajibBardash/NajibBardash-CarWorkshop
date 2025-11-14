package se.yrgo.carworkshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a customer in a car-workshop
 * A customer can have many vehicles.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;

    // Bi-directional relationship with "customer" in the Vehicle-class
    @JsonIgnore
    // Let's me add a customer with vehicle in a cascading manner in the init-class.
    @OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Vehicle> vehicles = new ArrayList<>();

    public Customer() {}

    // Constructor for init-data
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    // Adds a vehicle to the customers list and the customers id in the Vehicle object
    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setCustomer(this);
    }

    // Removes a vehicle from the customers list and the customers id from the Vehicle object
    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
        vehicle.setCustomer(null);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(vehicles, customer.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, vehicles);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
