package vehicleManagement.ui.registerVehicle;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import vehicleManagement.Main;
import vehicleManagement.data.vehicle.*;
import vehicleManagement.services.VehicleService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterVehicle implements Initializable {
    private VehicleService vehicleService = Main.vService;

    @FXML BorderPane subView;
    @FXML JFXComboBox<FuelType> fuelType;
    @FXML JFXComboBox<VehicleCategory> vehicleCategory;
    @FXML JFXComboBox<VehicleTypes> vehicleType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fuelType.getItems().setAll(FuelType.values());
        vehicleCategory.getItems().setAll(VehicleCategory.values());
        vehicleType.getItems().setAll(VehicleTypes.values());
    }

    public void confirm() {

    }

    public void selectVehicleType(ActionEvent actionEvent) {
        VehicleTypes selectedType = vehicleType.getValue();
        switch (selectedType) {
            case CAR:
                subView.setCenter(null);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../registerVehicle/registerCar.fxml"));
                    subView.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MOTORCYCLE:
                subView.setCenter(null);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
                    subView.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case TRANSPORTER:
                subView.setCenter(null);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
                    subView.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

enum VehicleTypes {
    CAR("Car"),
    MOTORCYCLE("Motorcycle"),
    TRANSPORTER("Transporter");

    private final String vehicleType;

    VehicleTypes(String vehicleName) {
        this.vehicleType = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}


