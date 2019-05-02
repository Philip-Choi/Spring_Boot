package ca.cmpt213.asn5.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
This class has UI to obtain information reuiqred to make a tokimon object.
Then it sends the data to PostTokimonSumitEventHandler which sends the data to the server.
 */

public class PostTokimonEventHandler implements EventHandler<ActionEvent> {

    Stage primaryStage;

    public PostTokimonEventHandler(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage subStage = new Stage();
        subStage.setTitle("Adding a Tokimon");

        // name
        Label nameLabel = new Label("Name: ");
        nameLabel.setPrefWidth(100);
        nameLabel.setPadding(new Insets(0,0,0,30));
        TextField nameField = new TextField();

        // weight
        Label weightLabel = new Label("Weight: ");
        nameLabel.setPrefWidth(100);
        nameLabel.setPadding(new Insets(0,0,0,8));
        TextField weightField = new TextField();
        Label kg = new Label("kg");

        // height
        Label heightLabel = new Label("Height: ");
        nameLabel.setPrefWidth(100);
        nameLabel.setPadding(new Insets(0,0,0,8));
        TextField heightField = new TextField();
        Label cm = new Label("cm");

        // Ability - radiobutton
        Label abilityLabel = new Label("Ability: ");
        nameLabel.setPrefWidth(100);
        nameLabel.setPadding(new Insets(0,0,0,8));

        RadioButton radio1 = new RadioButton("fly");
        RadioButton radio2 = new RadioButton("fire");
        RadioButton radio3 = new RadioButton("water");
        RadioButton radio4 = new RadioButton("electric");
        RadioButton radio5 = new RadioButton("ice");
        ToggleGroup abilityToggleGroup = new ToggleGroup();
        radio1.setToggleGroup(abilityToggleGroup);
        radio2.setToggleGroup(abilityToggleGroup);
        radio3.setToggleGroup(abilityToggleGroup);
        radio4.setToggleGroup(abilityToggleGroup);
        radio5.setToggleGroup(abilityToggleGroup);
        HBox abilityHbox = new HBox(10, radio1, radio2, radio3, radio4, radio5);

        //Strength - slider
        Label strengthLabel = new Label("Strength: ");
        final int MIN = 0, MAX = 100, INIT = 0;
        final double MAJOR_TICK_UNIT = 10;
        final int MINOR_TICK_COUNT = 9;
        final double SLIDER_WIDTH = 700;
        final double SLIDER_HEIGHT = 100;

        Slider strengthSlider = new Slider(MIN, MAX, INIT);
        strengthSlider.setMajorTickUnit(MAJOR_TICK_UNIT);
        strengthSlider.setShowTickMarks(true);
        strengthSlider.setMinorTickCount(MINOR_TICK_COUNT);
        strengthSlider.setShowTickLabels(true);
        strengthSlider.setSnapToTicks(true);
        strengthSlider.setPrefWidth(SLIDER_WIDTH);
        strengthSlider.setPrefHeight(SLIDER_HEIGHT);
        strengthSlider.setOrientation(Orientation.HORIZONTAL);

        //color - ListView
        Label colorLabel = new Label("Color: ");
        ListView<String> colorListView = new ListView<>();
        colorListView.setPrefSize(120, 100);
        colorListView.getItems().addAll("Purple", "Red", "Green", "Blue", "Yellow", "Black");
        colorListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // gender - checkbox
//        Label genderLabel = new Label("Gender: ");
//        RadioButton gender1 = new RadioButton("Male");
//        RadioButton gender2 = new RadioButton("Female");
//        ToggleGroup genderToggleGroup = new ToggleGroup();
//        gender1.setToggleGroup(genderToggleGroup);
//        gender2.setToggleGroup(genderToggleGroup);
//        HBox genderHbox = new HBox(10, gender1, gender2);


        Button submit = new Button("Submit");
        Button back = new Button("Back");
        HBox buttonHbox = new HBox(10, submit, back);

        GridPane gridpane = new GridPane();
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(10));
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(nameLabel, 0, 0);
        gridpane.add(nameField, 1, 0);
        gridpane.add(weightLabel, 0, 1);
        gridpane.add(weightField, 1, 1);
        gridpane.add(kg, 2, 1);

        gridpane.add(heightLabel, 0, 2);
        gridpane.add(heightField, 1,2);
        gridpane.add(cm, 2, 2);

        gridpane.add(abilityLabel, 0, 3);
        gridpane.add(abilityHbox, 1,3, 8, 1);

        gridpane.add(strengthLabel, 0, 4);
        gridpane.add(strengthSlider, 1, 4, 8, 1);

        gridpane.add(colorLabel, 0, 5);
        gridpane.add(colorListView, 1, 5);

        gridpane.add(buttonHbox, 1, 7);

        PostTokimonSubmitEventHandler postTokimonSubmitEventHandler = new PostTokimonSubmitEventHandler(primaryStage, subStage, nameField,
                weightField, heightField, abilityToggleGroup, strengthSlider, colorListView);

        submit.setOnAction(postTokimonSubmitEventHandler);

        //Go back to main
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent backActionEvent) {
                primaryStage.show();
                subStage.close();
            }
        });

        Scene subScene = new Scene(gridpane, 1000, 600);
        subStage.setScene(subScene);
        subStage.show();
        //Hide Current Window
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
