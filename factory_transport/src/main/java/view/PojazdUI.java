package view;

import model.Firma;
import services.FirmaServicesDriver;
import services.FirmaServicesVehicles;

import java.io.FileNotFoundException;
import java.util.Scanner;




public class PojazdUI {
    private Scanner scanner = new Scanner(System.in);

    private int wybor;
    private boolean flag = true;

    public void startOpcjeDlaPojazdowUI(Firma firma) throws FileNotFoundException {

        FirmaServicesVehicles firmaServicesVehicles = new FirmaServicesVehicles();

        while (flag) {
            System.out.println("Witaj w aplikacji");
            System.out.println("Wybierz co byś chciał zrobic:");
            System.out.println("1.Dodać nowy samochod");
            System.out.println("2.Wypisać liste samochodów");
            System.out.println("3.Wyswietl liczbe samochodów");
            System.out.println("4.Usunać samochodu");
            System.out.println("5.Edytuj samochod");
            System.out.println("6.Wyszukaj samochod");
            System.out.println("7.Wyjazd samochodu");
            System.out.println("8.Powrót samochodu");
            System.out.println("9.Przypisz osobe do pojazdu");
            System.out.println("10.Wypisz osobe z danego pojazdu");
            System.out.println("11.Wypisz samochody od najmniejszej ilosci odbytych kursow do najwiekszej.");
            System.out.println("12.Wyłącz aplikacje");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    firmaServicesVehicles.dodanieNowegoSamochoduDoFirmy(firma);
                    break;
                case 2:
                    firmaServicesVehicles.wypisanieSamochodFirmy(firma);
                    break;
                case 3:
                    firmaServicesVehicles.liczbaSamochowWFirmie(firma);
                    break;
                case 4:
                    firmaServicesVehicles.usuniecieSamochoduZFirmy(firma);
                    break;
                case 5:
                    firmaServicesVehicles.edycjaSamochodu(firma);
                    break;
                case 6:
                    wyszukajSamochod(firma,firmaServicesVehicles);
                    break;
                case 7:
                    firmaServicesVehicles.wyjazdSamochodu(firma);
                    break;
                case 8:
                    firmaServicesVehicles.powrotSamochodu(firma);
                    break;
                case 9:
                    firmaServicesVehicles.przypiszOsobeDoPojazdu(firma);
                    break;
                case 10:
                    firmaServicesVehicles.wypiszOsobeZPojazdu(firma);
                    break;
                case 11:
                    firmaServicesVehicles.samochodyZNajmnijeszaINajwieksza(firma);
                    break;
                case 12:
                    flag = false;
                    break;
            }
        }


    }

    private void wyszukajSamochod(Firma firma,FirmaServicesVehicles firmaServicesVehicles) {
        boolean flag = true;

        while (flag) {

            System.out.println("Witam, po czym byś chciał wyszukać pojazdy ?");
            System.out.println("1.Dostępne");
            System.out.println("2.W trasie");
            System.out.println("3.Ladowność");
            System.out.println("4.Pojemność");
            System.out.println("5.Wyjdz");
            System.out.println("Podaj numer: ");
            wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    firmaServicesVehicles.listaSamochodowNieTrasie(firma.getFlotaFirmy());
                    break;
                case 2:
                    firmaServicesVehicles.wypiszPojazdyWTrasie(firma);
                    break;
                case 3:
                    firmaServicesVehicles.wyszukajPoLadownosci(firma);
                    break;
                case 4:
                    firmaServicesVehicles.wyszukajPoPojemnosci(firma);
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
