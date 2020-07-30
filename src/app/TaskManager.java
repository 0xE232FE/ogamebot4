package app;

import app.leaftask.*;
import com.Log;
import org.openqa.selenium.WebDriver;


public class TaskManager
{
    private static  LeafTask[] tasks;
    private WebDriver webDriver;

    TaskManager(WebDriver webDriver) {
        this.webDriver = webDriver;
        initTasks();
    }

    /**
     * Metoda inicjalizująca Taski. Task który powinien zostać uruchomiony ma się znajdować w ciele tej metody.
     */
    private void initTasks()
    {
        tasks = new LeafTask[]{
                new Planety(webDriver,0,60*1000),
                new AttackDetector(webDriver,1,5),
                new FleetSaveAttack(webDriver,2,30*1000),
                new RuchFlot(webDriver,3,0),
                new Ekspedycje(webDriver,4,30*1000)
        };
        Log.printLog(TaskManager.class.getName(), "Utworzono " +tasks.length + " Tasków.");
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

    public static LeafTask[] getTasks() {
        return tasks;
    }
}
