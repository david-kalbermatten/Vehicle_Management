package vehicleManagement.ui.registerVehicle.registerTransporter;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vehicleManagement.supportClasses.ViewHelper;
import vehicleManagement.ui.registerVehicle.RegisterVehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterTransporter implements Initializable {

    @FXML JFXTextField loadingWeightInKG;
    @FXML JFXTextField heightInCm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterVehicle.loadingWeightInKG = loadingWeightInKG;
        RegisterVehicle.heightInCm = heightInCm;

        ViewHelper.setInputFieldToDouble(loadingWeightInKG);
        ViewHelper.setInputFieldToDouble(heightInCm);
    }
}
