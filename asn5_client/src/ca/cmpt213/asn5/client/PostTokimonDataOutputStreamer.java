package ca.cmpt213.asn5.client;

import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/*
This class sends out the data which corresponds to Tokimon object to the server
as Json format
 */

public class PostTokimonDataOutputStreamer {

    HttpURLConnection connection;
    String name;
    float weight;
    float height;
    String ability;
    int strength;
    String color;

    public PostTokimonDataOutputStreamer(HttpURLConnection connection, String name, float weight, float height,
                                         String ability, int strength, String color){
        this.connection = connection;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ability = ability;
        this.strength = strength;
        this.color = color;

        try {
            // sends data to the server
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

            Tokimon tokimon = new Tokimon(name, weight, height, ability, strength, color);
            Gson gson = new Gson();
            String json = gson.toJson(tokimon);
//            System.out.println(json);
            wr.write(json);
            wr.flush();
            wr.close();
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == 201){
                System.out.println("HTTP: " + connection.getResponseCode());
                System.out.println("Completed adding Tokimon");

                Stage subSubStage = new Stage();
                subSubStage.setTitle("HTTP 201: Created ");
                Label errorLabel = new Label("Completed adding Tokimon");
                VBox vbox = new VBox(errorLabel);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 50);
                subSubStage.setScene(subSubScene);
                subSubStage.show();
            } else {
                System.out.println("HTTP error");
                System.out.println("Error occurred during POST. Check the internet or localhost connection");

                Stage subSubStage = new Stage();
                subSubStage.setTitle("HTTP Error");
                Label errorLabel = new Label("Error occurred during POST. Check the internet or localhost connection");
                VBox vbox = new VBox(errorLabel);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 50);
                subSubStage.setScene(subSubScene);
                subSubStage.show();
            }
            connection.disconnect();
        } catch (IOException e){
            System.out.println("HTTP error");
            System.out.println("Error occurred during POST. Check the internet or localhost connection");

            Stage subSubStage = new Stage();
            subSubStage.setTitle("HTTP Error");
            Label errorLabel = new Label("Error occurred during POST. Check the internet or localhost connection");
            VBox vbox = new VBox(errorLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene subSubScene = new Scene(vbox, 500, 50);
            subSubStage.setScene(subSubScene);
            subSubStage.show();

        }
    }
}
