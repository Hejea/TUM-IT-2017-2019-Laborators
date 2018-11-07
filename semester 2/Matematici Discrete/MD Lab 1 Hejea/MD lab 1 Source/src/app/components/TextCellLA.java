package app.components;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class TextCellLA extends TextCell implements EventHandler<MouseEvent> {
    
    private boolean state;

    public TextCellLA(String text) {
        super(text);
        state = true;
        this.setEditable(false);
        this.setOnMouseClicked(this);
        this.setCursor(Cursor.HAND);
    }
    
    @Override
    public void handle(MouseEvent event) {
        this.setState(!state);
    }
    
    public boolean getState() {
        return state;
    }
    
    public void setState(boolean state) {
        if (!state) this.getStyleClass().add("text-cell-disable");
        else this.getStyleClass().remove("text-cell-disable");
        this.state = state;
    }
    
    public void setStateWithEnable(boolean state) {
        setState(state);
        setMouseTransparent(!state);
    }
}
