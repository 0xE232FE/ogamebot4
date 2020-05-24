package app;


import app.gui.controller.MainController;
//import app.task.*;
//import app.task.CzasGry;
import com.Log;


public class TaskManager
{
    private Task[] tasks;
    private MainController mainController;
    private int tick = 0;
    private GameClient gameClient;

    TaskManager(GameClient gameClient, MainController controller) {
        this.gameClient = gameClient;
        this.mainController = controller;
//        initTasks();
    }

    /*
        Index 0 - Czas gry
        Index 1 - Pobieranie danych o badaniach.
        Index 2 - Wykonywanie aktywności na planetach.
        Index 3 - Sprawdza na których planetach odbywa się budowa.
        Index 4 - Przechodzi do zakładki Ruch flot i zapisuje dane o flotach.
        Index 5 - Automatyczne wysyłanie transportu surowców do wskazanego celu z pozostałych planet.
     */
//    private void initTasks()
//    {
//        tasks = new Task[]{
//                new Badania(gameClient.getWebDriver(),gameClient.getController(),this,
//                        5*60*60*1000,1,gameClient.getBadania(), gameClient.getCzasGry()),
//                new Aktywnosc(gameClient.getWebDriver(),gameClient.getController(),this, 10*60*1000,
//                        2,gameClient.getCzasGry(),gameClient.getAktywnosc(),gameClient.getPlanety()),
//                new Planety(gameClient.getWebDriver(),gameClient.getController(),this,gameClient.getGameData(),
//                        10*1000,3),
//                new RuchFlot(gameClient.getWebDriver(),gameClient.getController(), this, 0,4,
//                        gameClient.getCzasGry(), gameClient.getLoty()),
//                new Autotransport(gameClient.getWebDriver(),gameClient.getController(),this,1000,5,
//                        gameClient.getAutotransportList(),gameClient.getLoty(),gameClient.getCzasGry()),
//                new Surowce(gameClient.getWebDriver(),gameClient.getController(),this,gameClient.getGameData(),
//                        60*60*1000,6),
//                new Budynki(gameClient.getWebDriver(),gameClient.getController(),this,gameClient.getGameData(),
//                        30*60*100,7),
//                new Wydobycie(gameClient.getWebDriver(),gameClient.getController(),this,gameClient.getGameData(),
//                        60*60*100,8)
//        };
//        mainController.setTaskOnOff(tasks.length);
////        tasks = new Task[]{
////                new SpySpam(gameClient.getWebDriver(),gameClient.getController(),this,0,0)
////        };
//    }

    private boolean isFreeSpace()
    {
        for(Task t : tasks)
        {
            if(t.isRun())
                return false;
        }
        return true;
    }

    private void stopAll()
    {
        for(Task t : tasks)
        {
            t.setRun(false);
        }
    }

    public void startAll()
    {
        for(Task t : tasks)
        {
            t.setRun(true);
        }
    }

    public  void startTask(int taskIndex)
    {
       if(taskIndex <= tasks.length-1)
           tasks[taskIndex].setRun(true);
       else
           Log.printErrorLog(TaskManager.class.getName(),"Podany taskIndex wykracza poza długość tablicy." +
                   " Dlugość tablicy to " + (tasks.length-1));
    }

    public  void stopTask(int taskIndex)
    {
        if(taskIndex <= tasks.length-1)
            tasks[taskIndex].setRun(false);
        else
            Log.printErrorLog(TaskManager.class.getName(),"Podany taskIndex wykracza poza długość tablicy." +
                    " Dlugość tablicy to " + (tasks.length-1));
    }

//    void tick()
//    {
//        if(mainController.isPause())
//            stopAll();
//        else
//        {
//            if(isFreeSpace())
//            {
//                int a = tick % tasks.length;
//                Log.printLog(TaskManager.class.getName(),"Tick = "+tick +" % " + tasks.length + " = " + a+" Task State : " +
//                        (mainController.getTaskOnOff()[tasks[a].getIndex()] == true ? "ON":"OFF"));
//                tasks[a].setRun(true);
//                Waiter.sleep(10,300);
//                tick ++;
//            }
//            else
//            {
//                if(tick == 0)
//                    stopAll();
//            }
//        }
//        Waiter.sleep(25,25);
//    }

    Task[] getTasks() {
        return tasks;
    }
}
