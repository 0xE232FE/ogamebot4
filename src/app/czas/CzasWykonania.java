package app.czas;

public class CzasWykonania
{
    private Czas czas = new Czas();
    private Data data = new Data();
    private boolean active = false;


    /*
    EXECUTING
     */

    /**
     * Oblicza ile mineło sekund od wskazanej daty w argumentach.
     * @return Czas w sekundach.
     */
    public int ileMinelo(Czas czas, Data data)
    {
        // Gdy wykonywany jest pierwszy raz i nie ma określonego czasu i daty.
        if(this.czas.czasWSekundach() == 0)
            return 0;

        if(this.data.toString().equals(data.toString()))
            return czas.czasWSekundach() - this.czas.czasWSekundach();
        else
            return (Czas.MAX_SECONDS_DAY - this.czas.czasWSekundach()) + czas.czasWSekundach();
    }

    /**
     * Określa nową datę po przesunięciu wprowadzonej o określony czas.
     * @param czasAktualny Aktualny czas.
     * @param dataAktualna Aktualna data.
     * @param czasPrzesuniecia Czas o jaki przesunąć.
     * @return Nowy czas po przesunięciu.
     */
    public static CzasWykonania okreslCzasWykonania(Czas czasAktualny, Data dataAktualna, Czas czasPrzesuniecia)
    {
        CzasWykonania tmp = new CzasWykonania();
        int tmp1 = czasAktualny.czasWSekundach();
        int tmp2 = czasPrzesuniecia.czasWSekundach();

        //Suma czasów większa od maksymalnego czasu 1 doby.
        if(Czas.MAX_SECONDS_DAY <= tmp1 + tmp2)
        {
            tmp.setDataString(dataAktualna.getTommorowDate().toString());
            int a = Czas.MAX_SECONDS_DAY - tmp1;
            tmp.setCzasString(new Czas(tmp2 - a).toString());
        }
        else
        {
            tmp.setDataString(dataAktualna.toString());
            tmp.setCzasString(new Czas(tmp1+tmp2).toString());
        }

        return tmp;
    }

    public Czas pozostaloCzasu(Czas czasAktualny, Data dataAktualna)
    {
        int tmp2;
        //Daty są takie same.
        if(dataAktualna.equals(data))
            tmp2 = czas.czasWSekundach() - czasAktualny.czasWSekundach();
        else
            tmp2 = czas.czasWSekundach() + (Czas.MAX_SECONDS_DAY - czasAktualny.czasWSekundach());


        return new Czas(tmp2);
    }


    /*
    SETTERS
     */
    public void setDataString(String dataString) {
        if(dataString != null)
            data.setDataVariable(dataString);
    }

    public  void setCzasString(String czasString) {
        if(czasString != null)
            czas.setTimeVariable(czasString);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /*
    GETTERS
     */

    /**
     *
     * @return Czas
     */
    public Czas getCzas() {
        return czas;
    }

    /**
     *
     * @return Datę
     */
    public  Data getData() {
        return data;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return data.toString() + " " + czas.toString();
    }
}
