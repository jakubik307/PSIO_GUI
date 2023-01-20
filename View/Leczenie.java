package View;

import Controller.Controller;
import Model.Action;
import Model.Osoby.Lekarz;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Leczenie implements Window {
    private static JFrame frameStatic = new JFrame();

    private final JFrame frame = new JFrame("Leczenie pacjenta");
    private JPanel rootPanel;
    private JButton anulujButton;
    private JButton zapiszButton;
    private JTextField fieldImie;
    private JTextField fieldNazwisko;
    private JTextField fieldWiek;
    private JTextField fieldPesel;
    private JComboBox<Lekarz> lekarzCombo;

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
                Controller.leczenie(fieldPesel.getText(), (Lekarz) lekarzCombo.getSelectedItem(), frameStatic);
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
                fieldImie.setText(Controller.getNameFromPesel(fieldPesel.getText()));
                fieldNazwisko.setText(Controller.getSurnameFromPesel(fieldPesel.getText()));
                fieldWiek.setText(Controller.getWiekFromPesel(fieldPesel.getText()));
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller.closeWindow(frameStatic);
                super.windowClosing(e);
            }
        });

        for (Model.Osoby.Osoba osoba : Action.getOsoby()) {
            if (osoba instanceof Lekarz) {
                lekarzCombo.addItem((Lekarz) osoba);
            }
        }
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
