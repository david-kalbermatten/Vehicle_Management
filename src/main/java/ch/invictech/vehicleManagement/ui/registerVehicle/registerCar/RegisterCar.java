package ch.invictech.vehicleManagement.ui.registerVehicle.registerCar;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ch.invictech.vehicleManagement.data.vehicle.CarType;
import ch.invictech.vehicleManagement.supportClasses.ViewHelper;
import ch.invictech.vehicleManagement.ui.registerVehicle.RegisterVehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterCar implements Initializable {
    @FXML JFXCheckBox hasSatNav;
    @FXML JFXTextField trunkSpace;
    @FXML JFXComboBox<CarType> carType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterVehicle.trunkSpace = trunkSpace;
        RegisterVehicle.hasSatNav = hasSatNav;
        RegisterVehicle.carType = carType;

        ViewHelper.setInputFieldToInteger(trunkSpace);

        carType.getItems().setAll(CarType.values());

    }
}
