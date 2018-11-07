package hejea;

class MinPerRow {
    double[] listRowMin;
    double   max;
    int      posMax;
    
    MinPerRow(double[][] mc) {
        
        int i, j, nRow = mc.length, nCol = mc[0].length;
        listRowMin = new double[nRow];
        
        for (i = 0; i < nRow; i++) {
            listRowMin[i] = mc[i][0];
            for (j = 1; j < nCol; j++) {
                if (listRowMin[i] > mc[i][j]) listRowMin[i] = mc[i][j];
            }
        }
    
        max = listRowMin[0];
        posMax = 0;
    
        for (i = 1; i < nRow; i++) {
            if (max < listRowMin[i]) {
                posMax = i;
                max = listRowMin[i];
            }
        }
    }
}
