package Model.Osoby;

import java.util.Random;

public class Pielegniarka extends Pracownik {
    public Pielegniarka(String imie, String nazwisko, long pesel, int wiek, int pensja) {
        super(imie, nazwisko, pesel, wiek, pensja);
    }

    public void wykonajSzczepienie(Pacjent pacjent, String nazwa) {
        Random random = new Random();
        int number = random.nextInt(1000, 2000);

        CertyfikatSzczepienia certyfikat = new CertyfikatSzczepienia(nazwa, number);
        pacjent.getCertyfikatySzczepienia().add(certyfikat);
    }
}
