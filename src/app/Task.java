package app;

//import app.gamedata.GameData;
//import app.gui.Controller;
import app.gui.controller.MainController;
import com.Log;
import org.openqa.selenium.WebDriver;

public class Task implements Runnable {


    private boolean run = true;

    private WebDriver w;
    private MainController controller;
    private Thread thread;
//    private TaskManager taskManager;
//    private GameData gameData;
    private long lastTimeExecute = 0;
    private long sleep = 0;
    private int index = -1;
    private String name = "Empty name";
//
//    public Task(WebDriver w, Controller controller, TaskManager taskManager, GameData gameData,
//                long sleep, int index, String name) {
//        this.w = w;
//        this.controller = controller;
//        this.taskManager = taskManager;
//        this.gameData = gameData;
//        this.sleep = sleep;
//        this.index = index;
//        this.name = name;
//    }
//
//    public Task(WebDriver w, Controller controller, TaskManager taskManager, long sleep, int index, String name) {
//        this.w = w;
//        this.controller = controller;
//        this.taskManager = taskManager;
//        this.sleep = sleep;
//        this.index = index;
//        this.name = name;
//    }
//
//    public Task(WebDriver w, Controller controller, TaskManager taskManager, long sleep, int index) {
//        this.w = w;
//        this.controller = controller;
//        this.taskManager = taskManager;
//        this.sleep = sleep;
//        this.index = index;
//    }
//
//    public Task(WebDriver w, Controller controller, TaskManager taskManager, long sleep) {
//        this.w = w;
//        this.controller = controller;
//        this.taskManager = taskManager;
//        this.sleep = sleep;
//    }
//
//    public Task(WebDriver w, Controller controller, TaskManager taskManager) {
//        this.w = w;
//        this.controller = controller;
//        this.taskManager = taskManager;
//    }
//
//    public Task(WebDriver w, Controller controller) {
//        this.w = w;
//        this.controller = controller;
//    }

    Task()
    {

    }

    public Task(WebDriver w, String name) {
        this.name = name;
        this.w = w;
    }

    public Task(WebDriver w, MainController controller) {
        this.w = w;
        this.controller = controller;
    }

    public Task(WebDriver w) {
        this.w = w;
    }

//    /**
//     * Zwraca wartość, czy Task jest uruchomiony.
//     */
//    public boolean isRun() {
//        return index == -1 ? run : (run && controller.getTaskOnOff()[index]);
//    }

    /**
     * Zwraca wartość, czy Task jest uruchomiony.
     */
    public boolean isRun() {
        return run;
    }

    /**
     * Ustawia wartość określającą czy Task jest uruchomiony. false - OFF, true - ON
     */
    public void setRun(boolean run) {
        this.run = run;
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
     * @return Głowny Controller
     */
    protected MainController getController() {
        return controller;
    }

    /**
     * Głwona metoda w ktorej wykonywana jest cala czynnosc Task'a
     */
    @Override
    public void run() {

    }

    /**
     *
     * @return Wątek
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Ustawienie wątku.
     * @param thread Wątek.
     */
    protected void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * Uruchamianie wątku.
     */
    protected void startThread()
    {
        if(thread != null)
            thread.start();
        else
            Log.printLog(Task.class.getName(),"Thread is null.");
    }

//    /**
//     *
//     * @return TaskManager
//     */
//    protected TaskManager getTaskManager() {
//        return taskManager;
//    }

//    /**
//     * Ustaw TaskManager
//     * @param taskManager Taskmanager
//     */
//    public void setTaskManager(TaskManager taskManager) {
//        this.taskManager = taskManager;
//    }

    /**
     * @return Czas ostatniego wykonania Taska.
     */
    public long getLastTimeExecute() {
        return lastTimeExecute;
    }

    /**
     * Ustaw czas ostatniego wykonania Taska.
     * @param lastTimeExecute
     */
    protected void setLastTimeExecute(long lastTimeExecute) {
        this.lastTimeExecute = lastTimeExecute;
    }

    /**
     *
     * @return Czas określają co ile ms ma Task być wykonywany
     */
    public long getSleep() {
        return sleep;
    }

    /**
     * Ustaw czas co ile wykonać wątek.
     * @param sleep Czas w ms.
     */
    public void setSleep(long sleep) {
        this.sleep = sleep;
    }

    /**
     * Sprawdza czy czas podany w parametrze (aktualny czas systemu/gry) odjęty od czasu ostatniego wykonania Taska,
     * jest większy od czasu określającego co ile uruchomić Task.
     * @param currentTime
     * @return
     */
    protected boolean isSleepTimeOut(long currentTime)
    {
        return  (currentTime - lastTimeExecute) > sleep;
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

//    /**
//     *
//     * @return Klasa przechowująca dane z gry.
//     */
//    public GameData getGameData() {
//        return gameData;
//    }
}


