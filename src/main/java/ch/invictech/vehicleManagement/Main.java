package ch.invictech.vehicleManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ch.invictech.vehicleManagement.GlobalVars;
import ch.invictech.vehicleManagement.services.PersistenceService;
import ch.invictech.vehicleManagement.services.RentalService;
import ch.invictech.vehicleManagement.services.VehicleService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GlobalVars.vService = new VehicleService();
        GlobalVars.rService = new RentalService();
        GlobalVars.pService = new PersistenceService();
        GlobalVars.stage = primaryStage;

        GlobalVars.pService.readFile();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/invictech/vehicleManagement/ui//mainScreen/mainScreen.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        primaryStage.setTitle("Vehicle Management");
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            GlobalVars.pService.writeFile();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
