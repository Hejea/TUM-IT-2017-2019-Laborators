package hejea;

import java.text.DecimalFormat;

public class MatrixCR {
    private static DecimalFormat df = new DecimalFormat("#####.#####");
    private double[][] mc;
    private double[][] mcc;
    private double[][] mr;
    private double[][] mrc;
    private MaxPerCol  maxPerCol;
    private MaxPerRow  maxPerRow;
    private MinPerRow  minPerRow;
    private int        nRow;
    private int        nCol;
    private String[][] titles = {
            {"Matricea Consecintelor"},
            {"Matricea Regretelor"},
            {"Criterii"},
            {"Optimistului"},
            {"Pesimistului"},
            {"Hurwicz"},
            {"Laplace"},
            {"Savage"},
            {"Maximizarii"},
            {"Minimizarii"}
    };
    
    private static String repeat(String s, int n) {
        return String.format("%0" + n + "d", 0)
                     .replace("0", s);
    }
    
    void ProblemaExemplu() {
    
        System.out.println("\n\nProblema Exemplu");
        
        int[] aDecizii = {5, 6, 7, 8};
        int[] aStari   = {5, 6, 7, 8};
        
        double pCost       = 3.0;
        double pProfit     = 3.5;
        double pAlternativ = 2.0;
        
        double   alpha         = 0.4;
        double[] probabilitati = {5.0 / 30.0, 10.0 / 30.0, 10.0 / 30.0, 5.0 / 30.0};
        
        nRow = aDecizii.length;
        nCol = aStari.length;
        
        int ncRow = nRow + 2;
        int ncCol = 5;
        int nrCol = 2;
        mc = new double[nRow][nCol];
        mcc = new double[ncRow][ncCol];
        
        mr = new double[nRow][nCol];
        mrc = new double[ncRow][nrCol];
        
        int    i, j;
        double cost;
        
        for (i = 0; i < nRow; i++) {
            cost = aDecizii[i] * pCost;
            for (j = 0; j < nCol; j++) {
                if (aDecizii[i] >= aStari[j]) {
                    mc[i][j] = (aStari[j] * pProfit) - cost + (aDecizii[i] - aStari[j]) * pAlternativ;
                } else {
                    mc[i][j] = mc[i][i];
                }
            }
        }
        
        maxPerCol = new MaxPerCol(mc);
        maxPerRow = new MaxPerRow(mc);
        minPerRow = new MinPerRow(mc);
        
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < nCol; j++) {
                mr[i][j] = maxPerCol.listColMax[j] - mc[i][j];
            }
        }
        
        LuareaDeciziilor(alpha, probabilitati);
    }
    
    void Problema_1_2() {
    
        System.out.println("\n\nProblema 1.2");
        
        int[] aDecizii = {10, 11, 12, 13};
        int[][] aStari = {{90, 100, 110},
                          {85, 90, 105},
                          {70, 80, 100},
                          {60, 70, 90}};
        
        double pCost = 4.0;
        double alpha = 0.5;
        
        double[] probabilitati = {0.4, 0.3, 0.3};
        
        nRow = aDecizii.length;
        nCol = aStari[0].length;
        
        int ncRow = nRow + 2;
        int ncCol = 5;
        int nrCol = 2;
        
        mc = new double[nRow][nCol];
        mcc = new double[ncRow][ncCol];
        
        mr = new double[nRow][nCol];
        mrc = new double[ncRow][nrCol];
        
        int    i, j;
        double cost;
        
        for (i = 0; i < nRow; i++) {
            cost = aDecizii[i] - pCost;
            for (j = 0; j < nCol; j++) {
                mc[i][j] = aStari[i][j] * cost;
            }
        }
        
        maxPerCol = new MaxPerCol(mc);
        maxPerRow = new MaxPerRow(mc);
        minPerRow = new MinPerRow(mc);
        
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < nCol; j++) {
                mr[i][j] = maxPerCol.listColMax[j] - mc[i][j];
            }
        }
        
        LuareaDeciziilor(alpha, probabilitati);
    }
    
    void show() {
        int j;
        int ncCol = 5;
        int nrCol = 2;
        int nbsp = (nCol) * 10;
        
        StringBuilder buffer = new StringBuilder();
        
        buffer.append(String.format("%s", repeat("_", nbsp+5*15+3)))
              .append(String.format("\n|%"+(nbsp+2)+"s%40s%35s\n|%"+(nbsp-6)+"s%8s%75s\n|%"+(nbsp+2)+"s",
                                    "|", titles[2][0], "|", titles[0][0], "|", "|", "|"))
              .append(String.format("%13s%2s", titles[3][0], "|"))
              .append(String.format("%13s%2s", titles[4][0], "|"))
              .append(String.format("%10s%5s", titles[5][0], "|"))
              .append(String.format("%10s%5s", titles[6][0], "|"))
              .append(String.format("%12s%3s", titles[8][0], "|"))
              .append(String.format("\n|%"+(nbsp+1)+"s|", repeat("-", nbsp+1)))
              .append(repeat(String.format("%14s|", repeat("-", 14)), ncCol))
              .append("\n|");
        
        buffer.append(MatrixToBuffer(mc, mcc));
        
        buffer.append(String.format("%s|\n|%"+(nbsp)+"s |", repeat("-", nbsp+5*15+1), "Max"));
        
        for (j = 0; j < ncCol; j++) {
            buffer.append(String.format("%9s= D%s |", df.format(mcc[nRow][j]), df.format(mcc[nRow + 1][j] + 1)));
        }
        
        buffer.append(String.format("\n%s", repeat("-", nbsp+5*15+3)));
        
        //--------------//
        
        buffer.append(String.format("\n%s", repeat("_", nbsp+2*15+3)))
              .append(String.format("\n|%"+(nbsp+2)+"s%19s%11s\n|%"+(nbsp-6)+"s%8s%30s\n|%"+(nbsp+2)+"s",
                                    "|", titles[2][0], "|", titles[1][0], "|", "|", "|"))
              .append(String.format("%10s%5s", titles[7][0], "|"))
              .append(String.format("%13s%2s", titles[9][0], "|"))
              .append(String.format("\n|%"+(nbsp+1)+"s|", repeat("-", nbsp+1)))
              .append(repeat(String.format("%14s|", repeat("-", 14)), nrCol))
              .append("\n|");
        
        buffer.append(MatrixToBuffer(mr, mrc));
        
        buffer.append(String.format("%s|\n|%"+(nbsp)+"s |", repeat("-", nbsp+2*15+1), "Min"));
        
        for (j = 0; j < nrCol; j++) {
            buffer.append(String.format("%8s = D%s |", df.format(mrc[nRow][j]), df.format(mrc[nRow + 1][j] + 1)));
        }
        
        buffer.append(String.format("\n%s", repeat("-", nbsp+2*15+3)));
        
        
        System.out.println(buffer);
    }
    
    private void LuareaDeciziilor(double alpha, double[] pr) {
        int    i, j;
        double temp1, temp2;
        
        for (i = 0; i < nRow; i++) {
            
            // Criteriul maxi-max (criteriul optimistului).
            mcc[i][0] = maxPerRow.listRowMax[i];
            
            // Criteriul Wald (maxi-min: criteriul pesimistului).
            mcc[i][1] = minPerRow.listRowMin[i];
            
            // Criteriul Hurwicz (alpha)
            mcc[i][2] = alpha * maxPerRow.listRowMax[i] + (1 - alpha) * minPerRow.listRowMin[i];
            
            
            for (j = 0, temp1 = 0, temp2 = 0; j < nCol; j++) {
                
                // Criteriul lui Laplace (criteriul şanselor egale).
                temp1 += mc[i][j];
                
                // Criteriul maximizării profitului scontat (așteptat)
                temp2 += pr[j] * mc[i][j];
            }
            mcc[i][3] = temp1 / nCol;
            mcc[i][4] = temp2;
        }
        
        double max2, max3, max4;
        int    pm2 = 0, pm3 = 0, pm4 = 0;
        max2 = mcc[0][2];
        max3 = mcc[0][3];
        max4 = mcc[0][4];
        for (i = 1; i < nRow; i++) {
            if (max2 < mcc[i][2]) {
                max2 = mcc[i][2];
                pm2 = i;
            }
            
            if (max3 < mcc[i][3]) {
                max3 = mcc[i][3];
                pm3 = i;
            }
            
            if (max4 < mcc[i][4]) {
                max4 = mcc[i][4];
                pm4 = i;
            }
        }
        
        mcc[i][0] = maxPerRow.max;
        mcc[i][1] = minPerRow.max;
        mcc[i][2] = max2;
        mcc[i][3] = max3;
        mcc[i][4] = max4;
        
        mcc[i + 1][0] = maxPerRow.posMax;
        mcc[i + 1][1] = minPerRow.posMax;
        mcc[i + 1][2] = pm2;
        mcc[i + 1][3] = pm3;
        mcc[i + 1][4] = pm4;
        
        MaxPerRow maxPerRow1 = new MaxPerRow(mr);
        
        for (i = 0; i < nRow; i++) {
            
            // Criteriul mini-max a regretelor (criteriul Savage)
            mrc[i][0] = maxPerRow1.listRowMax[i];
            
            
            for (j = 0, temp1 = 0; j < nCol; j++) {
                temp1 += pr[j] * mr[i][j];
            }
            
            mrc[i][1] = temp1;
        }
        
        double min = mrc[0][1];
        pm2 = 0;
        
        for (i = 1; i < nRow; i++) {
            if (min > mrc[i][1]) {
                min = mrc[i][1];
                pm2 = i;
            }
        }
        
        mrc[nRow][0] = maxPerRow1.min;
        mrc[nRow][1] = min;
        
        mrc[nRow + 1][0] = maxPerRow1.posMin;
        mrc[nRow + 1][1] = pm2;
    }
    
    private StringBuilder MatrixToBuffer(double[][] m1, double[][] m2) {
        int i, j;
        
        StringBuilder buffer = new StringBuilder();
        
        for (i = 0; i < m1.length; i++) {
            for (j = 0; j < m1[0].length; j++) {
                buffer.append(String.format("%10s", df.format(m1[i][j])));
            }
            buffer.append(" |");
            
            for (j = 0; j < m2[0].length; j++) {
                buffer.append(String.format("%13s |", df.format(m2[i][j])));
            }
            buffer.append("\n|");
        }
        
        return buffer;
    }
}
