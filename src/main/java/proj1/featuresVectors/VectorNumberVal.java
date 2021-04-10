package proj1.featuresVectors;

/*import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;*/

public class VectorNumberVal implements Comparable<VectorNumberVal> {


    /*------------------------ FIELDS REGION ------------------------*/
    private Cechy featureVector;
    private Double distance;

    /*------------------------ METHODS REGION ------------------------*/
    public VectorNumberVal(Cechy featureVector, Double distance) {
        this.featureVector = featureVector;
        this.distance = distance;
    }

    public Cechy getFeatureVector() {
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
        //VectorNumberVal that = (VectorNumberVal) o;

       /* return new EqualsBuilder()
                .append(featureVector, that.featureVector)
                .append(distance, that.distance)
                .isEquals();*/
    }


   /* @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(featureVector)
                .append(distance)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("featureVector", featureVector)
                .append("distance", distance)
                .toString();
    }
    */
}
