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
//    private static final String MAX_ILOSC = "/span/span/span[1]";
    private static final String MAX_ILOSC = "/span/span";
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
//        int i = -1;
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        {
            WebElement e;
            boolean bool = true;
            militaryContainer.setVar(nr);
            while(bool)
            {
                try
                {
//                    Log.printLog1(militaryContainer.toString(),FlotaI.class,101);
                    e = w.findElement(By.xpath(militaryContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
//                        military.setVar(nr);
//                        military.append(MAX_ILOSC);
//                        e = w.findElement(By.xpath(military.toString()));
//                        Log.printLog1(militaryContainer.append(MAX_ILOSC),FlotaI.class,108);
                        e = w.findElement(By.xpath(militaryContainer.append(MAX_ILOSC)));
                        bool = false;
//                        String tmp = e.getAttribute("data-value");
//                        Log.printLog1(tmp,FlotaI.class,111);
                        return Integer.valueOf(e.getAttribute("data-value"));
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

        return -1;
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
//        int i = -1;
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()) && isAnyShips(w))
        {
            WebElement e;
            boolean bool = true;
            civilContainer.setVar(nr);
            while(bool)
            {
                try
                {
//                    Log.printLog1(civilContainer.toString(),FlotaI.class,149);
                    e = w.findElement(By.xpath(civilContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
//                        civil.setVar(nr);
//                        civil.append(MAX_ILOSC);
//                        e = w.findElement(By.xpath(civil.toString()));
                        e = w.findElement(By.xpath(civilContainer.append(MAX_ILOSC)));
                        bool = false;
                       return Integer.valueOf(e.getAttribute("data-value"));
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

        return -1;
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
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;
            boolean bool = true;
            militaryContainer.setVar(nr);
            while(bool)
            {
                try
                {
//                    /html/body/div[5]/div[3]/div[2]/div[1]/div/div[7]/div/form/div/div[1]/ul/li[1]/input
//                    Log.printLog1(militaryContainer.toString(),FlotaI.class,205);
                    e = w.findElement(By.xpath(militaryContainer.toString()));
                    if(e.getAttribute("data-status").contains("on"))
                    {
//                        military.setVar(nr);
//                        militaryContainer.append(PODAJ_ILOSC);
                        e = w.findElement(By.xpath(militaryContainer.append(PODAJ_ILOSC)));
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
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;
            boolean bool = true;
            civilContainer.setVar(nr);
            while(bool)
            {
                try
                {
//                    Log.printLog1(civilContainer.toString());
                    e = w.findElement(By.xpath(civilContainer.toString()));
//                    Log.printLog1(e.toString());
                    if(e.getAttribute("data-status").contains("on"))
                    {
//                        Log.printLog1(civilContainer.toString());
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
                Log.printLog(FlotaI.class.getName(),"Brak floty na planecie.");
            }
            catch(Exception ex)
            {
//                Log.printLog(FlotaI.class.getName(),"Nie wczytano informacji o braku floty.");
                index++;
            }
            finally
            {
                if(index >= paths.length)
                {
//                    Log.printLog(FlotaI.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                    Log.printLog(FlotaI.class.getName(),"Na planecie znajduje się flota.");
                    return bool;
                }
//                else
//                {
//                    Log.printLog(FlotaI.class.getName(),"Nie wczytano informacji o braku floty, zmieniam na scieżkę nr "+ index);
//                }
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

    /**
     * Zwraca ilość aktualnie odbywających się ekspedycji.
     * @param w WebDriver
     * @return Gdy nie pobierze ilość Ekspedycji zwróci -1.
     */
    public static int iloscEkspedycji(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;

            try
            {
                e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[3]/div[1]/div[2]/span"));
                String s = e.getText();
                String tmp = s.split("/")[0].split(":")[1];
                return Integer.valueOf(tmp.substring(tmp.length()-1));
            }
            catch(Exception ex)
            {
                Log.printErrorLog(FlotaI.class.getName(),"Nie wczytano elementu ilość ekskpedcyji.");
            }

        }
        else
        {
            LeftMenu.pressFlota(w, FlotaI.class.getName());
            return -1;
        }
        return -1;
    }

    /**
     * Zwraca maksymalną ilość ekspedycji.
     * @param w WebDriver
     * @return Gdy nie pobierze ilość ekspedycji zwróci -1.
     */
    public static int maxIloscEkspedycji(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;

            try
            {
                e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[3]/div[1]/div[2]/span"));
                String s = e.getText();

                return Integer.valueOf(s.split("/")[1]);
            }
            catch(Exception ex)
            {
                Log.printErrorLog(FlotaI.class.getName(),"Nie wczytano elementu maksymalna ilość ekskpedcyji.");
            }

        }
        else
        {
            LeftMenu.pressFlota(w, FlotaI.class.getName());
            return -1;
        }
        return -1;
    }

    /**
     * Zwraca ilość aktualnie odbywających się misji.
     * @param w WebDriver
     * @return Gdy nie pobierze ilość misji zwróci -1.
     */
    public static int iloscMisji(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;

            try
            {
                e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[3]/div[1]/div[1]/span"));
                String s = e.getText();
                return Integer.valueOf(s.split("/")[0].split(":")[1]);
            }
            catch(Exception ex)
            {
                Log.printErrorLog(FlotaI.class.getName(),"Nie wczytano elementu ilość ekskpedcyji.");
            }
        }
        else
        {
            LeftMenu.pressFlota(w, FlotaI.class.getName());
            return -1;
        }
        return -1;
    }

    /**
     * Zwraca maksymalną ilośc misji własnych.
     * @param w WebDriver
     * @return Gdy nie pobierze ilość misji zwróci -1.
     */
    public static int maxIloscMisji(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Wyślij flotę I", FlotaI.class.getName()))
        {
            WebElement e;

            try
            {
                e = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[1]/div/div[3]/div[1]/div[1]/span"));
                String s = e.getText();

                return Integer.valueOf(s.split("/")[1]);
            }
            catch(Exception ex)
            {
                Log.printErrorLog(FlotaI.class.getName(),"Nie wczytano elementu maksymalna ilość ekskpedcyji.");
            }
        }
        else
        {
            LeftMenu.pressFlota(w, FlotaI.class.getName());
            return -1;
        }
        return -1;
    }



    @Deprecated
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
