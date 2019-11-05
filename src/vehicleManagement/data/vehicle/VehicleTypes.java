package vehicleManagement.data.vehicle;

public enum VehicleTypes {
    CAR("Car"),
    MOTORCYCLE("Motorcycle"),
    TRANSPORTER("Transporter");

    private final String vehicleType;

    VehicleTypes(String vehicleName) {
        this.vehicleType = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
