package include;

import java.util.ArrayList;
import java.util.List;

public class SystemEQ {
    public static String sysText;
    public static int    nRow;
    public static int    nCol;
    
    public double[][]     Eq;
    public double[][]     Q;
    public double[]       D;
    public List<double[]> X;
    public double[]       DifE;
    
    public void init() throws Exception {
        
        Eq = new double[nRow][nCol + 1];
        Q = new double[nRow][nCol];
        D = new double[nRow];
        DifE = new double[nRow];
        
        if (nCol > nRow) throw new Exception("Numarul de ecuatii este insuficient");
        if (nRow > nCol) throw new Exception("Numarul de ecuatii este depasit");
        
        String[] temp = sysText.split("[ \n]");
        int      i, j, k;
        
        for (i = 0, j = 0; i < temp.length; i++) {
            if (!temp[i].isEmpty()) {
                temp[j] = temp[i];
                j++;
            }
        }
        if (j == 0) throw new Exception("Nu sunt date");
        if (j != nRow * (nCol + 1)) throw new Exception("Nu sunt destule date");
        
        for (i = 0, k = 0; i < nRow; i++) {
            for (j = 0; j < nCol + 1; j++, k++) {
                try {
                    Eq[i][j] = Double.valueOf(temp[k]);
                } catch (Exception e) { throw new Exception("Date incorecte. Dati numere reale"); }
            }
        }
        
        double[] x1 = new double[Eq.length];
        X = new ArrayList<>();
        for (i = 0; i < Eq.length; i++) {
            if (Eq[i][i] == 0) throw new Exception("Neacceptat : Impartirea la zero");
            x1[i] = Eq[i][Eq.length] / Eq[i][i];
        }
        X.add(x1);
    }
}
