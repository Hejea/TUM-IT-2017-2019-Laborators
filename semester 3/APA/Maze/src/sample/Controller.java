package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    
    private final static double SCENEWIDTH  = 600;
    private final static double SCENEHEIGHT = 600;
    
    public static List<Cell> grid = new ArrayList<>(); // le labyrinthe :D
    
    static int    animationSpeed = 0;
    static int    mazeWidth      = 15;
    static int    mazeHeight     = 15;
    static double cellWidth      = SCENEWIDTH / mazeWidth;
    static double cellHeight     = SCENEHEIGHT / mazeHeight;
    
    private static boolean cleared = false;
    private static Player         player; // joueur vert
    private static FinishCell     finishCell; //Arrivé bleue
    private static AbstractSolver solver; // L'objet qui resout le labyrinthe
    
    @FXML
    private ToggleGroup Grp1, Grp, Grp2; //les groupes de radiobuttons
    
    @FXML
    private Label generationTime;
    @FXML
    private Label solveTime; //les label de temps de generation/resolution
    @FXML
    private Pane  mainPane; // l'afficheur
    
    @FXML
    private TextField tailleTextField; //Taille
    private Group tileGroup = new Group(); // groupe de tout les cellules du labyrinthe
    
    public static int validCellIndex(int i, int j) {
        return (i < 0 || j < 0 || j > mazeHeight - 1 || i > mazeHeight - 1) ? -1 : (j + i * mazeWidth);
    }
    
    @FXML
    public void initialize() {
        mainPane.setStyle("-fx-background-color:white;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"); // effets css
        mainPane.getChildren().addAll(tileGroup);
        tailleTextField.setText(String.valueOf(mazeWidth));
    }
    
    
    @FXML
    private void handleGenerateClick(ActionEvent event) {
        generateMaze();
    }
    
    
    @FXML
    public void keyListener(KeyEvent event) {
        player.moveTo(event.getCode(), grid); // deplacer le joueur
        checkWin(); // et voir si il a gagné
    }
    
    
    @FXML
    private void handleClearClick(ActionEvent event) {
        clearMaze();
    }
    
    
    @FXML
    private void handleSolveClick(ActionEvent event) {
        clearPathMaze(grid, tileGroup);
        if (!grid.isEmpty())
            solveMaze();
    }
    
    
    private void generateMaze() {
        clearMaze();
        updateGrid();
        long startTime = System.currentTimeMillis(); // start
        grid = getSelectedGenerator();
        player = new Player(0, 0);
        finishCell = new FinishCell(mazeWidth - 1, mazeHeight - 1);
        long timer = System.currentTimeMillis() - startTime; // end
        generationTime.setText(timer + " ms");
        DrawMaze();
    }
    
    
    private void solveMaze() {
        long startTime = System.currentTimeMillis();
        animationSpeed = getSelectedSpeed();
        grid = getSelectedSolver();
        long timer = System.currentTimeMillis() - startTime;
        solveTime.setText(timer + " ms");
        
    }
    
    private int getSelectedSpeed() {
        int i = 0;
        if (Grp2.getToggles().get(0).isSelected()) {
            i = 0;
        }
        if (Grp2.getToggles().get(1).isSelected()) {
            i = 5 * mazeHeight;
            i=100;
        }
        return i;
    }
    
    private List<Cell> getSelectedSolver() {
        RadioButton selectedRadioButton = (RadioButton) Grp1.getSelectedToggle();
        
        String choice = selectedRadioButton.getText();
        
        switch (choice) {
            case "BFS":
                BFSSolver bfsSolver = new BFSSolver(mazeWidth, mazeHeight, player.getCol(), player.getRow()
                        , finishCell.getRow(), finishCell.getCol(), grid, tileGroup);
                solver = bfsSolver;
                return bfsSolver.getMaze();
            case "DFS":
                DFSSolver dfsSolver = new DFSSolver(mazeWidth, mazeHeight, player.getCol(), player.getRow()
                        , finishCell.getRow(), finishCell.getCol(), grid, tileGroup);
                solver = dfsSolver;
                return dfsSolver.getMaze();
            case "Dijkstra's":
                DijkstraSolver dijkstraSolver = new DijkstraSolver(mazeWidth, mazeHeight, player.getCol(), player.getRow()
                        , finishCell.getRow(), finishCell.getCol(), grid, tileGroup);
                solver = dijkstraSolver;
                return dijkstraSolver.getMaze();
            default:
                WallFollower wallFollower = new WallFollower(mazeWidth, mazeHeight, player.getCol(), player.getRow()
                        , finishCell.getRow(), finishCell.getCol(), grid, tileGroup);
                solver = wallFollower;
                return wallFollower.getMaze();
        }
    }
    
    private List<Cell> getSelectedGenerator() {
        RadioButton selectedRadioButton = (RadioButton) Grp.getSelectedToggle();
        String      choice              = selectedRadioButton.getText();
        switch (choice) {
            case "Prim":
                return new PrimGenerator(mazeWidth, mazeHeight, tileGroup).generateMaze().getMaze();
            case "Kruskal":
                return new KruskalMazeGenerator(mazeWidth, mazeHeight, tileGroup).generateMaze().getMaze();
            case "Hunt And Kill":
                return new HuntAndKillGenerator(mazeWidth, mazeHeight, tileGroup).generateMaze().getMaze();
            default:
                return new DFSBackTrackerGenerator(mazeWidth, mazeHeight, tileGroup).generateMaze().getMaze();
        }
    }
    
    
    public void DrawMaze() {
        cleared = false;
        String color;
        Cell   temp;
        
        for (int x = 0; x < grid.size(); x++) {
            float top    = 0.0F;
            float bottom = 0.0F;
            float left   = 0.0F;
            float right  = 0.0F;
            temp = grid.get(x);
            
            if (temp.getPosWalls(0)) top = 1F;
            if (temp.getPosWalls(3)) left = 1F;
            if (temp.getPosWalls(1)) right = 1F;
            if (temp.getPosWalls(2)) bottom = 1F;
            
            color = !grid.get(x).getSolvedVisited() ? "#222" : "#FFF";
            
            String s = String.format("-fx-border-width: %s %s %s %s;", top, right, bottom, left);
            s += "-fx-border-color: black black black black;";
            s += String.format("\n-fx-background-color: %s;", color);
            temp.setStyle(s);
            
            s = "-fx-background-color: steelblue;";
            tileGroup.getChildren().add(temp);
        }
        tileGroup.getChildren().add(player);
        tileGroup.getChildren().add(finishCell);
    }
    
    private void updateGrid() {
        int w = 5;
        int h = 5;
        try {
            w = Integer.valueOf(tailleTextField.getText());
            h = Integer.valueOf(tailleTextField.getText());
            if (h < 5 || h > 301) { h = 5;}
            if (w < 5 || w > 301) { w = 5;}
            
        } catch (Exception e) {
        
        } finally {
            tailleTextField.setText(String.valueOf(w));
            tailleTextField.setText(String.valueOf(h));
            mazeWidth = w;
            mazeHeight = h;
            cellWidth = SCENEWIDTH / mazeWidth;
            cellHeight = SCENEHEIGHT / mazeHeight;
            clearMaze();
        }
    }
    
    private void clearMaze() {
        if (!cleared) {
            if (solver != null) {
                if (solver.getTimeline() != null) {
                    solver.getTimeline().stop(); // Arreter l'animation au cas ou ...
                }
            }
            grid.clear();
            tileGroup.getChildren().clear();
            solveTime.setText("");
            generationTime.setText("");
            cleared = true;
        }
    }
    
    private void clearPathMaze(List<Cell> grid, Group tilegroup) {
        if (solver != null) {
            if (solver.getTimeline() != null) {
                solver.getTimeline().stop(); // Arreter l'animation au cas ou ...
            }
        }
        for (int x = 0; x < grid.size(); x++) {
            grid.get(x).setVisited(true);
            grid.get(x).setProcessed(false);
            String helper;
            Node node = tilegroup.getChildren().get(x);
            helper = node.getStyle().replace("steelblue", "#FFF");
            node.setStyle(helper);
            helper = node.getStyle().replace("#a3c9f8", "#FFF");
            node.setStyle(helper);
            helper = node.getStyle().replace("#aaa", "#FFF");
            node.setStyle(helper);
        }
    }
    
    public void checkWin() {
        if (player.getRow() == finishCell.getRow() && player.getCol() == finishCell.getCol()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitare");
            alert.setHeaderText("Nivelul de succes!");
            mazeWidth += 10;
            mazeHeight += 10;
            alert.setContentText("Nivelul următor : " + mazeHeight);
            tailleTextField.setText(String.valueOf(mazeWidth));
            tailleTextField.setText(String.valueOf(mazeHeight));
            updateGrid();
            generateMaze();
            alert.showAndWait();
        }
    }
}
