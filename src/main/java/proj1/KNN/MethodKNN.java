package proj1.KNN;

import proj1.Data.Article;
import proj1.Measures.Measure;
import proj1.Metrics.Metric;
import proj1.featuresVectors.Cechy;
import proj1.featuresVectors.FeatureVector;
import proj1.featuresVectors.VectorNumberVal;
import proj1.featuresVectors.Feature;

import java.util.*;
//import java.util.stream.Collectors;

public class MethodKNN {


    private List<Feature> vectorList = new ArrayList<Feature>();
    private int k_parameter = 1;
    private float[] learn_test_partition = new float[2];


    //#########################################

        List<Cechy> calculate(Cechy featureVector,
                            List<Cechy> trainingVectors,
                            int numberK,
                            String numericalMetric,
                            String textMetric, Map<String, Boolean> checked) {

        List<VectorNumberVal> calculatedFeatureVectors = new ArrayList<VectorNumberVal>();

        //obliczanie wektorow calculatedFeatureVectors w celu wziecia k najblizszych sasiadow
        for (Cechy it : trainingVectors) {
            calculatedFeatureVectors.add(new VectorNumberVal(it,
                    FeatureVector.calculateDistance(featureVector, it, numericalMetric,
                            textMetric, checked)));
        }

        //te ktore sa najlbizej
        Collections.sort(calculatedFeatureVectors);
        //ukrucenie do k najblizszych sasiadow
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();


        List<Cechy> features = new ArrayList<Cechy>();

        //zwrócenie tych wektorów
        for(VectorNumberVal it : calculatedFeatureVectors){
            features.add(it.getFeatureVector());
        }

        return features;


    }


    //przekazanie listy dokumentow
    String classify(List<Article> documents) {
        Map<String, Integer> placesOccurrences = new LinkedHashMap<>();


        // 6 panstw w dla ktorych dokonywany jest przydzial
        placesOccurrences.put("west-germany", 0);
        placesOccurrences.put("usa", 0);
        placesOccurrences.put("france", 0);
        placesOccurrences.put("uk", 0);
        placesOccurrences.put("canada", 0);
        placesOccurrences.put("japan", 0);

        //zliczenie ilosci wystapien dla kazdego panstwa
        for(int i=0; i<documents.size(); i++){
            String place = documents.get(i).getPlaces().get(0);
            placesOccurrences.put(place, placesOccurrences.get((place))+1);
        }

        //Map.Entry<String, Integer> maksimum = placesOccurrences.entrySet().iterator().next();

        // ????????
        Map.Entry<String, Integer> maksimum  = placesOccurrences.entrySet().iterator().next();


        for(Map.Entry<String, Integer> it : placesOccurrences.entrySet()){
            if(it.getValue() > maksimum.getValue()){
                maksimum = it;
            }
        }
        //zwrocenie jaczestszego panstwa na podstawie najblizszych saisadow olbiczoncych w calculate
        return maksimum.getKey();


        /*return placesOccurrences.entrySet()
                .stream()
                .max((val1, val2) -> val1.getValue() > val2.getValue() ? 1 : -1)
                .get()
                .getKey();*/
    }



    public String calculateAndClassify(Cechy featureVector,
                                       List<Cechy> trainingVectors,
                                       int numberK,
                                       String numericalMetric,
                                       String textMetric, Map<String, Boolean> checked) {

        List<Cechy> selectedVectors = new ArrayList<Cechy>();
        selectedVectors = calculate(featureVector, trainingVectors, numberK, numericalMetric, textMetric, checked);



        List<Article> articles = new ArrayList<Article>();

        for(Cechy it : selectedVectors){
            articles.add(it.getArticle());
        }

        //tu zmienic na clasified albo przypisać
        String classifiedPlace = classify(articles);

        return classifiedPlace;

        /*String classifiedPlace = classify(selectedVectors
                .stream()
                .map((it) -> it.getText())
                .collect(Collectors.toCollection(ArrayList::new)));

        return classifiedPlace;*/
    }





    //TO JEST OKK ####################################


