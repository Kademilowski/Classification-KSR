package proj1.GUI;
import javax.swing.*;
import java.awt.*;

public class WindowKNN extends JFrame{

    public WindowKNN(){
        super("Witam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        setLayout(new FlowLayout());

        add(new JButton("Przycisk 1"));
        add(new JButton("Przycisk 2"));
        add(new JButton("Przycisk 3"));


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel name = new JLabel("Name: ");
        JTextField nameField = new JTextField();

        inputPanel.add(name);
        inputPanel.add(nameField);

        setVisible(true);
    }
}
