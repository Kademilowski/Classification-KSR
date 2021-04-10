package proj1.featuresVectors;

public class CountInterpunctions extends Feature {

    private double countInterpunctions;

    public CountInterpunctions(double countInterpunctions) {
        super(false, countInterpunctions);
        this.countInterpunctions = countInterpunctions;
    }

    public double getCountInterpunctions() {
        return countInterpunctions;
    }

    public void setCountInterpunctions(double countInterpunctions) {
        this.countInterpunctions = countInterpunctions;
    }
}
