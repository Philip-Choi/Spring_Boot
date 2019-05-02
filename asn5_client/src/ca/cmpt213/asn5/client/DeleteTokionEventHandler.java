package ca.cmpt213.asn5.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
This class contains UI for tokimon deletion.
It receives index from the user and send it to DeleteTokimonSubmitEventHandler
 */

public class DeleteTokionEventHandler implements EventHandler<ActionEvent>{

    Stage primaryStage;

    public DeleteTokionEventHandler(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage subStage = new Stage();
        subStage.setTitle("Deleting a Tokimon");

        Label indexLabel = new Label("Index: ");
        indexLabel.setPrefWidth(100);
        indexLabel.setPadding(new Insets(0,0,0,30));
        TextField indexField = new TextField();

        Button submit = new Button("Submit");
        Button back = new Button("Back");
        HBox hbox = new HBox(10, submit, back);

        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(indexLabel, 0, 0);
        gridpane.add(indexField, 1, 0);
        gridpane.add(hbox, 1, 2);

        DeleteTokimonSubmitEventHandler deleteTokimonSubmitEventHandle = new DeleteTokimonSubmitEventHandler(indexField);
        submit.setOnAction(deleteTokimonSubmitEventHandle);

        //Go back to main
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent backActionEvent) {
                primaryStage.show();
                subStage.close();
            }
        });
        Scene subScene = new Scene(gridpane, 300, 60);
        subStage.setScene(subScene);
        subStage.show();
        //Hide Current Window
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
