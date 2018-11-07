package app.components;

import app.tabs.GridListaAdiacenta;
import controller.MenuComponets.CMenu;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class MethodsStatic {
    public static Node getNode(final int row, final int column, GridPane gridPane) {
        Node result = null;
        
        ObservableList<Node> childrens = gridPane.getChildren();
        
        for (Node node : childrens)
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        
        return result;
    }
    
    public static boolean validCell(String value) {
        return value.equals("0") || value.equals("1");
    }
    
    public static void setFileChooserProprietes(FileChooser c, String s) {
        c.setTitle(s + " Resource File");
        
        String path = CMenu.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ") + "../../../res";
        c.setInitialDirectory(new File(path));
        
        c.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
    }
    
    public static void refresh(Sync from, List<ObservableList<Node>> nodes, List<GridPane> to) {
        try {
            int i;
            for (i = 0; i < to.size(); i++) {
                GridPane pane = to.get(i);
                nodes.get(i).remove(pane);
                from.synchronize(pane);
                nodes.get(i).add(pane);
            }
        } catch (Exception e) { e.printStackTrace();}
    }
    
    public static boolean isValidValue(String[] list, String value) {
        for (String aM : list) if (aM.equals(value)) return true;
        return false;
    }
    
    public static void addListAdiacenta(GridListaAdiacenta list, GridPane pane) {
        int i, j, n;
        
        List<List<TextCellLA>> lc = list.listTextCell;
        List<LabelCell> le = list.listLabelEdge;
        
        n = lc.size();
       
        for (i = 0; i < n; i++) {
            pane.getChildren().removeAll(lc.get(i));
            pane.add(le.get(i), 0, i + 4);
            for (j = 0; j < lc.get(i).size(); j++)
                pane.add(lc.get(i).get(j), j + 1, i + 4);
        }
    }
}
