package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.Wspolrzedne;
import app.atak.WrogieMisje;
import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.data.fleet_save_attack.FleetSave;
import app.log.LogFleetSaveAttack;
import app.planety.Planety;
import app.ruchflot.Loty;
import com.Log;
import com.Waiter;
import ogame.LeftMenu;
import ogame.flota.FlotaI;
import ogame.flota.FlotaII;
import ogame.flota.FlotaIII;
import ogame.planety.Planeta;
import ogame.ruchflot.ObiektLotu;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import ogame.ruchflotzdarzenia.WrogaMisja;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FleetSaveAttack extends LeafTask {

    static boolean attack = false;
    private CzasWykonania czasWykonania = new CzasWykonania();

    public FleetSaveAttack(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "FleetSave");
    }


    @Override
    public void execute()
    {
        if(isRun())
        {
            if (isSleepTimeOut(System.currentTimeMillis()))
            {
                if(attack)
                {
                    WrogaMisja delete = null;
                    for(WrogaMisja wrogaMisja : WrogieMisje.misje)
                    {
//                        Log.printLog(FleetSaveAttack.class.getName(),"START---------------"+wrogaMisja.getId()+"--------------------");
                        Planeta p = Planety.getPlaneta(wrogaMisja.getWspolrzedne());
                        switch (wrogaMisja.getCounter())
                        {
                            case 0:
                            {
//                                Log.printLog1("0",FleetSaveAttack.class,50);
                                // Sprawdza czy wykonać FS - pozsotało mniej niż 90 sek do ataku
                                if(wrogaMisja.wykonacFS(CzasGry.getCzas(),CzasGry.getData()))
                                {
                                    // Atak na księżyc
                                    if(wrogaMisja.isNaKsiezyc())
                                    {
                                        // Flota została wysłana na FS
                                        if(p.isFlotaZKsiezycaWyslanaNaFS())
                                        {
                                            wrogaMisja.setCounter(1);
                                            wrogaMisja.getDaneWyslanegoFS().setObiektLotu(p.getObiektFSZKsiezyca());
                                        }
                                        // Wysyła flotę na FS
                                        else
                                        {
                                            sentFleet(p,wrogaMisja.isNaKsiezyc(),wrogaMisja);
                                            wrogaMisja.setCounter(1);
                                        }
                                    }
                                    // Atak na planetę
                                    else
                                    {
                                        // Flota została wysłana na FS
                                        if(p.isFlotaZPlanetyWyslanaNaFS())
                                        {
                                            wrogaMisja.setCounter(1);
                                            wrogaMisja.getDaneWyslanegoFS().setObiektLotu(p.getObiektFSZPlanety());
                                        }
                                        // Wysyła flotę na FS
                                        else
                                        {
                                            sentFleet(p,wrogaMisja.isNaKsiezyc(),wrogaMisja);
                                            wrogaMisja.setCounter(1);
                                        }
                                    }
                                }
                                break;
                            }
                            case 1:
                            {
                                // Klikanie w podgląd jest aktywne
                                if(wrogaMisja.getKlikaniePodglad().isActive())
                                {
                                    // Jeżeli minął czas to kliknij podgląd
                                    if(wrogaMisja.getKlikaniePodglad().ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 15)
                                    {
                                        LeftMenu.pressPodglad(OgameWeb.webDriver,FleetSaveAttack.class.getName(),true);
                                        wrogaMisja.getKlikaniePodglad().setCzasString(CzasGry.getCzas().toString());
                                        wrogaMisja.getKlikaniePodglad().setDataString(CzasGry.getData().toString());
                                    }

                                    Waiter.sleep(50,50);
                                    RuchFlotZdarzenia.rozwin(getW());
                                    Waiter.sleep(50,50);
                                    List<WrogaMisja> tmp = RuchFlotZdarzenia.getWrogieMisje(OgameWeb.webDriver);
                                    // Sprawdza czy flota nie została opóźniona
                                    if(wrogaMisja.zostalaOpozniona(tmp))
                                        wrogaMisja.setCounter(2);
                                }
                                // Klikanie w podgląd nie jest aktywne
                                else
                                {
                                    // Sprawdza czy atak minął
                                    if(wrogaMisja.atakMinal(CzasGry.getCzas()))
                                    {
                                        LeftMenu.pressPodglad(OgameWeb.webDriver,FleetSaveAttack.class.getName());
                                        Waiter.sleep(50,50);
                                        RuchFlotZdarzenia.rozwin(getW());
                                        Waiter.sleep(50,50);
                                        List<WrogaMisja> tmp = RuchFlotZdarzenia.getWrogieMisje(OgameWeb.webDriver);
                                        // Sprawdza czy flota nie została opóźniona
                                        if(wrogaMisja.zostalaOpozniona(tmp))
                                            wrogaMisja.setCounter(2);
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                // Czy są dodatkowe ataki na te wspolrzedne
                                List<WrogaMisja> dodatkoweAtakiTmp  = WrogieMisje.getMisje(wrogaMisja.getWspolrzedne(),wrogaMisja.isNaKsiezyc());
                                if(wrogaMisja.dodatkoweAtakiNaTeWspolrzedne(dodatkoweAtakiTmp))
                                {
                                    WrogaMisja tmpKolejnyAtak = WrogieMisje.najblizszaMisja(wrogaMisja.getId(),dodatkoweAtakiTmp, wrogaMisja.isNaKsiezyc());
                                    // Czy kolejna misja wchodzi tego samego dnia
                                    if(tmpKolejnyAtak.getData().equals(CzasGry.getData()))
                                    {
                                        // Czas najbliższego wejscia ataku - aktualny czas
                                        int a = tmpKolejnyAtak.getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach();
                                        // Czas jaki minął od wysłania FS'a
                                        int zaIleWrociFlota;
                                        // FS wysłany dzisiejszego dnia
                                        if(wrogaMisja.getDaneWyslanegoFS().getData().equals(CzasGry.getData()))
                                            zaIleWrociFlota = CzasGry.getCzas().czasWSekundach() - wrogaMisja.getDaneWyslanegoFS().getCzas().czasWSekundach();
                                        // FS wysłany wczoraj.
                                        else
                                            zaIleWrociFlota = CzasGry.getCzas().czasWSekundach() + Czas.MAX_SECONDS_DAY - wrogaMisja.getDaneWyslanegoFS().getCzas().czasWSekundach();

                                        Log.printLog1("Aktualny czas gry " + CzasGry.getCzas() +". Czas wysłanego FS'a " + wrogaMisja.getDaneWyslanegoFS().getCzas().toString()+
                                                ". Flota wróci za " + zaIleWrociFlota +"sek.",FleetSaveAttack.class,156);
//
                                        // Jeśli zawróci flotę, to po powrocie będzie 90 sekund na ponowny lot.
                                        // wykona się gdy będzie conajmniej 90 sek. zapasu od powrotu floty do wejścia
                                        // następnego ataku
                                        Log.printLog1("Jeśli zawrócisz, flota wróci za  " + (zaIleWrociFlota)
                                                + "sek. Do następnego ataku pozsotało "+a+"sek.",FleetSaveAttack.class,162);
                                        if(zaIleWrociFlota > a)
                                        {
                                            // Zawraca wysłaną flotę na FS
                                            if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                                if(zawroc(wrogaMisja,p))
                                                    wrogaMisja.setCounter(3);
                                            // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                            else
                                                wrogaMisja.setCounter(3);
                                        }
                                        else
                                        {
                                            // Sprawdza czy od powrotu floty na planetę po zawroceniu do wejścia  kolejnego ataku
                                            // będzie conajmniej 120 sek różnicy.
                                            Log.printLog1("Jeśli zawrócisz, to po powrocie na planetę pozsotanie " +
                                                    + (a - zaIleWrociFlota) + "sek. do następnego ataku.",FleetSaveAttack.class,178);
                                            if(a - zaIleWrociFlota > 120)
                                            {
                                                // Jeżeli warunek został spełniony, czy następny atak wejdzie za conajmniej
                                                // 120 sek. to zawróci flotę.
                                                if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                                    if(zawroc(wrogaMisja,p))
                                                        wrogaMisja.setCounter(3);
                                                // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                                else
                                                    wrogaMisja.setCounter(3);
                                            }
                                            else
                                            {
                                                // Gdyby zawrocił pozostało by za mało czasu na ponowne wysłanie.
                                                // Sprawdzanie zaczyna się od początku.
                                                wrogaMisja.setCounter(1);
                                                Log.printLog1("Ustawiam counter na wartość 1.",FleetSaveAttack.class,195);
                                            }
                                        }
                                    }
                                    // Atak wchodzi jutro
                                    else
                                    {
                                        // Czas najbliższego wejscia ataku - aktualny czas
                                        int zaIleSekundWejdzieKolejnyAtak = tmpKolejnyAtak.getCzas().czasWSekundach() + Czas.MAX_SECONDS_DAY - CzasGry.getCzas().czasWSekundach();

                                        // Czas jaki minął od wysłania FS'a
                                        int zaIleWrociFlota = CzasGry.getCzas().czasWSekundach() - wrogaMisja.getDaneWyslanegoFS().getCzas().czasWSekundach();
                                        Log.printLog1("Aktualny czas gry " + CzasGry.getData().toString() +" "+CzasGry.getCzas().toString()
                                                +". Czas wysłanego FS'a " + wrogaMisja.getDaneWyslanegoFS().getData().toString() + " " +
                                                wrogaMisja.getDaneWyslanegoFS().getCzas().toString()+ ". Flota wróci za " + zaIleWrociFlota +"sek.",FleetSaveAttack.class,209);

                                        Log.printLog1("Jeśli zawrócisz, flota wróci za  " + (zaIleWrociFlota)
                                                + "sek. Do następnego ataku pozsotało "+zaIleSekundWejdzieKolejnyAtak+"sek.",FleetSaveAttack.class,212);

                                        if(zaIleWrociFlota > zaIleSekundWejdzieKolejnyAtak)
                                        {
                                            if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                                if(zawroc(wrogaMisja,p))
                                                    wrogaMisja.setCounter(3);
                                            // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                            else
                                                wrogaMisja.setCounter(3);
                                        }
                                        else
                                        {
                                            // Sprawdza czy od powrotu floty na planetę po zawroceniu do wejścia ataku
                                            // będzie conajmniej 120 sek różnicy.
                                            Log.printLog1("Jeśli zawrócisz, to po powrocie na planetę pozsotanie " +
                                                    + (zaIleSekundWejdzieKolejnyAtak - zaIleWrociFlota) + "sek. do następnego ataku.",FleetSaveAttack.class,228);
                                            if(zaIleSekundWejdzieKolejnyAtak - zaIleWrociFlota > 120)
                                            {
                                                if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                                    if(zawroc(wrogaMisja,p))
                                                        wrogaMisja.setCounter(3);
                                                // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                                else
                                                    wrogaMisja.setCounter(3);
                                            }
                                            else
                                                wrogaMisja.setCounter(1);
                                        }
                                    }
                                }
                                else
                                {
                                    if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS():p.isFlotaZPlanetyWyslanaNaFS())
                                        if(zawroc(wrogaMisja,p))
                                            wrogaMisja.setCounter(3);
                                    else
                                    {
                                        if(wrogaMisja.atakMinal(CzasGry.getCzas(),5))
                                        {
                                            Log.printLog(FleetSaveAttack.class.getName(),"Brak wysłanego FS'a z " + wrogaMisja.getWspolrzedne()  +
                                                    ". Przyjąłem, że flota została wcześniej wysłana i wraca z FS'a.");
                                            wrogaMisja.setCounter(3);
                                        }
                                        else
                                        {
                                            if(sprawdCzyJestFlotaNaPlanecie(p))
                                            {
                                                wrogaMisja.getKlikaniePodglad().setActive(true);
                                                wrogaMisja.setCounter(0);
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case 3:
                                delete = wrogaMisja;
                                break;
                        }
//                        Log.printLog(FleetSaveAttack.class.getName(),"STOP----------------"+wrogaMisja.getId()+"--------------------");
                    }
                    if(delete != null)
                    {
                        WrogieMisje.remove(delete);
//                        delete = null;
                    }
                }
            }
        }
        else
        {
            // Wypisywanie info o wyłączonym module.
            if(czasWykonania.ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 60)
            {
                Log.printLog(FleetSaveAttack.class.getName(), "OFF");
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

        saveLog();
    }

    /**
     * Wysyłanie całe floty z Planety wraz z surowcami na FS'a.
     * @param p Dane planety z której będzie wysyłany FS.
     * @param moon Czy FS ma być wysyłany z księżyca.
     * @param wrogaMisja Dane wrogiej  misji na wskazane współrzędne.
     */
    private void sentFleet(Planeta p,boolean moon,WrogaMisja wrogaMisja)
    {
        if(moon)
            p.clickMoon();
        else
            p.clickPlanet();

        LeftMenu.pressFlota(OgameWeb.webDriver,FleetSaveAttack.class.getName());
        Log.printLog(FleetSaveAttack.class.getName(),"Rozpoczynam wysyłanie FS'a z "+ (moon ? "księżyca " : "planety ")
                + p.getWspolrzedne());
        Waiter.sleep(50,50);
        Wspolrzedne wspolrzedne;
        FleetSave fleetSave = null;

        if(FlotaI.isAnyShips(OgameWeb.webDriver))
        {
            FlotaI.chooseAllShips(OgameWeb.webDriver);
            FlotaI.clickContinue(OgameWeb.webDriver);
            Waiter.sleep(50,50);
            // Atak na księżyc
            if(moon)
            {
                // Ustawiono auto fleetsave z księżyca - wyślij na planetę
                if(p.getAttackFleetSaveConfiguration().isAutoFleetSaveMoon())
                {
                    FlotaII.setGalaxy(OgameWeb.webDriver,p.wspolrzedne().getGalaktyka());
                    FlotaII.setUklad(OgameWeb.webDriver,p.wspolrzedne().getUklad());
                    FlotaII.setPlaneta(OgameWeb.webDriver,p.wspolrzedne().getPlaneta());
                    FlotaII.setSpeed(OgameWeb.webDriver,1);
                    FlotaII.clickPlaneta(OgameWeb.webDriver);
                    FlotaII.clickContinue(OgameWeb.webDriver);
                    Waiter.sleep(100,100);
                    wrogaMisja.getDaneWyslanegoFS().setObiektLotu(new ObiektLotu(p.getWspolrzedne(),true));
                    p.setObiektFSZKsiezyca(wrogaMisja.getDaneWyslanegoFS().getObiektLotu());
                }
                // Wyślij na podane wpsółrzędne
                else
                {
                    fleetSave  = p.getAttackFleetSaveConfiguration().getRandomMoonMissionConfiguration();
                    // Lista obiektów na FS'a jest pusta
                    if(fleetSave == null)
                        fleetSave = new FleetSave("Stacjonuj",p.getWspolrzedne(),0);
                    wspolrzedne = new Wspolrzedne(fleetSave.getWspolrzedne());
                    FlotaII.setGalaxy(OgameWeb.webDriver,wspolrzedne.getGalaktyka());
                    FlotaII.setUklad(OgameWeb.webDriver,wspolrzedne.getUklad());
                    FlotaII.setPlaneta(OgameWeb.webDriver,wspolrzedne.getPlaneta());
                    FlotaII.setSpeed(OgameWeb.webDriver,1);

                    if(fleetSave.getObiekt() == 0)
                        FlotaII.clickPlaneta(OgameWeb.webDriver);
                    else
                        FlotaII.clickKsiezyc(OgameWeb.webDriver);

                    FlotaII.clickContinue(OgameWeb.webDriver);
                    Waiter.sleep(100,100);
                    wrogaMisja.getDaneWyslanegoFS().setObiektLotu(new ObiektLotu(p.getWspolrzedne(),true));
                    p.setObiektFSZKsiezyca(wrogaMisja.getDaneWyslanegoFS().getObiektLotu());
                }

            }
            // Atak na planetę
            else
            {
                // Planeta posiada księżyc
                if(p.isMoon())
                {
                    if(p.getAttackFleetSaveConfiguration().isAutoFleetSavePlaneta())
                    {
                        FlotaII.setGalaxy(OgameWeb.webDriver,p.wspolrzedne().getGalaktyka());
                        FlotaII.setUklad(OgameWeb.webDriver,p.wspolrzedne().getUklad());
                        FlotaII.setPlaneta(OgameWeb.webDriver,p.wspolrzedne().getPlaneta());
                        FlotaII.setSpeed(OgameWeb.webDriver,1);
                        FlotaII.clickKsiezyc(OgameWeb.webDriver);
                        FlotaII.clickContinue(OgameWeb.webDriver);
                        Waiter.sleep(100,100);
                        wrogaMisja.getDaneWyslanegoFS().setObiektLotu(new ObiektLotu(p.getWspolrzedne(),false));
                        p.setObiektFSZPlanety(wrogaMisja.getDaneWyslanegoFS().getObiektLotu());
                    }
                    else
                    {
                        fleetSave  = p.getAttackFleetSaveConfiguration().getRandomPlanetaMissionConfiguration();
                        if(fleetSave == null)
                            fleetSave = new FleetSave("Stacjonuj",p.getWspolrzedne(),1);
                        wspolrzedne = new Wspolrzedne(fleetSave.getWspolrzedne());
                        FlotaII.setGalaxy(OgameWeb.webDriver,wspolrzedne.getGalaktyka());
                        FlotaII.setUklad(OgameWeb.webDriver,wspolrzedne.getUklad());
                        FlotaII.setPlaneta(OgameWeb.webDriver,wspolrzedne.getPlaneta());
                        FlotaII.setSpeed(OgameWeb.webDriver,1);

                        if(fleetSave.getObiekt() == 0)
                            FlotaII.clickPlaneta(OgameWeb.webDriver);
                        else
                            FlotaII.clickKsiezyc(OgameWeb.webDriver);

                        FlotaII.clickContinue(OgameWeb.webDriver);
                        Waiter.sleep(100,100);
                        wrogaMisja.getDaneWyslanegoFS().setObiektLotu(new ObiektLotu(wspolrzedne.toString(),true));
                        p.setObiektFSZPlanety(wrogaMisja.getDaneWyslanegoFS().getObiektLotu());
                    }

                }
                else
                {
                    fleetSave  = p.getAttackFleetSaveConfiguration().getRandomPlanetaMissionConfiguration();
//                    if(fleetSave == null)
//                        fleetSave = new FleetSave("Stacjonuj",p.getWspolrzedne(),0);
                    wspolrzedne = new Wspolrzedne(fleetSave.getWspolrzedne());
                    FlotaII.setGalaxy(OgameWeb.webDriver,wspolrzedne.getGalaktyka());
                    FlotaII.setUklad(OgameWeb.webDriver,wspolrzedne.getUklad());
                    FlotaII.setPlaneta(OgameWeb.webDriver,wspolrzedne.getPlaneta());
                    FlotaII.setSpeed(OgameWeb.webDriver,1);

                    if(fleetSave.getObiekt() == 0)
                        FlotaII.clickPlaneta(OgameWeb.webDriver);
                    else
                        FlotaII.clickKsiezyc(OgameWeb.webDriver);

                    FlotaII.clickContinue(OgameWeb.webDriver);
                    Waiter.sleep(100,100);
                    wrogaMisja.getDaneWyslanegoFS().setObiektLotu(new ObiektLotu(wspolrzedne.toString(),true));
                    p.setObiektFSZPlanety(wrogaMisja.getDaneWyslanegoFS().getObiektLotu());
                }
            }

            boolean missionSelected = false;
            while(!missionSelected)
            {
                if(p.getAttackFleetSaveConfiguration().isAutoFleetSavePlaneta() ||
                        p.getAttackFleetSaveConfiguration().isAutoFleetSaveMoon() )
                    FlotaIII.kliknijMisje(OgameWeb.webDriver,5);
                else
                    FlotaIII.kliknijMisje(OgameWeb.webDriver,fleetSave.getMisjaInt());

                Waiter.sleep(25,25);
                missionSelected = FlotaIII.isMissionSelected(OgameWeb.webDriver, FleetSaveAttack.class.getName());
            }
            FlotaIII.clickWszystkieSurowce(OgameWeb.webDriver);
            FlotaIII.wyslijFlote(OgameWeb.webDriver);

            wrogaMisja.getDaneWyslanegoFS().setCzas(CzasGry.getCzas().toString());
            wrogaMisja.getDaneWyslanegoFS().setData(CzasGry.getData().toString());

            Log.printLog(FleetSaveAttack.class.getName(),"Dane wysłanego FS'a z "+ (moon ? "księżyca " : "planety ")
                    + p.getWspolrzedne() +"\n "+ wrogaMisja.getDaneWyslanegoFS().toString());
            LogFleetSaveAttack.addLog(new app.log.Log(FleetSaveAttack.class.getName(),
                    "Wysłano FS'a z " + (moon ? "KSIĘŻYC " : "PLANETA ")
                            + p.getWspolrzedne() + " Dane FS'a: Cel - "+wrogaMisja.getDaneWyslanegoFS().getObiektLotu().getWspolrzedne() +
                            (wrogaMisja.getDaneWyslanegoFS().getObiektLotu().isKsiezyc() ? "KSIĘŻYC " : "PLANETA ") +
                            " Data - " + wrogaMisja.getDaneWyslanegoFS().getData().toString() + " Czas - " +
                            wrogaMisja.getDaneWyslanegoFS().getCzas().toString()));

            if(moon)
                p.setFlotaZKsiezycaWyslanaNaFS(true);
            else
                p.setFlotaZPlanetyWyslanaNaFS(true);
        }
        else
        {
            LogFleetSaveAttack.addLog(new app.log.Log(FleetSaveAttack.class.getName(),
                    "Nie wysłano FS'a z " + (moon ? "księżyc " : "planeta ")
                            + p.getWspolrzedne() + " z powodu braku statków na planecie."));
            Log.printLog(FleetSaveAttack.class.getName(),"Brak statków na "+ (moon ? "księżyc " : "planeta ")
                    + p.getWspolrzedne());
        }


        Log.printLog(FleetSaveAttack.class.getName(),"Zakończyłem wysyłanie FS'a z "+ (moon ? "księżyca " : "planety ")
                + p.getWspolrzedne());
    }

    /**
     * Zawraca flotę wysłaną na FS'a.
     * @param wrogMisja Atak na określone współrzedne planeta lub księżyc.
     * @param p Planeta/księżyc na która jest atak.
     * @return Zwróci <b>true</b> jeżeli zawróci.
     */
    private boolean zawroc(WrogaMisja wrogMisja, Planeta p)
    {
        Log.printLog(FleetSaveAttack.class.getName(),"Rozpoczynam zawracanie FS'a z "+ wrogMisja.getWspolrzedne() +
                (wrogMisja.isNaPlanete() ? " PLANETA" : " KSIĘZYC"));
        LeftMenu.pressRuchFlot(OgameWeb.webDriver,FleetSaveAttack.class.getName());

        boolean b = Loty.getLot(wrogMisja.getWspolrzedne(),wrogMisja.getDaneWyslanegoFS().getObiektLotu().getWspolrzedne(),
                wrogMisja.isNaKsiezyc() ? 1:0, wrogMisja.getDaneWyslanegoFS().getObiektLotu().isKsiezyc() ? 1:0).zawroc();

        Log.printLog(FleetSaveAttack.class.getName(),"Zawrócono FS'a z "+ wrogMisja.getWspolrzedne() +
                (wrogMisja.isNaPlanete() ? " PLANETA" : " KSIĘZYC"));

        LogFleetSaveAttack.addLog(new app.log.Log(FleetSaveAttack.class.getName(),
                "Zawrócono FS'a z "+ wrogMisja.getWspolrzedne() +
                        (wrogMisja.isNaPlanete() ? " PLANETA" : " KSIĘZYC")));

        if(wrogMisja.isNaKsiezyc())
            p.setFlotaZKsiezycaWyslanaNaFS(false);
        else
            p.setFlotaZPlanetyWyslanaNaFS(false);
        return b;
    }

    /**
     * Sprawdza czy na wskazanej planecie w argumencie jest flota.
     * @param p Planeta.
     * @return Jeżeli jest na planecie to zwróci <b>true</b>.
     */
    private boolean sprawdCzyJestFlotaNaPlanecie(Planeta p)
    {
        LeftMenu.pressFlota(OgameWeb.webDriver,FleetSaveAttack.class.getName());
        boolean b = FlotaI.isAnyShips(OgameWeb.webDriver);
        Log.printLog(FleetSaveAttack.class.getName(),"Sprawdzam czy jest flota na "+ p.getWspolrzedne() + " " +
                (b ? " Flota jest na planecie." : " Brak floty na planecie."));
        return  b;
    }

    private void saveLog()
    {
        if(!LogFleetSaveAttack.getCzasWykonania().isActive())
        {
            if(CzasGry.getCzas().czasWSekundach() > 0)
            {
                LogFleetSaveAttack.getCzasWykonania().setActive(true);
                LogFleetSaveAttack.getCzasWykonania().setCzasString(CzasGry.getCzas().toString());
                LogFleetSaveAttack.getCzasWykonania().setDataString(CzasGry.getData().toString());
                LogFleetSaveAttack.setFileNamePart1(CzasGry.getCzas(),CzasGry.getData());
                LogFleetSaveAttack.setFileNamePart2(CzasGry.getCzas(),CzasGry.getData());
            }
        }
        else
        {
            if(LogFleetSaveAttack.getCzasWykonania().ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 600)
            {
                LogFleetSaveAttack.setFileNamePart2(CzasGry.getCzas(),CzasGry.getData());
                LogFleetSaveAttack.getCzasWykonania().setCzasString(CzasGry.getCzas().toString());
                LogFleetSaveAttack.getCzasWykonania().setDataString(CzasGry.getData().toString());
                LogFleetSaveAttack.save();
            }
        }
    }
}
