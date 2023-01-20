package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListaSzczepien implements Window {
    private static JFrame frameStatic;

    private final JFrame frame = new JFrame("Szczepienia pacjenta");
    private JPanel rootPanel;
    private JTextArea szczepieniaArea;
    private JTextField fieldImie;
    private JTextField fieldNazwisko;
    private JTextField fieldWiek;
    private JTextField fieldPesel;

    public ListaSzczepien() {
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
                szczepieniaArea.setText(Controller.getSzczepieniaFromPesel(fieldPesel.getText()));
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
        frame.setContentPane(new ListaSzczepien().rootPanel);
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
