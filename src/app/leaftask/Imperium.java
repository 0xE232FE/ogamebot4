package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.planety.Planety;
import com.Log;
import com.Waiter;
import ogame.LeftMenu;
import ogame.Statusy;
import ogame.budynki.Budynek;
import ogame.budynki.Budynki;
import ogame.budynki.BudynkiProdukcyjne;
import ogame.budynki.BudynkiTechnologiczne;
import ogame.planety.ListaPlanet;
import ogame.planety.Planeta;
import org.openqa.selenium.WebDriver;

public class Imperium extends LeafTask {

    public Imperium(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Imperium");
    }

    private CzasWykonania czasWykonania = new CzasWykonania();
    private final Czas WYKONAJ_PONOWNIE_ZA = new Czas(6,0,0);
    public static CzasWykonania czasPobraniaDanych = new CzasWykonania();
    public static CzasWykonania czasKolejnegoWykonania = new CzasWykonania();
    public static boolean updateGUI = false;
    @Override
    public void execute() {

        if (isRun())
        {
            if(isSleepTimeOut(System.currentTimeMillis()))
            {
                //Sprawdza czy załadowano listę planet.
                if(!Planety.init)
                {
                    Log.printLog(Imperium.class.getName(),"Rozpoczynam pobieranie danych o budynkach produkcyjnych.");
                    pobierzDaneOBudynkachProdukcyjnych();
                    Log.printLog(Imperium.class.getName(),"Zakończyłem pobieranie danych o budynkach produkcyjnych.");
                    Log.printLog(Imperium.class.getName(),"Rozpoczynam pobieranie danych o budynkach technologicznych.");
                    pobierzDaneOBudynkachTechnologicznych();
                    Log.printLog(Imperium.class.getName(),"Zakończyłem pobieranie danych o budynkach technologicznychh.");
                }
                setLastTimeExecute(System.currentTimeMillis());
            }
        }
        else
        {
            if(czasWykonania.ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 60 )
            {
                Log.printLog(AttackDetector.class.getName(), "OFF");
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }

            if(!czasWykonania.isActive())
            {
                if(CzasGry.getCzas().czasWSekundach() > 0)
                {
                    czasWykonania.setActive(true);
                    czasWykonania.setCzasString(CzasGry.getCzas().toString());
                    czasWykonania.setDataString(CzasGry.getData().toString());
                }
            }
        }
    }

    private void pobierzDaneOBudynkachProdukcyjnych()
    {
        for(Planeta p : Planety.planety)
        {
            Budynki budynki = p.getBudynki();
            //Klikam w zkładkę surowce.
            LeftMenu.pressSurowce(OgameWeb.webDriver,Imperium.class.toString());
            //Kliknięcie w planetę.
            while(ListaPlanet.wybranaPlaneta(OgameWeb.webDriver) != p.getPozycjaNaLiscie() )
            {
                p.clickPlanet();
                Waiter.sleep(100,150);
            }
            //Pobieranie danych o budynkach produkcyjnych na planecie.
            for(int i = 1; i <= 10;i++)
            {

                Budynek b = budynki.getBudynekDataTechnology(BudynkiProdukcyjne.dataTechnology(OgameWeb.webDriver,i,false),false);
                b.setLevel(BudynkiProdukcyjne.poziomBudynku(OgameWeb.webDriver,i,false));
                b.setStatus(Statusy.getIndex(BudynkiProdukcyjne.statusBudynku(OgameWeb.webDriver,i,false)));
            }
        }
        //Ustawienie czasów wykonania.
        czasPobraniaDanych.setCzasString(CzasGry.getCzas().toString());
        czasPobraniaDanych.setDataString(CzasGry.getData().toString());
        czasKolejnegoWykonania = CzasWykonania.okreslCzasWykonania(
                czasPobraniaDanych.getCzas(),
                czasPobraniaDanych.getData(),
                WYKONAJ_PONOWNIE_ZA
        );
        //Zaznaczenie flagi o updateGUI.
        updateGUI = true;
        //Określenie czasu uśpienia
        setSleep(WYKONAJ_PONOWNIE_ZA.czasWSekundach());
        czasPobraniaDanych.setActive(true);
        czasKolejnegoWykonania.setActive(true);
    }

    private void pobierzDaneOBudynkachTechnologicznych()
    {
        for(Planeta p : Planety.planety)
        {
            Budynki budynki = p.getBudynki();
            //Klikam w zkładkę stacja.
            LeftMenu.pressStacja(OgameWeb.webDriver,Imperium.class.toString());
            //Kliknięcie w planetę.
            while(ListaPlanet.wybranaPlaneta(OgameWeb.webDriver) != p.getPozycjaNaLiscie() )
            {
                p.clickPlanet();
                Waiter.sleep(100,150);
            }
            //Pobieranie danych o budynkach technologicznych na planecie.
            for(int i = 1; i <= 8;i++)
            {
                Budynek b = budynki.getBudynekDataTechnology(BudynkiTechnologiczne.dataTechnology(OgameWeb.webDriver,i,false),false);
                b.setLevel(BudynkiTechnologiczne.poziomBudynku(OgameWeb.webDriver,i,false));
                b.setStatus(Statusy.getIndex(BudynkiTechnologiczne.statusBudynku(OgameWeb.webDriver,i,false)));
            }
        }
        //Ustawienie czasów wykonania.
        czasPobraniaDanych.setCzasString(CzasGry.getCzas().toString());
        czasPobraniaDanych.setDataString(CzasGry.getData().toString());
        czasKolejnegoWykonania = CzasWykonania.okreslCzasWykonania(
                czasPobraniaDanych.getCzas(),
                czasPobraniaDanych.getData(),
                WYKONAJ_PONOWNIE_ZA
        );
        //Zaznaczenie flagi o updateGUI.
        updateGUI = true;
        //Określenie czasu uśpienia
        setSleep(WYKONAJ_PONOWNIE_ZA.czasWSekundach());
        czasPobraniaDanych.setActive(true);
        czasKolejnegoWykonania.setActive(true);
    }
}

