package ogame.flota;

import app.GameClient;
import app.czas.CzasLotu;
import com.Log;
import com.Waiter;
import ogame.Header;
import ogame.SciezkaWebElementu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlotaIII
{
    private static SciezkaWebElementu missionsContainer = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[2]/div[2]/ul/li[","]");
    private static SciezkaWebElementu mission = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[2]/div[2]/ul/li[","]/a");

    /**
     * Klika w wybraną misję.
     * @param w driver
     * @param nr Rodzaj misji:
     *           1 - Ekspedycja
     *           2 - Kolonizuj
     *           3 - Recykluj pola
     *           4 - Transportuj
     *           5 - Stacjonuj
     *           6 - Szpieguj
     *           7 - Zatrzymaj
     *           8 - Atakuj
     *           9 - Atak związku
     *           10 - Niszcz
     */
    public static void kliknijMisje(WebDriver w, int nr)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e;
            missionsContainer.setVar(nr);
            e = w.findElement(By.xpath(missionsContainer.toString()));
            if(e.getAttribute("class").contains("on"))
            {
                mission.setVar(nr);
                e = w.findElement(By.xpath(mission.toString()));
                e.click();
                Log.printLog(FlotaIII.class.getName(), "Kliknięto misję nr "+nr);
            }
            else
                Log.printLog(FlotaIII.class.getName(), "Misja niedostępna");
        }
        else
            Log.printLog(FlotaIII.class.getName(), "Nie znaleziono WebElementu - Misja nr " + nr + ".");
    }

    /**
     * Zwraca rozdaj misji w postaci Integer. Wartość domyślna jest 2 - misja kolonizuj.
     * @param missionType
     *      *           1 - Ekspedycja
     *      *           2 - Kolonizuj
     *      *           3 - Recykluj pola
     *      *           4 - Transportuj
     *      *           5 - Stacjonuj
     *      *           6 - Szpieguj
     *      *           7 - Zatrzymaj
     *      *           8 - Atakuj
     *      *           9 - Atak związku
     *      *           10 - Niszcz
     * @return Wartość domyślna jest 2 - misja kolonizuj lub jak powyżej
     */
    public static int intMissionType(String missionType)
    {
        switch (missionType)
        {
            case "Ekspedycja": return 1;
            case "Kolonizuj": return 2;
            case "Recykluj pola": return 3;
            case "Transportuj": return 4;
            case "Stacjonuj": return 5;
            case "Szpieguj": return 6;
            case "Zatrzymaj": return 7;
            case "Atakuj": return 8;
            case "Atak związku": return 9;
            case "Niszcz": return 10;

        }
        return 2;
    }

    /**
     * Klika w button wszystkie suurowce.
     */
    public static void clickWszystkieSurowce(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[3]/div[2]/form/div[1]/div[2]/div[4]/a/img"));
            GameClient.scrollToElement(w, e);
            e.click();
            Log.printLog(FlotaIII.class.getName(),"Klikam wszystkie surowce.");
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - Wszystkie surowce.");
    }

    /**
     * Klika w button wyślij flotę.
     */
    public static void wyslijFlote(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[3]/div[2]/form/div[2]/a[2]/span"));
            GameClient.scrollToElement(w,e);
            e.click();
            Log.printLog(FlotaIII.class.getName(),"Kliknąłem wyślij.");
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - Wyślij flotę.");
    }

    /**
     * Komunikat wyświetla się gdy osiągnieliśmy maksymalną ilość kolonii i wysyłamy na misję kolonizuj. Potwierdza komunikat
     * @param w driver
     * @param className klasa
     */
    public static void potwierdzMisjeKolonizuj(WebDriver w, String className)
    {
        String [] paths = {
                "//*[@id=\"errorBoxDecisionYes\"]",
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
                e.click();
            }
            catch(Exception ex)
            {
                index++;
            }
            finally
            {
                if(index > 5)
                {
                    bool = false;
                    Log.printLog(className,"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                }
                if(bool)
                {
                    Log.printLog(className, "Nie widoczny ErrorBox do misji kolonizuj. Czekam... po raz "+ index);
                    Waiter.sleep(100,100);
                }
            }
        }
        Log.printLog(className, "Nie widoczny ErrorBox do misji kolonizuj.");
    }

    /**
     * Sprawdza czy została wybrana jakaś misja.
     * @param className Nazwa klasy z której jest metoda wywoływana.
     * @return Jeżeli została wybrana misja, to zwróci <b>true</b>.
     */
    public static boolean isMissionSelected(WebDriver w, String className)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e;
            for (int i = 1; i <= 10; i++)
            {
                mission.setVar(i);
                e = w.findElement(By.xpath(mission.toString()));
                if (e.getAttribute("class").contains("selected"))
                {
                    Log.printLog(className,"Wybrano misję nr "+i+".");
                    return true;
                }
            }
        }
        Log.printLog(className,"Nie wybrano żadnej misji.");
        return false;
    }

    public static CzasLotu czasLotu(WebDriver w)
    {
        CzasLotu czasLotu = new CzasLotu();
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[3]/div[2]/form/div[1]/div[1]/ul/li[4]/span"));

            czasLotu.setCzasString(e.getText().split(" ")[0]);
            Log.printLog(FlotaIII.class.getName(),"Pobrałem dane o czasie lotu.");
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - czas lotu.");

        return czasLotu;
    }

    public static CzasLotu przylot(WebDriver w)
    {
        CzasLotu czasLotu = new CzasLotu();
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[3]/div[2]/form/div[1]/div[1]/ul/li[5]/span/span"));

            String [] tmp = e.getText().split(" ");
            czasLotu.setCzasString(tmp[1]);
            czasLotu.setDataString(tmp[0]);
            Log.printLog(FlotaIII.class.getName(),"Pobrałem dane o czasie przylotu.");
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - czas przylotu.");

        return czasLotu;
    }

    public static CzasLotu powrot(WebDriver w)
    {
        CzasLotu czasLotu = new CzasLotu();
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[3]/div/div[4]/div[3]/div[2]/form/div[1]/div[1]/ul/li[6]/span/span"));

            String [] tmp = e.getText().split(" ");
            czasLotu.setCzasString(tmp[1]);
            czasLotu.setDataString(tmp[0]);
            Log.printLog(FlotaIII.class.getName(),"Pobrałem dane o czasie powrotu.");
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - czas powrotu.");

        return czasLotu;
    }

    /**
     * Pobiera dane o wolnej przestrzeni ładunkowej wysyłanej floty.
     */
    public static int wolnaPrzestrzenLadunkowa(WebDriver w)
    {

        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e =  w.findElement(By.xpath("//*[@id=\"remainingresources\"]"));
            String [] tmpTab = e.getText().split("\\.");

            StringBuilder tmpString = new StringBuilder();
            for(String s : tmpTab)
            {
                tmpString.append(s);
            }
            return Integer.valueOf(tmpString.toString());
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - czas powrotu.");

        return -1;
    }

    /**
     * Metoda pobierająca dane o maksymalne przestrzeni ładunkowej wysyłanej floty.
     */
    public static int maksymalnaPrzestrzenLadunkowa(WebDriver w)
    {

        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę III", FlotaIII.class.getName()))
        {
            WebElement e =  w.findElement(By.xpath("//*[@id=\"maxresources\"]"));
            String [] tmpTab = e.getText().split("\\.");

            StringBuilder tmpString = new StringBuilder();
            for(String s : tmpTab)
            {
                tmpString.append(s);
            }
            return Integer.valueOf(tmpString.toString());
        }
        else
            Log.printLog(FlotaIII.class.getName(),"Nie znaleziono WebElementu - czas powrotu.");

        return -1;
    }
}
