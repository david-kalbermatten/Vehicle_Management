package vehicleManagement.ui.displayVehicles;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import vehicleManagement.Main;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.VehicleService;
import vehicleManagement.ui.main.MainScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayVehiclesController implements Initializable {
    //Data
    private VehicleService vehicleService = Main.vService;
    private List<Vehicle> vehicles = vehicleService.vehicleList;

    //UI
    @FXML JFXButton testButton;
    MainScreenController mainScreenController;

    public void testAction() {
        System.out.println("Vehicle Button");
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
