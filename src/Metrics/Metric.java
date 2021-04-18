package Metrics;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Metric {
    public static double Euclidean(List<Double> v1, List<Double> v2){
        double distance = 0;

        for(int i=0; i<v1.size(); i++){
            distance += Math.pow((v1.get(i).doubleValue() - v2.get(i).doubleValue()), 2);
        }

        //System.out.println("wybrane");
        return Math.sqrt(distance);


    }

    public static double Manhattan(List<Double> v1, List<Double> v2){
        double distance = 0;

        for(int i=0; i<v1.size(); i++){
            distance += Math.abs((v1.get(i).doubleValue() - v2.get(i).doubleValue()));
        }
        return distance;
    }

    public static double Chebyshev(List<Double> v1, List<Double> v2){
        List<Double> tmp = new ArrayList<Double>();
        double value = 0;
        int index = 0;

        for(int i=0; i<v1.size(); i++){
            if(value  < Math.abs(v1.get(i).doubleValue() - v2.get(i).doubleValue())){
                index = i;
                value = Math.abs(v1.get(i).doubleValue() - v2.get(i).doubleValue());
            }
            //value = ;
            tmp.add(Math.abs(v1.get(i).doubleValue() - v2.get(i).doubleValue()));

        }



        Double t = Collections.max(tmp);
        return  t;

    }
}
