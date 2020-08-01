package ogame.ruchflot;

import app.GameClient;
import app.OgameWeb;
import app.czas.CzasGry;
import app.czas.CzasLotu;
import app.ruchflot.Lot;
import com.Log;
import com.Waiter;
import ogame.Header;
import ogame.LeftMenu;
import ogame.SciezkaWebElementu;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class RuchFlot
{

    private static final SciezkaWebElementu LISTA_AKTUALNYCH_LOTOW = new SciezkaWebElementu("//*[@id=\"inhalt\"]/div[",
            "]");
    /**
     * Pobiera nazwę misji.
     * @param w driver
     * @param nr Numer kolejny lotu. Zaczynając od 4.
     * @return Nazwa misji floty. w przypadku błędu zwróci pusty String.
     */
    private static String misjaFloty(WebDriver w, int nr, String className)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            LISTA_AKTUALNYCH_LOTOW.setVar(nr);
            try
            {
                WebElement e =  w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.toString()));
                return Misje.getMission(Integer.valueOf(e.getAttribute("data-mission-type"))).getName();
            }
            catch (Exception e)
            {
                Log.printLog(RuchFlot.class.getName(),"Brak misji floty.");
            }
            return "";
        }
        else
        {
            LeftMenu.pressRuchFlot(w,className);
            return "";
        }
    }

    /**
     * Klika w zawróć misję.
     * @param w WebDriver
     * @param i Nr kolejny misji na liście.
     * @return Jeżeli zostało kliknięte, to zwróci <b>true</b>.
     */
    public static boolean zawroc(WebDriver w, int i)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            LISTA_AKTUALNYCH_LOTOW.setVar(i);
            try
            {
                WebElement e =  w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.append("/span[9]/a")));
                GameClient.scrollToElement(w,e);
                e.click();
                return true;
            }
            catch (Exception e)
            {
                Log.printLog(RuchFlot.class.getName(),"Brak misji floty.");
            }
            return false;
        }
        else
        {
            LeftMenu.pressRuchFlot(w,RuchFlot.class.getName());
            return false;
        }
    }

//    /**
//     * Sprawdza czy jest dostępny button zawróc na misji floty.
//     * @return Jeżeli flota wraca - false, jeżeli leci od celu to true.
//     */
//    private static boolean zawrocDostepne(WebDriver w, int nr, Class c)
//    {
//        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
//        {
//            LISTA_AKTUALNYCH_LOTOW.setVar(nr);
//            try
//            {
//                WebElement e =  w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.toString()));
//                return !e.getAttribute("data-return-flight").equals("1");
//            }
//            catch (Exception e)
//            {
//                Log.printLog(RuchFlot.class.getName(),"Brak misji floty.");
//            }
//            return false;
//        }
//        else
//        {
//            LeftMenu.pressRuchFlot(w,c.getName());
//            return false;
//        }
//    }

    /**
     * Sprawdza czy jest dostępny button zawróc na misji floty.
     * @return Jeżeli flota wraca - false, jeżeli leci od celu to true.
     */
    private static boolean flotaWraca(WebDriver w, int nr, Class c)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            LISTA_AKTUALNYCH_LOTOW.setVar(nr);
            try
            {
                WebElement e =  w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.toString()));
                return e.getAttribute("data-return-flight").equals("1");
            }
            catch (Exception e)
            {
                Log.printLog(RuchFlot.class.getName(),"Brak misji floty.");
            }
            return false;
        }
        else
        {
            LeftMenu.pressRuchFlot(w,c.getName());
            return false;
        }
    }

    private static boolean kliknijOdswiez(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            try
            {
                WebElement e =  w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[3]/span[1]/a"));
                GameClient.scrollToElement(w,e);
                e.click();
                return true;
            }
            catch (Exception e)
            {
                Log.printErrorLog(RuchFlot.class.getName(),"Wystąpił błąd podczas próby kliknięcia odśwież.");
            }
            return false;
        }
        else
        {
            LeftMenu.pressRuchFlot(w,RuchFlot.class.getName());
            return false;
        }
    }

    /**
     * Pobiera czas dotarcia floty do celu.
     * @param nr Kolejny lot w zakładce Ruch flot. Pierwszy lot to nr 4.
     */
    private static CzasLotu czasUCelu(WebDriver w, Class c, int nr)
    {
        CzasLotu czasLotu = new CzasLotu();

        try
        {
//            if(zawrocDostepne(w,nr,c))
//            {
                LISTA_AKTUALNYCH_LOTOW.setVar(nr);
                WebElement e = w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.append("/span[1]")));
                //Sprawdzenie czy będzie wyświetlona data i czas w atrybucie
                while(e.getAttribute("class").contains("tpd"))
                {
                    kliknijOdswiez(w);
                    Waiter.sleep(25,100);
//                    Log.printLog1("????",RuchFlot.class,674);
                }
                //Pobieranie danych czasowych
                String s = w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.append("/span[1]"))).getAttribute("title");
                String [] tab = s.split(" ");
