package Model.Osoby;

import java.io.Serializable;

public abstract class Osoba implements Serializable {
    private final String imie, nazwisko;
    private final long pesel;
    private final int wiek;

    public Osoba(String imie, String nazwisko, long pesel, int wiek) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wiek = wiek;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public long getPesel() {
        return pesel;
    }

    public int getWiek() {
        return wiek;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + "\nWiek: " + wiek + " Pesel: " + pesel;
    }
}
