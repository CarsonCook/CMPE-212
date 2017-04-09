package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Text bmrText;

    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    Button calculateButton;

    TextField ageTextField;
    TextField heightTextField;
    TextField weightTextField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 270, 310));

        accessElements(primaryStage);
        setOnClicks();

        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static double calculateBMR(double weight, double height, int age, boolean isMan) {
        if (isMan) {
            return (10.0 * weight) + (6.25 * height) - (5.0 * (double)age) + 5.0;
        } else {
            return (10.0 * weight) + (6.25 * height) - (5.0 * (double)age) - 161.0;
        }
    }

    private void accessElements(Stage primaryStage) {
        calculateButton = (Button) primaryStage.getScene().lookup("#calculateButton");
        bmrText = (Text) primaryStage.getScene().lookup("#bmrText");
        ageTextField = (TextField) primaryStage.getScene().lookup("#ageTextField");
        heightTextField = (TextField) primaryStage.getScene().lookup("#heightTextField");
        weightTextField = (TextField) primaryStage.getScene().lookup("#weightTextField");
        maleRadioButton = (RadioButton) primaryStage.getScene().lookup("#maleRadioButton");
        femaleRadioButton = (RadioButton) primaryStage.getScene().lookup("#femaleRadioButton");
    }

    private void setOnClicks() {
        calculateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int age = Integer.parseInt(ageTextField.getText());
                double weight = Double.parseDouble(weightTextField.getText());
                double height = Double.parseDouble(heightTextField.getText());
                bmrText.setText("BMR="+calculateBMR(weight,height,age,maleRadioButton.isSelected())+" Calories");
            }
        });
        maleRadioButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                maleRadioButton.setSelected(true);
                femaleRadioButton.setSelected(false);
            }
        });
        femaleRadioButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                maleRadioButton.setSelected(false);
                femaleRadioButton.setSelected(true);
            }
        });
    }
}
