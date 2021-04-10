package proj1.featuresVectors;

public class Capital extends Feature {

    private String capital;

    public Capital(String capital) {
        super(true, capital);
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
