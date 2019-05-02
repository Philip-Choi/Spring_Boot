package ca.cmpt213.asn5.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/*
This class sends the id of tokimon the user wants to delete to the server.
 */

public class DeleteTokimonSubmitEventHandler implements EventHandler<ActionEvent> {

    TextField indexField;

    public DeleteTokimonSubmitEventHandler(TextField indexField){
        this.indexField = indexField;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            String index = indexField.getCharacters().toString();

            URL url = new URL("http://localhost:8080/api/tokimon/" + index);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == 404){
                System.out.println("HTTP 404 error: Submitted index out of bound");
                System.out.println("Please submit index within the bound");

                Stage subSubStage = new Stage();
                subSubStage.setTitle("HTTP 404 Error");
                Label errorLabel1 = new Label("Entered invalid index. Please re-enter valid index");
                Label errorLabel2 = new Label ("or check if there are Tokimons at server");
                VBox vbox = new VBox(errorLabel1, errorLabel2);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 50);
                subSubStage.setScene(subSubScene);
                subSubStage.show();
            }
            else {
                System.out.println("Completed deleting Tokimon");

                Stage subSubStage = new Stage();
                subSubStage.setTitle("HTTP 204: No Content");
                Label errorLabel = new Label("Deletion succeeded");
                VBox vbox = new VBox(errorLabel);
                vbox.setAlignment(Pos.CENTER);

                Scene subSubScene = new Scene(vbox, 500, 50);
                subSubStage.setScene(subSubScene);
                subSubStage.show();
            }
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Server connection error. Please check your internet connection");
            Stage subSubStage = new Stage();
            subSubStage.setTitle("Server connection Error");
            Label errorLabel = new Label("Could not send the DELETE. Check the internet or localhost connection");
            VBox vbox = new VBox(errorLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene subSubScene = new Scene(vbox, 500, 50);
            subSubStage.setScene(subSubScene);
            subSubStage.show();
        }
    }
}
