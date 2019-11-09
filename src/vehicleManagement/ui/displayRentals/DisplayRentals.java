package vehicleManagement.ui.displayRentals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.services.RentalService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayRentals implements Initializable {
    private RentalService rentalService;

    public AnchorPane root;
    public JFXTreeTableView rentalTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rentalService = GlobalVars.rService;
        GlobalVars.resizeStage(root);
    }


    public void confirm() {
        try {
            GlobalVars.inRentalEditMode = false;
            GlobalVars.rentalToEdit = null;
            Parent editView = FXMLLoader.load(getClass().getResource("../registerRental/registerRental.fxml"));
            ((BorderPane)root.getParent()).setCenter(editView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
