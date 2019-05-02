package ca.cmpt213.asn5.client;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/*
This class handles the data received from the user including error handling,
and send the data to PostTokimonDataOutputStreamer class
 */

public class PostTokimonSubmitEventHandler implements EventHandler<ActionEvent>{
    Stage primaryStage;
    Stage subStage;
    TextField nameField;
    TextField weightField;
    TextField heightField;
    ToggleGroup abilityToggleGroup;
    Slider strengthSlider;
    ListView<String>  colorListView;
    ToggleGroup genderToggleGroup;

    public PostTokimonSubmitEventHandler(Stage primaryStage, Stage subStage, TextField nameField, TextField weightField,
                                         TextField heightField, ToggleGroup abilityToggleGroup, Slider strengthSlider,
                                         ListView<String>  colorListView){

        this.primaryStage = primaryStage;
        this.subStage = subStage;
        this.nameField = nameField;
        this.weightField = weightField;
        this.heightField = heightField;
        this.abilityToggleGroup = abilityToggleGroup;
        this.strengthSlider = strengthSlider;
        this.colorListView = colorListView;
//        this.genderToggleGroup = genderToggleGroup;

    }
    @Override
    public void handle(ActionEvent actionEvent){
        String name;
        float weight;
        float height;
        String ability;
        int strength;
        String color;

        try {
            URL url = new URL("http://localhost:8080/api/tokimon/add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // empty case error handling
            if (nameField.getCharacters().toString().isEmpty() || weightField.getCharacters().toString().isEmpty() ||
                    heightField.getCharacters().toString().isEmpty() ||
                    ((RadioButton)abilityToggleGroup.getSelectedToggle()).getText().isEmpty() ||
                    colorListView.getSelectionModel().getSelectedItems().isEmpty() ){

                System.out.println("Input Value Error");
                System.out.println("Please enter all the field");
                Stage subSubStage = new Stage();
                subSubStage.setTitle("Input Error");
                Label errorLabel1 = new Label("Input value error");
                Label errorLabel2 = new Label("Pleaes enter all the field");
                Button back = new Button("Back");
                VBox vbox = new VBox(errorLabel1, errorLabel2);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 100);
                subSubStage.setScene(subSubScene);
                subSubStage.show();
            }
            //parsing attributes for tokimon
            name = nameField.getCharacters().toString();
            weight = Float.parseFloat(weightField.getCharacters().toString());
            height = Float.parseFloat(heightField.getCharacters().toString());
            ability = ((RadioButton)abilityToggleGroup.getSelectedToggle()).getText();
            strength = (int)strengthSlider.getValue();

            ObservableList<String> colorObject = colorListView.getSelectionModel().getSelectedItems();
            color = colorObject.get(0);
            //            String gender = ((RadioButton)genderToggleGroup.getSelectedToggle()).getText();

            // error handling
            if (name.length() > 20 || weight > 200 || height > 200){
                System.out.println("Input Value Error");
                System.out.println("Entered invalid input. Please put input within the bound");
                System.out.println("name.length() < 20 || weight < 200 || height < 200");

                Stage subSubStage = new Stage();
                subSubStage.setTitle("Input Error");
                Label errorLabel1 = new Label("Input value error. Pleaes enter valid input within the bound");
                Label errorLabel2 = new Label("name.length() < 20 || weight < 200 || height < 200");
                Button back = new Button("Back");
                VBox vbox = new VBox(errorLabel1, errorLabel2);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 100);
                subSubStage.setScene(subSubScene);
                subSubStage.show();

            } else {
                PostTokimonDataOutputStreamer postTokimonDataOutputStreamer = new PostTokimonDataOutputStreamer(connection,
                        name, weight, height, ability, strength, color);
            }

        } catch (IOException e) {
            System.out.println("Could not send the Post. Check the internet or localhost connection");
            Stage subSubStage = new Stage();
            subSubStage.setTitle("Server Connection Error");
            Label errorLabel = new Label("Could not send the Post. Check the internet or localhost connection");
            VBox vbox = new VBox(errorLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene subSubScene = new Scene(vbox, 500, 50);
            subSubStage.setScene(subSubScene);
            subSubStage.show();
        }
    }
}
