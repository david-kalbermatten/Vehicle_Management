package vehicleManagement.ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import vehicleManagement.GlobalVars;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.VehicleService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    VehicleService vehicleService = GlobalVars.vService;
    RentalService rentalService = GlobalVars.rService;

    @FXML BorderPane rootElement;
    @FXML JFXButton displayVehicles;
    @FXML VBox sideMenu;

    private JFXSnackbar snackbar;
    private BorderPane snackBarContainer;
    private Text snackBarText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defineSnackBar();
        /*sideMenu.getChildren().forEach(menuItem -> {
            JFXButton item = ((JFXButton) menuItem);
            ImageView image = new ImageView("/vehicleManagement/resources/baseline_directions_car_white_48dp.png");
            image.setFitWidth(24);
            image.setFitHeight(24);
            item.setGraphic(image);
            //item.setGraphicTextGap(20);
        });*/
    }

    public void changeViewDisplayVehicles() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../displayVehicles/displayVehicles.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewDisplayRentals() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../displayRentals/displayRentals.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterVehicle() {
        try {
            GlobalVars.inEditMode = false;
            GlobalVars.vehicleToEdit = null;
            Parent root = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterRental() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../registerRental/registerRental.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSnackBar(int numberOfMenuItem) {
        snackBarText.setText("I'm the Text Number " + numberOfMenuItem);
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(snackBarContainer, Duration.seconds(4)));
    }

    private void defineSnackBar() {
        snackbar = new JFXSnackbar(rootElement);
        snackBarContainer = new BorderPane();
        snackBarText = new Text("Placeholder SnackBar Text");

        //Styling
        rootElement.widthProperty().addListener((observable, oldValue, newValue) -> snackBarContainer.setPrefWidth(newValue.floatValue()));
        snackBarContainer.setPrefHeight(30);
        snackBarContainer.setBackground(new Background(new BackgroundFill(Color.rgb(40,40,40), CornerRadii.EMPTY, Insets.EMPTY)));
        snackBarText.setFill(Paint.valueOf("white"));
        snackbar.setEffect(new DropShadow(8,0,-2,Color.rgb(54,54,54,0.5)));

        snackBarContainer.setLeft(snackBarText);
        BorderPane.setAlignment(snackBarText, Pos.CENTER_LEFT);
    }

    public void closeProgram() {
        System.exit(0);
    }


}
