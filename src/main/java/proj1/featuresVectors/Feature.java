package proj1.featuresVectors;

import javax.print.DocFlavor;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Feature {

    private boolean isText = false;
    private Double number;
    private String text;

    public Feature(boolean isText) {
        this.isText = isText;
    }


    public Double getNumber() {
        return number;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public void setNumber(Double number) {
        this.number = number;
    }


    public boolean isNumber() {
        return false;
    }

    public boolean isText(){
        return true;
    }

    public String getText(){
        return text;
    }

}

//L 1. liczba słów w  tag text - z usunieciem stoptermow
//T 2. nazwa autora w dokumencie - w  tag author
//L 3. średnia liczba słów w zdaniu w danym dokumencie - tag text, nie usuwamy stoptermow
//T 4. daty w tag date albo w tag text
//T 5. tak samo jak w 4 tylko dla telefonu format
//L 6. srednia liczba znaków interpunkcyjnych - w tag text
//T 7. najczesciej wystepujaca stolica w porwnainu ze slownikiem i  tag text
//L 8. liczba zdan w 30% tekstu czyli zbadanie ilosci zdan i 30% z tego.
//L 9. średnia liczba wystapien wielkiej litery w zdaniu - w tag text.
//T 10. pelna nazwa waluty w tag text