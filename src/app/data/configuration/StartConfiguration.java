package app.data.configuration;

import com.Log;
import com.URLFactory;

import java.io.*;

public class StartConfiguration implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static String gameFolderPath = gamePath();
    public static String driverPath = driverPath();

    public StartConfiguration(String gameFolderPath, String driverPath) {
        StartConfiguration.driverPath = driverPath;
        StartConfiguration.gameFolderPath = gameFolderPath;
    }

    /**
     * Pobiera ścieżkę do sterowników.
     */
    private static String driverPath()
    {
        URLFactory urlFactory = new URLFactory(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toString());
        String pathName = urlFactory.disk()+"ogamebot4\\configuration.bot";

        File file = new File(pathName);
        if(file.exists())
        {
            try
            {
                FileInputStream f = new FileInputStream(pathName);
                ObjectInputStream o = new ObjectInputStream(f);

                o.readObject();
                String s = (String) o.readObject();

                o.close();
                f.close();
                return s;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.printLog(Configuration.class.getName(),"Sciezka "+pathName +" nie istnieje.");
        }
        return null;
    }

    /**
     * Pobiera ścieżkę do danych z gry.
     */
    private static String gamePath()
    {
        URLFactory urlFactory = new URLFactory(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toString());
        String pathName = urlFactory.disk()+"ogamebot4\\configuration.bot";

        File file = new File(pathName);
        if(file.exists())
        {
            try
            {
                FileInputStream f = new FileInputStream(pathName);
                ObjectInputStream o = new ObjectInputStream(f);

                String s = (String) o.readObject();

                o.close();
                f.close();
//                Log.printLog1(s,StartConfiguration.class,72);
                return s;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.printLog(Configuration.class.getName(),"Sciezka "+pathName +" nie istnieje.");
        }
        return null;
    }
    public static String getDriverPath() {
        return driverPath;
    }

    public static String getGameFolderPath() {
        return gameFolderPath;
    }
}
