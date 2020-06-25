package ogame.flota;

import app.GameClient;
import com.Log;
import com.Waiter;
import ogame.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlotaII
{
    /**
     * Wybiera pole do wpisania nr galakktyki i uzupełnia podaną cyfrą. Zakres jest zależny od Universum od 1 do max 8.
     * @param galaxy Numeer galaktyki zakres od 1 do 8.
     */
    public static void setGalaxy(WebDriver w, int galaxy)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"galaxy\"]"));
            GameClient.scrollToElement(w,e);
            Waiter.sleep(100,100);
            e.click();
            e.sendKeys(String.valueOf(galaxy));
            Log.printLog(FlotaII.class.getName(),"Wpisano współrzędne galaktyki: "+ galaxy);
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Współrzędne Galaktyki.");
    }

    /**
     * Wybiera pole do wpisania nr układu i uzupełnia podaną cyfrą. Powinna być z zakresu od 1 do 499.
     * @param u Numeer układu zakres od 1 do 499.
     */
    public static void setUklad(WebDriver w, int u)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"system\"]"));
            GameClient.scrollToElement(w,e);
            e.click();
            e.sendKeys(String.valueOf(u));
            Log.printLog(FlotaII.class.getName(),"Wpisano współrzędne układu: "+ u);
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Współrzędne Układu.");
    }

    /**
     * Wybiera pole do wpisania pozycji Planety i uzupełnia podaną cyfrą. Powinna być z zakresu od 1 do 15.
     * @param p Poozycja planety zakres od 1 do 15.
     */
    public static void setPlaneta(WebDriver w, int p)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"position\"]"));
            GameClient.scrollToElement(w,e);
            e.click();
            e.sendKeys(String.valueOf(p));
            Log.printLog(FlotaII.class.getName(),"Wpisano współrzędne planety: "+ p);
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Współrzędne Planety.");
    }

    /**
     * Wybiera jako cel lotu Planetę.
     */
    public static void clickPlaneta(WebDriver w)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"pbutton\"]"));
            GameClient.scrollToElement(w,e);
            e.click();
            Log.printLog(FlotaII.class.getName(),"Wybrano cel: Planeta");
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Planeta.");
    }

    /**
     * Wybiera jak cel lotu Księżyc.
     */
    public static void clickKsiezyc(WebDriver w)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"mbutton\"]"));
            GameClient.scrollToElement(w,e);
            e.click();
            Log.printLog(FlotaII.class.getName(),"Wybrano cel: Księżyc");
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Księżyc.");
    }

    /**
     * Ustawia prędkość. W zakresie od 1 do 10, gdzie 1 to 10% a 10 to 100%.
     * @param speed Od 1 do 10
     */
    public static void setSpeed(WebDriver w, int speed)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            String path = "/html/body/div[5]/div[3]/div[2]/div[2]/form/div/div[5]/div[2]/div[2]/ul[1]/li[3]/div[1]/div[2]/div["+speed+"]";
            w.findElement(By.xpath(path)).click();
            Log.printLog(FlotaII.class.getName(),"Wybrano prędkość: " + speed*10);
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Prędkość "+speed+".");
    }

    /**
     * Pobiera czas lotu.
     */
    public static String czasLotu(WebDriver w)
    {

//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            String path = "//*[@id=\"duration\"]";
            String s = w.findElement(By.xpath(path)).getText();
            Log.printLog(FlotaII.class.getName(),"Czas trwania lotu "+s);
            return s;
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Czas trwania lotu.");

        return null;
    }

    /**
     * Klika w button continue.
     */
    public static void clickContinue(WebDriver w)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę II", FlotaII.class.getName()))
        {
            w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[2]/form/div/div[5]/div[2]/div[2]/div/a[1]/span")).click();
            Log.printLog(FlotaII.class.getName(),"Klikam dalej.");
        }
        else
            Log.printLog(FlotaII.class.getName(),"Nie znaleziono WebElementu - Dalej.");
    }

    private static String getTytulHeader(WebDriver w, String className)
    {
        String [] paths = {
                "/html/body/div[5]/div[3]/div[2]/div[2]/form/div/div[1]/h2",
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
                index++;
            }
            finally
            {
                if(index >= paths.length)
                {
                    bool = false;
                    Log.printLog(className,"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                }

                if(bool)
                    Log.printLog(className, "Nie wczytano Tytuł Header. Zmieniam ścieżkę "+ index);
            }
        }
        Log.printLog(className, "Nie jest wyświetlony nagłówek Menu.");
        return null;
    }

    @Deprecated
    private static boolean isDobryHeaderWyswietlony(WebDriver w, String czescTresciNaglowka, String className)
    {
        String s = getTytulHeader(w,className);
        return s != null && s.contains(czescTresciNaglowka);
    }

}
