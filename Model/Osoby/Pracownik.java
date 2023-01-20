package Model.Osoby;

public abstract class Pracownik extends Osoba {
    private int etat;
    private int pensja;

    public Pracownik(String imie, String nazwisko, long pesel, int wiek, int etat) {
        super(imie, nazwisko, pesel, wiek);
        this.etat = etat;
    }

    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
