package app.components;

import javafx.scene.control.TextField;

public class TextCell extends TextField {

    public TextCell(String text) {
        super(text);
        this.getStyleClass().add("text-cell");
    }
}
