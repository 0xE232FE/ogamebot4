package ogame.flota;

import com.DifferentMethods;

import java.io.Serializable;

public class Flota implements Serializable
{
    public Statek lm = new Statek("Lekki myśliwiec","LM", 1, true);
    public Statek cm = new Statek("Ciężki myśliwiec","CM", 2, true);
    public Statek kr = new Statek("Krążownik","KR", 3, true);
    public Statek ow = new Statek("Okręt wojenny","OW", 4, true);
    public Statek panc = new Statek("Pancernik","PC", 5, true);
    public Statek bomb = new Statek("Bombowiec","BOMB", 6, true);
    public Statek niszcz = new Statek("Niszczyciel","NISZCZ", 7, true);
    public Statek gs = new Statek("Gwiazda śmierci","GS", 8, true);
    public Statek roz = new Statek("Rozpruwacz","ROZP", 9, true);
    public Statek pion = new Statek("Pionier","PION", 10, true);
    public Statek mt = new Statek("Mały transporter","MT", 1, false);
    public Statek dt = new Statek("Duży transporter","DT", 2, false);
    public Statek sk = new Statek("Statek kolonizacyjny","SK", 3, false);
    public Statek rec = new Statek("Recykler","REC", 4, false);
    public Statek ss = new Statek("Sonda szpiegowska","OW", 5, false);

    public Statek [] statki = {
            lm,cm,kr,ow, panc,bomb,niszcz,gs, roz,pion,mt,dt,sk,rec,ss
    };

    /*
    GETTERS
     */

    public Statek[] getStatki() {
        return statki;
    }

    public Statek getLm() {
        return lm;
    }

    public Statek getCm() {
        return cm;
    }

    public Statek getKr() {
        return kr;
    }

    public Statek getOw() {
        return ow;
    }

    public Statek getPanc() {
        return panc;
    }

    public Statek getBomb() {
        return bomb;
    }

    public Statek getNiszcz() {
        return niszcz;
    }

    public Statek getGs() {
        return gs;
    }

    public Statek getRoz() {
        return roz;
    }

    public Statek getPion() {
        return pion;
    }

    public Statek getMt() {
        return mt;
    }

    public Statek getDt() {
        return dt;
    }

    public Statek getSk() {
        return sk;
    }

    public Statek getRec() {
        return rec;
    }

    public Statek getSs() {
        return ss;
    }
    /*
       SETTERS
     */
    public void setLm(int i) {
        this.lm.setIlosc(i);
    }

    public void setCm(int i) {
        this.cm.setIlosc(i);
    }

    public void setKr(int i) {
        this.kr.setIlosc(i);
    }
    public void setPanc(int i) {
        this.panc.setIlosc(i);
    }
    public void setBomb(int i) {
        this.bomb.setIlosc(i);
    }

    public void setOw(int i) {
        this.ow.setIlosc(i);
    }

    public void setNiszcz(int i) {
        this.niszcz.setIlosc(i);
    }
    public void setGs(int i) {
        this.gs.setIlosc(i);
    }

    public void setRoz(int i) {
        this.roz.setIlosc(i);
    }

    public void setPion(int i) {
        this.pion.setIlosc(i);
    }

    public void setSs(int i) {
        this.ss.setIlosc(i);
    }

    public void setRec(int i) {
        this.rec.setIlosc(i);
    }

    public void setSk(int i) {
        this.sk.setIlosc(i);
    }

    public void setDt(int i) {
        this.dt.setIlosc(i);
    }

    public void setMt(int i) {
        this.mt.setIlosc(i);
    }


    public static class Statek implements Serializable
    {
        private final String nazwa;
        private final String nazwaKrotka;
        private final int pozycjaNaLiscie;
        private final boolean military;
        private int ilosc = -1;

        Statek(String nazwa, String nazwaKrotka, int pozycjaNaLiscie, boolean military) {
            this.nazwa = nazwa;
            this.nazwaKrotka = nazwaKrotka;
            this.pozycjaNaLiscie = pozycjaNaLiscie;
            this.military = military;
        }

        /*
        SETTERS
         */
        public void setIlosc(int ilosc) {
            this.ilosc = ilosc;
        }

        /*
        GETTERS
         */
        public String getNazwa() {
            return nazwa;
        }

        public String getNazwaKrotka() {
            return nazwaKrotka;
        }

        public int getIlosc() {
            return ilosc;
        }

        public int getPozycjaNaLiscie() {
            return pozycjaNaLiscie;
        }

        public boolean isMilitary() {
            return military;
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
