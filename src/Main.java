import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.vehicle.Vehicle;
import vehicleManagement.services.PersistenceService;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.VehicleService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GlobalVars.vService = new VehicleService();
        GlobalVars.rService = new RentalService();
        GlobalVars.pService = new PersistenceService();

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
