package proj1.featuresVectors;

import com.ibm.icu.util.MeasureUnit;
import proj1.Data.Article;
import proj1.Data.Document;
import proj1.Measures.Measure;
import proj1.Metrics.Metric;
import proj1.Measures.NGramMeassure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public static Double calculateDistance(Cechy c1, Cechy c2, String metric, String measure, Map<String, Boolean> checked){

        List<Double> numA = new ArrayList<>();
        List<Double> numB = new ArrayList<>();



        if(checked.get("wordsCount")) {
            numA.add((double) c1.getWordsCount());
            numB.add((double) c2.getWordsCount());

            //System.out.println("wordsCount");
        }

        if(checked.get("wordsCountSentences")) {
            numA.add(c1.getWordsCountAtSentence());
            numB.add(c2.getWordsCountAtSentence());
            //System.out.println("wordsCountSentences");
        }


        if(checked.get("avgBigLetter")){
            numA.add(c1.getAvgBigLetter());
            numB.add(c2.getAvgBigLetter());
            //System.out.println("avgBigLetter");
        }

        if(checked.get("countSentences30")){
            numA.add(c1.getCountSentences30());
            numB.add(c2.getCountSentences30());
            //System.out.println("countSentences30");

        }

        if(checked.get("interpunctions")){
            numA.add(c1.getInterpunctions());
            numB.add(c2.getInterpunctions());
            //System.out.println("interpunctions");

        }

        if(checked.get("capital")){
            numA.add(0.0);
            numB.add(1.0 - NGramMeassure.calculateTrigramSim(c1.getCapital(), c2.getCapital()));
            //System.out.println("capital");

        }

        if(checked.get("currency")){
            numA.add(0.0);
            numB.add(1.0 - NGramMeassure.calculateTrigramSim(c1.getCurrency(), c2.getCurrency()));
            //System.out.println("currency");
        }

        if(checked.get("topics")){
            String Topics1 = c1.getTopics().toString();
            String Topics2 = c2.getTopics().toString();
            numA.add(0.0);
            numB.add(1.0 - NGramMeassure.calculateTrigramSim(Topics1, Topics2));
            //System.out.println("currency");
        }

       // numA.add(0.0);









       // String c1Author = c1.getAuthor().toString();
        //String c2Author = c2.getAuthor().toString();
        //numB.add(1.0 - NGramMeassure.calculateTrigramSim(c1Author, c2Author));


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


}
