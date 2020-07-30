package com;

public class Log
{
    public static int EKSPEDYCJA_TYPE = 1;
    private static final int [] TYPE_TO_PRINT ={
            -1,
        EKSPEDYCJA_TYPE,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
    };
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
     * Stosować w metodach w celu zoobrazowania w którym miejscu metody się znajduję. Przykładowo, spośród którego bloku if
     * została wybrana zmienna.
     * @param log Treść wyświetlanego loga.
     */
    public static void printLog1(String log,Class c,String nazwaMetody, int wiersz)
    {
        System.out.println(com.DifferentMethods.fullDateFormat()+"[1] [" + c.getName() + "->"+ nazwaMetody+ " - " + wiersz
                +"] - " + log);
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
     * Wypisuje wiersz w konsoli z podaniem aktualnej daty i godziny systemowej oraz z określeniem typu Loga. Jeżeli typ
     * loga jest uruchpmiony, to w konsoli zostanie wyświetlony log.
     * @param className Klasa w której wywoływana jest metoda. Scieżka klasy jest wypisywana w konsoli.
     * @param log Treść loga jaki będzie wypisany w wierszu konsoli.
     * @param type Typ loga. Pole statyczne w klasie Log.
     */
    public static void printLog(Class className, String log, int type)
    {
        if(TYPE_TO_PRINT[type] == type)
            System.out.println(DifferentMethods.fullDateFormat() + className.getName() + " - " + log);
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


