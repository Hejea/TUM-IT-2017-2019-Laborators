package controller;

import app.tabs.GridPAdincime;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CTab4 {
    
    public GridPAdincime gCellAdincime;
    @FXML
    public Pane          base4Tab1;
    
    @FXML
    public void initialize() {
        System.out.println("Tab4 starts");
        
        gCellAdincime = new GridPAdincime();
        base4Tab1.getChildren().add(gCellAdincime);
    }
}
