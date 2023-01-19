package Model.Osoby;

import java.io.Serializable;

public class CertyfikatSzczepienia implements Serializable {
    private String name;
    private int numerSzczepienia;

    public CertyfikatSzczepienia(String name, int numerSzczepienia) {
        this.name = name;
        this.numerSzczepienia = numerSzczepienia;
    }

    public int getNumerSzczepienia() {
        return numerSzczepienia;
    }

    public void setNumerSzczepienia(int numerSzczepienia) {
        this.numerSzczepienia = numerSzczepienia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Choroba: " + name + " numerSzczepienia: " + numerSzczepienia;
    }
}
