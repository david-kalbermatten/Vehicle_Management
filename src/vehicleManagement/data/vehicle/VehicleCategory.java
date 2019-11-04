package vehicleManagement.data.vehicle;

public enum VehicleCategory {
    BASIC("Basic"),
    MEDIUM("Medium"),
    LUXURY("Luxus");

    private final String vehicleCategory;

    VehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getValue() {
        return vehicleCategory;
    }
}
