package com.abheri.sunaad.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.abheri.sunaad.BuildConfig;
import com.abheri.sunaad.model.Program;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 25/11/15.
 * <p/>
 * use 10.0.3.2 for IP address if using localhost for servers and
 * client is Genymotion
 */
public class Util {

    //Constants
    public static final int HOW_OLD = 3;

    public static final String HOME_SCREEN = "Home";
    public static final String PROGRAM_SCREEN = "Program";
    public static final String SABHA_SCREEN = "Venue";
    public static final String ARTISTE_SCREEN = "Artiste";
    public static final String SETTINGS_SCREEN = "Settings";

    public static final String ARTISTE_DIR_SCREEN = "Artiste Directory List";
    public static final String ORGANIZER_DIR_SCREEN = "Organizer Directory List";
    public static final String VENUE_DIR_SCREEN = "Venue Directory List";



    public static final String REFRESH_CALLED = "Refresh Called";
    public static final String SET_ALARM_CALLED = "Set Alarm Called";
    public static final String UNSET_ALARM_CALLED = "Unset Alarm Called";



    public static final String NAVIGATION_FRAGMET = "NavigationFragment";
    public static final String FEATURED_CONCERT_TICKER="featured_concert_ticker.htm";

    public static final long AUTO_REFRESH_INTERVAL = 1 * 24 * 60 * 60 * 1000;//7*24*60*60*1000;


    //-----------------


    /**
     * @param sview
     * @return
     */
    public static String getServiceUrl(SunaadViews sview) {

        String url = "";

        switch (sview) {

            case HOME:
            case PROGRAM:
            case ARTISTE:
            case SABHA:
                url = "https://sunaad-services-njs.herokuapp.com/getPrograms/";
                //url="http://10.0.3.2:9999/programs/";
                break;
            case ARTISTE_DIR:
                url = "https://sunaad-services-njs.herokuapp.com/getArtiste/";
                //url="http://10.0.3.2:9999/programs/";
                break;
            case ORGANIZER_DIR:
                url = "https://sunaad-services-njs.herokuapp.com/getOrganizer/";
                //url="http://10.0.3.2:9999/programs/";
                break;
            default:
                break;
        }

        return url;
    }

    /**
     * @param sview
     * @return
     */
    public static String getPageUrl(SunaadViews sview) {

        String url = "";

        switch (sview) {

            case HOME:
            case PROGRAM:
            case ARTISTE:
            case SABHA:
                //url="http://10.0.3.2/sunaad/";
                url = "http://abheri.pythonanywhere.com/static/";
                break;
            default:
                break;
        }

        return url;
    }

    public static String getImageUrl() {

        String url = "";

        url = "http://abheri.pythonanywhere.com/static/images/";

        return url;
    }

    public void myToastMessage(android.content.Context context, String message) {

        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean networkAvailable = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        if (!networkAvailable)
            myToastMessage(context, "Network not available. Using stored data");

        return networkAvailable;

    }

    public static boolean isYes(String inStr) {
        if (inStr.equalsIgnoreCase("Y") ||
                inStr.equalsIgnoreCase("YES")) {
            return true;
        }

        return false;
    }

    public static boolean isNo(String inStr) {
        if (inStr.equalsIgnoreCase("N") ||
                inStr.equalsIgnoreCase("NO")) {
            return true;
        }

        return false;
    }

    public static int isEventToday(Program prg, boolean compareDateOnly) {

        int retVal = 0;

        SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yyyy");
        String timec[] = prg.getStartTime().split(":");

        int eventHour = 11, eventMin = 0;
        if (timec[0] != null)
            eventHour = Integer.parseInt(timec[0]);
        if (timec[1] != null)
            eventMin = Integer.parseInt(timec[1]);


        Date eDate = prg.getEventDate();
        Calendar cae = Calendar.getInstance();
        cae.setTime(eDate);
        cae.set(cae.get(Calendar.YEAR), cae.get(Calendar.MONTH), cae.get(Calendar.DATE), eventHour, eventMin);
        eDate = cae.getTime();

        Calendar cat = Calendar.getInstance();
        //cat.set(cat.get(Calendar.YEAR), cat.get(Calendar.MONTH), cat.get(Calendar.DATE), 0 ,0);
        Date tDate = cat.getTime();

        if (compareDateOnly) {
            String dateStr1 = ft.format(eDate);
            String dateStr2 = ft.format(tDate);

            if (dateStr1.equalsIgnoreCase(dateStr2)) {
                retVal = 1;
            }
        } else {
            if (null != eDate) {
                retVal = eDate.compareTo(tDate);
            }
        }

        return retVal;
    }

    public static long startTimeInMillis(Program prg) {

        long retVal = 0;

        SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yyyy");
        String timec[] = prg.getStartTime().split(":");

        int eventHour = 11, eventMin = 0;
        if (timec[0] != null)
            eventHour = Integer.parseInt(timec[0]);
        if (timec[1] != null)
            eventMin = Integer.parseInt(timec[1]);


        Date eDate = prg.getEventDate();
        Calendar cae = Calendar.getInstance();
        cae.setTime(eDate);
        cae.set(cae.get(Calendar.YEAR), cae.get(Calendar.MONTH), cae.get(Calendar.DATE), eventHour, eventMin);
        eDate = cae.getTime();

        retVal = cae.getTimeInMillis();

        return retVal;
    }

    public static long getAlaramMillies(Program prg, int days_before, int hr_at, int min_at ) {

        long retVal = 0;

        SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yyyy");


        Date eDate = prg.getEventDate();
        Calendar cae = Calendar.getInstance();
        cae.setTime(eDate);
        cae.set(cae.get(Calendar.YEAR), cae.get(Calendar.MONTH), cae.get(Calendar.DATE),
                hr_at, min_at);

        cae.add(Calendar.DATE, days_before*-1);

        eDate = cae.getTime();

        retVal = cae.getTimeInMillis();

        return retVal;
    }

    public static void logToGA(String what) {
        Tracker mTracker;

        //Log to Google Analytics only when the build type = Release
        if (!BuildConfig.DEBUG) {
            // Obtain the shared Tracker instance.
            AnalyticsApplication application = (AnalyticsApplication) new AnalyticsApplication();
            mTracker = application.getDefaultTracker();
            Log.i("Sunaad", "Setting screen name: " + what);
            mTracker.setScreenName("Image~" + what);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Share")
                    .build());
        }
    }

    public static int getScrollPosition(List<Program> values) {

        int retVal = 0;

        for (int i = 0; i < values.size(); ++i) {
            Program cp = values.get(i);
            if (isEventToday(cp, true) == 1) {
                retVal = i;
                break;
            }
        }

        return retVal;

    }
}

