package controller;

import app.tabs.GridListaAdiacenta;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CTab3 {
    
    public GridListaAdiacenta gCellListaAdiacenta;
    @FXML
    public HBox               base3Tab1;
    @FXML
    public Pane               base3Tab2;
    @FXML
    public HBox               base3Tab3;
    
    private CMain cMain;
    
    @FXML
    public void initialize() {
        System.out.println("Tab3 starts");
        
        gCellListaAdiacenta = new GridListaAdiacenta();
        base3Tab2.getChildren().add(gCellListaAdiacenta);
    }
    
    public void init(CMain cMain) { this.cMain = cMain; }
}
