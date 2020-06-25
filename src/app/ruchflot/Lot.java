package app.ruchflot;

import app.OgameWeb;
import app.Wspolrzedne;
import app.czas.CzasLotu;
import com.DifferentMethods;
import ogame.ruchflot.RuchFlot;

/**
 * Przechowuje dane z jednego lotu z zakładki <b>Ruch flot</b>.
 */
public class Lot
{
    private boolean wraca = false;
    private boolean zKsiezyca = false;
    private boolean naKsiezyc = false;
    private String rodzaj = "";
    private CzasLotu czasUCelu = new CzasLotu();
    private CzasLotu czasPowrot = new CzasLotu();
    private String cel;
    private String start;
    private String id;
    private int lp;

    /*
    EXECUTING
     */

    public boolean zawroc()
    {
        return RuchFlot.zawroc(OgameWeb.webDriver,lp);
    }
    /*
    GETTERS
     */
    public boolean isWraca() {
        return wraca;
    }

    boolean iszKsiezyca() {
        return zKsiezyca;
    }

    boolean isNaKsiezyc() {
        return naKsiezyc;
    }

    String getRodzaj() {
        return rodzaj;
    }

    public CzasLotu getCzasUCelu() {
        return czasUCelu;
    }

    public CzasLotu getCzasPowrot() {
        return czasPowrot;
    }

    String getCel() {
        return cel;
    }

    public String getId() {
        return id;
    }

    public Wspolrzedne cel () { return new Wspolrzedne(cel);}

    public String getStart() {
        return start;
    }

    public Wspolrzedne start () { return new Wspolrzedne(start);}

    public int getLp() {
        return lp;
    }

    /*
    SETTERS
     */

    public void setLp(int lp) {
        this.lp = lp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWraca(boolean wraca) {
        this.wraca = wraca;
    }

    public void setzKsiezyca(boolean zKsiezyca) {
        this.zKsiezyca = zKsiezyca;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public void setCzasUCelu(CzasLotu czasUCelu) {
        this.czasUCelu = czasUCelu;
    }

    public void setCzasPowrot(CzasLotu czasPowrot) {
        this.czasPowrot = czasPowrot;
    }

    public void setNaKsiezyc(boolean naKsiezyc) {
        this.naKsiezyc = naKsiezyc;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String toString() {
        int dl = 25;

        String sb = "\n";
        sb += (DifferentMethods.initVariable("Współrzędne startu ",dl)+start)+
                "\n" +
                DifferentMethods.initVariable("Współrzędne celu ",dl) + cel+
                "\n" +
                DifferentMethods.initVariable("Czas u celu ",dl) + czasUCelu.toString()+
                "\n" +
                DifferentMethods.initVariable("Czas powrotu ",dl) + czasPowrot.toString()+
                "\n" +
                DifferentMethods.initVariable("Misja ",dl) + rodzaj+
                "\n" +
                DifferentMethods.initVariable("Wraca flota ",dl) + wraca+
                "\n" +
                DifferentMethods.initVariable("Wysłana z księżyca ",dl) + zKsiezyca+
                "\n" +
                DifferentMethods.initVariable("Leci na księżyc ",dl) + naKsiezyc+
                "\n" +
                DifferentMethods.initVariable("ID ",dl) + id+
                "\n" +
                DifferentMethods.initVariable("=END--END--END--END= ",dl);

        return sb;
    }
}
