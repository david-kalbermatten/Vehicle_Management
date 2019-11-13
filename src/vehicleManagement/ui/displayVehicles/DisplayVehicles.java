package vehicleManagement.ui.displayVehicles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.services.VehicleService;
import vehicleManagement.ui.InterfaceInitializer;
import vehicleManagement.ui.mainScreen.MainScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayVehicles implements Initializable {
    public AnchorPane root;
    public JFXComboBox vehicleType;
    public JFXTextField make;
    public JFXTreeTableView vehicleTable;
    public JFXButton confirmButton;
    //Data
    private VehicleService vehicleService;

    private List<Control> inputFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleService = GlobalVars.vService;
        defineInputFieldList();
        GlobalVars.resizeStage(root);
        InterfaceInitializer.initializeVehicleTableView(vehicleTable);
        InterfaceInitializer.populateTableView(vehicleService.vehicleList, vehicleTable);
    }

    public void confirm() {
        if(InterfaceInitializer.isAllSet(inputFieldList)) {
            try {
                GlobalVars.inVehicleEditMode = true;
                GlobalVars.vehicleToEdit = (Vehicle) vehicleTable.getTreeItem(vehicleTable.getSelectionModel().getFocusedIndex()).getValue();
                Parent editView = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
                ((BorderPane)root.getParent()).setCenter(editView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ValidatorService.showSnackbar("No Vehicle selected", root);
        }

    }

    private void defineInputFieldList() {
        inputFieldList = new ArrayList<>(
                Arrays.asList(
                        vehicleTable
                )
        );
    }
}
