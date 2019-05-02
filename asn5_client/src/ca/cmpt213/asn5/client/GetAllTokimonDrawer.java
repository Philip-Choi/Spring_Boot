package ca.cmpt213.asn5.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/*
This class draws all the tokimons based on the json data received from its parent class GetAllConnector.
It draws ellipse based on the size of weight and height.
 */

public class GetAllTokimonDrawer {
    HttpURLConnection connection;
    GridPane gridPane;

    public GetAllTokimonDrawer(HttpURLConnection connection, GridPane gridPane){
        this.connection = connection;
        this.gridPane = gridPane;

        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
//                System.out.println(line);
                Gson gson = new Gson();
                JsonArray ja = gson.fromJson(line, JsonArray.class);
                int index = 0;
                int drawX = 10;
                int drawY = 10;

                for (JsonElement obj: ja){
//                    System.out.println(obj.toString());
                    Text nameLabel = new Text("Name: ");
                    String name = obj.getAsJsonObject().get("tokimonName").toString();
                    Text nameValue = new Text(name);

                    Text weightLabel = new Text("Weight: ");
                    String weightSource = obj.getAsJsonObject().get("tokimonWeight").toString();
                    float weightValueFloat = Float.parseFloat(weightSource);
                    Text weightValue = new Text(weightSource);
                    Text weightScaleLabel = new Text("kg");

                    Text heightLabel = new Text("Height: ");
                    String heightSource = obj.getAsJsonObject().get("tokimonHeight").toString();
                    float heightValueFloat = Float.parseFloat(heightSource);
                    Text heightValue = new Text(heightSource);
                    Text heightScaleLabel = new Text("cm");

                    Text abilityLabel = new Text("Ability: ");
                    String ability = obj.getAsJsonObject().get("tokimonAbility").toString();
                    Text abilityValue = new Text(ability);

                    Text strengthLabel = new Text("Strength: ");
                    String strength = obj.getAsJsonObject().get("tokimonStrength").toString();
                    Text strengthValue = new Text(String.valueOf(strength));

                    String color = obj.getAsJsonObject().get("tokimonColor").toString();
                    int colorLength = color.length();
                    String trimedColor = color.substring(1, colorLength-1);

                    Ellipse figure = new Ellipse(drawX, drawY, weightValueFloat / 2, heightValueFloat / 2);
                    if (trimedColor.equals("Purple")) {
                        figure.setFill(Color.PURPLE);
                    }
                    if (trimedColor.equals("Red")) {
                        figure.setFill(Color.RED);
                    }
                    if (trimedColor.equals("Green")) {
                        figure.setFill(Color.GREEN);
                    }
                    if (trimedColor.equals("Blue")) {
                        figure.setFill(Color.BLUE);
                    }
                    if (trimedColor.equals("Black")) {
                        figure.setFill(Color.BLACK);
                    }

                    GridPane subGridPane = new GridPane();
                    subGridPane.setAlignment(Pos.CENTER);
                    subGridPane.setVgap(10);
                    subGridPane.setHgap(10);
                    subGridPane.setPadding(new Insets(10, 10, 10, 10));

                    subGridPane.add(figure, 0, 0);
                    subGridPane.add(nameLabel, 0, 1);
                    subGridPane.add(nameValue, 1, 1);
                    subGridPane.add(weightLabel, 0, 2);
                    subGridPane.add(weightValue, 1, 2);
                    subGridPane.add(weightScaleLabel, 2, 2);
                    subGridPane.add(heightLabel, 0, 3);
                    subGridPane.add(heightValue, 1, 3);
                    subGridPane.add(heightScaleLabel, 2, 3);
                    subGridPane.add(abilityLabel, 0, 4);
                    subGridPane.add(abilityValue, 1, 4);
                    subGridPane.add(strengthLabel, 0, 5);
                    subGridPane.add(strengthValue, 1, 5);
                    gridPane.add(subGridPane, index, 0);
                    index++;
                }
            }

            in.close();

        } catch (IOException e) {
            System.out.println("Failed to read the file");

            Stage subSubStage = new Stage();
            subSubStage.setTitle("Server connection Error");
            Label errorLabel = new Label("Failed to read the data from the server. Check the internet or localhost connection");
            VBox vbox = new VBox(errorLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene subSubScene = new Scene(vbox, 500, 50);
            subSubStage.setScene(subSubScene);
            subSubStage.show();
        }
    }

}
