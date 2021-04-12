package proj1;

import proj1.Data.Article;
import proj1.Data.Classified;
import proj1.Data.Document;
import proj1.Data.ReaderDocument;
import proj1.GUI.WindowK;
import proj1.KNN.MethodKNN;
import proj1.featuresVectors.*;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.io.IOException;


public class Main {


    private static List<Article> documents = new ArrayList<Article>();
    private static List<Article> trainingDocuments = new ArrayList<Article>();
    private static List<Article> testDocuments = new ArrayList<Article>();

    private static List<Cechy>trainingVectors = new ArrayList<Cechy>();
    private static List<Cechy>testVectors = new ArrayList<Cechy>();

    private static List<Classified> classifiedDocuemnts = new ArrayList<Classified>();
    static Map<String, Boolean> checkedFeatures = new LinkedHashMap<>();




    private static List<String> places =  Arrays.asList("west-germany, japan, france, uk, usa, canada");
    private static List<String> stopterms =  Arrays.asList("I' am", "hello");

    private static MethodKNN knnAlgorithm = new MethodKNN();

    private static List<Classified> documentsClassied = new ArrayList<Classified>();

    private static double accuracy = 0.0;



    public static void SetsDivision(int ratioTest) {

        Random random = new Random(35);
        final int testSetSize = documents.size() * (100 - ratioTest) / 100;


        // action(() -> {
        /*----- MAKE A COPY OF DOCUMENTS -----*/
        testDocuments = new ArrayList<>();
        trainingDocuments = new ArrayList<>(documents);
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

    public static void RemoveOtherDocuments(List<Article> article, List<String> places) {

        List<Article> tmp = new ArrayList<Article>();

        for (Article it : article) {
            if (it.getPlaces().size() == 1) {
                for (String p1 : it.getPlaces()) {
                    for (String p2 : places) {
                        if (p2.contains(p1)) {
                            tmp.add(it);

                        }

                    }

                }
            }

        }

       documents =  tmp;
    }


    public static void extracionFeatures(){

        //funkcja ktora ekstrachuje cechy z dokeumntow czyli przakzuje liste albo poejdyczny dokument
        for(Article it : trainingDocuments){
            trainingVectors.add(new Cechy(it, stopterms));
        }


        for(Article it : testDocuments){
            testVectors.add(new Cechy(it, stopterms));

        }

        for(Cechy it : trainingVectors){
            it.setFeatures();
        }

        for(Cechy it : testVectors){
            it.setFeatures();
        }

        for(Cechy it : trainingVectors){
            it.setFeatures();
        }


    }


    public static void methodKNN(int Knumber, String metric, String textMeasure, Map<String, Boolean> checked){


        for(Cechy it : testVectors){
            String correctPlace = it.getArticle().getPlaces().get(0);
            String classifyPlace;

            if(knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked) == null){
                classifyPlace = "";
            }else{
                classifyPlace = knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked);
            }


            classifiedDocuemnts.add(new Classified(it.getArticle(), classifyPlace, correctPlace));
        }

    }


    public static void Statistics(){
        //clasified
        Map<String, Integer> countries = new LinkedHashMap<>();
        //Pozytywnie zaklasyfiowane
        Map<String, Integer> countriesCorrect = new LinkedHashMap<>();
        //Domyslne
        Map<String, Integer> countriesDefault = new LinkedHashMap<>();

        //Precision
        Map<String, Double> precisions = new LinkedHashMap<>();
        //Recall
        Map<String, Double> recalls = new LinkedHashMap<>();
        //F1
        Map<String, Double> f1 = new LinkedHashMap<>();


        countries.put("usa", 0);
        countries.put("uk", 0);
        countries.put("japan", 0);
        countries.put("canada", 0);
        countries.put("west-germany", 0);
        countries.put("france", 0);


        countriesCorrect.put("usa", 0);
        countriesCorrect.put("uk", 0);
        countriesCorrect.put("japan", 0);
        countriesCorrect.put("canada", 0);
        countriesCorrect.put("west-germany", 0);
        countriesCorrect.put("france", 0);

        countriesDefault.put("usa", 0);
        countriesDefault.put("uk", 0);
        countriesDefault.put("japan", 0);
        countriesDefault.put("canada", 0);
        countriesDefault.put("west-germany", 0);
        countriesDefault.put("france", 0);

        Integer countDefault = null;
        Integer countCorrect = null;
        Integer count = null;

        for(Classified it : classifiedDocuemnts){

            countDefault = countriesDefault.get(it.getOrginalCategory());
            countCorrect = countriesCorrect.get(it.getCategory());
            count = countries.get(it.getCategory());

            countries.put(it.getCategory(), ++count);

            countriesDefault.put(it.getOrginalCategory(), ++countDefault);
            System.out.println("nieorginal: " + it.getCategory() + "   orginal: " + it.getOrginalCategory());
            //Pozytywnie to nie dziala
            if(it.getCategory().equals(it.getOrginalCategory())){
                countriesCorrect.put(it.getOrginalCategory(), ++countCorrect);
            }



        }

        //recall = (double)countriesCorrect.get("usa");
        //recall = recall / ((1.0)*(double)countriesDefault.get("usa"));

        //precision = (double)countriesCorrect.get("usa");
        //precision = precision / (double)countries.get("usa");

        System.out.println("Klasyfikowanie");
        for(Map.Entry<String, Integer> it : countries.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }
        System.out.println("Domyslnie");
        for(Map.Entry<String, Integer> it : countriesDefault.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());
        }

        System.out.println("Pozytwnie zaklasyfikowane");
        System.out.println();
        for(Map.Entry<String, Integer> it : countriesCorrect.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());
            accuracy = accuracy + it.getValue();

        }

        recalls.put("usa",(double)countriesCorrect.get("usa")/((1.0)*(double)countriesDefault.get("usa")));
        recalls.put("uk",(double)countriesCorrect.get("uk")/((1.0)*(double)countriesDefault.get("uk")));
        recalls.put("japan",(double)countriesCorrect.get("japan")/((1.0)*(double)countriesDefault.get("japan")));
        recalls.put("canada",(double)countriesCorrect.get("canada")/((1.0)*(double)countriesDefault.get("canada")));
        recalls.put("west-germany",(double)countriesCorrect.get("west-germany")/((1.0)*(double)countriesDefault.get("west-germany")));
        recalls.put("france",(double)countriesCorrect.get("france")/((1.0)*(double)countriesDefault.get("france")));

        precisions.put("usa",(double)countriesCorrect.get("usa")/((1.0)*(double)countries.get("usa")));
        precisions.put("uk",(double)countriesCorrect.get("uk")/((1.0)*(double)countries.get("uk")));
        precisions.put("japan",(double)countriesCorrect.get("japan")/((1.0)*(double)countries.get("japan")));
        precisions.put("canada",(double)countriesCorrect.get("canada")/((1.0)*(double)countries.get("canada")));
        precisions.put("west-germany",(double)countriesCorrect.get("west-germany")/((1.0)*(double)countries.get("west-germany")));
        precisions.put("france",(double)countriesCorrect.get("france")/((1.0)*(double)countries.get("france")));

        f1.put("usa",2.0*precisions.get("usa")*recalls.get("usa")/(precisions.get("usa") + recalls.get("usa")));
        f1.put("uk", 2.0*precisions.get("uk")*recalls.get("uk")/(precisions.get("uk")+recalls.get("uk")));
        f1.put("japan",2.0*precisions.get("japan")*recalls.get("japan")/(precisions.get("japan")+recalls.get("japan")));
        f1.put("canada",2.0*precisions.get("canada")*recalls.get("canada")/(precisions.get("canada")+recalls.get("canada")));
        f1.put("west-germany",2.0*precisions.get("west-germany")*recalls.get("west-germany")/(precisions.get("west-germany") + recalls.get("west-germany")));
        f1.put("france",2.0*precisions.get("france")*recalls.get("france")/(precisions.get("france")+recalls.get("france")));

       // double precision = 0.0;
       // double recall = 0.0;
        //double f1 = 0.0;

        accuracy = accuracy / classifiedDocuemnts.size();
        //recall =
        System.out.println("F1");
        for(Map.Entry<String, Double> it : f1.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }

        System.out.println("PRECISIONS");
        for(Map.Entry<String, Double> it : precisions.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }


        System.out.println("Recalls");
        for(Map.Entry<String, Double> it : recalls.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }

        //System.out.println("default " + countDefault);
        //System.out.println("CC " + countCorrect);
        //System.out.println("clasified " + count);
        //System.out.println(classifiedDocuemnts.size());
        System.out.println(accuracy);
        //System.out.println(precision);
        //System.out.println(recall);
        //System.out.println(f1);


    }





    public static void main(String[] args) {


        JFrame frame = new JFrame("Projekt 1 - Klasyifkacja k-NN");
        frame.setContentPane(new WindowK().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        final File folder = new File("D:\\Kamil\\Studia\\semestr_6\\KOMPUTEROWE_SYSTEMY_ROZPOZNAWANIA\\Laboratorium\\Projekt1\\src\\main\\java\\resources\\documents");
        Document doc = new Document();

        try {
            doc.listFilesForFolder(folder);
        } catch (IOException IOException) {
            System.out.println(IOException);
        }

        for (String text : doc.articles) {
            documents.add(new Article(ReaderDocument.GetDate(text), ReaderDocument.GetTopics(text),
                    ReaderDocument.GetPlaces(text), ReaderDocument.GetPeople(text), ReaderDocument.GetTitle(text),
                    ReaderDocument.GetAuthors(text), ReaderDocument.GetText(text)));


        }





       // RemoveOtherDocuments(documents, places);
        RemoveOtherDocuments(documents, places);


        for(Article it : documents){
            System.out.println("eee " + it.getPlaces());
        }
        System.out.println(documents.size());
        SetsDivision(60);
        System.out.println(documents.size());
        System.out.println(trainingDocuments.size());
        System.out.println(testDocuments.size());

        extracionFeatures();
        methodKNN(12, "Euculidean", "sdsd", checkedFeatures);
        Statistics();


        //System.out.println(documents2.size());

        //zmienić ścieżke na względną



       /* documents = new ArrayList<Article>();
        Document doc = new Document();

        try {
            doc.listFilesForFolder(folder);
        } catch (IOException IOException) {
            System.out.println(IOException);
        }

        for (String text : doc.articles) {
            documents.add(new Article(1, ReaderDocument.GetDate(text), ReaderDocument.GetTopics(text),
                    ReaderDocument.GetPlaces(text), ReaderDocument.GetPeople(text), ReaderDocument.GetTitle(text),
                    ReaderDocument.GetAuthors(text), ReaderDocument.GetText(text)));
        }

        System.out.println(documents.get(854).getTopics());
        System.out.println(documents.get(854).getTitle());

        int e = 1;
        for (Article it : documents) {
            System.out.println(ExtractorFeature.Currency(it.getText()) + " " + e);
            e++;
        }


        for (Article it : documents) {
            System.out.println(ExtractorFeature.AuthorName(it.getAuthors()));

            e++;
        }


        String[] z = new String[]{"w", "we"};
        //System.out.println(ExtractorV.WordsCount( z, documents.get(1).getText()));
        //System.out.println(ExtractorV.WordsCountAtSentence(documents.get(1).getText()));
        //System.out.println(ExtractorV.Currency(documents.get(854).getText()));*/

    }

}
