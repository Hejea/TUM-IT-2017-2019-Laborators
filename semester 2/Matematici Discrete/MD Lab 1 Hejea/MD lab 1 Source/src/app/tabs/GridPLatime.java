package app.tabs;

import app.components.LabelCell;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class GridPLatime extends GridPane {
    
    private List<List<Integer>> listEdge;
    private List<LabelCell>     listAdincime;
    private ComboBox<String>    newAEdge;
    private Label[]             emptyCell;
    private Label               titleAEdge;
    
    public GridPLatime() {
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
    
    private void showListAdincime(String s) {
        if (s != null && !s.equals("-")) {
            
            int nodeStart = Integer.valueOf(s.substring(1));
            int top, i, j, a;
            
            getChildren().removeAll(listAdincime);
            listAdincime.clear();
            List<Integer> l  = new ArrayList<>();
            List<Integer> m2 = new ArrayList<>();
            List<Integer> t;
            
            a = listEdge.size() + 1;
            for (i = 1; i < a; i++) m2.add(i);
            top = 0;
            
            while (!m2.isEmpty()) {
                l.add(nodeStart);
                listAdincime.add(new LabelCell(String.valueOf(nodeStart)));
                m2.remove((Integer) nodeStart);
                while (top < l.size()) {
                    nodeStart = l.get(top++);
                    
                    t = listEdge.get(nodeStart - 1);
                    j = t.size();
                    
                    for (i = 0; i < j; i++) {
                        a = t.get(i);
                        if (!l.contains(a)) {
                            l.add(a);
                            listAdincime.add(new LabelCell(String.valueOf(a)));
                            m2.remove((Integer) a);
                        }
                    }
                }
                if (!m2.isEmpty()) nodeStart = m2.get(0);
            }
            
            a = listAdincime.size();
            for (i = 0; i < a; i++) add(listAdincime.get(i), i, 2);
        }
    }
}









