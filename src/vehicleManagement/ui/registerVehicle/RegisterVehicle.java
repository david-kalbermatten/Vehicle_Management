package vehicleManagement.ui.registerVehicle;

import com.jfoenix.controls.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vehicleManagement.Main;
import vehicleManagement.data.vehicle.*;
import vehicleManagement.services.ValidatorService;
import vehicleManagement.services.VehicleService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterVehicle implements Initializable {
    private VehicleService vehicleService = Main.vService;
    private List<Control> inputFieldList;

    //UI Elements
    public AnchorPane root;
    public BorderPane subView;
    public JFXButton confirmButton;

    //Input fields
    public JFXTextField idNumber;
    public JFXTextField make;
    public JFXTextField model;
    public JFXTextField ccm;
    public JFXTextField exteriorColor;
    public JFXTextField millage;
    public JFXTextField licensePlate;
    public JFXTextField numberOfSeats;
    public JFXDatePicker dateOfPurchase;
    public JFXTextField priceOfPurchase;
    public JFXDatePicker availableFrom;
    public JFXDatePicker availableUntil;
    public JFXCheckBox availability;
    public JFXComboBox<FuelType> fuelType;
    public JFXComboBox<VehicleCategory> vehicleCategory;
    public JFXComboBox<VehicleTypes> vehicleType;

    //Car specific variables
    public static JFXTextField trunkSpace;
    public static JFXComboBox<CarType> carType;
    public static JFXCheckBox hasSatNav;

    //Motorcycle specific variables
    public static JFXTextField fuelCapacity;
    public static JFXCheckBox hasSatchel;

    //Transporter specific variables
    public static JFXTextField loadingWeightInKG;
    public static JFXTextField heightInCm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fuelType.getItems().setAll(FuelType.values());
        vehicleCategory.getItems().setAll(VehicleCategory.values());
        vehicleType.getItems().setAll(VehicleTypes.values());
        initializeInputFieldList();
        initializeUI(Main.inEditMode);

        //InputField Validation
        ValidatorService.setInputFieldToInteger(ccm);
        ValidatorService.setInputFieldToInteger(millage);
        ValidatorService.setInputFieldToInteger(numberOfSeats);
        ValidatorService.setInputFieldToDouble(priceOfPurchase);
    }

    public void confirm() {
        if(isAllSet()) {
            System.out.println("Works! :D");
            saveVehicle(vehicleType.getValue());
        } else {
            ValidatorService.showSnackbar("Not all fields have been set!", root);
        }
    }

    private void saveVehicle(VehicleTypes vehicleType) {
        Vehicle tmpVehicle = new Vehicle();
        tmpVehicle.setIdNumber(idNumber.getText());
        tmpVehicle.setMake(make.getText());
        tmpVehicle.setModel(model.getText());
        tmpVehicle.setCcm(Integer.parseInt(ccm.getText()));
        tmpVehicle.setFuelType(fuelType.getValue());
        tmpVehicle.setExteriorColor(exteriorColor.getText());
        tmpVehicle.setMillage(Integer.parseInt(ccm.getText()));
        tmpVehicle.setLicensePlate(licensePlate.getText());
        tmpVehicle.setNumberOfSeats(Integer.parseInt(numberOfSeats.getText()));
        tmpVehicle.setVehicleCategory(vehicleCategory.getValue());
        tmpVehicle.setDateOfPurchase(dateOfPurchase.getValue());
        tmpVehicle.setPriceOfPurchase(Double.parseDouble(priceOfPurchase.getText()));
        tmpVehicle.setAvailableFrom(availableFrom.getValue());
        tmpVehicle.setAvailableUntil(availableUntil.getValue());
        tmpVehicle.setAvailability(availability.isSelected());

        switch (vehicleType) {
            case CAR:
                tmpVehicle = new Car(tmpVehicle);
                ((Car) tmpVehicle).setCarType(carType.getValue());
                ((Car) tmpVehicle).setTrunkSpace(Integer.parseInt(trunkSpace.getText()));
                ((Car) tmpVehicle).setHasSatNav(hasSatNav.isSelected());
                break;
            case MOTORCYCLE:
                tmpVehicle = new Motorcycle(tmpVehicle);
                ((Motorcycle) tmpVehicle).setFuelCapacity(Integer.parseInt(fuelCapacity.getText()));
                ((Motorcycle) tmpVehicle).setHasSatchel(hasSatchel.isSelected());
                break;
            case TRANSPORTER:
                tmpVehicle = new Transporter(tmpVehicle);
                ((Transporter) tmpVehicle).setHeightInCm(Double.parseDouble(heightInCm.getText()));
                ((Transporter) tmpVehicle).setLoadingWeightInKG(Double.parseDouble(loadingWeightInKG.getText()));
                break;
        }

        if(Main.inEditMode) {
            vehicleService.updateVehicle(Main.vehicleToEdit, tmpVehicle);
        } else {
            vehicleService.addVehicle(tmpVehicle);
        }
        System.out.println("Temporary Vehicle ...");
        VehicleService.debugVehicle(tmpVehicle);
    }

    private boolean isAllSet() {
        List<Control> list = new ArrayList<>(inputFieldList);
        if(vehicleType.getValue() != null) {
            switch (vehicleType.getValue()) {
                case CAR:
                    list.add(trunkSpace);
                    list.add(carType);
                    break;
                case MOTORCYCLE:
                    list.add(fuelCapacity);
                    break;
                case TRANSPORTER:
                    list.add(loadingWeightInKG);
                    list.add(heightInCm);
                    break;
            }
            for (Object input : list) {
                if(input instanceof JFXTextField) {
                    if(((JFXTextField) input).getText().isEmpty()) return false;
                } else if(input instanceof JFXDatePicker) {
                    if(((JFXDatePicker) input).getValue() == null) return false;
                } else if(input instanceof JFXComboBox) {
                    if(((JFXComboBox) input).getValue() == null) return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void initializeUI(boolean isInEditMode) {
        if(isInEditMode) {
            confirmButton.setText("Update Vehicle");
        } else {
            confirmButton.setText("Register Vehicle");
        }

        if(isInEditMode) {
            Vehicle vehicleToEdit = Main.vehicleToEdit;
            if(vehicleToEdit instanceof Car) vehicleType.setValue(VehicleTypes.CAR);
            if(vehicleToEdit instanceof Motorcycle) vehicleType.setValue(VehicleTypes.MOTORCYCLE);
            if(vehicleToEdit instanceof Transporter) vehicleType.setValue(VehicleTypes.TRANSPORTER);

            idNumber.setText(vehicleToEdit.getIdNumber());
            make.setText(vehicleToEdit.getMake());
            model.setText(vehicleToEdit.getModel());
            ccm.setText(String.valueOf(vehicleToEdit.getCcm()));
            fuelType.setValue(vehicleToEdit.getFuelType());
            exteriorColor.setText(vehicleToEdit.getExteriorColor());
            millage.setText(String.valueOf(vehicleToEdit.getMillage()));
            licensePlate.setText(vehicleToEdit.getLicensePlate());
            numberOfSeats.setText(String.valueOf(vehicleToEdit.getNumberOfSeats()));
            vehicleCategory.setValue(vehicleToEdit.getVehicleCategory());
            dateOfPurchase.setValue(vehicleToEdit.getDateOfPurchase());
            priceOfPurchase.setText(vehicleToEdit.getPriceOfPurchase().toString());
            availableFrom.setValue(vehicleToEdit.getAvailableFrom());
            availableUntil.setValue(vehicleToEdit.getAvailableUntil());
            availability.setSelected(vehicleToEdit.isAvailability());


            switch (vehicleType.getValue()) {
                case CAR:
                    trunkSpace.setText(String.valueOf(((Car) vehicleToEdit).getTrunkSpace()));
                    carType.setValue(((Car) vehicleToEdit).getCarType());
                    hasSatNav.setSelected(((Car) vehicleToEdit).isHasSatNav());
                    break;
                case MOTORCYCLE:
                    fuelCapacity.setText(String.valueOf(((Motorcycle) vehicleToEdit).getFuelCapacity()));
                    hasSatchel.setSelected(((Motorcycle) vehicleToEdit).isHasSatchel());
                    break;
                case TRANSPORTER:
                    heightInCm.setText(((Double)((Transporter) vehicleToEdit).getHeightInCm()).toString());
                    loadingWeightInKG.setText(((Double)((Transporter) vehicleToEdit).getLoadingWeightInKG()).toString());
            }
        }
    }

    private void initializeInputFieldList() {
        inputFieldList = new ArrayList<>(
            Arrays.asList(
                idNumber,
                make,
                model,
                ccm,
                exteriorColor,
                millage,
                licensePlate,
                numberOfSeats,
                dateOfPurchase,
                priceOfPurchase,
                availableFrom,
                availableUntil,
                fuelType,
                vehicleType,
                vehicleCategory
            )
        );
    }

    public void selectVehicleType() {
        VehicleTypes selectedType = vehicleType.getValue();
        switch (selectedType) {
            case CAR:
                subView.setCenter(null);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("registerCar/registerCar.fxml"));
                    subView.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MOTORCYCLE:
                subView.setCenter(null);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("registerMotorcycle/registerMotorcycle.fxml"));
                    subView.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case TRANSPORTER:
                subView.setCenter(null);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("registerTransporter/registerTransporter.fxml"));
                    subView.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}


