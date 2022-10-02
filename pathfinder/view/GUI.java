package pathfinder.view;

import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pathfinder.Graph;
import pathfinder.Vertex;
import pathfinder.Edge.EdgeType;
import pathfinder.view.controller.EdgeCreationUpdater;
import pathfinder.view.controller.TypeToggleUpdater;

public class GUI extends Application {

    public static Graph graph;
    // public static Set<Node> selection;

    private static Button mapVertexButton(Vertex v) {
        Button button = new Button();
        button.setMaxWidth(50);
        button.setMaxHeight(50);
        button.setBackground(new Background(new BackgroundFill(Color.AZURE, new CornerRadii(10), Insets.EMPTY)));
        button.setText(v.getName());
        return button;
    }

    private static TextField makeTextField(String promptText) {
        TextField field = new TextField();
        field.setPromptText(promptText);
        return field;
    }

    private static ImageView makeImageView(String file) {
        ImageView image = new ImageView(file);
        image.setFitHeight(50);
        image.setFitWidth(50);
        return image;
    }

    private static Button makeTypeToggleButton() {
        Button button = new Button("Two-Way");
        button.setOnAction(new TypeToggleUpdater(EdgeType.TWO_WAY, button));
        return button;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        graph = new Graph();

        BorderPane borderPane = new BorderPane();
        GridPane map = new GridPane();
        VBox list = new VBox();
        HBox control = new HBox();
        HBox info = new HBox(); // subject to change if HBox isn't chill
        VBox pather = new VBox(); // also subject to change

        borderPane.setCenter(map);
        borderPane.setBottom(info);
        borderPane.setLeft(pather);
        borderPane.setRight(list);
        borderPane.setTop(control);

        // Controls
        Label fromHelp = new Label("Where from?");
        TextField fromInput = makeTextField("ex: Hyrule Field");
        Label toHelp = new Label("Where to?");
        TextField toInput = makeTextField("ex: Zora River");
        Label nameHelp = new Label("Name it something:");
        TextField nameInput = makeTextField("ex: River / Bottom");
        ImageView childImage = makeImageView("file:pathfinder/view/media/era_child.png");
        ImageView adultImage = makeImageView("file:pathfinder/view/media/era_adult.png");
        Button childTypeToggle = makeTypeToggleButton();
        Button adultTypeToggle = makeTypeToggleButton();
        Button createEdge = new Button("Create New Connection");
        createEdge.setOnAction(new EdgeCreationUpdater(fromInput, toInput, nameInput, childTypeToggle, adultTypeToggle, createEdge));
        Button clearFields = new Button("Clear Fields"); // deal with it later lmao

        VBox textInputs = new VBox(fromHelp, fromInput, toHelp, toInput, nameHelp, nameInput);
        HBox childSettings = new HBox(childImage, childTypeToggle);
        HBox adultSettings = new HBox(adultImage, adultTypeToggle);
        HBox finalSettings = new HBox(createEdge, clearFields);
        VBox otherInputs = new VBox(childSettings, adultSettings, finalSettings);
        control.getChildren().addAll(textInputs, otherInputs);


        Scene scene = new Scene(borderPane);

        stage.setScene(scene);
        stage.setTitle("OOTR Pathfinding Tool");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
