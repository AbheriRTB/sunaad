package com.abheri.sunaad.controller;

import android.content.Context;

import com.abheri.sunaad.model.Settings;
import com.abheri.sunaad.model.SettingsDataHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 17/09/16.
 */
public class SettingsController {

    Context context;

    public SettingsController(Context c){
        context = c;
    }

    public void SaveSettings(Settings settings_to_save){

        SettingsDataHelper sdh = new SettingsDataHelper(context);
        sdh.open();
        sdh.deleteAllSettings();
        sdh.createSettings(settings_to_save.getDaysBefore(),
                            settings_to_save.getAtTime(),
                            settings_to_save.getSound_alarm() >0? 1 : 0);
        sdh.close();
    }

    public Settings GetSettings(){

        SettingsDataHelper sdh = new SettingsDataHelper(context);
        sdh.open();
        List<Settings>sl = sdh.getAllSettings();
        sdh.close();

        if(sl != null && sl.size() > 0){
            return sl.get(0);
        }else{
            return null;
        }
    }

    public static String getFormattedTime(String time){

        Calendar ca =  Calendar.getInstance();
        String timec[] = time.split(":");

        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DATE),
                Integer.parseInt(timec[0]), Integer.parseInt(timec[1]));

        DateFormat tf = new SimpleDateFormat("hh:mm a");

        String returnStr = tf.format(ca.getTime()).toString();

        return returnStr;

    }
}
