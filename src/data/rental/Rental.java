package data.rental;

import data.vehicle.Vehicle;

import java.util.Date;

public class Rental {
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
