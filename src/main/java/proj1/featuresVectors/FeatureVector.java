package proj1.featuresVectors;

import com.ibm.icu.util.MeasureUnit;
import proj1.Data.Article;
import proj1.Data.Document;
import proj1.Measures.Measure;
import proj1.Metrics.Metric;
import proj1.Measures.NGramMeassure;

import java.util.ArrayList;
import java.util.List;

public class FeatureVector {


    private Article document;

    public FeatureVector(Article document){
        this.document = document;
    }

    public Article getDocument() {
        return document;
    }

    // ???????????????????
    /*public static void normalize(List<FeatureVector> vectors) {
        double[] maxs = new double[vectors.get(0).size()];
        for (int i = 0; i < vectors.get(0).size(); i++) {
            if (vectors.get(0).get(i).isNumber()) {
                final int index = i;


               *//* maxs[i] = vectors.stream()
                        .mapToDouble(vector -> vector.get(index).getNumber())
                        .max().getAsDouble();*//*
            }
        }
        for (FeatureVector vector : vectors) {
            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i).isNumber()) {
                    vector.get(i).setNumber(vector.get(i).getNumber() / maxs[i]);
                }
            }
        }
    }*/


    public static Double calculateDistance(Cechy c1, Cechy c2, String metric, String measure){

        List<Double> numA = new ArrayList<>();
        List<Double> numB = new ArrayList<>();

        numA.add((double) c1.getWordsCount());
        numA.add(c1.getWordsCountAtSentence());
        numA.add(c1.getAvgBigLetter());
        numA.add(c1.getCountSentences30());
        numA.add(c1.getInterpunctions());

        numB.add((double) c2.getWordsCount());
        numB.add(c2.getWordsCountAtSentence());
        numB.add(c2.getAvgBigLetter());
        numB.add(c2.getCountSentences30());
        numB.add(c2.getInterpunctions());

        if(metric == "Euculidean"){
            return Metric.Euclidean(numA, numB);
        }
        else if(metric == "Chebyschev"){
            return Metric.Chebyshev(numA, numB);
        }
        else if(metric == "Manhattan"){
            return Metric.Manhattan(numA, numB);
        }
        else{
            return 0.0;
        }

    }



    //TO JEST OOKKKKK #######################
    /*
    public static Double calculateDistance(List<Feature> c1, List<Feature> c2, String metric, String measure){

        List<Double> numA = new ArrayList<>();
        List<Double> numB = new ArrayList<>();

        for(int i =0; i< c1.size(); i++){

            if(c1.get(i).isNumber()){ //sprawdzenie czy i-ta skladowa cechy jest numerem
                numA.add(c1.get(i).getNumber());
                numB.add(c2.get(i).getNumber());
            } else{
                double similarity = 0.0;

                if("Ngram" == measure){
                    similarity = NGramMeassure.calculateTrigramSim(c1.get(i).getText(), c2.get(i).getText());
                }
                JaccardaMeasure

                numA.add(0.0);
                numB.add(1.0 - similarity);
            }
             }

                if(metric == "Euculidean"){
                return Metric.Euclidean(numA, numB);
                }
                else if(metric == "Chebyschev"){
                return Metric.Chebyshev(numA, numB);
                }
                else if(metric == "Manhattan"){
                return Metric.Manhattan(numA, numB);
                }
                else{
                return 0.0;
                }

                }
     */
}
