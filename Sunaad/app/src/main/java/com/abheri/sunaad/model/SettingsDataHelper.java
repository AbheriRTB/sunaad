package com.abheri.sunaad.model;

/**
 * Created by prasanna.ramaswamy on 22/08/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SettingsDataHelper {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = {
            DBHelper.COLUMN_ALARM_DAYS_BEFORE,
            DBHelper.COLUMN_ALARM_AT_TIME,
            DBHelper.COLUMN_SOUND_ALARM };

    public SettingsDataHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public SettingsDataHelper(Context context, SQLiteDatabase db) {
        dbHelper = new DBHelper(context);
        database = db;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Settings createSettings(int alarmDaysBefore, String alarmAtTime, int soundAlarm) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ALARM_DAYS_BEFORE, alarmDaysBefore);
        values.put(DBHelper.COLUMN_ALARM_AT_TIME, alarmAtTime);
        values.put(DBHelper.COLUMN_SOUND_ALARM, soundAlarm);
        long insertId = database.insert(DBHelper.TABLE_SETTINGS, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_SETTINGS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Settings newSettings = cursorToSettings(cursor);
        cursor.close();
        return newSettings;
    }

    public void deleteSettings(Settings settings) {
        /*long id = settings.getMinutes();
        System.out.println("Topic deleted with id: " + minutes);
        database.delete(DBHelper.TABLE_SETTINGS, DBHelper.COLUMN_ALARM_BEFORE
                + " = " + id, null); */
        deleteAllSettings();
    }

    public void deleteAllSettings() {
        List<Settings> allrows = getAllSettings();
        if(allrows != null && allrows.size() > 0) {
            int nrows = database.delete(DBHelper.TABLE_SETTINGS, "1", null);
            System.out.println(nrows + " Topics deleted");
        }
    }

    public List<Settings> getAllSettings() {
        List<Settings> settings = new ArrayList<Settings>();

        Cursor cursor = database.query(DBHelper.TABLE_SETTINGS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Settings settings1 = cursorToSettings(cursor);
            settings.add(settings1);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return settings;
    }

    private Settings cursorToSettings(Cursor cursor) {
        Settings settings = new Settings();
        settings.setDaysBefore(cursor.getInt(0));
        settings.setAtTime(cursor.getString(1));
        settings.setSound_alarm(cursor.getInt(2));
        return settings;
    }
}