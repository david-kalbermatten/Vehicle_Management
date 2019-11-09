package vehicleManagement.ui.displayVehicles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.services.VehicleService;
import vehicleManagement.ui.mainScreen.MainScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayVehicles implements Initializable {
    public AnchorPane root;
    public JFXComboBox vehicleType;
    public JFXTextField make;
    public JFXTreeTableView vehicleTable;
    //Data
    private VehicleService vehicleService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleService = GlobalVars.vService;
        GlobalVars.resizeStage(root);
    }

    public void confirm() {
        System.out.println("Vehicle Button");
        try {
            GlobalVars.inVehicleEditMode = false;
            GlobalVars.vehicleToEdit = null;
            Parent editView = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
            ((BorderPane)root.getParent()).setCenter(editView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
