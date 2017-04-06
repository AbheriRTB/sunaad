package com.abheri.sunaad.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.abheri.sunaad.view.Util;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 06/02/16.
 */
public class VenueListDataCache {

    Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    public VenueListDataCache(Context c){
        context = c;
    }

    public boolean SaveVenueDataInCache(List<Venue> venueList){
        //Creating a shared preference
        /*
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = settings.edit();
        Gson gson = new Gson();

        Date currentDate = new Date();
        prefsEditor.putLong("CacheTimeStamp", new Long(currentDate.getTime()));
        String json = gson.toJson(programList);
        prefsEditor.putString("ProgramListCached", json);

        prefsEditor.commit();
        */

        VenueDBHelper venueDBHelper = new VenueDBHelper(context);
        venueDBHelper.deleteAllVenue();
        venueDBHelper.createVenue(venueList);

        SettingsDBHelper settingsDBHelper = new SettingsDBHelper(context);
        settingsDBHelper.setLastModifiedDateTime(Util.getFormattedDateTime(new Date()),
                SQLStrings.COLUMN_VENUE_LAST_REFRESH);


        return true;
    }


    public List<Venue> RetrieveVenueDataFromCache(){
        List<Venue> venueList = null;

        try {
            VenueDBHelper adh = new VenueDBHelper(context);
            venueList = adh.getAllVenue();
        }catch(Exception e){
            e.printStackTrace();
        }

        return venueList;
    }

    public Long RetrieveVenueDataCacheTimeStamp(){

        Long ts= null;

        try {
            SharedPreferences mPrefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            long l = mPrefs.getLong("VenueCacheTimeStamp", 0);
            if(l > 0) {
                ts = new Long(l);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ts;
    }

    public boolean isVenueDataCacheOld(){

        return true;

    }
}
