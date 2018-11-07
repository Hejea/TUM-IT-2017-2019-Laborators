package app.components;

import static app.components.Function1Data.F;
import static java.lang.Math.abs;

public class MethodBisection {
    
    public double   eps;
    public double   a;
    public double   b;
    public double[] x;
    public double[] fx;
    public int      nrItr;
    public int      nrItrMax;
    
    public void run() {
        
        double fa, fb;
    
        x = new double[nrItrMax];
        fx = new double[nrItrMax];
        
        validData();
        fa = F(a);
        fb = F(b);
        
        if (fa * fb < 0) {
            nrItr = 0;
            do {
                x[nrItr] = (a + b) / 2;
                
                fa = F(a);
                fb = F(b);
                fx[nrItr] = F(x[nrItr]);
                
                if (nrItr > nrItrMax || fa * fx[nrItr] == 0) break;
                if (fa * fx[nrItr] >= 0) a = x[nrItr];
                if (fb * fx[nrItr] >= 0) b = x[nrItr];
                
                nrItr++;
                
            } while (abs(b - a) > eps);
        }
    }
    
    private void validData() {
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }
    }
}
