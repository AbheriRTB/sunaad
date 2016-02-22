package com.abheri.sunaad.view;

import android.widget.Toast;

/**
 * Created by prasanna.ramaswamy on 25/11/15.
 *
 * use 10.0.3.2 for IP address if using localhost for servers and
 * client is Genymotion
 */
public class Util {

    public static final int HOW_OLD = 10;

    public static String getServiceUrl(SunaadViews sview){

        String url = "";

        switch (sview){

            case HOME:
            case PROGRAM:
            case ARTISTE:
            case SABHA:
                url="http://abheri.pythonanywhere.com/programs/";
                //url="http://10.0.3.2:9999/programs/";
                break;
            default:
                break;
        }

        return url;
    }
    public static String getPageUrl(SunaadViews sview){

        String url = "";

        switch (sview){

            case HOME:
            case PROGRAM:
            case ARTISTE:
            case SABHA:
                //url="http://10.0.3.2/sunaad/";
                url="http://abheri.pythonanywhere.com/static/";
                break;
            default:
                break;
        }

        return url;
    }

    public static String getImageUrl(){

        String url = "";

        url="http://abheri.pythonanywhere.com/static/images/";

        return url;
    }

    public void myToastMessage(android.content.Context context){

        Toast.makeText(
                context,
                "Timer Cancelled...",
                Toast.LENGTH_SHORT).show();
    }
}
