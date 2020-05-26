package app.leaftask;

import app.LeafTask;
import com.Log;
import ogame.attack.Attack;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import org.openqa.selenium.WebDriver;

public class AttackDetector extends LeafTask
{

    public AttackDetector(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Attack Detector");
    }

    @Override
    public void execute() {

        if (isRun()) {
            if (isSleepTimeOut(System.currentTimeMillis())) {

                boolean attack = Attack.detected(getW());

//                planety.setAtackDetected(attack);

                if (attack)
                {
                    RuchFlotZdarzenia.rozwin(getW());
                    Log.printLog(Attack.class.getName(), "Wykryto atak.");
                    //todo - co zrobić gdy wykryje atak
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