   /* List<FeatureVector> calculate(List<Feature> featureVector,
                                  List<List<Feature>> trainingVectors,
                                  int numberK,
                                  String numericalMetric,
                                  String textMetric) {

        List<VectorNumberVal> calculatedFeatureVectors = new ArrayList<VectorNumberVal>();

        //obliczanie wektorow calculatedFeatureVectors w celu wziecia k najblizszych sasiadow
        for (List<Feature> it : trainingVectors) {
            calculatedFeatureVectors.add(new VectorNumberVal(it,
                    FeatureVector.calculateDistance(featureVector, it, numericalMetric,
                            textMetric)));
        }

        //te ktore sa najlbizej
        Collections.sort(calculatedFeatureVectors);
        //ukrucenie do k najblizszych sasiadow
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();


        List<FeatureVector> features = null;

        //zwrócenie tych wektorów
        for(VectorNumberVal it : calculatedFeatureVectors){
            features.add(it.getFeatureVector());
        }

        return features;


    }


    //przekazanie listy dokumentow
    String classify(List<Article> documents) {
        Map<String, Integer> placesOccurrences = new LinkedHashMap<>();


        // 6 panstw w dla ktorych dokonywany jest przydzial
        placesOccurrences.put("west-germany", 0);
        placesOccurrences.put("usa", 0);
        placesOccurrences.put("france", 0);
        placesOccurrences.put("uk", 0);
        placesOccurrences.put("canada", 0);
        placesOccurrences.put("japan", 0);

        //zliczenie ilosci wystapien dla kazdego panstwa
        for(int i=0; i<documents.size(); i++){
            String place = documents.get(i).getPlaces().get(0);
            placesOccurrences.put(place, placesOccurrences.get((place))+1);
        }

        //Map.Entry<String, Integer> maksimum = placesOccurrences.entrySet().iterator().next();

        // ????????
        Map.Entry<String, Integer> maksimum  = (Map.Entry<String, Integer>) placesOccurrences.keySet().toArray()[0];


        for(int i = 1; i < placesOccurrences.size(); i++){
            Map.Entry<String, Integer> tmp  = (Map.Entry<String, Integer>) placesOccurrences.keySet().toArray()[i];
            if(maksimum.getValue() < tmp.getValue()){
                maksimum = tmp;
            }

        }
        //zwrocenie jaczestszego panstwa na podstawie najblizszych saisadow olbiczoncych w calculate
        return maksimum.getKey();


        *//*return placesOccurrences.entrySet()
                .stream()
                .max((val1, val2) -> val1.getValue() > val2.getValue() ? 1 : -1)
                .get()
                .getKey();*//*
    }



    public String calculateAndClassify(FeatureVector featureVector,
                                       List<FeatureVector> trainingVectors,
                                       int numberK,
                                       String numericalMetric,
                                       String textMetric) {

        List<FeatureVector> selectedVectors = calculate(featureVector, trainingVectors, numberK, numericalMetric, textMetric);



        List<Article> articles = new ArrayList<Article>();

        for(FeatureVector it : selectedVectors){
            articles.add(it.getDocument());
        }

        //tu zmienic na clasified albo przypisać
        String classifiedPlace = classify(articles);

        return classifiedPlace;

        *//*String classifiedPlace = classify(selectedVectors
                .stream()
                .map((it) -> it.getText())
                .collect(Collectors.toCollection(ArrayList::new)));

        return classifiedPlace;*//*
    }*/

    //TO JEST OKK ####################################



   /* List<FeatureVector> calculate(FeatureVector featureVector,
                                  List<FeatureVector> trainingVectors,
                                  int numberK,
                                  String numericalMetric,
                                  String textMetric) {

        List<VectorNumberVal> calculatedFeatureVectors = new ArrayList<VectorNumberVal>();

        //obliczanie wektorow calculatedFeatureVectors w celu wziecia k najblizszych sasiadow
        for (FeatureVector it : trainingVectors) {
            calculatedFeatureVectors.add(new VectorNumberVal(it,
                    FeatureVector.calculateDistance(featureVector, it, numericalMetric,
                            textMetric)));
        }

        //te ktore sa najlbizej
        Collections.sort(calculatedFeatureVectors);
        //ukrucenie do k najblizszych sasiadow
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();


        List<FeatureVector> features = null;

        //zwrócenie tych wektorów
        for(VectorNumberVal it : calculatedFeatureVectors){
            features.add(it.getFeatureVector());
        }

        return features;


    }*/





}
