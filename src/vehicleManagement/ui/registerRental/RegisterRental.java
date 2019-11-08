package vehicleManagement.ui.registerRental;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import vehicleManagement.data.rental.RentalStatus;
import vehicleManagement.services.ValidatorService;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterRental implements Initializable {


    public JFXDatePicker rentedFrom;
    public JFXDatePicker rentedUntil;
    public JFXTextField rentalPrice;
    public JFXComboBox rentalStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ValidatorService.setInputFieldToDouble(rentalPrice);
        rentalStatus.getItems().setAll(RentalStatus.values());
    }
}
