package test

import app.czas.Czas
import app.czas.CzasGry
import com.Log
import ogame.planety.Planeta
import ogame.ruchflotzdarzenia.WrogaMisja
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FleetSaveAttackTest extends GroovyTestCase
{
//    public static final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
//    public static final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)
    public static final Planeta P_3 = new Planeta(3, "[1:1:3]","P_3", true,3)

//    @Before
//    void test()
//    {
//        CzasGry.setCzasString("23:30:00")
//        CzasGry.setDataString("13.06.2020")
//
//    }

    public static final WrogaMisja WROGA_MISJA_1 = new WrogaMisja(new Czas("23:40:00"),true,"[1:1:1]",1)
    public static final WrogaMisja WROGA_MISJA_2 = new WrogaMisja(new Czas("23:40:00"),false,"[1:1:2]",2)


    /*
    POJEDYŃCZE ATAKI - SPRAWDZANIE WYSYŁANIA FS'A
     */
    /**
     * Atak tego samego dnia na planetę. Sprawdza czas i datę wysłania FS'a
     * MANUALNY - OK
     */
    @Test
    void test_1()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_1)

        Assert.assertEquals("23:37:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())

    }

    /**
     * Atak tego samego dnia na księzyc. Sprawdza czas i datę wysłania FS'a
     * MANULANY
     */
    @Test
    void test_2()
    {
        final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_2.setMoonAttack(true)
        P_2.getMoonFS().add(WROGA_MISJA_2)

        Assert.assertEquals("23:37:00",P_2.getMoonFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_2.getMoonFS().getWyslij().getData().toString())
    }

    /**
     * Atak następnego samego dnia na planetę. FS następnego dnia. Sprawdza czas i datę wysłania FS'a
     * * MANULANY
     */

    @Test
    void test_3()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("00:04:00"),true,"[1:1:1]",3)

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("00:01:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("14.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
    }

    /**
     * Atak następnego dnia na księzyc. FS następnego dnia. Sprawdza czas i datę wysłania FS'a
     * * MANULANY
     */
    @Test
    void test_4()
    {
        final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("00:04:00"),false,"[1:1:2]",4)

        P_2.setMoonAttack(true)
        P_2.getMoonFS().add(WROGA_MISJA_4)

        Assert.assertEquals("00:01:00",P_2.getMoonFS().getWyslij().getCzas().toString())
        Assert.assertEquals("14.06.2020",P_2.getMoonFS().getWyslij().getData().toString())
    }

    /**
     * Atak następnego samego dnia na planetę. FS poprzedniego dnia. Sprawdza czas i datę wysłania FS'a
     * * MANULANY
     */

    @Test
    void test_5()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("00:01:00"),true,"[1:1:1]",3)

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("23:58:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())

    }

    /**
     * Atak następnego dnia na księzyc. FS poprzedniego dnia. Sprawdza czas i datę wysłania FS'a
     * * MANULANY
     */
    @Test
    void test_6()
    {
        final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("00:01:00"),false,"[1:1:2]",4)

        P_2.setMoonAttack(true)
        P_2.getMoonFS().add(WROGA_MISJA_4)

        Assert.assertEquals("23:58:00",P_2.getMoonFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_2.getMoonFS().getWyslij().getData().toString())
    }

    /*
    POJEDYŃCZE ATAKI - SPRAWDZANIE ZAWRÓCENIA FS'A
     */
    /**
     * Atak tego samego dnia na planetę. Sprawdza czas i datę zawrócenia FS'a
     * * MANULANY
     */
    @Test
    void test_7()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        final WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("23:40:00"),true,"[1:1:1]",1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("23:43:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())

    }

    /**
     * Atak tego samego dnia na księzyc. Sprawdza czas i datę zawrócenia FS'a.
     * * MANULANY
     */
    @Test
    void test_8()
    {
        final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)
        WrogaMisja WROGA_MISJA_2 = new WrogaMisja(new Czas("23:40:00"),false,"[1:1:2]",4)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_2.setMoonAttack(true)
        P_2.getMoonFS().add(WROGA_MISJA_2)

        Assert.assertEquals("23:43:00",P_2.getMoonFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_2.getMoonFS().getZawroc().getData().toString())
    }

    /**
     * Atak następnego  dnia na planetę. Zawrócenie  FS następnego dnia. Sprawdza czas i datę zawrócenia FS'a
     * * MANULANY
     */

    @Test
    void test_9()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("00:06:00"),true,"[1:1:1]",3)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")



        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("00:09:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("14.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())
    }

    /**
     * Atak następnego dnia na księzyc. Zawrócenie FS następnego dnia. Sprawdza czas i datę zawrócenia FS'a
     * * MANULANY
     */
    @Test
    void test_10()
    {
        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)
        WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("00:06:00"),false,"[1:1:2]",4)


        P_2.setMoonAttack(true)
        P_2.getMoonFS().add(WROGA_MISJA_4)

        Assert.assertEquals("00:09:00",P_2.getMoonFS().getZawroc().getCzas().toString())
        Assert.assertEquals("14.06.2020",P_2.getMoonFS().getZawroc().getData().toString())
    }

    /**
     * Atak tego samego dnia na planetę. Zawrócenie następnego dnia . Sprawdza czas i datę zawrócenia FS'a
     * * MANULANY
     */

    @Test
    void test_11()
    {
        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("23:59:20"),true,"[1:1:1]",3)

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("00:02:20",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("14.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())

    }

    /**
     * Atak poprzedniego dnia na księzyc. Zawrocenie FS następnego dnia. Sprawdza czas i datę zawrocenia FS'a
     * * MANULANY
     */
    @Test
    void test_12()
    {
        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        final Planeta P_2 = new Planeta(2, "[1:1:2]","P_2", true,2)
        WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("23:58:00"),false,"[1:1:2]",4)

        P_2.setMoonAttack(true)
        P_2.getMoonFS().add(WROGA_MISJA_4)

        Assert.assertEquals("00:01:00",P_2.getMoonFS().getZawroc().getCzas().toString())
        Assert.assertEquals("14.06.2020",P_2.getMoonFS().getZawroc().getData().toString())
    }

    /*
    KILKA ATAKÓW - SPRAWDZANIE ZAWRÓCENIA FS'A
     */
    /**
     * Dwa ataki tego samego dnia. Drugi wchodzi po pierwszym. Odstęp czasu mniej niż 10 min.
     * * MANULANY
     */
    @Test
    void test13()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        final WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("23:40:00"),true,"[1:1:1]",1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("23:37:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:43:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())

        final WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("23:47:00"),true,"[1:1:1]",2)
        P_1.getPlanetFS().add(WROGA_MISJA_4)

        Assert.assertEquals("23:37:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:50:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())
    }

    /**
     * Dwa ataki tego samego dnia. Drugi wchodzi przed pierwszym. Odstęp czasu mniej niż 10 min.
     * * MANULANY 
     */
    @Test
    void test14()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        final WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("23:40:00"),true,"[1:1:1]",1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("23:37:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:43:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())

        final WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("23:36:00"),true,"[1:1:1]",2)
        P_1.getPlanetFS().add(WROGA_MISJA_4)

        Assert.assertEquals("23:33:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:43:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())
    }

    /**
     * Dwa ataki tego samego dnia. Drugi wchodzi po pierwszym. Odstęp czasu więcej niż 10 min.
     */
    @Test
    void test15()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        final WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("23:36:00"),true,"[1:1:1]",1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("23:33:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:39:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())

        final WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("23:53:00"),true,"[1:1:1]",2)
        P_1.getPlanetFS().add(WROGA_MISJA_4)

        Assert.assertEquals("23:50:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:56:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())
    }

    /**
     * Dwa ataki tego samego dnia. Drugi wchodzi przed pierwszym. Odstęp czasu więcej niż 10 min.
     */
    @Test
    void test16()
    {
        final Planeta P_1 = new Planeta(1, "[1:1:1]","P_1", false,1)
        final WrogaMisja WROGA_MISJA_3 = new WrogaMisja(new Czas("23:52:00"),true,"[1:1:1]",1)

        CzasGry.setCzasString("23:30:00")
        CzasGry.setDataString("13.06.2020")

        P_1.setAttack(true)
        P_1.getPlanetFS().add(WROGA_MISJA_3)

        Assert.assertEquals("23:49:00",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:55:00",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())

        final WrogaMisja WROGA_MISJA_4 = new WrogaMisja(new Czas("23:35:59"),true,"[1:1:1]",2)
        P_1.getPlanetFS().add(WROGA_MISJA_4)

        Assert.assertEquals("23:32:59",P_1.getPlanetFS().getWyslij().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getWyslij().getData().toString())
        Assert.assertEquals("23:38:59",P_1.getPlanetFS().getZawroc().getCzas().toString())
        Assert.assertEquals("13.06.2020",P_1.getPlanetFS().getZawroc().getData().toString())
    }
}
