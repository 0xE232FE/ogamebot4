package app.data.fleet_save_attack;

import com.Log;

public class KliknijPodglad {

    private static int coIleSekundOdswiezyc = 90;

    public static int getCoIleSekundOdswiezyc() {
        return coIleSekundOdswiezyc;
    }

    public static void setCoIleSekundOdswiezyc(int coIleSekundOdswiezyc) {
        KliknijPodglad.coIleSekundOdswiezyc = coIleSekundOdswiezyc;
        Log.printLog(KliknijPodglad.class.getName(),"Ustawiono czas odświeżania strony na " + coIleSekundOdswiezyc +
                " sekund.");
    }
}
