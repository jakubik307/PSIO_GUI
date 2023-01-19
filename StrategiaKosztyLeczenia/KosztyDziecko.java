package StrategiaKosztyLeczenia;

public class KosztyDziecko implements Koszty {
    @Override
    public int liczenieKosztowLeczenia(int wiek) {
        return (1000) * (120 - wiek) / 200; //Koszt leczenia dziecka obliczamy tak jak koszt leczenia doroslego i dajemy 50% rabatu
    }
}
