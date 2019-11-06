package vehicleManagement.data.vehicle;

import java.util.Date;

public class Vehicle {
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
    protected Date dateOfPurchase;
    protected Double priceOfPurchase;
    protected Date availableFrom;
    protected Date availableUntil;
    protected boolean availability;

    public Vehicle() { }

    public Vehicle(String idNumber, String make, String model, int ccm, FuelType fuelType, String exteriorColor, int millage, String licensePlate, int numberOfSeats, VehicleCategory vehicleCategory, Date dateOfPurchase, Double priceOfPurchase, Date availableFrom, Date availableUntil, boolean availability) {
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

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Double getPriceOfPurchase() {
        return priceOfPurchase;
    }

    public void setPriceOfPurchase(Double priceOfPurchase) {
        this.priceOfPurchase = priceOfPurchase;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Date availableUntil) {
        this.availableUntil = availableUntil;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
