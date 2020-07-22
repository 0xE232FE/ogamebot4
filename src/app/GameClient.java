package app;

import app.gui.controller.MainController;
import app.tasks.BotLogic;
import app.tasks.CloseAd;
import app.tasks.CzasGry;
import org.openqa.selenium.*;


public class GameClient extends Task
{
    private WebDriver webDriver;
//    private MainController mainController;

    private CzasGry czasGry;

//    private GUIUpdater guiUpdater;
//    private TaskManager taskManager;
    private BotLogic botLogic;

    GameClient(WebDriver webDriver, MainController mainController)
    {
//        GUIUpdater guiUpdater = new GUIUpdater(webDriver,mainController);
        new GUIUpdater(webDriver,mainController);
//        TaskManager taskManager = new TaskManager(webDriver);
        new TaskManager(webDriver);
        // Zamykanie reklam
        new CloseAd(webDriver);
        setRun(false);
        this.webDriver = webDriver;
//        this.mainController = mainController;
        setThread(new Thread(this));
        startThread();

        czasGry = new CzasGry(webDriver);
        czasGry.setRun(true);

        botLogic = new BotLogic(webDriver);
        botLogic.setRun(true);

        mainController.setBotLogic(botLogic);
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

    /**
     * Przesuwa stronę do wskazanego elementu..
     */
    public static void scrollToElement(WebDriver w, WebElement e)
    {
        JavascriptExecutor js = (JavascriptExecutor) w;
        js.executeScript("arguments[0].scrollIntoView();", e);
    }

    /**
     * Przesuwa stronę na dół.
     */
    public static void scrollToBottom(WebDriver w)
    {
        JavascriptExecutor js = (JavascriptExecutor) w;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Przesuwa stronę na góre.
     */
    public static void scrollToTop(WebDriver w)
    {
        JavascriptExecutor js = (JavascriptExecutor) w;
        js.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Klika w element
     */

    public static void clickOnElement(WebDriver w,WebElement e)
    {
        JavascriptExecutor executor = (JavascriptExecutor)w;
        executor.executeScript("arguments[0].click();", e);
    }

    /**
     * Ukrywa element
     */

    public static void hideElement(WebDriver w,WebElement e)
    {
        JavascriptExecutor executor = (JavascriptExecutor)w;
        executor.executeScript("arguments[0].style.visibility='hidden'", e);
    }
}
