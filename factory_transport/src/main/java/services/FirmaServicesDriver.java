package services;

import model.Firma;
import model.Kierowca;
import validation.FirmaValidationDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class FirmaServicesDriver {

    private Scanner scanner = new Scanner(System.in);
    private FirmaValidationDriver firmaValidationDriver = new FirmaValidationDriver();

    private int id;
    private int wybor;
    private String imie;
    private String nazwisko;
    private boolean istnieje;

    public FirmaServicesDriver(Firma firma) throws FileNotFoundException {
        wczytajKierowcow(firma);
    }

    public void dodanieNowegoKierowcyDoFirmy(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

            id = kierowcyFirmy.size() + 1;
            imie = firmaValidationDriver.walidacjaImienia(scanner);
            nazwisko = firmaValidationDriver.walidacjaNazwiska(scanner);

            kierowcyFirmy.add(new Kierowca(id,imie,nazwisko));
            firma.setKierowcyFirmy(kierowcyFirmy);

    }

    public void wypisanieKierowcowFirmy(Firma firma){
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();
        wypisanieListyKierowcow(kierowcyFirmy);
    }

    public void usuniecieKierowcyZFirmy(Firma firma){
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

        System.out.println("Lista kierowców");
        System.out.println("----------start----------");
        wypisanieKierowcowFirmy(firma);
        System.out.println("----------koniec----------");

        if(!kierowcyFirmy.isEmpty()) {
            System.out.println("Podaj id kierowcy którego chcesz usunąć:");
            id = firmaValidationDriver.walidacjaID(scanner);

            istnieje = firmaValidationDriver.sprawdzaCzyOsobaIstnieje(id, kierowcyFirmy);

            if (istnieje) {
                List<Kierowca> nowaLista = kierowcyFirmy
                        .stream()
                        .filter(k -> k.getIdentyfikator() != id)
                        .collect(Collectors.toList());

                firma.setKierowcyFirmy(nowaLista);
            } else {
                System.out.println("Nie udało sie znaleźć osoby o takim id" + id + ". Sprawdz i spróbuj ponownie.");
            }
        }
    }

    public void edycjaKierowcy(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();
        int edycjaId;

        System.out.println("Lista kierowców");
        System.out.println("----------start----------");
        wypisanieKierowcowFirmy(firma);
        System.out.println("----------koniec----------");

        if(!kierowcyFirmy.isEmpty()) {
            System.out.println("Podaj id kierowcy którego chcesz edytować:");
            edycjaId = firmaValidationDriver.walidacjaID(scanner);

            List<Kierowca> kierowcaList = kierowcyFirmy
                    .stream()
                    .filter(c -> c.getIdentyfikator() == edycjaId)
                    .collect(Collectors.toList());

            Kierowca kierowca = kierowcaList.get(0);


            Kierowca nowyKierowca = edycjaKierowcyWybor(kierowca);


            zapisanieNowegoKierowcy(nowyKierowca, firma, kierowcyFirmy, edycjaId);
        }


    }

    private void zapisanieNowegoKierowcy(Kierowca nowyKierowca,
                                         Firma firma,
                                         List<Kierowca> kierowcyFirmy,
                                         int edycjaId) {
        List<Kierowca> lista = kierowcyFirmy
                .stream()
                .filter(c -> c.getIdentyfikator() != edycjaId)
                .collect(Collectors.toList());

        lista.add(nowyKierowca);

        firma.setKierowcyFirmy(lista);
    }

    private Kierowca edycjaKierowcyWybor(Kierowca kierowca) {

        System.out.println("Co chcesz zmienić ?");
        System.out.println("1.Imie");
        System.out.println("2.Nazwisko");
        System.out.println("3.Identyfikator");
        wybor = scanner.nextInt();

        switch (wybor){
            case 1:
                System.out.println("Nowe imie");
                imie = scanner.next();
                kierowca.setImie(imie);
                break;
            case 2:
                System.out.println("Nowe nazwisko");
                nazwisko = scanner.next();
                kierowca.setNazwisko(nazwisko);
                break;
            case 3:
                System.out.println("Nowy identyfikator");
                id = scanner.nextInt();
                kierowca.setIdentyfikator(id);
                break;
        }
        return kierowca;
    }

    public void liczbaKierowcowWFirmie(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();
        System.out.println("Liczba kierowców w firmie: " + kierowcyFirmy.size());
    }


    public void wyszukajPoImieniu(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

        imie = firmaValidationDriver.walidacjaImienia(scanner);

        if(kierowcyFirmy.isEmpty()){
            System.out.println("Lista jest pusta");
        }else {
            List<Kierowca> collect = kierowcyFirmy
                    .stream()
                    .filter(c -> c.getImie()
                            .toLowerCase()
                            .contains(
                                    imie.toLowerCase()
                            ))
                    .collect(Collectors.toList());

            wypisanieListyKierowcow(collect);
        }


    }
    public void wyszukajPoNazwisko(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

        nazwisko = firmaValidationDriver.walidacjaNazwiska(scanner);

        if(kierowcyFirmy.isEmpty()){
            System.out.println("Lista jest pusta");
        }else {
            List<Kierowca> collect = kierowcyFirmy
                    .stream()
                    .filter(c -> c.getNazwisko()
                            .toLowerCase()
                            .contains(
                                    nazwisko.toLowerCase()
                            ))
                    .collect(Collectors.toList());

            wypisanieListyKierowcow(collect);
        }
    }
    public void wyszukajPoImieniuNazwaisku(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

        imie = firmaValidationDriver.walidacjaImienia(scanner);
        nazwisko = firmaValidationDriver.walidacjaNazwiska(scanner);

        if(kierowcyFirmy.isEmpty()){
            System.out.println("Lista jest pusta");
        }else {
            List<Kierowca> collect = kierowcyFirmy
                    .stream()
                    .filter(c -> c.getNazwisko()
                            .toLowerCase()
                            .contains(
                                    nazwisko.toLowerCase()
                            ))
                    .filter(c -> c.getImie()
                            .toLowerCase()
                            .contains(
                                    imie.toLowerCase()
                            ))
                    .collect(Collectors.toList());

            wypisanieListyKierowcow(collect);
        }
    }

    public void wyszukajPoId(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

        System.out.println("Podaj numer identyfikatora: ");
        id = firmaValidationDriver.walidacjaID(scanner);

        if(kierowcyFirmy.isEmpty()){
            System.out.println("Lista jest pusta");
        }else {
            List<Kierowca> collect = kierowcyFirmy
                    .stream()
                    .filter(c -> c.getIdentyfikator() == id)
                    .collect(Collectors.toList());
            wypisanieListyKierowcow(collect);
        }
    }
    private void wypisanieListyKierowcow(List<Kierowca> kierowcyFirmy){
        if(kierowcyFirmy.isEmpty()){
            System.out.println("Lista jest pusta");
        }else {
            kierowcyFirmy
                    .forEach(System.out::println);
        }
    }


    public void wczytajKierowcow(Firma firma) throws FileNotFoundException {
        Scanner odczyt = new Scanner(new File("kierowcy.txt"));
        List<Kierowca> newLista = new ArrayList<Kierowca>();
        String text;
        while (odczyt.hasNextLine()){
            text = odczyt.nextLine();
            String[] s = text.split(" ");

            id = Integer.parseInt(s[0]) ;
            imie = s[1];
            nazwisko = s[2];

            newLista.add(new Kierowca(id,imie,nazwisko));


        }

        firma.setKierowcyFirmy(newLista);

    }
    Kierowca wybierzKierowce(Firma firma) {
        List<Kierowca> kierowcyFirmy = firma.getKierowcyFirmy();

        System.out.println("---start---");
        kierowcyFirmy
                .forEach(System.out::println);
        System.out.println("---koniec---");

        System.out.println("Podaj id kierowcy: ");
        id = scanner.nextInt();

        istnieje = firmaValidationDriver.sprawdzaCzyOsobaIstnieje(id, kierowcyFirmy);

        if(istnieje){
            Kierowca kierowca = kierowcyFirmy
                    .stream()
                    .filter(c -> c.getIdentyfikator() == id)
                    .collect(Collectors.toList())
                    .get(0);

            return kierowca;
        }else{
            return null;
        }

    }
}
