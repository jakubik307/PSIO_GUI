package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddPielegniarka implements Window {
    private static JFrame frameStatic;

    private final JFrame frame = new JFrame("Dodawanie pielęgniarki");
    private JPanel rootPanel;
    private JTextField fieldImie;
    private JTextField fieldNazwisko;
    private JTextField fieldWiek;
    private JTextField fieldPesel;
    private JTextField fieldEtat;
    private JButton anulujButton;
    private JButton zapiszButton;

    public AddPielegniarka() {
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.closeWindow(frameStatic);
            }
        });

        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.addPielegniarka(fieldImie.getText(), fieldNazwisko.getText(), fieldWiek.getText(), fieldPesel.getText(), fieldEtat.getText(), frameStatic);
            }
        });

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
        frame.setContentPane(new AddPielegniarka().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setAlwaysOnTop(true);
    }
}
