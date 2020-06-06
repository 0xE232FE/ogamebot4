package ogame.ruchflotzdarzenia;

import app.czas.Czas;
import app.czas.Data;

public class Misja
{
    private final Czas czasWykonania;
    private final Data dataWykonania;
    private final int typMisji;
    private final boolean wraca;
    private final int id;
    private final boolean misjaZKsiezyca;
    private final boolean misjaZPlanety;
    private final String wspolrzedneStartu;
    private final int iloscStatkow;
    private final String cel; // title="Planeta","Bezgraniczna dal"
    private final String wspolrzedneCelu;


    public Misja(Czas czasWykonania, Data dataWykonania, int typMisji, boolean wraca, int id, boolean misjaZKsiezyca,
                 boolean misjaZPlanety, String wspolrzedneStartu, int iloscStatkow, String cel, String wspolrzedneCelu)
    {
        this.czasWykonania = czasWykonania;
        this.dataWykonania = dataWykonania;
        this.typMisji = typMisji;
        this.wraca = wraca;
        this.id = id;
        this.misjaZKsiezyca = misjaZKsiezyca;
        this.misjaZPlanety = misjaZPlanety;
        this.wspolrzedneStartu = wspolrzedneStartu;
        this.iloscStatkow = iloscStatkow;
        this.cel = cel;
        this.wspolrzedneCelu = wspolrzedneCelu;
    }

    /**
     * Zwraca nazwę misji.
     * @param i Misja określona jak liczba
     * @return Nazwa misji.
     */
    String misja(int i)
    {
        String s = "";
        switch (i)
        {
            case 1:
                s = "Atakuj";
                break;
            case 3:
                s = "Transportuj";
                break;
            case 8:
                s = "Recykluj pola zniszczeń";
                break;
            case 15:
                s = "Ekspedycja";
                break;
        }
        return s;
    }
}
