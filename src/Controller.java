import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML JFXButton menuButton1;
    @FXML JFXSnackbar snackbar;
    @FXML VBox sideMenu;

    public void menuAction(int numberOfMenuItem) {
        BorderPane snackBarContainer = new BorderPane();
        VBox parent = (VBox) snackbar.getParent();

        snackBarContainer.setPrefHeight(30);
        snackBarContainer.setBackground(new Background(new BackgroundFill(Color.rgb(55,55,55), CornerRadii.EMPTY, Insets.EMPTY)));
        Text txt = new Text("Hello World! I'm a SnackBar :D" + numberOfMenuItem);
        txt.setFill(Paint.valueOf("white"));


        snackBarContainer.setPrefWidth(parent.getWidth());
        snackBarContainer.setEffect(new DropShadow(8,0,-2,Color.rgb(54,54,54,0.5)));
        snackBarContainer.setCenter(txt);

        sayHi2(snackBarContainer);
    }

    public void sayHi2(Node node) {
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(node, Duration.seconds(4)));
    }

    public void menuAction1() {
        menuAction(1);
    }

    public void menuAction2() {
        menuAction(2);
    }

    public void menuAction3() {
        menuAction(3);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
