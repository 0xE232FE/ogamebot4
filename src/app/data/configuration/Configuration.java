package app.data.configuration;

import com.Log;
import com.URLFactory;

import java.io.*;

public class Configuration
{
    public static boolean firstConfiguration = load();

//    private static boolean isFirstConfiguration()
//    {
//        URLFactory urlFactory = new URLFactory(Configuration.class.getClassLoader().getResource("").toString());
//        urlFactory.disk();
//        Log.printLog(Configuration.class.getName(), urlFactory.disk());
//        return load();
//    }

    /**
     * Wczytuje z pliku inforamcję czy wykonano konfigurację pierszego uruchomienia.
     */
    private static boolean load()
    {
        URLFactory urlFactory = new URLFactory(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toString());
        String pathName = urlFactory.disk()+"ogamebot4\\firtStart.bol";

        File file = new File(pathName);
        if(file.exists())
        {
            try
            {
                FileInputStream f = new FileInputStream(pathName);
                ObjectInputStream o = new ObjectInputStream(f);

                boolean b = (boolean) o.readObject();

                o.close();
                f.close();
                return b;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.printLog(Configuration.class.getName(),"Sciezka "+pathName +" nie istnieje.");
        }
        return true;
    }

    /**
     * Zapisuje informację do pliku, że dokonano kofiguracji pierwszego uruchomienia
     */
    public static void save()
    {
        URLFactory urlFactory = new URLFactory(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toString());
        String pathName = urlFactory.disk()+"ogamebot4\\firtStart.bol";

        try
        {
            FileOutputStream f = new FileOutputStream(new File(pathName));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(false);
            o.close();
            f.close();
            Log.printLog(Configuration.class.getName(),"Zapisano wskaźnik pod ścieżką "+pathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Zapisuje dane do pliku, o kofiguracji pierwszego uruchomienia
     */
    public static void saveConfiguration(String gamePath, String driverPath)
    {
        URLFactory urlFactory = new URLFactory(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toString());
        String pathName = urlFactory.disk()+"ogamebot4\\configuration.bot";

        try
        {
            FileOutputStream f = new FileOutputStream(new File(pathName));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(gamePath);
            o.writeObject(driverPath);
            o.close();
            f.close();
            Log.printLog(Configuration.class.getName(),"Zapisano wskaźnik pod ścieżką "+pathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
