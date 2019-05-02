package ca.cmpt213.asn5.client;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
This class connects to the server and retrieve data required for one tokimon.
It then sends the data to GetSingleTokimonDrawer class
 */

public class GetSingleSubmitEventHandler implements EventHandler<ActionEvent> {

    private final TextField indexField;
    private final Stage subStage;

    public GetSingleSubmitEventHandler(TextField indexField, Stage subStage) {
        this.indexField = indexField;
        this.subStage = subStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            String index = indexField.getCharacters().toString();
            URL url = new URL("http://localhost:8080/api/tokimon/" + index);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader in;
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = in.readLine()) != null){
                    GetSingleTokimonDrawer getSingleTokimonDrawer = new GetSingleTokimonDrawer(line, subStage, actionEvent);
                }
                in.close();
                System.out.println(connection.getResponseCode());
                System.out.println("HTTP 200: OK");
                System.out.println("Get single Tokimon succeeded");
                connection.disconnect();
            }
            else {
                System.out.println(responseCode);
                System.out.println("HTTP 404 error: Submitted index out of bound");
                System.out.println("Please submit index within the bound");

                Stage subSubStage = new Stage();
                subSubStage.setTitle("HTTP 404 Error");
                Label errorLabel1 = new Label("Entered invalid index. Please re-enter valid index");
                Label errorLabel2 = new Label ("or check if you have added Tokimon before");
                VBox vbox = new VBox(errorLabel1, errorLabel2);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 50);
                subSubStage.setScene(subSubScene);
                subSubStage.show();
            }


        } catch (IOException e) {
            System.out.println("Could not process the GET. Check the internet or localhost connection");

            Stage subSubStage = new Stage();
            subSubStage.setTitle("Internet Connection Error");
            Label errorLabel = new Label("Could not process the GET. Check the internet or localhost connection");
            VBox vbox = new VBox(errorLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene subSubScene = new Scene(vbox, 500, 50);
            subSubStage.setScene(subSubScene);
            subSubStage.show();
        }
    }
}
