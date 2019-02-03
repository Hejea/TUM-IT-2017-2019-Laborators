package sample;

import javafx.scene.layout.Pane;

public class FinishCell extends Pane {
    private int row;
    private int col;
    
    public FinishCell(int x, int y) {
        this.row = x;
        this.col = y;
    
        double p = 0.1;
        double pw = Controller.cellWidth * p;
        double ph = Controller.cellHeight * p;
    
        setWidth(Controller.cellWidth - pw);
        setHeight(Controller.cellHeight - ph);
        setMaxSize(Controller.cellWidth - pw, Controller.cellHeight - ph);
        setMinSize(Controller.cellWidth - pw, Controller.cellHeight - ph);
        setPrefSize(Controller.cellWidth - pw, Controller.cellHeight - ph);
        setStyle("-fx-background-color: coral;");
        relocate(col * Controller.cellWidth + pw / 2, row * Controller.cellHeight + ph / 2);
    }
    
    public int getCol() {
        return col;
    }
    
    public int getRow() {
        return row;
    }
}
