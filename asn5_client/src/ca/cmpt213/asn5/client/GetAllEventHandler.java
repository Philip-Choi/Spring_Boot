package ca.cmpt213.asn5.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/*
This class shows UI for Display All Tokimons. It holds getAllConnector class Object which connects to the server to get the data
 */

public class GetAllEventHandler implements EventHandler<ActionEvent> {
    private final Stage primaryStage;

    public GetAllEventHandler(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage subStage = new Stage();
        subStage.setTitle("Display all Tokimon");

        GridPane gridPane = new GridPane();
        Scene subScene = new Scene(gridPane, 1000, 1200);
        gridPane.setAlignment(Pos.CENTER);
        Button back = new Button("Back");
        gridPane.add(back, 0, 1);

        // Connector object
        GetAllConnector getAllConnector = new GetAllConnector(gridPane);

        subStage.setScene(subScene);
        subStage.show();

        //Go back to main
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent backActionEvent) {
                primaryStage.show();
                subStage.close();
            }
        });
    }
}
