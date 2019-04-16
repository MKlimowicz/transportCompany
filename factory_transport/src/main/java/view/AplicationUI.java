package view;

import model.Firma;
import services.FirmaServicesDriver;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AplicationUI {
    private Scanner scanner = new Scanner(System.in);

    private KierowcaUI kierowcaUI = new KierowcaUI();
    private PojazdUI pojazdUI = new PojazdUI();

    private boolean flag = true;

    public void startAp(Firma firma) throws FileNotFoundException {

        FirmaServicesDriver firmaServicesDriver = new FirmaServicesDriver(firma);

                while (flag) {
                    System.out.println("Witaj w aplikacji");
                    System.out.println("Wybierz co byś chciał zrobic:");
                    System.out.println("1.Opcje dla kierowcy");
                    System.out.println("2.Opcje dla pojazdu");
                    System.out.println("3.Wyłącz aplikacje");
                    int wybor = scanner.nextInt();

                    switch (wybor) {
                        case 1:
                            kierowcaUI.setFlag(true);
                            kierowcaUI.startEdycjaKierwcyUI(firma);
                            break;
                        case 2:
                            pojazdUI.setFlag(true);
                            pojazdUI.startOpcjeDlaPojazdowUI(firma);
                            break;
                case 3:
                    flag = false;
                    break;
            }
        }


    }



}
