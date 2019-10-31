import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML BorderPane rootElement;
    @FXML JFXButton menuButton1;
    @FXML JFXSnackbar snackbar;
    @FXML VBox sideMenu;

    private BorderPane snackBarContainer;
    private VBox parentOfSnackBar;
    private Text snackBarText;



    public void menuAction(int numberOfMenuItem) {
        snackBarText.setText("I'm the Text Number " + numberOfMenuItem);
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(snackBarContainer, Duration.seconds(4)));
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
        snackBarContainer = new BorderPane();
        parentOfSnackBar = (VBox) snackbar.getParent();
        snackBarText = new Text("Placeholder SnackBar Text");
        defineSnackBar();
    }

    private void defineSnackBar() {
        snackBarContainer.setPrefHeight(30);
        snackBarContainer.setBackground(new Background(new BackgroundFill(Color.rgb(40,40,40), CornerRadii.EMPTY, Insets.EMPTY)));

        snackBarText.setFill(Paint.valueOf("white"));
        snackBarContainer.setPrefWidth(parentOfSnackBar.getWidth());
        parentOfSnackBar.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                snackBarContainer.setPrefWidth(newValue.floatValue());
            }
        });

        snackBarContainer.setEffect(new DropShadow(8,0,-2,Color.rgb(54,54,54,0.5)));
        snackBarContainer.setLeft(snackBarText);
        BorderPane.setAlignment(snackBarText, Pos.CENTER_LEFT);
    }

    public void closeProgram() {
        System.exit(0);
    }
}
