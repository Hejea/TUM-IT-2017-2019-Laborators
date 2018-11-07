package app.tabs;

import app.components.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class GridMatrixIncidenta extends GridPane implements Sync {
    
    public  int                    nEdge;
    public  int                    nArc;
    public  List<TextCell>         listTextCell;
    private List<Object>           listElementMarked;
    private List<LabelCell>        listLabelEdge;
    private List<LabelCell>        listLabelArc;
    private List<ButtonCellRemove> listRemoveButtonEdge;
    private List<ButtonCellRemove> listRemoveButtonArc;
    private ButtonCellAdd          cellAddButtonArc;
    private ButtonCellAdd          cellAddButtonEdge;
    
    public GridMatrixIncidenta() {
        /* Este ne Editabil la inceput */
        this.setMouseTransparent(true);
        
        this.nArc = 0;
        this.nEdge = 0;
        
        listElementMarked = new ArrayList<>();
        listTextCell = new ArrayList<>();
        listLabelEdge = new ArrayList<>();
        listLabelArc = new ArrayList<>();
        listRemoveButtonEdge = new ArrayList<>();
        listRemoveButtonArc = new ArrayList<>();
        cellAddButtonEdge = new ButtonCellAdd("add");
        cellAddButtonArc = new ButtonCellAdd("add");
        cellAddButtonEdge.setOnMouseClicked(event -> btnAddEdge());
        cellAddButtonArc.setOnMouseClicked(event -> btnAddArc());
    }
    
    private void clearGrid() {
        getChildren().removeAll(listTextCell);
        getChildren().removeAll(listRemoveButtonEdge);
        getChildren().removeAll(listRemoveButtonArc);
        getChildren().removeAll(listLabelEdge);
        getChildren().removeAll(listLabelArc);
        getChildren().remove(cellAddButtonEdge);
        getChildren().remove(cellAddButtonArc);
    }
    
    private void addElementToGrid() {
        int i, j, k = 0;
        
        for (j = 0; j < listRemoveButtonEdge.size(); j++) {
            this.add(listRemoveButtonEdge.get(j), j + 2, 0);
            this.add(listLabelEdge.get(j), j + 2, 1);
        }
        
        for (i = 0; i < listRemoveButtonArc.size(); i++) {
            this.add(listRemoveButtonArc.get(i), 0, i + 2);
            this.add(listLabelArc.get(i), 1, i + 2);
            
            for (j = 0; j < nEdge; j++) this.add(listTextCell.get(k++), j + 2, i + 2);
        }
        
        this.add(cellAddButtonEdge, nEdge + 2, 0);
        this.add(cellAddButtonArc, 0, nArc + 2);
    }
    
    public void createNewMatrix(String[] listStringCell, int nEdge, int nArc) {
        
        clearGrid();
        listElementMarked.clear();
        listTextCell.clear();
        listLabelEdge.clear();
        listLabelArc.clear();
        listRemoveButtonEdge.clear();
        listRemoveButtonArc.clear();
        
        this.nEdge = nEdge;
        this.nArc = nArc;
        
        int i, j;
        
        ButtonCellRemove tempButtonEdge;
        ButtonCellRemove tempButtonArc;
        LabelCell        tempLabel;
        TextCell         tempTextCell;
        
        
        // HORIZONTAL -> Edge
        for (i = 0; i < nEdge; i++) {
            // Create HORIZONTAL Remove Button List
            tempButtonEdge = new ButtonCellRemove("");
            tempButtonEdge.setOnMouseClicked(this::btnRemoveEdge);
            listRemoveButtonEdge.add(i, tempButtonEdge);
            
            // Create HORIZONTAL Remove Label List
            tempLabel = new LabelCell("X" + (i + 1));
            listLabelEdge.add(i, tempLabel);
            add(tempLabel, i + 2, 1);
        }
        
        
        // VERTICAL -> Arc
        for (i = 0; i < nArc; i++) {
            // Create VERTICAL Remove Button List
            tempButtonArc = new ButtonCellRemove("");
            tempButtonArc.setOnMouseClicked(this::btnRemoveArc);
            listRemoveButtonArc.add(i, tempButtonArc);
            
            // Create VERTICAL Remove Label List
            tempLabel = new LabelCell("U" + (i + 1));
            listLabelArc.add(i, tempLabel);
            add(tempLabel, 1, i + 2);
            
            // Create Rows Cell List (matrix)
            for (j = 0; j < nEdge; j++) {
                tempTextCell = new TextCell(listStringCell[j + i * nEdge]);
                listTextCell.add(j + i * nEdge, tempTextCell);
                add(tempTextCell, j + 2, i + 2);
            }
        }
    }
    
    private void btnAddEdge() {
        addEdge();
    }
    
    private void btnAddArc() {
        addArc();
    }
    
    private void btnRemoveEdge(MouseEvent event) {
        if (event.getSource() instanceof ButtonCellRemove) {
            int i = this.listRemoveButtonEdge.indexOf(event.getSource());
            try {
                if (listLabelEdge.get(i).isDisable()) listElementMarked.removeAll(unMarkEdge(i));
                else listElementMarked.addAll(addMarkEdge(i));
            } catch (Exception ignored) {}
        }
    }
    
    private void btnRemoveArc(MouseEvent event) {
        if (event.getSource() instanceof ButtonCellRemove) {
            int i = listRemoveButtonArc.indexOf(event.getSource());
            try {
                if (listLabelArc.get(i).isDisable()) listElementMarked.removeAll(unMarkArc(i));
                else listElementMarked.addAll(addMarkArc(i));
            } catch (Exception ignored) {}
        }
    }
    
    private void addEdge() {
        clearGrid();
        ButtonCellRemove tempButtonEdge = new ButtonCellRemove("");
        tempButtonEdge.setOnMouseClicked(this::btnRemoveEdge);
        
        listRemoveButtonEdge.add(tempButtonEdge);
        listLabelEdge.add(new LabelCell("X" + (listLabelEdge.size() + 1)));
        
        int i, n;
        
        for (i = 0; i < this.nArc; i++) {
            n = nEdge + i * nEdge + i;
            this.listTextCell.add(n, new TextCell(String.valueOf(0)));
        }
        this.nEdge++;
        
        addElementToGrid();
    }
    
    private void addArc() {
        if (nEdge > 0) {
            clearGrid();
            
            ButtonCellRemove tempButtonArc = new ButtonCellRemove("");
            tempButtonArc.setOnMouseClicked(this::btnRemoveArc);
            
            listRemoveButtonArc.add(tempButtonArc);
            listLabelArc.add(new LabelCell("U" + (listLabelArc.size() + 1)));
            
            int i, n;
            
            n = this.nArc * this.nEdge;
            this.nArc++;
            
            for (i = 0; i < this.nEdge; i++) {
                this.listTextCell.add(n++, new TextCell(String.valueOf(0)));
            }
            
            addElementToGrid();
        }
    }
    
    private List<Object> addMarkEdge(int nEdge) {
        
        List<Object> elements = new ArrayList<>();
        
        if (this.nEdge > nEdge) {
            
            int           i, n;
            List<Integer> nArcToRemove = new ArrayList<>();
            
            listLabelEdge.get(nEdge).setDisable(true);
            
            elements.add(listRemoveButtonEdge.get(nEdge));
            elements.add(listLabelEdge.get(nEdge));
            
            for (i = 0; i < this.nArc; i++) {
                n = nEdge + i * (this.nEdge - 1) + i;
                
                if (listTextCell.get(n).getText().equals("-1") ||
                    listTextCell.get(n).getText().equals("1") ||
                    listTextCell.get(n).getText().equals("2")) {
                    
                    nArcToRemove.add(i);
                } else {
                    listTextCell.get(n).setDisable(true);
                    elements.add(listTextCell.get(n));
                }
            }
            
            for (int k : nArcToRemove) {
                elements.addAll(addMarkArc(k));
                listRemoveButtonArc.get(k).setDisable(true);
            }
        }
        
        return elements;
    }
    
    private List<Object> addMarkArc(int nArc) {
        
        List<Object> elements = new ArrayList<>();
        
        if (this.nArc > nArc) {
            int i, n;
            
            listLabelArc.get(nArc).setDisable(true);
            
            elements.add(listRemoveButtonArc.get(nArc));
            elements.add(listLabelArc.get(nArc));
            
            n = this.nEdge * nArc;
            for (i = 0; i < this.nEdge; i++) {
                listTextCell.get(n + i).setDisable(true);
                elements.add(listTextCell.get(n + i));
            }
        }
        
        return elements;
    }
    
    private List<Object> unMarkEdge(int nEdge) {
        
        List<Object> elements = new ArrayList<>();
        
        if (this.nEdge > nEdge) {
            
            int           i, n;
            List<Integer> nArcToEnable = new ArrayList<>();
            
            listLabelEdge.get(nEdge).setDisable(false);
            
            elements.add(listRemoveButtonEdge.get(nEdge));
            elements.add(listLabelEdge.get(nEdge));
            
            for (i = 0; i < this.nArc; i++) {
                n = nEdge + i * (this.nEdge - 1) + i;
                if (hasLink(n, i * (this.nEdge - 1) + i)) nArcToEnable.add(i);
            }
            
            for (int k : nArcToEnable) {
                elements.addAll(unMarkArc(k));
                listRemoveButtonArc.get(k).setDisable(false);
            }
        }
        
        return elements;
    }
    
    private List<Object> unMarkArc(int nArc) {
        
        List<Object> elements = new ArrayList<>();
        
        if (this.nArc > nArc) {
            int i, n;
            
            n = this.nEdge * nArc;
            for (i = 0; i < this.nEdge; i++) {
                if (!listLabelEdge.get(i).isDisable()) {
                    listTextCell.get(n + i).setDisable(false);
                    elements.add(listTextCell.get(n + i));
                }
            }
            
            listLabelArc.get(nArc).setDisable(false);
            
            elements.add(listRemoveButtonArc.get(nArc));
            elements.add(listLabelArc.get(nArc));
        }
        
        return elements;
    }
    
    public void unMarkElements() {
        int i;
        
        for (i = 0; i < listTextCell.size(); i++) {
            if (listElementMarked.contains(listTextCell.get(i))) {
                listTextCell.get(i).setDisable(false);
            }
        }
        
        for (i = 0; i < listLabelEdge.size(); i++) {
            if (listElementMarked.contains(listLabelEdge.get(i))) {
                listLabelEdge.get(i).setDisable(false);
            }
        }
        
        for (i = 0; i < listLabelArc.size(); i++) {
            if (listElementMarked.contains(listLabelArc.get(i))) {
                listLabelArc.get(i).setDisable(false);
            }
        }
        
        for (i = 0; i < listRemoveButtonArc.size(); i++) {
            if (listElementMarked.contains(listRemoveButtonArc.get(i))) {
                listRemoveButtonArc.get(i).setDisable(false);
            }
        }
        
        listElementMarked.clear();
    }
    
    public void removeInvalidArcs() {
        if (!listLabelArc.isEmpty()) {
            
            if (listLabelEdge.isEmpty()) {
                for (int i = 0; i < listLabelArc.size(); i++) {
                    try {
                        listElementMarked.addAll(addMarkArc(i));
                    } catch (Exception ignored) {}
                }
            } else {
                
                int     i, j, n, k, n1;
                boolean a, b, c;
                
                for (i = 0; i < nArc; i++) {
                    a = b = c = true;
                    for (j = 0; j < nEdge; j++) {
                        n = i * nEdge + j;
                        if (listTextCell.get(n).getText().equals("1")) a = false;
                        else if (listTextCell.get(n).getText().equals("-1")) b = false;
                        else if (listTextCell.get(n).getText().equals("2")) c = false;
                    }
                    if (!((!a && !b && c) || (a && b && !c))) try {
                        listElementMarked.addAll(addMarkArc(i));
                    } catch (Exception ignored) {}
                    
                    for (k = i + 1; k < nArc; k++) {
                        a = true;
                        if (!listLabelArc.get(k).isDisable())
                            for (j = 0; j < nEdge; j++) {
                                n1 = k * nEdge + j;
                                n = i * nEdge + j;
                                if (!listTextCell.get(n).getText().equals(listTextCell.get(n1).getText())) a = false;
                            }
                        if (a) try {
                            listElementMarked.addAll(addMarkArc(k));
                        } catch (Exception ignored) {}
                    }
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
            
            for (i = 0; i < listLabelEdge.size(); i++) {
                if (listElementMarked.contains(listLabelEdge.get(i))) {
                    listLabelEdge.remove(listLabelEdge.get(i));
                    this.nEdge--;
                    i--;
                }
            }
            
            
            for (i = 0; i < listLabelEdge.size(); i++) {
                listLabelEdge.get(i).setText("X" + (i + 1));
            }
            
            
            for (i = 0; i < listLabelArc.size(); i++) {
                if (listElementMarked.contains(listLabelArc.get(i))) {
                    listLabelArc.remove(listLabelArc.get(i));
                    this.nArc--;
                    i--;
                }
            }
            
            for (i = 0; i < listLabelArc.size(); i++) {
                listLabelArc.get(i).setText("U" + (i + 1));
            }
            
            for (i = 0; i < listRemoveButtonEdge.size(); i++) {
                if (listElementMarked.contains(listRemoveButtonEdge.get(i))) {
                    listRemoveButtonEdge.remove(listRemoveButtonEdge.get(i));
                    i--;
                }
            }
            
            for (i = 0; i < listRemoveButtonArc.size(); i++) {
                if (listElementMarked.contains(listRemoveButtonArc.get(i))) {
                    listRemoveButtonArc.remove(listRemoveButtonArc.get(i));
                    i--;
                }
            }
            
            listElementMarked.clear();
            
            addElementToGrid();
        }
    }
    
    public void hideListButtonCell() {
        setMouseTransparent(true);
        this.getChildren().removeAll(listRemoveButtonEdge);
        this.getChildren().removeAll(listRemoveButtonArc);
        this.getChildren().remove(cellAddButtonEdge);
        this.getChildren().remove(cellAddButtonArc);
    }
    
    public void showListButtonCell() {
        setMouseTransparent(false);
        int i;
        
        for (i = 0; i < this.nEdge; i++) this.add(listRemoveButtonEdge.get(i), i + 2, 0);
        for (i = 0; i < this.nArc; i++) this.add(listRemoveButtonArc.get(i), 0, i + 2);
        
        this.add(cellAddButtonEdge, this.nEdge + 2, 0);
        this.add(cellAddButtonArc, 0, this.nArc + 2);
    }
    
    private void synchronize(GridMatrixAdiacenta matrixAdiacenta) {
        
        int i, j, n, i1 = -1, j1 = -1;
        n = nArc * nEdge;
        String[][] strings  = new String[nEdge][nEdge];
        String[]   strings1 = new String[nEdge * nEdge];
        
        for (i = 0; i < n; i += nEdge) {
            for (j = 0; j < nEdge; j++)
                if (listTextCell.get(i + j).getText().equals("-1")) {
                    i1 = j;
                    for (j++; j < nEdge; j++) if (listTextCell.get(i + j).getText().equals("1")) j1 = j;
                } else if (listTextCell.get(i + j).getText().equals("1")) {
                    j1 = j;
                    for (j++; j < nEdge; j++) if (listTextCell.get(i + j).getText().equals("-1")) i1 = j;
                } else if (listTextCell.get(i + j).getText().equals("2")) i1 = j1 = j;
            strings[i1][j1] = "1";
        }
        
        for (i = 0, i1 = 0; i < nEdge; i++) {
            for (j = 0; j < nEdge; j++) {
                if (strings[i][j] == null) strings1[i1++] = "0";
                else strings1[i1++] = strings[i][j];
            }
        }
        
        matrixAdiacenta.createNewMatrix(strings1, nEdge);
    }
    
    private void synchronize(GridListaAdiacenta lAdiacenta) {
        int i, j, n, i1 = -1, j1 = -1;
        n = nArc * nEdge;
        String[][] strings  = new String[nEdge][nEdge];
        String[]   strings1 = new String[nEdge * nEdge];
        
        for (i = 0; i < n; i += nEdge) {
            for (j = 0; j < nEdge; j++) {
                if (listTextCell.get(i + j).getText().equals("-1")) {
                    i1 = j;
                    for (j++; j < nEdge; j++) if (listTextCell.get(i + j).getText().equals("1")) j1 = j;
                } else if (listTextCell.get(i + j).getText().equals("1")) {
                    j1 = j;
                    for (j++; j < nEdge; j++) if (listTextCell.get(i + j).getText().equals("-1")) i1 = j;
                } else if (listTextCell.get(i + j).getText().equals("2")) {
                    i1 = j1 = j;
                    break;
                }
            }
            strings[i1][j1] = String.valueOf(j1 + 1);
        }
        
        for (i = 0, i1 = 0; i < nEdge; i++) {
            for (j = 0; j < nEdge; j++) {
                if (strings[i][j] != null) strings1[i1++] = strings[i][j];
            }
            strings1[i1++] = "0";
        }
        
        lAdiacenta.createNewList(strings1, nEdge);
        
    }
    
    private boolean hasLink(int nPosEdge, int nPosArc) {
        
        // false - its links are marked
        // true  - are free
        
        if (listTextCell.get(nPosEdge).getText().equals("2")) return true;
        
        String s;
        int    i;
        
        for (i = nPosArc; i < nPosArc + this.nEdge; i++) {
            if (i != nPosEdge) {
                s = listTextCell.get(i).getText();
                if ((s.equals("1") || s.equals("-1") || s.equals("2")) &&
                    listLabelEdge.get(i - nPosArc).isDisable()) return false;
            }
        }
        
        return true;
    }
    
    @Override
    public void synchronize(GridPane pane) {
        if (pane instanceof GridMatrixAdiacenta)
            synchronize((GridMatrixAdiacenta) pane);
        if (pane instanceof GridListaAdiacenta)
            synchronize((GridListaAdiacenta) pane);
    }
}











