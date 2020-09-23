package ogame.planety;

import app.GameClient;
import com.Log;
import com.Waiter;
import ogame.SciezkaWebElementu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ListaPlanet
{
    private static SciezkaWebElementu bezKsiezyca = new SciezkaWebElementu("/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[",
            "]/a");
    private static SciezkaWebElementu zKsiezycem = new SciezkaWebElementu("/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[",
            "]/a[2]/img");
    //Wersja do 28.07.2020
//    private static SciezkaWebElementu planetContainer = new SciezkaWebElementu("/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[",
//            "]");
    //Wersja do 28.07.2020
//    private static SciezkaWebElementu planetContainer = new SciezkaWebElementu("/html/body/div[4]/div[4]/div/div/div/div/div[2]/div[",
//            "]");
    private static SciezkaWebElementu planetContainer = new SciezkaWebElementu("//*[@id=\"planetList\"]/div[",
            "]");

    private static final String HIGHTHLIGHT_PLANET = "hightlightPlanet";
    private static final String HIGHTHLIGHT_MOON = "hightlightMoon";
    private static final String CONSTRUCTION_ON_MOON = "constructionIcon moon";
    private static final String CONSTRUCTION_ON_PLANET = "constructionIcon";
    private static final String ATTACK_ALERT = "alert";

    /**
     * Sprawdza która planeta lub księżyc jest wybrana i zwraca jej pozycję na liscie.
     */
    public static int wybranaPlaneta(WebDriver w)
    {
        Waiter.sleep(100,200);
        int a = iloscPlanet(w);
        WebElement e;
        if(a != -1)
        {
            for(int i = 1; i <= a; i++)
            {
                planetContainer.setVar(i);
                try
                {
                    e = w.findElement(By.xpath(planetContainer.toString()));
                    String s = e.getAttribute("class");
                    if(s.contains(HIGHTHLIGHT_PLANET))
                        return i;
                    else if(s.contains(HIGHTHLIGHT_MOON))
                        return i - (i*2);
                }
                catch (Exception ex)
                {
                    Log.printErrorLog(ListaPlanet.class.getName(),"Nie wczytano wybranej planety.");
                }
            }
        }
        return 0;
    }

    /**
     * Sprawdza czy wskazana planeta z listy jest wybrana.
     * @param i Pozycja planety na liście.
     */
    public static boolean wybranaPlaneta(WebDriver w, int i)
    {
        Waiter.sleep(100,200);
        int a = iloscPlanet(w);
        WebElement e;
        if(a != -1) {
            planetContainer.setVar(i);
            e = w.findElement(By.xpath(planetContainer.toString()));
            String s = e.getAttribute("class");
            if (s.contains(HIGHTHLIGHT_PLANET))
                return true;
        }

        return false;
    }

    /**
     * Sprawdza czy wskazany księżyc z listy jest wybrany.
     * @param i Pozycja księżyca na liście.
     */
    public static boolean wybranyKsiezyc(WebDriver w, int i)
    {
        Waiter.sleep(100,200);
        int a = iloscPlanet(w);
        WebElement e;
        if(a != -1)
        {
            planetContainer.setVar(i);
            e = w.findElement(By.xpath(planetContainer.toString()));
            String s = e.getAttribute("class");

            if(s.contains(HIGHTHLIGHT_MOON))
                return true;
        }
        return false;
    }

    public static boolean posiadaKsiezyc(WebDriver w, int i)
    {
        int a = iloscPlanet(w);
        WebElement e;
        SciezkaWebElementu p = planetContainer;
        p.setVar(i);
        if(i <= a)
        {
            e = w.findElement(By.xpath(p.toString()));
            List<WebElement> etmp = e.findElements(By.tagName("a"));

            for(WebElement tmp : etmp)
            {
                boolean b =  tmp.getAttribute("class").contains("moonlink");
                if(b)
                    return true;
            }
        }
        return false;
    }

    /**
     * Pobiera współrzędne planety
     * @param i Pozycja na lisćie planet
     */
    public static String wspolrzedne(WebDriver w, int i)
    {
//        int a = iloscPlanet(w);
        WebElement e;
        SciezkaWebElementu p = planetContainer;
        p.setVar(i);
        e = w.findElement(By.xpath(p.toString()));
        List<WebElement> etmp = e.findElements(By.tagName("span"));

        for(WebElement tmp : etmp)
        {
            if(tmp.getAttribute("class").contains("planet-koords"))
                return tmp.getText();
        }
        return "null";
    }

    /**
     * Zwraca informację czy trwa rozbudowa na planecie.
     * @param i Pozycja na lisćie planet.
     */
    public static boolean rozbudowaPlanety(WebDriver w, int i)
    {
        int a = iloscPlanet(w);
        WebElement e;
        SciezkaWebElementu p = planetContainer;
        p.setVar(i);
        if(i <= a)
        {

            e = w.findElement(By.xpath(p.toString()));
            List<WebElement> etmp = e.findElements(By.tagName("a"));

            for(WebElement tmp : etmp)
            {
               boolean b =  tmp.getAttribute("class").contains(CONSTRUCTION_ON_PLANET);
               if(b)
                   return true;
            }
        }
        return false;
    }

    /**
     * Zwraca informację czy trwa rozbudowa na księżycu.
     * @param i Pozycja na lisćie planet.
     */
    public static boolean rozbudowaKsiezyca(WebDriver w, int i)
    {
        int a = iloscPlanet(w);
        WebElement e;
        SciezkaWebElementu p = planetContainer;
        p.setVar(i);
        if(i <= a)
        {

            e = w.findElement(By.xpath(p.toString()));
            List<WebElement> etmp = e.findElements(By.tagName("a"));

            for(WebElement tmp : etmp)
            {
                boolean b =  tmp.getAttribute("class").contains(CONSTRUCTION_ON_MOON);
                if(b)
                    return true;
            }
        }
        return false;
    }

    /**
     * Zwraca informację czy trwa atak na księżycu.
     * @param i Pozycja na lisćie planet.
     */
    public static boolean atak(WebDriver w, int i)
    {
        int a = iloscPlanet(w);
        WebElement e;
        SciezkaWebElementu p = planetContainer;
        p.setVar(i);
        if(i <= a)
        {
            try
            {
                e = w.findElement(By.xpath(p.toString()));
                List<WebElement> etmp = e.findElements(By.tagName("a"));

                for(WebElement tmp : etmp)
                {
                        boolean b =  tmp.getAttribute("class").contains(ATTACK_ALERT);
                        if(b)
                            return true;
                }
            }
            catch (Exception e1)
            {
                Log.printErrorLog(ListaPlanet.class.getName(),"Nie wczytano informacji o ataku na księżyc.");
            }
        }
        return false;
    }

    /**
     * Pobiera nazwe planety
     * @param i Pozycja na lisćie planet
     */
    public static String nazwa(WebDriver w, int i)
    {
//        int a = iloscPlanet(w);
        WebElement e;
        SciezkaWebElementu p = planetContainer;
        p.setVar(i);
        e = w.findElement(By.xpath(p.toString()));
        List<WebElement> etmp = e.findElements(By.tagName("span"));

        for(WebElement tmp : etmp)
        {
            if(tmp.getAttribute("class").contains("planet-name"))
                return tmp.getText();
        }
        return "null";
    }

    /**
     * Pobiera ID planety.
     * @param i Pozycja na lisćie planet
     */
    public static int planetID(WebDriver w, int i)
    {
        int a = iloscPlanet(w);
        WebElement e;
        if(i <= a)
        {
            planetContainer.setVar(i);
            e = w.findElement(By.xpath(planetContainer.toString()));
            String[] tmp = e.getAttribute("id").split("-");
            return Integer.valueOf(tmp[1]);
        }
        return 0;
    }

    /**
     * Pobiera aktualną ilość skolonizowanych planet.
     */
    public static int iloscPlanet(WebDriver w)
    {
        String [] paths = {
                "/html/body/div[5]/div[4]/div/div/div/div/div[1]/p/span",
                "/html/body/div[2]/div[2]/div[2]/div[3]/div/div/div/div[1]/p/span",
                "/html/body/div[4]/div[4]/div/div/div/div/div[1]/p/span"
        };

        WebElement e;
        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
                // Dwie następne linie nie są wymagane gdy bot sam kieruje stroną.
                WebDriverWait wait = new WebDriverWait(w, 5);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(paths[index])));
                e = w.findElement(By.xpath(paths[index]));
                bool = false;
                StringBuilder s = new StringBuilder(e.getText());
                int a = s.indexOf("/");
