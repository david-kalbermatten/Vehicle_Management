package vehicleManagement.services;

import vehicleManagement.GlobalVars;
import vehicleManagement.data.vehicle.*;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    public List<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicle(Vehicle vehicleToAdd) {
        vehicleList.add(vehicleToAdd);
        GlobalVars.inVehicleEditMode = false;
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        vehicleList.remove(vehicleToRemove);
    }

    public List<Vehicle> getFilteredList(VehicleTypes vehicleType, VehicleCategory vehicleCategory, boolean showUnavailableVehicles) {
        List<Vehicle> list = new ArrayList<>(vehicleList);
        List<Vehicle> itemsToRemove = new ArrayList<>();
        if(!showUnavailableVehicles) {
            list.forEach(vehicle -> {
                if(!vehicle.isAvailability()) itemsToRemove.add(vehicle);
            });
            list.removeAll(itemsToRemove);
            itemsToRemove.clear();
        }
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
        if(vehicleCategory != null) {
            list.forEach(vehicle -> {
                switch (vehicleCategory) {
                    case BASIC:
                        if(vehicle.getVehicleCategory() != VehicleCategory.BASIC) itemsToRemove.add(vehicle);
                        break;
                    case MEDIUM:
                        if(vehicle.getVehicleCategory() != VehicleCategory.MEDIUM) itemsToRemove.add(vehicle);
                        break;
                    case LUXURY:
                        if(vehicle.getVehicleCategory() != VehicleCategory.LUXURY) itemsToRemove.add(vehicle);
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
