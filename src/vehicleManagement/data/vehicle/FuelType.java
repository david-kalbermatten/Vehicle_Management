package vehicleManagement.data.vehicle;

public enum FuelType {
    BENZIN("Benzin"),
    DIESL("Diesel"),
    ELECTRO("Electro");

    private final String fuelType;

    FuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }
}
