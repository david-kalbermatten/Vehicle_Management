package vehicleManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.PersistenceService;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.VehicleService;

public class Main extends Application {
    public static VehicleService vService;
    public static RentalService rService;
    public static PersistenceService pService;
    public static boolean inEditMode;
    public static Vehicle vehicleToEdit;

    @Override
    public void start(Stage primaryStage) throws Exception{
        vService = new VehicleService();
        rService = new RentalService();
        pService = new PersistenceService();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicleManagement/ui/main/mainScreen.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
