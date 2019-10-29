package data;

public enum VehicleCategory {
    BASIC("Basic"),
    MEDIUM("Medium"),
    LUXURY("Luxus");

    private final String vehicleCategory;

    VehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }
    
    public String getVehicleCategory() {
        return vehicleCategory;
    }
}