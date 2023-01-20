package StrategiaKosztyLeczenia;

public class KosztyDziecko implements Koszty {
    @Override
    public int liczenieKosztowLeczenia(int wiek) {
        return (5) * (120 - wiek); //Koszt leczenia dziecka obliczamy tak jak koszt leczenia doroslego i dajemy 50% rabatu
    }
}
