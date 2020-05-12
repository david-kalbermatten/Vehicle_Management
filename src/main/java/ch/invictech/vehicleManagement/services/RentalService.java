package ch.invictech.vehicleManagement.services;

import ch.invictech.vehicleManagement.data.rental.Rental;
import ch.invictech.vehicleManagement.data.rental.RentalStatus;
import ch.invictech.vehicleManagement.supportClasses.ViewHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    public List<Rental> rentalList = new ArrayList<>();

    public void addRental(Rental rentalToAdd) {
        rentalList.add(rentalToAdd);
    }

    public void removeRental(Rental rentalToRemove) {
        rentalList.remove(rentalToRemove);
    }

    public List<Rental> getFilteredList(RentalStatus rentalStatus, String customerName, LocalDate rentalDate) {
        List<Rental> list = new ArrayList<>(rentalList);
        List<Rental> itemsToRemove = new ArrayList<>();
        if (rentalStatus != null) {
            list.forEach(rental -> {
                switch (rentalStatus) {
                    case OPEN:
                        if (rental.getStatus() != RentalStatus.OPEN) itemsToRemove.add(rental);
                        break;
                    case PAYED:
                        if (rental.getStatus() != RentalStatus.PAYED) itemsToRemove.add(rental);
                        break;
                    case CLOSED:
                        if (rental.getStatus() != RentalStatus.CLOSED) itemsToRemove.add(rental);
                        break;
                }
            });
            list.removeAll(itemsToRemove);
            itemsToRemove.clear();
        }
        if (!customerName.isEmpty()) {
            list.forEach(rental -> {
                if (!((ViewHelper.containsIgnoreCase(rental.getCustomerName(), customerName)) || ViewHelper.containsIgnoreCase(rental.getCustomerSurname(), customerName))) {
                    itemsToRemove.add(rental);
                }
            });
            list.removeAll(itemsToRemove);
            itemsToRemove.clear();
        }
        if (rentalDate != null) {
            list.forEach(rental -> {
                if (!(rental.getRentedFrom().compareTo(rentalDate) <= 0) && (rental.getRentedUntil().compareTo(rentalDate) >= 0)) {
                    itemsToRemove.add(rental);
                }
            });
            list.removeAll(itemsToRemove);
            itemsToRemove.clear();
        }
        return list;
    }
}
