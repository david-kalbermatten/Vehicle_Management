package vehicleManagement.ui.displayRentals;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import vehicleManagement.Main;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.services.RentalService;

import java.util.List;

public class DisplayRentalsController {
    //Data
    private RentalService rentalService = Main.rService;
    private List<Rental> rentals = rentalService.rentalList;

    //UI
    @FXML JFXButton testButton;

    public void testAction() {
        System.out.println("Rentals Button");
    }
}
