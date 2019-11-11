package vehicleManagement.services;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalService {
    public List<Rental> rentalList = new ArrayList<>();

    public RentalService() {
        //rentalList.addListener((ListChangeListener<Rental>) c -> GlobalVars.pService.writeFile());
    }

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

    public void updateRental(Rental oldRental, Rental newRental) {
        rentalList.set(rentalList.indexOf(oldRental), newRental);
    }


}
