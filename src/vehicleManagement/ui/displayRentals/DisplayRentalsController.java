package vehicleManagement.ui.displayRentals;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.services.RentalService;

import java.util.List;

public class DisplayRentalsController {
    //Data
    private RentalService rentalService = GlobalVars.rService;

    //UI
    @FXML JFXButton testButton;

    public void testAction() {
        System.out.println("Rentals Button");
    }
}
