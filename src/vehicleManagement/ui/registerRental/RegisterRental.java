package vehicleManagement.ui.registerRental;

import com.jfoenix.controls.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.rental.RentalStatus;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.services.VehicleService;

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
    public JFXComboBox vehicleType;
    public JFXButton confirmButton;

    public JFXTreeTableView<Vehicle> vehicleTable;
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
        ValidatorService.setInputFieldToInteger(plz);
        ValidatorService.setInputFieldToDouble(rentalPrice);
        rentalStatus.getItems().setAll(RentalStatus.values());
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
        if(isInEditMode) {
            confirmButton.setText("Update Rental");
        } else {
            confirmButton.setText("Register Rental");
        }
        JFXTreeTableColumn<Vehicle, String> columnId = new JFXTreeTableColumn<>("ID");
        columnId.setCellValueFactory(new TreeItemPropertyValueFactory<>("idNumber"));
        JFXTreeTableColumn<Vehicle, String> columnMake = new JFXTreeTableColumn<>("Make");
        columnMake.setCellValueFactory(new TreeItemPropertyValueFactory<>("make"));
        JFXTreeTableColumn<Vehicle, String> columnModel = new JFXTreeTableColumn<>("Model");
        columnModel.setCellValueFactory(new TreeItemPropertyValueFactory<>("model"));
        JFXTreeTableColumn<Vehicle, Integer> columnCcm = new JFXTreeTableColumn<>("ccm");
        columnCcm.setCellValueFactory(new TreeItemPropertyValueFactory<>("ccm"));

        vehicleTable.getColumns().addAll(columnId, columnMake, columnModel, columnCcm);
        TreeItem vehicles = new TreeItem<>();

        shownVehicles = new ArrayList<>(vehicleService.vehicleList);


        shownVehicles.forEach(v -> {
            vehicles.getChildren().add(new TreeItem<>(v));
        });
        vehicleTable.setRoot(vehicles);
        vehicleTable.setShowRoot(false);

        if(isInEditMode) {
            Rental rentalToEdit = GlobalVars.rentalToEdit;

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
    }

    private boolean isAllSet() {
        //TODO check if vehicle is selected from table
        for (Object input : inputFieldList) {
            if(input instanceof JFXTextField) {
                if(((JFXTextField) input).getText().isEmpty()) return false;
            } else if(input instanceof JFXDatePicker) {
                if(((JFXDatePicker) input).getValue() == null) return false;
            } else if(input instanceof JFXComboBox) {
                if(((JFXComboBox) input).getValue() == null) return false;
            }
        }
        if(vehicleTable.getSelectionModel().getSelectedCells().get(0).getTreeItem().getValue() == null) return false;
        return true;
    }

    private void saveRental() {
        Rental tmpRental = new Rental();
        tmpRental.setVehicle(vehicleTable.getSelectionModel().getSelectedCells().get(0).getTreeItem().getValue());
        tmpRental.setRentedFrom(rentedFrom.getValue());
        tmpRental.setRentedUntil(rentedUntil.getValue());
        tmpRental.setRentalPrice(Double.parseDouble(rentalPrice.getText()));
        tmpRental.setStatus(rentalStatus.getValue());

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
}
