package ogame.flota;

import app.GameClient;
import com.Log;
import ogame.Header;
import ogame.LeftMenu;
import ogame.SciezkaWebElementu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlotaI {

    private static SciezkaWebElementu military = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/div/form/div/div[1]/ul/li[",
            "]/span");
    private static SciezkaWebElementu militaryContainer = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/div/form/div/div[1]/ul/li[",
            "]");
    private static SciezkaWebElementu civil = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/div/form/div/div[2]/ul/li[",
            "]/span");
    private static SciezkaWebElementu civilContainer = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/div/form/div/div[2]/ul/li[",
            "]");
    private static final String MAX_ILOSC = "/span/span/span[1]";
    private static final String PODAJ_ILOSC = "/input";



    /**
     * Wybiera wszystkie dostępne statki wybranego rodzaju.
     * @param w driver
     * @param nr Rodzaj statku:
     *           1 - Lekki myśliwiec
     *           2 - Ciężki myśliwiec
     *           3 - Krążownik
     *           4 - Okręt wojenny
     *           5 - Pancernik
     *           6 - Bombowiec
     *           7 - Niszczyciel
     *           8 - Gwiazda śnierci
     *           9 - Rozpruwacz
     *           10 - Pionier
     */
    public static void kliknijStatekBojowy(WebDriver w, int nr)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        {
            WebElement e;
            boolean bool = true;
            militaryContainer.setVar(nr);
            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(militaryContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
                        military.setVar(nr);
                        e = w.findElement(By.xpath(military.toString()));
                        bool = false;
                        e.click();
                    }
                    else
                        Log.printLog(FlotaI.class.getName(),"Brak statków na planecie.");
                }
                catch(Exception ex)
                {
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano statku.");
                }
            }
        }
        else
            LeftMenu.pressFlota(w, FlotaI.class.getName());
    }

    /**
     * Wybiera wszystkie dostępne statki wybranego rodzaju.
     * @param w driver
     * @param nr Rodzaj statku:
     *           1 - Lekki myśliwiec
     *           2 - Ciężki myśliwiec
     *           3 - Krążownik
     *           4 - Okręt wojenny
     *           5 - Pancernik
     *           6 - Bombowiec
     *           7 - Niszczyciel
     *           8 - Gwiazda śnierci
     *           9 - Rozpruwacz
     *           10 - Pionier
     */
    public static int dostepnaIloscStatekBojowy(WebDriver w, int nr)
    {
        int i = -1;
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        {
            WebElement e;
            boolean bool = true;
            militaryContainer.setVar(nr);
            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(militaryContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
                        military.setVar(nr);
                        military.append(MAX_ILOSC);
                        e = w.findElement(By.xpath(military.toString()));
                        bool = false;
                        i = Integer.valueOf(e.getText());
                    }
                    else
                        Log.printLog(FlotaI.class.getName(),"Brak statków na planecie.");
                }
                catch(Exception ex)
                {
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano statku.");
                }
            }
        }
        else
            LeftMenu.pressFlota(w, FlotaI.class.getName());

        return i;
    }

    /**
     * Wybiera wszystkie dostępne statki wybranego rodzaju.
     * @param w driver
     * @param nr Rodzaj statku:
     *           1 - Mały transporter
     *           2 - Duży transporter
     *           3 - Statek kolonizacyjny
     *           4 - Recykler
     *           5 - Sonda szpiegowska
     */
    public static int dostepnaIloscStatekCywilny(WebDriver w, int nr)
    {
        int i = -1;
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        {
            WebElement e;
            boolean bool = true;
            civilContainer.setVar(nr);
            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(civilContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
                        civil.setVar(nr);
                        civil.append(MAX_ILOSC);
                        e = w.findElement(By.xpath(civil.toString()));
                        bool = false;
                        i = Integer.valueOf(e.getText());
                    }
                    else
                        Log.printLog(FlotaI.class.getName(),"Brak statków na planecie.");
                }
                catch(Exception ex)
                {
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano statku.");
                }
            }
        }
        else
            LeftMenu.pressFlota(w, FlotaI.class.getName());

        return i;
    }

    /**
     * Wybiera wszystkie dostępne statki wybranego rodzaju.
     * @param w driver
     * @param nr Rodzaj statku:
     *           1 - Lekki myśliwiec
     *           2 - Ciężki myśliwiec
     *           3 - Krążownik
     *           4 - Okręt wojenny
     *           5 - Pancernik
     *           6 - Bombowiec
     *           7 - Niszczyciel
     *           8 - Gwiazda śnierci
     *           9 - Rozpruwacz
     *           10 - Pionier
     */
    public static void wprowadzIloscStatekBojowy(WebDriver w, int nr, int ilosc)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;
            boolean bool = true;
            militaryContainer.setVar(nr);
            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(militaryContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
                        military.setVar(nr);
                        military.append(PODAJ_ILOSC);
                        e = w.findElement(By.xpath(military.toString()));
                        bool = false;
                        e.sendKeys(String.valueOf(ilosc));
                    }
                    else
                        Log.printLog(FlotaI.class.getName(),"Brak statków na planecie.");
                }
                catch(Exception ex)
                {
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano statku.");
                }
            }
        }
        else
            LeftMenu.pressFlota(w, FlotaI.class.getName());
    }

    /**
     * Wybiera wszystkie dostępne statki wybranego rodzaju.
     * @param w driver
     * @param nr Rodzaj statku:
     *           1 - Mały transporter
     *           2 - Duży transporter
     *           3 - Statek kolonizacyjny
     *           4 - Recykler
     *           5 - Sonda szpiegowska
     */
    public static void wprowadzIloscStatekCywilny(WebDriver w, int nr, int ilosc)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;
            boolean bool = true;
            civilContainer.setVar(nr);
            while(bool)
            {
                try
                {
                    Log.printLog1(civilContainer.toString());
                    e = w.findElement(By.xpath(civilContainer.toString()));
                    Log.printLog1(e.toString());
                    if(e.getAttribute("data-status").contains("on"))
                    {
                        Log.printLog1(civilContainer.toString());
                        e = w.findElement(By.xpath(civilContainer.append(PODAJ_ILOSC)));
                        bool = false;
                        e.sendKeys(String.valueOf(ilosc));
                    }
                    else
                        Log.printLog(FlotaI.class.getName(),"Brak statków na planecie.");
                }
                catch(Exception ex)
                {
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano statku.");
                }
            }
        }
        else
            LeftMenu.pressFlota(w, FlotaI.class.getName());
    }

    /**
     * Wybiera wszystkie dostępne statki wybranego rodzaju.
     * @param w driver
     * @param nr Rodzaj statku:
     *           1 - Mały transporter
     *           2 - Duży transporter
     *           3 - Statek kolonizacyjny
     *           4 - Recykler
     *           5 - Satelita słoneczny
     * @return <b>true</b> - flota została wysłana
     *         <b>false</b> - flota nie została wysłana lub brak floty na planecie
     */
    public static boolean kliknijStatekCywilny(WebDriver w, int nr)
    {
//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;
            civilContainer.setVar(nr);

            try
            {
                e = w.findElement(By.xpath(civilContainer.toString()));
                if(e.getAttribute("data-status").contains("on"))
                {
                    civil.setVar(nr);
                    e = w.findElement(By.xpath(civil.toString()));
                    e.click();
                    return true;
                }
                else
                {
                    Log.printLog(FlotaI.class.getName(),"Brak statków na planecie.");
                    return false;
                }
            }
            catch(Exception ex)
            {
                Log.printLog(FlotaI.class.getName(),"Nie wczytano statku.");
                return false;
            }

        }
        else
        {
            LeftMenu.pressFlota(w, FlotaI.class.getName());
            return false;
        }
    }
    /**
     * Wybiera wszystkie statki na planecie.
     * @param w driver
     */
    public static void chooseAllShips(WebDriver w)
    {

        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I",FlotaI.class.getName()) && isAnyShips(w))
        {
            WebElement e = w.findElement(By.xpath("//*[@id=\"sendall\"]"));
            GameClient.scrollToElement(w,e);
            e.click();
            Log.printLog(FlotaI.class.getName(),"Klikam w button: Wybieram wszystkie statki.");
            return;
        }
        Log.printLog(FlotaI.class.getName(),"Nie znaleziono WebElementu - Wszystkie statki.");
    }

    /**
     * Sprawdza czy na planecie znajdują się statki.
     * @param w driver
     * @return False - brak statków na planecie, True - są statki na planecie.
     */
    public static boolean isAnyShips(WebDriver w)
    {
        String [] paths = {"/html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/p/span",
                " /html/body/div[5]/div[3]/div[2]/div[1]/div/div[6]/p"
        };


        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
//                WebElement e = w.findElement(By.xpath(paths[index]));
                w.findElement(By.xpath(paths[index]));
                bool = false;
            }
            catch(Exception ex)
            {
                Log.printLog(FlotaI.class.getName(),"Nie wczytano informacji o braku floty.");
                index++;
            }
            finally
            {
                if(index >= paths.length)
                {
                    Log.printLog(FlotaI.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                    return bool;
                }
                else
                {
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano informacji o braku floty, zmieniam na scieżkę nr "+ index);
                }
            }
        }
        return bool;
    }

    /**
     * Klika przycisk przechodzenia do nastepnej zakładki Flota II.
     * @param w driver
     */
    public static void clickContinue(WebDriver w)
    {

//        if(isDobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        {
            String [] paths = {" /html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/div/div[2]/div/a/span",
                    "/html/body/div[5]/div[3]/div[2]/div[1]/div/div[8]/div/div[2]/div/a/span"
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
                    Log.printLog(FlotaI.class.getName(),"Nie wczytano przycisku Dalej.....");
                    index++;
                }
                finally
                {
                    if(index >= paths.length)
                    {
                        Log.printLog(FlotaI.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                    }

                    else
                    {
                        Log.printLog(FlotaI.class.getName(),"Zmieniam ścieżkę "+ index);
                    }
                }
            }
        }
        else
        {
            LeftMenu.pressFlota(w, FlotaI.class.getName());
        }
    }

    private static String getTytulHeader(WebDriver w, String className)
    {
        String [] paths = {
                "/html/body/div[5]/div[3]/div[2]/div[1]/div/div[2]/h2",
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
