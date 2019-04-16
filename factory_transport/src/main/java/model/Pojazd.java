package model;

import java.util.ArrayList;
import java.util.List;

public class Pojazd {
    private int id;
    private String nazwa;
    private float ladownosc;
    private float pojemnosc;
    private List<Kierowca> listaKierowcow;
    private boolean czyWTrasie;
    private int liczbaOdbytychKursow;

    public Pojazd(String nazwa, float ladownosc, float pojemnosc) {
        this.nazwa = nazwa;
        this.ladownosc = ladownosc;
        this.pojemnosc = pojemnosc;
        this.listaKierowcow = new ArrayList<Kierowca>();
        this.czyWTrasie = false;
        this.liczbaOdbytychKursow = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getLadownosc() {
        return ladownosc;
    }

    public void setLadownosc(float ladownosc) {
        this.ladownosc = ladownosc;
    }

    public float getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(float pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public List<Kierowca> getListaKierowcow() {
        return listaKierowcow;
    }

    public void setListaKierowcow(List<Kierowca> kierowcy) {
        listaKierowcow = kierowcy;
    }

    public boolean isCzyWTrasie() {
        return czyWTrasie;
    }

    public void setCzyWTrasie(boolean czyWTrasie) {
        this.czyWTrasie = czyWTrasie;
    }

    public int getLiczbaOdbytychKursow() {
        return liczbaOdbytychKursow;
    }

    public void setLiczbaOdbytychKursow() {
        liczbaOdbytychKursow++;
    }

    @Override
    public String toString() {
        return "Pojazd{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", ladownosc=" + ladownosc +
                ", pojemnosc=" + pojemnosc +
                ", listaKierowcow=" + listaKierowcow +
                ", czyWTrasie=" + czyWTrasie +
                ", liczbaOdbytychKursow=" + liczbaOdbytychKursow +
                '}';
    }
}
