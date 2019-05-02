package ca.cmpt213.asn5.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAllConnector {

    GridPane gridPane;

    public GetAllConnector(GridPane gridPane) {

        this.gridPane = gridPane;

        try{
            URL url = new URL("http://localhost:8080/api/tokimon/all" );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                GetAllTokimonDrawer getAllTokimonDrawer = new GetAllTokimonDrawer(connection, gridPane);
            }
            connection.disconnect();
        } catch (IOException e){
            System.out.println("Failed to connect to the server");
            Stage subSubStage = new Stage();
            subSubStage.setTitle("Server connection Error");
            Label errorLabel = new Label("Could not send the GET. Check the internet or localhost connection");
            VBox vbox = new VBox(errorLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene subSubScene = new Scene(vbox, 500, 50);
            subSubStage.setScene(subSubScene);
            subSubStage.show();
        }
    }
}
