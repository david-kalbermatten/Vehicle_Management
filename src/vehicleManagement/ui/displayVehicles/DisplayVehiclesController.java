package vehicleManagement.ui.displayVehicles;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.VehicleService;
import vehicleManagement.ui.main.MainScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayVehiclesController implements Initializable {
    public AnchorPane root;
    //Data
    private VehicleService vehicleService = GlobalVars.vService;

    //UI
    @FXML JFXButton testButton;
    MainScreenController mainScreenController;

    public void testAction() {
        System.out.println("Vehicle Button");
        try {
            GlobalVars.inVehicleEditMode = false;
            GlobalVars.vehicleToEdit = null;
            Parent rootParent = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
            ((BorderPane)root.getParent()).setCenter(rootParent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vehicleManagement/ui/main/mainScreen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainScreenController = fxmlLoader.getController();
    }
}
