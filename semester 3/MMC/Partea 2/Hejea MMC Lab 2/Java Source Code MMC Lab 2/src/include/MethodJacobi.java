package include;

import static java.lang.StrictMath.abs;

public class MethodJacobi {
    public static int    nrMaxIteration;
    public static double eps;
    public static Decimal df = new Decimal();
    
    public SystemEQ sys;
    
    public static void showMatrix(SystemEQ sys) {
        int i, j;
        
        for (i = 0; i < sys.Eq.length; i++) {
            System.out.println();
            for (j = 0; j < sys.Eq.length; j++) {
                System.out.format("%10s", df.format(sys.Eq[i][j]));
            }
            System.out.format("   |%10s", df.format(sys.Eq[i][sys.Eq.length]));
        }
        System.out.println("\n");
    }
    
    public static void showVector(double[] x) {
        int i;
        for (i = 0; i < x.length; i++) {
            System.out.format("%20s", df.format(x[i]));
        }
        System.out.println();
    }
    
    public static boolean CondStopEps(SystemEQ sys) {
        int      i;
        boolean  stop = true;
        double[] x1   = sys.X.get(sys.X.size() - 1);
        double[] x0   = sys.X.get(sys.X.size() - 2);
        double   max  = sys.DifE[0] = abs(x1[0] - x0[0]);
        
        for (i = 1; i < sys.Eq.length; i++) {
            sys.DifE[i] = abs(x1[i] - x0[i]);
            if (max > sys.DifE[i]) {
                max = sys.DifE[i];
            }
        }
        
        if (max > eps) {
            stop = false;
        }
        return stop;
    }
    
    public void run() throws Exception {
        
        sys = new SystemEQ();
        sys.init();
        
        int    i, j, k;
        double sum;
        
        if (!Convergenta.diagonalDominant(sys)) {
            throw new Exception("Matricea nu este convergenta");
        } else {
            
            k = 0;
            do {
                double[] x1 = sys.X.get(k).clone();
                double[] x  = sys.X.get(k).clone();
                
                for (i = 0; i < sys.Eq.length; i++) {
                    sys.D[i] = sys.Eq[i][sys.Eq.length] / sys.Eq[i][i];
                    sum = 0.0;
                    for (j = 0; j < sys.Eq.length; j++) {
                        if (j != i) {
                            sys.Q[i][j] = -sys.Eq[i][j] / sys.Eq[i][i];
                            sum += sys.Eq[i][j] * x[j];
                        } else {
                            sys.Q[i][j] = 0;
                        }
                    }
                    x1[i] = sys.Eq[i][sys.Eq.length] - sum;
                    x1[i] /= sys.Eq[i][i];
                }
                
                sys.X.add(x1);
                if (CondStopEps(sys)) break;
                
                k++;
            } while (k < nrMaxIteration);
        }
    }
}
