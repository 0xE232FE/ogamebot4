package app;

import app.data.accounts.Accounts;
import com.DifferentMethods;
import com.Log;
import ogame.GameTime;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;


class Login
{
    private WebDriver webDriver;
    Login(Run run) {

        webDriver = run.getOgameWeb().getWebDriver();
        //klika na zakładkę Login.
        WebElement element = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/ul/li[1]"));
        element.click();
        //wpisuje login/nazwę gracz.
        setLogin(webDriver, Accounts.getSelected().getLogin());
        //wpisuje hasło.
        setPassword(webDriver,Accounts.getSelected().getPassword());
        //sprawdza czy pojawiła się reklama i ją zamyka.
        closeAd(webDriver);
        //klika przycisk login.
        pressLogin(webDriver);
        //po załadowaniu sie nowej strony klika przycisk graj.
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

    private void closeAd(WebDriver webDriver)
    {
        int counter = 0;
        while(true)
        {
            boolean a = GameClient.isElementPresent(By.className("openX_int_closeButton"),webDriver);
            if(a)
            {
                Log.printLog(Login.class.getName(),"Znaleziono reklamę, zamykam.");
                WebElement x = webDriver.findElement(By.className("openX_int_closeButton"));
                x.findElement(By.tagName("a")).click();
                return;
            }
            counter++;

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if(counter > 10)
            {
//                Log.printLog(Login.class.getName(),"Nie znaleziono reklamy.");
                return;
            }
        }

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
        while (true)
        {
            boolean a = isElementPresent(By.id("myAccounts"));
            if(a)
            {
                WebElement playButton = webDriver.findElement(By.id("myAccounts"));
                if(playButton.isDisplayed())
                    playButton.findElement(By.tagName("button")).click();

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

