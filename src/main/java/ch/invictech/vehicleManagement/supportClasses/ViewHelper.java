package ch.invictech.vehicleManagement.supportClasses;

import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.StringConverter;
import ch.invictech.vehicleManagement.data.rental.Rental;
import ch.invictech.vehicleManagement.data.rental.RentalStatus;
import ch.invictech.vehicleManagement.data.vehicle.FuelType;
import ch.invictech.vehicleManagement.data.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ViewHelper {
    public static void initializeVehicleTableView(JFXTreeTableView treeTableView) {
        //Prepare Vehicle Table View
        JFXTreeTableColumn<Vehicle, String> columnId = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Vehicle, String> columnMake = new JFXTreeTableColumn<>("Make");
        JFXTreeTableColumn<Vehicle, String> columnModel = new JFXTreeTableColumn<>("Model");
        JFXTreeTableColumn<Vehicle, Integer> columnCcm = new JFXTreeTableColumn<>("ccm");
        JFXTreeTableColumn<Vehicle, FuelType> columnFuelType = new JFXTreeTableColumn<>("Fuel Type");
        JFXTreeTableColumn<Vehicle, String> columnColor = new JFXTreeTableColumn<>("Color");
        JFXTreeTableColumn<Vehicle, Integer> columnNumberOfSeats = new JFXTreeTableColumn<>("Seats");
        columnId.setCellValueFactory(new TreeItemPropertyValueFactory<>("idNumber"));
        columnMake.setCellValueFactory(new TreeItemPropertyValueFactory<>("make"));
        columnModel.setCellValueFactory(new TreeItemPropertyValueFactory<>("model"));
        columnCcm.setCellValueFactory(new TreeItemPropertyValueFactory<>("ccm"));
        columnFuelType.setCellValueFactory(new TreeItemPropertyValueFactory<>("fuelType"));
        columnColor.setCellValueFactory(new TreeItemPropertyValueFactory<>("exteriorColor"));
        columnNumberOfSeats.setCellValueFactory(new TreeItemPropertyValueFactory<>("numberOfSeats"));

        treeTableView.getColumns().addAll(columnId, columnMake, columnModel, columnCcm, columnFuelType, columnColor, columnNumberOfSeats);
    }

    public static void initializeRentalTableView(JFXTreeTableView treeTableView) {
        JFXTreeTableColumn<Rental, RentalStatus> columnRentalStatus = new JFXTreeTableColumn<>("Status");
        JFXTreeTableColumn<Rental, LocalDate> columnRentedFrom = new JFXTreeTableColumn<>("From");
        JFXTreeTableColumn<Rental, LocalDate> columnRentedUntil = new JFXTreeTableColumn<>("Until");
        JFXTreeTableColumn<Rental, Double> columnRentalPrice = new JFXTreeTableColumn<>("Price");
        JFXTreeTableColumn<Rental, String> columnCustomerSurname = new JFXTreeTableColumn<>("Surname");
        JFXTreeTableColumn<Rental, String> columnCustomerName = new JFXTreeTableColumn<>("Name");

        columnRentalStatus.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        columnRentedFrom.setCellValueFactory(new TreeItemPropertyValueFactory<>("rentedFrom"));
        columnRentedUntil.setCellValueFactory(new TreeItemPropertyValueFactory<>("rentedUntil"));
        columnRentalPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("rentalPrice"));
        columnCustomerSurname.setCellValueFactory(new TreeItemPropertyValueFactory<>("customerSurname"));
        columnCustomerName.setCellValueFactory(new TreeItemPropertyValueFactory<>("customerName"));

        treeTableView.getColumns().addAll(
                columnRentalStatus,
                columnRentedFrom,
                columnRentedUntil,
                columnRentalPrice,
                columnCustomerSurname,
                columnCustomerName
        );
    }

    public static void populateTableView(List objectList, JFXTreeTableView treeTableView) {
        TreeItem objects = new TreeItem<>();
        objectList.forEach(object -> {
            objects.getChildren().add(new TreeItem<>(object));
        });
        treeTableView.setRoot(objects);
        treeTableView.setShowRoot(false);
    }

    public static boolean isAllSet(List<Control> inputFieldList) {
        for (Object input : inputFieldList) {
            if(input instanceof JFXTextField) {
                if(((JFXTextField) input).getText().isEmpty()) return false;
            } else if(input instanceof JFXDatePicker) {
                if(((JFXDatePicker) input).getValue() == null) return false;
            } else if(input instanceof JFXComboBox) {
                if(((JFXComboBox) input).getValue() == null) return false;
            } else if(input instanceof JFXTreeTableView) {
                if(((JFXTreeTableView) input).getSelectionModel().getSelectedCells().isEmpty()) return false;
            }
        }
        return true;
    }

    public static void setSelectedOnTableView(JFXTreeTableView tableView, Object treeItem) {
        for (int i = 0; i < tableView.getCurrentItemsCount(); i++) {
            if(tableView.getSelectionModel().getModelItem(i).getValue() == treeItem) {
                tableView.getSelectionModel().select(i);
            }
        }
        if(tableView.getSelectionModel().getSelectedIndex() == -1) {
            tableView.getRoot().getChildren().add(new TreeItem<>(treeItem));
            tableView.getSelectionModel().selectLast();
        }
    }


    public static void setInputFieldToInteger(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);
    }

    public static void setInputFieldToDouble(TextField textField) {
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
        textField.setTextFormatter(textFormatter);
        textField.setText("");
    }

    public static void showSnackbar(String message, Pane rootElement) {
        JFXSnackbar snackbar = new JFXSnackbar(rootElement);
        BorderPane snackBarContainer = new BorderPane();
        Text snackBarText = new Text("Placeholder SnackBar Text");

        //Styling
        snackBarContainer.setPrefWidth(rootElement.getWidth());
        rootElement.widthProperty().addListener((observable, oldValue, newValue) -> snackBarContainer.setPrefWidth(newValue.floatValue()));
        snackBarContainer.setPrefHeight(30);
        snackBarContainer.setBackground(new Background(new BackgroundFill(Color.rgb(40,40,40), CornerRadii.EMPTY, Insets.EMPTY)));
        snackBarText.setFill(Paint.valueOf("white"));
        snackbar.setEffect(new DropShadow(8,0,-2,Color.rgb(54,54,54,0.5)));

        snackBarContainer.setLeft(snackBarText);
        BorderPane.setAlignment(snackBarText, Pos.CENTER_LEFT);

        snackBarText.setText(message);
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(snackBarContainer, Duration.seconds(3)));
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }
}
