package ogame;

public class SciezkaWebElementu {
    private String part1;
    private String part2;
    private String var;

    public SciezkaWebElementu(String part1, String part2, String var) {
        this.part1 = part1;
        this.part2 = part2;
        this.var = var;
    }
    public SciezkaWebElementu(String part1, String part2, int var) {
        this.part1 = part1;
        this.part2 = part2;
        this.var = String.valueOf(var);
    }
    public SciezkaWebElementu(String part1, String part2) {
        this.part1 = part1;
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return part1+var+part2;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setVar(int var) {
        this.var = String.valueOf(var);
    }

    @Deprecated
    public void appendToPart2(String s)
    {
        part2 += s;
    }

    /**
     * Zwraca ścięzkę składającą się z argumentów z konstruktora oraz z dodanym parametrem appendPath na jej końcu.
     * @param appendPath Sciezka dodawana na końcu.
     * @return Scieżka.
     */
    public String append(String appendPath)
    {
        return toString() + appendPath;
    }
}
