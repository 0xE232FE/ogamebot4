package app.gui.imperium;

import app.czas.CzasGry;
import app.planety.Planety;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import ogame.budynki.Budynek;
import ogame.planety.Planeta;
import ogame.surowce.Wydobycie;

import java.util.ArrayList;
import java.util.List;

public class ImperiumController
{
    private static List<Imperium> imperiumList = new ArrayList<>();

    @FXML
    private HBox hBoxImperium;

    @FXML
    private Label labelOstatniaAktualizacja;

    @FXML
    private Label labelPozostalo;

    public void update()
    {
        if(!Planety.init)
        {
            //Dodawanie do GUI danych o planetach.
            if(imperiumList.size() == 0)
            {
                for(Planeta p : Planety.planety)
                    imperiumList.add(new Imperium(p));

                for(Imperium imperium : imperiumList)
                {
                    Separator separator = new Separator();
                    separator.setOrientation(Orientation.VERTICAL);
                    hBoxImperium.getChildren().add(imperium.getVBox());
                    hBoxImperium.getChildren().add(separator);
                }
            }
        }


        if(app.leaftask.Imperium.updateGUI)
        {

            for(Imperium imperium : imperiumList)
            {
                Budynek[] budynki = imperium.getPlaneta().getBudynki().getBudynki();
                ImperiumPlanetaController controller = imperium.getController();

                //Update danych o budynkach produkcyjnych.
                for(Budynek b : budynki)
                {
                    int a = b.getDataTechnology();
                    switch (a)
                    {
                        case 1:
                            controller.getLabelKopalniaMetalu().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelKopalniaMetalu());
                            break;
                        case 2:
                            controller.getLabelKopalniaKrysztalu().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelKopalniaKrysztalu());
                            break;
                        case 3:
                            controller.getLabelEkstraktorDeuteru().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelEkstraktorDeuteru());
                            break;
                        case 4:
                            controller.getLabelElektrowniaSloneczna().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelElektrowniaSloneczna());
                            break;
                        case 12:
                            controller.getLabelElektrowniaFuzyjna().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelElektrowniaFuzyjna());
                            break;
                        case 14:
                            controller.getLabelFabrrykaRobotow().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelFabrrykaRobotow());
                            break;
                        case 15:
                            controller.getLabelFabrykaNanitow().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelFabrykaNanitow());
                            break;
                        case 21:
                            controller.getLabelStocznia().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelStocznia());
                            break;
                        case 22:
                            controller.getLabelMagazynMetalu().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelMagazynMetalu());
                            break;
                        case 23:
                            controller.getLabelMagazynKrysztalu().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelMagazynKrysztalu());
                            break;
                        case 24:
                            controller.getLabelZbiornikDeuteru().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelZbiornikDeuteru());
                            break;
                        case 31:
                            controller.getLabelLaboratoirumBadawcze().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelLaboratoirumBadawcze());
                            break;
                        case 33:
                            controller.getLabelTerraformer().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelTerraformer());
                            break;
                        case 34:
                            controller.getLabelDepozytSojuszniczy().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelDepozytSojuszniczy());
                            break;
                        case 36:
                            controller.getLabelDokKosmiczny().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelDokKosmiczny());
                            break;
                        case 44:
                            controller.getLabelSilosRakietowy().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelSilosRakietowy());
                            break;
                        case 212:
                            controller.getLabelSatelitaSloneczny().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelSatelitaSloneczny());
                            break;
                        case 217:
                            controller.getLabelPelzacz().setText(String.valueOf(b.getLevel()));
                            setLabeLBackground(b.getStatus(),controller.getLabelPelzacz());
                            break;
                    }
                }

                //Update danych o wydobyciu.
                Wydobycie wydobycie = imperium.getPlaneta().getWydobycie();
                if(wydobycie != null)
                {
                    controller.getLabelMetal().setText(wydobycie.getMetal());
                    controller.getLabelKrysztal().setText(wydobycie.getKrysztal());
                    controller.getLabelDeuter().setText(wydobycie.getDeuter());
                    controller.getLabelWolnaEnergia().setText(wydobycie.getWolnaEnergia());
                    if(wydobycie.getWolanEnergiaINT() < 0)
                        controller.getLabelWolnaEnergia().setStyle("-fx-background-color: tomato");
                }
            }
            app.leaftask.Imperium.updateGUI = false;
        }
    }

    //Update czasu wykonania.
    public void updateTime()
    {
        if(app.leaftask.Imperium.czasPobraniaDanych.isActive())
            labelOstatniaAktualizacja.setText(app.leaftask.Imperium.czasPobraniaDanych.toString());

        if(app.leaftask.Imperium.czasKolejnegoWykonania.isActive())
            labelPozostalo.setText(app.leaftask.Imperium.czasKolejnegoWykonania.pozostaloCzasu(CzasGry.getCzas(),CzasGry.getData()).toString());
    }

    private void setLabeLBackground(int status, Label label)
    {
        switch (status)
        {
            case 1:
                label.setStyle("-fx-background-color: springgreen");
                break;
            case 2:
                label.setStyle("-fx-background-color: lightgray");
                break;
            case 3:
                label.setStyle("-fx-background-color: tomato");
                break;
            case 4:
                label.setStyle("-fx-background-color: orange");
                break;
        }
    }
}
