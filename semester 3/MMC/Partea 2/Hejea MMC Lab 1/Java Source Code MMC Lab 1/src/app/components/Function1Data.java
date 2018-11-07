package app.components;

public class Function1Data {
    
    // F(xC).
    public static double F(double x) {
        return x * x * x - 3 * x + 5;
    }
    
    // functia phi(xC).
    public static double Fi(double x) {
        return (x * x * x + 5) / (3);
    }
    
    // derivata 1 F'(xC).
    public static double F1(double x) {
        return (3 * x * x - 3);
    }
    
    // derivata 2 F"(xC).
    public static double F2(double x) {
        return (6 * x);
    }
    
}
