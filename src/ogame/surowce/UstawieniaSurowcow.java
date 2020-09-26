package ogame.surowce;

import com.Log;
import ogame.Header;
import ogame.LeftMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UstawieniaSurowcow
{

    public static String metalGodzinneWydobycie(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ustawienia surowców", UstawieniaSurowcow.class.getName()))
        {
            try
            {
                WebElement e = w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[2]/div[2]/form/table/tbody/tr[18]/td[2]/span"));

                return e.getText();

            }
            catch(Exception ex)
            {
                Log.printErrorLog(UstawieniaSurowcow.class.getName(),"Nie wczytano metal godzinne wydobycie.");
            }

        }
        else
            LeftMenu.pressSurowce(w, UstawieniaSurowcow.class.getName());

        return "-1";
    }

    public static String krysztalGodzinneWydobycie(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ustawienia surowców", UstawieniaSurowcow.class.getName()))
        {
            try
            {
                WebElement e = w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[2]/div[2]/form/table/tbody/tr[18]/td[3]/span"));

                return e.getText();

            }
            catch(Exception ex)
            {
                Log.printErrorLog(UstawieniaSurowcow.class.getName(),"Nie wczytano kryształ godzinne wydobycie.");
            }

        }
        else
            LeftMenu.pressSurowce(w, UstawieniaSurowcow.class.getName());

        return "-1";
    }

    public static String deuterGodzinneWydobycie(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ustawienia surowców", UstawieniaSurowcow.class.getName()))
        {
            try
            {
                WebElement e = w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[2]/div[2]/form/table/tbody/tr[18]/td[4]/span"));

                return e.getText();

            }
            catch(Exception ex)
            {
                Log.printErrorLog(UstawieniaSurowcow.class.getName(),"Nie wczytano deuter godzinne wydobycie.");
            }

        }
        else
            LeftMenu.pressSurowce(w, UstawieniaSurowcow.class.getName());

        return "-1";
    }

    public static String wolnaEnergia(WebDriver w)
    {
        if(Header.dobryHeaderWyswietlony(w,"Ustawienia surowców", UstawieniaSurowcow.class.getName()))
        {
            try
            {
                WebElement e = w.findElement(By.xpath("//*[@id=\"inhalt\"]/div[2]/div[2]/form/table/tbody/tr[18]/td[5]/span"));

                return e.getText();

            }
            catch(Exception ex)
            {
                Log.printErrorLog(UstawieniaSurowcow.class.getName(),"Nie wczytano wolna energia.");
            }

        }
        else
            LeftMenu.pressSurowce(w, UstawieniaSurowcow.class.getName());

        return "-1";
    }


}
