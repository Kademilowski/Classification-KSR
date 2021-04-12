package proj1.Metrics;

import proj1.featuresVectors.Feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Metric {
    public static double Euclidean(List<Double> v1, List<Double> v2){
        double distance = 0;

        for(int i=0; i<v1.size(); i++){
            distance += Math.pow((v1.get(i) - v2.get(i)), 2);
        }
        return Math.sqrt(distance);
    }

    public static double Manhattan(List<Double> v1, List<Double> v2){
        double distance = 0;

        for(int i=0; i<v1.size(); i++){
            distance += Math.abs((v1.get(i) - v2.get(i)));
        }
        return distance;
    }

    public static double Chebyshev(List<Double> v1, List<Double> v2){
        List<Double> tmp = new ArrayList<Double>();

        for(int i=0; i<v1.size(); i++){
            tmp.add(Math.abs(v1.get(i) - v2.get(i)));
        }

        Double t = Collections.max(tmp);
        return  t;
        //return tmp.stream().max()
    }
}
