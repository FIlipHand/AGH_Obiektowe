package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
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
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(directions, map, positions);
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
        gridPane.getColumnConstraints().add(new ColumnConstraints(20));
        gridPane.getRowConstraints().add(new RowConstraints(20));
        System.out.println(boundaries);
        gridPane.add(new Label("x/y"), 0, 0);
        for (int i = 1; i <= x_2 - x_1 + 1; i++) {
            gridPane.add(new Label(String.valueOf(i + x_1 - 1)), i, 0);
        }
        for (int i = 1; i <= y_2 - y_1 + 1; i++) {
            gridPane.add(new Label(String.valueOf(y_2 - i + 1)), 0, i);
        }

        for (int i = y_1; i <= y_2 ; i++) {
            for (int j = x_1; j <= x_2; j++) {
                Object o = map.objectAt(new Vector2d(j, i));
                if (o != null) {
                    gridPane.add(new Label(o.toString()), j + 1 - x_1, y_2 - i + 1);
                }
            }
        }

//        GridPane.setHalignment(label, HPos.CENTER);
        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
