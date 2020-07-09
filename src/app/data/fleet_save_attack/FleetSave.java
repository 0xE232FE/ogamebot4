package app.data.fleet_save_attack;

import app.Wspolrzedne;
import com.DifferentMethods;
import ogame.flota.FlotaIII;

import java.io.Serializable;

public class FleetSave implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String misja;
    private final int misjaInt;
    private final String wspolrzedne;
    private final int obiekt;

    /**
     *
     * @param misja Nazwa misji.
     * @param wspolrzedne Współrzędne
     * @param obiekt 0 - planeta, 1 - ksieżyc
     */
    public FleetSave(String misja, String wspolrzedne, int obiekt) {
        this.misja = misja;
        this.wspolrzedne = wspolrzedne;
        this.obiekt = obiekt;
        misjaInt = FlotaIII.intMissionType(misja);
    }

    /**
     *
     * @param misja Nazwa misji.
     * @param wspolrzedne Współrzędne
     * @param obiekt 0 - planeta, 1 - ksieżyc
     */
    public FleetSave(String misja, Wspolrzedne wspolrzedne, int obiekt) {
        this.misja = misja;
        this.wspolrzedne = wspolrzedne.toString();
        this.obiekt = obiekt;
        misjaInt = FlotaIII.intMissionType(misja);
    }

    public String getMisja() {
        return misja;
    }

    public String getWspolrzedne() {
        return wspolrzedne;
    }

    public int getObiekt() {
        return obiekt;
    }

    public int getMisjaInt() {
        return misjaInt;
    }

    @Override
    public boolean equals(Object obj) {
        FleetSave fleetSave = (FleetSave) obj;

        return this.obiekt == fleetSave.getObiekt() && this.misja.equals(fleetSave.getMisja())
                && this.wspolrzedne.equals(fleetSave.getWspolrzedne());
    }

    @Override
    public String toString() {

        int dl = 25;

        String sb = "\n";
        sb += DifferentMethods.initVariable("Misja ",dl)+misja+
                "\n" +
                DifferentMethods.initVariable("Obiekt ",dl) + obiekt +
                "\n" +
                DifferentMethods.initVariable("Współrzędne obiektu ",dl) + wspolrzedne +
                "\n" +
                DifferentMethods.initVariable("=END--END--END--END= ",dl);

        return sb;
    }
}
