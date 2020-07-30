package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.Wspolrzedne;
import app.czas.CzasGry;
import app.czas.CzasLotu;
import app.czas.CzasWykonania;
import app.data.ekspedycje.Ekspedycja;
import app.planety.Planety;
import com.DifferentMethods;
import com.Log;
import ogame.GameTime;
import ogame.LeftMenu;
import ogame.flota.Flota;
import ogame.flota.FlotaI;
import ogame.flota.FlotaII;
import ogame.flota.FlotaIII;
import ogame.planety.Planeta;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ekspedycje extends LeafTask {

    public Ekspedycje(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Ekspedycje");
    }

    // Creating date format
//    DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
    DateFormat simple = new SimpleDateFormat("HH:mm:ss");
    private CzasWykonania czasWykonania = new CzasWykonania();
    @Override
    public void execute()
    {
        if (isRun())
        {
            printTimeVariable();
            if(isSleepTimeOut(System.currentTimeMillis()))
            {
                if(!osiagnietoMaxIloscEkspedycji)
                    wyslij();
                else
                    zweryfikujPowrotuEkspedycji();

                setLastTimeExecute(System.currentTimeMillis());
            }
        }
        else
        {
            if(czasWykonania.ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 60 )
            {
                Log.printLog(Ekspedycje.class.getName(), "OFF");
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

    private int maxIloscEkspedycji = 0;
    private int maxIloscMisji = 0;
    private boolean osiagnietoMaxIloscEkspedycji = false;

    public void wyslij()
    {
        Wspolrzedne wspolrzedne = app.data.ekspedycje.Ekspedycje.getWspolrzedneStartu();
        Planeta planeta = Planety.getPlaneta(wspolrzedne.toString());
        //Wybieranie właściwego miejsca startu.
        if(app.data.ekspedycje.Ekspedycje.configuration.isPlanet())
            planeta.clickPlanet();
        else if(app.data.ekspedycje.Ekspedycje.configuration.isKsiezyc())
            planeta.clickMoon();

        //Klikanie w zakładkę
        LeftMenu.pressFlota(OgameWeb.webDriver, Ekspedycje.class.getName());
        //Pobieranie danych o ilości ekspedycji
        if(maxIloscEkspedycji == 0 || maxIloscEkspedycji == -1)
            maxIloscEkspedycji = FlotaI.maxIloscEkspedycji(OgameWeb.webDriver);

        int aktualnaIloscEkspedycji = FlotaI.iloscEkspedycji(OgameWeb.webDriver);

        Log.printLog(Ekspedycje.class.getName(),"Aktualna ilość ekspedycji = " + aktualnaIloscEkspedycji +
                " Maksymalna ilosc ekspedycji = "+ maxIloscEkspedycji);

        //Pobieranie danych o aktualnych misjach
        if(maxIloscMisji == 0 || maxIloscMisji == -1)
            maxIloscMisji = FlotaI.maxIloscMisji(OgameWeb.webDriver);

        int aktualnaIloscMisji = FlotaI.iloscMisji(OgameWeb.webDriver);

        Log.printLog(Ekspedycje.class.getName(),"Aktualna ilość misji = " + aktualnaIloscMisji +
                " Maksymalna ilosc misji = "+ maxIloscMisji);

        //Warunek ilości wolnych misji
        if(maxIloscMisji - aktualnaIloscMisji > 2)
        {
            //Warunek ilości ekspedycji
            if(aktualnaIloscEkspedycji < maxIloscEkspedycji)
            {
                Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam wysyłanie ekspedycji nr " + (aktualnaIloscEkspedycji+1)+".");
                //Ustawianie ilości statków do wysłania
                for(Flota.Statek statek : app.data.ekspedycje.Ekspedycje.configuration.getFlota().statki)
                {
                    if(statek.getIlosc() > 0)
                    {
                        if(statek.isMilitary())
                        {
                            //Sprawdza, czy na planecie jest wystarczająca ilość statków.
                            int a = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,statek.getPozycjaNaLiscie());
                            if( a > statek.getIlosc())
                                FlotaI.wprowadzIloscStatekBojowy(OgameWeb.webDriver,statek.getPozycjaNaLiscie(), statek.getIlosc());
                            else
                            {
                                if(a != 0)
                                {
                                    FlotaI.kliknijStatekBojowy(OgameWeb.webDriver,statek.getPozycjaNaLiscie());
                                    Log.printLog(Ekspedycje.class.getName(),"Brak " + statek.getIlosc() + " " + statek.getNazwa() +
                                            ". Wysłano  "+statek.getNazwa() + " w ilości " + a);
                                }
                                else
                                    Log.printLog(Ekspedycje.class.getName(),"Brak "+statek.getNazwa() + " na " + wspolrzedne);
                            }
                        }
                        else
                        {
                            //Sprawdza, czy na planecie jest wystarczająca ilość statków.
                            int a = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,statek.getPozycjaNaLiscie());
                            if(a > statek.getIlosc())
                                FlotaI.wprowadzIloscStatekCywilny(OgameWeb.webDriver,statek.getPozycjaNaLiscie(), statek.getIlosc());
                            else
                            {
                                if(a != 0)
                                {
                                    FlotaI.kliknijStatekCywilny(OgameWeb.webDriver,statek.getPozycjaNaLiscie());
                                    Log.printLog(Ekspedycje.class.getName(),"Brak " + statek.getIlosc() + " " + statek.getNazwa() +
                                            ". Wysłano  "+statek.getNazwa() + " w ilości " + a);
                                }
                                else
                                    Log.printLog(Ekspedycje.class.getName(),"Brak "+statek.getNazwa() + " na " + wspolrzedne);
                            }
                        }
                    }
                }
                //Kontynuuj
                FlotaI.clickContinue(OgameWeb.webDriver);
                //Współrzędne ekspedycji
                FlotaII.setGalaxy(OgameWeb.webDriver, app.data.ekspedycje.Ekspedycje.configuration.getGalaktyka());
                FlotaII.setUklad(OgameWeb.webDriver, app.data.ekspedycje.Ekspedycje.configuration.getUklad());
                FlotaII.setPlaneta(OgameWeb.webDriver,16);
                FlotaII.clickContinue(OgameWeb.webDriver);
                //Pobieranie danych o locie
                CzasLotu przylot = FlotaIII.przylot(OgameWeb.webDriver);
                CzasLotu powrot = FlotaIII.powrot(OgameWeb.webDriver);
                CzasLotu start = new CzasLotu();
                start.setDataString(CzasGry.getData().toString());
                start.setCzasString(CzasGry.getCzas().toString());
                // Kontynuuj
                FlotaIII.wyslijFlote(OgameWeb.webDriver);
                //Wypisz dane lotu
//                Log.printLog(Ekspedycje.class.getName(),"Przylot: "+ przylot.getData().toString()+ " " + przylot.getCzas().toString());
//                Log.printLog(Ekspedycje.class.getName(),"Powrót: "+ powrot.getData().toString()+ " " + powrot.getCzas().toString());
                //Tworzenie nowego obiektu ekspedycja
                app.data.ekspedycje.Ekspedycje.dodajEkspedycje(new Ekspedycja(start,przylot,powrot));
                Log.printLog(Ekspedycje.class.getName(),"Zakończyłem wysyłanie ekspedycji nr " + (aktualnaIloscEkspedycji+1)+".");
                //Ustawienie sleep time
                setSleep(30);
            }
            else
            {
//                int a = app.data.ekspedycje.Ekspedycje.iloscSekundDoPowrotuNajblizszejEkspedycji();
                int a = 600;
                setSleep(a);
                Log.printLog(Ekspedycje.class.getName(),"Nie spełniony warunek ilości wolnych ekspedycji. " +
                        "Ustawiono ponowne sprawdzenie za " + a + " sek");
                osiagnietoMaxIloscEkspedycji = true;
            }
        }
        else
        {
            int a = 300;
            setSleep(a);
            Log.printLog(Ekspedycje.class.getName(),"Nie spełniony warunek ilości wolnych misji. " +
                    "Ustawiono ponowne sprawdzenie za " + a + " sek");
        }
    }

    private void zweryfikujPowrotuEkspedycji()
    {
        if(app.data.ekspedycje.Ekspedycje.sprawdzCzyWrocila())
        {
            //Klikanie w zakładkę
            if(LeftMenu.pressFlota(OgameWeb.webDriver, Ekspedycje.class.getName()))
            {
                int aktualnaIloscEkspedycji = FlotaI.iloscEkspedycji(OgameWeb.webDriver);
                if(aktualnaIloscEkspedycji < maxIloscEkspedycji)
                {
                    app.data.ekspedycje.Ekspedycje.usunMisje(maxIloscEkspedycji - aktualnaIloscEkspedycji);
                    osiagnietoMaxIloscEkspedycji = false;
                    setSleep(30);
                }
            }
            else
                setSleep(30);
        }
        else
            Log.printLog(Ekspedycje.class.getName(),"Flota jeszcze nie wrócila. ");
    }

    int counterTmp = 0;
    private void printTimeVariable()
    {
        if(counterTmp == 100)
        {
            counterTmp = 0;
            long tmp = System.currentTimeMillis();
            String currentTime = simple.format(new Date(tmp));
            String lastTimeExecute = simple.format(new Date(-3600000+getLastTimeExecute()));
            String sleepTime = simple.format(new Date(-3600000+getSleep()));
            String czasOdOstatniegoWykonania = simple.format(new Date(-3600000+tmp - getLastTimeExecute()));
            String pozostało = simple.format(new Date(-3600000+getSleep() - (tmp - getLastTimeExecute())));

            Log.printLog(Ekspedycje.class.getName(),"\n"+
                    DifferentMethods.initVariable("Uśpienie wątku:",35) + DifferentMethods.initVariable(sleepTime,10) + DifferentMethods.initVariable(String.valueOf(getSleep()),20)+"\n"
                            + DifferentMethods.initVariable("Aktualny czas:",35) + DifferentMethods.initVariable(currentTime,10) + DifferentMethods.initVariable(String.valueOf(tmp),20)+"\n"
                            + DifferentMethods.initVariable("Ostatni czas wykonania wątku:",35) + DifferentMethods.initVariable(lastTimeExecute,10) + DifferentMethods.initVariable(String.valueOf(getLastTimeExecute()),20)+"\n"
                            + DifferentMethods.initVariable("Czas od ostatniego wykonaia:",35) + DifferentMethods.initVariable(czasOdOstatniegoWykonania,10) + DifferentMethods.initVariable(String.valueOf(tmp - getLastTimeExecute()),20)+"\n"
                            + DifferentMethods.initVariable("Do wykonania wątku pozsotało:",35) + DifferentMethods.initVariable(pozostało,10)+DifferentMethods.initVariable(String.valueOf(getSleep() - (tmp - getLastTimeExecute())),20));
        }
        else
            counterTmp++;


    }

}