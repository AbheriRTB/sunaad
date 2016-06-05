package com.abheri.sunaad.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.abheri.sunaad.view.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 06/02/16.
 */
public class ProgramListDataCache {

    Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    public ProgramListDataCache(Context c){
        context = c;
    }

    public boolean SaveProgramDataInCache(List<Program> programList){
        //Creating a shared preference
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = settings.edit();
        Gson gson = new Gson();

        Date currentDate = new Date();
        prefsEditor.putLong("CacheTimeStamp", new Long(currentDate.getTime()));
        String json = gson.toJson(programList);
        prefsEditor.putString("ProgramListCached", json);
        prefsEditor.commit();

        return true;
    }

    public List<Program> RetrieveProgramDataFromCache(){
        List<Program> programList = null;

        try {
            SharedPreferences mPrefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString("ProgramListCached", "");
            programList = gson.fromJson(json, new TypeToken<ArrayList<Program>>() {}.getType());
        }catch(Exception e){
            e.printStackTrace();
        }

        return programList;
    }

    public Long RetrieveProgramDataCacheTimeStamp(){

        Long ts= null;

        try {
            SharedPreferences mPrefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            long l = mPrefs.getLong("CacheTimeStamp", 0);
            if(l > 0) {
                ts = new Long(l);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ts;
    }

    public boolean isProgramDataCacheOld(){

        Long tso = RetrieveProgramDataCacheTimeStamp();

        if(null != tso){
            Date cd = new Date();
            long cache_ts, current_ts;
            cache_ts = tso.longValue();
            current_ts = cd.getTime();

            /* cache is old if it is older than 7 days */
            if((current_ts-cache_ts) < Util.SEVEN_DAYS){
                return false;
            }
        }

        return true;

    }

    public void removeCache(){
        Log.d("ProgramlistDataCahce","InremoveCache :: ");
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = settings.edit();
        boolean s = settings.edit().clear().commit();
        Log.d("ProgramlistDataCahce","removeCache :: "+s);
    }


}
