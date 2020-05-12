package ch.invictech.vehicleManagement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ch.invictech.vehicleManagement.GlobalVars;
import ch.invictech.vehicleManagement.services.PersistenceService;
import ch.invictech.vehicleManagement.services.RentalService;
import ch.invictech.vehicleManagement.services.VehicleService;

public class Launch extends Application {
    @Override
    public void init()
    {
        System.out.printf("init() called on thread %s%n",
                Thread.currentThread());
        GlobalVars.vService = new VehicleService();
        GlobalVars.rService = new RentalService();
        GlobalVars.pService = new PersistenceService();
        GlobalVars.pService.readFile();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GlobalVars.stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/invictech/vehicleManagement/ui/mainScreen/mainScreen.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        primaryStage.setTitle("Vehicle Management");
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            GlobalVars.pService.writeFile();
            Platform.exit();
        });
    }

    @Override
    public void stop()
    {
        System.out.printf("stop() called on thread %s%n",
                Thread.currentThread());
    }


    public static void main(String[] args) {
        System.out.printf("main() called on %s%n", Thread.currentThread());
        Application.launch(args);
        System.out.print("terminating");
    }
}
