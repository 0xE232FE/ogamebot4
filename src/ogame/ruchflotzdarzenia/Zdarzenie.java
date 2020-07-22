package ogame.ruchflotzdarzenia;


import com.Log;
import ogame.SciezkaWebElementu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Zdarzenie
{
    private static final String MISJE_TBODY = "//*[@id=\"eventContent\"]/tbody";
    private static SciezkaWebElementu zdarzenie = new SciezkaWebElementu("//*[@id=\"eventContent\"]/tbody/tr[","]");

    /**
     * Pobiera typ misji jako int. Każda misja ma przypisany dla siebie odpowiednik w postaci liczby typu <b>int</b>.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Rodzaj misji.
     */
    static int eventType(WebDriver w, int nr)
    {
        zdarzenie.setVar(nr);
        WebElement e = w.findElement(By.xpath(zdarzenie.toString()));
        return Integer.valueOf(e.getAttribute("data-mission-type"));
    }

    /**
     * Pobiera informację, czy wskazane zdarzenie, to cześć Ataku Związku.
     * @param w WebDriver.
     * @param nr Numer misji na liście.
     * @return Jeżeli zdarzenie to część ataku związku zwróci <b>true</b> w innym wypadku false.
     */
    public static boolean isPartnerInfoOfAllianceAtak(WebDriver w, int nr)
    {
        zdarzenie.setVar(nr);
        WebElement e = w.findElement(By.xpath(zdarzenie.toString()));

        return e.getAttribute("class").contains("partnerInfo");
    }

    /**
     * Pobiera id misji. Jest to niepowtarzalny identyfikator dla każdej misji w Boxie Zdarzeń.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Id.
     */
    public  static int id(WebDriver w, int nr)
    {
        zdarzenie.setVar(nr);
        WebElement e = w.findElement(By.xpath(zdarzenie.toString()));

        StringBuilder sb = new StringBuilder(e.getAttribute("id").split("-")[1]);
        if(sb.toString().contains("union"))
        {
            sb.delete(0,5);
            return Integer.valueOf(sb.toString());
        }
        else
            return Integer.valueOf(sb.toString());
//            return Integer.valueOf(e.getAttribute("id").split("-")[1]);
    }

    /**
     * Pobiera czas misji. Jest to czas np. wejścia ataku lub dostaczenia surowców w misji Transportuj.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Czas.
     */
    static String time(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);

        WebElement e = w.findElement(By.xpath(tmp.append("/td[2]")));
        String [] tab = e.getText().split(" ");
//        Log.printLog1("Czas z boxu zdarzeń: " + tab[0], Zdarzenie.class,64);
        return tab[0];
    }

    /**
     * Sprawdza czy wskazana misja, to wrogi atak.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Jeżeli jest to wrogi atak, to zwraca <b>true</b>.
     */
    static boolean wrogiAtak(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);

        WebElement e = w.findElement(By.xpath(tmp.append("/td[1]/span")));

        return e.getAttribute("class").contains("hostile");
    }

    /**
     * Pobiera współrzędne celu.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Współrzędne.
     */
    static String wspolrzedneCelu(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);
        WebElement e = w.findElement(By.xpath(tmp.append("/td[9]")));

        return e.getText();
    }

    /**
     * Sprawdza czy misja jest na księżyc.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Jeżeli misja jest na księżyc zwraca <b>true</b>.
     */
    public static boolean naKsiezyc(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);

        String [] paths = {
                tmp.append("/td[8]/span/figure"),
                tmp.append("/td[8]/figure")
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
                return e.getAttribute("class").contains("planetIcon moon");
            }
            catch(Exception ex)
            {
                index++;
            }
            finally
            {
                if(index >= paths.length)
                {
                    bool = false;
                    Log.printLog(Zdarzenie.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                }

                if(bool)
                    Log.printLog(Zdarzenie.class.getName(), "Nie wczytano Tytuł Header. Zmieniam ścieżkę "+ index);
            }
        }
        return false;
    }

    /**
     * Sprawdza misja jest na planetę.
     * @param w WebDriver
     * @param nr Numer misji na liście.
     * @return Jeżeli misja jest na planetę zwraca <b>true</b>.
     */
    static boolean naPlanete(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);
        String [] paths = {
                tmp.append("/td[8]/span/figure"),
                tmp.append("/td[8]/figure")
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
                return e.getAttribute("class").contains("planetIcon planet");
            }
            catch(Exception ex)
            {
                index++;
            }
            finally
            {
                if(index >= paths.length)
                {
                    bool = false;
                    Log.printLog(Zdarzenie.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                }

                if(bool)
                    Log.printLog(Zdarzenie.class.getName(), "Nie wczytano Tytuł Header. Zmieniam ścieżkę "+ index);
            }
        }
        return false;
    }
}
