package vehicleManagement.ui.displayRentals;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.rental.RentalStatus;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.supportClasses.ViewHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayRentals implements Initializable {
    public JFXComboBox<RentalStatus> rentalStatus;
    public JFXTextField customerName;
    public JFXDatePicker rentalDate;
    private RentalService rentalService;

    public AnchorPane root;
    public JFXTreeTableView rentalTable;

    private List<Control> inputFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rentalService = GlobalVars.rService;
        defineInputFieldList();
        GlobalVars.resizeStage(root);
        rentalStatus.getItems().addAll(RentalStatus.values());
        customerName.textProperty().addListener(observable -> populateTableView());
        rentalDate.valueProperty().addListener(observable -> populateTableView());

        ViewHelper.initializeRentalTableView(rentalTable);
        ViewHelper.populateTableView(rentalService.rentalList, rentalTable);
    }


    public void confirm() {
        if(ViewHelper.isAllSet(inputFieldList)) {
            try {
                GlobalVars.inRentalEditMode = true;
                GlobalVars.rentalToEdit = (Rental) rentalTable.getTreeItem(rentalTable.getSelectionModel().getSelectedIndex()).getValue();
                Parent editView = FXMLLoader.load(getClass().getResource("../registerRental/registerRental.fxml"));
                ((BorderPane)root.getParent()).setCenter(editView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ValidatorService.showSnackbar("No Rental selected", root);
        }

    }

    private void defineInputFieldList() {
        inputFieldList = new ArrayList<>(
                Arrays.asList(
                        rentalTable
                )
        );
    }

    public void delete() {
        if(ViewHelper.isAllSet(inputFieldList)) {
            rentalService.removeRental((Rental) rentalTable.getTreeItem(rentalTable.getSelectionModel().getFocusedIndex()).getValue());
            populateTableView();
        } else {
            ValidatorService.showSnackbar("No Rental selected", root);
        }
    }

    public void populateTableView() {
        ViewHelper.populateTableView(rentalService.getFilteredList(rentalStatus.getValue(), customerName.getText(), rentalDate.getValue()), rentalTable);
    }

    public void resetFilters() {
        rentalStatus.getSelectionModel().select(-1);
        customerName.setText("");
        rentalDate.setValue(null);
    }
}
