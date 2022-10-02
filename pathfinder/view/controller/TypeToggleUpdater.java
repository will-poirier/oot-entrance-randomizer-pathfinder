package pathfinder.view.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import pathfinder.Edge.EdgeType;

public class TypeToggleUpdater implements EventHandler<ActionEvent> {
    private EdgeType type;
    private Button button;
    
    public TypeToggleUpdater(EdgeType type, Button button) {
        this.type = type;
        this.button = button;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (type) {
            case ONE_WAY:
                type = EdgeType.NO_WAY;
                button.setText("No-Way");
                break;
            case TWO_WAY:
                type = EdgeType.ONE_WAY;
                button.setText("One-Way");
                break;
            case NO_WAY:
                type = EdgeType.TWO_WAY;
                button.setText("Two-Way");
                break;
        }
    }

    public EdgeType getType() {
        return type;
    }
}
