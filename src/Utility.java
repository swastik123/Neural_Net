
public class Utility {

    public static double getSigmoidValue(double input) {
        return 1 / (1 + Math.pow(Math.E, -input));
    }
}

enum LayerType {
    HIDDEN,
    INPUT,
    OUTPUT
}
