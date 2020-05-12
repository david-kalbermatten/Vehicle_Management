package vehicleManagement.ui.mainScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import vehicleManagement.GlobalVars;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.VehicleService;

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
            Parent root = FXMLLoader.load(getClass().getResource("../displayVehicles/displayVehicles.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewDisplayRentals() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../displayRentals/displayRentals.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterVehicle() {
        try {
            GlobalVars.inVehicleEditMode = false;
            GlobalVars.vehicleToEdit = null;
            Parent root = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterRental() {
        try {
            GlobalVars.inRentalEditMode = false;
            GlobalVars.rentalToEdit = null;
            Parent root = FXMLLoader.load(getClass().getResource("../registerRental/registerRental.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeProgram() {
        System.exit(0);
    }


}
