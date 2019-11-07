package vehicleManagement;

import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.PersistenceService;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.VehicleService;

public class GlobalVars {
    public static VehicleService vService;
    public static RentalService rService;
    public static PersistenceService pService;
    public static Vehicle vehicleToEdit;
    public static boolean inEditMode;
}
