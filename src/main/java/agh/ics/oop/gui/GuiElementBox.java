package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    private VBox verticalBox;

    public GuiElementBox(IMapElement element) {
        ImageView imageView;
        try {
            Image image = new Image(new FileInputStream(element.getImagePath()));
            imageView = new ImageView(image);
            imageView.setFitHeight(HEIGHT);
            imageView.setFitWidth(WIDTH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Label label = new Label(element.getPosition().toString());
        this.verticalBox = new VBox(imageView, label);
        this.verticalBox.setAlignment(Pos.CENTER);
    }

    public VBox getVerticalBox() {
        return this.verticalBox;
    }
}
