package hejea;

class MaxPerRow {
    double[] listRowMax;
    double   max;
    double   min;
    int      posMax;
    int      posMin;
    
    MaxPerRow(double[][] mc) {
        
        int i, j, nRow = mc.length, nCol = mc[0].length;
        listRowMax = new double[nRow];
        
        for (i = 0; i < nRow; i++) {
            listRowMax[i] = mc[i][0];
            for (j = 1; j < nCol; j++) {
                if (listRowMax[i] < mc[i][j]) listRowMax[i] = mc[i][j];
            }
        }
        
        max = listRowMax[0];
        min = listRowMax[0];
        posMax = posMin = 0;
        
        for (i = 1; i < nRow; i++) {
            if (max < listRowMax[i]) {
                posMax = i;
                max = listRowMax[i];
            }
            if (min > listRowMax[i]) {
                posMin = i;
                min = listRowMax[i];
            }
        }
    }
}
