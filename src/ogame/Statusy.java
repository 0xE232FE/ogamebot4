package ogame;

public class Statusy
{
    private static final Status ON = new Status(1,"on");
    private static final Status OFF = new Status(2,"off");
    private static final Status DISABLED = new Status(3,"disabled");
    private static final Status ACTIVE = new Status(4,"active");

    private static final Status []STATUSY =
            {
                ON,
                OFF,
                DISABLED,
                ACTIVE,
            };

    public static int getIndex(String name)
    {
        for(Status s : STATUSY)
        {
            if(s.getName().equals(name))
                return s.getIndex();
        }

        return -1;
    }

    public static String getName(int index)
    {
        for(Status s : STATUSY)
        {
            if(s.getIndex() == index)
                return s.getName();
        }

        return "null";
    }

    public static class Status
    {
        private final int index;
        private final String name;

        Status(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }
}
