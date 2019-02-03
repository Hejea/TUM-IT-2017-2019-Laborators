package sample;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class PathFinding extends AbstractSolver {
    
    List<Integer> parents   = new ArrayList<>();
    List<Integer> childrens = new ArrayList<>();
    boolean       pathFound = false;
    
    public PathFinding(int width, int height, int i, int j, int ia, int ja, List<Cell> grid, Group tilegroupe) {
        super(width, height, i, j, ia, ja, grid, tilegroupe);
        pathFound = false;
        parents.add(-1);
        childrens.add(getIndex(i, j));
    }
    
    public abstract void testNeighbors(int indexCurr, int indexNeighbor, int wall);
    
    public void markThePath() {
        
        grid.get(getIndex(ia, ja)).setVisited(false);
        int a = childrens.indexOf(getIndex(ia, ja));
        try {
            while (parents.get(a) != -1) {
                grid.get(parents.get(a)).setVisited(false);
                a = childrens.indexOf(parents.get(a));
            }
        } catch (Exception e) { }
        
    }
}
