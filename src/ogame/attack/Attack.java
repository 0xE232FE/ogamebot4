package ogame.attack;

import com.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Attack {

    public static boolean detected(WebDriver w)
    {
        try
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"attack_alert\"]"));
            String s = e.getAttribute("class");
            if(s.contains("noAttack"))
                return false;
            else
                return true;
        }
        catch (Exception e)
        {
            Log.printLog(Attack.class.getName(), "Brak szukanego elementu. Sprawdź zakładkę.");
        }

        return false;
    }
}
