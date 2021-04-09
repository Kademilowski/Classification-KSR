package proj1.GUI;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        learnSetJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
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

        learnSetTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value =   learnSetTextField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    learnSetTextField.setEditable(true);
                } else {
                    learnSetTextField.setEditable(false);

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




    }


}
