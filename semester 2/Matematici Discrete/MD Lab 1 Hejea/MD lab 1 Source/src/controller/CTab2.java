package controller;

import app.tabs.GridMatrixIncidenta;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CTab2 {
    
    public  GridMatrixIncidenta gCellMatrixIncidenta;
    @FXML
    public  HBox                base2Tab1;
    @FXML
    public  Pane                base2Tab2;
    private CMain               cMain;
    
    @FXML
    public void initialize() {
        System.out.println("Tab2 starts");
        
        gCellMatrixIncidenta = new GridMatrixIncidenta();
        
        base2Tab2.getChildren().add(gCellMatrixIncidenta);
    }
    
    @FXML
    public void init(CMain cMain) {
        this.cMain = cMain;
    }
}
