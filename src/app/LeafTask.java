package app;

import com.Waiter;
import org.openqa.selenium.WebDriver;

public class LeafTask implements Execute
{
    private boolean run = false;
    private WebDriver w;
    private long lastTimeExecute = 0;
    private long sleep;
    private int index;
    private String name;

    public LeafTask(WebDriver w, int index, long sleep, String name) {
        this.w = w;
        this.sleep = sleep;
        this.name = name;
        this.index = index;
    }

    /*
    EXECUTING METHODS
     */

    /**
     * Sprawdza czy czas podany w parametrze (aktualny czas systemu/gry) odjęty od czasu ostatniego wykonania Taska,
     * jest większy od czasu określającego co ile uruchomić Task.
     * @return Jeżeli czas jest większy to zwróci <b>true</b>.
     */
    protected boolean isSleepTimeOut(long currentTime)
    {
        return  (currentTime - lastTimeExecute) > sleep;
    }

    /**
     * Ciało wykonawcze zadania
     */
    @Override
    public void execute() {
        Waiter.sleep(15,15);
    }

    /*
    SETTERS
     */
    /**
     * Ustawia wartość określającą czy Task jest uruchomiony. false - OFF, true - ON
     */
    public void setRun(boolean run) {
        this.run = run;
    }

    /**
     * Ustaw czas ostatniego wykonania Taska.
     * @param lastTimeExecute Czas w milisekundach.
     */
    protected void setLastTimeExecute(long lastTimeExecute) {
        this.lastTimeExecute = lastTimeExecute;
    }



    /**
     * Ustaw czas co ile wykonać wątek.
     * @param sekundy Czas w sekundach.
     */
    public void setSleep(long sekundy) {
        this.sleep = sekundy*1000;
    }
    /*
    GETTERS
     */
    /**
     * Zwraca wartość, czy Task jest uruchomiony.
     */
    public boolean isRun() {
        return run;
    }

    /**
     *
     * @return WebDriver
     */
    public WebDriver getW() {
        return w;
    }

    /**
     *
     * @return Index określający task - powinien być niepowtarzalny.
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @return Nazwa wątku.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Czas ostatniego wykonania Taska.
     */
    public long getLastTimeExecute() {
        return lastTimeExecute;
    }



    /**
     *
     * @return Czas określający co ile ms ma Task być wykonany.
     */
    public long getSleep() {
        return sleep;
    }

}
