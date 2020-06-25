package ogame.ruchflot;

import com.DifferentMethods;

public class ObiektLotu
{
    private final String wspolrzedne;
    private final boolean planeta;
    private final boolean ksiezyc;

    public ObiektLotu(String wspolrzedne, boolean planeta) {

        this.wspolrzedne = wspolrzedne;
        this.planeta = planeta;

        ksiezyc = !planeta;
    }

    /*
    GETTERS
     */
    public String getWspolrzedne() {
        return wspolrzedne;
    }

    public boolean isPlaneta() {
        return planeta;
    }

    public boolean isKsiezyc() {
        return ksiezyc;
    }

    @Override
    public String toString() {
        int dl = 25;

        String sb = "\n";
        sb += DifferentMethods.initVariable("Współrzędne  ", dl) + wspolrzedne +
                "\n" +
                DifferentMethods.initVariable("Lot na planetę ", dl) + planeta +
                "\n" +
                DifferentMethods.initVariable("Lot na księżyc ", dl) + ksiezyc +
                "\n" +
                DifferentMethods.initVariable("=END--END--END--END= ", dl);

        return sb;
    }
}
