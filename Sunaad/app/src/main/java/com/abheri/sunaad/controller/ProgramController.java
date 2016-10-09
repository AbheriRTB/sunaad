package com.abheri.sunaad.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramListDataCache;
import com.abheri.sunaad.model.Settings;
import com.abheri.sunaad.model.SettingsDataHelper;
import com.abheri.sunaad.view.AlarmBroadcastReceiver;
import com.abheri.sunaad.view.ProgramFragment;
import com.abheri.sunaad.view.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 17/09/16.
 */
public class  ProgramController implements CompoundButton.OnCheckedChangeListener, ImageView.OnClickListener{

    Context context;
    Fragment programFragment;

    public ProgramController(Context c){
        context = c;
        programFragment = null;
    }
    public ProgramController(Context c, Fragment f){
        context = c;
        programFragment = f;
    }


    public void setAlarm(Program prgObj) {


        String msgText = "";
        int alarm_days_before=0, sound_alarm=0, hour=0, min=0;
        String alarm_at_time="10:00";

        msgText += prgObj.getTitle() + " ";
        msgText += prgObj.getDetails() + " On ";
        msgText += prgObj.getEventDate()  + " At";
        msgText += prgObj.getStartTime();

        long startTimeInMillis = Util.startTimeInMillis(prgObj);
        long alarmStart = 0;

        SettingsDataHelper sdh = new SettingsDataHelper(context);
        sdh.open();
        ArrayList<Settings> set = (ArrayList<Settings>)sdh.getAllSettings();
        sdh.close();
        if(set.size() > 0){
            alarm_days_before = set.get(0).getDaysBefore();
            alarm_at_time = set.get(0).getAtTime();
            sound_alarm = set.get(0).getSound_alarm();

            String hr_min[] = alarm_at_time.split(":");
            if(hr_min[0] != null)
                hour = Integer.parseInt(hr_min[0]);
            if(hr_min[1] != null)
                min = Integer.parseInt(hr_min[1]);

            alarmStart = Util.getAlaramMillies(prgObj, alarm_days_before, hour, min);

        }


        Intent intent = new Intent(context.getApplicationContext(),
                AlarmBroadcastReceiver.class);
        intent.putExtra("MessageText", msgText);
        intent.putExtra("SelectedProgram", prgObj);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), (int)prgObj.getId(), intent, 0);
        AlarmManager alarmManager =
                (AlarmManager)context.getApplicationContext().
                        getSystemService(context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmStart , pendingIntent);

        prgObj.alarm_millis = alarmStart;

        updateProgramList(prgObj);

        Toast.makeText(context, "Alarm set " + alarm_days_before + " days before at " +
                alarm_at_time , Toast.LENGTH_SHORT).show();
    }

    public void CancelAlarm(Program prgObj){

        Intent intent = new Intent(context.getApplicationContext(),
                AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), (int)prgObj.getId(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT| Intent.FILL_IN_DATA);
        AlarmManager alarmManager =
                (AlarmManager)context.getApplicationContext().
                        getSystemService(context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        prgObj.alarm_millis = -1;

        updateProgramList(prgObj);

        Toast.makeText(context, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Switch alarm_switch = (Switch)buttonView;
        Program prgObj = (Program) alarm_switch.getTag(R.string.alarm_program);

        if(isChecked && prgObj.alarm_millis<0) {//set alarm only if it is not already set
            setAlarm(prgObj);
        }else if(!isChecked && prgObj.alarm_millis>0){//Cancel alarm only if not already cancelled
            CancelAlarm(prgObj);
        }
    }

    public void updateProgramList(Program prgObject){
        if(null == context){
            return;
        }

        ProgramListDataCache pdc = new ProgramListDataCache(context);
        List<Program> prgList = pdc.RetrieveProgramDataFromCache();
        for(int i=0; i<prgList.size(); ++i){
            if(prgList.get(i).getId() == prgObject.getId()){
                prgList.set(i, prgObject);
                break;
            }
        }

        pdc.SaveProgramDataInCache(prgList);

        if(null == programFragment){
            return;
        }

        ((ProgramFragment)programFragment).doScroll=false;
        ((ProgramFragment)programFragment).getData((ProgramFragment)programFragment, false);
    }

    @Override
    public void onClick(View v) {
        Program prgObj = (Program) v.getTag(R.string.alarm_program);

        if(prgObj.alarm_millis<0) {//set alarm only if it is not already set
            setAlarm(prgObj);
        }else if(prgObj.alarm_millis>0){//Cancel alarm only if not already cancelled
            CancelAlarm(prgObj);
        }
    }
}
