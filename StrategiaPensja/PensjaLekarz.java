package StrategiaPensja;

public class PensjaLekarz implements Pensja {
    @Override
    public int liczeniePensji(int etat) {
        return etat * 300;
    }
}
