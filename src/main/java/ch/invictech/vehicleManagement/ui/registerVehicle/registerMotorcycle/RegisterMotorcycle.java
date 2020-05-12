package ch.invictech.vehicleManagement.ui.registerVehicle.registerMotorcycle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ch.invictech.vehicleManagement.supportClasses.ViewHelper;
import ch.invictech.vehicleManagement.ui.registerVehicle.RegisterVehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterMotorcycle implements Initializable {
    @FXML JFXCheckBox hasSatchel;
    @FXML JFXTextField fuelCapacity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterVehicle.fuelCapacity = fuelCapacity;
        RegisterVehicle.hasSatchel = hasSatchel;

        ViewHelper.setInputFieldToInteger(fuelCapacity);
    }
}
