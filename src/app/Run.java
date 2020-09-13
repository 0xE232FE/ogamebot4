package app;

import app.data.accounts.Accounts;
//import app.data.configuration.Configuration;
import app.data.configuration.Configuration;
import app.data.configuration.StartConfiguration;
//import app.gui.Controller;
import app.gui.controller.MainController;
//import app.gui.data.Account;
import app.log.LogFleetSaveAttack;
import com.DifferentMethods;
import com.Log;
import com.Waiter;
//import ogame.OgameWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class Run extends Task
{

    private OgameWeb ogameWeb;
    private GameClient gameClient;
    private boolean first = true;
    private String serverName;
    private MainController mainController;

    public Run(MainController mainController)
    {
        this.mainController = mainController;
        serverName = Accounts.getSelected().getSerwer() + " OGame";
        setRun(false);
        this.mainController = mainController;
        ogameWeb = new OgameWeb(Accounts.getSelected().getWeb(), StartConfiguration.driverPath);
        setThread(new Thread(this));
        startThread();
    }

    OgameWeb getOgameWeb() {
        return ogameWeb;
    }

    @Override
    public void run() {

        Log.printLog(Run.class.getName(),String.valueOf(isRun()));
        while(isRun())
        {
            if(ogameWeb.getTitle().equals("OGame"))
            {
                if(ogameWeb.getWebDriver().getTitle().equals(serverName))
                {
                    Log.printLog(Run.class.getName(),"Zalogowałem się ponownie do gry na serwerze " + ogameWeb.getWebDriver().getTitle());
                    LogFleetSaveAttack.addLog(new app.log.Log(Run.class.getName(),"Zalogowałem się ponownie do gry na serwerze: "
                            + ogameWeb.getWebDriver().getTitle()));
                }
                else
                {
                    if(first)
                        new Login(this);
                    else
                    {
                        reLogin(ogameWeb.getWebDriver());
                        LogFleetSaveAttack.addLog(new app.log.Log(Run.class.getName(),"Zalogowałem się ponownie do gry na serwerze: "
                                + ogameWeb.getWebDriver().getTitle()));
                    }
                }
            }
            else if(ogameWeb.getWebDriver().getTitle().equals(serverName) && gameClient == null)
            {
                gameClient = new GameClient(ogameWeb.getWebDriver(), mainController);
                Log.printLog(Run.class.getName(),"Zalogowałem się do gry na serwerze: " + ogameWeb.getWebDriver().getTitle());
                LogFleetSaveAttack.addLog(new app.log.Log(Run.class.getName(),"Zalogowałem się do gry na serwerze: "
                        + ogameWeb.getWebDriver().getTitle()));
                gameClient.setRun(true);

                if(Configuration.testModule)
                    mainController.setTestModule();
            }

            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    void setFirst() {
        this.first = false;
    }

    private void reLogin(WebDriver w)
    {
        Waiter.sleep(250,250);
        Log.printLog(Run.class.getName(),"Wykryto wylogowanie. Rozpoczynam proces ponownego zalogowania się.");
        LogFleetSaveAttack.addLog(new app.log.Log(Run.class.getName(),"Wykryto wylogowanie. Rozpoczynam proces ponownego zalogowania się."));
        pressPlay(w);
        pressSecondPlay(w);
        zmienZakladke(w,serverName);
    }

    private void pressPlay(WebDriver w)
    {
        w.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/div[2]/div/a/button")).click();
        Log.printLog(Run.class.getName(),"Klikam GRAJ w pierwszym oknie.");
        Waiter.sleep(250,250);
    }

    private void pressSecondPlay(WebDriver w)
    {
        while (true)
        {
            boolean a = isElementPresent(w,By.id("myAccounts"));
            if(a)
            {
                WebElement playButton = w.findElement(By.id("myAccounts"));
                if(playButton.isDisplayed())
                    playButton.findElement(By.tagName("button")).click();

                break;
            }
        }
        Log.printLog(Run.class.getName(),"Klikam GRAJ w drugim oknie.");
    }

    private  void zmienZakladke(WebDriver w, String nazwaZakladki)
    {
        boolean b = w.getTitle().equals("OGame");
        while(b)
        {
            Log.printLog(Run.class.getName(),"Page title: " + w.getTitle());
            Set<String> s = w.getWindowHandles();

            for(String child : s)
            {
                w.switchTo().window(child);
                if(w.getTitle().equals(nazwaZakladki))
                {
                    b = w.getTitle().equals("OGame");
                    Log.printLog(Run.class.getName(),"Przełączyłem na zakładkę: " + w.getTitle());
                }

            }
        }
    }

    private boolean isElementPresent(WebDriver w, By by)
    {
        try
        {
            w.findElement(by);
            System.out.println(DifferentMethods.fullDateFormat() + "Widoczny button GRAJ.");
            return true;
        }
        catch (NoSuchElementException e)
        {
//            System.out.println(DifferentMethods.fullDateFormat() + "Nie widoczny button GRAJ.");
            return false;
        }

    }
}
