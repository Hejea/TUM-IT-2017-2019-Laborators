package hejea;

class MaxPerCol {
    double[] listColMax;
    
    MaxPerCol(double[][] mc) {
        
        int i, j;
        int nRow = mc.length;
        int nCol = mc[0].length;
        listColMax = new double[nCol];
        
        for (j = 0; j < nCol; j++) {
            listColMax[j] = mc[0][j];
            for (i = 1; i < nRow; i++) {
                if (listColMax[j] < mc[i][j]) listColMax[j] = mc[i][j];
            }
        }

    }
}
