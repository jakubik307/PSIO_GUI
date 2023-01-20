package Model.Osoby;

import java.io.Serializable;

public class CertyfikatSzczepienia implements Serializable {
    private final String name;
    private final int numerSzczepienia;

    public CertyfikatSzczepienia(String name, int numerSzczepienia) {
        this.name = name;
        this.numerSzczepienia = numerSzczepienia;
    }

    @Override
    public String toString() {
        return "choroba: " + name + "  |  numer szczepienia: " + numerSzczepienia;
    }
}
