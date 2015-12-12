package com.abheri.sunaad.view;

import android.widget.Toast;

/**
 * Created by prasanna.ramaswamy on 25/11/15.
 *
 * use 10.0.3.2 for IP address if using localhost for servers and
 * client is Genymotion
 */
public class Util {

    public static String getServiceUrl(SunaadViews sview){

        String url = "";

        switch (sview){

            case HOME:
                url="http://10.0.3.2/sunaad/";
                break;
            case PROGRAM:
                url="http://abheri.pythonanywhere.com/programs/";
                break;
            default:
                break;
        }

        return url;
    }

    public void myToastMessage(android.content.Context context){

        Toast.makeText(
                context,
                "Timer Cancelled...",
                Toast.LENGTH_SHORT).show();
    }
}
