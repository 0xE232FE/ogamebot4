package app;

import com.DifferentMethods;
import com.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OgameWeb {

    private static WebDriver webDriver;

    OgameWeb(String loginPage, String driverPath)
    {

        System.setProperty("webdriver.chrome.driver",driverPath);
        webDriver = new ChromeDriver();
        Log.printLog(OgameWeb.class.getName(),"Uruchamiam przeglądarkę.");
        webDriver.get(loginPage);
        Log.printLog(OgameWeb.class.getName(),"Uruchomiono stronę Ogame.");
        // Maksymalizacja okna przeglądarki
        webDriver.manage().window().maximize();
    }

    WebDriver getWebDriver() {
        return webDriver;
    }


    public void close()
    {
        if(webDriver.getTitle().equals("OGame"))
        {
            System.out.println(DifferentMethods.fullDateFormat() + "Zamykam przeglądarę ze stroną Ogame.");
            webDriver.quit();
        }
        else
            System.out.println(DifferentMethods.fullDateFormat() + "Tytuł strony: " + webDriver.getTitle());
    }

    String getTitle()
    {
        return  webDriver.getTitle();
    }
}
