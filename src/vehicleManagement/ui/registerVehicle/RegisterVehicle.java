package vehicleManagement.ui.registerVehicle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.skins.JFXComboBoxListViewSkin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.StringConverter;
import vehicleManagement.Main;
import vehicleManagement.data.vehicle.FuelType;
import vehicleManagement.data.vehicle.VehicleCategory;
import vehicleManagement.services.VehicleService;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterVehicle implements Initializable {
    private VehicleService vehicleService = Main.vService;

    @FXML JFXComboBox<FuelType> fuelType;
    @FXML JFXComboBox<VehicleCategory> vehicleCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fuelType.getItems().setAll(FuelType.values());
        vehicleCategory.getItems().setAll(VehicleCategory.values());
    }

    public void confirm() {

    }
}