//                String q = s.substring(0,a);

                return Integer.valueOf(s.substring(0,a));
            }
            catch(Exception ex)
            {
//                Log.printErrorLog(ListaPlanet.class.getName(),"Nie wczytano ilości planet.");
            }
            finally
            {
                if(index >= paths.length)
                {
                    Log.printErrorLog(ListaPlanet.class.getName(),"Nie wczytano ilości planet.");
                }
                else
                    index++;
            }
        }

        return -1;
    }

    /**
     *  Pobiera możliwą ilość planet do skolonizowania.
     */
    public static int maxIloscPlanet(WebDriver w)
    {
        String [] paths = {
                "/html/body/div[5]/div[4]/div/div/div/div/div[1]/p/span",
                "/html/body/div[2]/div[2]/div[2]/div[3]/div/div/div/div[1]/p/span"
        };

        WebElement e;
        int index = 0;
        boolean bool = true;

        while(bool)
        {
            try
            {
                // Dwie następne linie nie są wymagane gdy bot sam kieruje stroną.
                WebDriverWait wait = new WebDriverWait(w, 5);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(paths[index])));
                e = w.findElement(By.xpath(paths[index]));
                bool = false;
                StringBuilder s = new StringBuilder(e.getText());
                String[] tab = e.getText().split("/");
//                int a = s.indexOf("/");
//                String q = s.substring(0,a);

//                return Integer.valueOf(s.substring(0,a));
                return Integer.valueOf(tab[1]);
            }
            catch(Exception ex)
            {
                Log.printErrorLog(ListaPlanet.class.getName(),"Nie wczytano ilości planet.");
            }
            finally
            {
                if(index >= paths.length)
                {
                    Log.printErrorLog(ListaPlanet.class.getName(),"Nie wczytano ilość planet.");
                }
                else
                    index++;
            }
        }

        return -1;
    }

    public static void kliknijPlanete(WebDriver w, int i)
    {
        int q = iloscPlanet(w);

        if(i > q)
            Log.printLog(ListaPlanet.class.getName(),"Za wysoka wartość. Ilość planet na liście "+q+" wprowadzono "+i+".");
        else
        {
            SciezkaWebElementu[] sciezki = {
                    new SciezkaWebElementu("/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a/img",i),
                    new SciezkaWebElementu("/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[1]/img",i),
                    new SciezkaWebElementu("/html/body/div[2]/div[2]/div[2]/div[3]/div/div/div/div[2]/div[","]/a[1]/img",i),
                    new SciezkaWebElementu("/html/body/div[2]/div[2]/div[2]/div[3]/div/div/div/div[2]/div[","]/a/img",i),
            };

            WebElement e;
            int index = 0;
            boolean bool = true;

            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(sciezki[index].toString()));
                    GameClient.scrollToElement(w,e);
                    bool = false;
                    e.click();
                }
                catch(Exception ex)
                {
                    Log.printLog(ListaPlanet.class.getName(),"Nie wczytano planety.");
                    index++;
                }
                finally
                {
                    if(index >= sciezki.length)
                        Log.printLog(ListaPlanet.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");

                    if(bool)
                        Log.printLog(ListaPlanet.class.getName(),"Nie wczytano symbolu budowy przy planecie. Zmieniam ścieżkę "+ index);
                }
            }
        }
    }

    public static void kliknijKsiezyc(WebDriver w, Planeta p)
    {
        int q = iloscPlanet(w);

        if(p.getPozycjaNaLiscie() > q)
            Log.printLog(ListaPlanet.class.getName(),"Za wysoka wartość. Ilość planet na liście "+q+" wprowadzono "+p.getPozycjaNaLiscie()+".");
        else
        {
            SciezkaWebElementu[] sciezki = {
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[1]/img",p.getPozycjaNaLiscie()),
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[2]/img",p.getPozycjaNaLiscie()),
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[3]/img",p.getPozycjaNaLiscie()),
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[4]/img",p.getPozycjaNaLiscie()),
            };
            WebElement e;
            int index = 0;
            boolean bool = true;

            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(sciezki[index].toString()));
                    if(e.getAttribute("class").contains("moon"))
                    {
                        GameClient.scrollToElement(w,e);
                        bool = false;
                        e.click();
                    }
                }
                catch(Exception ex)
                {
//                    Log.printLog(ListaPlanet.class.getName(),"Nie wczytano ksiezyca.");
                }
                finally
                {
                    index++;
                    if(index >= sciezki.length)
                    {
                        Log.printLog(ListaPlanet.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                        bool = false;
                    }
                    if(bool)
                        Log.printLog(ListaPlanet.class.getName(),"Nie wczytano ksiezyca. Zmieniam ścieżkę "+ index);
                }
            }
        }
    }

    public static void kliknijKsiezyc(WebDriver w, int i)
    {
        int q = iloscPlanet(w);

        if(i > q)
            Log.printLog(ListaPlanet.class.getName(),"Za wysoka wartość. Ilość planet na liście "+q+" wprowadzono "+i+".");
        else
        {
            SciezkaWebElementu[] sciezki = {
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[1]/img",i),
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[2]/img",i),
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[3]/img",i),
                    new SciezkaWebElementu( "/html/body/div[5]/div[4]/div/div/div/div/div[2]/div[","]/a[4]/img",i),
            };
            WebElement e;
            int index = 0;
            boolean bool = true;

            while(bool)
            {
                try
                {
                    e = w.findElement(By.xpath(sciezki[index].toString()));
                    if(e.getAttribute("class").contains("moon"))
                    {
                        GameClient.scrollToElement(w,e);
                        bool = false;
                        e.click();
                    }
                }
                catch(Exception ex)
                {
//                    Log.printLog(ListaPlanet.class.getName(),"Nie wczytano ksiezyca.");
                }
                finally
                {
                    index++;
                    if(index >= sciezki.length)
                    {
                        Log.printLog(ListaPlanet.class.getName(),"Sprawdzono wszystkie ścieżki, żadna nie pasuje.");
                        bool = false;
                    }
                    if(bool)
                        Log.printLog(ListaPlanet.class.getName(),"Nie wczytano ksiezyca. Zmieniam ścieżkę "+ index);
                }
            }
        }
    }

    @Deprecated
    public static void wBudowie(WebDriver w,Planeta p)
    {
        int a = p.getPozycjaNaLiscie();
        p.setConstructionInProgress(false);
        p.setMoonConstructionInProgress(false);
        WebElement e;

        planetContainer.setVar(a);
        e = w.findElement(By.xpath(planetContainer.toString()));
        List<WebElement> elements = e.findElements(By.tagName("a"));


        for(WebElement webElement : elements)
        {
            String s = webElement.getAttribute("class");
            if(s.contains(CONSTRUCTION_ON_MOON))
                p.setMoonConstructionInProgress(true);
            else if(s.contains(CONSTRUCTION_ON_PLANET) && !s.contains(CONSTRUCTION_ON_MOON))
                p.setConstructionInProgress(true);
        }
    }
}
