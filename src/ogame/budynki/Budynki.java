package ogame.budynki;

public class Budynki {

//    public static final Budynek[] BUDYNKI_MOON =
//            {
//                    new Budynek(1,"Kopalnia metalu", 1),
//                    new Budynek(2,"Kopalnia kryształu", 2),
//                    new Budynek(3,"Ekstraktor deuteru", 3),
//                    new Budynek(4,"Elektrownia słoneczna", 4),
//                    new Budynek(5,"Elektrownia fuzyjna", 12),
//                    new Budynek(6,"Satelita słoneczny", 212),
//                    new Budynek(7,"Magazyn metalu", 22),
//                    new Budynek(8,"Magazyn kryształu", 23),
//                    new Budynek(9,"Zbiornik deuteru", 24),
//            };



//    public static final Budynek [] BUDYNKI =
//            {
//                    new Budynek(1,"Kopalnia metalu", 1),
//                    new Budynek(2,"Kopalnia kryształu", 2),
//                    new Budynek(3,"Ekstraktor deuteru", 3),
//                    new Budynek(4,"Elektrownia słoneczna", 4),
//                    new Budynek(5,"Elektrownia fuzyjna", 12),
//                    new Budynek(6,"Satelita słoneczny", 212),
//                    new Budynek(7,"Pełzacz", 217),
//                    new Budynek(8,"Magazyn metalu", 22),
//                    new Budynek(9,"Magazyn kryształu", 23),
//                    new Budynek(10,"Zbiornik deuteru", 24),
//            };



    private Budynek [] budynki;
    private Budynek [] budynkiMoon;

    public Budynki() {
        budynki = new Budynek[]{
                new Budynek(1,"Kopalnia metalu", 1),
                new Budynek(2,"Kopalnia kryształu", 2),
                new Budynek(3,"Ekstraktor deuteru", 3),
                new Budynek(4,"Elektrownia słoneczna", 4),
                new Budynek(5,"Elektrownia fuzyjna", 12),
                new Budynek(6,"Satelita słoneczny", 212),
                new Budynek(7,"Pełzacz", 217),
                new Budynek(8,"Magazyn metalu", 22),
                new Budynek(9,"Magazyn kryształu", 23),
                new Budynek(10,"Zbiornik deuteru", 24),
                new Budynek(1,"Fabryka robotów", 14),
                new Budynek(2,"Stocznia", 21),
                new Budynek(3,"Laboratorium badawcze", 31),
                new Budynek(4,"Depozyt sojuszniczy", 34),
                new Budynek(5,"Silos rakietowy", 44),
                new Budynek(6,"Fabryka nanitów", 15),
                new Budynek(7,"Terraformer", 33),
                new Budynek(8,"Dok kosmiczny", 36),
        };

        budynkiMoon = new Budynek[]{
                new Budynek(1,"Kopalnia metalu", 1),
                new Budynek(2,"Kopalnia kryształu", 2),
                new Budynek(3,"Ekstraktor deuteru", 3),
                new Budynek(4,"Elektrownia słoneczna", 4),
                new Budynek(5,"Elektrownia fuzyjna", 12),
                new Budynek(6,"Satelita słoneczny", 212),
                new Budynek(7,"Magazyn metalu", 22),
                new Budynek(8,"Magazyn kryształu", 23),
                new Budynek(9,"Zbiornik deuteru", 24),
                new Budynek(1,"Fabryka robotów", 14),
                new Budynek(2,"Stocznia", 21),
                new Budynek(3,"Stacja księżycowa", 41),
                new Budynek(4,"Falang czujników", 42),
                new Budynek(5,"Teleporter", 43),
        };
    }

    /**
     * Zwraca budynek o określonym indexie.
     * @param nr ***
     * @param onMoon Czy jest to na księżycu.
     * @return Budynek lub null, gdy nr jest spoza zakresu.
     */
    public Budynek getBudynekIndex(int nr, boolean onMoon)
    {
        if(onMoon)
        {
            for(Budynek b : budynkiMoon)
            {
                if(b.getIndex() == nr)
                    return b;
            }
        }
        else
        {
            for(Budynek b : budynki)
            {
                if(b.getIndex() == nr)
                    return b;
            }
        }
        return null;
    }

    /**
     * Zwraca budynek o określonej data-techologies.
     * @param dataTechnology ***
     * @param onMoon Czy jest to na księżycu.
     * @return Budynek lub null, gdy nr jest spoza zakresu.
     */
    public Budynek getBudynekDataTechnology(int dataTechnology, boolean onMoon)
    {
        if(onMoon)
        {
            for(Budynek b : budynkiMoon)
            {
                if(b.getDataTechnology() == dataTechnology)
                    return b;
            }
        }
        else
        {
            for(Budynek b : budynki)
            {
                if(b.getDataTechnology() == dataTechnology)
                    return b;
            }
        }
        return null;
    }

    /*
    GETTERS
     */

    public Budynek[] getBudynki() {
        return budynki;
    }

    public Budynek[] getBudynkiMoon() {
        return budynkiMoon;
    }
}
