package com.abheri.sunaad.model;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.abheri.sunaad.controller.SettingsController;

public class DBHelper extends SQLiteOpenHelper {

    protected static final String DATABASE_NAME = "sunaad.db";
    protected static final int DATABASE_VERSION = 4;

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


    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context)
    {
        if (instance == null)
            instance = new DBHelper(context);

        return instance;
    }


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(create_settings_table);
        createDefaultSettingsData(database);

        switch(DATABASE_VERSION){

            case 1://First DB version without tables. Just to trigger upgrade
            case 2://First DB version without tables. Just to trigger upgrade
            case 3:
                ProgramListDataCache pldc = new ProgramListDataCache(dbContext);
                pldc.removeCache();
                break;
            default:
                break;
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        Log.d("DBHelper", "DBVersion :: " + oldVersion + "  " + newVersion);


        //Clear the cache from SharedPreferences while upgrading
        switch(oldVersion){

            case 1://First DB version without tables. Just to trigger upgrade
            case 2://First DB version without tables. Just to trigger upgrade
            case 3:
                ProgramListDataCache pldc = new ProgramListDataCache(dbContext);
                pldc.removeCache();
                database.execSQL(create_settings_table);
                createDefaultSettingsData(database);
                break;
            default:
                break;
        }
    }

    void createDefaultSettingsData(SQLiteDatabase db){
        //Create Default Settings data
        SettingsDataHelper sdh = new SettingsDataHelper(dbContext, db);
        sdh.deleteAllSettings();
        //Default alarm settings: 1 day before at 10:00AM
        sdh.createSettings(1, "10:00", 1);
    }

} 
