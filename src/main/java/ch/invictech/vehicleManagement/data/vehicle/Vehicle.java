package ch.invictech.vehicleManagement.data.vehicle;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ch.invictech.vehicleManagement.supportClasses.LocalDateDeserializer;
import ch.invictech.vehicleManagement.supportClasses.LocalDateSerializer;

import java.time.LocalDate;


public abstract class Vehicle {
    protected int id;

    protected String idNumber;
    protected String make;
    protected String model;
    protected int ccm;
    protected FuelType fuelType;
    protected String exteriorColor;
    protected int millage;
    protected String licensePlate;
    protected int numberOfSeats;
    protected VehicleCategory vehicleCategory;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate dateOfPurchase;
    protected Double priceOfPurchase;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate availableFrom;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate availableUntil;
    protected boolean availability;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle() { }

    public Vehicle(String idNumber, String make, String model, int ccm, FuelType fuelType, String exteriorColor, int millage, String licensePlate, int numberOfSeats, VehicleCategory vehicleCategory, LocalDate dateOfPurchase, Double priceOfPurchase, LocalDate availableFrom, LocalDate availableUntil, boolean availability) {
        this();
        this.idNumber = idNumber;
        this.make = make;
        this.model = model;
        this.ccm = ccm;
        this.fuelType = fuelType;
        this.exteriorColor = exteriorColor;
        this.millage = millage;
        this.licensePlate = licensePlate;
        this.numberOfSeats = numberOfSeats;
        this.vehicleCategory = vehicleCategory;
        this.dateOfPurchase = dateOfPurchase;
        this.priceOfPurchase = priceOfPurchase;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
        this.availability = availability;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public int getMillage() {
        return millage;
    }

    public void setMillage(int millage) {
        this.millage = millage;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Double getPriceOfPurchase() {
        return priceOfPurchase;
    }

    public void setPriceOfPurchase(Double priceOfPurchase) {
        this.priceOfPurchase = priceOfPurchase;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(LocalDate availableUntil) {
        this.availableUntil = availableUntil;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
