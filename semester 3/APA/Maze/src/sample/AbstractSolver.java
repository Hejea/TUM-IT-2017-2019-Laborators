package sample;

import javafx.animation.Timeline;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolver extends AbstractMaze {
    public Timeline       timeline;
    
    protected List<Cell> grid = new ArrayList<>();
    protected Cell current;
    protected int  ia, ja;
    protected int i, j;
    
    public AbstractSolver(int width, int height, int i, int j, int ia, int ja, List<Cell> currGrid, Group tilegroup) {
        super(height, width, tilegroup);
        this.ia = ia;
        this.ja = ja;
        this.i = i;
        this.j = j;
        grid = currGrid;
    }
    
    public abstract void solve();
    
    public abstract void OneIteration();
    
    public Timeline getTimeline() {
        return timeline;
    }
    
    public List<Cell> getMaze() {
        return grid;
    }
    
    public void drawPath() {
        for (int x = 0; x < grid.size(); x++) {
            if (!grid.get(x).isVisited()) {
                String helper;
                helper = tilegroup.getChildren().get(x).getStyle();
                helper = helper.replace("#a3c9f8", "steelblue");
                tilegroup.getChildren().get(x).setStyle(helper);
            }
        }
    }
    
    public void drawCell() {
        String helper;
        helper = tilegroup.getChildren().get(getIndex(current.getRow(), current.getCol())).getStyle();
        helper = helper.replace("#FFF", "#a3c9f8");
        tilegroup.getChildren().get(getIndex(current.getRow(), current.getCol())).setStyle(helper);
    }
}
