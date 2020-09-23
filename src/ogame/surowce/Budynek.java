package ogame.surowce;

public class Budynek
{
    private final int index;
    private final String name;
    private final int dataTechnology;

    public Budynek(int index, String name, int dataTechnology) {
        this.index = index;
        this.name = name;
        this.dataTechnology = dataTechnology;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getDataTechnology() {
        return dataTechnology;
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
