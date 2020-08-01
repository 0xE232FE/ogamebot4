package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.Wspolrzedne;
import app.czas.CzasGry;
import app.czas.CzasLotu;
import app.czas.CzasWykonania;
import app.data.ekspedycje.Ekspedycja;
import app.planety.Planety;
import app.ruchflot.Lot;
import app.ruchflot.Loty;
import com.DifferentMethods;
import com.Log;
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
import java.util.List;

public class Ekspedycje extends LeafTask {

    public Ekspedycje(WebDriver w, int index, long sleep) {
//        super(w, index, sleep, "Ekspedycje",false);
        super(w, index, sleep, "Ekspedycje");
    }

    // Creating date format
//    DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
    private DateFormat simple = new SimpleDateFormat("HH:mm:ss");
    private CzasWykonania czasWykonania = new CzasWykonania();
    private boolean uzupelnijDaneEkspedycji = false;
    @Override
    public void execute()
    {
        if (isRun())
        {
//            printTimeVariable();
            if(isSleepTimeOut(System.currentTimeMillis()))
            {
                if(app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji == -1 ||
                        app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji == -1)
                {
                    pobierzDaneOIlosciachEkspedycji();
                }

                Log.printLog(Ekspedycje.class.getName(),"Aktualna ilość ekspedycji = " + app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji +
                        " Ilosc ekspedycji na liscie = "+ app.data.ekspedycje.Ekspedycje.listaEkspedycji.size());
                if(app.data.ekspedycje.Ekspedycje.listaEkspedycji.size() != app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji)
                {
                    pobierzDaneOTrwajacychEkspedycjach();
                }

                if(!osiagnietoMaxIloscEkspedycji)
                {
                    wyslij();
                }

                if(uzupelnijDaneEkspedycji)
                {
                    uzupelnijDaneEkspedycji();
                }

                if(minalCzasPowrotuEkspedycji())
                {
                    List<Ekspedycja> tmp = app.data.ekspedycje.Ekspedycje.ukonczoneEkspedycje();
                    if(tmp.size() > 0)
                    {
                        app.data.ekspedycje.Ekspedycje.usunMisje(tmp);
                        osiagnietoMaxIloscEkspedycji = false;
                    }
                }

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

    private int maxIloscMisji = -1;
    private boolean osiagnietoMaxIloscEkspedycji = false;

    private void wyslij()
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

        //Pobieranei danych o ilości misji
        app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji = FlotaI.iloscEkspedycji(OgameWeb.webDriver);

        Log.printLog(Ekspedycje.class.getName(),"Aktualna ilość ekspedycji = " + app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji +
                " Maksymalna ilosc ekspedycji = "+ app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji);

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
            if(app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji < app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji)
            {
                Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam wysyłanie ekspedycji nr " + (app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji+1)+".");
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
                app.data.ekspedycje.Ekspedycje.dodajEkspedycje(new Ekspedycja(start,przylot,powrot));
                Log.printLog(Ekspedycje.class.getName(),"Zakończyłem wysyłanie ekspedycji nr " + (app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji+1)+".");
                //Flaga - wymagane uzupełnienie danych Ekspedycji
                uzupelnijDaneEkspedycji = true;
//                //Ustawienie sleep time
//                setSleep(30);
            }
            else
            {
//                int a = app.data.ekspedycje.Ekspedycje.iloscSekundDoPowrotuNajblizszejEkspedycji();
//                int a = 600;
//                setSleep(a);
                Log.printLog(Ekspedycje.class.getName(),"Nie spełniony warunek ilości wolnych ekspedycji. " +
                        "Ustawiono ponowne sprawdzenie za " + getSleep() + " sek");
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

    private boolean minalCzasPowrotuEkspedycji()
    {
        if(app.data.ekspedycje.Ekspedycje.najblizszaEkspedycja.getPowrot().getData().toString().equals(CzasGry.getData().toString()))
        {
            if(CzasGry.getCzas().czasWSekundach() > app.data.ekspedycje.Ekspedycje.najblizszaEkspedycja.getPowrot().getCzas().czasWSekundach());
                return true;
        }

        return false;

//        if(app.data.ekspedycje.Ekspedycje.sprawdzCzyWrocila())
//        {
//            //Klikanie w zakładkę
//            if(LeftMenu.pressFlota(OgameWeb.webDriver, Ekspedycje.class.getName()))
//            {
//                int aktualnaIloscEkspedycji = FlotaI.iloscEkspedycji(OgameWeb.webDriver);
//                if(aktualnaIloscEkspedycji < app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji)
//                {
//                    app.data.ekspedycje.Ekspedycje.usunMisje(app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji - aktualnaIloscEkspedycji);
//                    osiagnietoMaxIloscEkspedycji = false;
//                    setSleep(30);
//                }
//            }
//            else
//                setSleep(30);
//        }
//        else
//            Log.printLog(Ekspedycje.class.getName(),"Flota jeszcze nie wrócila. ");
    }

    private void uzupelnijDaneEkspedycji()
    {
        for(Ekspedycja e : app.data.ekspedycje.Ekspedycje.listaEkspedycji)
        {
            if(e.getId().equals(""))
            {
                List<Lot> tmp = Loty.getLoty("Ekspedycja");
                //Sprawdzenie listy lotow
                for(Lot l : tmp)
                {
                    int a = e.getPrzylot().getCzas().czasWSekundach();
                    int b = l.getCzasUCelu().getCzas().czasWSekundach();
                    //
                    if(a > b && (a - b <= 20))
                    {
                        e.setId(l.getId());
                        e.setPrzylot(l.getCzasUCelu());
                        e.setPowrot(l.getCzasPowrot());
                        break;
                    }
                    else
                    {
                        if(b - a <= 20)
                        {
                            e.setId(l.getId());
                            e.setPrzylot(l.getCzasUCelu());
                            e.setPowrot(l.getCzasPowrot());
                            break;
                        }
                    }
                }
            }
        }
        uzupelnijDaneEkspedycji = false;
    }

    private void pobierzDaneOIlosciachEkspedycji()
    {
        Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam pobieranie danych o ilości ekspedycji.");
        //Klikanie w zakładkę
        LeftMenu.pressFlota(OgameWeb.webDriver, Ekspedycje.class.getName());
        //Pobieranie danych o ilości ekspedycji
        if(app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji == -1)
            app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji = FlotaI.maxIloscEkspedycji(OgameWeb.webDriver);

        app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji = FlotaI.iloscEkspedycji(OgameWeb.webDriver);

        Log.printLog(Ekspedycje.class.getName(),"Aktualna ilość ekspedycji = " + app.data.ekspedycje.Ekspedycje.aktualnaIloscEkspedycji +
                " Maksymalna ilosc ekspedycji = "+ app.data.ekspedycje.Ekspedycje.maxIloscEkspedycji);
        Log.printLog(Ekspedycje.class.getName(),"Zakończyłem pobieranie danych o ilości ekspedycji.");
    }

    private void pobierzDaneOTrwajacychEkspedycjach()
    {
        Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam pobieranie danych o odbywających się ekspedycjach.");
        List<Lot> tmp = Loty.getLoty("Ekspedycja");
        for(Lot l : tmp)
        {
            Ekspedycja ekspedycja = new Ekspedycja(new CzasLotu(),l.getCzasUCelu(),l.getCzasPowrot());
            ekspedycja.setId(l.getId());
            app.data.ekspedycje.Ekspedycje.dodajEkspedycje(ekspedycja);
        }
        Log.printLog(Ekspedycje.class.getName(),"Zakończyłem pobieranie danych o odbywających się ekspedycjach.");
    }

    private int counterTmp = 0;
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
            String pozostalo = simple.format(new Date(-3600000+getSleep() - (tmp - getLastTimeExecute())));

            Log.printLog(Ekspedycje.class.getName(),"\n"+
                    DifferentMethods.initVariable("Uśpienie wątku:",35) + DifferentMethods.initVariable(sleepTime,10) + DifferentMethods.initVariable(String.valueOf(getSleep()),20)+"\n"
                            + DifferentMethods.initVariable("Aktualny czas:",35) + DifferentMethods.initVariable(currentTime,10) + DifferentMethods.initVariable(String.valueOf(tmp),20)+"\n"
                            + DifferentMethods.initVariable("Ostatni czas wykonania wątku:",35) + DifferentMethods.initVariable(lastTimeExecute,10) + DifferentMethods.initVariable(String.valueOf(getLastTimeExecute()),20)+"\n"
                            + DifferentMethods.initVariable("Czas od ostatniego wykonaia:",35) + DifferentMethods.initVariable(czasOdOstatniegoWykonania,10) + DifferentMethods.initVariable(String.valueOf(tmp - getLastTimeExecute()),20)+"\n"
                            + DifferentMethods.initVariable("Do wykonania wątku pozsotało:",35) + DifferentMethods.initVariable(pozostalo,10)+DifferentMethods.initVariable(String.valueOf(getSleep() - (tmp - getLastTimeExecute())),20));
        }
        else
            counterTmp++;

    }

    public void czyEkspedycjaZostalaOpozniona()
    {

    }

}
