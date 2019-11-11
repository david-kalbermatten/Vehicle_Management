package vehicleManagement.services;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.vehicle.*;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    public List<Vehicle> vehicleList = new ArrayList<>();

    public VehicleService() {
        //vehicleList.addListener((ListChangeListener<Vehicle>) c -> GlobalVars.pService.writeFile());
    }

    public void addVehicle(Vehicle vehicleToAdd) {
        vehicleList.add(vehicleToAdd);
        GlobalVars.inVehicleEditMode = false;
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        vehicleList.remove(vehicleToRemove);
    }

    public void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {
        vehicleList.set(vehicleList.indexOf(oldVehicle), newVehicle);
    }

    public List<Vehicle> getFilteredList(VehicleTypes vehicleType) {
        List<Vehicle> list = new ArrayList<>(vehicleList);
        List<Vehicle> itemsToRemove = new ArrayList<>();
        if(vehicleType != null) {
            list.forEach(vehicle -> {
                switch (vehicleType) {
                    case CAR:
                        if(!(vehicle instanceof Car)) itemsToRemove.add(vehicle);
                        break;
                    case MOTORCYCLE:
                        if(!(vehicle instanceof Motorcycle)) itemsToRemove.add(vehicle);
                        break;
                    case TRANSPORTER:
                        if(!(vehicle instanceof Transporter)) itemsToRemove.add(vehicle);
                        break;
                }
            });
            list.removeAll(itemsToRemove);
            itemsToRemove.clear();
        }

        return list;
    }

    public static void debugVehicle(Vehicle vehicle) {
        System.out.println("is car: " + (vehicle instanceof Car));
        System.out.println("is motorcycle: " + (vehicle instanceof Motorcycle));
        System.out.println("is transporter: " + (vehicle instanceof Transporter));
        //new ArrayList<Field>(Arrays.asList(vehicle.getClass().getDeclaredFields())).forEach(x -> System.out.println(x.getName()));
    }
}
