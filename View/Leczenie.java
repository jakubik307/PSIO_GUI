package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Leczenie implements Window {
    private static JFrame frameStatic = new JFrame();

    private JFrame frame = new JFrame("Leczenie pacjenta");
    private JPanel rootPanel;
    private JButton anulujButton;
    private JButton zapiszButton;
    private JTextField fieldImie;
    private JTextField fieldNazwisko;
    private JTextField fieldWiek;
    private JTextField fieldPesel;
    private JComboBox lekarzCombo;

    public Leczenie() {
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.closeWindow(frameStatic);
            }
        });

        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        fieldPesel.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            private void update() {
                fieldImie.setText(Controller.getNamefromPesel(fieldPesel.getText()));
                fieldNazwisko.setText(Controller.getSurnamefromPesel(fieldPesel.getText()));
                fieldWiek.setText(Controller.getWiekfromPesel(fieldPesel.getText()));
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
        frame.setContentPane(new Leczenie().rootPanel);
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
                Controller.closeWindow(frameStatic);
                super.windowClosing(e);
            }
        });
    }
}
