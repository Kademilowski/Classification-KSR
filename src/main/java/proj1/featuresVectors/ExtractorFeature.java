package proj1.featuresVectors;

import org.apache.commons.lang3.ArrayUtils;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.util.*;

import static java.text.DateFormat.FULL;
import static java.text.DateFormat.MEDIUM;
import static java.util.Locale.*;
import static java.util.TimeZone.LONG;
import static java.util.TimeZone.SHORT;

public class ExtractorFeature {




    //Count Sentences ok - wynik
    public static double CountSentences(String text){
        double countSentences = 0;
        // z web

        BreakIterator iterator = BreakIterator.getSentenceInstance(new Locale("en", "US"));
        StringBuffer markers = new StringBuffer();
        int boundary = iterator.first();

        markers.setLength(text.length() + 1);

        for (int k = 0; k < markers.length(); k++) {
            markers.setCharAt(k, ' ');
        }

        iterator.setText(text);

        while (boundary != BreakIterator.DONE) {
            markers.setCharAt(boundary, '^');
            ++countSentences;
            boundary = iterator.next();

        }

        if(countSentences <= 0) {
            return 0;
        } else {
            return countSentences-2;
        }
    }



    //1 jescze chyba nie dziala z stoptermami ok ale bez stoptermow - liczba
    //stopterm lista bo zeby usunac slowa
    public static double WordsCount(List<String> stopterms, String text) {
        double countWords = 0;

        text = text.trim();

        String[] textWords = text.split("\\W+");
        List<String> textWords2 = Arrays.asList(textWords);


        textWords2.removeAll(stopterms);


        if (textWords2.size() <= 0)
            return 0;

        return textWords2.size();



        /*
         TO JEST DOBRE W RAZIE W

        text = text.trim();
        if (text.isEmpty())
            return 0;
        countWords = text.split("\\s+").length;
        return countWords;
        */
    }




    //2 dziala ale do malych liter i usunac by i Reuters - String
    public static String AuthorName(String text) {

        String author = text; // ??? czy coś jeszcze usunać
        author = author.toLowerCase();

        String tmp = author.copyValueOf("reuters".toCharArray());
        author = author.replace(tmp, "");

        tmp = author.copyValueOf("by ".toCharArray());
        author = author.replace(tmp, "");

        return author;
    }



    //3 dziala - liczba
    public static double WordsCountAtSentence(String text) {

        double countWords = 0;
        double average = 0;
        double countSentences = 0;
        countSentences = CountSentences(text);

        if(countSentences <= 0){
            return 0;
        }

        text = text.trim();
        if (text.isEmpty()){
            return 0;
        }
        countWords = text.split("\\s+").length;


        average = countWords/countSentences;
        //average = countSentences;
        return average;
    }


    //4 ?????? string
    public static String FormatDate(){
        Date today = new Date();
        Locale[] locales = { US, UK, GERMANY, FRANCE };
        int[] styles = { FULL, LONG, MEDIUM, SHORT };
        String[] styleNames = { "FULL", "LONG", "MEDIUM", "SHORT" };
        DateFormat fmt = null;
        for (Locale locale : locales) {
            System.out.println("\nThe Date for " + locale.getDisplayCountry() + ":");
            for (int i = 0; i < styles.length; i++) {
                fmt = DateFormat.getDateInstance(styles[i], locale);
                System.out.println("\tIn " + styleNames[i] + " is " + fmt.format(today));
            }
        }

        return fmt.toString();
    }




    //5 jak ????? - string
    public static String FormatPhone(){
        Date today = new Date();
        Locale[] locales = { US, UK, GERMANY, FRANCE };
        int[] styles = { FULL, LONG, MEDIUM, SHORT };
        String[] styleNames = { "FULL", "LONG", "MEDIUM", "SHORT" };
        DateFormat fmt = null;
        for (Locale locale : locales) {
            System.out.println("\nThe Date for " + locale.getDisplayCountry() + ":");
            for (int i = 0; i < styles.length; i++) {
                fmt = DateFormat.getDateInstance(styles[i], locale);
                System.out.println("\tIn " + styleNames[i] + " is " + fmt.format(today));
            }
        }
        return fmt.toString();
    }



