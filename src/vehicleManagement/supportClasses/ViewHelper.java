package vehicleManagement.supportClasses;

import com.jfoenix.controls.*;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.rental.RentalStatus;
import vehicleManagement.data.vehicle.FuelType;
import vehicleManagement.data.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.List;

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
            tableView.getRoot().getChildren().add(treeItem);
        }
    }
}
