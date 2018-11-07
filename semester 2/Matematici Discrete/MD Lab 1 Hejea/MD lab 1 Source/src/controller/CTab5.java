package controller;

import app.tabs.GridPLatime;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class CTab5 {
    
    public GridPLatime gCellLatime;
    @FXML
    public HBox        base5Tab1;
    
    @FXML
    public void initialize() {
        System.out.println("Tab5 starts");
        
        gCellLatime = new GridPLatime();
        base5Tab1.getChildren().add(gCellLatime);
    }
}
