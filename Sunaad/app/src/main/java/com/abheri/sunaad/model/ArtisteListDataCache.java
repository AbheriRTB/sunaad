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
public class ArtisteListDataCache {

    Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    public ArtisteListDataCache(Context c){
        context = c;
    }

    public boolean SaveArtisteDataInCache(List<Artiste> artisteList){
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

        ArtisteDBHelper artisteDBHelper = new ArtisteDBHelper(context);
        artisteDBHelper.deleteAllArtiste();
        artisteDBHelper.createArtiste(artisteList);

        SettingsDBHelper settingsDBHelper = new SettingsDBHelper(context);
        settingsDBHelper.setLastModifiedDateTime(Util.getFormattedDateTime(new Date()),
                                                        SQLStrings.COLUMN_ARTISTE_LAST_REFRESH);


        return true;
    }


    public List<Artiste> RetrieveArtisteDataFromCache(){
        List<Artiste> artisteList = null;

        try {
            ArtisteDBHelper adh = new ArtisteDBHelper(context);
            artisteList = adh.getAllArtiste();
        }catch(Exception e){
            e.printStackTrace();
        }

        return artisteList;
    }

    public Long RetrieveArtisteDataCacheTimeStamp(){

        Long ts= null;

        try {
            SharedPreferences mPrefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            long l = mPrefs.getLong("ArtisteCacheTimeStamp", 0);
            if(l > 0) {
                ts = new Long(l);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ts;
    }

    public boolean isArtisteDataCacheOld(){

        return true;

    }
}
