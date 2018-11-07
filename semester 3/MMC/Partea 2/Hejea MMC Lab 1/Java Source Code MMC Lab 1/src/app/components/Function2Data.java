package app.components;

public class Function2Data {
    
    // F(xC).
    public static double F(double x) {
        return x * x * x - 0.2 * x * x + 0.5 * x - 1.0;
    }
    
    // functia phi(xC).
    public static double Fi(double x) {
        return x;
    }
    
    // derivata 1 F'(xC).
    public static double F1(double x) {
        return 3 * x * x - 0.4 * x + 0.5;
    }
    
    // derivata 2 F"(xC).
    public static double F2(double x) {
        return (6 * x - 0.4);
    }
    
}
