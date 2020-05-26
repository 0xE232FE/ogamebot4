package app;


import app.gui.controller.MainController;
import app.leaftask.AttackDetector;
import com.Log;
import org.openqa.selenium.WebDriver;


public class TaskManager
{
    private static  LeafTask[] tasks;
    private MainController mainController;
    private WebDriver webDriver;

    TaskManager(WebDriver webDriver, MainController controller) {
        this.mainController = controller;
        this.webDriver = webDriver;
        initTasks();
    }

    /*
        Index 0 - Attack Detector
     */
    private void initTasks()
    {
        tasks = new LeafTask[]{
                new AttackDetector(webDriver,0,5)
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
