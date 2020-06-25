package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.Wspolrzedne;
import app.atak.WrogieMisje;
import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.planety.Planety;
import app.planety.StaticCoord;
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

        if (isRun())
        {
            if (isSleepTimeOut(System.currentTimeMillis()))
            {
                if(attack)
                {
                    WrogaMisja delete = null;
                    for(WrogaMisja wrogaMisja : WrogieMisje.misje)
                    {
                        Log.printLog(FleetSaveAttack.class.getName(),"START---------------"+wrogaMisja.getId()+"--------------------");
                        Planeta p = Planety.getPlaneta(wrogaMisja.getWspolrzedne());
                        switch (wrogaMisja.getCounter())
                        {
                            case 0:
                            {
//                                Log.printLog1("0",FleetSaveAttack.class,50);
                                if(wrogaMisja.wykonacFS(CzasGry.getCzas(),CzasGry.getData()))
                                {

                                    if(wrogaMisja.isNaKsiezyc())
                                    {
                                        if(p.isFlotaZKsiezycaWyslanaNaFS())
                                        {
                                            wrogaMisja.setCounter(1);
                                            wrogaMisja.getDaneWyslanegoFS().setObiektLotu(p.getObiektFSZKsiezyca());
                                        }
                                        else
                                        {
                                            sentFleet(p,wrogaMisja.isNaKsiezyc(),wrogaMisja);
                                            wrogaMisja.setCounter(1);
                                        }
                                    }
                                    else
                                    {
                                        if(p.isFlotaZPlanetyWyslanaNaFS())
                                        {
                                            wrogaMisja.setCounter(1);
                                            wrogaMisja.getDaneWyslanegoFS().setObiektLotu(p.getObiektFSZPlanety());
                                        }
                                        else
                                        {
                                            sentFleet(p,wrogaMisja.isNaKsiezyc(),wrogaMisja);
                                            wrogaMisja.setCounter(1);
                                        }
                                    }
//                                    Log.printLog1("0.1",FleetSaveAttack.class,55);
                                }
                                break;
                            }
                            case 1:
                            {
//                                Log.printLog1("1",FleetSaveAttack.class,61);
                                if(wrogaMisja.getKlikaniePodglad().isActive())
                                {
                                    Log.printLog1("1.1",FleetSaveAttack.class,64);

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
                                    if(wrogaMisja.zostalaOpozniona(tmp))
                                    {
                                        wrogaMisja.setCounter(2);
                                        Log.printLog1("1.1.1",FleetSaveAttack.class,68);
                                    }
                                }
                                else
                                {
                                    if(wrogaMisja.atakMinal(CzasGry.getCzas()))
                                    {
                                        Log.printLog1("1.1",FleetSaveAttack.class,64);
                                        LeftMenu.pressPodglad(OgameWeb.webDriver,FleetSaveAttack.class.getName());
                                        Waiter.sleep(50,50);
                                        RuchFlotZdarzenia.rozwin(getW());
                                        Waiter.sleep(50,50);
                                        List<WrogaMisja> tmp = RuchFlotZdarzenia.getWrogieMisje(OgameWeb.webDriver);
                                        if(wrogaMisja.zostalaOpozniona(tmp))
                                        {
                                            wrogaMisja.setCounter(2);
                                            Log.printLog1("1.1.1",FleetSaveAttack.class,68);
                                        }
                                    }
                                }

                                break;
                            }
                            case 2:
                            {
                                Log.printLog1("2",FleetSaveAttack.class,137);
                                // Czy są dodatkowe ataki na te wspolrzedne
                                List<WrogaMisja> dodatkoweAtakiTmp  = WrogieMisje.getMisje(wrogaMisja.getWspolrzedne(),wrogaMisja.isNaKsiezyc());
                                if(wrogaMisja.dodatkoweAtakiNaTeWspolrzedne(dodatkoweAtakiTmp))
                                {
                                    Log.printLog1("2.1",FleetSaveAttack.class,142);
                                    WrogaMisja tmpKolejnyAtak = WrogieMisje.najblizszaMisja(wrogaMisja.getId(),dodatkoweAtakiTmp, wrogaMisja.isNaKsiezyc());
                                    // Czy kolejna misja wchodzi tego samego dnia
//                                    if(tmpKolejnyAtak.getData().equals(wrogaMisja.getData()))
                                    if(tmpKolejnyAtak.getData().equals(CzasGry.getData()))
                                    {
                                        Log.printLog1("2.1.1",FleetSaveAttack.class,148);
                                        // Za jaki czas wejdzie atak po wysłaniu Fs'a
//                                        String tmp1 = tmpKolejnyAtak.getCzas().toString();
//                                        String tmp2 = wrogaMisja.getDaneWyslanegoFS().getCzas().toString();
//                                        String tmp3 = CzasGry.getCzas().toString();

                                        // Czas najbliższego wejscia ataku - aktualny czas
                                        int a = tmpKolejnyAtak.getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach();
//                                        Log.printLog1("2.1.2 - a=" + a +"; tmp1="+tmp1 +"; tmp3="+tmp3, FleetSaveAttack.class,122);
                                        // Czas jaki minął od wysłania FS'a
                                        int zaIleWrociFlota;
                                        // FS wysłany dzisiejszego dnia
                                        if(wrogaMisja.getDaneWyslanegoFS().getData().equals(CzasGry.getData()))
                                            zaIleWrociFlota = CzasGry.getCzas().czasWSekundach() - wrogaMisja.getDaneWyslanegoFS().getCzas().czasWSekundach();
                                        // FS wysłany wczoraj.
                                        else
                                            zaIleWrociFlota = CzasGry.getCzas().czasWSekundach() + Czas.MAX_SECONDS_DAY - wrogaMisja.getDaneWyslanegoFS().getCzas().czasWSekundach();

                                        Log.printLog1("Aktualny czas gry " + CzasGry.getCzas() +". Czas wysłanego FS'a " + wrogaMisja.getDaneWyslanegoFS().getCzas().toString()+
                                                ". Flota wróci za " + zaIleWrociFlota +"sek.",FleetSaveAttack.class,167);
//                                        Log.printLog1("2.1.3 - zaIleWrociFlota=" + zaIleWrociFlota +"; tmp3="+tmp3 +"; tmp2="+tmp2,FleetSaveAttack.class,118);

                                        // Czas najbliższego wejscia ataku - czas wysłania flory na FS
//                                        int c = tmpKolejnyAtak.getCzas().czasWSekundach() - wrogaMisja.getDaneWyslanegoFS().getCzas().czasWSekundach();
//                                        Log.printLog1("2.1.4 - c=" + c +"; tmp1="+tmp1 +"; tmp2="+tmp2,FleetSaveAttack.class,131);

                                        // Jeśli zawróci flotę, to po powrocie będzie 90 sekund na ponowny lot.
                                        // wykona się gdy będzie conajmniej 90 sek. zapasu od powrotu floty do wejścia
                                        // następnego ataku
                                        Log.printLog1("2.1.6 - Jeśli zawrócisz, flota wróci za  " + (zaIleWrociFlota)
                                                + "sek. Do następnego ataku pozsotało "+a+"sek.",FleetSaveAttack.class,178);
                                        if(zaIleWrociFlota > a)
                                        {
                                            Log.printLog1("2.1.1.1",FleetSaveAttack.class,181);
                                            if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                            {
                                                if(zawroc(wrogaMisja,p))
                                                {
                                                    wrogaMisja.setCounter(3);
                                                    Log.printLog1("2.1.1.1.1",FleetSaveAttack.class,187);
                                                }
                                            }
                                            // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                            else
                                            {

                                                wrogaMisja.setCounter(3);
                                                Log.printLog1("2.1.1.1.2",FleetSaveAttack.class,195);
                                            }
                                        }
                                        else
                                        {
                                            // Sprawdza czy od powrotu floty na planetę po zawroceniu do wejścia ataku
                                            // będzie conajmniej 120 sek różnicy.
                                            Log.printLog1("2.1.1.2 - Jeśli zawrócisz, to po powrocie na planetę pozsotanie " +
                                                    + (a - zaIleWrociFlota) + "sek. do następnego ataku.",FleetSaveAttack.class,162);
                                            if(a - zaIleWrociFlota > 120)
                                            {
                                                Log.printLog1("2.1.1.1",FleetSaveAttack.class,163);
                                                if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                                {
                                                    if(zawroc(wrogaMisja,p))
                                                    {
                                                        wrogaMisja.setCounter(3);
                                                        Log.printLog1("2.1.1.1.1",FleetSaveAttack.class,171);
                                                    }
                                                }
                                                // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                                else
                                                {
                                                    wrogaMisja.setCounter(3);
                                                    Log.printLog1("2.1.1.1.2",FleetSaveAttack.class,179);
                                                }
                                            }
                                            else
                                            {
                                                wrogaMisja.setCounter(1);
                                                Log.printLog1("Ustawiam counter na wartość 1.",FleetSaveAttack.class,185);
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
                                                wrogaMisja.getDaneWyslanegoFS().getCzas().toString()+ ". Flota wróci za " + zaIleWrociFlota +"sek.",FleetSaveAttack.class,231);

                                        Log.printLog1("Jeśli zawrócisz, flota wróci za  " + (zaIleWrociFlota)
                                                + "sek. Do następnego ataku pozsotało "+zaIleSekundWejdzieKolejnyAtak+"sek.",FleetSaveAttack.class,235);

                                        if(zaIleWrociFlota > zaIleSekundWejdzieKolejnyAtak)
                                        {
//                                            Log.printLog1("2.1.1.1",FleetSaveAttack.class,140);
                                            if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                            {
                                                if(zawroc(wrogaMisja,p))
                                                {
                                                    wrogaMisja.setCounter(3);
//                                                    Log.printLog1("2.1.1.1.1",FleetSaveAttack.class,146);
                                                }
                                            }
                                            // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                            else
                                            {

                                                wrogaMisja.setCounter(3);
//                                                Log.printLog1("2.1.1.1.2",FleetSaveAttack.class,154);
                                            }
                                        }
                                        else
                                        {
                                            // Sprawdza czy od powrotu floty na planetę po zawroceniu do wejścia ataku
                                            // będzie conajmniej 120 sek różnicy.
                                            Log.printLog1("Jeśli zawrócisz, to po powrocie na planetę pozsotanie " +
                                                    + (zaIleSekundWejdzieKolejnyAtak - zaIleWrociFlota) + "sek. do następnego ataku.",FleetSaveAttack.class,261);
                                            if(zaIleSekundWejdzieKolejnyAtak - zaIleWrociFlota > 120)
                                            {
//                                                Log.printLog1("2.1.1.1",FleetSaveAttack.class,163);
                                                if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS() : p.isFlotaZPlanetyWyslanaNaFS())
                                                {
                                                    if(zawroc(wrogaMisja,p))
                                                    {
                                                        wrogaMisja.setCounter(3);
//                                                        Log.printLog1("2.1.1.1.1",FleetSaveAttack.class,171);
                                                    }
                                                }
                                                // Flota nie jest wysłana na FS'a lub została już zawrócona z FS'a
                                                else
                                                {
                                                    wrogaMisja.setCounter(3);
//                                                    Log.printLog1("2.1.1.1.2",FleetSaveAttack.class,179);
                                                }
                                            }
                                            else
                                            {
                                                wrogaMisja.setCounter(1);
//                                                Log.printLog1("Ustawiam counter na wartość 1.",FleetSaveAttack.class,185);
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    Log.printLog1("2.2",FleetSaveAttack.class,226);
                                    if(wrogaMisja.isNaKsiezyc() ? p.isFlotaZKsiezycaWyslanaNaFS():p.isFlotaZPlanetyWyslanaNaFS())
                                    {
                                        if(zawroc(wrogaMisja,p))
                                        {
                                            wrogaMisja.setCounter(3);
                                            Log.printLog1("2.2.1",FleetSaveAttack.class,230);
                                        }
                                    }
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
                        Log.printLog(FleetSaveAttack.class.getName(),"STOP----------------"+wrogaMisja.getId()+"--------------------");
                    }

                    if(delete != null)
                    {
                        WrogieMisje.remove(delete);
                        delete = null;
                    }
                }
            }
        }
        else
        {
            if(czasWykonania.ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 60)
            {
                Log.printLog(FleetSaveAttack.class.getName(), "OFF");
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }

            if(!czasWykonania.isActive())
            {
                czasWykonania.setActive(true);
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }
        }
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
        Wspolrzedne wspolrzedne = StaticCoord.KALYKE_FS_1_9_12;
        if(FlotaI.isAnyShips(OgameWeb.webDriver))
        {
            FlotaI.chooseAllShips(OgameWeb.webDriver);
            FlotaI.clickContinue(OgameWeb.webDriver);
            Waiter.sleep(50,50);
            // Wysyłany z księżyca - wyślij na planetę
            if(moon)
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
            else
            {
                // Posiada księżyć - wyślij na księżyc
                if(p.isMoon())
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
                    FlotaII.setGalaxy(OgameWeb.webDriver,wspolrzedne.getGalaktyka());
                    FlotaII.setUklad(OgameWeb.webDriver,wspolrzedne.getUklad());
                    FlotaII.setPlaneta(OgameWeb.webDriver,wspolrzedne.getPlaneta());
                    FlotaII.setSpeed(OgameWeb.webDriver,1);
                    FlotaII.clickPlaneta(OgameWeb.webDriver);
                    FlotaII.clickContinue(OgameWeb.webDriver);
                    Waiter.sleep(100,100);
                    wrogaMisja.getDaneWyslanegoFS().setObiektLotu(new ObiektLotu(wspolrzedne.toString(),true));
                    p.setObiektFSZPlanety(wrogaMisja.getDaneWyslanegoFS().getObiektLotu());
                }
            }

            boolean missionSelected = false;
            while(!missionSelected)
            {
                if(moon || p.isMoon())
                    FlotaIII.kliknijMisje(OgameWeb.webDriver,5);
                else
                    FlotaIII.kliknijMisje(OgameWeb.webDriver,8);

                Waiter.sleep(25,25);
                missionSelected = FlotaIII.isMissionSelected(OgameWeb.webDriver, FleetSaveAttack.class.getName());
            }
            FlotaIII.clickWszystkieSurowce(OgameWeb.webDriver);
            FlotaIII.wyslijFlote(OgameWeb.webDriver);

            wrogaMisja.getDaneWyslanegoFS().setCzas(CzasGry.getCzas().toString());
            wrogaMisja.getDaneWyslanegoFS().setData(CzasGry.getData().toString());

            Log.printLog(FleetSaveAttack.class.getName(),"Dane wysłanego FS'a z "+ (moon ? "księżyca " : "planety ")
                    + p.getWspolrzedne() +"\n "+ wrogaMisja.getDaneWyslanegoFS().toString());

            if(moon)
                p.setFlotaZKsiezycaWyslanaNaFS(true);
            else
                p.setFlotaZPlanetyWyslanaNaFS(true);
        }
        else
            Log.printLog(FleetSaveAttack.class.getName(),"Brak statków na "+ (moon ? "księżyc " : "planeta ")
                    + p.getWspolrzedne());

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
}
