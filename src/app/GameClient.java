package app;

import app.gui.controller.MainController;
import app.tasks.BotLogic;
import app.tasks.CzasGry;
import org.openqa.selenium.*;


public class GameClient extends Task
{
    private WebDriver webDriver;
    private MainController mainController;

    private CzasGry czasGry;

    private GUIUpdater guiUpdater;
    private TaskManager taskManager;
    private BotLogic botLogic;

    GameClient(WebDriver webDriver, MainController mainController)
    {
        guiUpdater = new GUIUpdater(webDriver,mainController);
        taskManager = new TaskManager(webDriver,mainController);
        setRun(false);
        this.webDriver = webDriver;
        this.mainController = mainController;
        setThread(new Thread(this));
        startThread();

        czasGry = new CzasGry(webDriver);
        czasGry.setRun(true);

        botLogic = new BotLogic(webDriver);
        botLogic.setRun(true);
    }

    @Override
    public synchronized void run() {

        while(true)
        {
            if(isRun())
            {

            }
        }
    }

    WebDriver getWebDriver() {
        return webDriver;
    }

    static boolean isElementPresent(By by, WebDriver webDriver)
    {
        try
        {
            webDriver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

    }

    public static boolean isElementPresent (By by, WebElement webElement)
    {
        try
        {
            webElement.findElement(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

    }

    public static void scrollToElement(WebDriver w, WebElement e)
    {
        JavascriptExecutor js = (JavascriptExecutor) w;
        js.executeScript("arguments[0].scrollIntoView();", e);
    }

    public static void scrollToBottom(WebDriver w)
    {
        JavascriptExecutor js = (JavascriptExecutor) w;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToTop(WebDriver w)
    {
        JavascriptExecutor js = (JavascriptExecutor) w;
        js.executeScript("window.scrollTo(0, 0)");
    }
}
