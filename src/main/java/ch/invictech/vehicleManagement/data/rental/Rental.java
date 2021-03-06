package ch.invictech.vehicleManagement.data.rental;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ch.invictech.vehicleManagement.supportClasses.LocalDateDeserializer;
import ch.invictech.vehicleManagement.supportClasses.LocalDateSerializer;
import ch.invictech.vehicleManagement.data.vehicle.Vehicle;

import java.time.LocalDate;

public class Rental {
    public Rental() {

    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getRentedFrom() {
        return rentedFrom;
    }

    public void setRentedFrom(LocalDate rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

    public LocalDate getRentedUntil() {
        return rentedUntil;
    }

    public void setRentedUntil(LocalDate rentedUntil) {
        this.rentedUntil = rentedUntil;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerStreet() {
        return customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerPLZ() {
        return customerPLZ;
    }

    public void setCustomerPLZ(String customerPLZ) {
        this.customerPLZ = customerPLZ;
    }

    public String getCustomerArea() {
        return customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(LocalDate customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerLicenseID() {
        return customerLicenseID;
    }

    public void setCustomerLicenseID(String customerLicenseID) {
        this.customerLicenseID = customerLicenseID;
    }

    //Rental values
    private Vehicle vehicle;
    private int vehicleID;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate rentedFrom;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate rentedUntil;
    private Double rentalPrice;
    private RentalStatus status;

    //Customer Values
    private String customerName;
    private String customerSurname;
    private String customerStreet;
    private String customerPLZ;
    private String customerArea;
    private String customerPhone;
    private String customerEmail;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate customerBirthday;
    private String customerLicenseID;

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }
}
