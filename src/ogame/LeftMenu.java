package ogame;

import com.Log;
import com.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LeftMenu
{


    public static void pressPodglad(WebDriver w, String className)
    {

        String [] paths = {
                "/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[1]/a",
                "/html/body/div[5]/div[2]/div[3]/div/ul/li[1]/a"
        };


        if(isDobryHeaderWyswietlony(w,"Podgląd", className))
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

    public static void pressPodglad(WebDriver w, String className, boolean klikajZawsze)
    {

        String [] paths = {
                "/html/body/div[2]/div[2]/div[2]/div[1]/div[2]/div/ul/li[1]/a",
                "/html/body/div[5]/div[2]/div[3]/div/ul/li[1]/a"
        };


        if(!klikajZawsze && isDobryHeaderWyswietlony(w,"Podgląd", className))
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

        if(isDobryHeaderWyswietlony(w,"Ustawienia surowców", className))
        {
            Log.printLog(className,"Przycisk Ustawienia surowców jest już wybrany.");
        }
        else
        {
            WebElement e;
            e = w.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div/ul/li[2]/span/a/div"));
            e.click();
            Log.printLog(className,"Klikam przycisk Ustawienia surowców.");
        }
    }

    public static void pressBadania(WebDriver w, String className)
    {

        if(isDobryHeaderWyswietlony(w,"Badania", className))
        {
            Log.printLog(className,"Przycisk Badania jest już wybrany.");
        }
        else
        {
            WebElement e;
            e = w.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div/ul/li[6]/a"));
            e.click();
            Log.printLog(className,"Klikam przycisk Badania.");
        }
    }

    public static void pressSurowce(WebDriver w, String className)
    {

        if(isDobryHeaderWyswietlony(w,"Surowce", className))
        {
            Log.printLog(className,"Przycisk Surowce jest już wybrany.");
        }
        else
        {
            WebElement e;
            e = w.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div/ul/li[2]/a"));
            e.click();
            Log.printLog(className,"Klikam przycisk Surowce.");
        }
    }

    public static void pressFlota(WebDriver w, String className)
    {

        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", className))
        {
            Log.printLog(className,"Przycisk Flota jest już wybrany.");
        }
        else
        {
            WebElement e;
            e = w.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div/ul/li[9]/a"));
            e.click();
            Log.printLog(className,"Klikam przycisk flota.");
        }
    }


    public static void pressRuchFlot(WebDriver w, String className)
    {
        if(isDobryHeaderWyswietlony(w,"Ruch floty", className))
        {
            Log.printLog(className,"Ruch flot jest wybrany.");
        }
        else
        {
            Waiter.sleep(50,50);
            WebElement e = w.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div/ul/li[9]/span/a/div"));
            e.click();
            Log.printLog(className,"Klikam ruch flot.");
        }
    }

    /**
     * Należy używać metod z klasy Header
     */
    @Deprecated
    private static String getTytulHeader(WebDriver w, String className)
    {
        String [] paths = {
                "/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div[2]/form/div/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div/div[1]/div[1]/div[2]/h2/a/p",
                "/html/body/div[5]/div[3]/div[2]/div/header/h2",
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
                bool = false;
                return  e.getText();
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

    private static String getTytulHeader(WebDriver w, String className, String czescTresciNaglowka)
    {
        String [] paths = {
                "/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div[2]/form/div/div[1]/h2",
                "/html/body/div[5]/div[3]/div[2]/div/div[1]/div[1]/div[2]/h2/a/p",
                "/html/body/div[5]/div[3]/div[2]/div/header/h2",
                "/html/body/div[5]/div[3]/div[2]/div[1]/div/div[2]/h2",
                "/html/body/div[5]/div[3]/div[2]/div/div/header/h2",
        };

        WebElement e;
        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
//                Log.printLog1(paths[index],LeftMenu.class,140);
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
//
//                if(bool)
//                    Log.printLog(className, "Nie wczytano Tytuł Header. Zmieniam ścieżkę "+ index);
            }
        }
        Log.printLog(className, "Nie jest wyświetlony nagłówek Menu.");
        return null;
    }

    private static boolean isDobryHeaderWyswietlony(WebDriver w, String czescTresciNaglowka, String className)
    {
        String s = getTytulHeader(w,className,czescTresciNaglowka);
//        Log.printLog1(s,LeftMenu.class,172);
        return s != null && s.contains(czescTresciNaglowka);
    }
}
