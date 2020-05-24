package app.tasks;

import app.Task;
import com.Waiter;
import ogame.GameTime;
import org.openqa.selenium.WebDriver;

public class CzasGry extends Task {

    public CzasGry(WebDriver w) {
        super(w, "Czas gry");
        setRun(false);
        setThread(new Thread(this));
        startThread();
    }

    @Override
    public void run() {
        while(true)
        {
            if(isRun())
            {
                app.czas.CzasGry.setDataString(GameTime.date(getW()));
                app.czas.CzasGry.setCzasString(GameTime.time(getW()));
            }
            Waiter.sleep(50,200);
        }
    }
}
