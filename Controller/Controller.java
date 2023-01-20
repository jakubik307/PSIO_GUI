package Controller;

import Model.Action;
import Model.Osoby.Lekarz;
import Model.Osoby.Pielegniarka;
import View.MainMenu;
import View.Window;

import javax.swing.*;

public class Controller {
    private static boolean windowOpened = false;
    private static int currentTableState = 3;

    public static void wczytajDane() {
        Action.wczytajDane();
    }

    public static void setTable(JTable table, int option) {
        switch (option) {
            //1 - pracownicy
            case 1 -> {
                table.setModel(new MainMenu.PracownikTableModel(Action.wyswietlPracownikow()));
                currentTableState = 1;
            }
            //2 - pacjenci
            case 2 -> {
                table.setModel(new MainMenu.PacjentTableModel(Action.wyswietlPacjentow()));
                currentTableState = 2;
            }
            //3 - osoby
            case 3 -> {
                table.setModel(new MainMenu.OsobaTableModel(Action.getOsoby()));
                currentTableState = 3;
            }
        }
    }

    public static void openWindow(Window window) {
        if (!windowOpened) {
            window.GUI_Create();
            windowOpened = true;
        }
    }

    public static void closeWindow(JFrame frame) {
        frame.dispose();
        windowOpened = false;
    }

    public static void addLekarz(String imie, String nazwisko, String wiek, String pesel, String etat, String specjalizacja, JFrame frame) {
        try {
            if (imie.equals("") || nazwisko.equals("") || (Integer.parseInt(wiek) < 0 || Long.parseLong(pesel) < 0) || Integer.parseInt(etat) < 0 || specjalizacja.equals("")) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                Action.addLekarz(imie, nazwisko, specjalizacja, Integer.parseInt(wiek), Integer.parseInt(etat), Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), 1);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addPielegniarka(String imie, String nazwisko, String wiek, String pesel, String etat, JFrame frame) {
        try {
            if (imie.equals("") || nazwisko.equals("") || (Integer.parseInt(wiek) < 0 || Long.parseLong(pesel) < 0) || Integer.parseInt(etat) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                Action.addPielegniarka(imie, nazwisko, Integer.parseInt(wiek), Integer.parseInt(etat), Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), 2);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addPacjent(String imie, String nazwisko, String wiek, String pesel, JFrame frame) {
        try {
            if (imie.equals("") || nazwisko.equals("") || (Integer.parseInt(wiek) < 0 || Long.parseLong(pesel) < 0)) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                Action.addPacjent(imie, nazwisko, Integer.parseInt(wiek), Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), 1);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addChoroba(String pesel, JFrame frame) {
        try {
            if (Long.parseLong(pesel) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                Action.addChoroba(Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), currentTableState);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String getNamefromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getNameFromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String getSurnamefromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getSurnameFromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String getWiekfromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getWiekfromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static void leczenie(String pesel, Lekarz lekarz, JFrame frame) {
        try {
            if (Long.parseLong(pesel) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                Action.leczenie(Long.parseLong(pesel), lekarz);
                Controller.setTable(MainMenu.getTableStatic(), currentTableState);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void szczepienie(String pesel, Pielegniarka pielegniarka, String nazwa, JFrame frame) {
        try {
            if (Long.parseLong(pesel) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                Action.szczepienie(Long.parseLong(pesel), pielegniarka, nazwa);
                Controller.setTable(MainMenu.getTableStatic(), currentTableState);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }
}


