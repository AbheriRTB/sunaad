package com.abheri.sunaad.model;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    protected static final String DATABASE_NAME = "sunaad.db";
    protected static final int DATABASE_VERSION = 2;

    protected static final String TABLE_SETTINGS = "settings";
    protected static final String COLUMN_ALARM_DAYS_BEFORE = "alarm_days_before";
    protected static final String COLUMN_ALARM_AT_TIME = "alarm_at_time";
    protected static final String COLUMN_SOUND_ALARM = "sound_alarm";



    protected static final String create_settings_table = "create table "
            + TABLE_SETTINGS + "("
            + COLUMN_ALARM_DAYS_BEFORE
            + " integer not null,"
            + COLUMN_ALARM_AT_TIME
            + " text not null,"
            + COLUMN_SOUND_ALARM
            + " integer not null);";

    protected Context dbContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(create_settings_table);

        switch(DATABASE_VERSION){

            case 1://First DB version without tables. Just to trigger upgrade
                ProgramListDataCache pldc = new ProgramListDataCache(dbContext);
                pldc.removeCache();
                break;
            default:
                break;
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        Log.d("DBHelper", "DBVersion :: " + oldVersion + "  " + newVersion);


        //Clear the cache from SharedPreferences while upgrading
        switch(oldVersion){

            case 1://First DB version without tables. Just to trigger upgrade
                ProgramListDataCache pldc = new ProgramListDataCache(dbContext);
                pldc.removeCache();
                break;
            default:
                break;
        }
    }

} 
