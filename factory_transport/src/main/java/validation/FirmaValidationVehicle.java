package validation;

import model.Pojazd;

import java.util.List;
import java.util.stream.Collectors;

public class FirmaValidationVehicle {


    public boolean sprawdzaCzyPojazdIstnieje(int id, List<Pojazd> pojazdyFirmy) {

        List<Pojazd> collect = pojazdyFirmy
                .stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());

        return !collect.isEmpty();

    }

    public boolean sprawdzCzyPojazdNieJestWTrasie(int id, List<Pojazd> pojazdyFirmy) {
        List<Pojazd> collect = pojazdyFirmy
                .stream()
                .filter(p -> p.getId() == id)
                .filter(p -> !p.isCzyWTrasie())
                .collect(Collectors.toList());

        //jesli puste to znaczy ze wyjechal zwroc true
        //jesli jest jakis pojazd to znaczy ze nei wyjechal zwroc false
        return collect.isEmpty();
    }


}
