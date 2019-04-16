package model;

public class Kierowca {
    private int identyfikator;
    private String imie;
    private String nazwisko;

    public Kierowca(int identyfikator, String imie, String nazwisko) {
        this.identyfikator = identyfikator;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(int identyfikator) {
        this.identyfikator = identyfikator;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Kierowca{" +
                "identyfikator=" + identyfikator +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
