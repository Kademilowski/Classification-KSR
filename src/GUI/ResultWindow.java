package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.xml.xpath.XPathEvaluationResult;


import Data.Classified;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultWindow {

    static double accuracy; //wszystkie pozytywne na wszystkie dokumenty
    //clasified
    static Map<String, Integer> countries = new LinkedHashMap<>();
    //Pozytywnie zaklasyfiowane
    static Map<String, Integer> countriesCorrect = new LinkedHashMap<>();
    //Domyslne
    static Map<String, Integer> countriesDefault = new LinkedHashMap<>();

    //Precision
    static Map<String, Double> precisions = new LinkedHashMap<>();
    //Recall
    static Map<String, Double> recalls = new LinkedHashMap<>();
    //F1
    static Map<String, Double> f1 = new LinkedHashMap<>();

    List<JTextField> textFieldsList = new ArrayList<JTextField>();
    public JFrame frame;

    ResultWindow(List<Classified> classifiedDocuemnts){

            frame = new JFrame();


            //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setSize(520, 280);
            frame.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            //frame.setLayout();
            //frame.setLayout(new FlowLayout(5));
            frame.setVisible(true);
            Border border = frame.getRootPane().getBorder();
            Border margin = new EmptyBorder(10,10,10,10);
            frame.getRootPane().setBorder(new CompoundBorder(border, margin));

            int indx = 3;
            String titleMeasure = "";

        // panel.setBackground(Color.darkGray);

            /*Container accuracyLayout = frame.getContentPane();
            Container f1Layout = frame.getContentPane();
            Container recallsLayout = frame.getContentPane();
            Container precissionsLayout = frame.getContentPane();*/


             Font fontResult = new Font("Serif", Font.BOLD, 20);
             Font fontTitles = new Font("Serif", Font.BOLD, 14);
             Font fontOthers = new Font("Serif", Font.PLAIN, 15);


            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);

            Statistics(classifiedDocuemnts);

        JLabel label = new JLabel("Wyniki");
        label.setFont(fontResult);
        c.gridx = 0;
        c.weightx = 0.5;
        c.gridy = 0;
        frame.add(label, c);

        JTextField accuracyTextField = new JTextField("Accuracy: " + accuracy);
        accuracyTextField.setFont(fontOthers);
        accuracyTextField.setEditable(false);
        c.gridx = 0;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridy = 1;
        frame.add(accuracyTextField, c);
        c.gridwidth = 1;



        JTextField f1TextField = new JTextField("F1");
        f1TextField.setEditable(false);
        f1TextField.setFont(fontTitles);
        c.gridx = 0;
        c.weightx = 0.5;
        c.gridy = 2;
        frame.add(f1TextField, c);

            for(Map.Entry<String, Double> it : f1.entrySet()){
                JTextField textField = new JTextField(it.getKey() + ": " + df.format(it.getValue()));
                textField.setEditable(false);
                textField.setFont(fontOthers);
                c.gridx = 0;
                c.weightx = 0.5;
                c.gridy = indx;
                frame.add(textField, c);
                indx++;
                //textFieldsList.add(textField);
                //f1Layout.add(textField);
            }


            JTextField recallsTextField = new JTextField("Recalls");
            recallsTextField.setEditable(false);
            recallsTextField.setFont(fontTitles);
            c.gridx = 1;
            c.weightx = 0.5;
            c.gridy = 2;
            frame.add(recallsTextField, c);
            indx = 3;

            for(Map.Entry<String, Double> it : recalls.entrySet()){
                JTextField textField = new JTextField(it.getKey() + ": " +  df.format(it.getValue()));
                textField.setEditable(false);
                textField.setFont(fontOthers);
                c.gridx = 1;
                c.weightx = 0.5;
                c.gridy = indx;
                frame.add(textField, c);
                indx++;
                //textFieldsList.add(textField);
               // recallsLayout.add(textField);

            }



            JTextField precisionTextField = new JTextField("Precision");
            precisionTextField.setFont(fontTitles);
            precisionTextField.setEditable(false);
            c.gridx = 2;
            c.weightx = 0.5;
            c.gridy = 2;
            frame.add(precisionTextField, c);
            indx = 3;


            for(Map.Entry<String, Double> it : precisions.entrySet()){

                JTextField textField = new JTextField(it.getKey() + ": " +  df.format(it.getValue()));
                textField.setEditable(false);
                textField.setFont(fontOthers);
                c.gridx = 2;
                c.weightx = 0.5;
                c.gridy = indx;
                frame.add(textField, c);
                indx++;
                //textFieldsList.add(textField);
                //precissionsLayout.add(textField);
            }


        }




    public static void Statistics(List<Classified> classifiedDocuemnts){


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
                //System.out.println("nieorginal: " + it.getCategory() + "   orginal: " + it.getOrginalCategory());
                //Pozytywnie to nie dziala
                if (it.getCategory().equals(it.getOrginalCategory())) {
                    countriesCorrect.put(it.getOrginalCategory(), ++countCorrect);
                }

        }



        System.out.println("Klasyfikowanie");
        for(Map.Entry<String, Integer> it : countries.entrySet()){

        }
        System.out.println("Domyslnie");
        for(Map.Entry<String, Integer> it : countriesDefault.entrySet()){
            System.out.println(it.getKey() + " " + it.getValue());
        }

        System.out.println("Pozytwnie zaklasyfikowane");
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

        for(Map.Entry<String, Double> it : recalls.entrySet()){
            if(it.getValue().isNaN()){
                it.setValue(0.0);
            }
        }

        precisions.put("usa",(double)countriesCorrect.get("usa")/((1.0)*(double)countries.get("usa")));
        precisions.put("uk",(double)countriesCorrect.get("uk")/((1.0)*(double)countries.get("uk")));
        precisions.put("japan",(double)countriesCorrect.get("japan")/((1.0)*(double)countries.get("japan")));
        precisions.put("canada",(double)countriesCorrect.get("canada")/((1.0)*(double)countries.get("canada")));
        precisions.put("west-germany",(double)countriesCorrect.get("west-germany")/((1.0)*(double)countries.get("west-germany")));
        precisions.put("france",(double)countriesCorrect.get("france")/((1.0)*(double)countries.get("france")));

        for(Map.Entry<String, Double> it : precisions.entrySet()){
            if(it.getValue().isNaN()){
                it.setValue(0.0);
            }
        }

        f1.put("usa",2.0*precisions.get("usa")*recalls.get("usa")/(precisions.get("usa") + recalls.get("usa")));
        f1.put("uk", 2.0*precisions.get("uk")*recalls.get("uk")/(precisions.get("uk")+recalls.get("uk")));
        f1.put("japan",2.0*precisions.get("japan")*recalls.get("japan")/(precisions.get("japan")+recalls.get("japan")));
        f1.put("canada",2.0*precisions.get("canada")*recalls.get("canada")/(precisions.get("canada")+recalls.get("canada")));
        f1.put("west-germany",2.0*precisions.get("west-germany")*recalls.get("west-germany")/(precisions.get("west-germany") + recalls.get("west-germany")));
        f1.put("france",2.0*precisions.get("france")*recalls.get("france")/(precisions.get("france")+recalls.get("france")));

        for(Map.Entry<String, Double> it : f1.entrySet()){
            if(it.getValue().isNaN()){
                it.setValue(0.0);
            }
        }


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

    }



}



