package app.ruchflot;

import app.OgameWeb;
import com.Log;
import ogame.ruchflot.RuchFlot;

import java.util.ArrayList;
import java.util.List;

/**
 * Przechowuje dane z zakładki <b>Ruch flot</b>.
 */
public class Loty
{
    public static String czasAktualizacji = "";
    public static List<Lot> listaLotow = new ArrayList<>();

    /**
     * Zwraca lot o wskazanych parametrach z listy  lotów.
     * @param misja Rodzaj misji. Np. Transportuj
     * @param wspolrzedneStartu Współrzędne obiektu startowego.
     * @param obiektStart Obiekt startu, jeżeli 1 to  z księżyca, a 0 to z planety.
     * @param wspolrzedneCelu Współrzędne  obiektu celu.
     * @return Lot lub null, jeżeli nie ma w liście.
     */
    public static Lot getLot(String misja, String wspolrzedneStartu, int obiektStart, String wspolrzedneCelu)
    {
        for (Lot l : listaLotow)
        {
            boolean check0 = l.getRodzaj().equals(misja);
            boolean check1 = l.getStart().equals(wspolrzedneStartu);
            boolean check2 = (l.iszKsiezyca() ? 1:0) == obiektStart;
            boolean check3 = l.getCel().equals(wspolrzedneCelu);

            if(check0 && check1 && check2 && check3)
                return l;
        }
        return null;
    }

    /**
     * Zwraca lot o podanych parametrach
     * @param wspolrzedneStartu Współrzędna obiektu startowego.
     * @param wspolrzedneCelu Współrzędna obiektu celu.
     * @param obiektStart 1 - ksiezyc, 0 - planeta
     * @param obiektCel 1 - ksiezyc, 0 - planeta
     * @return Lot lub null, jeżeli nie ma lotu o podanych parametrach.
     */
    public static Lot getLot(String wspolrzedneStartu, String wspolrzedneCelu, int obiektStart, int obiektCel)
    {

        for (Lot l : RuchFlot.daneLotow(OgameWeb.webDriver))
        {
//            Log.printLog1(l.toString(),Loty.class,47);
            boolean check0 = l.getStart().equals(wspolrzedneStartu);
            boolean check1 = l.getCel().equals(wspolrzedneCelu);
            boolean check2 = (l.iszKsiezyca() ? 1:0) == obiektStart;
            boolean check3 = (l.isNaKsiezyc() ? 1:0) == obiektCel;

            if(check0 && check1 && check2 && check3)
                return l;
        }
        return null;
    }

    public static List<Lot> getLoty(String nazwaMisji)
    {
        List<Lot> tmp = new ArrayList<>();
        for (Lot l : listaLotow)
        {
            if(l.getRodzaj().equals(nazwaMisji))
                tmp.add(l);
        }

        return tmp;
    }
}
