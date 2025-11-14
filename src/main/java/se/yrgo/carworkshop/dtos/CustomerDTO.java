package se.yrgo.carworkshop.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO-class for customers to avoid infinite-recursion and limit data-output
 */
public class CustomerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<VehicleDTO> vehicles = new ArrayList<>();

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

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
