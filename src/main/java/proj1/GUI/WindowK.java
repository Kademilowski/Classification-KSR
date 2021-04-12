package proj1.GUI;

import proj1.Data.Article;
import proj1.Data.Classified;
import proj1.Data.Document;
import proj1.Data.ReaderDocument;
import proj1.KNN.MethodKNN;
import proj1.QualityMeasures.QualityMeasures;
import proj1.featuresVectors.Cechy;
import proj1.featuresVectors.ExtractorFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class WindowK {
    public JPanel panel1;
    private JTextField kParameterTextField;
    private JRadioButton chebyshevRadioBtn;
    private JRadioButton euculidesRadioBtn;
    private JRadioButton manhattanRadioBtn;
    private JRadioButton nGramRadioBtn;
    private JCheckBox wordNumberCheckBox;
    private JCheckBox wordsSentenceCheckBox;
    private JCheckBox capitalCheckBox;
    private JCheckBox currencyCheckBox;
    private JCheckBox avgBigLetterCheckBox;
    private JCheckBox dataFormatCheckBox;
    private JCheckBox phoneFormatCheckBox;
    private JCheckBox avgSignsCheckBox;
    private JCheckBox numberSentAtFragCheckBox;
    private JCheckBox authorCheckBox;
    private JButton classificationBtn;
    private JLabel resultJLabel;
    private JLabel accuracyJLabel;
    private JLabel precisionJLabel;
    private JLabel recallJLabel;
    private JLabel f1JLabel;
    private JLabel metricJLabel;
    private JLabel textMeasureJLabel;
    private JLabel kParamaeterJLabel;
    private JLabel testSetJLabel;
    private JLabel learnSetJLabel;
    private JLabel featuresJLabel;
    private JTextField testSetTextField;
    private JCheckBox topicsCheckBox;
    private JCheckBox firstSentenceCheckBox;
    private JTextField learnSetTextField;

    private ButtonGroup groupMetric = new ButtonGroup();
    private ButtonGroup groupTextMetric = new ButtonGroup();

    public WindowK(){
        groupMetric.add(euculidesRadioBtn);
        groupMetric.add(chebyshevRadioBtn);
        groupMetric.add(manhattanRadioBtn);
        groupTextMetric.add(nGramRadioBtn);



        //resultJLabel.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        resultJLabel.setFont(resultJLabel.getFont().deriveFont(32.0f));
        accuracyJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        precisionJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        recallJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        f1JLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));

        metricJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        textMeasureJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        featuresJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        kParamaeterJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        //learnSetJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        testSetJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));


        kParameterTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value =  kParameterTextField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    kParameterTextField.setEditable(true);
                } else {
                    kParameterTextField.setEditable(false);

                }
            }
        });




        testSetTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value =   testSetTextField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    testSetTextField.setEditable(true);
                } else {
                    testSetTextField.setEditable(false);

                }
            }
        });


        classificationBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                checkedFeatures.put("wordsCount", wordNumberCheckBox.isSelected());
                checkedFeatures.put("wordsCountSentences", wordsSentenceCheckBox.isSelected());
                checkedFeatures.put("avgBigLetter", avgBigLetterCheckBox.isSelected());
                checkedFeatures.put("interpunctions", avgSignsCheckBox.isSelected());
                checkedFeatures.put("countSentences30", numberSentAtFragCheckBox.isSelected());
                checkedFeatures.put("capital", capitalCheckBox.isSelected());
                checkedFeatures.put("currency", currencyCheckBox.isSelected());
                checkedFeatures.put("topics", topicsCheckBox.isSelected());
                checkedFeatures.put("firstSentence", firstSentenceCheckBox.isSelected());
                checkedFeatures.put("authors", authorCheckBox.isSelected());


                Knumber = Integer.parseInt(kParameterTextField.getText());
                ratioTest = Integer.parseInt(testSetTextField.getText());

                Scanner s = null;
                try {
                    s = new Scanner(new File(String.valueOf(fileStopterms)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (s.hasNext()){
                    stopterms.add(s.next());
                }
                s.close();


                System.out.println(stopterms.toString());

                final File folder = new File("D:\\Kamil\\Studia\\semestr_6\\KOMPUTEROWE_SYSTEMY_ROZPOZNAWANIA\\Laboratorium\\Projekt1\\src\\main\\java\\resources\\documents");
                Document doc = new Document();

                try {
                    doc.listFilesForFolder(folder);
                } catch (java.io.IOException IOException) {
                    System.out.println(IOException);
                }

                for (String text : doc.articles) {
                    documents.add(new Article(ReaderDocument.GetDate(text), ReaderDocument.GetTopics(text),
                            ReaderDocument.GetPlaces(text), ReaderDocument.GetPeople(text), ReaderDocument.GetTitle(text),
                            ReaderDocument.GetAuthors(text), ReaderDocument.GetText(text)));

                }



                // RemoveOtherDocuments(documents, places);
                RemoveOtherDocuments(documents, places);


                for(Article it : documents){
                   // System.out.println("eee " + it.getPlaces());
                    //System.out.println(ExtractorFeature.FirstSentence(it.getText()));

                }

                for(int i=0; i<100; i++){
                    System.out.println(documents.get(i).getTitle());
                    System.out.println(ExtractorFeature.WordsCount(stopterms, documents.get(i).getText()));
                }

                System.out.println(documents.size());
                SetsDivision(ratioTest);
                System.out.println(documents.size());
                System.out.println(trainingDocuments.size());
                System.out.println(testDocuments.size());

                extracionFeatures();
                methodKNN(Knumber, "Euculidean", "Trigram", checkedFeatures);
                QualityMeasures.Statistics(classifiedDocuemnts);
               /* if(metric == "Euculidean"){
                    return Metric.Euclidean(numA, numB);
                }

                "Hamming"
                else if(metric == "Chebyschev"){
                    return Metric.Chebyshev(numA, numB);
                }
                else if(metric == "Manhattan"){
                    return Metric.Manhattan(numA, numB);
                }*/


                documents.clear();
                trainingDocuments.clear();
                testDocuments.clear();
                trainingVectors.clear();
                testVectors.clear();
                classifiedDocuemnts.clear();
                stopterms.clear();


            }
        });

    }

    //public void doClassification(){

   // }


    private static java.util.List<Article> documents = new ArrayList<Article>();
    private static java.util.List<Article> trainingDocuments = new ArrayList<Article>();
    private static java.util.List<Article> testDocuments = new ArrayList<Article>();

    private static java.util.List<Cechy> trainingVectors = new ArrayList<Cechy>();
    private static java.util.List<Cechy> testVectors = new ArrayList<Cechy>();

    private static java.util.List<Classified> classifiedDocuemnts = new ArrayList<Classified>();



    private static java.util.List<String> places =  Arrays.asList("west-germany, japan, france, uk, usa, canada");
   // private static java.util.List<String> stopterms =  Arrays.asList("I' am", "hello");
   private static java.util.List<String> stopterms = new List<String>() {
       @Override
       public int size() {
           return 0;
       }

       @Override
       public boolean isEmpty() {
           return false;
       }

       @Override
       public boolean contains(Object o) {
           return false;
       }

       @Override
       public Iterator<String> iterator() {
           return null;
       }

       @Override
       public Object[] toArray() {
           return new Object[0];
       }

       @Override
       public <T> T[] toArray(T[] a) {
           return null;
       }

       @Override
       public boolean add(String s) {
           return false;
       }

       @Override
       public boolean remove(Object o) {
           return false;
       }

       @Override
       public boolean containsAll(Collection<?> c) {
           return false;
       }

       @Override
       public boolean addAll(Collection<? extends String> c) {
           return false;
       }

       @Override
       public boolean addAll(int index, Collection<? extends String> c) {
           return false;
       }

       @Override
       public boolean removeAll(Collection<?> c) {
           return false;
       }

       @Override
       public boolean retainAll(Collection<?> c) {
           return false;
       }

       @Override
       public void clear() {

       }

       @Override
       public boolean equals(Object o) {
           return false;
       }

       @Override
       public int hashCode() {
           return 0;
       }

       @Override
       public String get(int index) {
           return null;
       }

       @Override
       public String set(int index, String element) {
           return null;
       }

       @Override
       public void add(int index, String element) {

       }

       @Override
       public String remove(int index) {
           return null;
       }

       @Override
       public int indexOf(Object o) {
           return 0;
       }

       @Override
       public int lastIndexOf(Object o) {
           return 0;
       }

       @Override
       public ListIterator<String> listIterator() {
           return null;
       }

       @Override
       public ListIterator<String> listIterator(int index) {
           return null;
       }

       @Override
       public List<String> subList(int fromIndex, int toIndex) {
           return null;
       }
   };
    File fileStopterms = new File("D:\\Kamil\\Studia\\semestr_6\\KOMPUTEROWE_SYSTEMY_ROZPOZNAWANIA\\Laboratorium\\Projekt1\\src\\main\\java\\resources\\stopwords\\stopwords.txt");



    private static MethodKNN knnAlgorithm = new MethodKNN();

    private static java.util.List<Classified> documentsClassied = new ArrayList<Classified>();

    static int Knumber = 0;
    static int ratioTest = 0;
    static Map<String, Boolean> checkedFeatures = new LinkedHashMap<>();



    public static void SetsDivision(int ratioTest) {

        Random random = new Random(35);
        final int testSetSize = documents.size() * (100 - ratioTest) / 100;


        // action(() -> {
        /*----- MAKE A COPY OF DOCUMENTS -----*/
        testDocuments = new ArrayList<>();
        trainingDocuments = new ArrayList<>(documents);
        //trainingDocuments = documents.stream().collect(Collectors.toCollection(ArrayList::new));
        /*----- MOVE RANDOM ITEMS TO TEST LIST -----*/
        for (int i = 0; i < testSetSize; i++) {
            int number = random.nextInt(trainingDocuments.size());
            testDocuments.add(trainingDocuments.get(number));
            trainingDocuments.remove(number);
            //      }
            // }, "Dividing into two lists");

        }
    }

    public static void RemoveOtherDocuments(java.util.List<Article> article, java.util.List<String> places) {

        List<Article> tmp = new ArrayList<Article>();

        for (Article it : article) {
            if (it.getPlaces().size() == 1) {
                for (String p1 : it.getPlaces()) {
                    for (String p2 : places) {
                        if (p2.contains(p1)) {
                            tmp.add(it);

                        }

                    }

                }
            }

        }

        documents =  tmp;
    }


    public static void extracionFeatures(){

        //funkcja ktora ekstrachuje cechy z dokeumntow czyli przakzuje liste albo poejdyczny dokument
        for(Article it : trainingDocuments){
            trainingVectors.add(new Cechy(it, stopterms));
        }


        for(Article it : testDocuments){
            testVectors.add(new Cechy(it, stopterms));

        }

        for(Cechy it : trainingVectors){
            it.setFeatures();
        }

        for(Cechy it : testVectors){
            it.setFeatures();
        }

        for(Cechy it : trainingVectors){
            it.setFeatures();
        }


    }


    public static void methodKNN(int Knumber, String metric, String textMeasure, Map<String, Boolean> checked){


        for(Cechy it : testVectors){
            String correctPlace = it.getArticle().getPlaces().get(0);
            String classifyPlace;

            if(knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked) == null){
                classifyPlace = "";
            }else{
                classifyPlace = knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked);
            }


            classifiedDocuemnts.add(new Classified(it.getArticle(), classifyPlace, correctPlace));
        }

    }




    public static void main(String[] args) {


        JFrame frame = new JFrame("Projekt 1 - Klasyifkacja k-NN");
        WindowK windowK = new WindowK();
        frame.setContentPane(windowK.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}





   /* public static void Statistics(){
        //clasified
        Map<String, Integer> countries = new LinkedHashMap<>();
        //Pozytywnie zaklasyfiowane
        Map<String, Integer> countriesCorrect = new LinkedHashMap<>();
        //Domyslne
        Map<String, Integer> countriesDefault = new LinkedHashMap<>();

        //Precision
        Map<String, Double> precisions = new LinkedHashMap<>();
        //Recall
        Map<String, Double> recalls = new LinkedHashMap<>();
        //F1
        Map<String, Double> f1 = new LinkedHashMap<>();


        countries.put("usa", 0);
        countries.put("uk", 0);
        countries.put("japan", 0);
        countries.put("canada", 0);
        countries.put("west-germany", 0);
        countries.put("france", 0);


        countriesCorrect.put("usa", 0);
        countriesCorrect.put("uk", 0);
        countriesCorrect.put("japan", 0);
        countriesCorrect.put("canada", 0);
        countriesCorrect.put("west-germany", 0);
        countriesCorrect.put("france", 0);

        countriesDefault.put("usa", 0);
        countriesDefault.put("uk", 0);
        countriesDefault.put("japan", 0);
        countriesDefault.put("canada", 0);
        countriesDefault.put("west-germany", 0);
        countriesDefault.put("france", 0);

        Integer countDefault = null;
        Integer countCorrect = null;
        Integer count = null;

        for(Classified it : classifiedDocuemnts){

            countDefault = countriesDefault.get(it.getOrginalCategory());
            countCorrect = countriesCorrect.get(it.getCategory());
            count = countries.get(it.getCategory());

            countries.put(it.getCategory(), ++count);

            countriesDefault.put(it.getOrginalCategory(), ++countDefault);
            System.out.println("nieorginal: " + it.getCategory() + "   orginal: " + it.getOrginalCategory());
            //Pozytywnie to nie dziala
            if(it.getCategory().equals(it.getOrginalCategory())){
                countriesCorrect.put(it.getOrginalCategory(), ++countCorrect);
            }



        }

        //recall = (double)countriesCorrect.get("usa");
        //recall = recall / ((1.0)*(double)countriesDefault.get("usa"));

        //precision = (double)countriesCorrect.get("usa");
        //precision = precision / (double)countries.get("usa");

        System.out.println("Klasyfikowanie");
        for(Map.Entry<String, Integer> it : countries.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }
        System.out.println("Domyslnie");
        for(Map.Entry<String, Integer> it : countriesDefault.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());
        }

        System.out.println("Pozytwnie zaklasyfikowane");
        System.out.println();
        for(Map.Entry<String, Integer> it : countriesCorrect.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());
            accuracy = accuracy + it.getValue();

        }

        recalls.put("usa",(double)countriesCorrect.get("usa")/((1.0)*(double)countriesDefault.get("usa")));
        recalls.put("uk",(double)countriesCorrect.get("uk")/((1.0)*(double)countriesDefault.get("uk")));
        recalls.put("japan",(double)countriesCorrect.get("japan")/((1.0)*(double)countriesDefault.get("japan")));
        recalls.put("canada",(double)countriesCorrect.get("canada")/((1.0)*(double)countriesDefault.get("canada")));
        recalls.put("west-germany",(double)countriesCorrect.get("west-germany")/((1.0)*(double)countriesDefault.get("west-germany")));
        recalls.put("france",(double)countriesCorrect.get("france")/((1.0)*(double)countriesDefault.get("france")));

        precisions.put("usa",(double)countriesCorrect.get("usa")/((1.0)*(double)countries.get("usa")));
        precisions.put("uk",(double)countriesCorrect.get("uk")/((1.0)*(double)countries.get("uk")));
        precisions.put("japan",(double)countriesCorrect.get("japan")/((1.0)*(double)countries.get("japan")));
        precisions.put("canada",(double)countriesCorrect.get("canada")/((1.0)*(double)countries.get("canada")));
        precisions.put("west-germany",(double)countriesCorrect.get("west-germany")/((1.0)*(double)countries.get("west-germany")));
        precisions.put("france",(double)countriesCorrect.get("france")/((1.0)*(double)countries.get("france")));

        f1.put("usa",2.0*precisions.get("usa")*recalls.get("usa")/(precisions.get("usa") + recalls.get("usa")));
        f1.put("uk", 2.0*precisions.get("uk")*recalls.get("uk")/(precisions.get("uk")+recalls.get("uk")));
        f1.put("japan",2.0*precisions.get("japan")*recalls.get("japan")/(precisions.get("japan")+recalls.get("japan")));
        f1.put("canada",2.0*precisions.get("canada")*recalls.get("canada")/(precisions.get("canada")+recalls.get("canada")));
        f1.put("west-germany",2.0*precisions.get("west-germany")*recalls.get("west-germany")/(precisions.get("west-germany") + recalls.get("west-germany")));
        f1.put("france",2.0*precisions.get("france")*recalls.get("france")/(precisions.get("france")+recalls.get("france")));



        accuracy = accuracy / classifiedDocuemnts.size();
        //recall =
        System.out.println("F1");
        for(Map.Entry<String, Double> it : f1.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }

        System.out.println("PRECISIONS");
        for(Map.Entry<String, Double> it : precisions.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }


        System.out.println("Recalls");
        for(Map.Entry<String, Double> it : recalls.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());

        }


        System.out.println(accuracy);

    }*/





