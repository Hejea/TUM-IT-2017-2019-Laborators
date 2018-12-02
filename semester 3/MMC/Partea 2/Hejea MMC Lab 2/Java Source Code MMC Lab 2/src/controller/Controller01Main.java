package controller;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller01Main {
    
    public static Stage      appMainStage;
    
    @FXML
    public VBox vBox;
    
    @FXML
    public void initialize() {
        System.out.println("Starts : Controller 01 Main");
        
        int width = 915;
        int height = 740;
        
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            //System.out.println("Height: " + appMainStage.getHeight() + " Width: " + appMainStage.getWidth());

            appMainStage.setMaxWidth(width);
            appMainStage.setMinWidth(width);
            appMainStage.setMinHeight(height);
            appMainStage.setMaxHeight(height);
            
        };
        appMainStage.widthProperty().addListener(stageSizeListener);
        appMainStage.heightProperty().addListener(stageSizeListener);
    }
}
