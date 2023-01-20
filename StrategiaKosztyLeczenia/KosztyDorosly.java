package StrategiaKosztyLeczenia;

public class KosztyDorosly implements Koszty {
    @Override
    public int liczenieKosztowLeczenia(int wiek) {
        return (1000) * (120 - wiek) / 100; //Koszt leczenia doroslego obliczamy na podstawie jego wieku
    }
}