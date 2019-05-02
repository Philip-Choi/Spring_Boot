package ca.cmpt213.asn5.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.Map;

/*
This class parse the data received from GetSingleSubmitEventHandler
and displays the tokimon according to the data.
 */

public class GetSingleTokimonDrawer {
    String line;
    Stage subStage;
    ActionEvent actionEvent;

    public GetSingleTokimonDrawer(String line, Stage subStage, ActionEvent actionEvent){
        this.line = line;
        this.subStage = subStage;
        this.actionEvent = actionEvent;

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        //parse
        Map<String, String> map = gson.fromJson(line, type);
        String name = map.get("tokimonName");
        float weight = Float.parseFloat(map.get("tokimonWeight"));
        float height = Float.parseFloat((String) map.get("tokimonHeight"));
        String ability = (String) map.get("tokimonAbility");
        int strength = Integer.parseInt((String) map.get("tokimonStrength"));
        String color = (String) map.get("tokimonColor");

        //display
        Stage subSubStage = new Stage();
        subSubStage.setTitle("Display a Tokimon");

        //figure
        GridPane gridpane = new GridPane();
        Scene subSubScene = new Scene(gridpane, 400, 500);

        Ellipse figure = new Ellipse(subSubScene.getWidth() / 2, height + 10, weight / 2, height / 2);
        if (color.equals("Purple")) {
            figure.setFill(Color.PURPLE);
        }
        if (color.equals("Red")) {
            figure.setFill(Color.RED);
        }
        if (color.equals("Green")) {
            figure.setFill(Color.GREEN);
        }
        if (color.equals("Blue")) {
            figure.setFill(Color.BLUE);
        }
        if (color.equals("Black")) {
            figure.setFill(Color.BLACK);
        }

        Text nameLabel = new Text(subSubScene.getWidth() / 2, 50, "Name: ");
        Text t1 = new Text(subSubScene.getWidth() / 2, 70, name);
        Text weightLabel = new Text("Weight: ");
        Text t2 = new Text(String.valueOf(weight));
        Text weightScaleLabel = new Text("kg");
        Text heightLabel = new Text("Height: ");
        Text t3 = new Text(String.valueOf(height));
        Text heightScaleLabel = new Text("cm");
        Text abilityLabel = new Text("Ability: ");
        Text t4 = new Text(ability);
        Text strengthLabel = new Text("Strength: ");
        Text t5 = new Text(String.valueOf(strength));

        Button back = new Button("Back");

        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(nameLabel, 0, 1);
        gridpane.add(t1, 1, 1);
        gridpane.add(weightLabel, 0, 2);
        gridpane.add(t2, 1, 2);
        gridpane.add(weightScaleLabel, 2, 2);
        gridpane.add(heightLabel, 0, 3);
        gridpane.add(t3, 1, 3);
        gridpane.add(heightScaleLabel, 2, 3);
        gridpane.add(abilityLabel, 0, 4);
        gridpane.add(t4, 1, 4);
        gridpane.add(strengthLabel, 0, 5);
        gridpane.add(t5, 1, 5);
        gridpane.add(back, 0, 6);
        gridpane.getChildren().add(figure);

        subSubStage.setScene(subSubScene);
        subSubStage.show();
        //Hide Current Window
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        //Go back to main
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent backActionEvent) {
                subStage.show();
                subSubStage.close();
            }
        });
    }
}
