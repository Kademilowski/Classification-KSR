
package proj1.Measures;

import proj1.Data.Article;
import proj1.KNN.Classifiable;

import java.util.HashSet;
import java.util.Set;

public class NGramMeassure extends Measure {

    private Article reference = null;

    public Classifiable getReference()
    {
        //return reference;
        return null;
    }


    //TO CHYBA RACZEJ
    public static Double calculateTrigramSim(String a, String b){
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
        return sum / ngramsA.size();
    }





    public void setReference(Classifiable aReference)
    {
        reference = (Article)aReference;
    }

    public int compare(Classifiable text1, Classifiable text2)
    {
        //Double similarity1 = calculateNGramSimilarityForStrings(3, ((Text)reference).getWords(), ((Text)text1).getWords());
       // Double similarity2 = calculateNGramSimilarityForStrings(3, ((Text)reference).getWords(), ((Text)text2).getWords());

        Double similarity1 = 1.0;
        Double similarity2 = 1.0;

        if(similarity1 < similarity2)
        {
            return -1;
        }
        else if(similarity1 > similarity2)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }




    //Porównanie pojednyczych wyrazow
    public Double calculateNGramSimilarForWords(Integer n, String w1, String w2){

        if(w1.length() < n || w2.length() < n)
        {
            return 0.0;
        }

        Integer count = 0;


        for(Integer i = 0; i <= w1.length()-n; ++i)
        {
            String nGram1 = w1.substring(i,i+n);

            if(w2.contains(nGram1))
            {
                ++count;
            }
        }


        return (double)count / ((double)w1.length()-n+1);
    }


    //Porównanie dla  zbioru wyrazow
    public Double calculateNGramSimilarityForStrings(Integer n, Set<String> words1, Set<String> words2)
    {
        Double sum = 0.0;
        for(String word1 : words1)
        {
            Double max = 0.0;
            for(String word2 : words2)
            {
                Double current = calculateNGramSimilarForWords(n, word1, word2);
                max = current > max ? current : max;
            }
            sum += max;
        }

        return sum / words1.size();
    }






    //Porównanie dwóch tekstów ???
/*    public int compare(Classifiable text1, Classifiable text2)
    {
        Double similarity1 = calculateNGramSimilarityForStrings(3, );
        Double similarity2 = calculateNGramSimilarityForStrings(3, ((Text)reference).getWords(), ((Text)text2).getWords());

        if(similarity1 < similarity2)
        {
            return -1;
        }
        else if(similarity1 > similarity2)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }*/


}

