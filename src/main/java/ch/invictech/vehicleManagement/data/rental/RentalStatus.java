package ch.invictech.vehicleManagement.data.rental;

public enum RentalStatus {
    OPEN("offen"),
    PAYED("bezahlt"),
    CLOSED("abgeschlossen");

    private final String rentalStatus;

    RentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }
}
