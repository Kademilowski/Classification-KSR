package proj1.Measures;

import proj1.Data.Article;
import proj1.KNN.Classifiable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JaccardaMeasure extends Measure {

    private Article reference = null;

    public Classifiable getReference()
    {
        return null;
        //return reference;
    }

    public void setReference(Classifiable aReference)
    {
        reference = (Article) aReference;
    }

    public int compare(Classifiable text1, Classifiable text2)
    {
        String[] n = {"witam","sdsd"} ;
        Set<String> ref1 = new HashSet<String>(Arrays.asList(n));


        //Set<String> ref1 = new HashSet<String>(((Article)reference).getWords());
        Set<String> ref2 = new HashSet<String>(ref1);
        Set<String> ref3 = new HashSet<String>(ref1);
        Set<String> ref4 = new HashSet<String>(ref1);


        //ODKOMENTOWAC albo nie
        //ref1.retainAll(((Article)text1).getWords());
        //ref2.addAll(((Article)text1).getWords());
       // ref3.retainAll(((Article)text2).getWords());
        //ref4.addAll(((Article)text2).getWords());

        Double similarity1 = (double)ref1.size() / (double)ref2.size();
        Double similarity2 = (double)ref3.size() / (double)ref4.size();

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


}
