package app.data;

import com.Log;

import java.io.File;

public class MatMadFile
{
    /**
     * Sprawdza czy istnieje plik pod wskazaną ścieżką.
     * @param path Sciezka plik.
     * @return Jeżeli istnie zwróci true.
     */
    public static boolean isFileExists(String path)
    {
        File file = new File(path);
        if(file.exists())
            return true;
        else
        {
            Log.printLog(MatMadFile.class.getName(),"Plik o podanej ścieżce nie istnieje. "+path);
            return false;
        }
    }

    /**
     * Sprawdza czy istnieje folder pod wskazaną ścieżką.
     * @param path Sciezka foldera.
     * @return Jeżeli istnie zwróci true.
     */
    public static boolean isFolderExists(String path)
    {
        File file = new File(path);
        if(file.exists())
            return true;
        else
        {
            Log.printLog(MatMadFile.class.getName(),"Folder o podanej ścieżce nie istnieje. "+path);
            file.mkdir();
            Log.printLog(MatMadFile.class.getName(),"U tworzyłem folder o podanej ścieżce "+path);
            return false;
        }
    }
}
