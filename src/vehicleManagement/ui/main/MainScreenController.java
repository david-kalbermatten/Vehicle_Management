package vehicleManagement.ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML BorderPane rootElement;
    @FXML JFXButton displayVehicles;
    @FXML VBox sideMenu;

    private JFXSnackbar snackbar;
    private BorderPane snackBarContainer;
    private Text snackBarText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defineSnackBar();
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
            Parent root = FXMLLoader.load(getClass().getResource("../registerVehicle/registerVehicle.fxml"));
            rootElement.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewRegisterRental() {

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
