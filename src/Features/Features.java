package Features;


import Data.Article;

import java.util.List;

public class Features {


    private Article article;
    private List<String> stopterms;

    public Features(Article article, List<String> stopterms){
        this.article = article;
        this.stopterms = stopterms;
    }

    private List<String> author;
    private double avgBigLetter;
    private String capital;
    private double interpunctions;
    private String currency;
    private int wordsCount;
    private double wordsCountAtSentence;
    private double countSentences30;
    private List<String> topics;
    private String firstSentence;


    public void setFeatures(){
        this.topics = (ExtractorFeature.Topics(this.article.getTopics()));
        this.author = (ExtractorFeature.AuthorName(this.article.getAuthors()));
        this.avgBigLetter =(ExtractorFeature.AverageBigLetter(this.article.getText()));
        this.capital = (ExtractorFeature.Capital(this.article.getText(), this.article.getPlaces()));
        this.currency = (ExtractorFeature.Currency(this.article.getText()));
        this.interpunctions = (ExtractorFeature.CountInterpunctions(this.article.getText()));
        this.wordsCount = (ExtractorFeature.WordsCount(this.stopterms, this.article.getText()));
        this.wordsCountAtSentence = (ExtractorFeature.WordsCountAtSentence(this.article.getText()));
        this.countSentences30 = (ExtractorFeature.CountSentences30PercentText(this.article.getText()));
        this.firstSentence = (ExtractorFeature.FirstSentence(this.article.getText()));


    }

    public String getFirstSentence() {
        return firstSentence;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<String> getStopterms() {
        return stopterms;
    }

    public void setStopterms(List<String> stopterms) {
        this.stopterms = stopterms;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public double getAvgBigLetter() {
        return avgBigLetter;
    }

    public void setAvgBigLetter(double avgBigLetter) {
        this.avgBigLetter = avgBigLetter;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getInterpunctions() {
        return interpunctions;
    }

    public void setInterpunctions(double interpunctions) {
        this.interpunctions = interpunctions;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public double getWordsCountAtSentence() {
        return wordsCountAtSentence;
    }

    public void setWordsCountAtSentence(double wordsCountAtSentence) {
        this.wordsCountAtSentence = wordsCountAtSentence;
    }

    public double getCountSentences30() {
        return countSentences30;
    }

    public void setCountSentences30(double countSentences30) {
        this.countSentences30 = countSentences30;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }


/*
    public void setFirstSentence(String firstSentence) {
        this.firstSentence = firstSentence;
    }*/
}
