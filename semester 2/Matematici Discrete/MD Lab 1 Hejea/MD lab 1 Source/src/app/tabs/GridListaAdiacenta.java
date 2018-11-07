package app.tabs;

import app.components.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class GridListaAdiacenta extends GridPane implements Sync {
    
    private int                    nEdge;
    private List<Object>           listElementMarked;
    public  List<List<TextCellLA>> listTextCell;
    public  List<LabelCell>        listLabelEdge;
    private List<ButtonCellRemove> listRemoveButtonEdge;
    private List<ButtonCellAdd>    listAddButtonArc;
    private ButtonCellAdd          cellAddButtonEdge;
    private Label                  emptyCell1;
    private Label                  emptyCell2;
    private ComboBox<String>       newAEdge;
    private Label                  titleAEdge;
    
    public GridListaAdiacenta() {
        /* Este ne Editabil la inceput */
        this.setMouseTransparent(true);
        
        this.nEdge = 0;
        
        listElementMarked = new ArrayList<>();
        listTextCell = new ArrayList<>();
        listLabelEdge = new ArrayList<>();
        listRemoveButtonEdge = new ArrayList<>();
        listAddButtonArc = new ArrayList<>();
        cellAddButtonEdge = new ButtonCellAdd("add");
        emptyCell1 = new Label("");
        emptyCell2 = new Label("");
        titleAEdge = new Label("Virf Adiacent");
        newAEdge = new ComboBox<>();
        newAEdge.getStylesheets().add("style/combo.css");
        emptyCell1.getStyleClass().add("rowEmpty");
        emptyCell2.getStyleClass().add("rowEmpty");
        titleAEdge.getStyleClass().add("title-aedge");
        
        cellAddButtonEdge.setOnMouseClicked(event -> btnAddEdge());
    }
    
    private void btnAddEdge() {
        addEdge();
    }
    
    private void btnAddArc(MouseEvent event) {
        if (event.getSource() instanceof ButtonCellAdd) {
            int i = listAddButtonArc.indexOf(event.getSource());
            if (listLabelEdge.get(i).getState()) try {
                addArc(i, Integer.valueOf(newAEdge.getValue().substring(1)));
            } catch (Exception ignored) {}
        }
    }
    
    private void btnRemoveEdge(MouseEvent event) {
        if (event.getSource() instanceof ButtonCellRemove) {
            int i = listRemoveButtonEdge.indexOf(event.getSource());
            try {
                if (listLabelEdge.get(i).getState()) listElementMarked.addAll(addMarkEdge(i));
                else listElementMarked.removeAll(unMarkEdge(i));
            } catch (Exception ignored) {}
        }
    }
    
    private void clearGrid() {
        for (int i = 0; i < nEdge; i++) getChildren().removeAll(listTextCell.get(i));
        getChildren().removeAll(listLabelEdge);
        getChildren().removeAll(listRemoveButtonEdge);
        getChildren().removeAll(listAddButtonArc);
        getChildren().remove(cellAddButtonEdge);
    }
    
    private void addElementToGrid() {
        int i, j, k;
        
        for (j = 0; j < listRemoveButtonEdge.size(); j++) {
            this.add(listRemoveButtonEdge.get(j), 0, j + 2);
            this.add(listLabelEdge.get(j), 1, j + 2);
            this.add(listAddButtonArc.get(j), listTextCell.get(j).size() + 2, j + 2);
        }
        
        for (i = 0, k = 0; i < nEdge; i++)
            for (j = 0; j < listTextCell.get(i).size(); j++, k++) add(listTextCell.get(i).get(j), j + 2, i + 2);
        this.add(cellAddButtonEdge, 1, this.nEdge + 2);
        
    }
    
    public void createNewList(String[] listStringCell, int nEdge) {
        
        clearGrid();
        listElementMarked.clear();
        listTextCell.clear();
        listLabelEdge.clear();
        listRemoveButtonEdge.clear();
        newAEdge.getItems().clear();
        
        this.nEdge = nEdge;
        
        int i, j, k = 0;
        
        ButtonCellRemove tempRemoveEdge;
        ButtonCellAdd    tempAddEdgeA;
        LabelCell        tempLabel;
        TextCellLA       tempTextCell;
        
        
        String[] strings = new String[nEdge + 1];
        strings[0] = "-";
        
        // VERTICAL -> Edge
        for (i = 0; i < nEdge; i++) {
            tempRemoveEdge = new ButtonCellRemove("");
            tempRemoveEdge.setOnMouseClicked(this::btnRemoveEdge);
            listRemoveButtonEdge.add(tempRemoveEdge);
            
            tempAddEdgeA = new ButtonCellAdd("<-");
            tempAddEdgeA.setOnMouseClicked(this::btnAddArc);
            listAddButtonArc.add(tempAddEdgeA);
            
            tempLabel = new LabelCell((strings[i + 1] = "X" + (i + 1)));
            listLabelEdge.add(i, tempLabel);
            add(tempLabel, 1, i + 2);
            
            j = 0;
            listTextCell.add(new ArrayList<>());
            while (!listStringCell[k].equals("0")) {
                tempTextCell = new TextCellLA(listStringCell[k]);
                listTextCell.get(i).add(j, tempTextCell);
                add(tempTextCell, j + 2, i + 2);
                j++;
                k++;
            }
            k++;
        }
        
        newAEdge.setItems(FXCollections.observableArrayList(strings));
        newAEdge.getSelectionModel().selectFirst();
    }
    
    private void addEdge() {
        clearGrid();
        ButtonCellAdd tempAddEdgeA = new ButtonCellAdd("<-");
        tempAddEdgeA.setOnMouseClicked(this::btnAddArc);
    
        ButtonCellRemove tempRemoveEdge = new ButtonCellRemove("");
        tempRemoveEdge.setOnMouseClicked(this::btnRemoveEdge);
        
        listRemoveButtonEdge.add(tempRemoveEdge);
        listAddButtonArc.add(tempAddEdgeA);
        listLabelEdge.add(new LabelCell("X" + (listLabelEdge.size() + 1)));
        listTextCell.add(new ArrayList<>());
        
        this.nEdge++;
        
        newAEdge.getItems().add("X" + nEdge);
        
        addElementToGrid();
    }
    
    private void addArc(int nEdge, int nAiac) {
        clearGrid();
        int              i, j;
        List<TextCellLA> get = listTextCell.get(nEdge);
        
        for (i = 0; i < get.size(); i++) {
            TextCellLA tc = get.get(i);
            j = Integer.valueOf(tc.getText());
            if (j == nAiac) break;
            if (j > nAiac) {
                get.add(i, new TextCellLA(String.valueOf(nAiac)));
                break;
            }
        }
        if (get.size() == i) get.add(new TextCellLA(String.valueOf(nAiac)));
        
        addElementToGrid();
    }
    
    private List<Object> addMarkEdge(int nEdge) {
        
        List<Object> elements = new ArrayList<>();
        TextCellLA   textLA;
        
        int i, j;
        
        listLabelEdge.get(nEdge).setState(false);
        
        elements.add(listRemoveButtonEdge.get(nEdge));
        elements.add(listLabelEdge.get(nEdge));
        listAddButtonArc.get(nEdge).setDisable(true);
        
        ObservableList<String> items = newAEdge.getItems();
        for (i = 1; i < items.size(); i++) {
            if (Integer.valueOf(items.get(i).substring(1)) == nEdge + 1) {
                items.remove(i);
                break;
            }
        }
        newAEdge.getSelectionModel().selectFirst();
        
        for (i = 0; i < this.nEdge; i++) {
            if (listLabelEdge.get(i).getState())
                for (j = 0; j < listTextCell.get(i).size(); j++) {
                    textLA = listTextCell.get(i).get(j);
                    if (textLA.getText().equals(String.valueOf(nEdge + 1))) {
                        textLA.setStateWithEnable(false);
                        elements.add(textLA);
                    }
                }
        }
        
        for (i = 0; i < listTextCell.get(nEdge).size(); i++)
            if ((textLA = listTextCell.get(nEdge).get(i)).getState()) {
                textLA.setStateWithEnable(false);
                elements.add(textLA);
            }
        
        return elements;
    }
    
    private List<Object> unMarkEdge(int nEdge) {
        
        List<Object> elements = new ArrayList<>();
        TextCellLA   textLA;
        
        int i, j, k;
        
        listLabelEdge.get(nEdge).setState(true);
        
        elements.add(listRemoveButtonEdge.get(nEdge));
        elements.add(listLabelEdge.get(nEdge));
        listAddButtonArc.get(nEdge).setDisable(false);
        
        ObservableList<String> items = newAEdge.getItems();
        
        for (i = 1; i < items.size(); i++)
            if (Integer.valueOf(items.get(i).substring(1)) > nEdge + 1) {
                items.add(i, "X" + (nEdge + 1));
                break;
            }
        
        if (items.size() == i) items.add(items.size(), "X" + (nEdge + 1));
        newAEdge.getSelectionModel().selectFirst();
        
        for (i = 0; i < this.nEdge; i++)
            if (listLabelEdge.get(i).getState())
                for (j = 0; j < listTextCell.get(i).size(); j++) {
                    textLA = listTextCell.get(i).get(j);
                    k = Integer.valueOf(textLA.getText()) - 1;
                    if (listLabelEdge.get(k).getState())
                        if (textLA.getText().equals(String.valueOf(nEdge + 1))) {
                            textLA.setStateWithEnable(true);
                            elements.add(textLA);
                        }
                }
        
        for (i = 0; i < listTextCell.get(nEdge).size(); i++) {
            textLA = listTextCell.get(nEdge).get(i);
            k = Integer.valueOf(textLA.getText()) + 1;
            if (!textLA.getState() &&
                ((LabelCell) MethodsStatic.getNode(k, 1, this)).getState()) {
                
                textLA.setStateWithEnable(true);
                elements.add(textLA);
            }
        }
        
        return elements;
    }
    
    public void unMarkElements() {
        int i, j;
        
        for (i = 0; i < listTextCell.size(); i++)
            for (j = 0; j < listTextCell.get(i).size(); j++)
                if (!listTextCell.get(i).get(j).getState()) listTextCell.get(i).get(j).setStateWithEnable(true);
        
        for (i = 0; i < listLabelEdge.size(); i++)
            if (!listLabelEdge.get(i).getState()) {
                listAddButtonArc.get(i).setDisable(false);
                listLabelEdge.get(i).setState(true);
            }
        
        listElementMarked.clear();
    }
    
    public void removeMarkedElements() {
        clearGrid();
        newAEdge.getItems().clear();
        
        int i, j, k, n;
        
        for (i = 0; i < listTextCell.size(); i++)
            if (listElementMarked.contains(listTextCell.get(i))) {
                listTextCell.remove(listTextCell.get(i));
                i--;
            } else for (j = 0; j < listTextCell.get(i).size(); j++) {
                if (!listTextCell.get(i).get(j).getState()) {
                    listTextCell.get(i).remove(j);
                    j--;
                }
            }
        
        for (i = 0, k = 0; i < listTextCell.size(); i++)
            if (!listLabelEdge.get(i).getState()) k++;
            else for (j = 0; j < listTextCell.get(i).size(); j++) {
                n = Integer.valueOf(listTextCell.get(i).get(j).getText());
                n -= k;
                listTextCell.get(i).get(j).setText(String.valueOf(n));
            }
        
        for (i = 0; i < listLabelEdge.size(); i++)
            if (listElementMarked.contains(listLabelEdge.get(i))) {
                listAddButtonArc.remove(i);
                listLabelEdge.remove(listLabelEdge.get(i));
                listTextCell.remove(i);
                i--;
            }
        this.nEdge = listLabelEdge.size();
        
        String   temp;
        String[] strings = new String[listLabelEdge.size() + 1];
        strings[0] = "-";
        for (i = 0; i < listLabelEdge.size(); i++) {
            listLabelEdge.get(i).setText((temp = "X" + (i + 1)));
            strings[i + 1] = temp;
        }
        newAEdge.getItems().addAll(strings);
        newAEdge.getSelectionModel().selectFirst();
        
        for (i = 0; i < listRemoveButtonEdge.size(); i++)
            if (listElementMarked.contains(listRemoveButtonEdge.get(i))) {
                listRemoveButtonEdge.remove(listRemoveButtonEdge.get(i));
                i--;
            }
        
        listElementMarked.clear();
        
        addElementToGrid();
        
    }
    
    public void hideListButtonCell() {
        setMouseTransparent(true);
        this.getChildren().removeAll(listRemoveButtonEdge);
        this.getChildren().removeAll(listAddButtonArc);
        this.getChildren().remove(cellAddButtonEdge);
        this.getChildren().remove(emptyCell1);
        this.getChildren().remove(emptyCell2);
        this.getChildren().remove(titleAEdge);
        this.getChildren().remove(newAEdge);
    }
    
    public void showListButtonCell() {
        setMouseTransparent(false);
        int i;
        
        for (i = 0; i < this.nEdge; i++) {
            this.add(listRemoveButtonEdge.get(i), 0, i + 2);
            this.add(listAddButtonArc.get(i), listTextCell.get(i).size() + 2, i + 2);
        }
        this.add(titleAEdge, 0, 0, 3, 1);
        this.add(newAEdge, 3, 0, 2, 1);
        this.add(emptyCell1, 2, 1);
        this.add(emptyCell2, 0, 1);
        this.add(cellAddButtonEdge, 1, this.nEdge + 2);
    }
    
    private void synchronize(GridMatrixAdiacenta mAdiacenta) {
        int      i, j, n, k;
        boolean  t;
        String   s;
        String[] strings = new String[nEdge * nEdge];
        
        List<TextCellLA> tc;
        n = 0;
        
        for (i = 0; i < nEdge; i++) {
            tc = listTextCell.get(i);
            for (j = 0; j < nEdge; j++) {
                s = String.valueOf(j + 1);
                t = false;
                for (k = 0; k < tc.size(); k++) if (tc.get(k).getText().equals(s)) t = true;
                if (t) strings[n] = "1";
                else strings[n] = "0";
                n++;
            }
        }
        
        mAdiacenta.createNewMatrix(strings, nEdge);
    }
    
    private void synchronize(GridMatrixIncidenta mIncidenta) {
        int i, j, k, n = 0, i1;
        
        List<String>     list = new ArrayList<>();
        List<TextCellLA> tc;
        
        for (i = 0; i < listTextCell.size(); i++) {
            tc = listTextCell.get(i);
            for (j = 0; j < tc.size(); j++, n++) {
                k = Integer.valueOf(tc.get(j).getText()) - 1;
                for (i1 = 0; i1 < nEdge; i1++)
                    if (i == i1 && i1 != k) list.add("-1");
                    else if (i1 != i && k == i1) list.add("1");
                    else if (i1 == k) list.add("2");
                    else list.add("0");
            }
        }
        
        mIncidenta.createNewMatrix(list.toArray(new String[0]), nEdge, n);
    }
    
    public List<List<Integer>> getListInteger() {
        List<List<Integer>> lists = new ArrayList<>();
        List<TextCellLA> tc;
        List<Integer> row;
        int i, j, n, m;
        n = listTextCell.size();
        
        for (i = 0; i < n; i++) {
            tc = listTextCell.get(i);
            row = new ArrayList<>();
            m = tc.size();
            for (j = 0; j < m; j++) {
                row.add(Integer.valueOf(tc.get(j).getText()));
            }
            lists.add(row);
        }
        
        return lists;
    }
    
    @Override
    public void synchronize(GridPane pane) {
        if (pane instanceof GridMatrixAdiacenta)
            synchronize((GridMatrixAdiacenta) pane);
        if (pane instanceof GridMatrixIncidenta)
            synchronize((GridMatrixIncidenta) pane);
    }
}
