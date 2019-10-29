package data;

public enum FuelType {
    BENZIN("Benzin"),
    DIESL("Diesel"),
    ELECTRO("Electro");

    private final String value;

    FuelType(String fuelType) {
        value = fuelType;
    }

    public String getValue() {
        return value;
    }
}
