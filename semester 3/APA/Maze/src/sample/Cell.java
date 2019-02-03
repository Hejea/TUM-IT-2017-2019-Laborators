package sample;

import javafx.scene.layout.Pane;

import java.util.List;

public class Cell extends Pane {
    private int col, row, id;
    private boolean[] walls         = {true, true, true, true}; // Top right bottom  left
    private boolean   visited       = false;
    private boolean   solvedVisited = true;
    private boolean   processed     = false;
    
    //Constructor
    public Cell(int i, int j) {
        super();
        row = i;
        col = j;
        
        double h = Controller.cellHeight;
        double w = Controller.cellWidth;
        
        setWidth(w);
        setHeight(h);
        relocate(col * w, row * h);
        setMaxSize(w, h);
        setMinSize(w, h);
        setPrefSize(w, h);
    }
    
    //Getters and setters
    public boolean isProcessed() {
        return processed;
    }
    
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public boolean[] getWalls() {
        return walls;
    }
    
    public boolean getPosWalls(int pos) {
        return walls[pos];
    }
    
    public void setWalls(int pos, boolean value) {
        this.walls[pos] = value;
    }
    
    public boolean isVisited() {
        return visited;
    }
    
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public int getIdforEller() {
        return id;
    }
    
    public void setIdforEller(int id) {
        this.id = id;
    }
    
    public Cell getTopNeighbour(List<Cell> grid) {
        if (row != 0) {
            return grid.get(getIndex(row - 1, col));
        } else {
            return null;
        }
    }
    
    public Cell getRightNeighbour(List<Cell> grid) {
        if (row != Controller.mazeHeight - 1) {
            return grid.get(getIndex(row, col + 1));
        } else {
            return null;
        }
    }
    
    public Cell getBottomNeighbour(List<Cell> grid) {
        if (row != Controller.mazeHeight - 1) {
            return grid.get(getIndex(row + 1, col));
        } else {
            return null;
        }
    }
    
    public Cell getLeftNeighbour(List<Cell> grid) {
        if (col != 0) {
            return grid.get(getIndex(row, col - 1));
        } else {
            return null;
        }
    }
    
    public boolean getSolvedVisited() {
        return this.solvedVisited;
    }
    
    public void removeWalls(Cell neighbor) {
        int x = this.col - neighbor.col;
        int y = this.row - neighbor.row;
        
        if (x == 1) {
            this.setWalls(3, false);
            neighbor.setWalls(1, false);
        } else if (x == -1) {
            this.setWalls(1, false);
            neighbor.setWalls(3, false);
        }
        
        if (y == 1) {
            this.setWalls(0, false);
            neighbor.setWalls(2, false);
        } else if (y == -1) {
            this.setWalls(2, false);
            neighbor.setWalls(0, false);
        }
    }
    
    @Override
    public String toString() {
        return "" + walls[0] + "," + walls[1] + "," + walls[2] + "," + walls[3] + "\n";
    }
    
    public int getIndex(int i, int j) {
        return (i < 0 || j < 0 || j > Controller.mazeWidth - 1 || i > Controller.mazeHeight - 1) ? -1 : (j + i * Controller.mazeWidth);
    }
    
}