package ogame.ruchflotzdarzenia;

import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.czas.Data;
import com.DifferentMethods;
import ogame.ruchflot.ObiektLotu;

import java.util.List;

public class WrogaMisja
{
    private final WyslanyFS daneWyslanegoFS;
    private CzasWykonania klikaniePodglad;
    private Czas czas;
    private Data data;
    private boolean naKsiezyc = false;
    private boolean naPlanete;
    private final String wspolrzedne;
    private final int id;
    /*
        0 - wykryto atak
        1 - wysłano FS'a
        2 - sprawdzam czy zawrócić
        3 - flota zawrócona
     */
    private int counter = 0;

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

        daneWyslanegoFS = new WyslanyFS(czas,data);
        klikaniePodglad = new CzasWykonania();
    }

    /*
    EXECUTING
     */

    /**
     * Oblicza ile czasu zostało do ataku.
     */
    public Czas pozostaloDoAtaku(Czas aktualny)
    {
        return new Czas(czas.czasWSekundach() - aktualny.czasWSekundach());
    }

    /**
     * Oblicza ile czasu zostało do ataku.
     */
    public Czas pozostaloDoAtaku(Czas aktualny, Data aktualna)
    {
        if(data.equals(aktualna))
            return new Czas(czas.czasWSekundach() - aktualny.czasWSekundach());
        else
            return new Czas(Czas.MAX_SECONDS_DAY - aktualny.czasWSekundach() + czas.czasWSekundach());
    }

    /**
     * Sprawdza czy do ataku zostało mniej niż 90 sek. (1 min. 30 sek.).
     */
    public boolean wykonacFS(Czas aktualny, Data aktualna)
    {
        if(data.equals(aktualna))
            return  (czas.czasWSekundach() - aktualny.czasWSekundach()) <= 90;
        else
            return false;
    }

    /**
     * Sprawdza czy  atak minął. Zwraca true gdy jest ponad 15 sek. po ataku.
     */
    public boolean atakMinal(Czas aktualny)
    {
        return pozostaloDoAtaku(aktualny).czasWSekundach() < -10;
    }

    /**
     * Sprawdza czy atak minął.
     * @param aktualny Aktualny czas gry.
     * @param sec Ilość sekund po ataku.
     * @return Jeżeli po wejściu ataku mineło więcej sekund niż podana liczba w parametrze <b>sec</b> zwróci true.
     */
    public boolean atakMinal(Czas aktualny, int sec)
    {
        return pozostaloDoAtaku(aktualny).czasWSekundach() < sec;
    }


    /**
     * Sprawdza czy misja nie została opóźniona.
     * @param misje Lista wrogich misji z EventBoxa
     * @return Jeżeli misja została opóźniona zwróci <b>true</b>
     */
    public boolean zostalaOpozniona(List<WrogaMisja> misje)
    {
        for(WrogaMisja misja : misje)
        {
            if(misja.getId() == id)
            {
                setCzas(misja.getCzas());
                setData(misja.getData());
                return false;
            }
        }
        return true;
    }

    public boolean dodatkoweAtakiNaTeWspolrzedne(List<WrogaMisja> misje)
    {
        for(WrogaMisja misja : misje)
        {
            if(misja.getWspolrzedne().equals(wspolrzedne) && misja.getId() != id)
                return true;
        }
        return false;
    }

    /*
    SETTERS
     */

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setCzas(Czas czas) {
        this.czas = czas;
    }

    public void setData(Data data) {
        this.data = data;
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

    public int getCounter() {
        return counter;
    }

    public WyslanyFS getDaneWyslanegoFS() {
        return daneWyslanegoFS;
    }

    public CzasWykonania getKlikaniePodglad() {
        return klikaniePodglad;
    }

    @Override
    public String toString() {
        int dl = 25;

        String sb = "\n";
        sb += DifferentMethods.initVariable("Czas ",dl) + czas.toString() +
                "\n" +
                DifferentMethods.initVariable("Data ",dl) + data.toString() +
                "\n" +
                DifferentMethods.initVariable("ID ",dl) + id +
                "\n" +
                DifferentMethods.initVariable("Atakowane współrzędne ",dl) + wspolrzedne +
                "\n" +
                DifferentMethods.initVariable("Atak na planetę ",dl) + naPlanete +
                "\n" +
                DifferentMethods.initVariable("Atak na księżyc ",dl) + naKsiezyc +
                "\n" +
                DifferentMethods.initVariable("=END--END--END--END= ",dl);

        return sb;
    }

    /**
     * Klasa przechowująca dane o wysłanym FS's. (tj. czas, data, obiekt lotu).
     */
    public class WyslanyFS {
        private Czas czas;
        private Data data;
        private ObiektLotu obiektLotu;

        WyslanyFS(Czas czas, Data data) {

            int a = Czas.MAX_SECONDS_DAY - czas.czasWSekundach();
            if (a < 90) {
                this.czas = new Czas(90 - a);
                this.data = data.getTommorowDate();
            }
            else {
                this.czas = new Czas(czas.czasWSekundach() - 90);
                this.data = data;
            }
        }

        /*
        SETTER
         */
        public void setObiektLotu(ObiektLotu obiektLotu) {
            this.obiektLotu = obiektLotu;
        }

        public void setCzas(String czas) {
            this.czas.setTimeVariable(czas);
        }

        public void setData(String data) {
            this.data.setDataVariable(data);
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

        public ObiektLotu getObiektLotu() {
            return obiektLotu;
        }

        @Override
        public String toString() {
            int dl = 25;

            String sb = "\n";
            sb += DifferentMethods.initVariable("Czas ", dl) + czas.toString() +
                    "\n" +
                    DifferentMethods.initVariable("Data ", dl) + data.toString() +
                    "\n" +
                    DifferentMethods.initVariable("Obiekt lotu ", dl) + obiektLotu.toString() +
                    "\n" +
                    DifferentMethods.initVariable("=END--END--END--END= ", dl);

            return sb;
        }
    }
}