    //6 uzuplenić wszystkimi interpunkcjami z body - liczba
    public static double CountInterpunctions(String text){
        double interpunctions = 0;
        double countSentences = 0;
        countSentences = CountSentences(text);
        if(countSentences <= 0) return 0;

        char[] charsInterpunctions = new char[] {'.', ',' , '!', '?', ':', ';', '\'', '\\', '\"','(', ')', '[', ']', '-', '_' };

        for(int i = 0; i < text.length(); i++) {

            for(char it : charsInterpunctions){
                if(text.charAt(i) == it){
                    interpunctions++;

                }
            }

        }

        return interpunctions/countSentences;
    }



    //7 Stolice - String
    public static String Capital(String text, List<String> places){
        int[] capitalCount = new int[] {0, 0, 0, 0, 0, 0};
        String[] capitals = new String[] {"tokyo", "bonn", "washington", "paris", "london", "ottawa"};

        String tmp = places.toString();
        tmp = tmp.toLowerCase();


        text =tmp + " " + text.toLowerCase();



        Scanner scanner = new Scanner(text);

        while(scanner.hasNext()){
            String nextWord = scanner.next().trim();
            if(nextWord.equals(capitals[0])){
                ++capitalCount[0];
            } else if(nextWord.equals(capitals[1])){
                ++capitalCount[1];
            } else if (nextWord.equals(capitals[2])){
                ++capitalCount[2];
            } else if (nextWord.equals(capitals[3])){
                ++capitalCount[3];
            } else if (nextWord.equals(capitals[4])){
                ++capitalCount[4];
            } else if (nextWord.equals(capitals[5])){
                ++capitalCount[5];
            }
        }
        List b = Arrays.asList(ArrayUtils.toObject(capitalCount));
        return capitals[ArrayUtils.indexOf(capitalCount, (int)Collections.max(b))];
    }





    //8 liczba zdan zawartych w peirwszych 30% tekstu - liczba
    public static double CountSentences30PercentText(String text) {
        double sentences = 0;


        if((ExtractorFeature.CountSentences(text)*0.3) >= 1){
            sentences = ExtractorFeature.CountSentences(text);
        }

        return sentences;
    }



    //9 - liczba
    public static double AverageBigLetter(String text){
        double upperCase =  0;
        double countSentences = 0;
        countSentences = CountSentences(text);
        if(countSentences <= 0){return 0;}

        for(int i=0; i<text.length(); i++){
            if(Character.isUpperCase(text.charAt(i))){
                ++upperCase;
            }
        }

        return upperCase/countSentences;
    }



    //10 chyba dziala - String
    public static String Currency(String text){
        int[] currencyCount = new int[] {0, 0, 0, 0, 0, 0};
        String[] currencies = new String[] {"dollar", "yen", "peseta", "dollar canadian", "d-mark", "franc"};

        text = text.toLowerCase();
        Scanner scanner = new Scanner(text);

        while(scanner.hasNext()){
            String nextWord = scanner.next().trim();
            if(nextWord.equals(currencies[0])){
                ++currencyCount[0];
            } else if(nextWord.equals(currencies[1])){
                ++currencyCount[1];
            } else if (nextWord.equals(currencies[2])){
                ++currencyCount[2];
            } else if (nextWord.equals(currencies[3])){
                ++currencyCount[3];
            } else if (nextWord.equals(currencies[4])){
                ++currencyCount[4];
            } else if (nextWord.equals(currencies[5])){
                ++currencyCount[5];
            }
        }
        List b = Arrays.asList(ArrayUtils.toObject(currencyCount));
        return currencies[ArrayUtils.indexOf(currencyCount, (int)Collections.max(b))];
    }



}