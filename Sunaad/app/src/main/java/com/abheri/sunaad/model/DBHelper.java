package com.abheri.sunaad.model;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    protected static final String DATABASE_NAME = "sunaad.db";

    //Previous version 7 (08 Apr 2017)
    //No DB Changes
    protected static final int DATABASE_VERSION = 8;

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

        database.execSQL(SQLStrings.create_settings_table);
        createDefaultSettingsData(database);
        database.execSQL(SQLStrings.create_artiste_table);
        database.execSQL(SQLStrings.create_organizer_table);
        database.execSQL(SQLStrings.create_venue_table);


        switch(DATABASE_VERSION){

            case 1://First DB version without tables. Just to trigger upgrade
            case 2://First DB version without tables. Just to trigger upgrade
            case 3:
            case 4:
            case 5:
            case 6:
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

        ProgramListDataCache pldc = new ProgramListDataCache(dbContext);
        //Clear the cache from SharedPreferences while upgrading
        pldc.removeCache();
        switch(oldVersion){

            case 1://First DB version without tables. Just to trigger upgrade
            case 2://First DB version without tables. Just to trigger upgrade
                database.execSQL(SQLStrings.create_settings_table);
                createDefaultSettingsData(database);
                break;
            case 3://Has Settings table already
                createDefaultSettingsData(database);
                break;
            case 4:
                database.execSQL(SQLStrings.create_artiste_table);
                database.execSQL(SQLStrings.create_organizer_table);
                database.execSQL(SQLStrings.create_venue_table);

                createDefaultSettingsData(database);
                break;
            case 5:
                //No changes here. Bumped up db version jus to call onUpgrade to removeCache.
                //06-Apr-2017
                database.execSQL(SQLStrings.drop_settings_table);
                database.execSQL(SQLStrings.create_settings_table);
                //----
                createDefaultSettingsData(database);
                break;
            case 6:
                //06-Apr-2017
                database.execSQL(SQLStrings.drop_settings_table);
                database.execSQL(SQLStrings.create_settings_table);
                //----
                createDefaultSettingsData(database);
                break;
            case 7://Nothing to be done. Only change in home view
                break;

            default:
                break;
        }
    }

    void createDefaultSettingsData(SQLiteDatabase db){
        //Create Default Settings data
        SettingsDBHelper sdh = new SettingsDBHelper(dbContext, db);
        sdh.deleteAllSettings();
        //Default alarm settings: 1 day before at 10:00AM
        sdh.createSettings(1, "10:00", 1);
    }

} 
