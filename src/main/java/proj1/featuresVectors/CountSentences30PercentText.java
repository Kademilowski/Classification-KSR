package proj1.featuresVectors;

public class CountSentences30PercentText extends Feature {

    private int countSentences30;

    public CountSentences30PercentText(int countSentences30) {
        super(false);
        this.countSentences30 = countSentences30;
    }

    public int getCountSentences30() {
        return countSentences30;
    }

    public void setCountSentences30(int countSentences30) {
        this.countSentences30 = countSentences30;
    }
}
