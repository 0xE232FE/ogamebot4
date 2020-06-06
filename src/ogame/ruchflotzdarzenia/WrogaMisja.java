package ogame.ruchflotzdarzenia;

import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.Data;
import com.DifferentMethods;

public class WrogaMisja
{
    private final Czas czas;
    private final Data data;
    private boolean naKsiezyc = false;
    private boolean naPlanete = false;
    private final String wspolrzedne;
    private final int id;

    public WrogaMisja(Czas czas, boolean naPlanete, String wspolrzedne, int id) {
        this.czas = czas;
        this.naPlanete = naPlanete;
        this.id = id;

        if(!naPlanete)
            naKsiezyc = true;

        this.wspolrzedne = wspolrzedne;

        if(czas.czasWSekundach() > CzasGry.getCzas().czasWSekundach())
            data = CzasGry.getData();
        else
            data = CzasGry.getData().getTommorowDate();
    }

    /*
    EXECUTING
     */

    /**
     * Oblicza ile czasu zostało do ataku.
     */
    public Czas pozostałoDoAtaku(Czas aktualny)
    {
        return new Czas(czas.czasWSekundach() - aktualny.czasWSekundach());
    }

    /**
     * Sprawdza czy do ataku zostało mniej niż 300 sek. (5 min.).
     */
    public boolean wykonacFS(Czas aktualny)
    {
        return  (czas.czasWSekundach() - aktualny.czasWSekundach()) <= 300;
    }

    /**
     * Sprawdza czy do atak minął. Jeżeli jest 60 sek. (1 min.) po ataku to zwraca <b>true</b>.
     */
    public boolean atakMinal(Czas aktualny)
    {
        return pozostałoDoAtaku(aktualny).czasWSekundach() < -60;
    }



    /*
    GETTERS
     */

    public Czas getCzas() {
        return czas;
    }

    public Data getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public boolean isNaKsiezyc() {
        return naKsiezyc;
    }

    public boolean isNaPlanete() {
        return naPlanete;
    }

    public String getWspolrzedne() {
        return wspolrzedne;
    }

    @Override
    public String toString() {
        int dl = 25;

        String sb = "\n";
        sb += DifferentMethods.initVariable("Czas ",dl) + czas.toString() +
                "\n" +
                DifferentMethods.initVariable("Data ",dl) + data.toString() +
                "\n" +
                DifferentMethods.initVariable("Atakowana planety ",dl) + wspolrzedne +
                "\n" +
                DifferentMethods.initVariable("Atak na planetę ",dl) + naPlanete +
                "\n" +
                DifferentMethods.initVariable("Atak na księżyc ",dl) + naKsiezyc +
                "\n" +
                DifferentMethods.initVariable("=END--END--END--END= ",dl);

        return sb;
    }
}
