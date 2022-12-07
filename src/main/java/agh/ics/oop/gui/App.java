package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    private IWorldMap map;
    private IEngine engine;

    @Override
    public void init() throws Exception {
        super.init();
        MoveDirection[] directions = new MoveDirection[0];
        try {
            directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        } catch (IllegalArgumentException e) {
            System.exit(0);
        }
        map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(map);
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        Pair<Vector2d, Vector2d> boundaries = map.getMapBoundaries();
        int x_1 = boundaries.getFirst().x;
        int y_1 = boundaries.getFirst().y;
        int x_2 = boundaries.getSecond().x;
        int y_2 = boundaries.getSecond().y;
        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
        gridPane.getRowConstraints().add(new RowConstraints(30));

        // Draw boundaries
        Label corner = new Label("x/y");
        gridPane.add(corner, 0, 0);
        GridPane.setHalignment(corner, HPos.CENTER);
        for (int i = 0; i <= x_2 - x_1; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(30));
            Label label = new Label(String.valueOf(i + x_1));
            gridPane.add(label, i + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 0; i <= y_2 - y_1; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(30));
            Label label = new Label(String.valueOf(y_2 - i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, i + 1);

        }
        // Draw objects
        for (int i = y_1; i <= y_2; i++) {
            for (int j = x_1; j <= x_2; j++) {
                Object o = map.objectAt(new Vector2d(j, i));
                if (o != null) {
                    Label label = new Label(o.toString());
                    GridPane.setHalignment(label, HPos.CENTER);

                    gridPane.add(label, j + 1 - x_1, y_2 - i + 1);
                }
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}