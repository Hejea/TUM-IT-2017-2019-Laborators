package include;

import java.text.DecimalFormat;

public class Decimal {
    public DecimalFormat df;
    static String        pattern = "#####.#####";
    
    public Decimal() {
        df = newDecimalPattern(5,5);
    }
    
    public DecimalFormat newDecimalPattern(int preN, int postN) {
        pattern = String.format("%"+preN+"s.%"+postN+"s", " ", " ").replace(" ", "#");
        df = new DecimalFormat(pattern);
        return df;
    }
    
    public String format(double d) {
        return df.format(d);
    }
}
