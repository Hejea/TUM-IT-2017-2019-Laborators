package controller;

import app.tabs.GridMatrixAdiacenta;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CTab1 {
    
    public  GridMatrixAdiacenta gCellMatrixAdiacenta;
    @FXML
    public  HBox                base1Tab1;
    @FXML
    public  Pane                base1Tab2;
    private CMain               cMain;
    
    @FXML
    public void initialize() {
        System.out.println("Tab1 starts");
        
        gCellMatrixAdiacenta = new GridMatrixAdiacenta();
        
        base1Tab2.getChildren().add(gCellMatrixAdiacenta);
    }
    
    @FXML public void init(CMain cMain) { this.cMain = cMain; }
    
}
