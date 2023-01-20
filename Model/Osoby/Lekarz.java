package Model.Osoby;

public class Lekarz extends Pracownik {
    private final String specjalizacja;

    public Lekarz(String imie, String nazwisko, long pesel, int wiek, int etat, String specjalizacja) {
        super(imie, nazwisko, pesel, wiek, etat);
        this.specjalizacja = specjalizacja;
    }

    public void leczPacjenta(Pacjent pacjent) {
        pacjent.setCzyChory(false);
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }
}
