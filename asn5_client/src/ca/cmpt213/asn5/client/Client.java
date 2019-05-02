package ca.cmpt213.asn5.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
This is a Client class. This class contains all the User UI to interact with the server
 */

public class Client extends Application {

    public static void main(String[] args) {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a button components
        Button getAll = new Button("Display all Tokimons");
        Button getSingle = new Button ("Display a Tokimon");
        Button post = new Button ("Add a Tokimon");
        Button delete = new Button ("Delete a Tokimon");

        // Add buttons
        Label hboxLabel = new Label("Choose the option");
        HBox hbox = new HBox(30, getAll, getSingle, post, delete);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(hboxLabel, hbox);
        vbox.setPadding(new Insets(10,10, 10, 10));
        vbox.setAlignment(Pos.CENTER);

        //Display all
        GetAllEventHandler getAllEventHandler = new GetAllEventHandler(primaryStage);
        getAll.setOnAction(getAllEventHandler);

        //Display Single
        GetSingleEventHandler getSingleEventHandler = new GetSingleEventHandler(primaryStage);
        getSingle.setOnAction(getSingleEventHandler);

        //Post a Tokimon
        PostTokimonEventHandler postTokimonEventHandler = new PostTokimonEventHandler(primaryStage);
        post.setOnAction(postTokimonEventHandler);

        //Delete a Tokimon
        DeleteTokionEventHandler deleteTokimonEventHandler = new DeleteTokionEventHandler(primaryStage);
        delete.setOnAction(deleteTokimonEventHandler);

        Scene scene = new Scene(vbox, 700, 100); // (parent, hor, vert)

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tokimon UI");
        primaryStage.show();
    }
}
