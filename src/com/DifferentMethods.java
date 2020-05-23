package com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DifferentMethods
{
    /**
     * Wypisuje całą zawartość tabeli w oddzielnych liniach. Każdą linia jest określona indexem z tabeli o 1 wartość
     * większą.
     * @param t Tabela.
     */
    public static void printTable(String [] t)
    {
        for(int i = 0; i < t.length; i++)
        {
            System.out.println((i+1)+"-"+t[i]);
        }
    }

    /**
     * Uzupełnia wartość zmiennej pustymi znakami do określonej długości.
     * @param variable Zmienna.
     * @param length Długość zmiennej.
     * @return Zmienną o określonej długości.
     */
    public static String initVariable(String variable, int length)
    {
        if(variable == null)
        {
            StringBuilder s = new StringBuilder("null");
            while(s.length() < length)
            {
                s.append(" ");
            }
            return s.toString();
        }

        StringBuilder stringBuilder = new StringBuilder(variable);
        while(stringBuilder.length() < length)
        {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Metoda zwracająca aktualną datę.
     * @return Aktualna data w formacie dd.mm.yyyy
     */
    private static String date()
    {
        Calendar cal = Calendar. getInstance();
        Date date= cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return dateFormat.format(date);
    }
    /**
     * Metoda zwracająca aktualną datę.
     * @return Aktualna data w formacie ddmmyyyy
     */
    private static String dateFormatForFile()
    {
        Calendar cal = Calendar. getInstance();
        Date date= cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        return dateFormat.format(date);
    }

    /**
     * Zwraca aktualną godzinę w formacie hh:mm:ss.
     * @return Aktualna godzina.
     */
    private static String time()
    {
        Calendar cal = Calendar. getInstance();
        Date date= cal.getTime();
        SimpleDateFormat watchFormat = new SimpleDateFormat("HH:mm:ss");
        return watchFormat.format(date);
    }
    /**
     * Zwraca aktualną godzinę w formacie hhmmss.
     * @return Aktualna godzina.
     */
    private static String timeFormatForFile()
    {
        Calendar cal = Calendar. getInstance();
        Date date= cal.getTime();
        SimpleDateFormat watchFormat = new SimpleDateFormat("HHmmss");
        return watchFormat.format(date);
    }

    /**
     * Zwraca aktualną datę i godzinę w formacie dd.mm.yyyy hh:mm:ss.
     * @return Aktualna data i godzina.
     */
    public static String fullDate()
    {
        return date() + " " + time();
    }

    /**
     * Zwraca aktualną datę i godzinę w formacie [dd.mm.yyyy hh:mm:ss].
     * @return Aktualna data i godzina.
     */
    public static String fullDateFormat()
    {
        return "["+ date() + " " + time() + "] ";
    }


    /**
     * Zwraca aktualną datę i godzinę w formacie dd.mm.yyyy_hh:mm:ss.
     * @return Aktualna data i godzina.
     */
    public static String fullDateFormatForFile()
    {
        return dateFormatForFile() +"_"+timeFormatForFile();
    }

    public static void printFullDateFormat()
    {
        System.out.println(fullDateFormat());
    }

    /**
     * Wypisuję informację, że jestem w Fail Tasku.
     * @param className Nazwa klasy.
     */
    public static void printOutFail(String className)
    {
        System.out.println(fullDateFormat() + className + " Fail Task.");
    }
    /**
     * Wypisuję informację, że jestem w Success Tasku.
     * @param className Nazwa klasy.
     */
    public static void printOutSuccess(String className)
    {
        System.out.println(fullDateFormat() + className + " Success Task.");
    }

    /**
     * Usuwa wszsytkie znaki występujące w wyrazie.
     * @param ch Znak który ma być usunięty.
     * @param word Wyraz.
     * @return Zedytowany wyraz.
     */
    public static String deleteChars(char ch, String word)
    {
        char [] chars = word.toCharArray();
        StringBuilder s = new StringBuilder();
        for (char aChar : chars) {
            if (aChar != ch)
                s.append(aChar);
        }
        return s.toString();
    }

    /**
     * Dodaj kropki do tekstu, po każdym 3 znaku. Stworzone do stosowania przy liczbach. Przykład 24567 -> 24.567
     * @param txt Tekst.
     */
    public static String addDots(String txt)
    {
        StringBuilder s = new StringBuilder(txt);
        int a = s.length();
        StringBuilder tmp = new StringBuilder();
        if(a%3 == 2 && a > 3)
        {
            for(int i = 0; i < a; i++)
            {
                tmp.append(s.charAt(i));
                if(i%3 == 1 && i != a-1)
                    tmp.append(".");
            }
        }
        else if(a%3 == 1 && a > 3)
        {
            for(int i = 0; i < a; i++)
            {
                tmp.append(s.charAt(i));
                if(i%3 == 0 && i != a-1)
                    tmp.append(".");
            }
        }
        else if(a%3 == 0 && a > 3)
        {
            for(int i = 0; i < a; i++)
            {
                tmp.append(s.charAt(i));
                if(i%3 == 2 && i != a-1)
                    tmp.append(".");
            }
        }
        else
            tmp = new StringBuilder(txt);
        return tmp.toString();
    }
    public static void printLogo()
    {
        System.out.println("--------------------------------");
        System.out.println("-------~~~~MAT MAD PRO~~~~------");
        System.out.println("--------------------------------");
    }

    public static void printText(String text)
    {
        System.out.println(text);
    }

}
