package controller.MenuComponets;

import app.components.MethodsStatic;
import controller.CMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CMenu {
    
    private static CMain      cMain;
    private static Pane[]     panes;
    private static GridPane[] gridPanes;
    public         Button     bApply;
    public         Button     bCancel;
    private        int        statusExit;
    @FXML
    private        MenuItem   idEdit;
    @FXML
    private        Menu       idSaveAs;
    @FXML
    private        MenuItem   idSave0;
    @FXML
    private        MenuItem   idSave1;
    @FXML
    private        MenuItem   idSave2;
    
    public CMenu() {
        statusExit = 0;
        
        bApply = new Button("Apply");
        bCancel = new Button("Cancel");
        
        bApply.setOnAction(e -> onClickApply());
        bApply.getStyleClass().add("apply-button");
        
        bCancel.setOnAction(e -> onClickCancel());
        bCancel.getStyleClass().add("cancel-button");
    }
    
    static List<ObservableList<Node>> getA(int index) {
        List<ObservableList<Node>> a = new ArrayList<>();
        for (int i = 0; i < panes.length; i++) if (i != index) a.add(CMenu.panes[i].getChildren());
        return a;
    }
    
    static List<GridPane> getB(int index) {
        List<GridPane> b = new ArrayList<>();
        for (int i = 0; i < gridPanes.length; i++) if (i != index) b.add(CMenu.gridPanes[i]);
        return b;
    }
    
    @FXML
    public void onClickNew() {
        onClickDelete();
    }
    
    @FXML
    public void onClickOpen() {
        FileChooser fileChooser = new FileChooser();
        MethodsStatic.setFileChooserProprietes(fileChooser, "Open");
        
        File selectedFile = fileChooser.showOpenDialog(CMain.stage);
        if (selectedFile != null) {
            statusExit = 1;
            OpenFromFile openFromFile = new OpenFromFile(selectedFile, cMain);
            onClickCancel();
            onClickApply();
            openFromFile.open();
        }
    }
    
    @FXML
    public void onClickSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        MethodsStatic.setFileChooserProprietes(fileChooser, "Save");
        
        File selectedFile = fileChooser.showSaveDialog(CMain.stage);
        if (selectedFile != null) {
            SaveToFile saveToFile = new SaveToFile(selectedFile, cMain);
            String     x          = "0";
            if (event != null) x = ((MenuItem) event.getSource()).getId();
            statusExit = 0;
            if (x.equals(idSave0.getId()) || cMain.idMatrixAdiacenta.isSelected()) {
                saveToFile.saveMatrixAdiacenta();
            } else if (x.equals(idSave1.getId()) || cMain.idMatrixIncidenta.isSelected()) {
                saveToFile.saveMatrixIncidenta();
            } else if (x.equals(idSave2.getId())) {
                System.out.println(" = 2");
            }
        }
    }
    
    @FXML
    public void onClickEdit() {
        if (cMain.idMatrixAdiacenta.isSelected()) {
            cMain.tab1Controller.gCellMatrixAdiacenta.showListButtonCell();
            cMain.tab1Controller.base1Tab1.getChildren().addAll(bApply, bCancel);
        }
        
        if (cMain.idMatrixIncidenta.isSelected()) {
            cMain.tab2Controller.gCellMatrixIncidenta.showListButtonCell();
            cMain.tab2Controller.base2Tab1.getChildren().addAll(bApply, bCancel);
        }
        
        if (cMain.idListaAdiacenta.isSelected()) {
            cMain.tab3Controller.gCellListaAdiacenta.showListButtonCell();
            cMain.tab3Controller.base3Tab1.getChildren().addAll(bApply, bCancel);
        }
        idSaveAs.setDisable(true);
        idEdit.setDisable(true);
    }
    
    @FXML
    public void onClickClose() {
        if (statusExit == 0) {
            System.exit(0);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Request");
        alert.setHeaderText("Do you want to save your data ?");
        alert.setContentText(null);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Save");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Close");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) onClickSave(null);
        System.exit(0);
    }
    
    @FXML
    public void onClickDelete() {
        cMain.tab1Controller.gCellMatrixAdiacenta.createNewMatrix(new String[0], 0);
        cMain.tab2Controller.gCellMatrixIncidenta.createNewMatrix(new String[0], 0, 0);
        cMain.tab3Controller.gCellListaAdiacenta.createNewList(new String[0], 0);
        cMain.tab4Controller.gCellAdincime.createNewList(new ArrayList<>());
        cMain.tab5Controller.gCellLatime.createNewList(new ArrayList<>());
    }
    
    public void onClickApply() {
        try {
            if (cMain.idMatrixAdiacenta.isSelected()) {
                cMain.tab1Controller.gCellMatrixAdiacenta.removeMarkedElements();
                cMain.tab1Controller.gCellMatrixAdiacenta.removeInvalidEdge();
                MethodsStatic.refresh(cMain.tab1Controller.gCellMatrixAdiacenta, getA(0), getB(0));
                cMain.tab1Controller.gCellMatrixAdiacenta.hideListButtonCell();
                cMain.tab1Controller.base1Tab1.getChildren().removeAll(bApply, bCancel);
            }
            
            if (cMain.idMatrixIncidenta.isSelected()) {
                cMain.tab2Controller.gCellMatrixIncidenta.removeMarkedElements();
                cMain.tab2Controller.gCellMatrixIncidenta.removeInvalidArcs();
                MethodsStatic.refresh(cMain.tab2Controller.gCellMatrixIncidenta, getA(1), getB(1));
                cMain.tab2Controller.gCellMatrixIncidenta.hideListButtonCell();
                cMain.tab2Controller.base2Tab1.getChildren().removeAll(bApply, bCancel);
            }
            
            if (cMain.idListaAdiacenta.isSelected()) {
                cMain.tab3Controller.gCellListaAdiacenta.removeMarkedElements();
                MethodsStatic.refresh(cMain.tab3Controller.gCellListaAdiacenta, getA(2), getB(2));
                cMain.tab3Controller.gCellListaAdiacenta.hideListButtonCell();
                cMain.tab3Controller.base3Tab1.getChildren().removeAll(bApply, bCancel);
            }
            
            statusExit = 1;
            idSaveAs.setDisable(false);
            idEdit.setDisable(false);
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public void onClickCancel() {
        if (cMain.idMatrixAdiacenta.isSelected()) {
            cMain.tab1Controller.gCellMatrixAdiacenta.unMarkElements();
        }
        
        if (cMain.idMatrixIncidenta.isSelected()) {
            cMain.tab2Controller.gCellMatrixIncidenta.unMarkElements();
        }
        
        if (cMain.idListaAdiacenta.isSelected()) {
            cMain.tab3Controller.gCellListaAdiacenta.unMarkElements();
        }
    }
    
    @FXML
    public void init(CMain cMain) {
        CMenu.cMain = cMain;
        CMenu.panes = new Pane[]{CMenu.cMain.tab1Controller.base1Tab2,
                                 CMenu.cMain.tab2Controller.base2Tab2,
                                 CMenu.cMain.tab3Controller.base3Tab2};
        CMenu.gridPanes = new GridPane[]{CMenu.cMain.tab1Controller.gCellMatrixAdiacenta,
                                         CMenu.cMain.tab2Controller.gCellMatrixIncidenta,
                                         CMenu.cMain.tab3Controller.gCellListaAdiacenta};
    }
    
}
