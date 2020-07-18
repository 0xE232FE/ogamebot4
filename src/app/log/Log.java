package app.log;

import com.DifferentMethods;

public class Log
{
    private final String className;
    private final String logText;
    private final String time;

    public Log(String className, String logText) {
        this.className = className;
        this.logText =  logText;
        time = DifferentMethods.fullDateFormat();
    }

    public String getClassName() {
        return className;
    }

    String getLogText() {
        return logText;
    }

    String getTime() {
        return time;
    }
}
