package Model.Osoby;

import java.util.Random;
import java.util.Scanner;

public class Pielegniarka extends Pracownik {
    public Pielegniarka(String imie, String nazwisko, long pesel, int wiek, int pensja) {
        super(imie, nazwisko, pesel, wiek, pensja);
    }

    public void wykonajSzczepienie(Pacjent pacjent) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int number = random.nextInt(1000, 2000);

        System.out.println("Podaj przeciwko czemu zaszczepic pacjenta: ");

        CertyfikatSzczepienia certyfikat = new CertyfikatSzczepienia(scanner.next(), number);

        pacjent.getCertyfikatySzczepienia().add(certyfikat);

        System.out.println("Nadany numer certyfikatu szczepienia: " + number);
    }

    @Override
    public String toString() {
        return "Pielegniarka: " + super.toString();
    }
}
