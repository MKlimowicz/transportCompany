package view;

import model.Firma;
import services.FirmaServicesDriver;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class KierowcaUI {
    private Scanner scanner = new Scanner(System.in);

    private int wybor;
    private boolean flag = true;

    public void startEdycjaKierwcyUI(Firma firma) throws FileNotFoundException {

        FirmaServicesDriver firmaServicesDriver = new FirmaServicesDriver(firma);

        while (flag) {
            System.out.println("Witaj w aplikacji");
            System.out.println("Wybierz co byś chciał zrobic:");
            System.out.println("1.Dodać nowego kierowce");
            System.out.println("2.Wypisać liste kierowców");
            System.out.println("3.Wyswietl liczbe kierowców");
            System.out.println("4.Usunać kierowce");
            System.out.println("5.Edytuj kierowce");
            System.out.println("6.Wyszukaj kierowce");
            System.out.println("7.Wyłącz aplikacje");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    firmaServicesDriver.dodanieNowegoKierowcyDoFirmy(firma);
                    break;
                case 2:
                    firmaServicesDriver.wypisanieKierowcowFirmy(firma);
                    break;
                case 3:
                    firmaServicesDriver.liczbaKierowcowWFirmie(firma);
                    break;
                case 4:
                    firmaServicesDriver.usuniecieKierowcyZFirmy(firma);
                    break;
                case 5:
                    firmaServicesDriver.edycjaKierowcy(firma);
                    break;
                case 6:
                    wyszukajKierowce(firma,firmaServicesDriver);
                    break;
                case 7:
                    flag = false;
                    break;
            }
        }
    }


    private void wyszukajKierowce(Firma firma,FirmaServicesDriver firmaServicesDriver) {
        boolean flag = true;

        while (flag) {

            System.out.println("Witam, po czym byś chciał wyszukać kierowce ?");
            System.out.println("1.imie");
            System.out.println("2.nazwisko");
            System.out.println("3.Imie oraz nazwisko");
            System.out.println("4.Identyfikator");
            System.out.println("5.Wyjdz");
            System.out.println("Podaj numer: ");
            wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    firmaServicesDriver.wyszukajPoImieniu(firma);
                    break;
                case 2:
                    firmaServicesDriver.wyszukajPoNazwisko(firma);
                    break;
                case 3:
                    firmaServicesDriver.wyszukajPoImieniuNazwaisku(firma);
                    break;
                case 4:
                    firmaServicesDriver.wyszukajPoId(firma);
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
