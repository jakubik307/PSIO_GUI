package View;

import Controller.Controller;
import Model.Action;
import Model.Osoby.Pielegniarka;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Szczepienie implements Window {
    private static JFrame frameStatic;

    private JFrame frame = new JFrame("Szczepienie pacjenta");
    private JPanel rootPanel;
    private JTextField fieldImie;
    private JTextField fieldNazwisko;
    private JTextField fieldWiek;
    private JTextField fieldPesel;
    private JComboBox<Pielegniarka> pielegniarkaCombo;
    private JButton anulujButton;
    private JButton zapiszButton;
    private JTextField fieldChoroba;

    public Szczepienie() {
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.closeWindow(frameStatic);
            }
        });

        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.szczepienie(fieldPesel.getText(), (Pielegniarka) pielegniarkaCombo.getSelectedItem(), fieldChoroba.getText(), frameStatic);
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
            if (osoba instanceof Pielegniarka) {
                pielegniarkaCombo.addItem((Pielegniarka) osoba);
            }
        }
    }

    @Override
    public void GUI_Create() {
        frameStatic = frame;
        frame.setContentPane(new Szczepienie().rootPanel);
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
