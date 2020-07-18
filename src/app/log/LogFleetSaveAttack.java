package app.log;

import app.czas.Czas;
import app.czas.CzasWykonania;
import app.czas.Data;
import app.data.MatMadFile;
import app.data.accounts.Accounts;
import com.DifferentMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LogFleetSaveAttack
{
    private static String fileNamePart1;
    private static String fileNamePart2;
    private static StringBuilder tmpFileNameToDelete;
    private static List<app.log.Log> logList = new ArrayList<>();
    private static final int DATA_INFO_LENGTH = 75;
    private static CzasWykonania czasWykonania = new CzasWykonania();

    /**
     * Ustawia pierwszy człon nazwy pliku.
     * @param czas Aktualny czas.
     * @param data Aktualna data.
     */
    public static void setFileNamePart1(Czas czas, Data data) {
        LogFleetSaveAttack.fileNamePart1 = data.toStringFileFormat() +"-"+czas.toStringFileFormat();
    }

    /**
     * Ustawia drugi człon nazwy pliku.
     * @param czas Aktualny czas.
     * @param data Aktualna data.
     */
    public static void setFileNamePart2(Czas czas, Data data) {
        tmpFileNameToDelete = new StringBuilder(Accounts.accountDataSavePath()+"log\\"+
                fileNamePart1+"_"+fileNamePart2+".txt");
        LogFleetSaveAttack.fileNamePart2 = data.toStringFileFormat() +"-"+czas.toStringFileFormat();
    }

    /**
     * Tworzy treść loga w formie. [Dane czasowe][Dane klasy z której wywołano metodę][Treść loga]
     * @param log Log.
     * @return Log w pełnej formie.
     */
    public static String log(String className, String log, String time)
    {
        return DifferentMethods.initVariable(time +" "+  className,DATA_INFO_LENGTH)
                + " " + log;
    }

    /**
     * Tworzy treść loga w formie. [Dane czasowe][Dane klasy z której wywołano metodę][Treść loga]
     * @param log Log.
     * @return Log w pełnej formie.
     */
    public static String log(String className, String log)
    {
        return DifferentMethods.initVariable(log,DATA_INFO_LENGTH)
                + " " + className;
    }


    /**
     * Dodaje loga di listy.
     * @param log Treść loga.
     */
    public static void addLog(app.log.Log log)
    {
        logList.add(log);
    }

    /**
     * Zwraca 50 ostatnich logów.
     */
    public static List<String> get50LastLog()
    {
        List<String> tmp = new ArrayList<>();
        for(int i = logList.size()-1; i >= 0; i--)
        {
            tmp.add(logList.get(i).getTime() + " " +logList.get(i).getLogText());
        }
        return tmp;
    }

    /**
     * Zapis logów do pliku.
     */
    public static void save()
    {
        try
        {
            if(MatMadFile.isFolderExists(Accounts.accountDataSavePath()+"log\\"))
            {
                PrintWriter out = new PrintWriter(Accounts.accountDataSavePath()+"log\\"+
                        fileNamePart1+"_"+fileNamePart2+".txt");
                // Zapisywanie linia po linii.
                for(Log l : logList)
                {
                    out.println(log(l.getClassName(), l.getLogText(), l.getTime()));
                }

                out.close();

                com.Log.printLog(LogFleetSaveAttack.class.getName(),
                        "Zapisano logi pod ścieżką" +Accounts.accountDataSavePath()+"log\\"+
                                fileNamePart1+"_"+fileNamePart2+".txt");
            }
            else
            {
                com.Log.printLog(LogFleetSaveAttack.class.getName(),
                        "Scieżka " + Accounts.accountDataSavePath()+"log\\" + " nie istnieje.");
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        File fileToDelete = new File(tmpFileNameToDelete.toString());
        if(fileToDelete.exists())
        {
            fileToDelete.delete();
            com.Log.printLog(LogFleetSaveAttack.class.getName(),
                    "Log pod scieżka " + tmpFileNameToDelete.toString() + " został usunięty.");
        }
    }
    /*
    GETTERS
     */

    public static CzasWykonania getCzasWykonania() {
        return czasWykonania;
    }

    public static List<Log> getLogList() {
        return logList;
    }
}
