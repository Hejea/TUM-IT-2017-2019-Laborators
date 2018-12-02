package controller;

import include.Decimal;
import include.MethodJacobi;
import include.SystemEQ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller02Jacobi {
    public static int nsp;
    static Decimal df = new Decimal();
    @FXML
    public TextField idEps;
    @FXML
    public TextField idMaxItr;
    @FXML
    public Button    idBtnCalculeaza;
    @FXML
    public TextArea  idSystemEq;
    @FXML
    public TextArea  idMatrixQ;
    @FXML
    public TextArea  idVectorD;
    @FXML
    public TextArea  idVectorX;
    @FXML
    public TextField idN;
    @FXML
    public TextField idM;
    MethodJacobi methodJacobi;
    
    public static void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare");
        alert.setHeaderText(e.toString());
        alert.setContentText(null);
        alert.showAndWait();
    }
    
    public String showVector(double[] x) {
        int           i;
        StringBuilder s = new StringBuilder();
        
        for (i = 0; i < x.length; i++) {
            s.append(String.format("%" + (nsp + 4) + "s", String.valueOf(df.format(x[i]))));
        }
        s.append("\n");
        return s.toString();
    }
    
    @FXML
    public void initialize() {
        System.out.println("Starts : Controller 02 Jacobi");
        nsp = countEpsDecimalDigits(Double.valueOf(idEps.getText())) + 4;
        methodJacobi = new MethodJacobi();
        
        initData();
        
        calculeaza(methodJacobi);
    }
    
    public void calculeaza(MethodJacobi methodJacobi) {
        
        try {
            nsp = countEpsDecimalDigits(Double.valueOf(idEps.getText())) + 4;
            syncData();
            rewriteSystemEq();
            methodJacobi.run();
            writeMatrixQ(methodJacobi);
            writeVectorD(methodJacobi);
            writeVectorX(methodJacobi);
        } catch (Exception e) {
            showError(e);
        }
        
    }
    
    void writeMatrixQ(MethodJacobi methodJacobi) {
        StringBuilder s    = new StringBuilder();
        int           nsp1 = 10;
        int           i, j;
        int           n    = methodJacobi.sys.Q.length;
        
        df.newDecimalPattern(5, 2);
        
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                s.append(String.format("%" + nsp1 + "s", String.valueOf(df.format(methodJacobi.sys.Q[i][j]))));
            }
            s.append("\n");
        }
        
        idMatrixQ.setText(s.toString());
    }
    
    void writeVectorD(MethodJacobi methodJacobi) {
        StringBuilder s   = new StringBuilder();
        int           nsp = 10;
        int           i;
        int           n   = methodJacobi.sys.Q.length;
        
        df.newDecimalPattern(5, 2);
        
        for (i = 0; i < n; i++) {
            s.append(String.format("%" + nsp + "s\n", String.valueOf(df.format(methodJacobi.sys.D[i]))));
        }
        
        idVectorD.setText(s.toString());
    }
    
    void writeVectorX(MethodJacobi methodJacobi) {
        StringBuilder s = new StringBuilder();
        
        int      i;
        int      size;
        double[] x;
        i = countEpsDecimalDigits(Double.valueOf(idEps.getText()));
        df.newDecimalPattern(i, i);
        
        s.append(String.format("%15s", " "));
        size = methodJacobi.sys.X.get(0).length;
        for (i = 1; i <= size; i++) {
            s.append(String.format("%" + (nsp + 4) + "s", "X" + i));
        }
        s.append("\n\n");
        size = methodJacobi.sys.X.size();
        if (size > 1) {
            s.append(String.format("Iteratia %3s : ", size - 2));
            x = methodJacobi.sys.X.get(size - 2);
            s.append(showVector(x));
        }
        
        s.append(String.format("Iteratia %3s : ", size - 1));
        x = methodJacobi.sys.X.get(size - 1);
        s.append(showVector(x));
        
        s.append(String.format("\n%12s : ", "Eroarea"));
        s.append(showVector(methodJacobi.sys.DifE));
        
        idVectorX.setText(s.toString());
    }
    
    void rewriteSystemEq() {
        SystemEQ systemEQ = new SystemEQ();
        try {
            systemEQ.init();
            idSystemEq.setText("");
            
            StringBuilder s   = new StringBuilder();
            int           nsp = 6;
            int           i, j;
            int           n   = systemEQ.Eq.length;
            
            df.newDecimalPattern(5, 2);
            
            for (i = 0; i < n; i++) {
                for (j = 0; j < n + 1; j++) {
                    s.append(String.format("%" + nsp + "s", String.valueOf(df.format(systemEQ.Eq[i][j]))));
                }
                s.append("\n");
            }
            
            idSystemEq.setText(s.toString());
            
        } catch (Exception e) {
            showError(e);
        }
    }
    
    public void initData() {
        idEps.setText(String.valueOf(0.00001));
        idMaxItr.setText(String.valueOf(100));
        
        String s   = "";
        int    nsp = 6;
        
        s += String.format("%" + nsp + "s", "10.0");
        s += String.format("%" + nsp + "s", "-1.0");
        s += String.format("%" + nsp + "s", "2.0");
        s += String.format("%" + nsp + "s", "0.0");
        s += String.format("%" + nsp + "s\n", "6.0");
        
        s += String.format("%" + nsp + "s", "-1.0");
        s += String.format("%" + nsp + "s", "11.0");
        s += String.format("%" + nsp + "s", "-1.0");
        s += String.format("%" + nsp + "s", "3.0");
        s += String.format("%" + nsp + "s\n", "25.0");
        
        s += String.format("%" + nsp + "s", "2.0");
        s += String.format("%" + nsp + "s", "-1.0");
        s += String.format("%" + nsp + "s", "10.0");
        s += String.format("%" + nsp + "s", "-1.0");
        s += String.format("%" + nsp + "s\n", "-11.0");
        
        s += String.format("%" + nsp + "s", "0.0");
        s += String.format("%" + nsp + "s", "3.0");
        s += String.format("%" + nsp + "s", "-1.0");
        s += String.format("%" + nsp + "s", "8.0");
        s += String.format("%" + nsp + "s", "15.0");
        
        idSystemEq.setText(s);
        
        syncData();
        
        // test
        SystemEQ systemEQ = new SystemEQ();
        try {
            systemEQ.init();
        } catch (Exception e) {
            showError(e);
        }
    }
    
    void syncData() {
        SystemEQ.sysText = idSystemEq.getText();
        SystemEQ.nRow = Integer.valueOf(idN.getText());
        SystemEQ.nCol = Integer.valueOf(idM.getText());
        
        MethodJacobi.eps = Double.valueOf(idEps.getText());
        MethodJacobi.nrMaxIteration = Integer.valueOf(idMaxItr.getText());
    }
    
    private int countEpsDecimalDigits(double e) {
        int i = 0;
        while (e < 1) {
            i++;
            e *= 10;
        }
        return i;
    }
    
    public void onClickCalculeaza(ActionEvent event) {
        calculeaza(methodJacobi);
    }
}
