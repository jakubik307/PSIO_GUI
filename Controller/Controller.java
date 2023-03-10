package Controller;

import Model.Action;
import Model.Osoby.*;
import View.MainMenu;
import View.Window;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {
    private static final int CURRENT_YEAR = 2023;
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

    public static void peselWiekCorrect(String pesel, int wiek) throws PeselWiekException {
        int rokUrodzenia;

        if (pesel.length() != 11) {
            throw new PeselWiekException("Pesel powinien mieć 11 cyfr");
        }

        if (pesel.charAt(2) == '2' || pesel.charAt(2) == '3') {
            rokUrodzenia = 2000 + Integer.parseInt(pesel.substring(0, 2));
        } else if (pesel.charAt(2) == '0' || pesel.charAt(2) == '1') {
            rokUrodzenia = 1900 + Integer.parseInt(pesel.substring(0, 2));
        } else {
            throw new PeselWiekException("Nieprawidłowy miesiąc w peselu");
        }

        if (rokUrodzenia != CURRENT_YEAR - wiek && rokUrodzenia != CURRENT_YEAR - wiek - 1) {
            throw new PeselWiekException("Wiek nie zgadza się z peselem");
        }
    }

    public static void addLekarz(String imie, String nazwisko, String wiek, String pesel, String etat, String specjalizacja, JFrame frame) {
        try {
            if (imie.equals("") || nazwisko.equals("") || (Integer.parseInt(wiek) < 0 || Long.parseLong(pesel) < 0) || Integer.parseInt(etat) < 0 || specjalizacja.equals("")) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                peselWiekCorrect(pesel, Integer.parseInt(wiek));

                for (Osoba osoba : Action.getOsoby()) {
                    if (Long.parseLong(pesel) == osoba.getPesel()) {
                        JOptionPane.showMessageDialog(frame, "Osoba o podanym peselu już istnieje.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                Action.addLekarz(imie, nazwisko, specjalizacja, Integer.parseInt(wiek), Integer.parseInt(etat), Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), 1);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        } catch (PeselWiekException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addPielegniarka(String imie, String nazwisko, String wiek, String pesel, String etat, JFrame frame) {
        try {
            if (imie.equals("") || nazwisko.equals("") || (Integer.parseInt(wiek) < 0 || Long.parseLong(pesel) < 0) || Integer.parseInt(etat) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                peselWiekCorrect(pesel, Integer.parseInt(wiek));

                for (Osoba osoba : Action.getOsoby()) {
                    if (Long.parseLong(pesel) == osoba.getPesel()) {
                        JOptionPane.showMessageDialog(frame, "Osoba o podanym peselu już istnieje.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                Action.addPielegniarka(imie, nazwisko, Integer.parseInt(wiek), Integer.parseInt(etat), Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), 1);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        } catch (PeselWiekException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addPacjent(String imie, String nazwisko, String wiek, String pesel, JFrame frame) {
        try {
            if (imie.equals("") || nazwisko.equals("") || (Integer.parseInt(wiek) < 0 || Long.parseLong(pesel) < 0)) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                peselWiekCorrect(pesel, Integer.parseInt(wiek));

                for (Osoba osoba : Action.getOsoby()) {
                    if (Long.parseLong(pesel) == osoba.getPesel()) {
                        JOptionPane.showMessageDialog(frame, "Osoba o podanym peselu już istnieje.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                Action.addPacjent(imie, nazwisko, Integer.parseInt(wiek), Long.parseLong(pesel));
                Controller.setTable(MainMenu.getTableStatic(), 2);
                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        } catch (PeselWiekException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void addChoroba(String pesel, JFrame frame) {
        try {
            if (Long.parseLong(pesel) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                for (Osoba osoba : Action.getOsoby()) {
                    if (Long.parseLong(pesel) == osoba.getPesel()) {
                        Action.addChoroba(Long.parseLong(pesel));
                        Controller.setTable(MainMenu.getTableStatic(), currentTableState);
                        Controller.closeWindow(frame);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Osoba o podanym peselu nie istnieje.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String getNameFromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getNameFromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String getSurnameFromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getSurnameFromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String getWiekFromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getWiekFromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String getSzczepieniaFromPesel(String pesel) {
        try {
            long p = Long.parseLong(pesel);
            return Action.getSzczepienieFromPesel(p);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static void leczenie(String pesel, Lekarz lekarz, JFrame frame) {
        try {
            if (Long.parseLong(pesel) < 0) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                for (Osoba osoba : Action.getOsoby()) {
                    if (Long.parseLong(pesel) == osoba.getPesel()) {
                        Action.leczenie(Long.parseLong(pesel), lekarz);
                        Controller.setTable(MainMenu.getTableStatic(), currentTableState);
                        Controller.closeWindow(frame);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Osoba o podanym peselu nie istnieje.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
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
                for (Osoba osoba : Action.getOsoby()) {
                    if (Long.parseLong(pesel) == osoba.getPesel()) {
                        Action.szczepienie(Long.parseLong(pesel), pielegniarka, nazwa);
                        Controller.setTable(MainMenu.getTableStatic(), currentTableState);
                        Controller.closeWindow(frame);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Osoba o podanym peselu nie istnieje.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void wyszukiwanie(String imie, String nazwisko, String wiek, String pesel, String pensjaMin, String specjalizacja, JFrame frame) {
        try {
            if (!pesel.equals("") && (Long.parseLong(pesel) <= 0) || (!wiek.equals("") && Integer.parseInt(wiek) <= 0) || (!pensjaMin.equals("") && Integer.parseInt(pensjaMin) <= 0)) {
                JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
            } else {
                long peselLong;
                int wiekInt;
                int pensjaInt;

                if (pesel.equals("")) {
                    peselLong = 0L;
                } else {
                    peselLong = Long.parseLong(pesel);
                }

                if (wiek.equals("")) {
                    wiekInt = 0;
                } else {
                    wiekInt = Integer.parseInt(wiek);
                }

                if (pensjaMin.equals("")) {
                    pensjaInt = 0;
                } else {
                    pensjaInt = Integer.parseInt(pensjaMin);
                }

                ArrayList<Osoba> osoby = Action.wyszukiwanie(imie, nazwisko, wiekInt, peselLong, pensjaInt, specjalizacja);
                boolean pacjentFlag = false;
                boolean pracownikFlag = false;

                for (Osoba osoba : osoby) {
                    if (osoba instanceof Pacjent) {
                        pacjentFlag = true;
                    } else if (osoba instanceof Pracownik) {
                        pracownikFlag = true;
                    }
                }

                if (pacjentFlag && pracownikFlag) {
                    MainMenu.getTableStatic().setModel(new MainMenu.OsobaTableModel(osoby));
                } else if (pacjentFlag) {
                    MainMenu.getTableStatic().setModel(new MainMenu.PacjentTableModel(osoby));
                } else if (pracownikFlag) {
                    MainMenu.getTableStatic().setModel(new MainMenu.PracownikTableModel(osoby));
                } else {
                    JOptionPane.showMessageDialog(frame, "Nie znaleziono pasujących danych.", "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
                }

                Controller.closeWindow(frame);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Sprawdź poprawność wprowadzonych danych.", "Błędne dane", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static class PeselWiekException extends Exception {
        public PeselWiekException(String errorMessage) {
            super(errorMessage);
        }
    }
}


