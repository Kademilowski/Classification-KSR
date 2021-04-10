package proj1.featuresVectors;

public class CountSentences30PercentText extends Feature {

    private double countSentences30;

    public CountSentences30PercentText(double countSentences30) {
        super(false, countSentences30);
        this.countSentences30 = countSentences30;
    }

    public double getCountSentences30() {
        return countSentences30;
    }

    public void setCountSentences30(double countSentences30) {
        this.countSentences30 = countSentences30;
    }
}
