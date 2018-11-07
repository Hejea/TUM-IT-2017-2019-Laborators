package app.components;

import javafx.scene.control.Label;

public class ButtonCellRemove extends Label {

    public ButtonCellRemove(String text) {
        super(text);
        this.getStyleClass().add("remove-button-cell");
    }
}
