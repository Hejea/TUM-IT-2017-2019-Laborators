package app.tabs;

import app.components.LabelCell;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class GridPAdincime extends GridPane {
    
    private List<List<Integer>> listEdge;
    private List<LabelCell>     listAdincime;
    private ComboBox<String>    newAEdge;
    private Label[]             emptyCell;
    private Label               titleAEdge;
    
    public GridPAdincime() {
        listEdge = new ArrayList<>();
        listAdincime = new ArrayList<>();
        newAEdge = new ComboBox<>();
        titleAEdge = new Label("Virf de Start");
        
        newAEdge.getStylesheets().add("style/combo.css");
        titleAEdge.getStyleClass().add("title-aedge");
        
        emptyCell = new Label[4];
        for (int i = 0; i < 4; i++) {
            emptyCell[i] = new Label("");
            emptyCell[i].getStyleClass().add("rowEmpty");
        }
        
        newAEdge.getSelectionModel().selectedItemProperty().
                addListener((v, oldValue, newValue) -> showListAdincime(newValue));
        
        refresh();
    }
    
    public void createNewList(List<List<Integer>> listInteger) {
        refresh();
        int i;
        int n = listInteger.size() + 1;
        
        List<Integer> li;
        
        listAdincime.clear();
        listEdge.clear();
        newAEdge.getSelectionModel().clearSelection();
        String[] strings = new String[n];
        
        strings[0] = "-";
        
        for (i = 1; i < n; i++) {
            li = new ArrayList<>(listInteger.get(i - 1));
            listEdge.add(li);
            strings[i] = "X" + i;
        }
        
        newAEdge.setItems(FXCollections.observableArrayList(strings));
        newAEdge.getSelectionModel().selectFirst();
    }
    
    private void refresh() {
        getChildren().clear();
        add(titleAEdge, 0, 0, 3, 1);
        add(newAEdge, 3, 0, 2, 1);
        add(emptyCell[0], 0, 1);
        add(emptyCell[1], 2, 1);
        add(emptyCell[2], 1, 1);
        add(emptyCell[3], 2, 3);
    }
    
    private void DFS(int x, boolean[] viz) {
        int i, n;
        viz[x] = true;
        listAdincime.add(new LabelCell(String.valueOf(x)));
        for (i = 0; i < listEdge.get(x - 1).size(); i++) {
            n = listEdge.get(x - 1).get(i);
            if (!viz[n]) DFS(n, viz);
        }
    }
    
    private void showListAdincime(String s) {
        if (s != null && !s.equals("-")) {
            int nTot = listEdge.size() + 1;
            int nNod, i;
            
            getChildren().removeAll(listAdincime);
            listAdincime.clear();
            boolean[] mark = new boolean[nTot];
            nNod = Integer.valueOf(s.substring(1));
            
            DFS(nNod, mark);
            for (i = 1; i < nTot; i++) if (!mark[i]) DFS(i, mark);
            
            nNod = listAdincime.size();
            for (i = 0; i < nNod; i++) add(listAdincime.get(i), i, 2);
        }
    }
}
