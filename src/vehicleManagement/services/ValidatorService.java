package vehicleManagement.services;

import com.jfoenix.controls.JFXSnackbar;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.awt.event.KeyListener;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ValidatorService {

    public static void setInputFieldToInteger(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);
    }

    public static void setInputFieldToDouble(TextField textField) {
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
        textField.setTextFormatter(textFormatter);
    }

    public static void showSnackbar(String message, Pane rootElement) {
        JFXSnackbar snackbar = new JFXSnackbar(rootElement);
        BorderPane snackBarContainer = new BorderPane();
        Text snackBarText = new Text("Placeholder SnackBar Text");

        //Styling
        snackBarContainer.setPrefWidth(rootElement.getWidth());
        rootElement.widthProperty().addListener((observable, oldValue, newValue) -> snackBarContainer.setPrefWidth(newValue.floatValue()));
        snackBarContainer.setPrefHeight(30);
        snackBarContainer.setBackground(new Background(new BackgroundFill(Color.rgb(40,40,40), CornerRadii.EMPTY, Insets.EMPTY)));
        snackBarText.setFill(Paint.valueOf("white"));
        snackbar.setEffect(new DropShadow(8,0,-2,Color.rgb(54,54,54,0.5)));

        snackBarContainer.setLeft(snackBarText);
        BorderPane.setAlignment(snackBarText, Pos.CENTER_LEFT);

        snackBarText.setText(message);
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(snackBarContainer, Duration.seconds(3)));
    }
}
