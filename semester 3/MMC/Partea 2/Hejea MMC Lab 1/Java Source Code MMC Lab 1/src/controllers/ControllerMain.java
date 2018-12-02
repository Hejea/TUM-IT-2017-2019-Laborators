package controllers;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerMain {
    
    public static Stage appMainStage;
    public static VBox  outerContainer;
    public static VBox  outerContainer1;
    
    @FXML
    public VBox vBox;
    
    
    @FXML
    public void initialize() {
        System.out.println("Main Controller Starts");
        
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            //System.out.println("Height: " + appMainStage.getHeight() + " Width: " + appMainStage.getWidth());
            vBox.setMaxWidth(500);
            vBox.setPrefWidth(500);
            vBox.setMaxHeight(appMainStage.getHeight());
            vBox.setPrefHeight(appMainStage.getHeight());
            appMainStage.setMaxWidth(1356);
            appMainStage.setMinWidth(1356);
            appMainStage.setMaxHeight(435);
            appMainStage.setMinHeight(435);
    
            //outerContainer.setMaxHeight(appMainStage.getHeight() - 385);
            //outerContainer.setPrefHeight(appMainStage.getHeight() - 385);
            //outerContainer1.setMaxHeight(appMainStage.getHeight() - 385);
            //outerContainer1.setPrefHeight(appMainStage.getHeight() - 385);
        };
        appMainStage.widthProperty().addListener(stageSizeListener);
        appMainStage.heightProperty().addListener(stageSizeListener);
    }
}
