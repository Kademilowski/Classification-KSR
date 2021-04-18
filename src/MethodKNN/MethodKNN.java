package MethodKNN;



import Data.Article;
import Features.Features;
import Features.FeatureVector;
import Features.VectorNumberVal;

import java.util.*;
//import java.util.stream.Collectors;

public class MethodKNN {



    //#########################################

    List<Features> calculate(Features featureVector,
                             List<Features> trainingVectors,
                             int numberK,
                             String numericalMetric,
                             String textMetric, Map<String, Boolean> checked) {

        List<VectorNumberVal> calculatedFeatureVectors = new ArrayList<VectorNumberVal>();



        //obliczanie wektorow calculatedFeatureVectors w celu wziecia k najblizszych sasiadow
        for (Features it : trainingVectors) {
            calculatedFeatureVectors.add(new VectorNumberVal(it,
                    FeatureVector.calculateDistance(featureVector, it, numericalMetric,
                            textMetric, checked)));

        }

        //te ktore sa najlbizej
        Collections.sort(calculatedFeatureVectors);
        //ukrucenie do k najblizszych sasiadow
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();


        List<Features> features = new ArrayList<Features>();


        //zwrócenie tych wektorów
        for(VectorNumberVal it : calculatedFeatureVectors){
            features.add(it.getFeatureVector());
           // System.out.println(it.getDistance());

        }

        return features;


    }


    //przekazanie listy dokumentow
    String classify(List<Article> documents) {
        Map<String, Integer> placesOccurrences = new LinkedHashMap<>();


        // 6 panstw w dla ktorych dokonywany jest przydzial
        placesOccurrences.put("uk", 0);
        placesOccurrences.put("usa", 0);
        placesOccurrences.put("france", 0);
        placesOccurrences.put("west-germany", 0);
        placesOccurrences.put("canada", 0);
        placesOccurrences.put("japan", 0);

        //zliczenie ilosci wystapien dla kazdego panstwa
        for(int i=0; i<documents.size(); i++){
            int count = 0;
            String place = documents.get(i).getPlaces().get(0);
            //System.out.println(place);
            if(place.equals("usa")){
                count = placesOccurrences.get("usa") + 1;
                placesOccurrences.put("usa", count);
            }
            else if(place.equals("uk")){
                count = placesOccurrences.get("uk") + 1;
                placesOccurrences.put("uk", count);
            }
            else if(place.equals("france")){
                count = placesOccurrences.get("france") + 1;
                placesOccurrences.put("france", count);
            }
            else if(place.equals("west-germany")){
                count = placesOccurrences.get("west-germany") + 1;
                placesOccurrences.put("west-germany", count);
            }
            else if(place.equals("canada")){
                count = placesOccurrences.get("canada") + 1;
                placesOccurrences.put("canada", count);
            }
            else if(place.equals("japan")){
                count = placesOccurrences.get("japan") + 1;
                placesOccurrences.put("japan", count);
            }

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


    }



    public String Classification(Features featureVector,
                                 List<Features> trainingVectors,
                                 int numberK,
                                 String numericalMetric,
                                 String textMetric, Map<String, Boolean> checked) {

        List<Features> selectedVectors = new ArrayList<Features>();
        selectedVectors = calculate(featureVector, trainingVectors, numberK, numericalMetric, textMetric, checked);


        List<Article> articles = new ArrayList<Article>();

        for(Features it : selectedVectors){
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









}
