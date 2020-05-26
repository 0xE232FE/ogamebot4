package app.czas;

import java.io.Serializable;

public class Czas implements Serializable
{
        private int hour = 0, minute = 0, second = 0;
        public static final long MAX_SECONDS_DAY = 60*60*24;

        Czas()
        {

        }

        /**
         *
         * @param czas  w sekundach
         */
        public Czas(int czas)
        {
            int tmp = czas;

            hour = tmp/3600;
            tmp = tmp - (hour*3600);
            minute = tmp/60;
            second = tmp - (minute*60);
        }

        /**
         * Rozbija czas na godziny, minuty, sekundy.
         * @param czas Czas w formacie [xx:xx:xx] lub [x:xx:xx]
         */
        void setTimeVariable(String czas)
        {
            StringBuilder sb = new StringBuilder(czas);

            if(sb.charAt(7) == ' ')
            {
                hour = Integer.valueOf(sb.substring(0,1));
                minute = Integer.valueOf(sb.substring(2,4));
                second = Integer.valueOf(sb.substring(5,7));
            }
            else
            {
                hour = Integer.valueOf(sb.substring(0,2));
                minute = Integer.valueOf(sb.substring(3,5));
                second = Integer.valueOf(sb.substring(6,8));
            }
        }

        /**
         *
         * @return Ilosc godzin.
         */
        public int getHour() {
            return hour;
        }

        /**
         *
         * @return Ilosc minut.
         */
        public int getMinute() {
            return minute;
        }

        /**
         *
         * @return Ilosc sekund.
         */
        public int getSecond() {
            return second;
        }

        /**
         *
         * @return Czas okreśolny w sekundach.
         */
        public int czasWSekundach()
        {
            return second + (minute *60) + (hour *3600);
        }

        /**
         *
         * @return Czas w formacie 00:00:00.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if(hour <10)
                sb.append("0").append(hour);
            else
                sb.append(hour);

            sb.append(":");

            if(minute <10)
                sb.append("0").append(minute);
            else
                sb.append(minute);

            sb.append(":");

            if(second <10)
                sb.append("0").append(second);
            else
                sb.append(second);

            return sb.toString();
        }
}
