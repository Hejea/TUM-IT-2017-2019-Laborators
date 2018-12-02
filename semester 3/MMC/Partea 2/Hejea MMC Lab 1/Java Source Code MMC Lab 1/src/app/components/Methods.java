package app.components;

import static app.components.Function2Data.F;
import static app.components.Function2Data.F2;
import static java.lang.Math.abs;

public class Methods {
    
    public double   eps;
    public double   a;
    public double   b;
    public double[] xC;
    public double[] fxC;
    public double[] xS;
    public double[] fxS;
    public int      nrItrC;
    public int      nrItrS;
    public int      nrItrMaxChord;
    public int      nrItrMaxSecant;
    public String   isSolC;
    public String   isSolS;
    
    public void runChord() {
        
        double xf, fa, fb, fxf, q, w;
        
        xC = new double[nrItrMaxChord];
        fxC = new double[nrItrMaxChord];
        nrItrC = 0;
        isSolC = "";
        
        validData();
        fa = F(a);
        fb = F(b);
        
        if (fa * fb < 0) {
            
            if (fa * F2(a) < 0) {
                xC[0] = a;
                xf = b;
            } else {
                xC[0] = b;
                xf = a;
            }
            
            fxC[0] = F(xC[0]);
            fxf = F(xf);
            
            do {
                nrItrC++;
    
                // ce trebuie de inteles
                // q = f(x0) * (xF - x0)
                // w = f(xF) - f(x0)
                
                q = fxC[nrItrC - 1] * (xf - xC[nrItrC - 1]);
                w = fxf - fxC[nrItrC - 1];
                
                xC[nrItrC] = xC[nrItrC - 1] - q / w;
                fxC[nrItrC] = F(xC[nrItrC]);
                
            } while (abs(xC[nrItrC] - xC[nrItrC - 1]) > eps);
        } else {
            isSolC = "Nu are solutii pe intervalul dat";
        }
    }
    
    public void runSecant() {
        
        double fa, fb, q, w;
        
        xS = new double[nrItrMaxChord];
        fxS = new double[nrItrMaxChord];
        nrItrS = 0;
        isSolS = "";
        
        validData();
        fa = F(a);
        fb = F(b);
        if (fa * fb < 0) {
            if (fa * F2(a) > 0) {
                xS[0] = a;
                xS[1] = a + eps;
            } else {
                xS[0] = b;
                xS[1] = b - eps;
            }
            
            fxS[0] = F(xS[0]);
            fxS[1] = F(xS[1]);
    
            nrItrS = 1;
            
            do {
                nrItrS++;
                
                // ce trebuie de inteles
                // q = f(x1) * (x1 - x0)
                // w = f(x1) - f(x0)
                
                q = fxS[nrItrS - 1] * (xS[nrItrS - 1] - xS[nrItrS - 2]);
                w = fxS[nrItrS - 1] - fxS[nrItrS - 2];
                
                xS[nrItrS] = xS[nrItrS - 1] - q / w;
                fxS[nrItrS] = F(xS[nrItrS]);
                
            } while (abs(xS[nrItrS] - xS[nrItrS - 1]) >= eps);
        } else {
            isSolS = "Nu are solutii pe intervalul dat";
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
