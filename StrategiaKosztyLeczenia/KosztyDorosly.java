package StrategiaKosztyLeczenia;

public class KosztyDorosly implements Koszty {
    @Override
    public int liczenieKosztowLeczenia(int wiek) {
        int koszty = (10) * (120 - wiek); //Koszt leczenia doroslego obliczamy na podstawie jego wieku

        try {
            if (koszty < 0) {
                throw new UjemneKosztyException("koszty nie mogą wyjść ujemne");
            } else {
                return koszty;
            }
        } catch (UjemneKosztyException e) {
            return 0;
        }
    }

    public static class UjemneKosztyException extends Exception {
        public UjemneKosztyException(String errorMessage) {
            super(errorMessage);
        }
    }
}