//                czasLotu.setCzasString(s);
                //Sprawdzenie czy zwrócony atrybut posiada datę i czas.
                if(tab.length <= 1)
                {
                    if(tab[0].contains("."))
                        czasLotu.setDataString(tab[0]);
                    else
                        czasLotu.setCzasString(tab[0]);
                }
                else
                {
                    czasLotu.setDataString(tab[0]);
                    czasLotu.setCzasString(tab[1]);
                }
                return czasLotu;
//            }
        }
        catch (Exception ex)
        {
            Log.printErrorLog(RuchFlot.class.getName(), "Nie wczytano z misji czasu u celu.");
            ex.printStackTrace();
        }
        return czasLotu;
    }

    /**
     * Pobiera współrzędne startu floty.
     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
     */
    private static String start(WebDriver w, int i)
    {
        LISTA_AKTUALNYCH_LOTOW.setVar(i);
        try
        {
            WebElement e = w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.append("/span[5]/span[1]/a")));
            return e.getText();
        }
        catch (Exception ex)
        {
            Log.printErrorLog(RuchFlotZdarzenia.class.getName(),"-> start()");
        }
        return "[0:0:0]";
    }

    /**
     * Pobiera dane czy flota wystartowała z księżyca.
     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
     */
    private static boolean startZKsiezyca(WebDriver w, int i)
    {
        LISTA_AKTUALNYCH_LOTOW.setVar(i);
        try
        {
            WebElement e = w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.append("/span[5]/span[2]/figure")));

            return e.getAttribute("class").contains("moon");
        }
        catch (Exception e)
        {
            Log.printErrorLog(RuchFlot.class.getName(),"-> startZKsiezyca()");
        }

        return false;
    }

    /**
     * Pobiera dane czy flota leci na księżyc.
     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
     */
    private static boolean lotNaKsiezyc(WebDriver w, int i)
    {
        LISTA_AKTUALNYCH_LOTOW.setVar(i);
        try
        {
            WebElement e = w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.append("/span[11]/span[1]/span/figure")));

            return e.getAttribute("class").contains("moon");
        }
        catch (Exception e)
        {
            Log.printErrorLog(RuchFlot.class.getName(),"-> lotNaKsiezyc()");
        }

        return false;
    }

    /**
     * Pobiera współrzędne celu lotu floty.
     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
     */
    private static String cel(WebDriver w, int i)
    {
        LISTA_AKTUALNYCH_LOTOW.setVar(i);

        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            String [] paths = {
                    LISTA_AKTUALNYCH_LOTOW.append("/span[11]/span[2]/a"),
                    LISTA_AKTUALNYCH_LOTOW.append("/span[10]/span[2]/a")
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
                    return e.getText();
                }
                catch(Exception ex)
                {
                    Log.printErrorLog(RuchFlot.class.getName(),"Nie wczytano współrzędnych celu.");
                }
                finally
                {
                    index++;
                    if(index >= paths.length)
                    {
                        bool = false;
                        Log.printLog(RuchFlot.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                    }
                }
            }
        }
        else
        {
            LeftMenu.pressRuchFlot(w,RuchFlot.class.getName());
        }

        return "[0:0:0]";
    }

    /**
     * Pobiera dane o aktualnej ilości wykorzystanych slotów lotów flot.
     */
    private static int iloscLotow(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            String s;
            try
            {

                s = w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[3]/span[2]/span[1]")).getText();
                return Integer.valueOf(s);
            }
            catch (Exception e)
            {
                Log.printErrorLog(RuchFlot.class.getName(),"iloscLotow() - Nie wczytano elementu z ilością lotów.");
            }

        }
        return -1;
    }

    /**
     * Pobiera dane o maksymalnej ilosci slotów lotów flot.
     */
    public static int maxIloscLotow(WebDriver w, String className)
    {

        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            String s;
            try
            {

                s = w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[3]/span[2]/span[2]")).getText();
                return Integer.valueOf(s);
            }
            catch (Exception e)
            {
                Log.printErrorLog(RuchFlot.class.getName(),"iloscLotow() - Nie wczytano elementu z max ilością lotów.");
            }
        }
        return -1;
    }

    /**
     * Pobiera czas powrotu floty na planetę lub księżyc.
     * @param nr Kolejny lot w zakładce Ruch flot. Pierwszy lot to nr 4.
     */
    private static CzasLotu czasPowrotu(WebDriver w, Class c, int nr)
    {
        CzasLotu czasLotu = new CzasLotu();

        try
        {
//            if(zawrocDostepne(w,nr,c))
//            {
                LISTA_AKTUALNYCH_LOTOW.setVar(nr);
                WebElement e = w.findElement(By.xpath(LISTA_AKTUALNYCH_LOTOW.toString()));
                boolean tmp = true;
                //Flaga do wykonania pętli w ktorej wyszukiwany jest WebElement, a następnie sprawdzany czy
                //posiada czas i date, jeżeli nie to odświeżana jest strona i WebElement jest ponownie załadowany
                //i wyszukiwany.
                while(tmp)
                {
//                    Log.printLog1("????",RuchFlot.class,882);
                    for(WebElement e2 : e.findElements(By.tagName("span")))
                    {
                        if(e2.getAttribute("class").contains("nextTimer"))
                        {
                            e = e2;
                            break;
                        }
                    }
                    //Sprawdzenie czy będzie wyświetlona data i czas w atrybucie
                    while(e.getAttribute("class").contains("tpd"))
                    {
                        kliknijOdswiez(w);
                        Waiter.sleep(25,100);
                        Log.printLog1("????",RuchFlot.class,892);
                    }
                    tmp = false;
                }

                //Pobieranie danych czasowych
                String s = e.getAttribute("title");
                String [] tab = s.split(" ");
                czasLotu.setCzasString(s);
                //Sprawdzenie czy zwrócony atrybut posiada datę i czas.
                if(tab.length <= 1)
                {
                    if(tab[0].contains("."))
                        czasLotu.setDataString(tab[0]);
                    else
                        czasLotu.setCzasString(tab[0]);
                }
                else
                {
                    czasLotu.setDataString(tab[0]);
                    czasLotu.setCzasString(tab[1]);
                }
                return czasLotu;
//            }
//            else
//                Log.printLog("Nie wczytano czasu powrotu. Zawróc");
        }
        catch (Exception ex)
        {
            Log.printErrorLog(RuchFlot.class.getName(), "Nie wczytano z misji czasu u celu.");
            ex.printStackTrace();
        }
        return czasLotu;
    }


    public static String id(WebDriver w, int i)
    {
        String id = "";
        if(Header.dobryHeaderWyswietlony(w,"Ruch floty",RuchFlot.class.getName()))
        {
            LISTA_AKTUALNYCH_LOTOW.setVar(i);
            try
            {
                id = w.findElement(By.xpath( LISTA_AKTUALNYCH_LOTOW.toString())).getAttribute("id");
            }
            catch (Exception ex)
            {
                Log.printErrorLog(RuchFlot.class.getName(),"Nie wczytano id lotu.");
            }
        }
        return id;
    }

    /**
     * Pobiera wszystkie dane o lotach do Listy.
     */
    public static List<Lot> daneLotow (WebDriver w)
    {
        List<Lot> loty = new ArrayList<>();
        int a = iloscLotow(w);

        for(int i = 4; i < 4+a; i++)
        {
            int tmp = iloscLotow(w);
            if(tmp == a)
            {
                Log.printLog(RuchFlot.class.getName(),"Rozpoczynam pobieranie danych o locie nr "+ (i-3));
                Lot l = new Lot();
//                String s = misjaFloty(w,i, RuchFlot.class.getName());
                //lp
                l.setLp(i);
                // id
                l.setId(id(w,i));
                // czy flota wraca
                boolean flotaWraca = flotaWraca(w,i,RuchFlot.class);
                l.setWraca(flotaWraca);

                // wysłana z księzyca
                l.setzKsiezyca(startZKsiezyca(w,i));

                // leci na ksieżyc

                l.setNaKsiezyc(lotNaKsiezyc(w,i));
                //Rodzaj
                l.setRodzaj(misjaFloty(w,i, RuchFlot.class.getName()));

                // ustawienie wspołrzędnych startu i celu
                l.setStart(start(w,i));
                l.setCel(cel(w,i));

                // ustawienie czasów dotarcia do celu i powrotu
                if(flotaWraca)
                    l.setCzasPowrot(czasUCelu(w, RuchFlot.class,i));
                else
                {
                    l.setCzasPowrot(czasPowrotu(w, RuchFlot.class,i));
                    l.setCzasUCelu(czasUCelu(w, RuchFlot.class,i));
                }

                loty.add(l);
                Log.printLog(RuchFlot.class.getName(),"Zakończono  pobieranie danych o locie nr "+ (i-3) +
                        ". Dodano do listy lotów.");
            }
            else
            {
                Log.printLog(RuchFlot.class.getName(),"Zmieniona ilośc lotów. Początkowa ilość - "+a+". Aktualna ilość" +
                        " " + tmp+". Przerywam wykoanie pobierania danych.");
                break;
            }
        }
        return loty;
    }
}
/*
Wersja 1
 */
