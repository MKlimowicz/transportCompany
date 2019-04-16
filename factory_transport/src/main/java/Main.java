import model.Firma;
import model.Pojazd;
import view.AplicationUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Firma firma = new Firma();
        AplicationUI aplicationUI = new AplicationUI();

        aplicationUI.startAp(firma);
    }
}
