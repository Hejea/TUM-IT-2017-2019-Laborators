package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.util.Duration;

import java.util.List;
import java.util.Stack;

public class DFSSolver extends PathFinding {
    public Stack<Cell> dfsStack = new Stack<>();
    
    public boolean pathFound = false;
    
    public DFSSolver(int width, int height, int i, int j, int ia, int ja, List<Cell> grid, Group tilegroupe) {
        super(width, height, i, j, ia, ja, grid, tilegroupe);
        current = grid.get(getIndex(i, j));
        dfsStack.push(current);
        solve();
    }
    
    public void solve() {
        if (Controller.animationSpeed == 0) {
            while (!dfsStack.empty()) {
                if ((current.getRow() == ia && current.getCol() == ja) || dfsStack.empty()) {
                    if (current.getRow() == ia && current.getCol() == ja) {
                        pathFound = true;
                        markThePath();
                        drawPath();
                    }
                    break;
                }
                OneIteration();
            }
        } else {
            pathFound = false;
            timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                if ((current.getRow() == ia && current.getCol() == ja) || dfsStack.empty()) {
                    if (current.getRow() == ia && current.getCol() == ja) {
                        pathFound = true;
                        markThePath();
                        drawPath();
                    }
                    timeline.stop();
                }
                OneIteration();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            timeline.setRate(Controller.animationSpeed);
        }
        
    }
    
    public void OneIteration() {
        current = dfsStack.pop();
        current.setVisited(true);
        int index       = getIndex(current.getRow(), current.getCol());
        int indexTop    = getIndex(current.getRow() - 1, current.getCol());
        int indexRight  = getIndex(current.getRow(), current.getCol() + 1);
        int indexBottom = getIndex(current.getRow() + 1, current.getCol());
        int indexLeft   = getIndex(current.getRow(), current.getCol() - 1);
        testNeighbors(index, indexTop, 0);
        testNeighbors(index, indexRight, 1);
        testNeighbors(index, indexBottom, 2);
        testNeighbors(index, indexLeft, 3);
        drawCell();
    }
    
    @Override
    public void testNeighbors(int indexCurr, int indexNeighbor, int wall) {
        Cell neighbor = (indexNeighbor != -1) ? grid.get(indexNeighbor) : null;
        if (neighbor != null && !current.getPosWalls(wall) && !neighbor.isProcessed()) {
            neighbor.setProcessed(true);
            parents.add(indexCurr);
            childrens.add(indexNeighbor);
            dfsStack.push(neighbor);
        }
    }
}
