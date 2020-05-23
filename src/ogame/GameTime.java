package ogame;

import com.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GameTime
{
    public static String time (WebDriver w)
    {
        boolean bool = true;

        while(bool)
        {
            try
            {
                String s = w.findElement(By.xpath("/html/body/div[5]/div[1]/div[5]/div/ul/li[9]")).getText();
                StringBuilder sb = new StringBuilder(s);

                bool = false;
                return sb.substring(11,s.length());
            }
            catch(Exception e)
            {
                Log.printLog(GameTime.class.getName(),"Nie wczytano czasu gry. Zwracam null.");
            }
            finally
            {
                if(bool)
                    Log.printLog(GameTime.class.getName(),"Nie wczytano czasu gry. Powtarzam próbę.");
            }
        }
        return null;
    }

    public static String date(WebDriver w)
    {
        boolean bool = true;

        while(bool)
        {
            try
            {
                String s = w.findElement(By.xpath("/html/body/div[5]/div[1]/div[5]/div/ul/li[9]")).getText();
                StringBuilder sb = new StringBuilder(s);

                bool = false;
                return sb.substring(0,10);
            }
            catch(Exception e)
            {
                Log.printLog(GameTime.class.getName(),"Nie wczytano daty gry. Zwracam null.");
            }
            finally
            {
                if(bool)
                    Log.printLog(GameTime.class.getName(),"Nie wczytano daty gry. Powtarzam próbę.");
            }
        }
        return null;
    }


}
