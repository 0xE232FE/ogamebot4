package app.tasks;

import app.OgameWeb;
import app.Task;
import app.log.Log;
import app.log.LogFleetSaveAttack;
import com.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CloseAd extends Task {

    public CloseAd(WebDriver w)
    {
        super(w);
        setThread(new Thread(this));
        startThread();
    }

    @Override
    public void run() {
        while(true)
        {
            if(isRun())
            {
                closeAd();
            }
            Waiter.sleep(1*1000,5*1000);
        }
    }

    /**
     * Wykrywa pojawiającą się reklamę i ją zamyka.
     */
    private  void closeAd()
    {
            try
            {
                WebElement x = OgameWeb.webDriver.findElement(By.className("openX_int_closeButton"));
                x.findElement(By.tagName("a")).click();
                LogFleetSaveAttack.addLog(new Log(CloseAd.class.getName(),"Znaleziono rekląmę. Kliknięto zamknij."));
            }
            catch (Exception e)
            {
//                com.Log.printErrorLog(CloseAd.class.getName(),"Nie znaleziono reklamy.");
            }
    }
}
