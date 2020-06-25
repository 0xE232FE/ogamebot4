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

        if(this.data.equals(data))
            return czas.czasWSekundach() - this.czas.czasWSekundach();
        else
            return (Czas.MAX_SECONDS_DAY - this.czas.czasWSekundach()) + czas.czasWSekundach();
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
