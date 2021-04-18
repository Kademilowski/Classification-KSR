package Features;

public class VectorNumberVal implements Comparable<VectorNumberVal> {

    private Features featureVector;
    private Double distance;

    public VectorNumberVal(Features featureVector, Double distance) {
        this.featureVector = featureVector;
        this.distance = distance;
    }

    public Features getFeatureVector() {
        return this.featureVector;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(VectorNumberVal o) {
        if (this.getDistance().equals(o.getDistance())) {
            return 0;
        } else if (this.getDistance() > o.getDistance()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return false;

    }



}
