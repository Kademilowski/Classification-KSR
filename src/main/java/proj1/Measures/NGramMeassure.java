
package proj1.Measures;

import proj1.Data.Article;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NGramMeassure extends Measure {


    public static Double calculateTrigram(String a, String b){
        final int n = 3;



        /* retrieve ngrams */
        Set<String> ngramsA = new HashSet<>();
        Set<String> ngramsB = new HashSet<>();
        for (int i = 0; i < a.length() - n + 1; i++) {
            ngramsA.add(a.substring(i, i + 3));
        }
        for (int i = 0; i < b.length() - n + 1; i++) {
            ngramsB.add(b.substring(i, i + 3));
        }

        /* calculate similarity */
        double sum = 0.0;
        for (String ngramA : ngramsA) {
            if (ngramsB.contains(ngramA)) {
                sum += 1.0;
            }
        }

        if(ngramsA.size()==0 || ngramsB.size()==0){
            return 0.0;
        }


        //System.out.println(sum / ngramsA.size());

        return sum / ngramsA.size();
    }








}

