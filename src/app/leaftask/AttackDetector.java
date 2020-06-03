package app.leaftask;

import app.LeafTask;
import com.Log;
import ogame.attack.Attack;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import org.openqa.selenium.WebDriver;

import java.util.List;

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

//                List<RuchFlotZdarzenia.Misja> misje = RuchFlotZdarzenia.misje(getW());
//                for(RuchFlotZdarzenia.Misja m : misje)
//                    Log.printLog1(m.toString(),Attack.class, 29);


                if(attack)
                {
                    RuchFlotZdarzenia.rozwin(getW());
                    Log.printLog(Attack.class.getName(), "Wykryto atak.");

                    int tmp = RuchFlotZdarzenia.iloscMisjiWrogich(getW());
                    Log.printLog1(String.valueOf(tmp), AttackDetector.class,33);

                    if(tmp != iloscMisjiWrogich)
                    {
                        iloscMisjiWrogich = tmp;
                        Log.printLog(Attack.class.getName(), "Pobieram dane o wrogich misjach.");
                        //todo - co zrobić gdy wykryje atak
                    }

                }
                else
                    Log.printLog(Attack.class.getName(), "Brak ataku.");

                setLastTimeExecute(System.currentTimeMillis());
            }
        }
        else
            Log.printLog(Attack.class.getName(), "OFF");
    }
}
