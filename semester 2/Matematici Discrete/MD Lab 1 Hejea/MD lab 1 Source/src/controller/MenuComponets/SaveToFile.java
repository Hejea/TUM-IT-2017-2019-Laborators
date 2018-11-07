package controller.MenuComponets;

import app.components.TextCell;
import controller.CMain;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile {
    private File  file;
    private CMain cMain;
    
    public SaveToFile(File file, CMain cMain) {
        this.file = file;
        this.cMain = cMain;
    }
    
    public void saveMatrixAdiacenta() {
        try {
            List<String>   listLine     = new ArrayList<>();
            List<TextCell> listTextCell = cMain.tab1Controller.gCellMatrixAdiacenta.listTextCell;
            StringBuffer   line;
            int            i, j, n;
            
            n = cMain.tab1Controller.gCellMatrixAdiacenta.nEdge;
            
            listLine.add("0");
            listLine.add(String.valueOf(n));
            
            for (i = 0; i < n; i++) {
                line = new StringBuffer();
                for (j = 0; j < n; j++) {
                    line.append(listTextCell.get(i * n + j).getText());
                    if (j < n - 1) line.append(" ");
                }
                listLine.add(line.toString());
            }
            
            Files.write(file.toPath(), listLine, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveMatrixIncidenta() {
        try {
            List<String>   listLine     = new ArrayList<>();
            List<TextCell> listTextCell = cMain.tab2Controller.gCellMatrixIncidenta.listTextCell;
            StringBuffer   line;
            int            i, j, nEdge, nArc;
            
            nArc = cMain.tab2Controller.gCellMatrixIncidenta.nArc;
            nEdge = cMain.tab2Controller.gCellMatrixIncidenta.nEdge;
            
            listLine.add("1");
            listLine.add(String.valueOf(nArc) + " " + String.valueOf(nEdge));
            
            for (i = 0; i < nArc; i++) {
                line = new StringBuffer();
                for (j = 0; j < nEdge; j++) {
                    line.append(listTextCell.get(i * nEdge + j).getText());
                    if (j < nEdge - 1) line.append(" ");
                }
                listLine.add(line.toString());
            }
            
            Files.write(file.toPath(), listLine, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
