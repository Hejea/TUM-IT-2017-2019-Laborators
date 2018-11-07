package app.tabs;

import app.components.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class GridMatrixAdiacenta extends GridPane implements Sync {
    
    public  int                    nEdge;
    public  List<TextCell>         listTextCell;
    private List<Object>           listElementMarked;
    private List<LabelCell>        listLabelH;
    private List<ButtonCellRemove> listRemoveButton;
    private List<LabelCell>        listLabelV;
    private ButtonCellAdd          cellAddButton;
    
    public GridMatrixAdiacenta() {
        /* Este ne Editabil la inceput */
        this.setMouseTransparent(true);
        
        this.nEdge = 0;
        this.nEdge = 0;
        
        listElementMarked = new ArrayList<>();
        listTextCell = new ArrayList<>();
        listLabelH = new ArrayList<>();
        listLabelV = new ArrayList<>();
        listRemoveButton = new ArrayList<>();
        cellAddButton = new ButtonCellAdd("add");
        cellAddButton.setOnMouseClicked(event -> btnAdd());
        
    }
    
    public void createNewMatrix(String[] listStringCell, int nEdge) {
        
        clearGrid();
        listElementMarked.clear();
        listTextCell.clear();
        listLabelH.clear();
        listLabelV.clear();
        listRemoveButton.clear();
        
        this.nEdge = nEdge;
        
        int              i, j;
        ButtonCellRemove tempRemoveButtonCell;
        LabelCell        tempLabel;
        TextCell         tempTextCell;
        
        for (i = 0; i < nEdge; i++) {
            tempRemoveButtonCell = new ButtonCellRemove("");
            tempRemoveButtonCell.setOnMouseClicked(this::btnRemove);
            listRemoveButton.add(tempRemoveButtonCell);
            
            tempLabel = new LabelCell("X" + (i + 1));
            listLabelH.add(tempLabel);
            add(tempLabel, i + 1, 1);
        }
        
        for (i = 0; i < nEdge; i++) {
            tempLabel = new LabelCell("X" + (i + 1));
            listLabelV.add(tempLabel);
            add(tempLabel, 0, i + 2);
            
            for (j = 0; j < nEdge; j++) {
                tempTextCell = new TextCell(listStringCell[j + i * nEdge]);
                listTextCell.add(tempTextCell);
                add(tempTextCell, j + 1, i + 2);
            }
        }
    }
    
    private void btnAdd() {
        addEdge();
    }
    
    private void btnRemove(MouseEvent event) {
        if (event.getSource() instanceof ButtonCellRemove) {
            int i = this.listRemoveButton.indexOf(event.getSource());
            try {
                if (listLabelH.get(i).isDisable()) listElementMarked.removeAll(unMarkEdge(i));
                else listElementMarked.addAll(addMarkEdge(i));
            } catch (Exception ignored) {}
        }
    }
    
    private void clearGrid() {
        getChildren().removeAll(listTextCell);
        getChildren().removeAll(listRemoveButton);
        getChildren().remove(cellAddButton);
        getChildren().removeAll(listLabelH);
        getChildren().removeAll(listLabelV);
    }
    
    private void addElementToGrid() {
        int i, j, k = 0;
        for (i = 0; i < nEdge; i++) {
            add(listRemoveButton.get(i), i + 1, 0);
            add(listLabelH.get(i), i + 1, 1);
            add(listLabelV.get(i), 0, i + 2);
            for (j = 0; j < nEdge; j++) add(listTextCell.get(k++), j + 1, i + 2);
        }
        add(cellAddButton, nEdge + 1, 0);
    }
    
    public List<Object> unMarkEdge(int edge) {
        
        List<Object> elements = new ArrayList<>();
        
        if (this.nEdge > edge) {
            
            int i, j, n;
            
            listLabelH.get(edge).setDisable(false);
            listLabelV.get(edge).setDisable(false);
            
            elements.add(listRemoveButton.get(edge));
            elements.add(listLabelH.get(edge));
            elements.add(listLabelV.get(edge));
            
            n = nEdge * nEdge;
            for (i = 0, j = edge; j < n; j += nEdge, i++)
                if (!listLabelH.get(i).isDisable()) {
                    elements.add(listTextCell.get(j));
                    listTextCell.get(j).setDisable(false);
                }
            
            n = edge * nEdge;
            for (i = 0; i < nEdge; i++)
                if (i != edge)
                    if (!listLabelH.get(i).isDisable()) {
                        elements.add(listTextCell.get(n + i));
                        listTextCell.get(n + i).setDisable(false);
                    }
        }
        
        return elements;
    }
    
    public List<Object> addMarkEdge(int edge) {
        
        List<Object> elements = new ArrayList<>();
        
        if (this.nEdge > edge) {
            
            int i, j, n;
            
            elements.add(listRemoveButton.get(edge));
            elements.add(listLabelH.get(edge));
            elements.add(listLabelV.get(edge));
            
            n = nEdge * nEdge;
            for (i = 0, j = edge; j < n; j += nEdge, i++)
                if (!listLabelH.get(i).isDisable()) {
                    elements.add(listTextCell.get(j));
                    listTextCell.get(j).setDisable(true);
                }
            
            listLabelH.get(edge).setDisable(true);
            listLabelV.get(edge).setDisable(true);
            
            n = edge * nEdge;
            for (i = 0; i < nEdge; i++)
                if (!listLabelH.get(i).isDisable()) {
                    elements.add(listTextCell.get(n + i));
                    listTextCell.get(n + i).setDisable(true);
                }
        }
        
        return elements;
    }
    
    public void removeInvalidEdge() {
        if (!listLabelH.isEmpty()) {
            
            int i, j, n;
            
            for (i = 0; i < nEdge; i++) {
                for (j = 0; j < nEdge; j++) {
                    n = i * nEdge + j;
                    if (!MethodsStatic.validCell(listTextCell.get(n).getText())) try {
                        listElementMarked.addAll(addMarkEdge(i));
                    } catch (Exception ignored) {}
                }
            }
            removeMarkedElements();
        }
    }
    
    public void removeMarkedElements() {
        if (!listElementMarked.isEmpty()) {
            clearGrid();
            int i;
            
            for (i = 0; i < listTextCell.size(); i++) {
                if (listElementMarked.contains(listTextCell.get(i))) {
                    listTextCell.remove(listTextCell.get(i));
                    i--;
                }
            }
            
            for (i = 0; i < listLabelH.size(); i++) {
                if (listElementMarked.contains(listLabelH.get(i))) {
                    listLabelH.remove(listLabelH.get(i));
                    listLabelV.remove(listLabelV.get(i));
                    this.nEdge--;
                    i--;
                }
            }
            
            for (i = 0; i < listLabelH.size(); i++) {
                listLabelH.get(i).setText("X" + (i + 1));
                listLabelV.get(i).setText("X" + (i + 1));
            }
            
            for (i = 0; i < listRemoveButton.size(); i++) {
                if (listElementMarked.contains(listRemoveButton.get(i))) {
                    listRemoveButton.remove(listRemoveButton.get(i));
                    i--;
                }
            }
            
            listElementMarked.clear();
            
            addElementToGrid();
        }
    }
    
    public void unMarkElements() {
        int i;
        
        for (i = 0; i < listTextCell.size(); i++) {
            if (listElementMarked.contains(listTextCell.get(i))) {
                listTextCell.get(i).setDisable(false);
            }
        }
        
        for (i = 0; i < listLabelH.size(); i++) {
            if (listElementMarked.contains(listLabelH.get(i))) {
                listLabelH.get(i).setDisable(false);
            }
        }
        
        for (i = 0; i < listLabelV.size(); i++) {
            if (listElementMarked.contains(listLabelV.get(i))) {
                listLabelV.get(i).setDisable(false);
            }
        }
/*
        for (i = 0; i < listRemoveButton.size(); i++) {
            if (listElementMarked.contains(listRemoveButton.get(i))) {
                listRemoveButton.get(i).setDisable(false);
            }
        }
 */
        listElementMarked.clear();
    }
    
    private void addEdge() {
        
        clearGrid();
        ButtonCellRemove tempRemoveButtonCell = new ButtonCellRemove("");
        tempRemoveButtonCell.setOnMouseClicked(this::btnRemove);
        
        listRemoveButton.add(tempRemoveButtonCell);
        listLabelH.add(new LabelCell("X" + (listLabelH.size() + 1)));
        listLabelV.add(new LabelCell("X" + (listLabelV.size() + 1)));
        
        int i, n;
        
        for (i = 0; i < this.nEdge; i++) {
            n = nEdge + (i * nEdge) + i;
            this.listTextCell.add(n, new TextCell(String.valueOf(0)));
        }
        nEdge++;
        
        n = nEdge * (nEdge - 1);
        
        for (i = 0; i < this.nEdge; i++) {
            this.listTextCell.add(n, new TextCell(String.valueOf(0)));
            n++;
        }
        
        addElementToGrid();
    }
    
    public void hideListButtonCell() {
        setMouseTransparent(true);
        this.getChildren().removeAll(listRemoveButton);
        this.getChildren().remove(cellAddButton);
    }
    
    public void showListButtonCell() {
        setMouseTransparent(false);
        for (int i = 1; i < nEdge + 1; i++) this.add(listRemoveButton.get(i - 1), i, 0);
        this.add(cellAddButton, nEdge + 1, 0);
    }
    
    private void synchronize(GridMatrixIncidenta matrixIncidenta) {
        int i, j, nArc = 0, n = 0, i1;
        
        List<String> list = new ArrayList<>();
        
        for (TextCell cell : listTextCell) {
            if (cell.getText().equals("1")) {
                i = n / nEdge;
                j = n % nEdge;
                
                for (i1 = 0; i1 < nEdge; i1++) {
                    if (i == i1 && i1 != j) {
                        list.add("-1");
                    } else if (i1 != i && j == i1) {
                        list.add("1");
                    } else if (i1 == j) {
                        list.add("2");
                    } else list.add("0");
                }
                nArc++;
            }
            n++;
        }
        matrixIncidenta.createNewMatrix(list.toArray(new String[0]), nEdge, nArc);
    }
    
    private void synchronize(GridListaAdiacenta lAdiacenta) {
        int i, j, n;
        
        List<String> strings = new ArrayList<>();
        
        n = 0;
        for (i = 0; i < nEdge; i++) {
            for (j = 0; j < nEdge; j++, n++)
                if (listTextCell.get(n).getText().equals("1")) strings.add(String.valueOf(j + 1));
            strings.add("0");
        }
        
        lAdiacenta.createNewList(strings.toArray(new String[0]), nEdge);
    }
    
    @Override
    public void synchronize(GridPane pane) {
        if (pane instanceof GridMatrixIncidenta) synchronize((GridMatrixIncidenta) pane);
        if (pane instanceof GridListaAdiacenta) synchronize((GridListaAdiacenta) pane);
    }
}









