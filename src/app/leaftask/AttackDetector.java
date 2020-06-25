package app.leaftask;

import app.LeafTask;
import app.OgameWeb;
import app.atak.WrogieMisje;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import com.Log;
import com.Waiter;
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
                    Log.printLog(Attack.class.getName(), "Wykryto " + tmp + " " +
                            (tmp == 1 ? "atak" : tmp >=2 && tmp <=4 ? "ataki" :"ataków") + ". Lista posiada "
                            + WrogieMisje.misje.size() + " misje.");
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
                else
                {
//                    Log.printLog(Attack.class.getName(), "Brak ataku.");
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
                czasWykonania.setActive(true);
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }
        }
    }
}
