package se.yrgo.carworkshop.dtos;

/**
 * A DTO-class for vehicles to avoid infinite-recursion and limit data-output
 */
public class VehicleDTO {
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    private int productionYear;
    private Long customerId; // Returns customerId instead of all details

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Long getCustomerId() {
        if (customerId == null) {
            return null;
        }
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
