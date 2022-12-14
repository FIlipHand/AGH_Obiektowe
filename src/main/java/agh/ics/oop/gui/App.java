package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private IWorldMap map;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private GridPane gridPane = new GridPane();
    private VBox startBox;

    @Override
    public void init() throws Exception {
        super.init();
        map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(map, positions);
        Thread thread = new Thread(engine);
        engine.addObserver(() -> Platform.runLater(this::drawGridElements));

        TextField textField = new TextField();
        Button button = new Button("Start");
        button.setOnAction(a -> {
            engine.setDirections(textField.getText().split(" "));
            thread.start();
            button.setDisable(true);
        });
        startBox = new VBox(textField, button);
        drawGridElements();
    }

    private void drawGridElements() {
        gridPane.setGridLinesVisible(false);
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.setGridLinesVisible(true);

        Pair<Vector2d, Vector2d> boundaries = map.getMapBoundaries();
        int x_1 = boundaries.getFirst().x;
        int y_1 = boundaries.getFirst().y;
        int x_2 = boundaries.getSecond().x;
        int y_2 = boundaries.getSecond().y;
        gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH));
        gridPane.getRowConstraints().add(new RowConstraints(HEIGHT));

        // Draw boundaries
        Label corner = new Label("x/y");
        gridPane.add(corner, 0, 0);
        GridPane.setHalignment(corner, HPos.CENTER);
        for (int i = 0; i <= x_2 - x_1; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH));
            Label label = new Label(String.valueOf(i + x_1));
            gridPane.add(label, i + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 0; i <= y_2 - y_1; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(HEIGHT));
            Label label = new Label(String.valueOf(y_2 - i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, i + 1);

        }
        // Draw objects
        for (int i = y_1; i <= y_2; i++) {
            for (int j = x_1; j <= x_2; j++) {
                Object o = map.objectAt(new Vector2d(j, i));
                if (o != null) {
                    VBox vBox = new GuiElementBox((IMapElement) o).getVerticalBox();
                    GridPane.setHalignment(vBox, HPos.CENTER);

                    gridPane.add(vBox, j + 1 - x_1, y_2 - i + 1);
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox everything = new VBox(this.startBox ,gridPane);
        Scene scene = new Scene(everything, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
