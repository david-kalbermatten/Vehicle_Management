package vehicleManagement.ui.registerVehicle.registerMotorcycle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.ui.registerVehicle.RegisterVehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterMotorcycle implements Initializable {
    @FXML JFXCheckBox hasSatchel;
    @FXML JFXTextField fuelCapacity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterVehicle.fuelCapacity = fuelCapacity;
        RegisterVehicle.hasSatchel = hasSatchel;

        ValidatorService.setInputFieldToInteger(fuelCapacity);
    }
}
