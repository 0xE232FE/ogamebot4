package app.leaftask;

import app.LeafTask;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import com.Log;
import ogame.planety.ListaPlanet;
import ogame.planety.Planeta;
import org.openqa.selenium.WebDriver;

public class Planety extends LeafTask {

    private CzasWykonania czasWykonania = new CzasWykonania();

    public Planety(WebDriver w, int index, long sleep) {
        super(w, index, sleep, "Planety");
    }


    @Override
    public void execute() {

        if (isRun()) {
            if (isSleepTimeOut(System.currentTimeMillis()))
            {
                if(app.planety.Planety.init)
                {
                    init();
                    app.planety.Planety.init = false;
                }
                else
                {
                    update();
//                    app.planety.Planety.print();
                    setSleep(5*60*1000);
                }
                setLastTimeExecute(System.currentTimeMillis());
            }
        }
        else
        {
            if(czasWykonania.ileMineło(CzasGry.getCzas(),CzasGry.getData()) > 60)
            {
                Log.printLog(Planety.class.getName(), "OFF");
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

    private void init()
    {
        int a = ListaPlanet.iloscPlanet(getW());
        int id;
        String wspolrzedne, nazwa;
        boolean moon;
        Planeta p;
        for(int i = 1; i <= a; i++)
        {
            id = ListaPlanet.planetID(getW(),i);
            wspolrzedne = ListaPlanet.wspolrzedne(getW(),i);
            nazwa = ListaPlanet.nazwa(getW(),i);
            moon = ListaPlanet.posiadaKsiezyc(getW(),i);

            p = new Planeta(i,wspolrzedne,nazwa,moon,id);

            p.setAttack(ListaPlanet.atak(getW(),i));
            p.setConstructionInProgress(ListaPlanet.rozbudowaPlanety(getW(),i));
            p.setMoonConstructionInProgress(ListaPlanet.rozbudowaKsiezyca(getW(),i));
            p.setSelected(ListaPlanet.wybranaPlaneta(getW(),i));
            p.setMoonSelected(ListaPlanet.wybranyKsiezyc(getW(),i));

            app.planety.Planety.add(p);
        }
    }

    private void update()
    {
        int i = 1;
        for(Planeta p : app.planety.Planety.planety)
        {
            p.setAttack(ListaPlanet.atak(getW(),i));
            p.setConstructionInProgress(ListaPlanet.rozbudowaPlanety(getW(),i));
            p.setMoonConstructionInProgress(ListaPlanet.rozbudowaKsiezyca(getW(),i));
            p.setSelected(ListaPlanet.wybranaPlaneta(getW(),i));
            p.setMoonSelected(ListaPlanet.wybranyKsiezyc(getW(),i));
            i++;
        }
    }
}
