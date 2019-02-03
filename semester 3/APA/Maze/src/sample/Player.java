package sample;


import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.List;

public class Player extends Pane {
    private int row;
    private int col;
    private double p  = 0.1;
    private double pw;
    private double ph;
    
    public Player(int x, int y) {
        this.row = y;
        this.col = x;
        
        p = 0.1;
        pw = Controller.cellWidth * p;
        ph = Controller.cellHeight * p;
        
        setWidth(Controller.cellWidth - pw);
        setHeight(Controller.cellHeight - ph);
        setMaxSize(Controller.cellWidth - pw, Controller.cellHeight - ph);
        setMinSize(Controller.cellWidth - pw, Controller.cellHeight - ph);
        setPrefSize(Controller.cellWidth - pw, Controller.cellHeight - ph);
        setStyle("-fx-background-color: chartreuse;");
        relocate(col * Controller.cellWidth + pw/2, row * Controller.cellHeight + ph/2);
    }
    
    public void moveTo(KeyCode to, List<Cell> grid) {
        switch (to) {
            case W:
                if (!grid.get(Controller.validCellIndex(col, row)).getPosWalls(0)) {
                    setPosition(row, col - 1);
                }
                break;
            case S:
                if (!grid.get(Controller.validCellIndex(col, row)).getPosWalls(2)) {
                    setPosition(row, col + 1);
                }
                break;
            case D:
                if (!grid.get(Controller.validCellIndex(col, row)).getPosWalls(1)) {
                    setPosition(row + 1, col);
                }
                break;
            case A:
                if (!grid.get(Controller.validCellIndex(col, row)).getPosWalls(3)) {
                    setPosition(row - 1, col);
                }
                break;
            default:
                break;
        }
    }
    
    public void setPosition(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        relocate(row * Controller.cellWidth + pw/2, col * Controller.cellHeight+ ph/2);
    }
    
    public int getCol() {
        return col;
    }
    
    public int getRow() {
        return row;
    }
}
