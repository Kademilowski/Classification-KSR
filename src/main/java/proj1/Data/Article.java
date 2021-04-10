package proj1.Data;

import proj1.Measures.NGramMeassure;
import proj1.Tuple.Tuple;
import proj1.Tuple.Tuple2;
import proj1.featuresVectors.ExtractorFeature;

import java.util.*;

public class Article {

    private String date;
    private List<String> topics;
    private List<String> places;
    private List<String> people;
    private String title;
    private List<String> authors;
    private String text;


    //nazwa cechy i wartosćć jej w postaci liczby
    List<Tuple2<String, Double>> features = new ArrayList<Tuple2<String, Double>>();




    //CONSTRUCTOR
    public Article(String date, List<String> topics, List<String> places,
                   List<String> people, String title, List<String> authors, String text) {

        //this.id = id;
        this.date = date;
        this.topics = topics;
        this.places = places;
        this.people = people;
        this.title = title;
        this.authors = authors;
        this.text = text;


    }

    public List<Tuple2<String, Double>> getFeatures() {
        return features;
    }

    public void setFeatures(List<Tuple2<String, Double>> features) {
        this.features = features;
    }


    public void setFeatures2(List<String> stopterms){


        features.add(Tuple.create("wordsCount", new Double(ExtractorFeature.WordsCount(stopterms, this.text))));
        //features.add(Tuple.create("Author", NGramMeassure.calculateTrigramSim()));
        features.add(Tuple.create("avgCountWordsAtSentence", ExtractorFeature.WordsCountAtSentence(this.text)));
        features.add(Tuple.create("avgCountInterpunctions", ExtractorFeature.CountInterpunctions(this.text)));
        //features.add(Tuple.create("capital", ca);
        features.add(Tuple.create("countSentencesIn30", ExtractorFeature.CountSentences30PercentText(this.text)));
        features.add(Tuple.create("avgBigLetter", ExtractorFeature.AverageBigLetter(this.text)));
        //features.add(Tuple.create("currency", ExtractorFeature.Currency(this.text)));

    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setText(String text) {
        this.text = text;
    }

    //GETTER'Y
    //public int getId() {return id;}

    public String getDate() {
        return date;
    }

    public List<String> getTopics() {
        return topics;
    }

    public List<String> getPlaces() {
        return places;
    }

    public List<String> getPeople() {
        return people;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getText() {
        return text;
    }
}
