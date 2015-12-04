package com.abheri.sunaad.view;

/**
 * Created by prasanna.ramaswamy on 25/11/15.
 */
public class Util {

    public static String getServiceUrl(SunaadViews sview){

        String url = "";

        switch (sview){

            case PROGRAM:
                url="http://10.0.3.2:9000/programs/";
                break;
            default:
                break;
        }

        return url;
    }
}
