package ogame.flota;

import com.DifferentMethods;

public class Flota
{
    public static Statek lm = new Statek("Lekki myśliwiec","LM");
    public static Statek cm = new Statek("Ciężki myśliwiec","CM");
    public static Statek kr = new Statek("Krążownik","KR");
    public static Statek ow = new Statek("Okręt wojenny","OW");
    public static Statek pc = new Statek("Pancernik","PC");
    public static Statek bomb = new Statek("Bombowiec","BOMB");
    public static Statek niszcz = new Statek("Niszczyciel","NISZCZ");
    public static Statek gs = new Statek("Gwiazda śmierci","GS");
    public static Statek rozp = new Statek("Rozpruwacz","ROZP");
    public static Statek mt = new Statek("Mały transporter","MT");
    public static Statek dt = new Statek("Duży transporter","DT");
    public static Statek sk = new Statek("Statek kolonizacyjny","SK");
    public static Statek rec = new Statek("Recykler","REC");
    public static Statek ss = new Statek("Sonda szpiegowska","OW");
    
    public static class Statek
    {
        private final String nazwa;
        private final String nazwaKrotka;
        private int ilosc = -1;

        Statek(String nazwa, String nazwaKrotka) {
            this.nazwa = nazwa;
            this.nazwaKrotka = nazwaKrotka;
        }

        public void setIlosc(int ilosc) {
            this.ilosc = ilosc;
        }

        public String getNazwa() {
            return nazwa;
        }

        public String getNazwaKrotka() {
            return nazwaKrotka;
        }

        public int getIlosc() {
            return ilosc;
        }

        @Override
        public String toString() {
            int dl = 25;

            String sb = "\n";
            sb += DifferentMethods.initVariable("Nazwa statku ",dl)+nazwa+
                    "\n" +
                    DifferentMethods.initVariable("Skrócona nazwa statku ",dl) + nazwaKrotka +
                    "\n" +
                    DifferentMethods.initVariable("Ilość ",dl) + ilosc +
                    "\n";

            return sb;
        }
    }
}
