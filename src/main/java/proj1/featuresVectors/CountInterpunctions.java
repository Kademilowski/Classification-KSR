package proj1.featuresVectors;

public class CountInterpunctions extends Feature {

    private int countInterpunctions;

    public CountInterpunctions(int countInterpunctions) {
        super(false);
        this.countInterpunctions = countInterpunctions;
    }

    public int getCountInterpunctions() {
        return countInterpunctions;
    }

    public void setCountInterpunctions(int countInterpunctions) {
        this.countInterpunctions = countInterpunctions;
    }
}
