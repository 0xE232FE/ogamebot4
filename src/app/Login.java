package app;

import app.data.accounts.Accounts;
import com.DifferentMethods;
import com.Log;
import com.Waiter;
import org.openqa.selenium.*;

import java.util.Set;


class Login
{
    private WebDriver webDriver;
    Login(Run run) {
        webDriver = run.getOgameWeb().getWebDriver();

        closeAd();
        //Klika zaakceptuj cookies
        boolean bool = acceptCookies();
        while(!bool)
        {
            bool = acceptCookies();
        }
        //klika na zakładkę Login.
        WebElement element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/ul/li[1]"));
        //Klika zaakceptuj cookies
        element.click();
        //wpisuje login/nazwę gracz.
        setLogin(webDriver, Accounts.getSelected().getLogin());
        //wpisuje hasło.
        setPassword(webDriver,Accounts.getSelected().getPassword());
        //sprawdza czy pojawiła się reklama i ją zamyka.
        closeAd();
        //klika przycisk login.
        pressLogin(webDriver);
        //po załadowaniu sie nowej strony klika przycisk graj.
        GameClient.scrollToBottom(webDriver);
        pressPlay(webDriver);
        //po zaladowaniu się strony z kontami gracza na serwerach klika przycisk graj.
        pressSecondPlay(webDriver);

        String parent = webDriver.getWindowHandle();
        //Pętla oczekuję na otwatcie nowej zakładki, po otwarciu przełacza na nowa zakładkę.
        while(webDriver.getTitle().equals("OGame"))
        {
            System.out.println(DifferentMethods.fullDateFormat() + "Page title: " + webDriver.getTitle());
            Set<String> s = webDriver.getWindowHandles();

            for(String child : s)
            {
                if(!child.equals(parent))
                {
                    webDriver.switchTo().window(child);
                    System.out.println(DifferentMethods.fullDateFormat() + "Przełączyłem na zakładkę: " + webDriver.getTitle());
                }
            }
        }
        //Zaznacza w klasie Run, że to było piersze logowanie. W momencie wylogowania z serwera klasa Run rozpoczyna
        // proces relogowania, zaczynając od strony z kontami gracza na serwerach
        run.setFirst();
    }

    /*
    Zamyka wyświetloną reklamę w oknie wpisywania loginu lub w oknie wybierania serwera.
     */
    private boolean closeAd()
    {
        try
        {
            WebElement x = OgameWeb.webDriver.findElement(By.className("openX_int_closeButton"));
            if(x.isDisplayed())
                Log.printLog(Login.class.getName(),"Reklama jest widoczna.");
            else
                Log.printLog(Login.class.getName(),"Reklama jest nie widoczna.");
            x.findElement(By.tagName("a")).click();
            Log.printLog(Login.class.getName(),"Znaleziono rekląmę. Kliknięto zamknij.");
            return true;
        }
        catch (Exception e)
        {
            Log.printErrorLog(Login.class.getName(),"Nie znaleziono reklamy.");
        }
        return false;
    }

    private boolean acceptCookies()
    {
        try
        {
            WebElement x = webDriver.findElement(By.xpath("/html/body/div[3]/div/div/span[2]/button[2]"));
            if(x.isDisplayed())
            {
                Log.printLog(Login.class.getName(),"Zaaakceptuj cookie jest widoczna.");
                x.click();
                Log.printLog(Login.class.getName(),"Znaleziono Zaaakceptuj cookie. Kliknięto zamknij.");
                return true;
            }
            else
                Log.printLog(Login.class.getName(),"Zaaakceptuj cookie nie widoczna.");

        }
        catch (Exception e)
        {
            Log.printErrorLog(Login.class.getName(),"Nie znaleziono Zaaakceptuj cookie.");
        }
        return false;
    }

    private void setLogin(WebDriver webDriver, String login)
    {

//        WebElement loginTextField = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[1]/div/input"));
        WebElement loginTextField = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/div/form/div[1]/div/input"));
        loginTextField.sendKeys(login);
    }

    private void setPassword(WebDriver webDriver, String password)
    {
//        WebElement passwordTextField = webDriver.findElement((By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[2]/div/input")));
        WebElement passwordTextField = webDriver.findElement((By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/div/form/div[2]/div/input")));
        passwordTextField.sendKeys(password);
    }

    private void pressLogin(WebDriver webDriver)
    {
                WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/div/form/p/button[1]"));
        loginButton.click();
    }

    private void pressPlay(WebDriver webDriver)
    {
        while (true)
        {
            boolean a = isElementPresent(By.id("joinGame"));
            if(a)
            {
                WebElement playButton = webDriver.findElement(By.id("joinGame"));
                GameClient.scrollToElement(webDriver,playButton);
                if(playButton.isDisplayed())
                    playButton.findElement(By.tagName("a")).click();

                break;
            }
        }
    }

    private void pressSecondPlay(WebDriver webDriver)
    {
        int counter = 0;
        while (true)
        {
            boolean a = isElementPresent(By.id("myAccounts"));
            if(a)
            {
                while(!closeAd())
                {
                    if(counter > 10)
                    {
                        Log.printLog(Login.class.getName(),"Przekroczono limit prób zamknięcia reklamy.");
                        break;
                    }

                    if(counter == 5)
                    {
                        webDriver.navigate().refresh();
                        Log.printLog(Login.class.getName(),"Odświeżam stronę.");
                        Waiter.sleep(500,1500);
                    }

                    Waiter.sleep(50,50);
                    counter++;
                }
                WebElement playButton = webDriver.findElement(By.id("myAccounts"));
                if(playButton.isDisplayed())
                {
                    playButton.findElement(By.tagName("button")).click();
                }
                break;
            }
        }
    }

    private boolean isElementPresent(By by)
    {
        try
        {
            webDriver.findElement(by);
            System.out.println(DifferentMethods.fullDateFormat() + "Widoczny button GRAJ.");
            return true;
        }
        catch (NoSuchElementException e)
        {
            System.out.println(DifferentMethods.fullDateFormat() + "Nie widoczny button GRAJ.");
            return false;
        }

    }
}

