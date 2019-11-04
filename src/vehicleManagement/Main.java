package vehicleManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vehicleManagement.services.RentalService;
import vehicleManagement.services.VehicleService;

public class Main extends Application {
    public static VehicleService vService;
    public static RentalService rService;

    @Override
    public void start(Stage primaryStage) throws Exception{
        vService = new VehicleService();
        rService = new RentalService();

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
