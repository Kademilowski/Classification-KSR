package proj1.featuresVectors;

public class WordsCountAtSentence extends Feature {

    private int wordsCountAtSentence;

    public WordsCountAtSentence(int wordsCountAtSentence) {
        super(false);
        this.wordsCountAtSentence = wordsCountAtSentence;
    }

    public int getWordsCountAtSentence() {
        return wordsCountAtSentence;
    }

    public void setWordsCountAtSentence(int wordsCountAtSentence) {
        this.wordsCountAtSentence = wordsCountAtSentence;
    }
}
