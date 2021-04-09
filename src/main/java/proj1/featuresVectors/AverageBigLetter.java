package proj1.featuresVectors;

public class AverageBigLetter extends Feature {


    private double averagesBigLetters = 0.0;

    public AverageBigLetter(double averagesBigLetters) {
        super(false);
        this.averagesBigLetters = averagesBigLetters;
    }

    public double getAveragesBigLetters() {
        return averagesBigLetters;
    }

    public void setAveragesBigLetters(double averagesBigLetters) {
        this.averagesBigLetters = averagesBigLetters;
    }
}
