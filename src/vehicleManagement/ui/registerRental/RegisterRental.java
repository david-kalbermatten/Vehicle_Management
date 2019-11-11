package vehicleManagement.ui.registerRental;

import com.jfoenix.controls.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.rental.RentalStatus;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.data.vehicle.VehicleTypes;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.services.VehicleService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterRental implements Initializable {

    VehicleService vehicleService = GlobalVars.vService;
    RentalService rentalService = GlobalVars.rService;
    private List<Control> inputFieldList;
    private List<Vehicle> shownVehicles;

    public AnchorPane root;
    public JFXComboBox<VehicleTypes> vehicleType;
    public JFXButton confirmButton;

    public JFXTreeTableView vehicleTable;
    public JFXDatePicker rentedFrom;
    public JFXDatePicker rentedUntil;
    public JFXTextField rentalPrice;
    public JFXComboBox<RentalStatus> rentalStatus;
    public JFXTextField surname;
    public JFXTextField name;
    public JFXTextField street;
    public JFXTextField plz;
    public JFXTextField area;
    public JFXTextField phone;
    public JFXTextField email;
    public JFXDatePicker birthday;
    public JFXTextField licenseId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeInputFieldList();
        initializeUI(GlobalVars.inRentalEditMode);
        GlobalVars.resizeStage(root);
    }

    public void confirm() {
        if(isAllSet()) {
            saveRental();
            ValidatorService.showSnackbar("Successfully Saved Rental", root);
        } else {
            ValidatorService.showSnackbar("Not All Set", root);
        }
    }

    private void initializeUI(boolean isInEditMode) {
        vehicleType.getItems().setAll(VehicleTypes.values());
        rentalStatus.getItems().setAll(RentalStatus.values());
        ValidatorService.setInputFieldToInteger(plz);
        ValidatorService.setInputFieldToDouble(rentalPrice);
        initializeTreeTableView();

        if(isInEditMode) {
            confirmButton.setText("Update Rental");
            fillInInputFields(GlobalVars.rentalToEdit);
        } else {
            confirmButton.setText("Register Rental");
        }
    }

    private boolean isAllSet() {
        for (Object input : inputFieldList) {
            if(input instanceof JFXTextField) {
                if(((JFXTextField) input).getText().isEmpty()) return false;
            } else if(input instanceof JFXDatePicker) {
                if(((JFXDatePicker) input).getValue() == null) return false;
            } else if(input instanceof JFXComboBox) {
                if(((JFXComboBox) input).getValue() == null) return false;
            }
        }
        return !vehicleTable.getSelectionModel().getSelectedCells().isEmpty();
    }

    private void saveRental() {
        Rental tmpRental = new Rental();

        //Set Rental Information
        Vehicle sV = (Vehicle) vehicleTable.getTreeItem(vehicleTable.getSelectionModel().getSelectedIndex()).getValue();
        for (Vehicle vehicle : vehicleService.vehicleList) {
             if(sV == vehicle) {
                 tmpRental.setVehicle(vehicle);
                 System.out.println("Found a match for selected vehicle");
             }
        }
        tmpRental.setRentedFrom(rentedFrom.getValue());
        tmpRental.setRentedUntil(rentedUntil.getValue());
        tmpRental.setRentalPrice(Double.parseDouble(rentalPrice.getText()));
        tmpRental.setStatus(rentalStatus.getValue());
        //Set Customer Information
        tmpRental.setCustomerName(name.getText());
        tmpRental.setCustomerSurname(surname.getText());
        tmpRental.setCustomerStreet(street.getText());
        tmpRental.setCustomerPLZ(plz.getText());
        tmpRental.setCustomerArea(area.getText());
        tmpRental.setCustomerPhone(phone.getText());
        tmpRental.setCustomerEmail(email.getText());
        tmpRental.setCustomerBirthday(birthday.getValue());
        tmpRental.setCustomerLicenseID(licenseId.getText());

        if(GlobalVars.inRentalEditMode) {
            rentalService.updateRental(GlobalVars.rentalToEdit, tmpRental);
        } else {
            rentalService.addRental(tmpRental);
        }
    }

    private void fillInInputFields(Rental rentalToEdit) {
        rentedFrom.setValue(rentalToEdit.getRentedFrom());
        rentedUntil.setValue(rentalToEdit.getRentedUntil());
        rentalPrice.setText(rentalToEdit.getRentalPrice().toString());
        rentalStatus.setValue(rentalToEdit.getStatus());
        surname.setText(rentalToEdit.getCustomerSurname());
        name.setText(rentalToEdit.getCustomerName());
        street.setText(rentalToEdit.getCustomerStreet());
        plz.setText(rentalToEdit.getCustomerPLZ());
        phone.setText(rentalToEdit.getCustomerPhone());
        email.setText(rentalToEdit.getCustomerEmail());
        birthday.setValue(rentalToEdit.getCustomerBirthday());
        licenseId.setText(rentalToEdit.getCustomerLicenseID());
    }

    private void initializeInputFieldList() {
        inputFieldList = new ArrayList<>(
                Arrays.asList(
                        rentedFrom,
                        rentedUntil,
                        rentalPrice,
                        rentalStatus,
                        surname,
                        name,
                        street,
                        plz,
                        phone,
                        email,
                        birthday,
                        licenseId
                )
        );
    }

    private void initializeTreeTableView() {
        //Prepare Vehicle Table View
        JFXTreeTableColumn<Vehicle, String> columnId = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Vehicle, String> columnMake = new JFXTreeTableColumn<>("Make");
        JFXTreeTableColumn<Vehicle, String> columnModel = new JFXTreeTableColumn<>("Model");
        JFXTreeTableColumn<Vehicle, Integer> columnCcm = new JFXTreeTableColumn<>("ccm");

        columnId.setCellValueFactory(new TreeItemPropertyValueFactory<>("idNumber"));
        columnMake.setCellValueFactory(new TreeItemPropertyValueFactory<>("make"));
        columnModel.setCellValueFactory(new TreeItemPropertyValueFactory<>("model"));
        columnCcm.setCellValueFactory(new TreeItemPropertyValueFactory<>("ccm"));

        vehicleTable.getColumns().addAll(columnId, columnMake, columnModel, columnCcm);

        selectVehicleType();
    }

    public void selectVehicleType() {
        shownVehicles = vehicleService.getFilteredList(vehicleType.getValue());
        TreeItem vehicles = new TreeItem<>();
        shownVehicles.forEach(v -> {
            vehicles.getChildren().add(new TreeItem<>(v));
        });
        vehicleTable.setRoot(vehicles);
        vehicleTable.setShowRoot(false);
    }
}
