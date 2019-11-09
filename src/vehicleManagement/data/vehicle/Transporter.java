package vehicleManagement.data.vehicle;

import java.time.LocalDate;

public class Transporter extends Vehicle {
    protected double heightInCm;
    protected double loadingWeightInKG;

    public Transporter() {
        super.type = "Transporter";
    }

    public Transporter(Vehicle vehicle) {
        super(vehicle.idNumber, vehicle.make, vehicle.model, vehicle.ccm, vehicle.fuelType, vehicle.exteriorColor, vehicle.millage, vehicle.licensePlate, vehicle.numberOfSeats, vehicle.vehicleCategory, vehicle.dateOfPurchase, vehicle.priceOfPurchase, vehicle.availableFrom, vehicle.availableUntil, vehicle.availability);
        super.type = "Transporter";
    }

    public Transporter(Vehicle vehicle, double heightInCm, double loadingWeightInKG) {
        this(vehicle);
        this.heightInCm = heightInCm;
        this.loadingWeightInKG = loadingWeightInKG;
    }

    public Transporter(String idNumber, String make, String model, int ccm, FuelType fuelType, String exteriorColor, int millage, String licensePlate, int numberOfSeats, VehicleCategory vehicleCategory, LocalDate dateOfPurchase, Double priceOfPurchase, LocalDate availableFrom, LocalDate availableUntil, boolean availability, double heightInCm, double loadingWeightInKG) {
        super(idNumber, make, model, ccm, fuelType, exteriorColor, millage, licensePlate, numberOfSeats, vehicleCategory, dateOfPurchase, priceOfPurchase, availableFrom, availableUntil, availability);
        this.heightInCm = heightInCm;
        this.loadingWeightInKG = loadingWeightInKG;
        super.type = "Transporter";
    }

    public double getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(double heightInCm) {
        this.heightInCm = heightInCm;
    }

    public double getLoadingWeightInKG() {
        return loadingWeightInKG;
    }

    public void setLoadingWeightInKG(double loadingWeightInKG) {
        this.loadingWeightInKG = loadingWeightInKG;
    }
}
