package vehicleManagement.services;

import vehicleManagement.Main;
import vehicleManagement.data.vehicle.Car;
import vehicleManagement.data.vehicle.Motorcycle;
import vehicleManagement.data.vehicle.Transporter;
import vehicleManagement.data.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    public List<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicle(Vehicle vehicleToAdd) {
        vehicleList.add(vehicleToAdd);
        Main.inEditMode = false;
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        vehicleList.remove(vehicleToRemove);
    }

    public void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {
        vehicleList.set(vehicleList.indexOf(oldVehicle), newVehicle);
    }

    public static void debugVehicle(Vehicle vehicle) {
        System.out.println("Is car: " + (vehicle instanceof Car));
        System.out.println("Is motorcycle: " + (vehicle instanceof Motorcycle));
        System.out.println("Is transporter: " + (vehicle instanceof Transporter));
    }
}
