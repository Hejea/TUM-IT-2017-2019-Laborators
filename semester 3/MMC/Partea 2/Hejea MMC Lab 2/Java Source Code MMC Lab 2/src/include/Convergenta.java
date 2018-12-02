package include;

import static java.lang.StrictMath.abs;

public class Convergenta {
    
    public static boolean diagonalDominant(SystemEQ sys) {
        int i, j;
        boolean converge = true;
        double aux;
        
        for (i = 0; i < sys.Eq.length; i++) {
            aux = 0.0;
            for (j = 0; j < sys.Eq.length; j++) {
                if (i != j) {
                    aux += abs(sys.Eq[i][j]);
                }
            }
            if (abs(sys.Eq[i][i]) <= aux) {
                converge = false;
                break;
            }
        }
        return converge;
    }
    
}
