package ogame.budynki;

import app.GameClient;
import com.Log;
import ogame.Header;
import ogame.LeftMenu;
import ogame.SciezkaWebElementu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BudynkiTechnologiczne {

    private static SciezkaWebElementu BUDYNEK = new SciezkaWebElementu("//*[@id=\"technologies\"]/ul/li[","]");
    private static SciezkaWebElementu POZIOM_BUDYNKU = new SciezkaWebElementu("//*[@id=\"technologies\"]/ul/li[","]/span/span/span[1]");
    private static SciezkaWebElementu ROZBUDUJ_BUDYNEK = new SciezkaWebElementu("//*[@id=\"technologies\"]/ul/li[","]/span/button");

    private static final Budynek[] BUDYNKI_MOON =
            {
                    new Budynek(1,"Fabryka robotów", 14),
                    new Budynek(2,"Stocznia", 21),
                    new Budynek(3,"Stacja księżycowa", 41),
                    new Budynek(4,"Falang czujników", 42),
                    new Budynek(5,"Teleporter", 43),
            };

    private static final Budynek [] BUDYNKI =
            {
                    new Budynek(1,"Fabryka robotów", 14),
                    new Budynek(2,"Stocznia", 21),
                    new Budynek(3,"Laboratorium badawcze", 31),
                    new Budynek(4,"Depozyt sojuszniczy", 34),
                    new Budynek(5,"Silos rakietowy", 44),
                    new Budynek(6,"Fabryka nanitów", 15),
                    new Budynek(7,"Terraformer", 33),
                    new Budynek(8,"Dok kosmiczny", 36),
            };
    /**
     * Wybiera wskazany budynek.
     * @param w driver
     * @param nr Rodzaj budynku na planecie:
     *           1 - Fabryka robotów
     *           2 - Stocznia
     *           3 - Laboratirum badawcze
     *           4 - Depozyt sojuszniczy
     *           5 - Silos rakietowy
     *           6 - Fabryka nanitów
     *           7 - Terraformer
     *           8 - Dok kosmiczny
     *
     *           Rodzaj budynku na księżycu:
     *           1 - Fabryka robotów
     *           2 - Stocznia
     *           3 - Stacja księżycowa
     *           4 - Falanga czujników
     *           5 - Teleporter
     */
    public static boolean kliknijWBudynek(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Stacja", BudynkiTechnologiczne.class.getName()))
        {
            BUDYNEK.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(BUDYNEK.toString()));
                GameClient.scrollToElement(w,e);
                e.click();
                return true;

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiTechnologiczne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiTechnologiczne.class.getName());

        return false;
    }


    /**
     * Pobiera status budynku. Czy jest w rozbudowie, możliwa rozbudowa, brakuje surowców na rozbudowę, niedostępny.
     * @param w driver
     * @param nr Rodzaj budynku na planecie:
     *           1 - Fabryka robotów
     *           2 - Stocznia
     *           3 - Laboratirum badawcze
     *           4 - Depozyt sojuszniczy
     *           5 - Silos rakietowy
     *           6 - Fabryka nanitów
     *           7 - Terraformer
     *           8 - Dok kosmiczny
     *
     *           Rodzaj budynku na księżycu:
     *           1 - Fabryka robotów
     *           2 - Stocznia
     *           3 - Stacja księżycowa
     *           4 - Falanga czujników
     *           5 - Teleporter
     */
    public static String statusBudynku(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Stacja", BudynkiTechnologiczne.class.getName()))
        {
            BUDYNEK.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(BUDYNEK.toString()));
                return e.getAttribute("data-status");

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiTechnologiczne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiTechnologiczne.class.getName());

        return "null";
    }


    /**
     * Zwraca niepowtarzalny numer dla każdegu rodzaju budynku.
     * @param w ***
     * @param nr ***
     * @param onMoon ***
     * @return Niepowtarzalny numer lub -1 gdy brak możliwości pobrania.
     */
    public static int dataTechnology(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Stacja", BudynkiTechnologiczne.class.getName()))
        {
            BUDYNEK.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(BUDYNEK.toString()));
                return Integer.valueOf(e.getAttribute("data-technology"));

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiTechnologiczne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiTechnologiczne.class.getName());

        return -1;
    }

    /**
     * Zwraca aktualny poziom budynku.
     * @param w driver
     * @param nr Rodzaj budynku na planecie:
     *           1 - Fabryka robotów
     *           2 - Stocznia
     *           3 - Laboratirum badawcze
     *           4 - Depozyt sojuszniczy
     *           5 - Silos rakietowy
     *           6 - Fabryka nanitów
     *           7 - Terraformer
     *           8 - Dok kosmiczny
     *
     *           Rodzaj budynku na księżycu:
     *           1 - Fabryka robotów
     *           2 - Stocznia
     *           3 - Stacja księżycowa
     *           4 - Falanga czujników
     *           5 - Teleporter
     */
    public static int poziomBudynku(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Stacja", BudynkiTechnologiczne.class.getName()))
        {
            POZIOM_BUDYNKU.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(POZIOM_BUDYNKU.toString()));

                return Integer.valueOf(e.getText());

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiTechnologiczne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiTechnologiczne.class.getName());

        return -1;
    }

    /**
     * Klika w rozbuduj w wskazanym budynku.
     * @param w ***
     * @param nr ***
     * @param onMoon Czy jest to na księżycu.
     * @return true jeżeli kliknął w innym wypadku false.
     */
    public static boolean rozbudujBudynek(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Stacja", BudynkiTechnologiczne.class.getName()))
        {
            ROZBUDUJ_BUDYNEK.setVar(nr);

            if(statusBudynku(w,nr,onMoon).equals("on"))
            {
                try
                {
                    WebElement e = w.findElement(By.xpath(ROZBUDUJ_BUDYNEK.toString()));
                    GameClient.scrollToElement(w,e);
                    e.click();
                    return true;

                }
                catch(Exception ex)
                {
                    Log.printErrorLog(BudynkiTechnologiczne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
                }
            }
            else
                Log.printErrorLog(BudynkiTechnologiczne.class.getName(),"Budynek "+ getBudynek(nr,onMoon).getName()+" nie może " +
                        "zostać rozbudowany.");

        }
        else
            LeftMenu.pressSurowce(w, BudynkiProdukcyjne.class.getName());

        return false;
    }




    /**
     * Zwraca budynek który jest w rozbudowie. Jeżeli żaden się nie rozbudowuje, to zwróci pusty obiekt.
     * @param webDriver ***
     * @param onMoon Czy jest to na księżycu.
     * @return Budynek lub pusty konstruktor, gdy nic się nie buduje.
     */
    public static Budynek wRozbudowie(WebDriver webDriver, boolean onMoon)
    {
        if(onMoon)
        {
            for(int i = 1; i <= 9; i++)
            {
                if(statusBudynku(webDriver,i,onMoon).equals("active"))
                    return getBudynek(i,onMoon);
            }
        }
        else
        {
            for(int i = 1; i <= 10; i++)
            {
                if(statusBudynku(webDriver,i,onMoon).equals("active"))
                    return getBudynek(i,onMoon);
            }
        }
        return new Budynek(-1,"null", -1);
    }

    /**
     * Zwraca budynek o określonym indexie.
     * @param nr ***
     * @param onMoon Czy jest to na księżycu.
     * @return Budynek lub pusty konstruktor, gdy nr jest spoza zakresu.
     */
    private static Budynek getBudynek(int nr, boolean onMoon)
    {
        if(onMoon)
        {
            for(Budynek b : BUDYNKI_MOON)
            {
                if(b.getIndex() == nr)
                    return b;
            }
        }
        else
        {
            for(Budynek b : BUDYNKI)
            {
                if(b.getIndex() == nr)
                    return b;
            }
        }
        return new Budynek(-1,"null", -1);
    }
}
