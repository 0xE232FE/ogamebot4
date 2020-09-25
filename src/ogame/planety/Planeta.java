package ogame.planety;

import app.OgameWeb;
import app.Wspolrzedne;
import app.data.fleet_save_attack.FleetSaveAttackMissionConfiguration;
import com.DifferentMethods;
import com.Log;
import ogame.budynki.Budynki;
import ogame.ruchflot.ObiektLotu;


public class Planeta
{
    private final int pozycjaNaLiscie;
    private final String wspolrzedne;
    private final String nazwa;
    private final boolean moon;
    private final int id;
    private final String planetFileName;
    private final String moonFileName;
    private boolean selected = false;
    private boolean moonSelected = false;
    private boolean constructionInProgress = false;
    private boolean moonConstructionInProgress = false;
    private boolean attack = false;
    private boolean moonAttack = false;
    private boolean flotaZPlanetyWyslanaNaFS = false;
    private boolean flotaZKsiezycaWyslanaNaFS = false;
    private ObiektLotu obiektFSZPlanety;
    private ObiektLotu obiektFSZKsiezyca;
    private FleetSaveAttackMissionConfiguration attackFleetSaveConfiguration;

//        private Wydobycie wydobycie = new Wydobycie();
    private Budynki budynki = new Budynki();

    public Planeta(int pozycjaNaLiscie, String wspolrzedne, String nazwa, boolean moon, int id) {
        this.pozycjaNaLiscie = pozycjaNaLiscie;
        this.wspolrzedne = wspolrzedne;
        this.nazwa = nazwa;
        this.moon = moon;
        this.id = id;

        // Ustawianie nazwy pliku
        planetFileName = new Wspolrzedne(wspolrzedne).fileName();
        moonFileName = planetFileName +"_M";

        attackFleetSaveConfiguration = FleetSaveAttackMissionConfiguration.load(planetFileName);
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Planeta) obj).getId();
    }
    /*
        EXECUTING METHODS
     */

    /**
     * Klika w planetę.
     */
    public void clickPlanet()
    {
        if(OgameWeb.webDriver != null)
            ListaPlanet.kliknijPlanete(OgameWeb.webDriver,pozycjaNaLiscie);
        else
            Log.printLog(Planeta.class.getName(),"Nie wykonano polecenia clickPlanet na planecie "+nazwa+" "
                    + wspolrzedne + " z powodu WebDriver == null");
    }

    /**
     * Klika w księżyc, jeżeli posiada.
     */
    public void clickMoon()
    {
        if(OgameWeb.webDriver != null && moon)
            ListaPlanet.kliknijKsiezyc(OgameWeb.webDriver,pozycjaNaLiscie);
        else
            Log.printLog(Planeta.class.getName(),"Nie wykonano polecenia clickMoon na planecie "+nazwa+" "
                    + wspolrzedne + (moon ? " z powodu WebDriver == null":" z powodu braku księżyca."));
    }

    /*
        SETTERS
         */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setConstructionInProgress(boolean constructionInProgress) {
        this.constructionInProgress = constructionInProgress;
    }

    public void setMoonConstructionInProgress(boolean moonConstructionInProgress) {
        this.moonConstructionInProgress = moonConstructionInProgress;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public void setMoonAttack(boolean moonAttack) {
        this.moonAttack = moonAttack;
    }

    public void setMoonSelected(boolean moonSelected) {
        this.moonSelected = moonSelected;
    }

    public void setFlotaZPlanetyWyslanaNaFS(boolean flotaZPlanetyWyslanaNaFS) {
        this.flotaZPlanetyWyslanaNaFS = flotaZPlanetyWyslanaNaFS;
    }

    public void setFlotaZKsiezycaWyslanaNaFS(boolean flotaZKsiezycaWyslanaNaFS) {
        this.flotaZKsiezycaWyslanaNaFS = flotaZKsiezycaWyslanaNaFS;
    }

    public void setObiektFSZPlanety(ObiektLotu obiektFSZPlanety) {
        this.obiektFSZPlanety = obiektFSZPlanety;
    }

    public void setObiektFSZKsiezyca(ObiektLotu obiektFSZKsiezyca) {
        this.obiektFSZKsiezyca = obiektFSZKsiezyca;
    }

    /*
    GETTERS
     */

    public Budynki getBudynki() {
        return budynki;
    }

    public int getPozycjaNaLiscie() {
        return pozycjaNaLiscie;
    }

    public String getWspolrzedne() {
        return wspolrzedne;
    }

    public Wspolrzedne wspolrzedne()
    {
        return new Wspolrzedne(wspolrzedne);
    }

    public String getNazwa() {
        return nazwa;
    }

    public boolean isMoon() {
        return moon;
    }

    public int getId() {
        return id;
    }

    public ObiektLotu getObiektFSZPlanety() {
        return obiektFSZPlanety;
    }

    public ObiektLotu getObiektFSZKsiezyca() {
        return obiektFSZKsiezyca;
    }

    public boolean isFlotaZPlanetyWyslanaNaFS() {
        return flotaZPlanetyWyslanaNaFS;
    }

    public boolean isFlotaZKsiezycaWyslanaNaFS() {
        return flotaZKsiezycaWyslanaNaFS;
    }

    public FleetSaveAttackMissionConfiguration getAttackFleetSaveConfiguration() {
        return attackFleetSaveConfiguration;
    }

    public String getPlanetFileName() {
        return planetFileName;
    }

    public String getMoonFileName() {
        return moonFileName;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isConstructionInProgress() {
        return constructionInProgress;
    }

    public boolean isMoonConstructionInProgress() {
        return moonConstructionInProgress;
    }

    public boolean isAttack() {
        return attack;
    }

    public boolean isMoonAttack() {
        return moonAttack;
    }

    public boolean isMoonSelected() {
        return moonSelected;
    }


    @Override
    public String toString() {

            int dl = 25;

            String sb = "\n";
            sb += DifferentMethods.initVariable("Pozycja na liście planet ",dl)+pozycjaNaLiscie+
                    "\n" +
                    DifferentMethods.initVariable("ID planety ",dl) + id +
                    "\n" +
                    DifferentMethods.initVariable("Współrzędne planety ",dl) + wspolrzedne +
                    "\n" +
                    DifferentMethods.initVariable("Nazwa planety ",dl) + nazwa +
                    "\n" +
                    DifferentMethods.initVariable("Posiada księżyc ",dl) + moon +
                    "\n" +
                    DifferentMethods.initVariable("Planeta w budowie ",dl) + constructionInProgress +
                    "\n" +
                    DifferentMethods.initVariable("Księżyc w budowie ",dl) + moonConstructionInProgress +
                    "\n" +
                    DifferentMethods.initVariable("Wybrana planety ",dl) + selected +
                    "\n" +
                    DifferentMethods.initVariable("Wybrany księżyc ",dl) + moonSelected +
                    "\n" +
                    DifferentMethods.initVariable("Atak na planetę ",dl) + attack +
                    "\n" +
                    DifferentMethods.initVariable("Atak na księżyc ",dl) + moonAttack +
                    "\n" +
                    DifferentMethods.initVariable("=END--END--END--END= ",dl);

            return sb;
    }
}
