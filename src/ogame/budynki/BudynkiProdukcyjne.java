package ogame.budynki;

import app.GameClient;
import com.Log;
import ogame.Header;
import ogame.LeftMenu;
import ogame.SciezkaWebElementu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BudynkiProdukcyjne
{
    private static SciezkaWebElementu BUDYNEK = new SciezkaWebElementu("//*[@id=\"producers\"]/li[","]");
    private static SciezkaWebElementu POZIOM_BUDYNKU = new SciezkaWebElementu("//*[@id=\"producers\"]/li[","]/span/span/span[1]");
    private static SciezkaWebElementu ROZBUDUJ_BUDYNEK = new SciezkaWebElementu("//*[@id=\"producers\"]/li[","]/span/button");

    private static final Budynek[] BUDYNKI_MOON =
            {
                    new Budynek(1,"Kopalnia metalu", 1),
                    new Budynek(2,"Kopalnia kryształu", 2),
                    new Budynek(3,"Ekstraktor deuteru", 3),
                    new Budynek(4,"Elektrownia słoneczna", 4),
                    new Budynek(5,"Elektrownia fuzyjna", 12),
                    new Budynek(6,"Satelita słoneczny", 212),
                    new Budynek(7,"Magazyn metalu", 22),
                    new Budynek(8,"Magazyn kryształu", 23),
                    new Budynek(9,"Zbiornik deuteru", 24),
            };

    private static final Budynek [] BUDYNKI =
            {
                    new Budynek(1,"Kopalnia metalu", 1),
                    new Budynek(2,"Kopalnia kryształu", 2),
                    new Budynek(3,"Ekstraktor deuteru", 3),
                    new Budynek(4,"Elektrownia słoneczna", 4),
                    new Budynek(5,"Elektrownia fuzyjna", 12),
                    new Budynek(6,"Satelita słoneczny", 212),
                    new Budynek(7,"Pełzacz", 217),
                    new Budynek(8,"Magazyn metalu", 22),
                    new Budynek(9,"Magazyn kryształu", 23),
                    new Budynek(10,"Zbiornik deuteru", 24),
            };
    /**
     * Wybiera wskazany budynek.
     * @param w driver
     * @param nr Rodzaj budynku na planecie:
     *           1 - Kopalnia metalu
     *           2 - Kopalnia kryształu
     *           3 - Ekstraktor deuteru
     *           4 - Elektrownia słoneczna
     *           5 - Elektrownia fuzyjna
     *           6 - Satelita słoneczny
     *           7 - Pełzacz
     *           8 - Magazyn metalu
     *           9 - Magazyn kryształu
     *           10- Zbiornik deuteru
     *
     *           Rodzaj budynku na księżycu:
     *           1 - Kopalnia metalu
     *           2 - Kopalnia kryształu
     *           3 - Ekstraktor deuteru
     *           4 - Elektrownia słoneczna
     *           5 - Elektrownia fuzyjna
     *           6 - Satelita słoneczny
     *           7 - Magazyn metalu
     *           8 - Magazyn kryształu
     *           9 - Zbiornik deuteru
     */
    public static boolean kliknijWBudynek(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Surowce", BudynkiProdukcyjne.class.getName()))
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
                Log.printErrorLog(BudynkiProdukcyjne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiProdukcyjne.class.getName());

        return false;
    }

    /**
     * Pobiera status budynku. Czy jest w rozbudowie, możliwa rozbudowa, brakuje surowców na rozbudowę, niedostępny.
     * @param w driver
     * @param nr Rodzaj budynku na planecie:
     *           1 - Kopalnia metalu
     *           2 - Kopalnia kryształu
     *           3 - Ekstraktor deuteru
     *           4 - Elektrownia słoneczna
     *           5 - Elektrownia fuzyjna
     *           6 - Satelita słoneczny
     *           7 - Pełzacz
     *           8 - Magazyn metalu
     *           9 - Magazyn kryształu
     *           10- Zbiornik deuteru
     *
     *           Rodzaj budynku na księżycu:
     *           1 - Kopalnia metalu
     *           2 - Kopalnia kryształu
     *           3 - Ekstraktor deuteru
     *           4 - Elektrownia słoneczna
     *           5 - Elektrownia fuzyjna
     *           6 - Satelita słoneczny
     *           7 - Magazyn metalu
     *           8 - Magazyn kryształu
     *           9 - Zbiornik deuteru
     */
    public static String statusBudynku(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Surowce", BudynkiProdukcyjne.class.getName()))
        {
            BUDYNEK.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(BUDYNEK.toString()));
                return e.getAttribute("data-status");

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiProdukcyjne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiProdukcyjne.class.getName());

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
        if(Header.dobryHeaderWyswietlony(w,"Surowce", BudynkiProdukcyjne.class.getName()))
        {
            BUDYNEK.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(BUDYNEK.toString()));
                return Integer.valueOf(e.getAttribute("data-technology"));

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiProdukcyjne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiProdukcyjne.class.getName());

        return -1;
    }

    /**
     * Zwraca aktualny poziom budynku.
     * @param w driver
     * @param nr Rodzaj budynku na planecie:
     *           1 - Kopalnia metalu
     *           2 - Kopalnia kryształu
     *           3 - Ekstraktor deuteru
     *           4 - Elektrownia słoneczna
     *           5 - Elektrownia fuzyjna
     *           6 - Satelita słoneczny
     *           7 - Pełzacz
     *           8 - Magazyn metalu
     *           9 - Magazyn kryształu
     *           10- Zbiornik deuteru
     *
     *           Rodzaj budynku na księżycu:
     *           1 - Kopalnia metalu
     *           2 - Kopalnia kryształu
     *           3 - Ekstraktor deuteru
     *           4 - Elektrownia słoneczna
     *           5 - Elektrownia fuzyjna
     *           6 - Satelita słoneczny
     *           7 - Magazyn metalu
     *           8 - Magazyn kryształu
     *           9 - Zbiornik deuteru
     */
    public static int poziomBudynku(WebDriver w, int nr, boolean onMoon)
    {
        if(Header.dobryHeaderWyswietlony(w,"Surowce", BudynkiProdukcyjne.class.getName()))
        {
            POZIOM_BUDYNKU.setVar(nr);

            try
            {
                WebElement e = w.findElement(By.xpath(POZIOM_BUDYNKU.toString()));

                return Integer.valueOf(e.getText());

            }
            catch(Exception ex)
            {
                Log.printErrorLog(BudynkiProdukcyjne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
            }

        }
        else
            LeftMenu.pressSurowce(w, BudynkiProdukcyjne.class.getName());

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
        if(Header.dobryHeaderWyswietlony(w,"Surowce", BudynkiProdukcyjne.class.getName()))
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
                    Log.printErrorLog(BudynkiProdukcyjne.class.getName(),"Nie wczytano budynku "+ getBudynek(nr,onMoon).getName()+".");
                }
            }
            else
                Log.printErrorLog(BudynkiProdukcyjne.class.getName(),"Budynek "+ getBudynek(nr,onMoon).getName()+" nie może " +
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
    public static Budynek getBudynek(int nr, boolean onMoon)
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
