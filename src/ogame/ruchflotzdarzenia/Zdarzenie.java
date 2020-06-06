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

    public  static int eventType(WebDriver w, int nr)
    {
        zdarzenie.setVar(nr);
        WebElement e = w.findElement(By.xpath(zdarzenie.toString()));
        return Integer.valueOf(e.getAttribute("data-mission-type"));
    }

    public  static int id(WebDriver w, int nr)
    {
        zdarzenie.setVar(nr);
        WebElement e = w.findElement(By.xpath(zdarzenie.toString()));
        return Integer.valueOf(e.getAttribute("id").split("-")[1]);
    }


    public static String time(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);

        WebElement e = w.findElement(By.xpath(tmp.append("/td[2]")));

//        Log.printLog1(e.getText(),Zdarzenie.class,40);
        String [] tab = e.getText().split(" ");

        return tab[0];
    }

    public static String wspolrzedneCelu(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);
        WebElement e = w.findElement(By.xpath(tmp.append("/td[9]")));

        return e.getText();
    }

    public static boolean naKsiezyc(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);


        WebElement e = w.findElement(By.xpath(tmp.append("/td[8]/span/figure")));


        return e.getAttribute("class").contains("planetIcon moon");
    }

    public static boolean naPlanete(WebDriver w, int nr)
    {
        SciezkaWebElementu tmp = zdarzenie;
        tmp.setVar(nr);


        WebElement e = w.findElement(By.xpath(tmp.append("/td[8]/span/figure")));


        return e.getAttribute("class").contains("planetIcon planet");
    }
}
