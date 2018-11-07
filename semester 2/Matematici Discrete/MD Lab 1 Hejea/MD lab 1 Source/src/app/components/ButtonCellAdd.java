package app.components;

import javafx.scene.control.Label;

public class ButtonCellAdd extends Label {
    
    public ButtonCellAdd(String text) {
        super(text);
        this.getStyleClass().add("add-button-cell");
    }
}
