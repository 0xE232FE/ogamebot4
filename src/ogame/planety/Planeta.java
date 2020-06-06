package ogame.planety;

import com.DifferentMethods;
import com.Log;
//import ogame.budynki.Budynki;
import org.openqa.selenium.WebDriver;

public class Planeta
{
        private final int pozycjaNaLiscie;
        private final String wspolrzedne;
        private final String nazwa;
        private final boolean moon;
        private final int id;
        private boolean selected = false;
        private boolean moonSelected = false;
        private boolean constructionInProgress = false;
        private boolean moonConstructionInProgress = false;
        private boolean attack = false;
        private boolean moonAttack = false;
//        private Surowce surowce = new Surowce();
//        private Surowce surowceMoon = null;
//        private Wydobycie wydobycie = new Wydobycie();
//        private Budynki budynki = new Budynki();

        private WebDriver webDriver;

    public Planeta(int pozycjaNaLiscie, String wspolrzedne, String nazwa, boolean moon, int id) {
        this.pozycjaNaLiscie = pozycjaNaLiscie;
        this.wspolrzedne = wspolrzedne;
        this.nazwa = nazwa;
        this.moon = moon;
        this.id = id;

//        if(moon)
//            surowceMoon = new Surowce();
    }

    @Override
    public boolean equals(Object obj) {
        Planeta p = (Planeta) obj;
        return this.id == ((Planeta) obj).getId();
    }
    /*
        EXECUTING METHODS
     */
    public void clickPlanet()
    {
        if(webDriver != null)
            ListaPlanet.kliknijPlanete(webDriver,pozycjaNaLiscie);
        else
            Log.printLog(Planeta.class.getName(),"Nie wykonano polecenia clickPlanet na planecie "+nazwa+" "
                    + wspolrzedne + " z powodu WebDriver == null");
    }

     public void clickMoon()
     {
         if(webDriver != null)
             ListaPlanet.kliknijKsiezyc(webDriver,pozycjaNaLiscie);
         else
             Log.printLog(Planeta.class.getName(),"Nie wykonano polecenia clickMoon na planecie "+nazwa+" "
                     + wspolrzedne + " z powodu WebDriver == null");
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

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /*
    GETTERS
     */

    public int getPozycjaNaLiscie() {
        return pozycjaNaLiscie;
    }

    public String getWspolrzedne() {
        return wspolrzedne;
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

//    public Surowce getSurowce() {
//        return surowce;
//    }
//
//    public Wydobycie getWydobycie() {
//        return wydobycie;
//    }
//
//    public Surowce getSurowceMoon() {
//        return surowceMoon;
//    }
//
//    public Budynki getBudynki() {
//        return budynki;
//    }

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
