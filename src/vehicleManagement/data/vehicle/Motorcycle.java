package vehicleManagement.data.vehicle;

import java.time.LocalDate;

public class Motorcycle extends Vehicle {
    protected int fuelCapacity;
    protected boolean hasSatchel;

    public Motorcycle() {

    }

    public Motorcycle(Vehicle vehicle) {
        super(vehicle.idNumber, vehicle.make, vehicle.model, vehicle.ccm, vehicle.fuelType, vehicle.exteriorColor, vehicle.millage, vehicle.licensePlate, vehicle.numberOfSeats, vehicle.vehicleCategory, vehicle.dateOfPurchase, vehicle.priceOfPurchase, vehicle.availableFrom, vehicle.availableUntil, vehicle.availability);

    }

    public Motorcycle(Vehicle vehicle, int fuelCapacity, boolean hasSatchel) {
        this(vehicle);
        this.fuelCapacity = fuelCapacity;
        this.hasSatchel = hasSatchel;
    }

    public Motorcycle(String idNumber, String make, String model, int ccm, FuelType fuelType, String exteriorColor, int millage, String licensePlate, int numberOfSeats, VehicleCategory vehicleCategory, LocalDate dateOfPurchase, Double priceOfPurchase, LocalDate availableFrom, LocalDate availableUntil, boolean availability, int fuelCapacity, boolean hasSatchel) {
        super(idNumber, make, model, ccm, fuelType, exteriorColor, millage, licensePlate, numberOfSeats, vehicleCategory, dateOfPurchase, priceOfPurchase, availableFrom, availableUntil, availability);
        this.fuelCapacity = fuelCapacity;
        this.hasSatchel = hasSatchel;

    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public boolean isHasSatchel() {
        return hasSatchel;
    }

    public void setHasSatchel(boolean hasSatchel) {
        this.hasSatchel = hasSatchel;
    }
}
