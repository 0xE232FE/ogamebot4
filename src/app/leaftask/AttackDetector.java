package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.atak.WrogieMisje;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.data.fleet_save_attack.KliknijPodglad;
import app.log.LogFleetSaveAttack;
import com.Log;
import com.Waiter;
import ogame.LeftMenu;
import ogame.attack.Attack;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import org.openqa.selenium.WebDriver;

public class AttackDetector extends LeafTask
{

    private int iloscMisjiWrogich = -1;
    private CzasWykonania czasWykonania = new CzasWykonania();

    public AttackDetector(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Attack Detector");
    }

    @Override
    public void execute() {

        if (isRun()) {
            if (isSleepTimeOut(System.currentTimeMillis())) {

                boolean attack = Attack.detected(getW());

                if(attack)
                {

                    int tmp = RuchFlotZdarzenia.iloscMisjiWrogich(getW());
//                    Log.printLog(Attack.class.getName(), "Wykryto " + tmp + " " +
//                            (tmp == 1 ? "atak" : tmp >=2 && tmp <=4 ? "ataki" :"ataków") + ". Lista posiada "
//                            + WrogieMisje.misje.size() + " misje.");
                    if(tmp != iloscMisjiWrogich)
                    {
                        while(RuchFlotZdarzenia.eventBoxNiewidoczny(OgameWeb.webDriver))
                        {
                            RuchFlotZdarzenia.rozwin(OgameWeb.webDriver);
                            Waiter.sleep(50,50);
                        }
                        iloscMisjiWrogich = tmp;
                        Log.printLog(Attack.class.getName(), "Pobieram dane o wrogich misjach.");
                        WrogieMisje.setMisje(RuchFlotZdarzenia.getWrogieMisje(OgameWeb.webDriver));
                        Log.printLog(Attack.class.getName(), "Zakończono pobieranie danych o wrogich misjach.");
                    }
                    else
                    {
                        WrogieMisje.printLeftTime();
                    }
                    FleetSaveAttack.attack = true;
                }
//                else
//                {
//                    Log.printLog(Attack.class.getName(), "Brak ataku.");
//                }
                setLastTimeExecute(System.currentTimeMillis());

                klikPodglad();
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

    private boolean init = true;
    private CzasWykonania czasWykonaniaKlikPodglad = new CzasWykonania();
    private void klikPodglad()
    {
        if(init)
        {
            if(CzasGry.getCzas().czasWSekundach() > 0)
            {
                czasWykonaniaKlikPodglad.setCzasString(CzasGry.getCzas().toString());
                czasWykonaniaKlikPodglad.setDataString(CzasGry.getData().toString());
                init = false;
                Log.printLog(AttackDetector.class.getName(),"Inicjalizuję dane do metody klikPodglad(). Czas "+czasWykonaniaKlikPodglad.getCzas() +
                        " Data " + czasWykonaniaKlikPodglad.getData());
            }
        }
        else
        {
//            Log.printLog(AttackDetector.class.getName(),"Aktualna data "+CzasGry.getData().toString() + " Data ostatniego wykonania "
//            + czasWykonaniaKlikPodglad.getData() + " Aktualny czas " + CzasGry.getCzas().toString() + " Czas ostatniego wykonania " +
//                    czasWykonaniaKlikPodglad.getCzas().toString() +" \n Różnica " + (CzasGry.getCzas().czasWSekundach() - czasWykonaniaKlikPodglad.getCzas().czasWSekundach()));
            if(CzasGry.getData().toString().equals(czasWykonaniaKlikPodglad.getData().getTommorowDate().toString()))
            {
                LeftMenu.pressPodglad(OgameWeb.webDriver,AttackDetector.class.getName(),true);
                Log.printLog(AttackDetector.class.getName(),"Kliknięto Podgląd, żeby odświeżyć stronę. Zaaktualizowano " +
                        "datę na kolejny dzień "+CzasGry.getData().toString());
                LogFleetSaveAttack.addLog(new app.log.Log(AttackDetector.class.getName(),"Odświeżono stronę poprzez kliknięcie w podgląd. "));
//                LogFleetSaveAttack.addLog(LogFleetSaveAttack.log(AttackDetector.class,"Odświeżono stronę poprzez kliknięcie w podgląd. "));
                czasWykonaniaKlikPodglad.setCzasString(CzasGry.getCzas().toString());
                czasWykonaniaKlikPodglad.setDataString(CzasGry.getData().toString());
            }
            else
            {
                if(CzasGry.getData().toString().equals(czasWykonaniaKlikPodglad.getData().toString()) &&
                        CzasGry.getCzas().czasWSekundach() - czasWykonaniaKlikPodglad.getCzas().czasWSekundach() >= KliknijPodglad.getCoIleSekundOdswiezyc())
                {
                    LeftMenu.pressPodglad(OgameWeb.webDriver,AttackDetector.class.getName(),true);
                    Log.printLog(AttackDetector.class.getName(),"Kliknięto Podgląd, żeby odświeżyć stronę.");
                    LogFleetSaveAttack.addLog(new app.log.Log(AttackDetector.class.getName(),"Odświeżono stronę poprzez kliknięcie w podgląd. "));
//                    LogFleetSaveAttack.addLog(LogFleetSaveAttack.log(AttackDetector.class,"Odświeżono stronę poprzez kliknięcie w podgląd. "));
                    czasWykonaniaKlikPodglad.setCzasString(CzasGry.getCzas().toString());
                    czasWykonaniaKlikPodglad.setDataString(CzasGry.getData().toString());
                }
            }
        }
    }
}
