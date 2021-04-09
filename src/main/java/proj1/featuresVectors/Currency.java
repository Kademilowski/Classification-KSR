package proj1.featuresVectors;

public class Currency extends Feature {

    private String currency;


    public Currency(String currency) {
        super(true);
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