//public class RuchFlot
//{
//    private static SciezkaWebElementu lotBox = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]");
//    private static SciezkaWebElementu misja = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[3]");
//    // czas powrotu
//    private static SciezkaWebElementu czas1 = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[2]");
//    // czas u celu
//    private static SciezkaWebElementu czas2 = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[13]");
//    private static SciezkaWebElementu czas3Espedycja = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[12]");
//    //współrzędne startu
//    private static SciezkaWebElementu startWspolrzedne = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[5]/span[1]/a");
//    // ścieżka do obiektu obok współrzędnych - czy start z planety lub ksiezyca
//    private static SciezkaWebElementu startObiekt = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[5]/span[2]");
////    private static SciezkaWebElementu startObiekt = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[5]/span[2]/span");
////
//    // ścieżka do obiektu obok współrzędnych - czy leci na planetę lub ksiezyca
//    private static SciezkaWebElementu celObiekt = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[11]/span[1]/span");
//    // gdy zawraca
//    private static SciezkaWebElementu celWspolrzedne = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[10]/span[2]/a");
//    // gdy leci do celu
//    private static SciezkaWebElementu celWspolrzedne2 = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[11]/span[2]/a");
//    private static SciezkaWebElementu zawroc = new SciezkaWebElementu("/html/body/div[5]/div[3]/div[2]/div/div/div[","]/span[9]/a/img");
//
//    /**
//     * Pobiera misję floty.
//     * @param w driver
//     * @param nr lot
//     * @return Misja floty.
//     */
//    private static String misjaFloty(WebDriver w, int nr, String className)
//    {
//        if(isDobryHeaderWyswietlony(w, className))
//        {
//            misja.setVar(nr);
//            try
//            {
//                WebElement e =  w.findElement(By.xpath(misja.toString()));
//                return e.getText();
//            }
//            catch (Exception e)
//            {
//                Log.printLog(RuchFlot.class.getName(),"Brak misji floty.");
//            }
//            return "";
//        }
//        else
//        {
//            LeftMenu.pressRuchFlot(w,className);
//            return "";
//        }
//    }
//
//    /**
//     * Klika w zawróć misję.
//     * @param w WebDriver
//     * @param i Nr kolejny misji na liście.
//     * @return Jeżeli zostało kliknięte, to zwróci <b>true</b>.
//     */
//    public static boolean zawroc(WebDriver w, int i)
//    {
//        if(isDobryHeaderWyswietlony(w, RuchFlot.class.getName()))
//        {
//            zawroc.setVar(i);
//            try
//            {
//                WebElement e =  w.findElement(By.xpath(zawroc.toString()));
//                GameClient.scrollToElement(OgameWeb.webDriver,e);
//                e.click();
//                return true;
//            }
//            catch (Exception e)
//            {
//                Log.printLog(RuchFlot.class.getName(),"Brak misji floty.");
//            }
//            return false;
//        }
//        else
//        {
//            LeftMenu.pressRuchFlot(w,RuchFlot.class.getName());
//            return false;
//        }
//    }
//
//    /**
//     * Jeżeli w misji jest (R) to flota wraca do miejsca startu. Jeżeli nie to flota leci do celu.
//     * @param s misja floty;
//     * @return Jeżeli flota wraca - false, jeżeli leci od celu to true.
//     */
//    private static boolean zawrocDostepne(String s)
//    {
//        return s != null && !s.contains("(R)");
//    }
//
//
//
//    /**
//     * Pobiera czas dotarcia floty do celu.
//     * @param nrLotu Kolejny lot w zakładce Ruch flot. Pierwszy lot to nr 4.
//     */
//    private static CzasLotu czasUCelu(WebDriver w, String className, int nrLotu)
//    {
//        CzasLotu czasLotu = new CzasLotu();
//        try
//        {
//            if(zawrocDostepne(Objects.requireNonNull(misjaFloty(w, nrLotu, className))))
//            {
//                czas1.setVar(nrLotu);
//                String s = w.findElement(By.xpath(czas1.toString())).getText();
//                czasLotu.setCzasString(s);
//
//                if(CzasGry.getCzas().czasWSekundach() > czasLotu.getCzas().czasWSekundach())
//                    czasLotu.setData(CzasGry.getData().getTommorowDate());
//                else
//                    czasLotu.setDataString(CzasGry.getData().toString());
//
//                return czasLotu;
//            }
//        }
//        catch (Exception ex)
//        {
//            Log.printErrorLog(RuchFlot.class.getName(), "Nie wczytano z misji czasu u celu.");
//            ex.printStackTrace();
//        }
//        return czasLotu;
//    }
//
//    /**
//     * Pobiera współrzędne startu floty.
//     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
//     */
//    private static String start(WebDriver w, int i)
//    {
//        startWspolrzedne.setVar(i);
//        WebElement e;
//        try
//        {
//            e = w.findElement(By.xpath(startWspolrzedne.toString()));
//            return e.getText();
//        }
//        catch (Exception ex)
//        {
//            Log.printErrorLog(RuchFlotZdarzenia.class.getName(),"-> start()");
//        }
//        return null;
//    }
//
//    /**
//     * Pobiera dane czy flota wystartowała z księżyca.
//     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
//     */
//    private static boolean startZKsiezyca(WebDriver w, int i)
//    {
//        startObiekt.setVar(i);
//        try
//        {
//            WebElement e = w.findElement(By.xpath(startObiekt.toString()));
//            String s = e.getText();
//            return s != null && s.contains("Księżyc");
//        }
//        catch (Exception e)
//        {
//            Log.printErrorLog(RuchFlot.class.getName(),"-> startZKsiezyca()");
//        }
//
//        return false;
//    }
//
//    /**
//     * Pobiera dane czy flota leci na księżyc.
//     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
//     */
//    private static boolean lotNaKsiezyc(WebDriver w, int i)
//    {
//        celObiekt.setVar(i);
//        try
//        {
//            WebElement e = w.findElement(By.xpath(celObiekt.toString()));
//            String s = e.getText();
//            return s != null && s.contains("Księżyc");
//        }
//        catch (Exception e)
//        {
//            Log.printErrorLog(RuchFlotZdarzenia.class.getName(),"-> startZKsiezyca()");
//        }
//
//        return false;
//    }
//
//    /**
//     * Pobiera współrzędne celu lotu floty.
//     * @param i Nr lotu z zakładki Ruchu flot. Pierwszy lot to nr 4.
//     */
//    private static String cel(WebDriver w, int i)
//    {
//        String s;
//
//        if(zawrocDostepne(misjaFloty(w, i, RuchFlot.class.getName())))
//        {
//            celWspolrzedne2.setVar(i);
//            celWspolrzedne.setVar(i);
//
//            String [] paths = {
//                   celWspolrzedne2.toString(),
//                    celWspolrzedne.toString()
//            };
//
//            WebElement e;
//            int index = 0;
//            boolean bool = true;
//
//            while(bool)
//            {
//                try
//                {
//                    e = w.findElement(By.xpath(paths[index]));
//                    bool = false;
//                    return e.getText();
//                }
//                catch(Exception ex)
//                {
//                    Log.printErrorLog(RuchFlot.class.getName(),"Nie wczytano współrzędnych celu.");
//                }
//                finally
//                {
//                    index++;
//                    if(index >= paths.length)
//                    {
//                        bool = false;
//                        Log.printLog(RuchFlot.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
//                    }
//
//                    if(bool)
//                        Log.printLog(RuchFlot.class.getName(), "Nie wczytano współrzędnych celu. Zmieniam ścieżkę "+ index);
//                }
//            }
//        }
//        else
//        {
//
//            try
//            {
//                celWspolrzedne.setVar(i);
//                s = w.findElement(By.xpath(celWspolrzedne.toString())).getText();
//                return s;
//            }
//            catch(Exception ex)
//            {
//                Log.printErrorLog(RuchFlot.class.getName(),"Nie wczytano współrzędnych celu.");
//                return null;
//            }
//        }
//
//        return null;
//    }
//
//    /**
//     * Pobiera dane o aktualnej ilości wykorzystanych slotów lotów flot.
//     */
//    private static int iloscLotow(WebDriver w, String className)
//    {
//        if(isDobryHeaderWyswietlony(w, className))
//        {
//            String s;
//            try
//            {
//                s = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div/div/div[3]/span[2]/span[1]")).getText();
//            }
//            catch (Exception e)
//            {
//                Log.printErrorLog(RuchFlot.class.getName(),"iloscLotow() - Nie wczytano elementu z ilością lotów.");
//                return  0;
//            }
//
//            return Integer.valueOf(s);
//        }
//        return 0;
//    }
//
//    /**
//     * Pobiera dane o maksymalnej ilosci slotów lotów flot.
//     */
//    public static int maxIloscLotow(WebDriver w, String className)
//    {
//        if(isDobryHeaderWyswietlony(w, className))
//        {
//            String s = w.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div/div/div[3]/span[2]/span[2]")).getText();
//            return Integer.valueOf(s);
//        }
//        return 0;
//    }
//
//    /**
//     * Pobiera czas powrotu floty na planetę lub księżyc.
//     * @param nrLotu Kolejny lot w zakładce Ruch flot. Pierwszy lot to nr 4.
//     */
//    private static CzasLotu czasPowrotu(WebDriver w, String className, int nrLotu)
//    {
//        CzasLotu czasLotu = new CzasLotu();
//        if(zawrocDostepne(Objects.requireNonNull(misjaFloty(w, nrLotu, className))))
//        {
//            czas2.setVar(nrLotu);
//            czas3Espedycja.setVar(nrLotu);
//
//            String [] paths = {
//                    czas2.toString(),
//                    czas3Espedycja.toString()
//            };
//
//            WebElement e;
//            int index = 0;
//            boolean bool = true;
//
//            while(bool)
//            {
//                try
//                {
//                    e = w.findElement(By.xpath(paths[index]));
//                    if(e.getAttribute("class").equals("nextabsTime"))
//                    {
//                        bool = false;
//                        czasLotu.setCzasString(e.getText());
//                    }
//                }
//                catch(Exception ex)
//                {
//                    Log.printErrorLog(RuchFlot.class.getName(), "Nie wczytano czasu powrotu misji.");
//                }
//                finally
//                {
//                    index++;
//                    if(index >= paths.length)
//                    {
//                        bool = false;
//                        Log.printLog(RuchFlot.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
//                    }
//
//                    if(bool)
//                        Log.printLog(RuchFlot.class.getName(), "Nie wczytano czasu powrotu. Zmieniam ścieżkę "+ index);
//                }
//            }
//        }
//        else
//        {
//            czas1.setVar(nrLotu);
//            try
//            {
//                String s = w.findElement(By.xpath(czas1.toString())).getText();
//                czasLotu.setCzasString(s);
//            }
//            catch (Exception ec)
//            {
//                Log.printErrorLog(RuchFlot.class.getName(),"Nie wczytano czasu powrotu floty.");
//            }
//
//        }
//
//        if(CzasGry.getCzas().czasWSekundach() > czasLotu.getCzas().czasWSekundach())
//            czasLotu.setData(CzasGry.getData().getTommorowDate());
//        else
//            czasLotu.setDataString(CzasGry.getData().toString());
//
//        return czasLotu;
//    }
//
//    /**
//     * Samoopisująca.
//     */
//    private static String getTytulHeader(WebDriver w, String className)
//    {
//        String [] paths = {
//                "/html/body/div[5]/div[3]/div[2]/div/div/header/h2",
//        };
//
//        WebElement e;
//        int index = 0;
//        boolean bool = true;
//
//        while(bool)
//        {
//            try
//            {
//                e = w.findElement(By.xpath(paths[index]));
//                bool = false;
//                return  e.getText();
//            }
//            catch(Exception ex)
//            {
//                index++;
//            }
//            finally
//            {
//                if(index >= paths.length)
//                {
//                    bool = false;
//                    Log.printLog(className,"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
//                }
//
//                if(bool)
//                    Log.printLog(className, "Nie wczytano Tytuł Header. Zmieniam ścieżkę "+ index);
//            }
//        }
//        Log.printLog(className, "Nie jest wyświetlony nagłówek Menu.");
//        return null;
//    }
//    public static String id(WebDriver w, int i)
//    {
//        String id = "";
//        if(isDobryHeaderWyswietlony(w, RuchFlot.class.getName()))
//        {
//            lotBox.setVar(i);
//
//            try
//            {
//                id = w.findElement(By.xpath(lotBox.toString())).getAttribute("id");
//            }
//            catch (Exception ex)
//            {
//                Log.printErrorLog(RuchFlot.class.getName(),"Nie wczytano id lotu.");
//            }
//        }
//        return id;
//    }
//
//    /**
//     * Samoopisująca.
//     */
//    private static boolean isDobryHeaderWyswietlony(WebDriver w, String className)
//    {
//        String s = getTytulHeader(w,className);
//        return s != null && s.contains("Ruch floty");
//    }
//
//    /**
//     * Pobiera wszystkie dane o lotach do Listy.
//     */
//    public static List<Lot> daneLotow (WebDriver w)
//    {
//        List<Lot> loty = new ArrayList<>();
//        int a = iloscLotow(w, RuchFlot.class.getName());
//
//        for(int i = 4; i < 4+a; i++)
//        {
//            int tmp = iloscLotow(w, RuchFlot.class.getName());
//            if(tmp == a)
//            {
//                Log.printLog(RuchFlot.class.getName(),"Rozpoczynam pobieranie danych o locie nr "+ (i-3));
//                Lot l = new Lot();
//                String s = misjaFloty(w,i, RuchFlot.class.getName());
//                //lp
//                l.setLp(i);
//                // id
//                l.setId(id(w,i));
//                // czy flota wraca
//                boolean zawrocDostepne = s != null && !zawrocDostepne(s);
//                l.setWraca(zawrocDostepne);
//
//                // wysłana z księzyca
//                l.setzKsiezyca(startZKsiezyca(w,i));
//
//                // leci na ksieżyc
//
//                l.setNaKsiezyc(lotNaKsiezyc(w,i));
//
//                if(zawrocDostepne)
//                {
//                    String [] tab = s.split(" ");
//                    // ustawienie nazwy misji
//                    l.setRodzaj(tab.length>1 ? tab[0]:"Brak lotu");
//                }
//                else
//                    l.setRodzaj(s);
//
//                // ustawienie wspołrzędnych startu i celu
//                l.setStart(start(w,i));
//                l.setCel(cel(w,i));
//
//                // ustawienie czasów dotarcia do celu i powrotu
//                l.setCzasPowrot(czasPowrotu(w, RuchFlot.class.getName(),i));
//                l.setCzasUCelu(czasUCelu(w, RuchFlot.class.getName(),i));
//
//                loty.add(l);
//                Log.printLog(RuchFlot.class.getName(),"Zakończono  pobieranie danych o locie nr "+ (i-3) +
//                        ". Dodano do listy lotów.");
//            }
//            else
//            {
//                Log.printLog(RuchFlot.class.getName(),"Zmieniona ilośc lotów. Początkowa ilość - "+a+". Aktualna ilość" +
//                        " " + tmp+". Przerywam wykoanie pobierania danych.");
//                break;
//            }
//        }
//        return loty;
//    }
//}
