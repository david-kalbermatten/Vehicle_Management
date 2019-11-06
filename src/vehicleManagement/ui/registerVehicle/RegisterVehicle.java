package vehicleManagement.ui.registerVehicle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterVehicle implements Initializable {
    public AnchorPane root;
    private VehicleService vehicleService = Main.vService;
    private ArrayList inputFieldList;

    public BorderPane subView;
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
        generateInputFieldList();

        //InputField Validation
        ValidatorService.setInputFieldToInteger(ccm);
        ValidatorService.setInputFieldToInteger(millage);
        ValidatorService.setInputFieldToInteger(numberOfSeats);
        ValidatorService.setInputFieldToDouble(priceOfPurchase);
    }

    public void confirm() {
        if(isAllSet()) {
            System.out.println("Works! :D");

        } else {
            ValidatorService.showSnackbar("Not all fields have been set!", root);
        }
    }

    private boolean isAllSet() {
        ArrayList list = (ArrayList) inputFieldList.clone();
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

    private void generateInputFieldList() {
        inputFieldList = new ArrayList(
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

    public void selectVehicleType(ActionEvent actionEvent) {
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


