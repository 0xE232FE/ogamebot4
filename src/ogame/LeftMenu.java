package ogame;

import com.Log;
import com.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LeftMenu
{

    private static boolean press(WebDriver w, String [] paths , String header, String className)
    {
        if(Header.dobryHeaderWyswietlony(w,header,className))
        {
            Log.printLog(className,"Przycisk " + header + " jest już wybrany.");
            return true;
        }
        else
        {
            WebElement e;
            int index = 0;
            boolean bool = true;

            while(bool)
            {
                try
                {
                    Waiter.sleep(50,50);
                    e = w.findElement(By.xpath(paths[index]));
                    bool = false;
                    e.click();
                    Log.printLog(className,"Klikam przycisk " + header + ".");
                    return true;
                }
                catch(Exception ex)
                {
                    Log.printErrorLog(LeftMenu.class.getName(),"Zwrócno błąd przy kliknięciu w "+header + ".");
                }
                finally
                {
                    index++;
                    if(index >= paths.length)
                    {
                        bool = false;
                        Log.printLog(className,"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                    }
                }
            }
        }
        return false;
    }

    public static void pressPodglad(WebDriver w, String className)
    {
        press(w,
                new String[]{"/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[1]/a",
               "/html/body/div[5]/div[2]/div[3]/div/ul/li[1]/a"},
                "Podgląd",
                className);
    }

    public static void pressPodglad(WebDriver w, String className, boolean klikajZawsze)
    {

        String [] paths = {
                "/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[1]/a",
                "/html/body/div[5]/div[2]/div[3]/div/ul/li[1]/a"
        };


        if(!klikajZawsze && Header.dobryHeaderWyswietlony(w,"Podgląd",className))
        {
            Log.printLog(className,"Przycisk Podgląd jest już wybrany.");
        }
        else
        {
            WebElement e;
            int index = 0;
            boolean bool = true;

            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(paths[index]));
                    bool = false;
                    e.click();
                    Log.printLog(className,"Klikam przycisk Podgląd.");
                }
                catch(Exception ex)
                {
                    Log.printErrorLog(LeftMenu.class.getName(),"Zwrócno błąd przy kliknięciu w Podgląd.");
                }
                finally
                {
                    index++;
                    if(index >= paths.length)
                    {
                        bool = false;
                        Log.printLog(className,"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                    }
                }
            }
        }
    }

    public static void pressUstawieniaSurowcow(WebDriver w, String className)
    {
        press(w,
                new String[]{"/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[2]/span/a/div",
                        "/html/body/div[5]/div[2]/div[3]/div/ul/li[2]/span/a/div"},
                "Ustawienia surowców",
                className);
    }

    public static void pressBadania(WebDriver w, String className)
    {
        press(w,
                new String[]{"/html/body/div[5]/div[2]/div[3]/div/ul/li[6]/a/span",
                        "/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[6]/a"},
                "Badania",
                className);
    }

    public static void pressSurowce(WebDriver w, String className)
    {
        press(w,
                new String[]{"/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[2]/a",
                        "/html/body/div[5]/div[2]/div[3]/div/ul/li[2]/a/span"},
                "Surowce",
                className);
    }


    public static void pressFlota(WebDriver w, String className)
    {
        press(w,
                new String[]{"/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[9]/a",
                        "/html/body/div[5]/div[2]/div[3]/div/ul/li[9]/a/span"},
                "Flota",
                className);
    }

    public static boolean pressRuchFlot(WebDriver w, String className)
    {
        return press(w,
                new String[]{"//*[@id=\"menuTable\"]/li[9]/span/a/div"},
                "Ruch floty",
                className);
    }

    public static boolean pressStacja(WebDriver w, String className)
    {

       return press(w,
                new String[]{"//*[@id=\"menuTable\"]/li[3]/a"},
                "Stacja",
                className);
    }
}
