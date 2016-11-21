package com.abheri.sunaad.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by prasanna.ramaswamy on 06/02/16.
 */
public class OrganizerListDataCache {

    Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    public OrganizerListDataCache(Context c){
        context = c;
    }

    public boolean SaveOrganizerInCache(List<?> dataList){
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

        return true;
    }


    public List<Organizer> RetrieveOrganizerDataFromCache(){
        List<Organizer> organizerList = null;

        try {
            OrganizerDBHelper adh = new OrganizerDBHelper(context);
            organizerList = adh.getAllOrganizer();
        }catch(Exception e){
            e.printStackTrace();
        }

        return organizerList;
    }

    public Long RetrieveOrganizerDataCacheTimeStamp(){

        Long ts= null;

        try {
            SharedPreferences mPrefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            long l = mPrefs.getLong("OrganizerCacheTimeStamp", 0);
            if(l > 0) {
                ts = new Long(l);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ts;
    }

    public boolean isOrganizerDataCacheOld(){

        return true;

    }
}
