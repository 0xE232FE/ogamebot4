package ogame.surowce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SurowceNaPlanecie {

    private static final String resourceBar = "//*[@id=\"resourcesbarcomponent\"]";

    public static int metal(WebDriver w)
    {
        WebElement e =  w.findElement(By.xpath(resourceBar));
        List<WebElement> tmpList = e.findElements(By.tagName("span"));
        StringBuilder tmpString = new StringBuilder();
        for(WebElement tmp : tmpList)
        {
            if(tmp.getAttribute("id").contains("resources_metal"))
            {
                tmpString = new StringBuilder(tmp.getText());
                break;
            }
        }

        String [] tmpTab = tmpString.toString().split("\\.");

        tmpString = new StringBuilder();
        for(String s : tmpTab)
        {
            tmpString.append(s);
        }

        return Integer.valueOf(tmpString.toString());
    }

    public static int krysztal(WebDriver w)
    {
        WebElement e =  w.findElement(By.xpath(resourceBar));
        List<WebElement> tmpList = e.findElements(By.tagName("span"));
        StringBuilder tmpString = new StringBuilder();
        for(WebElement tmp : tmpList)
        {
            if(tmp.getAttribute("id").contains("resources_crystal"))
            {
                tmpString = new StringBuilder(tmp.getText());
                break;
            }
        }
        String [] tmpTab = tmpString.toString().split("\\.");
        tmpString = new StringBuilder();
        for(String s : tmpTab)
        {
            tmpString.append(s);
        }

        return Integer.valueOf(tmpString.toString());
    }

    public static int deuter(WebDriver w)
    {
        WebElement e =  w.findElement(By.xpath(resourceBar));
        List<WebElement> tmpList = e.findElements(By.tagName("span"));
        StringBuilder tmpString = new StringBuilder();
        for(WebElement tmp : tmpList)
        {
            if(tmp.getAttribute("id").contains("resources_deuterium"))
            {
                tmpString = new StringBuilder(tmp.getText());
                break;
            }
        }
        String [] tmpTab = tmpString.toString().split("\\.");
        tmpString = new StringBuilder();
        for(String s : tmpTab)
        {
            tmpString.append(s);
        }

        return Integer.valueOf(tmpString.toString());
    }

    public static int wolnaEnergia(WebDriver w)
    {
        WebElement e =  w.findElement(By.xpath(resourceBar));
        List<WebElement> tmpList = e.findElements(By.tagName("span"));
        StringBuilder tmpString = new StringBuilder();
        for(WebElement tmp : tmpList)
        {
            if(tmp.getAttribute("id").contains("resources_energy"))
            {
                tmpString = new StringBuilder(tmp.getText());
                break;
            }
        }
        String [] tmpTab = tmpString.toString().split("\\.");
        tmpString = new StringBuilder();
        for(String s : tmpTab)
        {
            tmpString.append(s);
        }

        return Integer.valueOf(tmpString.toString());
    }
}

