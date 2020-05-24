package app;

import java.io.Serializable;

public class Wspolrzedne implements Serializable {

    private static final long serialVersionUID = 1L;
    private int galaktyka;
    private int uklad;
    private int planeta;
    private int id;

    public Wspolrzedne(String wspolrzedne)
    {
        StringBuilder sb = new StringBuilder(wspolrzedne);

        int a = sb.indexOf(":");
        int b = sb.lastIndexOf(":");
        int l = sb.length();

        galaktyka = Integer.valueOf(sb.substring(1,a));
        uklad = Integer.valueOf(sb.substring(a+1,b));
        planeta = Integer.valueOf(sb.substring(b+1,l-1));

        String tmp = sb.substring(1,a) + sb.substring(a+1,b) + sb.substring(b+1,l-1);
        id = Integer.valueOf(tmp);
    }

    public Wspolrzedne(int galaktyka, int uklad, int planeta)
    {
        this.galaktyka = galaktyka;
        this.uklad = uklad;
        this.planeta = planeta;
    }

    public int getGalaktyka() {
        return galaktyka;
    }

    public int getUklad() {
        return uklad;
    }

    public int getPlaneta() {
        return planeta;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "["+galaktyka+":"+uklad+":"+planeta+"]";
    }

    @Override
    public boolean equals(Object obj) {
        Wspolrzedne w = (Wspolrzedne) obj;
        return this.id == w.getId();
    }
}
