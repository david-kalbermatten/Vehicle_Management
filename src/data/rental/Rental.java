package data.rental;

import data.vehicle.Vehicle;

import java.util.Date;

public class Rental {
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getRentedFrom() {
        return rentedFrom;
    }

    public void setRentedFrom(Date rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

    public Date getRentedUntil() {
        return rentedUntil;
    }

    public void setRentedUntil(Date rentedUntil) {
        this.rentedUntil = rentedUntil;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
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

    public Date getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(Date customerBirthday) {
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
    private Date rentedFrom;
    private Date rentedUntil;
    private double rentalPrice;
    private RentalStatus status;

    //Customer Values
    private String customerName;
    private String customerSurname;
    private String customerStreet;
    private String customerPLZ;
    private String customerArea;
    private String customerPhone;
    private String customerEmail;
    private Date customerBirthday;
    private String customerLicenseID;

}
