package app.components;


import javafx.scene.control.Label;

public class LabelCell extends Label {
    
    private boolean state;

    public LabelCell(String text) {
        super(text);
        state = true;
        this.getStyleClass().add("label-cell");
    }
    
    public boolean getState() {
        return state;
    }
    
    public void setState(boolean state) {
        if (state) this.getStyleClass().remove("label-cell-disable");
        else this.getStyleClass().add("label-cell-disable");
        this.state = state;
    }
}
