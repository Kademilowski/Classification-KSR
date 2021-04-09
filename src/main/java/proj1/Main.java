package proj1;

import proj1.Data.Article;
import proj1.Data.Document;
import proj1.Data.ReaderDocument;
import proj1.GUI.WindowK;
import proj1.featuresVectors.ExtractorFeature;
import proj1.featuresVectors.FeatureExtractorDecorator;
import proj1.featuresVectors.FeatureVector;

import javax.swing.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;


public class Main {


    private static List<Article> documents;
    private static List<Article> trainingDocuments;
    private static List<Article> testDocuments;

    private static  List<FeatureVector>trainingFeatureVectors;
    private static  List<FeatureVector>testFeatureVectors;
    private static FeatureExtractorDecorator extractorDecorator;
    private static Map<String, Map<String, Integer>> classification;

    private static void DividedIntoTwoSets(int ratioTest) {

        Random random = new Random(35);
        final int testSetSize = documents.size() * (100 - ratioTest) / 100;


        //Stream<String> documentsStream = Stream.of(documents);

        // action(() -> {
        /*----- MAKE A COPY OF DOCUMENTS -----*/
        testDocuments = new ArrayList<>();
        trainingDocuments = documents;
        //trainingDocuments = documents.stream().collect(Collectors.toCollection(ArrayList::new));

        /*----- MOVE RANDOM ITEMS TO TEST LIST -----*/
        for (int i = 0; i < testSetSize; i++) {
            int number = random.nextInt(trainingDocuments.size());
            testDocuments.add(trainingDocuments.get(number));
            trainingDocuments.remove(number);
            //      }
            // }, "Dividing into two lists");

        }
    }




    private static void extractFeatures() {
        trainingFeatureVectors = new ArrayList<>();
        testFeatureVectors = new ArrayList<>();

       // final FeatureExtractorAbbreviationResolver featureExtractorAbbreviationResolver =
       //         new FeatureExtractorAbbreviationResolver(keywordsSets.get(0), keywordsSets.get(1));
        extractorDecorator = new FeatureExtractorDecorator();
        for (final String abbr : featureExtractorAbbreviations) {
            extractorDecorator.addExtractor(featureExtractorAbbreviationResolver.resolveAbbreviation(abbr));
        }

        //action(() -> {
            trainingFeatureVectors
                    .addAll(trainingDocuments.stream()
                            .map(extractorDecorator::extract)
                            .collect(Collectors.toList()));

            testFeatureVectors
                    .addAll(testDocuments.stream()
                            .map(extractorDecorator::extract)
                            .collect(Collectors.toList()));
      //  }, "Features extracting");

       // action(() -> {
            FeatureVector.normalize(trainingFeatureVectors);
            FeatureVector.normalize(testFeatureVectors);
      //  }, "Normalize feature vectors");
    }

    private static void knnClassification(int numberK, String numericalMetric,
                                          String textMetric) {
        //action(() -> {
            classification = new HashMap<>();
            for (FeatureVector it : testFeatureVectors) {
                final String properPlace = it.getDocument().getPlaces().get(0);
                final String recognizedPlace = knnAlgorithm
                        .calculateAndClassify(it, trainingFeatureVectors, numberK,
                                numericalMetric, textMetric);
                if (!classification.containsKey(properPlace)) {
                    classification.put(properPlace, new HashMap<>());
                }
                classification.get(properPlace)
                        .put(recognizedPlace, classification.get(properPlace)
                                .getOrDefault(recognizedPlace, 0) + 1);
            }

       // }, "kNN");
    }




    public static void main(String[] args) {


        JFrame frame = new JFrame("Projekt 1 - Klasyifkacja k-NN");
        frame.setContentPane(new WindowK().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //zmienić ścieżke na względną
        final File folder = new File("D:\\Kamil\\Studia\\semestr_6\\KOMPUTEROWE_SYSTEMY_ROZPOZNAWANIA\\Laboratorium\\Projekt1\\src\\main\\java\\resources\\documents");





        ArrayList<Article> documents = new ArrayList<Article>();
        Document doc = new Document();

        try{
            doc.listFilesForFolder(folder);
        } catch(IOException IOException){
            System.out.println(IOException);
        }

        for(String text : doc.articles){
            documents.add(new Article(1, ReaderDocument.GetDate(text),ReaderDocument.GetTopics(text),
            ReaderDocument.GetPlaces(text), ReaderDocument.GetPeople(text), ReaderDocument.GetTitle(text),
                    ReaderDocument.GetAuthors(text), ReaderDocument.GetText(text)));
        }

        System.out.println(documents.get(854).getTopics());
        System.out.println(documents.get(854).getTitle());

        int  e = 1;
        for(Article it : documents){
            System.out.println(ExtractorFeature.Currency(it.getText()) + " " + e);
            e++;
        }


        for(Article it : documents){
            for(String it2 : it.getAuthors()){
                System.out.println(ExtractorFeature.AuthorName(it2 + " " + e));
            }

            e++;
        }


        String[] z = new String[]{"w", "we"};
        //System.out.println(ExtractorV.WordsCount( z, documents.get(1).getText()));
        //System.out.println(ExtractorV.WordsCountAtSentence(documents.get(1).getText()));
        //System.out.println(ExtractorV.Currency(documents.get(854).getText()));



}
