package controller;

import app.components.MethodsStatic;
import controller.MenuComponets.CMenu;
import controller.MenuComponets.OpenFromFile;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class CMain {
    
    public static Stage stage;
    
    @FXML
    public  VBox    vBox;
    @FXML
    public  Tab     idMatrixAdiacenta;
    @FXML
    public  Tab     idMatrixIncidenta;
    @FXML
    public  Tab     idListaAdiacenta;
    @FXML
    public  Tab     idPAdincime;
    @FXML
    public  Tab     idPLatime;
    @FXML
    public  TabPane idTabPane;
    @FXML
    private CMenu   menuController;
    @FXML
    public  CTab1   tab1Controller;
    @FXML
    public  CTab2   tab2Controller;
    @FXML
    public  CTab3   tab3Controller;
    @FXML
    public  CTab4   tab4Controller;
    @FXML
    public  CTab5   tab5Controller;
    
    @FXML
    public void initialize() {
        System.out.println("Main starts");
        
        
        tab1Controller.init(this);
        tab2Controller.init(this);
        tab3Controller.init(this);
        menuController.init(this);
        
        idMatrixAdiacenta.setOnSelectionChanged(event -> onChangedFormView());
        idMatrixIncidenta.setOnSelectionChanged(event -> onChangedFormView());
        idListaAdiacenta.setOnSelectionChanged(event -> onChangedFormView());
        
        idPAdincime.setOnSelectionChanged(event -> onChanged());
        idPLatime.setOnSelectionChanged(event -> onChanged());
        
        
        /*
        File         ff = new File("G:\\Programe\\UTM 2017\\s 2\\MD\\MD Lab 1 Hejea\\MD lab 1 Source\\res\\kk.txt");
        OpenFromFile op = new OpenFromFile(ff, this);
        op.open();
        menuController.onClickEdit();
        idTabPane.getSelectionModel().select(idPAdincime);
        */
        
        
        //stage.setOnCloseRequest(event -> menuController.onClickClose());
        
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            //System.out.println("Height: " + stage.getHeight() + " Width: " + stage.getWidth());
            vBox.setMaxWidth(stage.getWidth());
            vBox.setPrefWidth(stage.getWidth());
            vBox.setMaxHeight(stage.getHeight());
            vBox.setPrefHeight(stage.getHeight());
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
        
        
    }

    public void onChangedFormView() {
       
        if (idMatrixAdiacenta.isSelected() ||
            idMatrixIncidenta.isSelected() ||
            idListaAdiacenta.isSelected()) {
            
            menuController.onClickCancel();
            menuController.onClickApply();
        }
    }
    
    public void onChanged() {
        if (idPAdincime.isSelected()) {
            tab4Controller.gCellAdincime.createNewList(tab3Controller.gCellListaAdiacenta.getListInteger());
            MethodsStatic.addListAdiacenta(tab3Controller.gCellListaAdiacenta, tab4Controller.gCellAdincime);
        }
    
        if (idPLatime.isSelected()) {
            tab5Controller.gCellLatime.createNewList(tab3Controller.gCellListaAdiacenta.getListInteger());
            MethodsStatic.addListAdiacenta(tab3Controller.gCellListaAdiacenta, tab5Controller.gCellLatime);
        }
    }
    
}
