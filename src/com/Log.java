package com;

public class Log
{
    /**
     * Wypisuje wiersz w konsoli z podaniem aktualnej daty i godziny systemowej.
     * @param log Treść loga jaki będzie wypisany w wierszu konsoli.
     */
    @Deprecated
    public static void printLog(String log)
    {
        System.out.println(DifferentMethods.fullDateFormat() + log);
    }

    /**
     * Stosować w metodach w celu zoobrazowania w którym miejscu metody się znajduję. Przykładowo, spośród którego bloku if
     * została wybrana zmienna.
     * @param log Treść wyświetlanego loga.
     */
    public static void printLog1(String log)
    {
        System.out.println(com.DifferentMethods.fullDateFormat()+"[1] " + log);
    }

    /**
     * Stosować w metodach w celu zoobrazowania w którym miejscu metody się znajduję. Przykładowo, spośród którego bloku if
     * została wybrana zmienna.
     * @param log Treść wyświetlanego loga.
     */
    public static void printLog1(String log,Class c, int wiersz)
    {
        System.out.println(com.DifferentMethods.fullDateFormat()+"[1] " + c.getName() + " - " + wiersz +" - " + log);
    }
    /**
     * Wypisuje wiersz w konsoli z podaniem aktualnej daty i godziny systemowej.
     * @param className Klasa w której wywoływana jest metoda. Scieżka klasy jest wypisywana w konsoli.
     * @param log Treść loga jaki będzie wypisany w wierszu konsoli.
     */
    public static void printLog(String className, String log)
    {
        System.out.println(DifferentMethods.fullDateFormat() + className + " - " + log);
    }
    /**
     * Wypisuje wiersz w konsoli z podaniem aktualnej daty i godziny systemowej.
     * @param className Klasa w której wywoływana jest metoda. Scieżka klasy jest wypisywana w konsoli.
     * @param log Treść loga jaki będzie wypisany w wierszu konsoli.
     */
    public static void printErrorLog(String className, String log)
    {
        System.out.println(DifferentMethods.fullDateFormat() + "ERROR " + className + " - " + log);
    }
}


