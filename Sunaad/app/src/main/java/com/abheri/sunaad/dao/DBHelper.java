package com.abheri.sunaad.dao;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sunaad.db";
    private static final int DATABASE_VERSION = 2;

    private Context dbContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

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


        //Clear the cache from SharedPreferences while upgrading
        switch(DATABASE_VERSION){

            case 1://First DB version without tables. Just to trigger upgrade
                ProgramListDataCache pldc = new ProgramListDataCache(dbContext);
                pldc.removeCache();
                break;
            default:
                break;
        }
    }

} 
