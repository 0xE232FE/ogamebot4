package app.czas;

import java.io.Serializable;

public class Data implements Serializable {
    int day = 0, month = 0, year = 0;
    String data;

    /**
     * Rozbija datę na rok, miesiacm dzien
     * @param czas Data w formacie dd.mm.rrrr
     */
    public void setDataVariable(String czas)
    {
        StringBuilder sb = new StringBuilder(czas);

        if(sb.length() == 10)
        {
            day = Integer.valueOf(sb.substring(0,2));
            month = Integer.valueOf(sb.substring(3,5));
            year = Integer.valueOf(sb.substring(6,10));
        }
        else
        {
            day = Integer.valueOf(sb.substring(0,2));
            month = Integer.valueOf(sb.substring(3,5));
            year = Integer.valueOf(sb.substring(6,8));
        }

        data = toString();
    }

    /**
     *
     * @return Dzien miesiaca
     */
    int getDay() {
        return day;
    }

    /**
     *
     * @return Miesiac
     */
    int getMonth() {
        return month;
    }

    /**
     *
     * @return Rok
     */
    int getYear() {
        return year;
    }

    /**
     *
     * @return Data w formacie dd.mm.rrrr
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(day <10)
            sb.append("0").append(day);
        else
            sb.append(day);

        sb.append(".");

        if(month <10)
            sb.append("0").append(month);
        else
            sb.append(month);

        sb.append(".");

        if(year <2000)
            sb.append("20").append(year == 0 ? "00" : year);
        else
            sb.append(year);

        return sb.toString();
    }
    /**
     *
     * @return Data w formacie rrrrmmdd
     */
    public String toStringFileFormat()
    {
        StringBuilder sb = new StringBuilder();

        if(day <10)
            sb.append("0").append(day);
        else
            sb.append(day);

        if(month <10)
            sb.append("0").append(month);
        else
            sb.append(month);

        if(year <2000)
            sb.append("20").append(year);
        else
            sb.append(year);

        return sb.toString();
    }

    /**
     * Metoda zmieniająca datę o jeden dzień do przodu.
     * @param data Data do zmiany.
     */
    public void setTommorowData(Data data)
    {
        int[] maxDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        boolean leapYear = data.getYear() % 4 == 0;

        if(data.getDay() >= 28)
        {
            if(data.getMonth() == 12 && data.getDay() == 31)
            {
                setDataVariable("01.01."+(data.getYear()+1));
            }
            else
            {
                boolean changed = false;
                switch (data.getMonth())
                {
                    case 1:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.02."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 2:
                        if(leapYear)
                            if(data.getDay() == 29)
                            {
                                setDataVariable("01.03."+(data.getYear()));
                                changed = true;
                            }
                            else
                            if(data.getDay() == 28)
                            {
                                setDataVariable("01.03."+(data.getYear()));
                                changed = true;
                            }
                        break;
                    case 3:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.04."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 4:
                        if(data.getDay() == 30)
                        {
                            setDataVariable("01.05."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 5:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.06."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 6:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.07."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 7:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.08."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 8:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.09."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 9:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.10."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 10:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.11."+(data.getYear()));
                            changed = true;
                        }
                        break;
                    case 11:
                        if(data.getDay() == 31)
                        {
                            setDataVariable("01.12."+(data.getYear()));
                            changed = true;
                        }
                        break;
                }

                if(!changed)
                    setDataVariable(((data.getDay()+1) > 10 ? (data.getDay()+1) : "0"+(data.getDay()+1)) + "." +
                            (data.getMonth() > 10 ? data.getMonth():"0"+data.getMonth()) + "." + data.getYear());
            }
        }
        else
            setDataVariable(((data.getDay()+1) > 10 ? (data.getDay()+1) : "0"+(data.getDay()+1)) + "." +
                    (data.getMonth() > 10 ? data.getMonth():"0"+data.getMonth()) + "." + data.getYear());
    }

    @Override
    public boolean equals(Object obj) {
       Data d = (Data) obj;
        return this.getYear() == d.getYear() && this.getMonth() == d.getMonth() && this.getDay() == d.getDay();
    }
}
