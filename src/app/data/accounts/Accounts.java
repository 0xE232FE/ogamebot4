package app.data.accounts;

import app.data.configuration.Configuration;
import app.data.configuration.StartConfiguration;
import com.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Accounts
{
    private static Account selected = loadSelected();
    private static List<Account> accountList = load();

    public static void setSelected(Account selected) {
        Accounts.selected = selected;
    }

    public static Account getSelected() {
        return selected;
    }

    /**
     * Dodaje nowe konto do listy, jeżeli nie znajduje się na niej.
     * @param a Konto.
     */
    public static void addAccount(Account a)
    {
        if(!isDuplicated(a))
            accountList.add(a);
        else
            Log.printLog(Accounts.class.getName(),"");
    }

    /**
     * Sprawdza czy wprowadzone konto znajduje się na liście.
     * @param a Konto.
     */
    private static boolean isDuplicated(Account a)
    {
        if(accountList.size() > 0)
        {
            for(Account tmp : accountList)
            {
                if(tmp.equals(a))
                    return true;
            }
        }
        else
            return false;

        return false;
    }

    /**
     * Zapisuje informację do pliku o kontach.
     */
    public static void save()
    {
        String pathName = StartConfiguration.gameFolderPath+"\\accounts.bot";

        try
        {
            FileOutputStream f = new FileOutputStream(new File(pathName));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(accountList);
            o.close();
            f.close();
            Log.printLog(Configuration.class.getName(),"Zapisano konta pod ścieżką "+pathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zapisuje informację o ostatnio wybranym koncie.
     */
    public static void saveSelected()
    {
        String pathName = StartConfiguration.gameFolderPath+"\\accounts_selected.bot";

        try
        {
            FileOutputStream f = new FileOutputStream(new File(pathName));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(selected);
            o.close();
            f.close();
            Log.printLog(Configuration.class.getName(),"Zapisano zalogowane konto pod ścieżką "+pathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wczytuje z pliku inforamcję czy wykonano konfigurację pierszego uruchomienia.
     */
    private static List<Account> load()
    {

        String pathName = StartConfiguration.gameFolderPath+"\\accounts.bot";

        File file = new File(pathName);
        if(file.exists())
        {
            try
            {
                FileInputStream f = new FileInputStream(pathName);
                ObjectInputStream o = new ObjectInputStream(f);

                List<Account> list = (List<Account>) o.readObject();
                Log.printLog(Accounts.class.getName(),"Sciezka "+pathName +" istnieje. Poprawnie wczytano listę kont.");
                o.close();
                f.close();
                return list;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.printLog(Accounts.class.getName(),"Sciezka "+pathName +" nie istnieje. Nie wczytano listy kont.");
        }
        return new ArrayList<>();
    }

    /**
     * Wczytuje z pliku ostatnio zalogowane konto..
     */
    private static Account loadSelected()
    {

        String pathName = StartConfiguration.gameFolderPath+"\\accounts_selected.bot";

        File file = new File(pathName);
        if(file.exists())
        {
            try
            {
                FileInputStream f = new FileInputStream(pathName);
                ObjectInputStream o = new ObjectInputStream(f);

                Account a = (Account) o.readObject();
                Log.printLog(Accounts.class.getName(),"Sciezka "+pathName +" istnieje. Poprawnie wczytano ostatnio zalogowane konto.");
                o.close();
                f.close();
                return a;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.printLog(Accounts.class.getName(),"Sciezka "+pathName +" nie istnieje. Nie wczytano ostatnio wybranego konta.");
        }
        return null;
    }
}
