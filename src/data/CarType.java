package data;

public enum CarType {
    SMALL("Kleinwagen"),
    LIMO("Limosine"),
    KOMBI("Kombi"),
    SUV("SUV"),
    CABRIO("Cabriolet");

    private final String carType;

    CarType(String carType) {
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }
}
