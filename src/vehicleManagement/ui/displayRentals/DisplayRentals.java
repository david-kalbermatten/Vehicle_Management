package vehicleManagement.ui.displayRentals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.ui.InterfaceInitializer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayRentals implements Initializable {
    private RentalService rentalService;

    public AnchorPane root;
    public JFXTreeTableView rentalTable;

    private List<Control> inputFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rentalService = GlobalVars.rService;
        defineInputFieldList();
        GlobalVars.resizeStage(root);
        InterfaceInitializer.initializeRentalTableView(rentalTable);
        InterfaceInitializer.populateTableView(rentalService.rentalList, rentalTable);
    }


    public void confirm() {
        if(InterfaceInitializer.isAllSet(inputFieldList)) {
            try {
                GlobalVars.inRentalEditMode = true;
                GlobalVars.rentalToEdit = (Rental) rentalTable.getTreeItem(rentalTable.getSelectionModel().getSelectedIndex()).getValue();
                Parent editView = FXMLLoader.load(getClass().getResource("../registerRental/registerRental.fxml"));
                ((BorderPane)root.getParent()).setCenter(editView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ValidatorService.showSnackbar("No Rental Selected", root);
        }

    }

    private void defineInputFieldList() {
        inputFieldList = new ArrayList<>(
                Arrays.asList(
                        rentalTable
                )
        );
    }
}
