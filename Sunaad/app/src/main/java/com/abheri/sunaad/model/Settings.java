package com.abheri.sunaad.model;

import java.io.Serializable;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Settings implements Serializable{
    private int days;
    private String atTime;
    private int sound_alarm;


    public int getDaysBefore() {  return days;
    }
    public void setDaysBefore(int days) { this.days = days;
    }

    public String getAtTime() {  return atTime;
    }
    public void setAtTime(String atTime) { this.atTime = atTime;
    }


    public int getSound_alarm() { return sound_alarm;
    }
    public void setSound_alarm(int sound_alarm) {
        this.sound_alarm = sound_alarm;
    }



    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return "Days:" + days + " At: " + atTime +  " Sound" + sound_alarm;
    }
    //Test

}
