package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Wyszukiwanie implements Window {
    private static JFrame frameStatic;

    private final JFrame frame = new JFrame("Wyszukiwarka");
    private JPanel rootPanel;
    private JTextField fieldImie;
    private JTextField fieldNazwisko;
    private JTextField fieldWiek;
    private JTextField fieldPesel;
    private JTextField fieldPensja;
    private JTextField fieldSpecjalizacja;
    private JButton anulujButton;
    private JButton szukajButton;

    public Wyszukiwanie() {
        anulujButton.addActionListener(e -> Controller.closeWindow(frameStatic));

        szukajButton.addActionListener(e -> Controller.wyszukiwanie(fieldImie.getText(), fieldNazwisko.getText(), fieldWiek.getText(), fieldPesel.getText(), fieldPensja.getText(), fieldSpecjalizacja.getText(), frameStatic));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller.closeWindow(frameStatic);
                super.windowClosing(e);
            }
        });
    }

    @Override
    public void GUI_Create() {
        frameStatic = frame;
        frame.setContentPane(new Wyszukiwanie().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setAlwaysOnTop(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller.closeWindow(frame);
                super.windowClosing(e);
            }
        });
    }
}
