package ogame.ruchflot;

public class Misje
{
    private static final Misja STACJONUJ = new Misja(-1,"Stacjonuj");
    private static final Misja EKSPEDYCJA = new Misja(15,"Ekspedycja");
    private static final Misja KOLONIZUJ = new Misja(7,"Kolonizuj");
    private static final Misja TANSPORTUJ = new Misja(-1,"Transportuj");
    private static final Misja RECYKLUJ_POLA = new Misja(-1,"Recykluj pola");
    private static final Misja SZPIEGUJ = new Misja(-1,"Szpieguj");
    private static final Misja ZATRZYMAJ = new Misja(-1,"Zatrzymaj");
    private static final Misja ATAKUJ = new Misja(-1,"Atakuj");
    private static final Misja ATAK_LACZONY = new Misja(-1,"Atak łączony");
    private static final Misja NISZCZ = new Misja(-1,"Niszcz");

    private static final Misja [] misje = {
            STACJONUJ,
            EKSPEDYCJA,
            KOLONIZUJ,
            TANSPORTUJ,
            RECYKLUJ_POLA,
            SZPIEGUJ,
            ZATRZYMAJ,
            ATAKUJ,
            ATAK_LACZONY,
            NISZCZ
            };
    /**
     * Zwraca obiekt <b>Misja</b> na podstawie jej nazwy.
     * @param type 1,2 - 15 itp.
     * @return Jeżeli nie znajdzie misji zwróci obiekt, który w nazwie będzie miał komunikat.
     */
    public static Misja getMission(int type)
    {
        for(Misja m : misje)
        {
            if(m.getNr() == type)
                return m;
        }
        return new Misja(-1,"Nieznany typ misji");
    }

    /**
     * Zwraca obiekt <b>Misja</b> na podstawie jej nazwy.
     * @param nazwaMisji Transportuj,Kolonizuj itp.
     * @return Jeżeli nie znajdzie misji zwróci obiekt, który w nazwie będzie miał komunikat.
     */
    public static Misja getMission(String nazwaMisji)
    {
        for(Misja m : misje)
        {
            if(m.getName().equals(nazwaMisji))
                return m;
        }
        return new Misja(-1,"Nieznana nazwa misji");
    }

    static class Misja
    {
        private final int nr;
        private final String name;

        Misja(int nr, String name) {
            this.nr = nr;
            this.name = name;
        }

        /*
        GETTERS
         */
        public int getNr() {
            return nr;
        }

        public String getName() {
            return name;
        }
    }
}
