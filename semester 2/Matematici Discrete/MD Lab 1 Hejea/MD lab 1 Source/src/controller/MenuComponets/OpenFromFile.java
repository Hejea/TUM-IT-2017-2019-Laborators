package controller.MenuComponets;

import app.components.MethodsStatic;
import app.tabs.GridMatrixAdiacenta;
import app.tabs.GridMatrixIncidenta;
import app.tabs.GridListaAdiacenta;
import controller.CMain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OpenFromFile {
    private File  file;
    private CMain cMain;
    
    public OpenFromFile(File file, CMain cMain) {
        this.file = file;
        System.out.println(file);
        this.cMain = cMain;
    }
    
    public void open() {
        String type;
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNext()) {
                type = scanner.next();
                
                if (type.equals("0")) {        // Matricea Adiacenta
                    try {
                        OpenMatrixAdiacenta(cMain.tab1Controller.gCellMatrixAdiacenta, scanner);
                    } catch (Exception e) {
                        System.out.println("Load Error. Matricea Adiacenta.\n\n");
                    }
                } else if (type.equals("1")) { // Matricea Incidenta
                    try {
                        OpenMatrixIncidenta(cMain.tab2Controller.gCellMatrixIncidenta, scanner);
                    } catch (Exception e) {
                        System.out.println("\nLoad Error. Matricea Incidenta.\n\n");
                        e.printStackTrace();
                    }
                } else if (type.equals("2")) { // Lista Adiacenta
                    try {
                        OpenListaAdiacenta(cMain.tab3Controller.gCellListaAdiacenta, scanner);
                    } catch (Exception e) {
                        System.out.println("Load Error. Lista Adiacenta.\n\n");
                        e.printStackTrace();
                    }
                } else if (type.equals("3")) { // Matricea Costuriilor
                    try {
                    
                    } catch (Exception e) {
                        System.out.println("Load Error. Matricea Costuriilor.\n\n");
                    }
                } else { System.out.println("\nLoad Error. Not valid file.\n\n"); }
            }
        } catch (Exception e) { }
    }
    
    public void OpenMatrixAdiacenta(GridMatrixAdiacenta matrixAdiacenta, Scanner scanner) throws Exception {
        int    i, n, nE;
        String value;
        String m0[] = {"0", "1"};
        
        nE = Integer.valueOf(scanner.next());
        n = nE * nE;
        String[] strings = new String[n];
        
        for (i = 0; i < n; i++) {
            value = scanner.next();
            if (MethodsStatic.isValidValue(m0, value)) strings[i] = value;
            else throw new Exception("Not valid value");
        }
        
        matrixAdiacenta.createNewMatrix(strings, nE);
    
        MethodsStatic.refresh(matrixAdiacenta, CMenu.getA(0), CMenu.getB(0));
        cMain.idTabPane.getSelectionModel().select(cMain.idMatrixAdiacenta);
    }
    
    public void OpenMatrixIncidenta(GridMatrixIncidenta matrixIncidenta, Scanner scanner) throws Exception {
        int    i, n, nE, nA;
        String value;
        String m1[] = {"-1", "0", "1", "2"};
        
        nA = Integer.valueOf(scanner.next());
        nE = Integer.valueOf(scanner.next());
        
        n = nA * nE;
        String[] strings = new String[n];
        
        for (i = 0; i < n; i++) {
            value = scanner.next();
            if (MethodsStatic.isValidValue(m1, value)) strings[i] = value;
            else throw new Exception("Not valid value");
        }
        
        matrixIncidenta.createNewMatrix(strings, nE, nA);
        
        MethodsStatic.refresh(matrixIncidenta, CMenu.getA(1),  CMenu.getB(1));
        cMain.idTabPane.getSelectionModel().select(cMain.idMatrixIncidenta);
    }
    
    public void OpenListaAdiacenta(GridListaAdiacenta listaAdiacenta, Scanner scanner) throws Exception {
        int    i, nE;
        Integer n;
        String value;
        
        nE = Integer.valueOf(scanner.next());
        List<String> strings = new ArrayList<>();
        i = 0;
        while (i < nE) {
            value = scanner.next();
            if ((n = Integer.valueOf(value)) != null) {
                if (n <= nE && n >= 0) {
                    if (n == 0) i++;
                    strings.add(value);
                } else throw new Exception("Not valid value");
            }
        }
        
        listaAdiacenta.createNewList(strings.toArray(new String[0]), nE);

        MethodsStatic.refresh(listaAdiacenta,  CMenu.getA(2),  CMenu.getB(2));
        cMain.idTabPane.getSelectionModel().select(cMain.idListaAdiacenta);
    }
    
}
