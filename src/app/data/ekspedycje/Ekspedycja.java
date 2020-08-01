package app.data.ekspedycje;

import app.czas.CzasLotu;
import com.DifferentMethods;

public class Ekspedycja
{
    private CzasLotu przylot;
    private CzasLotu powrot;
    private final CzasLotu start;
    private String id = "";
    private boolean opozniona;

    public Ekspedycja(CzasLotu start, CzasLotu przylot, CzasLotu powrot) {
        this.przylot = przylot;
        this.powrot = powrot;
        this.start = start;
    }

    /*
        GETTERS
         */
    public CzasLotu getPrzylot() {
        return przylot;
    }

    public CzasLotu getPowrot() {
        return powrot;
    }

    public CzasLotu getStart() {
        return start;
    }

    public String getId() {
        return id;
    }

    public boolean isOpozniona() {
        return opozniona;
    }

    /*
    SETTERS
     */
    public void setId(String id) {
        this.id = id;
    }

    void setOpozniona(boolean opozniona) {
        this.opozniona = opozniona;
    }

    public void setPrzylot(CzasLotu przylot) {
        this.przylot = przylot;
    }

    public void setPowrot(CzasLotu powrot) {
        this.powrot = powrot;
    }

    @Override
    public String toString() {
        int dl = 15;

        String sb = "\n";
        sb += DifferentMethods.initVariable("Start ", dl) + start.toString() +
                "\n" +
                DifferentMethods.initVariable("Przylot ", dl) + przylot.toString() +
                "\n" +
                DifferentMethods.initVariable("Powrót ", dl) + powrot.toString() +
                "\n" +
                DifferentMethods.initVariable("Id ", dl) + id +
                "\n" +
                DifferentMethods.initVariable("Opóźniona ", dl) + opozniona +
                "\n" +
                DifferentMethods.initVariable("=END--END--END--END= ", dl);

        return sb;
    }
}
