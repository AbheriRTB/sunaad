package com.abheri.sunaad.model;

import java.io.Serializable;

/**
 * Created by prasanna.ramaswamy on 27/10/15.
 */


public class Settings implements Serializable{
    private int days;
    private String atTime;
    private int sound_alarm;
    private String artiste_last_modified;
    private String organizer_last_modified;
    private String venue_last_modified;
    private String program_last_modified;




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

    public String getArtiste_last_modified() {  return artiste_last_modified;
    }
    public void setArtiste_last_modified(String last_modified) { this.artiste_last_modified = last_modified;
    }

    public String getOrganizer_last_modified() {  return organizer_last_modified;
    }
    public void setOrganizer_last_modified(String last_modified) { this.organizer_last_modified = last_modified;
    }

    public String getVenue_last_modified() {  return venue_last_modified;
    }
    public void setVenue_last_modified(String last_modified) { this.venue_last_modified = last_modified;
    }

    public String getProgram_last_modified() {  return program_last_modified;
    }
    public void setProgram_last_modified(String last_modified) { this.program_last_modified = last_modified;
    }




    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return "Days:" + days + " At: " + atTime +  " Sound" + sound_alarm;
    }
    //Test

}
