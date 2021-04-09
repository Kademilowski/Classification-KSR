package proj1.featuresVectors;

public class WordsCount extends Feature {

    private int countWords;

    public WordsCount(int countWords) {
        super(false);
        this.countWords = countWords;
    }

    public int getCountWords() {
        return countWords;
    }

    public void setCountWords(int countWords) {
        this.countWords = countWords;
    }
}
