package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.data.autotransport.AutotransportData;
import app.gui.autotransport.AutotransportInfo;
import app.gui.autotransport.AutotransportTabController;
import com.DifferentMethods;
import com.Log;
import com.Waiter;
import ogame.LeftMenu;
import ogame.flota.FlotaI;
import ogame.flota.FlotaII;
import ogame.flota.FlotaIII;
import ogame.planety.ListaPlanet;
import ogame.planety.Planeta;
import ogame.surowce.SurowceNaPlanecie;
import org.openqa.selenium.WebDriver;

public class Autotransport extends LeafTask
{
    public Autotransport(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Autotransport",false);
    }

    private CzasWykonania czasWykonania = new CzasWykonania();
    private int technologiaNadprzestrzenna = AutotransportData.configuration.getTechnologiaNadprzestrzenna();
    private int iloscMTZadeklarowana = AutotransportData.configuration.getFlota().getMt().getIlosc();
    private int iloscDTZadeklarowana = AutotransportData.configuration.getFlota().getDt().getIlosc();
    private int iloscMisji = -1, maksymalnaIloscMisji = -1;
    private final int MIN_ILOSC_SUROWCOW = AutotransportData.configuration.getIloscSurowcow();

    @Override
    public void execute()
    {
        if (isRun())
        {
            if (isSleepTimeOut(System.currentTimeMillis()))
            {
                int metal, krysztal, deuter, sumaSurowcow;

                //Pobieranie danych z listy
                for(AutotransportInfo autotransportInfo : AutotransportTabController.getAutoTransportInfoList())
                {
                    Planeta p = autotransportInfo.getAutotransport().getPlaneta();
                    app.data.autotransport.Autotransport autotransport = autotransportInfo.getAutotransport();
                    //Sprawdzenie czy wysyłanie surowców z planety jest aktywne
                    if(autotransport.isWyslijUser() &&  autotransport.isWyslijPonownie())
                    {
                        //Kliknięcie w planetę
                        while(ListaPlanet.wybranaPlaneta(OgameWeb.webDriver) != p.getPozycjaNaLiscie() )
                        {
                            p.clickPlanet();
                            Waiter.sleep(100,150);
                        }
                        //Kliknięcie w zakladkę flota
                        LeftMenu.pressFlota(OgameWeb.webDriver,Autotransport.class.getName());
                        iloscMisji = FlotaI.iloscMisji(OgameWeb.webDriver);
                        maksymalnaIloscMisji = FlotaI.maxIloscMisji(OgameWeb.webDriver);
                        //Sprawdzanie czy są wolne sloty misji. Pozostawione powinny być dwa wolne sloty na wypadek ataku i FS'a
                        if(dostepneWolneFloty())
                        {
                            //Pobranie danych o ilości surowców
                            metal = SurowceNaPlanecie.metal(OgameWeb.webDriver);
                            krysztal = SurowceNaPlanecie.krysztal(OgameWeb.webDriver);
                            deuter = SurowceNaPlanecie.deuter(OgameWeb.webDriver);

                            sumaSurowcow = metal + krysztal + deuter;

                            if(sumaSurowcow > MIN_ILOSC_SUROWCOW)
                            {
                                //Pobieranie danych o ilości statków na planecie
                                int mt = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,1);
                                int dt = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,2);

                               if(FlotaI.isAnyShips(OgameWeb.webDriver)&& (mt != 0 || dt != 0))
                               {
                                   //Okreslanie ilości małych transporterów potrzebnych do przewiezienia surowców
                                   int potrzebnaIloscMT = sumaSurowcow/(5000 +(technologiaNadprzestrzenna*250)) + 1;
                                   //Określanie ilości dużych transporterów potrzebnych do przewiezienia surowców
                                   int potrzebnaIloscDT = sumaSurowcow/(25000 +(technologiaNadprzestrzenna*1250)) + 1;
                                   //Sprawdzenie czy wysyłać DT czy MT w pierwszej kolejności
                                   //Wyślij MT
                                   if(iloscMTZadeklarowana >= iloscDTZadeklarowana*5)
                                   {
                                       //Sprawdzenie czy wystarczy samych MT czy nalezy wysłać DT
                                       if(potrzebnaIloscMT <= mt )
                                           potrzebnaIloscDT = 0;
                                       else
                                       {
                                           int tmp = sumaSurowcow;
                                           //Ile pozostało surowców, gdy załaduje wszytstko na MT.
                                           tmp = tmp - mt * (5000+(technologiaNadprzestrzenna*250));
                                           potrzebnaIloscDT = tmp/(25000 +(technologiaNadprzestrzenna*1250)) + 1;
                                       }
                                       //Wpisywanie ilości floty
                                       FlotaI.wprowadzIloscStatekCywilny(OgameWeb.webDriver,1,potrzebnaIloscMT+1);
                                       if(potrzebnaIloscDT != 0)
                                           FlotaI.wprowadzIloscStatekCywilny(OgameWeb.webDriver,2,potrzebnaIloscDT);

                                       Waiter.sleep(50,100);
                                       FlotaI.clickContinue(OgameWeb.webDriver);
                                       //Wpisywanie współrzednych
                                       FlotaII.setGalaxy(OgameWeb.webDriver,AutotransportData.configuration.getGalaktyka());
                                       FlotaII.setUklad(OgameWeb.webDriver,AutotransportData.configuration.getUklad());
                                       FlotaII.setPlaneta(OgameWeb.webDriver,AutotransportData.configuration.getPlaneta());
                                       if(AutotransportData.configuration.isPlanet())
                                           FlotaII.clickPlaneta(OgameWeb.webDriver);
                                       else if(AutotransportData.configuration.isKsiezyc())
                                           FlotaII.clickKsiezyc(OgameWeb.webDriver);

                                       FlotaII.clickContinue(OgameWeb.webDriver);

                                       boolean missionSelected = false;
                                       //Wybieranie rodzaju misji.
                                       while(!missionSelected)
                                       {
                                           FlotaIII.kliknijMisje(OgameWeb.webDriver,4);
                                           Waiter.sleep(25,25);
                                           missionSelected = FlotaIII.isMissionSelected(OgameWeb.webDriver, FleetSaveAttack.class.getName());
                                       }
                                       //Załadowanie surowców
                                       FlotaIII.clickWszystkieSurowce(OgameWeb.webDriver);
                                       //Pobieranie danych o wysłanych surowcach
                                       int wolnaPrzestrzen = FlotaIII.wolnaPrzestrzenLadunkowa(OgameWeb.webDriver);
                                       int maxPrzestrzen = FlotaIII.maksymalnaPrzestrzenLadunkowa(OgameWeb.webDriver);
                                       if(wolnaPrzestrzen == 0)
                                       {
                                           autotransport.setPozostaloSurowcow(sumaSurowcow-maxPrzestrzen);
                                           autotransport.setZabranoSurowcow(maxPrzestrzen);
                                       }
                                       else
                                       {
                                           autotransport.setPozostaloSurowcow(0);
                                           autotransport.setZabranoSurowcow(sumaSurowcow);
                                       }
                                       //Pobieranie danych o wysłanej flocie.
                                       autotransport.setCzasDostarczenia(FlotaIII.przylot(OgameWeb.webDriver));
                                       autotransport.setCzasPowrotu(FlotaIII.powrot(OgameWeb.webDriver));
                                       //Ustawienie czasu następnego wykonania.
                                       //Ustawienie czasu następnego wykonania.
                                       if(autotransport.getPozostaloSurowcow() >= MIN_ILOSC_SUROWCOW)
                                       {
                                           autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                                   CzasGry.getCzas(),CzasGry.getData(),
                                                   new Czas(FlotaIII.czasLotu(OgameWeb.webDriver).getCzas().czasWSekundach()*2+60)
                                           ));
                                       }
                                       else
                                       {
                                           autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                                   CzasGry.getCzas(),CzasGry.getData(),
                                                   AutotransportData.getInterwalCzasowy()
                                           ));
                                       }
                                       autotransport.setWyslijPonownie(false);
                                       //Wyślij flotę
                                       FlotaIII.wyslijFlote(OgameWeb.webDriver);
                                   }
                                   //Wyslij DT
                                   else
                                   {
                                       //Sprawdzenie czy wystarczy samych DT czy nalezy wysłać MT
                                       if(potrzebnaIloscDT <= dt )
                                           potrzebnaIloscMT = 0;
                                       else
                                       {
                                           int tmp = sumaSurowcow;
                                           //Ile pozostało surowców, gdy załaduje wszytstko na DT.
                                           tmp = tmp - dt * (25000+(technologiaNadprzestrzenna*1250));
                                           potrzebnaIloscMT = tmp/(5000 +(technologiaNadprzestrzenna*250)) + 1;
                                       }

                                       //Wpisywanie ilości floty
                                       FlotaI.wprowadzIloscStatekCywilny(OgameWeb.webDriver,2,potrzebnaIloscDT+1);
                                       if(potrzebnaIloscMT != 0)
                                           FlotaI.wprowadzIloscStatekCywilny(OgameWeb.webDriver,1,potrzebnaIloscMT+1);

                                       Waiter.sleep(50,100);
                                       FlotaI.clickContinue(OgameWeb.webDriver);
                                       //Wpisywanie współrzednych
                                       FlotaII.setGalaxy(OgameWeb.webDriver,AutotransportData.configuration.getGalaktyka());
                                       FlotaII.setUklad(OgameWeb.webDriver,AutotransportData.configuration.getUklad());
                                       FlotaII.setPlaneta(OgameWeb.webDriver,AutotransportData.configuration.getPlaneta());
                                       if(AutotransportData.configuration.isPlanet())
                                           FlotaII.clickPlaneta(OgameWeb.webDriver);
                                       else if(AutotransportData.configuration.isKsiezyc())
                                           FlotaII.clickKsiezyc(OgameWeb.webDriver);

                                       FlotaII.clickContinue(OgameWeb.webDriver);

                                       boolean missionSelected = false;
                                       //Wybieranie rodzaju misji.
                                       while(!missionSelected)
                                       {
                                           FlotaIII.kliknijMisje(OgameWeb.webDriver,4);
                                           Waiter.sleep(25,25);
                                           missionSelected = FlotaIII.isMissionSelected(OgameWeb.webDriver, FleetSaveAttack.class.getName());
                                       }
                                       //Załadowanie surowców
                                       FlotaIII.clickWszystkieSurowce(OgameWeb.webDriver);
                                       //Pobieranie danych o wysłanych surowcach
                                       int wolnaPrzestrzen = FlotaIII.wolnaPrzestrzenLadunkowa(OgameWeb.webDriver);
                                       int maxPrzestrzen = FlotaIII.maksymalnaPrzestrzenLadunkowa(OgameWeb.webDriver);
                                       if(wolnaPrzestrzen == 0)
                                       {
                                           autotransport.setPozostaloSurowcow(sumaSurowcow-maxPrzestrzen);
                                           autotransport.setZabranoSurowcow(maxPrzestrzen);
                                       }
                                       else
                                       {
                                           autotransport.setPozostaloSurowcow(0);
                                           autotransport.setZabranoSurowcow(sumaSurowcow);
                                       }
                                       //Pobieranie danych o wysłanej flocie.
                                       autotransport.setCzasDostarczenia(FlotaIII.przylot(OgameWeb.webDriver));
                                       autotransport.setCzasPowrotu(FlotaIII.powrot(OgameWeb.webDriver));
                                       //Ustawienie czasu następnego wykonania.
                                       if(autotransport.getPozostaloSurowcow() >= MIN_ILOSC_SUROWCOW)
                                       {
                                           autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                                   CzasGry.getCzas(),CzasGry.getData(),
                                                   new Czas(FlotaIII.czasLotu(OgameWeb.webDriver).getCzas().czasWSekundach()*2+60)
                                           ));
                                       }
                                       else
                                       {
                                           autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                                   CzasGry.getCzas(),CzasGry.getData(),
                                                   AutotransportData.getInterwalCzasowy()
                                           ));
                                       }
                                       autotransport.setWyslijPonownie(false);
                                       //Wyślij flotę
                                       FlotaIII.wyslijFlote(OgameWeb.webDriver);
                                   }
                               }
                               else
                               {
                                   autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                           CzasGry.getCzas(),CzasGry.getData(),
                                           new Czas(0,30,0)
                                   ));
                                   autotransport.setWyslijPonownie(false);

                                   Log.printLog(Autotransport.class.getName(),"Nie wysłano transportu z planety  "+
                                           p.getWspolrzedne() +
                                           " z powodu braku Małych i Dużych Transporterów.");
                               }

                            }
                            else
                            {
                                autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                        CzasGry.getCzas(),CzasGry.getData(),
                                        AutotransportData.getInterwalCzasowy()
                                ));
                                autotransport.setWyslijPonownie(false);
                                autotransport.setPozostaloSurowcow(sumaSurowcow);

                                Log.printLog(Autotransport.class.getName(),"Nie wysłano transportu z planety  "+
                                        p.getWspolrzedne() +
                                        " z powodu zbyt małej ilość surowców. Na planecie znajdowało się " +
                                        DifferentMethods.addDots(String.valueOf(sumaSurowcow)));
                            }
                        }
                        else
                        {
                            autotransport.setNastepneWykonanie(CzasWykonania.okreslCzasWykonania(
                                    CzasGry.getCzas(),CzasGry.getData(),
                                    new Czas(0,10,0)
                            ));
                            autotransport.setWyslijPonownie(false);

                            Log.printLog(Autotransport.class.getName(),"Nie wysłano transportu z planety  "+
                                    p.getWspolrzedne() +
                                    " z powodu braku wolnych slotów.");
                        }
                    }
                    else
                        Log.printLog(Autotransport.class.getName(),"Dla planety o współrzędnych "+
                                p.getWspolrzedne() +
                                " autotransport jest nieaktywny.");
                }

                setLastTimeExecute(System.currentTimeMillis());
            }
        }
        else
        {
            if(czasWykonania.ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 60 )
            {
                Log.printLog(Autotransport.class.getName(), "OFF");
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

    /**
     * Pobiera ilość wysłanych misji z poprzedniej wykonywanej pętli.
     * @return true - jeżeli robi to pierwszy raz lub gdy są 3 wolne sloty.
     */
    private boolean dostepneWolneFloty()
    {
        if(maksymalnaIloscMisji == -1 || iloscMisji == -1)
            return true;
        return maksymalnaIloscMisji - iloscMisji > 2;
    }

}
