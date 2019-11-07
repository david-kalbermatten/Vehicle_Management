package vehicleManagement.services;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.vehicle.Car;
import vehicleManagement.data.vehicle.Motorcycle;
import vehicleManagement.data.vehicle.Transporter;
import vehicleManagement.data.vehicle.Vehicle;

public class VehicleService {
    public ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    public VehicleService() {
        vehicleList.addListener(new ListChangeListener<Vehicle>() {
            @Override
            public void onChanged(Change<? extends Vehicle> c) {
                GlobalVars.pService.update();
            }
        });
    }

    public void addVehicle(Vehicle vehicleToAdd) {
        vehicleList.add(vehicleToAdd);
        GlobalVars.inEditMode = false;
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        vehicleList.remove(vehicleToRemove);
    }

    public void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {
        vehicleList.set(vehicleList.indexOf(oldVehicle), newVehicle);
    }

    public static void debugVehicle(Vehicle vehicle) {
        System.out.println("is car: " + (vehicle instanceof Car));
        System.out.println("is motorcycle: " + (vehicle instanceof Motorcycle));
        System.out.println("is transporter: " + (vehicle instanceof Transporter));
        //new ArrayList<Field>(Arrays.asList(vehicle.getClass().getDeclaredFields())).forEach(x -> System.out.println(x.getName()));
    }
}
