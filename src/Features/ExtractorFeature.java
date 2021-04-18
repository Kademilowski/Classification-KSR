package Features;

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
    public static int WordsCount(List<String> stopterms, String text) {
        double countWords = 0;

        text = text.trim();

        String[] textWords = text.split("\\W+");
        List<String> textWords2 = Arrays.asList(textWords);


        //textWords2.removeAll(stopterms);
        if (text.isEmpty())
            return 0;
        countWords = text.split("\\s+").length;

        //System.out.println(countWords);

        return (int)countWords;

        /*if (textWords2.size() <= 0)
            return 0;
        System.out.println(textWords2.size());
        return (int) textWords2.size();*/



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
    public static List<String> AuthorName(List<String> text) {

        List<String> authors = text; // ??? czy coś jeszcze usunać
        List<String> tmpAuthors = new ArrayList<String>();
        String topic;


        for(String it : authors) {
            topic = it.toLowerCase();


            String tmp = topic.copyValueOf("reuters".toCharArray());
            topic = topic.replace(tmp, "");

            tmp = topic.copyValueOf("by ".toCharArray());
            topic = topic.replace(tmp, "");

            //System.out.println(topic);

            if(topic.isEmpty() || topic == null){
                topic="";
            }

            tmpAuthors.add(topic);



        }

        return tmpAuthors;

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
                //System.out.println("\tIn " + styleNames[i] + " is " + fmt.format(today));
            }
        }

        return fmt.toString();
    }





    //6 uzuplenić interpunkcjami z body - liczba
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
        //System.out.println(interpunctions);
        return interpunctions/countSentences;
    }



    //7 Stolice - String
    public static String Capital(String text, List<String> places){

        Map<String, Integer> capitals = new LinkedHashMap<>();
        capitals.put("washington", 0);
        capitals.put("bonn", 0);
        capitals.put("tokyo", 0);
        capitals.put("paris", 0);
        capitals.put("london", 0);
        capitals.put("ottawa", 0);


        String tmp = places.toString();
        tmp = tmp.toLowerCase();
        tmp = tmp.replace("[", " ");
        tmp = tmp.replace("]", " ");
        int count = 0;

        text =tmp + " " + text.toLowerCase();
        text = text.trim();
        Scanner scanner = new Scanner(text);

        while(scanner.hasNext()){
            String nextWord = scanner.next();

            if(nextWord.equals("tokyo")){
                count  = capitals.get("tokyo");
                capitals.put("tokyo", ++count);
            }
            else if(nextWord.equals("bonn")){
                count  = capitals.get("bonn");
                capitals.put("bonn", ++count);
            }
            else if(nextWord.equals("washington")){
                count  = capitals.get("washington");
                capitals.put("washington", ++count);
            }
            else if(nextWord.equals("paris")){
                count  = capitals.get("paris");
                capitals.put("paris", ++count);
            }
            else if(nextWord.equals("london")){
                count  = capitals.get("london");
                capitals.put("london", ++count);
            }
            else if(nextWord.equals("ottawa")){
                count  = capitals.get("ottawa");
                capitals.put("ottawa", ++count);
            }


        }
        //List b = Arrays.asList(ArrayUtils.toObject(currencyCount));
        Map.Entry<String, Integer> maksimum  = capitals.entrySet().iterator().next();

        for(Map.Entry<String, Integer> it : capitals.entrySet()){
            if(it.getValue() > maksimum.getValue()){
                maksimum = it;
            }
        }

        //System.out.println(maksimum.getKey());
        return maksimum.getKey();
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
        //System.out.println(upperCase/countSentences);
        return upperCase/countSentences;
    }



    //10 chyba dziala - String
    public static String Currency(String text){

        Map<String, Integer> currencies = new LinkedHashMap<>();
        currencies.put("dollar", 0);
        currencies.put("yen", 0);
        currencies.put("peseta", 0);
        currencies.put("dollar canadian", 0);
        currencies.put("d-mark", 0);
        currencies.put("franc", 0);

        int count = 0;
        //{"dollar", "yen", "peseta", "dollar canadian", "d-mark", "franc"};

        text = text.toLowerCase();



        Scanner scanner = new Scanner(text);
        //count  = currencies.get(it.getCategory());

        while(scanner.hasNext()){
            String nextWord = scanner.next().trim();

            if(nextWord.equals("dollar")){
                count  = currencies.get("dollar");
                currencies.put("dollar", ++count);
            }
            else if(nextWord.equals("yen")){
                count  = currencies.get("yen");
               // System.out.println("to jjen");
                currencies.put("yen", ++count);
            }
            else if(nextWord.equals("dollar canadian")){
                count  = currencies.get("dollar canadian");
                currencies.put("dollar canadian", ++count);
            }
            else if(nextWord.equals("d-mark")){
                count  = currencies.get("d-mark");
                currencies.put("d-mark", ++count);
            }
            else if(nextWord.equals("franc")){
                count  = currencies.get("franc");
                currencies.put("franc", ++count);
            }

        }
        //List b = Arrays.asList(ArrayUtils.toObject(currencyCount));
        Map.Entry<String, Integer> maksimum  = currencies.entrySet().iterator().next();

        for(Map.Entry<String, Integer> it : currencies.entrySet()){
            //System.out.println(it.getValue());
            if(it.getValue() > maksimum.getValue()){
                maksimum = it;
            }
        }

        //System.out.println(maksimum.getKey());
        return maksimum.getKey();

    }


    //REZERWOWE
    public static List<String> Topics(List<String> text) {

        List<String> topics = text; // ??? czy coś jeszcze usunać
        List<String> tmpTopics = new ArrayList<String>();
        String topic;


        for(String it : topics) {
            topic = it.toLowerCase();


            String tmp = topic.copyValueOf("reuters".toCharArray());
            topic = topic.replace(tmp, "");

            tmp = topic.copyValueOf("by ".toCharArray());
            topic = topic.replace(tmp, "");

            //System.out.println(topic);

            if (topic.isEmpty() || topic == null) {
                topic = "";
            }

            tmpTopics.add(topic);


        }

        return tmpTopics;
    }



    public static String FirstSentence(String text) {

        double countSentences = 0;

        String firstSentence = "";
        //System.out.println(text);
        BreakIterator iterator = BreakIterator.getSentenceInstance(new Locale("en", "US"));
        StringBuffer markers = new StringBuffer();


        markers.setLength(text.length() + 1);

        for (int k = 0; k < markers.length(); k++) {
            markers.setCharAt(k, ' ');
        }

        iterator.setText(text);



        // int countt = 0;
        iterator.setText(text);
        int start = iterator.first();
        int end = iterator.next();
        try{
            firstSentence = text.substring(start, end);
        } catch(Exception e){
            firstSentence = "";
        }


        return firstSentence;

    }


}