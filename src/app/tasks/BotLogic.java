package app.tasks;

import app.Task;
import app.TaskManager;
import com.Waiter;
import org.openqa.selenium.WebDriver;

public class BotLogic extends Task {

    public BotLogic(WebDriver w)
    {
        super(w);
        setThread(new Thread(this));
        startThread();
    }

    @Override
    public void run() {
        while(true)
        {
            if(isRun())
            {
                TaskManager.getTasks()[0].execute();
                TaskManager.getTasks()[1].execute();
                TaskManager.getTasks()[2].execute();
                TaskManager.getTasks()[3].execute();
                TaskManager.getTasks()[4].execute();
                TaskManager.getTasks()[5].execute();
            }
            Waiter.sleep(10,40);
        }
    }
}
