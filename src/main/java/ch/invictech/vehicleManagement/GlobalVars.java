package ch.invictech.vehicleManagement;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ch.invictech.vehicleManagement.data.rental.Rental;
import ch.invictech.vehicleManagement.data.vehicle.Vehicle;
import ch.invictech.vehicleManagement.services.PersistenceService;
import ch.invictech.vehicleManagement.services.RentalService;
import ch.invictech.vehicleManagement.services.VehicleService;

public class GlobalVars {
    public static VehicleService vService;
    public static RentalService rService;
    public static PersistenceService pService;
    public static Stage stage;
    public static final int heightOffset = 60;
    public static final int widthOffset = 215;

    public static Vehicle vehicleToEdit;
    public static boolean inVehicleEditMode;

    public static Rental rentalToEdit;
    public static boolean inRentalEditMode;

    public static void resizeStage(Pane root) {
        stage.setMinHeight(root.getMinHeight() + heightOffset);
        stage.setMinWidth(root.getMinWidth() + widthOffset);
        if(!stage.isMaximized()) {
            if(root.getMinHeight() >= (stage.getHeight() + heightOffset)) {
                stage.setHeight(root.getMinHeight() + heightOffset);
            }
            if(root.getMinWidth() >= (stage.getWidth() + widthOffset)) {
                stage.setWidth(root.getMinWidth() + widthOffset);
            }
        }

    }
}
