package app.leaftask;

import app.LeafTask;
import app.atak.WrogieMisje;
import com.Log;
import ogame.attack.Attack;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import org.openqa.selenium.WebDriver;

public class AttackDetector extends LeafTask
{

    private int iloscMisjiWrogich = -1;
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
                    Log.printLog(Attack.class.getName(), "Wykryto atak.");

                    int tmp = RuchFlotZdarzenia.iloscMisjiWrogich(getW());

                    if(tmp != iloscMisjiWrogich)
                    {
                        RuchFlotZdarzenia.rozwin(getW());
                        iloscMisjiWrogich = tmp;
                        Log.printLog(Attack.class.getName(), "Pobieram dane o wrogich misjach.");
                        WrogieMisje.setMisje(RuchFlotZdarzenia.getWrogieMisje(getW()));
                        Log.printLog(Attack.class.getName(), "Zakończono pobieranie danych o wrogich misjach.");
                    }
                    else
                    {
                        WrogieMisje.printLeftTime();
                    }

                }
                else
                    Log.printLog(Attack.class.getName(), "Brak ataku.");

                setLastTimeExecute(System.currentTimeMillis());
            }
        }
        else
            Log.printLog(AttackDetector.class.getName(), "OFF");
    }
}
