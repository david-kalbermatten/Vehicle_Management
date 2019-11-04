package vehicleManagement.services;

import vehicleManagement.data.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    public List<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicle(Vehicle vehicleToAdd) {
        vehicleList.add(vehicleToAdd);
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        vehicleList.remove(vehicleToRemove);
    }
}
