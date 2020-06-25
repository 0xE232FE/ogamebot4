package app.leaftask;

import app.LeafTask;
import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import app.ruchflot.Lot;
import app.ruchflot.Loty;
import com.Log;
import ogame.LeftMenu;
import ogame.ruchflotzdarzenia.RuchFlotZdarzenia;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;


public class RuchFlot extends LeafTask {

    public RuchFlot(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Ruch flot");
    }

    private CzasWykonania czasWykonania = new CzasWykonania();
    @Override
    public void execute() {
        if (isRun()) {
            if (isSleepTimeOut(System.currentTimeMillis())) {

                LeftMenu.pressRuchFlot(getW(),RuchFlot.class.getName());

                List<Lot> tmp = ogame.ruchflot.RuchFlot.daneLotow(getW());
                updateLoty(tmp);

                setLastTimeExecute(System.currentTimeMillis());
                setSleep(nextTimeExecute(tmp));
                Log.printLog(RuchFlot.class.getName(),"Ustawiono sleep time na "+getSleep() + " msek.");

                for(Lot l : Loty.listaLotow)
                {
                    Log.printLog(RuchFlot.class.getName(),l.toString());
                }

            }

//            Log.printLog(RuchFlot.class.getName(),"Rozpoczynam sprawdzanie czy zmieniła się ilość lotów....");
            if(isMissionsChanged())
            {
                setSleep(0);
            }
//            Log.printLog(RuchFlot.class.getName(),"Zakończyłem sprawdzanie zmiany ilości lotów.");
        }
        else
        {
            if(czasWykonania.ileMinelo(CzasGry.getCzas(),CzasGry.getData()) > 60)
            {
                Log.printLog(RuchFlot.class.getName(), "OFF");
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }

            if(!czasWykonania.isActive())
            {
                czasWykonania.setActive(true);
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }
        }

    }

    /**
     * Aktualizacja danych z zakładki Ruch flot.
     */
    private void updateLoty(List<Lot> lista)
    {
        Loty.listaLotow.clear();
        Loty.listaLotow = lista;
        Loty.czasAktualizacji = CzasGry.string();
    }

    /**
     *  Ustawia za ile sek. ma ponownie wykonać się task. Czas jest pobierany z ruchu flot.
     */
    private long nextTimeExecute(List<Lot> list)
    {
        List<Lot> tmp = new ArrayList<>();

        for(Lot l : list)
        {
            if(l.isWraca() ||  l.getCzasPowrot().getData().toString().equals(CzasGry.getData().toString()))
                tmp.add(l);
        }

        if(tmp.size() == 0 || !tmp.get(0).getCzasPowrot().getData().equals(CzasGry.getData()))
            return Czas.MAX_SECONDS_DAY - CzasGry.getCzas().czasWSekundach();
        else
            return tmp.get(0).getCzasPowrot().getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach();
    }

    private int iloscMisjiWlasnych = -2;

    private boolean isMissionsChanged() {

        int tmp = RuchFlotZdarzenia.iloscMisjiWlasnych(getW());
//        Log.printLog(RuchFlot.class.getName()," ->isMissionsChanged() : Ilość misji -  poprzednie = " +
//                iloscMisjiWlasnych + " aktualne = " + tmp);

        if(tmp != iloscMisjiWlasnych && tmp != -1)
        {
            iloscMisjiWlasnych = tmp;
            return true;
        }
        else
            return false;

    }
}
