package model;

import java.util.ArrayList;
import java.util.List;

public class Firma {
   private List<Pojazd> flotaFirmy;
   private List<Kierowca> kierowcyFirmy;

     public Firma() {
        this.flotaFirmy = new ArrayList<Pojazd>();
        this.kierowcyFirmy = new ArrayList<Kierowca>();
    }

    public List<Pojazd> getFlotaFirmy() {
        return flotaFirmy;
    }

    public void setFlotaFirmy(List<Pojazd> flotaFirmy) {
        this.flotaFirmy = flotaFirmy;
    }

    public List<Kierowca> getKierowcyFirmy() {
        return kierowcyFirmy;
    }

    public void setKierowcyFirmy(List<Kierowca> kierowcyFirmy) {
        this.kierowcyFirmy = kierowcyFirmy;
    }
}
