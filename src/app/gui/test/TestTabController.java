package app.gui.test;

import app.OgameWeb;
import app.gui.IntegerOnlyInputTextFieldListener;
import com.DifferentMethods;
import com.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import ogame.LeftMenu;
import ogame.flota.FlotaI;
import ogame.flota.FlotaIII;
import ogame.planety.ListaPlanet;
import ogame.surowce.Surowce;
import ogame.surowce.SurowceNaPlanecie;

public class TestTabController {
    private boolean activeFlag = false;

    @FXML
    private TextField textFieldPlanetID;

    @FXML
    private TextField textFieldKsiezycID;

    /*
    AUTOTRANSPORT
     */
    @FXML
    void kliknijPlanete(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");
            if(!textFieldPlanetID.getText().equals(""))
                ListaPlanet.kliknijPlanete(OgameWeb.webDriver,Integer.valueOf(textFieldPlanetID.getText()));
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono pozyzcję planety na liście.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void kliknijKsiezyc(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");
            if(!textFieldKsiezycID.getText().equals(""))
                ListaPlanet.kliknijKsiezyc(OgameWeb.webDriver,Integer.valueOf(textFieldKsiezycID.getText()));
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono pozyzcji księżyca na liście.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void iloscSurowcow(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            //Pobranie danych o ilości surowców
            int metal = SurowceNaPlanecie.metal(OgameWeb.webDriver);
            int krysztal = SurowceNaPlanecie.krysztal(OgameWeb.webDriver);
            int deuter = SurowceNaPlanecie.deuter(OgameWeb.webDriver);
            int energia = SurowceNaPlanecie.wolnaEnergia(OgameWeb.webDriver);

            Log.printLog(TestTabController.class.getName(),"Surowce na planecie: \n" +
                    DifferentMethods.initVariable("Metal ",10) +
                    DifferentMethods.initVariable(String.valueOf(metal),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(metal)),15) + "\n" +
                    DifferentMethods.initVariable("Kryształ ",10) +
                    DifferentMethods.initVariable(String.valueOf(krysztal),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(krysztal)),15) + "\n" +
                    DifferentMethods.initVariable("Deuter ",10) +
                    DifferentMethods.initVariable(String.valueOf(deuter),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(deuter)),15) + "\n" +
                    DifferentMethods.initVariable("Energia ",10) +
                    DifferentMethods.initVariable(String.valueOf(energia),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(energia)),15) + "\n");

            b.setText(text);
            activeFlag = false;
        }
    }

    /*
    FLOTA I
     */

    @FXML
    private TextField textFieldIStatekCywilnyIlosc;

    @FXML
    private TextField textFieldStatekCywilny;

    @FXML
    private TextField textFieldStatekBojowy;

    @FXML
    private TextField textFieldIStatekBojowyIlosc;
    @FXML
    void czyNaPlanecieJestFlota(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            boolean bool = FlotaI.isAnyShips(OgameWeb.webDriver);

            Log.printLog(TestTabController.class.getName(),"Czy jest flota na planecie: " + bool);

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void iloscStatkow(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");


            /*
             *           1 - Lekki myśliwiec
             *           2 - Ciężki myśliwiec
             *           3 - Krążownik
             *           4 - Okręt wojenny
             *           5 - Pancernik
             *           6 - Bombowiec
             *           7 - Niszczyciel
             *           8 - Gwiazda śnierci
             *           9 - Rozpruwacz
             *           10 - Pionier
             */
            //Pobranie danych o ilości statków
            int b1 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,1);
            int b2 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,2);
            int b3 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,3);
            int b4 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,4);
            int b5 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,5);
            int b6 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,6);
            int b7 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,7);
            int b8 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,8);
            int b9 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,9);
            int b10 = FlotaI.dostepnaIloscStatekBojowy(OgameWeb.webDriver,10);

            /*
             *           1 - Mały transporter
             *           2 - Duży transporter
             *           3 - Statek kolonizacyjny
             *           4 - Recykler
             *           5 - Sonda szpiegowska
             */
            int c1 = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,1);
            int c2 = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,2);
            int c3 = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,3);
            int c4 = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,4);
            int c5 = FlotaI.dostepnaIloscStatekCywilny(OgameWeb.webDriver,5);

            Log.printLog(TestTabController.class.getName(),"Flota na planecie: \n" +
                    DifferentMethods.initVariable("Lekki myśliwiec ",25) +
                    DifferentMethods.initVariable(String.valueOf(b1),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b1)),15) + "\n" +
                    DifferentMethods.initVariable("Cięzki myśliwiec ",25) +
                    DifferentMethods.initVariable(String.valueOf(b2),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b2)),15) + "\n" +
                    DifferentMethods.initVariable("Krążownik ",25) +
                    DifferentMethods.initVariable(String.valueOf(b3),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b3)),15) + "\n" +
                    DifferentMethods.initVariable("Okręt wojenny ",25) +
                    DifferentMethods.initVariable(String.valueOf(b4),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b4)),15) + "\n" +
                    DifferentMethods.initVariable("Pancernik ",25) +
                    DifferentMethods.initVariable(String.valueOf(b5),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b5)),15) + "\n" +
                    DifferentMethods.initVariable("Bombowiec ",25) +
                    DifferentMethods.initVariable(String.valueOf(b6),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b6)),15) + "\n" +
                    DifferentMethods.initVariable("Niszczyciel ",25) +
                    DifferentMethods.initVariable(String.valueOf(b7),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b7)),15) + "\n" +
                    DifferentMethods.initVariable("Gwiazda śmierci ",25) +
                    DifferentMethods.initVariable(String.valueOf(b8),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b8)),15) + "\n" +
                    DifferentMethods.initVariable("Rozpruwacz ",25) +
                    DifferentMethods.initVariable(String.valueOf(b9),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b9)),15) + "\n" +
                    DifferentMethods.initVariable("Pionier ",25) +
                    DifferentMethods.initVariable(String.valueOf(b10),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(b10)),15) + "\n" +
                    DifferentMethods.initVariable("Mały transporter ",25) +
                    DifferentMethods.initVariable(String.valueOf(c1),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(c1)),15) + "\n" +
                    DifferentMethods.initVariable("Duży transporter ",25) +
                    DifferentMethods.initVariable(String.valueOf(c2),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(c2)),15) + "\n" +
                    DifferentMethods.initVariable("Statek kolonizacyjny ",25) +
                    DifferentMethods.initVariable(String.valueOf(c3),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(c3)),15) + "\n" +
                    DifferentMethods.initVariable("Recykler ",25) +
                    DifferentMethods.initVariable(String.valueOf(c4),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(c4)),15) + "\n" +
                    DifferentMethods.initVariable("Sonda szpiegowska ",25) +
                    DifferentMethods.initVariable(String.valueOf(c5),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(c5)),15) + "\n");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void wprowadzIloscStatekBojowy(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");
            String tmp1 = textFieldStatekBojowy.getText();
            String tmp2 = textFieldIStatekBojowyIlosc.getText();
            if(!tmp1.equals(""))
            {
                if(!tmp2.equals(""))
                    FlotaI.wprowadzIloscStatekBojowy(OgameWeb.webDriver,Integer.valueOf(tmp1),Integer.valueOf(tmp2));
                else
                    Log.printLog(TestTabController.class.getName(),"Nie wprowadzono ilości statków.");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju statku.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void wprowadzIloscStatekCywilny(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");
            String tmp1 = textFieldStatekCywilny.getText();
            String tmp2 = textFieldIStatekCywilnyIlosc.getText();
            if(!tmp1.equals(""))
            {
                if(!tmp2.equals(""))
                    FlotaI.wprowadzIloscStatekCywilny(OgameWeb.webDriver,Integer.valueOf(tmp1),Integer.valueOf(tmp2));
                else
                    Log.printLog(TestTabController.class.getName(),"Nie wprowadzono ilości statków.");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju statku.");

            b.setText(text);
            activeFlag = false;
        }
    }

    /*
    FLOTA III
     */

    @FXML
    void wolnaPrzestrzenLadunkowa(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            int a = FlotaIII.wolnaPrzestrzenLadunkowa(OgameWeb.webDriver);

            Log.printLog(TestTabController.class.getName(),"Wolna przestrzeń ładunkowa: \n" +
                    DifferentMethods.initVariable(String.valueOf(a),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(a)),15) + "\n");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void maksymalnaPrzestrzenLadunkowa(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            int a = FlotaIII.maksymalnaPrzestrzenLadunkowa(OgameWeb.webDriver);

            Log.printLog(TestTabController.class.getName(),"Maksymalna przestrzeń ładunkowa: \n" +
                    DifferentMethods.initVariable(String.valueOf(a),12) +
                    DifferentMethods.initVariable(DifferentMethods.addDots(String.valueOf(a)),15) + "\n");

            b.setText(text);
            activeFlag = false;
        }
    }

    /*
    SUROWCE
     */

    @FXML
    private TextField textFieldBudynek;

    @FXML
    private CheckBox checkBoxKsiezyc;
    @FXML
    void kliknijWBudynek(ActionEvent event)
    {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            String tmp = textFieldBudynek.getText();
            boolean moon = checkBoxKsiezyc.isSelected();
            if(!tmp.equals(""))
            {
                boolean a = Surowce.kliknijWBudynek(OgameWeb.webDriver,Integer.valueOf(tmp),moon);
                if(a)
                    Log.printLog(TestTabController.class.getName(),"Kliknięto w " + Surowce.getBudynek(Integer.valueOf(tmp),moon).getName() +".");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju budynku.");

            b.setText(text);
            activeFlag = false;
        }
    }


    @FXML
    private TextField textFieldBudynek1;

    @FXML
    private CheckBox checkBoxKsiezyc1;
    @FXML
    void pobierzStatusBudynku(ActionEvent event)
    {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            String tmp = textFieldBudynek1.getText();
            boolean moon = checkBoxKsiezyc1.isSelected();
            if(!tmp.equals(""))
            {
                String a = Surowce.statusBudynku(OgameWeb.webDriver,Integer.valueOf(tmp),moon);
                Log.printLog(TestTabController.class.getName(),"Status " + Surowce.getBudynek(Integer.valueOf(tmp),moon).getName() +": \n" +
                        DifferentMethods.initVariable(a,12) + "\n");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju budynku.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    private TextField textFieldBudynek2;

    @FXML
    private CheckBox checkBoxKsiezyc2;
    @FXML
    void pobierzPoziomBudynku(ActionEvent event)
    {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            String tmp = textFieldBudynek2.getText();
            boolean moon = checkBoxKsiezyc2.isSelected();
            if(!tmp.equals(""))
            {
                int a = Surowce.poziomBudynku(OgameWeb.webDriver,Integer.valueOf(tmp),moon);
                Log.printLog(TestTabController.class.getName(),"Poziom " + Surowce.getBudynek(Integer.valueOf(tmp),moon).getName() +": \n" +
                        DifferentMethods.initVariable(String.valueOf(a),12) + "\n");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju budynku.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    private TextField textFieldBudynek3;

    @FXML
    private CheckBox checkBoxKsiezyc3;
    @FXML
    void rozbudujBudynek(ActionEvent event)
    {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            String tmp = textFieldBudynek3.getText();
            boolean moon = checkBoxKsiezyc3.isSelected();
            if(!tmp.equals(""))
            {
                boolean a = Surowce.rozbudujBudynek(OgameWeb.webDriver,Integer.valueOf(tmp),moon);
                if(a)
                    Log.printLog(TestTabController.class.getName(),"Kliknięto rozbuduj " + Surowce.getBudynek(Integer.valueOf(tmp),moon).getName() +".");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju budynku.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    private CheckBox checkBoxKsiezyc4;
    @FXML
    void budynekWRozbudowie(ActionEvent event)
    {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            boolean moon = checkBoxKsiezyc4.isSelected();

            Log.printLog(TestTabController.class.getName(),"Rozbudowywany budynek: \n" + Surowce.wRozbudowie(OgameWeb.webDriver,moon) +".");

            b.setText(text);
            activeFlag = false;
        }
    }

    /*
    ZAKŁADKI
     */

    @FXML
    void kliknijFlota(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");
            String tmp1 = textFieldStatekBojowy.getText();
            String tmp2 = textFieldIStatekBojowyIlosc.getText();
            if(!tmp1.equals(""))
            {
                if(!tmp2.equals(""))
                    FlotaI.wprowadzIloscStatekBojowy(OgameWeb.webDriver,Integer.valueOf(tmp1),Integer.valueOf(tmp2));
                else
                    Log.printLog(TestTabController.class.getName(),"Nie wprowadzono ilości statków.");
            }
            else
                Log.printLog(TestTabController.class.getName(),"Nie wprowadzono rodzaju statku.");

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void kliknijPodglad(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            LeftMenu.pressPodglad(OgameWeb.webDriver, TestTabController.class.getName());

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void kliknijUstawieniaSurowcow(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            LeftMenu.pressUstawieniaSurowcow(OgameWeb.webDriver, TestTabController.class.getName());

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void kliknijBadania(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            LeftMenu.pressBadania(OgameWeb.webDriver, TestTabController.class.getName());

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void kliknijSurowce(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            LeftMenu.pressSurowce(OgameWeb.webDriver, TestTabController.class.getName());

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void kliknijRuchFlot(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");

            LeftMenu.pressRuchFlot(OgameWeb.webDriver, TestTabController.class.getName());

            b.setText(text);
            activeFlag = false;
        }
    }

    @FXML
    void wypiszWybranaPlanete(ActionEvent event) {
        if(!activeFlag)
        {
            activeFlag = true;
            Button b = (Button) event.getSource();
            String text = b.getText();
            b.setText("...");


            Log.printLog(TestTabController.class.getName(),"Wybrana planeta: \n" +
                    ListaPlanet.wybranaPlaneta(OgameWeb.webDriver) + "\n");


            b.setText(text);
            activeFlag = false;
        }
    }
    @FXML
    public void initialize()
    {
        textFieldPlanetID.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldPlanetID,2));
        textFieldKsiezycID.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldKsiezycID,2));
        textFieldStatekBojowy.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldStatekBojowy,2));
        textFieldStatekCywilny.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldStatekCywilny,1));
        textFieldIStatekBojowyIlosc.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldIStatekBojowyIlosc,7));
        textFieldIStatekCywilnyIlosc.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldIStatekCywilnyIlosc,7));
        textFieldBudynek.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldBudynek,7));
    }

}
