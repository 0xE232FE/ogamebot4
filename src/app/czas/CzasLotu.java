package app.czas;

public class CzasLotu
{
    private Czas czas = new Czas();
    private Data data = new Data();

    /*
    SETTERS
     */
    public void setData(Data data) {
        this.data = data;
    }

    public void setCzas(Czas czas) { this.czas = czas;}


    public void setDataString(String dataString) {
        if(dataString != null)
            data.setDataVariable(dataString);
    }

    public  void setCzasString(String czasString) {
        if(czasString != null)
            czas.setTimeVariable(czasString);
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

    @Override
    public String toString() {
        return data.toString() + " " + czas.toString();
    }
}
