package vehicleManagement.ui.registerRental;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.rental.RentalStatus;
import vehicleManagement.data.vehicle.*;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.services.VehicleService;
import vehicleManagement.ui.InterfaceInitializer;

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
    public JFXComboBox<VehicleCategory> vehicleCategory;
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
        if(InterfaceInitializer.isAllSet(inputFieldList)) {
            saveRental();
            try {
                ValidatorService.showSnackbar("Successfully Saved Vehicle", ((BorderPane) root.getParent()));
                Parent view = FXMLLoader.load(getClass().getResource("../displayRentals/displayRentals.fxml"));
                ((BorderPane) root.getParent()).setCenter(view);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ValidatorService.showSnackbar("Not All Set", root);
        }
    }

    private void initializeUI(boolean isInEditMode) {
        vehicleType.getItems().setAll(VehicleTypes.values());
        rentalStatus.getItems().setAll(RentalStatus.values());
        ValidatorService.setInputFieldToInteger(plz);
        ValidatorService.setInputFieldToDouble(rentalPrice);
        InterfaceInitializer.initializeVehicleTableView(vehicleTable);
        InterfaceInitializer.populateTableView(vehicleService.getFilteredList(vehicleType.getValue(), vehicleCategory.getValue(), false), vehicleTable);

        if(isInEditMode) {
            confirmButton.setText("Update Rental");
            fillInInputFields(GlobalVars.rentalToEdit);
        } else {
            confirmButton.setText("Register Rental");
        }
    }

    private void saveRental() {
        Rental tmpRental = null;
        if(GlobalVars.inRentalEditMode) {
            tmpRental = GlobalVars.rentalToEdit;
        } else {
            tmpRental = new Rental();
        }
        //Set Rental Information
        Object selectedVehicle = vehicleTable.getTreeItem(vehicleTable.getSelectionModel().getSelectedIndex()).getValue();
        if(selectedVehicle instanceof Car) tmpRental.setVehicle((Car) selectedVehicle);
        if(selectedVehicle instanceof Motorcycle) tmpRental.setVehicle((Motorcycle) selectedVehicle);
        if(selectedVehicle instanceof Transporter) tmpRental.setVehicle((Transporter) selectedVehicle);
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

        if (!GlobalVars.inRentalEditMode) {
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

        InterfaceInitializer.setSelectedOnTableView(vehicleTable, rentalToEdit.getVehicle());
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
                        licenseId,
                        vehicleTable
                )
        );
    }

    public void populateTableView() {
        InterfaceInitializer.populateTableView(vehicleService.getFilteredList(vehicleType.getValue(), vehicleCategory.getValue(), false), vehicleTable);
    }
}
