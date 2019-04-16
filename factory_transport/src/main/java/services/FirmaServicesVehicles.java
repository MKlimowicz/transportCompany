package services;

import model.Firma;
import model.Kierowca;
import model.Pojazd;

import validation.FirmaValidationVehicle;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FirmaServicesVehicles {
    private Scanner sc = new Scanner(System.in);
    private FirmaValidationVehicle firmaValidationVehicle = new FirmaValidationVehicle();
    private List<Pojazd> flotaFirmy;

    private String name;
    private float ladownosc;
    private float pojemnosc;
    private int id;
    private boolean istnieje;
    private boolean wyjechal;


    public void dodanieNowegoSamochoduDoFirmy(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();

        System.out.println("Witaj, dodamy dziś nowy pojazd. : D");
        System.out.println("Podaj jego nazwe: ");
        name = sc.next();
        System.out.println("Podaj jego ładowność(liczba osob): ");
        ladownosc = sc.nextFloat();
        System.out.println("Podaj jego pojemność(ile kg może zabrać sprzętu): ");
        pojemnosc = sc.nextFloat();

        Pojazd newPojazd = new Pojazd(name, ladownosc, pojemnosc);
        newPojazd.setId(flotaFirmy.size() + 1);

        flotaFirmy.add(newPojazd);


    }

    public void wypisanieSamochodFirmy(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();
        if (flotaFirmy.isEmpty()) {
            System.out.println("Firma nie posiada samochodów");
        } else {
            flotaFirmy.forEach(System.out::println);
        }
    }

    public void liczbaSamochowWFirmie(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();
        System.out.println("Liczba samochodów w flocie: " + flotaFirmy.size());
    }

    public void usuniecieSamochoduZFirmy(Firma firma) {
        List<Pojazd> pojazdyFirmy = firma.getFlotaFirmy();

        printfListCar(firma);

        if (!pojazdyFirmy.isEmpty()) {
            System.out.println("Podaj id kierowcy którego chcesz usunąć:");
            id = sc.nextInt();

            istnieje = firmaValidationVehicle.sprawdzaCzyPojazdIstnieje(id, pojazdyFirmy);

            if (istnieje) {
                List<Pojazd> nowaLista = pojazdyFirmy
                        .stream()
                        .filter(k -> k.getId() != id)
                        .collect(Collectors.toList());

                firma.setFlotaFirmy(nowaLista);
            } else {
                System.out.println("Nie udało sie znaleźć żadnego pojazdu o takim id" + id + ". Sprawdz liste i spróbuj ponownie.");
            }
        }
    }

    public void edycjaSamochodu(Firma firma) {
        List<Pojazd> pojazdyFirmy = firma.getFlotaFirmy();
        int edycjaId;

        printfListCar(firma);

        if (!pojazdyFirmy.isEmpty()) {
            System.out.println("Podaj id pojazdu którego chcesz edytować:");
            edycjaId = sc.nextInt();

            istnieje = firmaValidationVehicle.sprawdzaCzyPojazdIstnieje(edycjaId, pojazdyFirmy);

            if (istnieje) {
                List<Pojazd> pojazdList = pojazdyFirmy
                        .stream()
                        .filter(c -> c.getId() == edycjaId)
                        .collect(Collectors.toList());

                Pojazd pojazd = pojazdList.get(0);

                Pojazd nowyPojazd = edycjaPojazduWybor(pojazd);

                zapisanieNowegoPojazdu(nowyPojazd, firma, pojazdyFirmy, edycjaId);

            } else {
                System.out.println("Podany pojazd o id: " + edycjaId + ". Niestety nie istnieje.");
            }
        }

    }

    private void printfListCar(Firma firma) {
        System.out.println("Lista Pojazdow");
        System.out.println("----------start----------");
        wypisanieSamochodFirmy(firma);
        System.out.println("----------koniec----------");
    }

    private void zapisanieNowegoPojazdu(Pojazd nowyPojazd, Firma firma, List<Pojazd> pojazdyFirmy, int edycjaId) {
        List<Pojazd> lista = pojazdyFirmy
                .stream()
                .filter(c -> c.getId() != edycjaId)
                .collect(Collectors.toList());

        lista.add(nowyPojazd);

        firma.setFlotaFirmy(lista);
    }

    private Pojazd edycjaPojazduWybor(Pojazd pojazd) {

        System.out.println("Co chcesz zmienić ?");
        System.out.println("1.Nazwe");
        System.out.println("2.Ładownośc");
        System.out.println("3.Pojemność");

        int wybor = sc.nextInt();

        switch (wybor) {
            case 1:
                System.out.println("Nowa nazwa");
                name = sc.next();
                pojazd.setNazwa(name);
                break;
            case 2:
                System.out.println("Zmień ładownośc :");
                ladownosc = sc.nextFloat();
                pojazd.setLadownosc(ladownosc);
                break;
            case 3:
                System.out.println("Zmień pojemnosc :");
                pojemnosc = sc.nextFloat();
                pojazd.setLadownosc(pojemnosc);
                break;
        }
        return pojazd;
    }


    public void wyszukajPoLadownosci(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();

        System.out.println("Witam, podaj ladowność min pojazdu jaką oczekujesz(liczba miejsc) ");
        float min = sc.nextFloat();
        System.out.println("Witam, podaj ladowność max pojazdu jaką oczekujesz(liczba miejsc) ");
        float max = sc.nextFloat();

        flotaFirmy
                .stream()
                .filter(c -> c.getLadownosc() >= min)
                .filter(c -> c.getLadownosc() <= max)
                .forEach(System.out::println);

    }

    public void wyszukajPoPojemnosci(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();

        System.out.println("Witam, podaj pojemnosc min pojazdu jaką oczekujesz(KG) ");
        float min = sc.nextFloat();
        System.out.println("Witam, podaj pojemnosc max pojazdu jaką oczekujesz(KG) ");
        float max = sc.nextFloat();

        flotaFirmy
                .stream()
                .filter(c -> c.getPojemnosc() >= min)
                .filter(c -> c.getPojemnosc() <= max)
                .forEach(System.out::println);

    }


    public void wyjazdSamochodu(Firma firma) {
        List<Pojazd> pojazdyFirmy = firma.getFlotaFirmy();

        printfListCar(firma);

        if (!pojazdyFirmy.isEmpty()) {
            System.out.println("Podaj id pojazdu który wyjeżdża:");
            id = sc.nextInt();
            istnieje = firmaValidationVehicle.sprawdzaCzyPojazdIstnieje(id, pojazdyFirmy);
            if (istnieje) {
                wyjechal = firmaValidationVehicle.sprawdzCzyPojazdNieJestWTrasie(id, pojazdyFirmy);
                if (!wyjechal) {
                    Pojazd pojazd = pojazdOId(pojazdyFirmy, id);
                    pojazd.setCzyWTrasie(true);

                    List<Pojazd> nowaLista = listAutPoZmiane(pojazd, pojazdyFirmy);
                    firma.setFlotaFirmy(nowaLista);

                } else {
                    System.out.println("Nie mozna zmieniac");
                }

            } else {
                System.out.println("Pojazd o id: " + id + " nie istnieje. Sprawdz jeszcze raz liste");
            }

        }
    }

    private List<Pojazd> listAutPoZmiane(Pojazd pojazd, List<Pojazd> pojazdyFirmy) {
        List<Pojazd> nowaLista = pojazdyFirmy
                .stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());

        nowaLista.add(pojazd);

        return nowaLista;
    }

    private Pojazd pojazdOId(List<Pojazd> pojazdyFirmy, int id) {
        List<Pojazd> znajdzAutoOId = pojazdyFirmy
                .stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());

        return znajdzAutoOId.get(0);
    }

    public void powrotSamochodu(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();
        listaSamochodowWTrasie(flotaFirmy);

        System.out.println("Podaj id samochodu które wróciło: ");
        id = sc.nextInt();

        istnieje = firmaValidationVehicle.sprawdzaCzyPojazdIstnieje(id, flotaFirmy);

        if (istnieje) {
            wyjechal = firmaValidationVehicle.sprawdzCzyPojazdNieJestWTrasie(id, flotaFirmy);
            if (wyjechal) {
                Pojazd pojazd = pojazdOId(flotaFirmy, id);
                pojazd.setCzyWTrasie(false);
                pojazd.setLiczbaOdbytychKursow();

                List<Pojazd> pojazdList = listAutPoZmiane(pojazd, flotaFirmy);

                firma.setFlotaFirmy(pojazdList);
            }
        } else {
            System.out.println("Pojazd o id: " + id + " nie istnieje. Sprawdz jeszcze raz liste");
        }

    }

    private void listaSamochodowWTrasie(List<Pojazd> flotaFirmy) {
        System.out.println("Lista Pojazdow");
        System.out.println("----------start----------");
        flotaFirmy.stream()
                .filter(Pojazd::isCzyWTrasie)
                .forEach(System.out::println);
        System.out.println("----------koniec----------");
    }

    public void przypiszOsobeDoPojazdu(Firma firma) throws FileNotFoundException {
        FirmaServicesDriver firmaServicesDriver = new FirmaServicesDriver(firma);
        flotaFirmy = firma.getFlotaFirmy();
        listaSamochodowNieTrasie(flotaFirmy);

        System.out.println("Podaj id samochodu do którego chcesz przypisać osobe: ");
        id = sc.nextInt();

        istnieje = firmaValidationVehicle.sprawdzaCzyPojazdIstnieje(id, flotaFirmy);

        if (istnieje) {
            wyjechal = firmaValidationVehicle.sprawdzCzyPojazdNieJestWTrasie(id, flotaFirmy);
            if (!wyjechal) {
                System.out.println("wlazlo");
                Pojazd pojazd = pojazdOId(flotaFirmy, id);
                Kierowca kierowca = firmaServicesDriver.wybierzKierowce(firma);

                List<Kierowca> listaKierowcow = pojazd.getListaKierowcow();

                if (listaKierowcow.size() <= pojazd.getLadownosc()) {
                    istnieje = sprawdzaCzyOsobyNiemaJuzNaLiscie(listaKierowcow, kierowca);

                    if (!istnieje) {
                        listaKierowcow.add(kierowca);
                        List<Pojazd> pojazdList = listAutPoZmiane(pojazd, flotaFirmy);
                        firma.setFlotaFirmy(pojazdList);
                    } else {
                        System.out.println("Osoba którą chcesz dodać do pojazdu jest juz do niego przypisana");
                    }

                } else {
                    System.out.println("Niestety nie ma juz miejsc w pojezdzie.");
                }
            } else {
                System.out.println("Samochod jest w trasie.");
            }
        } else {
            System.out.println("Pojazd o id: " + id + " nie istnieje. Sprawdz jeszcze raz liste");
        }

    }

    private boolean sprawdzaCzyOsobyNiemaJuzNaLiscie(List<Kierowca> listaKierowcow, Kierowca kierowca) {
        List<Kierowca> collect = listaKierowcow
                .stream()
                .filter(c -> c.getIdentyfikator() == kierowca.getIdentyfikator())
                .collect(Collectors.toList());

        return !collect.isEmpty();

    }


    public void listaSamochodowNieTrasie(List<Pojazd> flotaFirmy) {
        System.out.println("Lista Pojazdow");
        System.out.println("----------start----------");
        if (!flotaFirmy.isEmpty()) {
            flotaFirmy.stream()
                    .filter(c -> !c.isCzyWTrasie())
                    .forEach(System.out::println);
        } else {
            System.out.println("Niema dostęnych pojazdów.Wszystkie są w trasie.");
        }

        System.out.println("----------koniec----------");
    }

    public void wypiszPojazdyWTrasie(Firma firma) {
        System.out.println("Lista Pojazdow");
        System.out.println("----------start----------");
        if (!flotaFirmy.isEmpty()) {
            flotaFirmy.stream()
                    .filter(c -> c.isCzyWTrasie())
                    .forEach(System.out::println);
        } else {
            System.out.println("Niema dostęnych pojazdów");
        }

        System.out.println("----------koniec----------");
    }

    public void samochodyZNajmnijeszaINajwieksza(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();

        flotaFirmy
                .stream()
                .sorted(Comparator.comparing(Pojazd::getLiczbaOdbytychKursow))
                .map(Pojazd::getLiczbaOdbytychKursow)
                .forEachOrdered(System.out::println);

    }

    public void wypiszOsobeZPojazdu(Firma firma) {
        flotaFirmy = firma.getFlotaFirmy();

        List<Pojazd> collect = flotaFirmy
                .stream()
                .filter(c -> !c.getListaKierowcow()
                        .isEmpty())
                .collect(Collectors.toList());


        System.out.println("Lista Pojazdow");
        System.out.println("-----start-----");
        if (!collect.isEmpty()) {
            collect.forEach(System.out::println);
        } else {
            System.out.println("Niema pojazdów z przypisanymi osobami");
        }
        System.out.println("----------koniec----------");

        System.out.println("Podaj id pojazdu: ");
        id = sc.nextInt();

        List<Pojazd> pojazdDoZmiany = collect
                    .stream()
                    .filter(c -> c.getId() == id)
                    .collect(Collectors.toList());


        if(!collect.isEmpty()){
            Pojazd pojazd = pojazdDoZmiany.get(0);
            List<Kierowca> listaKierowcow = pojazd.getListaKierowcow();

            listaKierowcow.forEach(System.out::println);

            System.out.println("Podaj id osoby która chcesz usunać: ");
            id = sc.nextInt();


            listaKierowcow = listaKierowcow
                    .stream()
                    .filter(c -> c.getIdentyfikator() != id)
                    .collect(Collectors.toList());

            pojazd.setListaKierowcow(listaKierowcow);
            int id = pojazd.getId();

            List<Pojazd> collect1 = flotaFirmy
                    .stream()
                    .filter(c -> c.getId() != id)
                    .collect(Collectors.toList());
            collect1.add(pojazd);
            firma.setFlotaFirmy(collect1);




        }else {
            System.out.println("Pojazd o podanym ID nie istnieje.");
        }

    }

}
