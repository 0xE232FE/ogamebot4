package ogame;

import com.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GameTime
{

    public static String time (WebDriver w)
    {
//        String [] paths = {
//                "/html/body/div[5]/div[1]/div[5]/div/ul/li[9]",
//                "/html/body/div[2]/div[2]/div[1]/div[2]/div/ul/li[9]"
//        };

        String [] paths = {
                "//*[@id=\"bar\"]/ul/li[9]",
        };

        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
                String s = w.findElement(By.xpath(paths[index])).getText();
                StringBuilder sb = new StringBuilder(s);

                bool = false;
                return sb.substring(11,s.length());
            }
            catch(Exception e)
            {
//                Log.printLog(GameTime.class.getName(),"Nie wczytano czasu gry. Sprawdzono ścieżkę nr "+index+".");
            }
            finally
            {
                index++;
//                if(bool)
//                    Log.printLog(GameTime.class.getName(),"Nie wczytano czasu gry. Powtarzam próbę.");
                if(index > paths.length)
                    bool = false;
            }
        }
//        Log.printLog(GameTime.class.getName(),"Nie wczytano czasu gry. Zwracam null.");
        return null;
    }

    public static String date(WebDriver w)
    {
//        String [] paths = {
//                "/html/body/div[5]/div[1]/div[5]/div/ul/li[9]",
//                "/html/body/div[2]/div[2]/div[1]/div[2]/div/ul/li[9]"
//        };

        String [] paths = {
                "//*[@id=\"bar\"]/ul/li[9]",
        };

        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
                String s = w.findElement(By.xpath(paths[index])).getText();
                StringBuilder sb = new StringBuilder(s);

                bool = false;
                return sb.substring(0,10);
            }
            catch(Exception e)
            {
//                Log.printLog(GameTime.class.getName(),"Nie wczytano daty gry. Sprawdzono ścieżkę nr "+index+".");
            }
            finally
            {
                index++;
//                if(bool)
//                    Log.printLog(GameTime.class.getName(),"Nie wczytano daty gry. Powtarzam próbę.");

                if(index > paths.length)
                    bool = false;
            }
        }
//        Log.printLog(GameTime.class.getName(),"Nie wczytano daty gry. Zwracam null.");
        return null;
    }
}
