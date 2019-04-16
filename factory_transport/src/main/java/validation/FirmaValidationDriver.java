package validation;

import model.Kierowca;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FirmaValidationDriver {
    private int id;
    private String imie;
    private String nazwisko;

   private String infoImieOne = "Podaj imie  kierowcy: ";
   private String infoImieTwo = "Imie musi się składać z  2 minimum dwóch znaków: ";

   private String infoNazwiskoOne = "Podaj nazwisko kierowcy: ";
   private String infoNazwiskoTwo = "Nazwisko musi się składać z 2 minimum dwóch znaków: ";

    public int walidacjaID(Scanner scanner){

        try {
            id = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Podany identyfikator nie może zawierać liter.");
        }

        return id;
    }
    public String walidacjaImienia(Scanner scanner){
       imie = walidacjaImieniaNazwiska(scanner,infoImieOne,infoImieTwo);
       return imie;
    }

    public String walidacjaNazwiska(Scanner scanner) {
        nazwisko = walidacjaImieniaNazwiska(scanner,infoNazwiskoOne,infoNazwiskoTwo);
        return nazwisko;
    }

    private String walidacjaImieniaNazwiska(Scanner scanner,String infoImieOne,String infoImieTwo){
        int dlugosc = 0;
        System.out.println(infoImieOne);
        imie = scanner.next();
        dlugosc = imie.length();

        if(dlugosc < 2){
            while (dlugosc < 2){
                System.out.println(infoImieTwo);
                imie = scanner.next();
                dlugosc = imie.length();
            }
            return  imie;
        }else{
            return imie;
        }
    }

    public boolean sprawdzaCzyOsobaIstnieje(int id, List<Kierowca> kierowcyFirmy) {
        List<Kierowca> nowaLista = kierowcyFirmy
                .stream()
                .filter(k -> k.getIdentyfikator() == id)
                .collect(Collectors.toList());

        if(nowaLista.isEmpty()){
            return false;
        }

        return true;
    }
}
