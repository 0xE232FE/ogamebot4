package test;

import app.czas.Czas;
import app.czas.CzasWykonania;
import app.czas.Data;
import org.junit.Assert;
import org.junit.Test;

public class Autotransport
{
    @Test
    public  void ladownoscMalychTransporterow()
    {
        int iloscMT = 825000/(5000 +(13*250));
        Assert.assertEquals(iloscMT+1,101);
        iloscMT = 826000/(5000 +(13*250));
        Assert.assertEquals(iloscMT+1,101);
        iloscMT = 800000/(5000 +(12*250));
        Assert.assertEquals(iloscMT+1,101);
    }

    @Test
    public  void ladownoscDuzychTransporterow()
    {
        int iloscDT = 4125000/(25000 +(13*1250));
        Assert.assertEquals(iloscDT+1,101);
        iloscDT = 4135000/(25000 +(13*1250));
        Assert.assertEquals(iloscDT+1,101);
        iloscDT = 4000000/(25000 +(12*1250));
        Assert.assertEquals(iloscDT+1,101);
    }

    @Test
    public void obliczPozostalyCzasDoWykonania()
    {
        CzasWykonania  aktualnyCzas = new CzasWykonania();
        aktualnyCzas.setCzasString("18:00:00");
        aktualnyCzas.setDataString("01.01.2020");

        //Sprawdza poprawność zapisania czasu w sekundach.
        Assert.assertEquals(64800,aktualnyCzas.getCzas().czasWSekundach());
        //Sprawdza czy wylicza prawidłowo ile czasu mineło.
        //1
        Assert.assertEquals(10800,aktualnyCzas.ileMinelo(new Czas("21:00:00"),new Data("01.01.2020")));
        //2
        Assert.assertEquals(21600,aktualnyCzas.ileMinelo(new Czas("00:00:00"),new Data("02.01.2020")));
        //3
        Assert.assertEquals(28800,aktualnyCzas.ileMinelo(new Czas("02:00:00"),new Data("02.01.2020")));
        //4
        Assert.assertEquals(21610,aktualnyCzas.ileMinelo(new Czas("00:00:10"),new Data("02.01.2020")));

        CzasWykonania czasNastepnegoWykonania  = CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(), new Czas(12,0,0));
        //Sprawdzenie obliczonego następnego czasu wykonania
        Assert.assertEquals("06:00:00",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(12,0,0)).getCzas().toString());
        //Obliczanie pozostałego czasu.
        //1
        Assert.assertEquals("06:00:00",czasNastepnegoWykonania.pozostaloCzasu(new Czas("00:00:00"),new Data("02.01.2020")).toString());
        //2
        Assert.assertEquals("06:30:00",czasNastepnegoWykonania.pozostaloCzasu(new Czas("23:30:00"),new Data("01.01.2020")).toString());
        //3
        Assert.assertEquals("00:00:01",czasNastepnegoWykonania.pozostaloCzasu(new Czas("05:59:59"),new Data("02.01.2020")).toString());
        //4
        Assert.assertEquals(-59,czasNastepnegoWykonania.pozostaloCzasu(new Czas("06:00:59"),new Data("02.01.2020")).czasWSekundach());
        //Sprawdzenie czy po minięciu czasu wartość będzie mniejsza od 0.
        Assert.assertTrue(czasNastepnegoWykonania.pozostaloCzasu(new Czas("06:00:59"),new Data("02.01.2020")).czasWSekundach() < 0);
    }

    @Test
    public void dodawanieCzasu()
    {
        CzasWykonania  aktualnyCzas = new CzasWykonania();
        aktualnyCzas.setCzasString("18:00:00");
        aktualnyCzas.setDataString("01.01.2020");

        //Dodawanie czasu do aktualnego
        //1
        Assert.assertEquals("01.01.2020",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(1,0,0)).getData().toString());
        //2
        Assert.assertEquals("19:00:00",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(1,0,0)).getCzas().toString());
        //3
        Assert.assertEquals("01.01.2020",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(1,30,0)).getData().toString());
        //4
        Assert.assertEquals("19:30:00",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(1,30,0)).getCzas().toString());
        //5
        Assert.assertEquals("01.01.2020",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(1,30,30)).getData().toString());
        //6
        Assert.assertEquals("19:30:30",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(1,30,30)).getCzas().toString());
        //7
        Assert.assertEquals("02.01.2020",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(6,0,0)).getData().toString());
        //8
        Assert.assertEquals("00:00:00",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(6,0,0)).getCzas().toString());
        //9
        Assert.assertEquals("02.01.2020",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(6,0,0)).getData().toString());
        //10
        Assert.assertEquals("06:00:00",
                CzasWykonania.okreslCzasWykonania(aktualnyCzas.getCzas(),aktualnyCzas.getData(),new Czas(12,0,0)).getCzas().toString());
    }
}
