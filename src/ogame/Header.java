package ogame;

import com.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header {

    private static String title(WebDriver w, String className, String czescTresciNaglowka)
    {
        String [] paths = {
                "/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div[2]/form/div/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div/div[1]/div[1]/div[2]/h2/a/p",
                "/html/body/div[5]/div[3]/div[2]/div/header/h2",
                "/html/body/div[5]/div[3]/div[2]/div[3]/div/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div[1]/div/div[2]/h2",
                "/html/body/div[5]/div[3]/div[2]/div/div/header/h2"
        };

        WebElement e;
        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
                e = w.findElement(By.xpath(paths[index]));
                if(e.getText().contains(czescTresciNaglowka))
                {
                    bool = false;
                    return  e.getText();
                }
            }
            catch(Exception ex)
            {
//                Log.printLog("Nie wczytano Tytuł Header.");
            }
            finally
            {
                index++;
                if(index >= paths.length)
                {
                    bool = false;
                    Log.printLog(className,"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                }

//                if(bool)
//                    Log.printLog(className, "Nie wczytano Tytuł Header. Zmieniam ścieżkę "+ index);
            }
        }
        Log.printLog(className, "Nie jest wyświetlony nagłówek Menu.");
        return null;
    }

    public static boolean dobryHeaderWyswietlony(WebDriver w, String czescTresciNaglowka, String className)
    {
        String s = title(w,className,czescTresciNaglowka);
        return s != null && s.contains(czescTresciNaglowka);
    }
}
