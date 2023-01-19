package StrategiaPensja;

public class PensjaPielegniarka implements Pensja {
    @Override
    public int liczeniePensji(int etat) {
        return etat * 200;
    }
}
