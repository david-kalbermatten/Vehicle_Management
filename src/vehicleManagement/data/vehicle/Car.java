package vehicleManagement.data.vehicle;

import java.util.Date;

public class Car extends Vehicle {
    protected int trunkSpace;
    protected CarType carType;
    protected boolean hasSatNav;

    public Car() {}

    public Car(Vehicle vehicle, int trunkSpace, CarType carType, boolean hasSatNav) {
        super(vehicle.idNumber, vehicle.make, vehicle.model, vehicle.ccm, vehicle.fuelType, vehicle.exteriorColor, vehicle.millage, vehicle.licensePlate, vehicle.numberOfSeats, vehicle.vehicleCategory, vehicle.dateOfPurchase, vehicle.priceOfPurchase, vehicle.availableFrom, vehicle.availableUntil, vehicle.availability);
        this.trunkSpace = trunkSpace;
        this.carType = carType;
        this.hasSatNav = hasSatNav;
    }

    public Car(String idNumber, String make, String model, int ccm, FuelType fuelType, String exteriorColor, int millage, String licensePlate, int numberOfSeats, VehicleCategory vehicleCategory, Date dateOfPurchase, Double priceOfPurchase, Date availableFrom, Date availableUntil, boolean availability, int trunkSpace, CarType carType, boolean hasSatNav) {
        super(idNumber, make, model, ccm, fuelType, exteriorColor, millage, licensePlate, numberOfSeats, vehicleCategory, dateOfPurchase, priceOfPurchase, availableFrom, availableUntil, availability);
        this.trunkSpace = trunkSpace;
        this.carType = carType;
        this.hasSatNav = hasSatNav;
    }

    public int getTrunkSpace() {
        return trunkSpace;
    }

    public void setTrunkSpace(int trunkSpace) {
        this.trunkSpace = trunkSpace;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public boolean isHasSatNav() {
        return hasSatNav;
    }

    public void setHasSatNav(boolean hasSatNav) {
        this.hasSatNav = hasSatNav;
    }
}
