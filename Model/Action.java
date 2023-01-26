package Model;

import Model.Osoby.*;
import StrategiaKosztyLeczenia.Koszty;
import StrategiaKosztyLeczenia.KosztyDorosly;
import StrategiaKosztyLeczenia.KosztyDziecko;
import StrategiaPensja.Pensja;
import StrategiaPensja.PensjaLekarz;
import StrategiaPensja.PensjaPielegniarka;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Action {
    private static ArrayList<Osoba> osoby = new ArrayList<>();

    @Deprecated
    public static void stanPoczatkowy() {

        //Wczytanie poczatkowej bazy danych w przypadku gdy nie byla ona wczesniej utworzona;
        Koszty koszty = new KosztyDorosly();
        Pensja pensja = new PensjaLekarz();

        Lekarz chirurg = new Lekarz("Jan", "Kowalski", 92092295748L, 31, 40, "chirurg");
        chirurg.setPensja(pensja.liczeniePensji(chirurg.getEtat()));
        Lekarz ogolny = new Lekarz("Piotr", "Nowak", 98032832183L, 25, 50, "ogolny");
        ogolny.setPensja(pensja.liczeniePensji(ogolny.getEtat()));

        pensja = new PensjaPielegniarka();

        Pielegniarka pielegniarka = new Pielegniarka("Anna", "Kowalska", 82071966417L, 41, 40);
        pielegniarka.setPensja(pensja.liczeniePensji(pielegniarka.getEtat()));

        Dorosly dorosly1 = new Dorosly("Jakub", "Mak", 51090549649L, 72);
        dorosly1.setKosztyLeczenia(koszty.liczenieKosztowLeczenia(dorosly1.getWiek()));

        koszty = new KosztyDziecko();

        Dziecko dziecko1 = new Dziecko("Anna", "Górska", 17282226149L, 6);
        dziecko1.setKosztyLeczenia(koszty.liczenieKosztowLeczenia(dziecko1.getWiek()));

        osoby.add(chirurg);
        osoby.add(ogolny);
        osoby.add(pielegniarka);
        osoby.add(dorosly1);
        osoby.add(dziecko1);
    }

    @SuppressWarnings("unchecked")
    public static void wczytajDane() {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("bazadanych.ser"));
            Object obj1 = is.readObject();
            osoby = (ArrayList<Osoba>) obj1;
            is.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wczytano domyslna baze danych");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void zapiszDane() {
        try {
            ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("bazadanych.ser"));
            so.writeObject(osoby);
            so.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Osoba> wyswietlPracownikow() {
        ArrayList<Osoba> pracownicy = new ArrayList<>();
        for (Osoba osoba : osoby) {
            if (osoba instanceof Pracownik) {
                pracownicy.add(osoba);
            }
        }
        return pracownicy;
    }

    public static ArrayList<Osoba> wyswietlPacjentow() {
        ArrayList<Osoba> pacjenci = new ArrayList<>();
        for (Osoba osoba : osoby) {
            if (osoba instanceof Pacjent) {
                pacjenci.add(osoba);
            }
        }
        return pacjenci;
    }

    public static ArrayList<Osoba> getOsoby() {
        return osoby;
    }

    public static void addLekarz(String imie, String nazwisko, String specjalizacja, int wiek, int etat, long pesel) {
        //Ustawienie wybranej strategii przyznawania pensji
        Pensja pensja = new PensjaLekarz();

        Lekarz lekarz = new Lekarz(imie, nazwisko, pesel, wiek, etat, specjalizacja);

        //Ustawienie pensji lekarza
        lekarz.setPensja(pensja.liczeniePensji(lekarz.getEtat()));

        //Dodanie lekarza do bazy danych
        osoby.add(lekarz);
    }

    public static void addPielegniarka(String imie, String nazwisko, int wiek, int etat, long pesel) {
        //Ustawienie wybranej strategii przyznawania pensji
        Pensja pensja = new PensjaPielegniarka();

        Pielegniarka pielegniarka = new Pielegniarka(imie, nazwisko, pesel, wiek, etat);

        //Ustawienie pensji lekarza
        pielegniarka.setPensja(pensja.liczeniePensji(pielegniarka.getEtat()));

        //Dodanie lekarza do bazy danych
        osoby.add(pielegniarka);
    }

    public static void addPacjent(String imie, String nazwisko, int wiek, long pesel) {
        Koszty koszty;

        if (wiek < 18) {
            Dziecko dziecko = new Dziecko(imie, nazwisko, pesel, wiek);

            //Zastosowanie strategii liczenia kosztow leczenia dla dziecka
            koszty = new KosztyDziecko();
            dziecko.setKosztyLeczenia(koszty.liczenieKosztowLeczenia(dziecko.getWiek()));

            osoby.add(dziecko);
        } else {
            Dorosly dorosly = new Dorosly(imie, nazwisko, pesel, wiek);

            //Zastosowanie strategii liczenia kosztow leczenia dla doroslego
            koszty = new KosztyDorosly();
            dorosly.setKosztyLeczenia(koszty.liczenieKosztowLeczenia(dorosly.getWiek()));

            osoby.add(dorosly);
        }
    }

    public static String getNameFromPesel(long pesel) {
        for (Osoba osoba : osoby) {
            if (pesel == osoba.getPesel()) {
                return osoba.getImie();
            }
        }
        return "";
    }

    public static String getSurnameFromPesel(long pesel) {
        for (Osoba osoba : osoby) {
            if (pesel == osoba.getPesel()) {
                return osoba.getNazwisko();
            }
        }
        return "";
    }

    public static String getWiekFromPesel(long pesel) {
        for (Osoba osoba : osoby) {
            if (pesel == osoba.getPesel()) {
                return String.valueOf(osoba.getWiek());
            }
        }
        return "";
    }

    public static String getSzczepienieFromPesel(long pesel) {
        for (Osoba osoba : osoby) {
            if (pesel == osoba.getPesel() && osoba instanceof Pacjent pacjent) {

                if (pacjent.getCertyfikatySzczepienia().size() == 0) {
                    return "Brak szczepień";
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (CertyfikatSzczepienia certyfikat : pacjent.getCertyfikatySzczepienia()) {
                    stringBuilder.append(certyfikat).append("\n");
                }

                return stringBuilder.toString();
            }
        }
        return "";
    }

    public static void addChoroba(long pesel) {
        for (Osoba osoba : osoby) {
            if (osoba instanceof Pacjent) {
                if (pesel == osoba.getPesel()) {
                    ((Pacjent) osoba).setCzyChory(true);
                }
            }
        }
    }

    public static void leczenie(long pesel, Lekarz lekarz) {
        Pacjent pacjent;

        for (Osoba osoba : osoby) {
            if (osoba instanceof Pacjent) {
                if (pesel == osoba.getPesel()) {
                    pacjent = (Pacjent) osoba;
                    lekarz.leczPacjenta(pacjent);
                }
            }
        }
    }

    public static void szczepienie(long pesel, Pielegniarka pielegniarka, String nazwa) {
        Pacjent pacjent;

        for (Osoba osoba : osoby) {
            if (osoba instanceof Pacjent) {
                if (pesel == osoba.getPesel()) {
                    pacjent = (Pacjent) osoba;
                    pielegniarka.wykonajSzczepienie(pacjent, nazwa);
                }
            }
        }
    }

    public static ArrayList<Osoba> wyszukiwanie(String imie, String nazwisko, int wiek, long pesel, int pensjaMin, String specjalizacja) {
        ArrayList<Osoba> record = new ArrayList<>();

        for (Osoba osoba : osoby) {
            if (osoba.getImie().toLowerCase().contains(imie.toLowerCase()) && osoba.getNazwisko().toLowerCase().contains(nazwisko.toLowerCase()) && ((wiek == 0) || (osoba.getWiek() == wiek)) && ((pesel == 0) || (osoba.getPesel() == pesel)) && (pensjaMin == 0 || (((osoba instanceof Pracownik)) && (((Pracownik) osoba).getPensja() >= pensjaMin))) && (specjalizacja.equals("") || (((osoba instanceof Lekarz)) && (((Lekarz) osoba).getSpecjalizacja().toLowerCase().contains(specjalizacja.toLowerCase()))))) {
                record.add(osoba);
            }
        }

        return record;
    }

    public static void exportToCSV(JFrame frame) {
        try (FileWriter fileWriter = new FileWriter("osoby.csv")) {
            for (Osoba osoba : osoby) {
                if (osoba instanceof Pacjent) {
                    fileWriter.write("pacjent," + osoba.getImie() + "," + osoba.getNazwisko() + "," + osoba.getWiek() + "," + osoba.getPesel() + "," + ((Pacjent) osoba).isChory() + "," + ((Pacjent) osoba).getKosztyLeczenia());
                } else if (osoba instanceof Pielegniarka) {
                    fileWriter.write("pielegniarka," + osoba.getImie() + "," + osoba.getNazwisko() + "," + osoba.getWiek() + "," + osoba.getPesel() + "," + ((Pielegniarka) osoba).getPensja());
                } else if (osoba instanceof Lekarz) {
                    fileWriter.write("lekarz," + osoba.getImie() + "," + osoba.getNazwisko() + "," + osoba.getWiek() + "," + osoba.getPesel() + "," + ((Lekarz) osoba).getPensja() + "," + ((Lekarz) osoba).getSpecjalizacja());
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Wystąpił nieoczekiwany błąd w eksportowaniu plików", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
