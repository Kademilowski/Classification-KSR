package Features;


import Data.Article;
import Measures.Levensthein;
import Measures.NGramMeassure;
import Metrics.Metric;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FeatureVector {


    private Article document;

    public FeatureVector(Article document){
        this.document = document;
    }

    public Article getDocument() {
        return document;
    }


    public static Double calculateDistance(Features c1, Features c2, String metric, String measure, Map<String, Boolean> checked){

        List<Double> numA = new ArrayList<>();
        List<Double> numB = new ArrayList<>();

        Random rand = new Random(25);

        if(checked.get("wordsCount")) {
              numA.add((double) c1.getWordsCount());
              numB.add((double) c2.getWordsCount());
            //numA.add(0.0);
            //numB.add(0.0);
            //numB.add(Math.random()*100000.0);
            //  //System.out.println("wordsCount");
        }

        if(checked.get("wordsCountSentences")) {

            numA.add(c1.getWordsCountAtSentence());
            numB.add(c2.getWordsCountAtSentence());
           /*// numA.add(0.0);
           // numB.add(0.0);
            //System.out.println("wordsCountSentences");
            //numA.add(0.0);
            // numB.add(20.0);
            //numA.add(Math.random()*10000.0);
            //numB.add(Math.random()*10000.0);*/
        }


        if(checked.get("avgBigLetter")){
             numA.add(c1.getAvgBigLetter());
             numB.add(c2.getAvgBigLetter());
           /* //   numA.add((double) c1.getWordsCount());
            //   numB.add((double) c2.getWordsCount());
            numA.add(Math.random()*10000.0);
            //numB.add(0.0);
            numB.add(Math.random()*10000.0);
            // numA.add(0.0);
            // numB.add(20.0);
            //System.out.println("avgBigLetter");*/
        }

        if(checked.get("countSentences30")){
            //   numA.add((double) c1.getWordsCount());
            //   numB.add((double) c2.getWordsCount());
            numA.add(Math.random()*10000.0);
            //numB.add(0.0);
            numB.add(Math.random()*10000.0);
            // numA.add(c1.getCountSentences30());
            // numB.add(c2.getCountSentences30());
            //System.out.println("countSentences30");
            // numA.add(0.0);
            // numB.add(20.0);

        }

        if(checked.get("interpunctions")){
               numA.add((double) c1.getWordsCount());
               numB.add((double) c2.getWordsCount());
            //numA.add(Math.random()*10000.0);
            //numB.add(0.0);
           // numB.add(Math.random()*10000.0);
            //   numA.add(c1.getInterpunctions());
            //  numB.add(c2.getInterpunctions());
            //System.out.println("interpunctions");
            // numA.add(0.0);
            // numB.add(20.0);

        }

        if(measure.equals("Levenshtein")) {
            if (checked.get("capital")) {
                numA.add(0.0);
                //numB.add((double)HammingDistance.hammingDist(c1.getCapital(), c2.getCapital()));
                numB.add((double)Levensthein.Levenshtein(c1.getCapital(), c2.getCapital()));

                //numB.add(BoyerMayer.search(c1.getCapital().toCharArray(),  c2.getCapital().toCharArray()));
                //System.out.println("capital");

            }

            if (checked.get("currency")) {
                numA.add(0.0);
                //numB.add((double)HammingDistance.hammingDist(c1.getCapital(), c2.getCapital()));
                numB.add((double)Levensthein.Levenshtein(c1.getCapital(), c2.getCapital()));
                //numB.add(BoyerMayer.search(c1.getCapital().toCharArray(),  c2.getCapital().toCharArray()));
                //System.out.println("currency");
            }

            if (checked.get("topics")) {
                String Topics1 = c1.getTopics().toString();
                String Topics2 = c2.getTopics().toString();
               // numA.add(0.0);
                //numB.add((double)HammingDistance.hammingDist(c1.getCapital(), c2.getCapital()));
                //numB.add(Levensthein.Levenshtein(c1.getCapital(), c2.getCapital()));

                //numB.add(BoyerMayer.search(c1.getCapital().toCharArray(), c2.getCapital().toCharArray()));
                //System.out.println("currency");
            }

            if (checked.get("authors")) {
                String A1 = c1.getAuthor().toString();
                String A2 = c2.getAuthor().toString();
                numA.add(0.0);
                numB.add(Levensthein.Levenshtein(A1, A2));
            }
        } else{
            if (checked.get("capital")) {

                //   numA.add((double) c1.getWordsCount());
                //   numB.add((double) c2.getWordsCount());
                numA.add(0.0);
                //numB.add(0.0);
                numB.add(Math.random()*10000.0);
                // numA.add(0.0);
                // numB.add(1.0 - NGramMeassure.calculateTrigram(c1.getCapital(), c2.getCapital()));
                    //System.out.println(c1.getCapital() + "   " +  c2.getCapital());
                // numB.add(NGramMeassure.calculateTrigram(c1.getCapital(), c2.getCapital()));
                //System.out.println("capital");

            }

            if (checked.get("currency")) {

                //   numA.add((double) c1.getWordsCount());
                //   numB.add((double) c2.getWordsCount());
               // numA.add(0.0);
                //numB.add(0.0);
                //numB.add(Math.random()*10000.0);
                 numA.add(0.0);
                 numB.add(1.0 - NGramMeassure.calculateTrigram(c1.getCurrency(), c2.getCurrency()));
                // numB.add(NGramMeassure.calculateTrigram(c1.getCurrency(), c2.getCurrency()));
                //System.out.println("currency");
            }

            if (checked.get("topics")) {
                String Topics1 = c1.getTopics().toString();
                String Topics2 = c2.getTopics().toString();
                //   numA.add((double) c1.getWordsCount());
                //   numB.add((double) c2.getWordsCount());
                numA.add(0.0);
                //numB.add(0.0);
                numB.add(Math.random()*10000.0);
                //numA.add(0.0);
                //numB.add(1.0 - NGramMeassure.calculateTrigram(Topics1, Topics2));
                // numB.add(NGramMeassure.calculateTrigram(Topics1, Topics2));
                //System.out.println("currency");
            }

            if (checked.get("authors")) {
                //   numA.add((double) c1.getWordsCount());
                //   numB.add((double) c2.getWordsCount());
                numA.add(0.0);
                //numB.add(0.0);
                numB.add(Math.random()*10000.0);
                String A1 = c1.getAuthor().toString();
                String A2 = c2.getAuthor().toString();
                 // numA.add(0.0);
                 // numB.add(1.0 - NGramMeassure.calculateTrigram(A1, A2));
            }
        }



        if(metric == "Euculidean"){
            //System.out.println(Metric.Euclidean(numA, numB));
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
