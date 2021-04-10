package proj1.featuresVectors;

public class WordsCountAtSentence extends Feature {

    private double wordsCountAtSentence;

    public WordsCountAtSentence(int wordsCountAtSentence) {
        super(false, wordsCountAtSentence);
        this.wordsCountAtSentence = wordsCountAtSentence;
    }

    public double getWordsCountAtSentence() {
        return wordsCountAtSentence;
    }

    public void setWordsCountAtSentence(double wordsCountAtSentence) {
        this.wordsCountAtSentence = wordsCountAtSentence;
    }
}
