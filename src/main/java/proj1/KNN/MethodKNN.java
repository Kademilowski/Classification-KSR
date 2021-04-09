package proj1.KNN;

import proj1.Data.Article;
import proj1.Measures.Measure;
import proj1.Metrics.Metric;
import proj1.featuresVectors.FeatureVector;
import proj1.featuresVectors.VectorNumberVal;
import proj1.featuresVectors.Feature;

import java.util.*;
//import java.util.stream.Collectors;

public class MethodKNN {


    private List<Feature> vectorList = new ArrayList<Feature>();
    private int k_parameter = 1;
    private float[] learn_test_partition = new float[2];

    public MethodKNN(int k_parameter,float[] learn_test_partition){
        this.k_parameter = k_parameter;
        this.learn_test_partition = learn_test_partition;
    }


    
    //#########################################

    List<FeatureVector> calculate(FeatureVector featureVector,
                            List<FeatureVector> trainingVectors,
                            int numberK,
                            String numericalMetric,
                            String textMetric) {

        List<VectorNumberVal> calculatedFeatureVectors = new ArrayList<VectorNumberVal>();

        for (FeatureVector it : trainingVectors) {
            calculatedFeatureVectors.add(new VectorNumberVal(it,
                    FeatureVector.calculateDistance(featureVector, it, numericalMetric,
                            textMetric)));
        }

        Collections.sort(calculatedFeatureVectors);
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();


        List<FeatureVector> features = null;


        for(VectorNumberVal it : calculatedFeatureVectors){
            features.add(it.getFeatureVector());

        }

        return features;

        //po co to collectowane ???
       /* return calculatedFeatureVectors.stream()
                .map((it) -> it.getFeatureVector())
                .collect(Collectors.toCollection(ArrayList::new));*/
    }

    String classify(List<Article> documents) {
        Map<String, Integer> placesOccurrences = new LinkedHashMap<>();
        placesOccurrences.put("west-germany", 0);
        placesOccurrences.put("usa", 0);
        placesOccurrences.put("france", 0);
        placesOccurrences.put("uk", 0);
        placesOccurrences.put("canada", 0);
        placesOccurrences.put("japan", 0);


        for(int i=0; i<documents.size(); i++){
            String place = documents.get(i).getPlaces().get(0);
            placesOccurrences.put(place, placesOccurrences.get((place))+1);
        }

        //Map.Entry<String, Integer> maksimum = placesOccurrences.entrySet().iterator().next();
        Map.Entry<String, Integer> maksimum  = (Map.Entry<String, Integer>) placesOccurrences.keySet().toArray()[0];


        for(int i = 1; i < placesOccurrences.size(); i++){
            Map.Entry<String, Integer> tmp  = (Map.Entry<String, Integer>) placesOccurrences.keySet().toArray()[i];
            if(maksimum.getValue() < tmp.getValue()){
                maksimum = tmp;
            }


        }
        return maksimum.getKey();


        /*return placesOccurrences.entrySet()
                .stream()
                .max((val1, val2) -> val1.getValue() > val2.getValue() ? 1 : -1)
                .get()
                .getKey();*/
    }



    public String calculateAndClassify(FeatureVector featureVector,
                                       List<FeatureVector> trainingVectors,
                                       int numberK,
                                      String numericalMetric,
                                       String textMetric) {

        List<FeatureVector> selectedVectors = calculate(featureVector,
                trainingVectors, numberK, numericalMetric, textMetric);



        List<Article> articles = new ArrayList<Article>();

        for(FeatureVector it : selectedVectors){
            articles.add(it.getDocument());
        }

        String classifiedPlace = classify(articles);

        return classifiedPlace;

        /*String classifiedPlace = classify(selectedVectors
                .stream()
                .map((it) -> it.getText())
                .collect(Collectors.toCollection(ArrayList::new)));

        return classifiedPlace;*/
    }








}
