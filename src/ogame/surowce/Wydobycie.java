package ogame.surowce;

import com.DifferentMethods;

public class Wydobycie
{
    private final int metalINT, krysztalINT ,deuterINT , wolanEnergiaINT;
    private final String metal, krysztal, deuter, wolnaEnergia;

    public Wydobycie(String metal, String krysztal, String deuter, String wolnaEnergia) {
        this.metal = metal;
        this.krysztal = krysztal;
        this.deuter = deuter;
        this.wolnaEnergia = wolnaEnergia;

        metalINT = Integer.valueOf(DifferentMethods.deleteChars('.',metal));
        krysztalINT = Integer.valueOf(DifferentMethods.deleteChars('.',krysztal));
        deuterINT = Integer.valueOf(DifferentMethods.deleteChars('.',deuter));
        wolanEnergiaINT = Integer.valueOf(DifferentMethods.deleteChars('.',wolnaEnergia));

    }

    public String getMetal() {
        return metal;
    }

    public String getKrysztal() {
        return krysztal;
    }

    public String getDeuter() {
        return deuter;
    }

    public String getWolnaEnergia() {
        return wolnaEnergia;
    }

    public int getMetalINT() {
        return metalINT;
    }

    public int getKrysztalINT() {
        return krysztalINT;
    }

    public int getDeuterINT() {
        return deuterINT;
    }

    public int getWolanEnergiaINT() {
        return wolanEnergiaINT;
    }
}
