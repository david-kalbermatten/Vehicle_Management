package ch.invictech.vehicleManagement.ui.mainScreen;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import ch.invictech.vehicleManagement.GlobalVars;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML BorderPane rootElement;
    @FXML JFXButton displayVehicles;
    @FXML VBox sideMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeViewDisplayVehicles();
    }

    public void changeViewDisplayVehicles() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ch/invictech/vehicleManagement/ui/displayVehicles/displayVehicles.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewDisplayRentals() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ch/invictech/vehicleManagement/ui/displayRentals/displayRentals.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterVehicle() {
        try {
            GlobalVars.inVehicleEditMode = false;
            GlobalVars.vehicleToEdit = null;
            Parent root = FXMLLoader.load(getClass().getResource("/ch/invictech/vehicleManagement/ui/registerVehicle/registerVehicle.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterRental() {
        try {
            GlobalVars.inRentalEditMode = false;
            GlobalVars.rentalToEdit = null;
            Parent root = FXMLLoader.load(getClass().getResource("/ch/invictech/vehicleManagement/ui/registerRental/registerRental.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeProgram() {
        System.exit(0);
    }


}
