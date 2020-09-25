package ogame.budynki;

public class Budynek
{
    private final int index;
    private final String name;
    private final int dataTechnology;
    private int level = 0;
    private int status = 0;

    public Budynek(int index, String name, int dataTechnology) {
        this.index = index;
        this.name = name;
        this.dataTechnology = dataTechnology;
    }

    /*
    SETTERS
     */

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /*
            GETTERS
             */
    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getDataTechnology() {
        return dataTechnology;
    }

    public int getLevel() {
        return level;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Budynek{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", dataTechnology=" + dataTechnology +
                '}';
    }
}
