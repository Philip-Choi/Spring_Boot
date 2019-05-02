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
This class contains UI for display single tokimon. It has child class GetSingleSubmitEventHandler
which connects to the server and  send the data to drawing class GetSingleTokimonDrawer
 */

public class GetSingleEventHandler implements EventHandler<ActionEvent> {

    private final Stage primaryStage;

    public GetSingleEventHandler(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage subStage = new Stage();
        subStage.setTitle("Display a Tokimon");

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

        GetSingleSubmitEventHandler getSingleSubmitEventHandler = new GetSingleSubmitEventHandler(indexField, subStage);
        submit.setOnAction(getSingleSubmitEventHandler);


        Scene subScene = new Scene(gridpane, 400, 500);
        subStage.setScene(subScene);
        subStage.show();
        //Hide Current Window
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();

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
