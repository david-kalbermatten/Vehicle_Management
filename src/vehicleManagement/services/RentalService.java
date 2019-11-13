package vehicleManagement.services;

import vehicleManagement.data.rental.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalService {
    public List<Rental> rentalList = new ArrayList<>();

    public void addRental(Rental rentalToAdd) {
        rentalList.add(rentalToAdd);
    }

    public void removeRental(Rental rentalToRemove) {
        rentalList.remove(rentalToRemove);
    }

    public List<Rental> searchByCustomerName(String name) {
        List<Rental> resultList = new ArrayList<>(rentalList);
        List<Rental> toRemove = new ArrayList<>();
        rentalList.forEach(rental -> {
            if(rental.getCustomerName().contains(name)) {
                toRemove.add(rental);
            };
        });

        resultList.removeAll(toRemove);
        return resultList;
    }

    public List<Rental> searchByRentalDate(LocalDate date) {
        List<Rental> resultList = new ArrayList<>(rentalList);
        List<Rental> toRemove = new ArrayList<>();
        rentalList.forEach(rental -> {
            if(!(rental.getRentedFrom().compareTo(date) >= 0) && (rental.getRentedUntil().compareTo(date) <= 0)) {
                toRemove.add(rental);
            };
        });

        resultList.removeAll(toRemove);
        return resultList;
    }
}
