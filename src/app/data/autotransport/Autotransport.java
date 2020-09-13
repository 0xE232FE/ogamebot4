package app.data.autotransport;

import app.czas.CzasLotu;
import app.czas.CzasWykonania;
import ogame.planety.Planeta;

public class Autotransport
{
    private final Planeta planeta;
    private CzasLotu czasDostarczenia;
    private CzasLotu czasPowrotu;
    private int zabranoSurowcow = -1;
    private int pozostaloSurowcow = -1;
    private CzasWykonania nastepneWykonanie;
    private boolean wyslijUser = false;
    private boolean wyslijPonownie = true;

    public Autotransport(Planeta planeta) {
        this.planeta = planeta;
//        czasDostarczenia = new CzasLotu();
//        czasPowrotu = new CzasLotu();
        nastepneWykonanie = new CzasWykonania();
    }

    /*
    GETTERS
     */

    public Planeta getPlaneta() {
        return planeta;
    }

    public CzasLotu getCzasDostarczenia() {
        return czasDostarczenia;
    }

    public CzasLotu getCzasPowrotu() {
        return czasPowrotu;
    }

    public int getZabranoSurowcow() {
        return zabranoSurowcow;
    }

    public int getPozostaloSurowcow() {
        return pozostaloSurowcow;
    }

    public CzasWykonania getNastepneWykonanie() {
        return nastepneWykonanie;
    }

    public boolean isWyslijUser() {
        return wyslijUser;
    }

    public boolean isWyslijPonownie() {
        return wyslijPonownie;
    }

    /*
    SETTERS
     */

    public void setCzasDostarczenia(CzasLotu czasDostarczenia) {
        this.czasDostarczenia = czasDostarczenia;
    }

    public void setCzasPowrotu(CzasLotu czasPowrotu) {
        this.czasPowrotu = czasPowrotu;
    }

    public void setZabranoSurowcow(int zabranoSurowcow) {
        this.zabranoSurowcow = zabranoSurowcow;
    }

    public void setPozostaloSurowcow(int pozostaloSurowcow) {
        this.pozostaloSurowcow = pozostaloSurowcow;
    }

    public void setWyslijUser(boolean wyslijUser) {
        this.wyslijUser = wyslijUser;
    }

    public void setNastepneWykonanie(CzasWykonania nastepneWykonanie) {
        this.nastepneWykonanie = nastepneWykonanie;
    }

    public void setWyslijPonownie(boolean wyslijPonownie) {
        this.wyslijPonownie = wyslijPonownie;
    }
}
