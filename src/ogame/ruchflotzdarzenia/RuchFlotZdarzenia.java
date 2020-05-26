package ogame.ruchflotzdarzenia;

import app.GameClient;
import com.DifferentMethods;
import com.Log;
import com.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Dane są pobierane z zakładki <b>Zdarzenia</b>. Zakładka jest włączana poprzez kliknięcie strzałki w boxie,
 * w którym wyświetlana jest ilość misji oraz dane o najbliższej misji, która będzie się kończyć.
 */
public class RuchFlotZdarzenia
{

    private static final String ILOSC_MISJI_P = "/html/body/div[5]/div[1]/div[4]/div/div[1]/div[1]/p";
    private static final String MISJE_TBODY = "//*[@id=\"eventContent\"]/tbody";



    public static void rozwin(WebDriver w)
    {
            WebElement e = w.findElement(By.xpath("//*[@id=\"js_eventDetailsClosed\"]"));
            GameClient.scrollToElement(w,e);
            e.click();
            Waiter.sleep(100,100);
            Log.printLog(RuchFlotZdarzenia.class.getName(),"Klikam w button: Rozwiń box Zdarzenia.");
    }

    public static void zwin(WebDriver w)
    {
        WebElement e = w.findElement(By.xpath("//*[@id=\"js_eventDetailsOpen\"]"));
        GameClient.scrollToElement(w,e);
        e.click();
        Waiter.sleep(100,100);
        Log.printLog(RuchFlotZdarzenia.class.getName(),"Klikam w button: Zwiń box Zdarzenia.");
    }

    private static List<Misja>  misje(WebDriver w)
    {
        if(!brakRuchuFlot(w))
        {
            WebElement e = w.findElement(By.xpath(ILOSC_MISJI_P));
            List<WebElement> e2 = e.findElements(By.tagName("span"));
            List<Misja>  m = new ArrayList<>();

            for(WebElement tmp : e2)
            {
                if(tmp.getAttribute("class").equals("undermark"))
                {
                    String s = tmp.getText();
                    String [] strings = s.split(" ");

                    if(strings.length > 1 && (strings[2].equals("własna") || strings[2].equals("przyjazna") ||strings[2].equals("wroga")))
                        m.add(new Misja(strings[2],Integer.valueOf(strings[0])));
                }
            }
            return m;
        }
        return null;
    }

    public static int iloscMisjiWlasnych(WebDriver w)
    {
        try {
            List<Misja> misje = misje(w);

            if (misje == null)
                return -1;
            else {
                for (Misja m : misje) {
                    if (m.getNazwa().equals("własna"))
                        return m.getIlosc();
                }
            }
        }
        catch (Exception e)
        {
            Log.printErrorLog(RuchFlotZdarzenia.class.getName(),"-> iloscMisjiWlasnych()");
        }
        return -1;
    }

    private static boolean brakRuchuFlot(WebDriver w)
    {
        return w.findElement(By.xpath("//*[@id=\"eventboxBlank\"]")).getAttribute("style").equals("");
    }


    public static class Misja
    {
        private String nazwa;
        private int ilosc;

        Misja(String nazwa, int ilosc) {
            this.nazwa = nazwa;
            this.ilosc = ilosc;
        }

        String getNazwa() {
            return nazwa;
        }

        public void setNazwa(String nazwa) {
            this.nazwa = nazwa;
        }

        int getIlosc() {
            return ilosc;
        }

        public void setIlosc(int ilosc) {
            this.ilosc = ilosc;
        }

        @Override
        public String toString() {
            int dl = 15;

            String sb = "\n";
            sb += (DifferentMethods.initVariable("Nazwa misji ",dl)+nazwa)+
                    "\n" +
                    DifferentMethods.initVariable("Ilość misji ",dl) + ilosc+
                    "\n" +
                    DifferentMethods.initVariable("=END--END--END--END= ",dl);

            return sb;
        }
    }

}
