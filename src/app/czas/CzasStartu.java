package app.czas;

public class CzasStartu
{
    private static Czas czas = new Czas();
    private static Data data = new Data();

    public static void setDataString(String dataString) {
        if(dataString != null)
            data.setDataVariable(dataString);
    }

    public static void setCzasString(String czasString) {
        if(czasString != null)
            czas.setTimeVariable(czasString);
    }

    /**
     *
     * @return Czas
     */
    public static Czas getCzas() {
        return czas;
    }

    /**
     *
     * @return Datę
     */
    public static Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString() + " " + czas.toString();
    }

    public static String string()
    {
        return data.toString() + " " + czas.toString();
    }
}
