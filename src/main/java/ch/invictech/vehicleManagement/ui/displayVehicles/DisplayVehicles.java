package ch.invictech.vehicleManagement.ui.displayVehicles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import ch.invictech.vehicleManagement.GlobalVars;
import ch.invictech.vehicleManagement.data.rental.Rental;
import ch.invictech.vehicleManagement.data.vehicle.Vehicle;
import ch.invictech.vehicleManagement.data.vehicle.VehicleCategory;
import ch.invictech.vehicleManagement.data.vehicle.VehicleTypes;
import ch.invictech.vehicleManagement.services.VehicleService;
import ch.invictech.vehicleManagement.supportClasses.ViewHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayVehicles implements Initializable {
    public AnchorPane root;
    public JFXTreeTableView vehicleTable;
    public JFXButton confirmButton;
    public JFXComboBox<VehicleTypes> vehicleType;
    public JFXComboBox<VehicleCategory> vehicleCategory;
    public JFXButton deleteButton;
    //Data
    private VehicleService vehicleService;

    private List<Control> inputFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleService = GlobalVars.vService;
        vehicleType.getItems().setAll(VehicleTypes.values());
        vehicleCategory.getItems().setAll(VehicleCategory.values());
        defineInputFieldList();
        GlobalVars.resizeStage(root);
        ViewHelper.initializeVehicleTableView(vehicleTable);
        populateTableView();
        vehicleTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            for (Rental rental : GlobalVars.rService.rentalList) {
                if(rental.getVehicle() == vehicleTable.getTreeItem(vehicleTable.getSelectionModel().getSelectedIndex()).getValue()) {
                    deleteButton.setDisable(true);
                    break;
                } else {
                    deleteButton.setDisable(false);
                }
            }
        });
    }

    public void confirm() {
        if(ViewHelper.isAllSet(inputFieldList)) {
            try {
                GlobalVars.inVehicleEditMode = true;
                GlobalVars.vehicleToEdit = (Vehicle) vehicleTable.getTreeItem(vehicleTable.getSelectionModel().getFocusedIndex()).getValue();
                Parent editView = FXMLLoader.load(getClass().getResource("../../../../../../resources/ch/invictech/vehicleManagement/ui/registerVehicle/registerVehicle.fxml"));
                ((BorderPane)root.getParent()).setCenter(editView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ViewHelper.showSnackbar("No Vehicle selected", root);
        }

    }

    private void defineInputFieldList() {
        inputFieldList = new ArrayList<>(
                Arrays.asList(
                        vehicleTable
                )
        );
    }

    public void populateTableView() {
        ViewHelper.populateTableView(vehicleService.getFilteredList(vehicleType.getValue(), vehicleCategory.getValue(), true), vehicleTable);
    }

    public void resetFilters() {
        vehicleType.getSelectionModel().select(-1);
        vehicleCategory.getSelectionModel().select(-1);
    }

    public void delete() {
        if(ViewHelper.isAllSet(inputFieldList)) {
            vehicleService.removeVehicle((Vehicle) vehicleTable.getTreeItem(vehicleTable.getSelectionModel().getFocusedIndex()).getValue());
            populateTableView();
        } else {
            ViewHelper.showSnackbar("No Vehicle selected", root);
        }
    }
}
