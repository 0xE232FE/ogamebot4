package ogame.ruchflotzdarzenia;

import app.GameClient;
import app.czas.Czas;
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
//    private SciezkaWebElementu zdarzenie = new SciezkaWebElementu("//*[@id=\"eventContent\"]/tbody/tr[","]");


    /**
     * Jeżeli jest niewidoczny to zwróci true.
     */
    public static boolean eventBoxNiewidoczny(WebDriver w)
    {
        WebElement e = w.findElement(By.xpath("//*[@id=\"eventboxContent\"]"));
        return e.getAttribute("style").contains("none");
    }

    /**
     * Pobiera i zwraca wrogie misje z EventBoxa.
     */
    public static List<WrogaMisja> getWrogieMisje(WebDriver w)
    {
        List<WrogaMisja> tmp = new ArrayList<>();
        int a = iloscZdarzen(w);
        for(int i = 1; i <= a; i++)
        {
            if(Zdarzenie.eventType(w,i) == 1 && Zdarzenie.wrogiAtak(w,i))
            {
                if(Zdarzenie.isPartnerInfoOfAllianceAtak(w,i))
                    Log.printLog(RuchFlotZdarzenia.class.getName(),"Wskazane zdarzenie nr "+ i + " to jedna z flot ataku w związku.");
                else
                {
                    Log.printLog(RuchFlotZdarzenia.class.getName(),"Rozpoczynam pobieranie misji wrogiej z pozycji " + i + ".");
                    WrogaMisja wrogaMisja = new WrogaMisja(new Czas(Zdarzenie.time(w,i)),Zdarzenie.naPlanete(w,i),
                            Zdarzenie.wspolrzedneCelu(w,i),Zdarzenie.id(w,i));
                    tmp.add(wrogaMisja);
                    Log.printLog(RuchFlotZdarzenia.class.getName(),"Zakończono pobieranie misji wrogiej.");
                }
            }
        }

        return tmp;
    }

    /**
     * Klika w button rozwijający misje w EventBoxie.
     */
    public static void rozwin(WebDriver w)
    {
            WebElement e = w.findElement(By.xpath("//*[@id=\"js_eventDetailsClosed\"]"));
            GameClient.scrollToElement(w,e);

            if(eventBoxNiewidoczny(w))
            {
                boolean test = true;
                byte counter = 0;
                try
                {
                   while(test)
                   {
                       counter++;
                       e.click();
                       test = false;
                       Waiter.sleep(100,100);
                   }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    Log.printErrorLog(RuchFlotZdarzenia.class.getName(), "Nie kliknięto buttona do rozwinięcia ruchu flot. Próba "+counter);
                    if(counter >= 10)
                        return;
                }

                Log.printLog(RuchFlotZdarzenia.class.getName(),"Klikam w button: Rozwiń box Zdarzenia.");
            }
            else
                Log.printLog(RuchFlotZdarzenia.class.getName(),"Event box zdarzenia jest wyświetlony.");

    }

    /**
     * Zwraca ilość zdarzeń wyświetlanych na głownie stronie gry.
     */
    private static int iloscZdarzen(WebDriver w)
    {
        List<WebElement> tmp = new ArrayList<>();
        try
        {
            WebElement e = w.findElement(By.xpath(MISJE_TBODY));
            tmp = e.findElements(By.tagName("tr"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return tmp.size();
    }

    /**
     * Metoda klikająca w button zwijający EventBox
     */
    public static void zwin(WebDriver w)
    {
        WebElement e = w.findElement(By.xpath("//*[@id=\"js_eventDetailsOpen\"]"));
        GameClient.scrollToElement(w,e);
        e.click();
        Waiter.sleep(100,100);
        Log.printLog(RuchFlotZdarzenia.class.getName(),"Klikam w button: Zwiń box Zdarzenia.");
    }

    /**
     * Pobiera misje z EventBoca i zwraca je w postaci listy.
     */
    private static List<Misja>  misje(WebDriver w)
    {
        if(!brakRuchuFlot(w))
        {
            WebElement e = w.findElement(By.xpath(ILOSC_MISJI_P));
            List<WebElement> e2 = e.findElements(By.tagName("span"));
            List<Misja>  m = new ArrayList<>();

            for(WebElement tmp : e2)
            {
                String attr = tmp.getAttribute("class");

                if(attr.equals("undermark") || attr.equals("middlemark") ||attr.equals("overmark") )
                {
                    String s = tmp.getText();
                    String [] strings = s.split(" ");

                    int a = strings.length;

                    switch (a)
                    {
                        case 2:
                        {
                            if(strings[1].equals("przyjazny") || strings[1].equals("wrogi"))
                                m.add(new Misja(strings[1],Integer.valueOf(strings[0])));
                            break;
                        }
                        case 3:
                        {
                            if(strings[2].equals("własna"))
                                m.add(new Misja(strings[2],Integer.valueOf(strings[0])));
                            break;
                        }

                    }
                }
            }
            return m;
        }
        return null;
    }
    /**
     * Zwraca ilości misji własnych w EventBoxie.
     */
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

    /**
     * Zwraca ilości misji wrogich w EventBoxie
     */
    public static int iloscMisjiWrogich(WebDriver w)
    {
        try {
            List<Misja> misje = misje(w);

            if (misje == null)
                return -1;
            else {
                for (Misja m : misje) {
                    if (m.getNazwa().equals("wrogi"))
                        return m.getIlosc();
                }
            }
        }
        catch (Exception e)
        {
            Log.printErrorLog(RuchFlotZdarzenia.class.getName(),"-> iloscMisjiWrogich()");
        }
        return -1;
    }

    /**
     * Sprawdza czy w EventBoxie są jakieś misje.
     * @return Jeżeli są misję zwróci <b>true</b>
     */
    private static boolean brakRuchuFlot(WebDriver w)
    {
        return w.findElement(By.xpath("//*[@id=\"eventboxBlank\"]")).getAttribute("style").equals("");
    }

    /**
     * Klasa przechowująca dane o misjach. Nazwe misji i ilość misji o określonej nazwie.
     */
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

        int getIlosc() {
            return ilosc;
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
