package pathfinder.view.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pathfinder.Edge.EdgeType;
import pathfinder.view.GUI;
import pathfinder.State;

public class EdgeCreationUpdater implements EventHandler<ActionEvent> {
    
    private TextField from;
    private TextField to;
    private TextField name;
    private Button childEraType;
    private Button adultEraType;
    private Button submit;

    public EdgeCreationUpdater(TextField from, TextField to, TextField name, Button childEraType, Button adultEraType, Button submit) {
        this.from = from;
        this.to = to;
        this.name = name;
        this.childEraType = childEraType;
        this.adultEraType = adultEraType;
        this.submit = submit;
    }

    @Override
    public void handle(ActionEvent arg0) {
        Map<State, EdgeType> typeMap = new HashMap<>();
        switch (childEraType.getText()) {
            case "Two-Way":
                typeMap.put(State.CHILD, EdgeType.TWO_WAY);
                break;
            case "One-Way":
                typeMap.put(State.CHILD, EdgeType.ONE_WAY);
                break;
            case "No-Way":
                typeMap.put(State.CHILD, EdgeType.NO_WAY);
                break;
        }
        switch (adultEraType.getText()) {
            case "Two-Way":
                typeMap.put(State.ADULT, EdgeType.TWO_WAY);
                break;
            case "One-Way":
                typeMap.put(State.ADULT, EdgeType.ONE_WAY);
                break;
            case "No-Way":
                typeMap.put(State.ADULT, EdgeType.NO_WAY);
                break;
        }
        GUI.graph.connect(name.getText(), to.getText(), from.getText(), typeMap);
        System.out.println("hey wah it didn't crash");
    }
}
