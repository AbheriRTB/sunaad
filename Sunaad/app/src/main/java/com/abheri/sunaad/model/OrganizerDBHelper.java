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

public class OrganizerDBHelper {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public OrganizerDBHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public OrganizerDBHelper(Context context, SQLiteDatabase db) {
        dbHelper = new DBHelper(context);
        database = db;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void createOrganizer(List<Organizer> organizer){
        open();

        for(int i = 0 ; i<organizer.size();i++){
            ContentValues values = new ContentValues();
            Organizer organizer1 = organizer.get(i);
            values.put(SQLStrings.COLUMN_ORGANIZER_ID, organizer1.getOrganizerId());
            values.put(SQLStrings.COLUMN_ORGANIZER_NAME, organizer1.getOrganizerName());
            values.put(SQLStrings.COLUMN_ORGANIZER_DESC, organizer1.getOrganizerDesc());
            values.put(SQLStrings.COLUMN_ORGANIZER_WEBSITE, organizer1.getOrganizerWebsite());
            values.put(SQLStrings.COLUMN_ORGANIZER_PHONE, String.valueOf(organizer1.getOrganizerPhone()));
            values.put(SQLStrings.COLUMN_ORGANIZER_EMAIL, organizer1.getOrganizerEmail());
            values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS1, organizer1.getOrganizerAddress1());
            values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS2, organizer1.getOrganizerAddress2());
            values.put(SQLStrings.COLUMN_ORGANIZER_CITY, organizer1.getOrganizerCity());
            values.put(SQLStrings.COLUMN_ORGANIZER_STATE, organizer1.getOrganizerState());
            values.put(SQLStrings.COLUMN_ORGANIZER_COUNTRY, organizer1.getOrganizerCountry());
            values.put(SQLStrings.COLUMN_ORGANIZER_PINCODE, organizer1.getOrganizerPincode());
            values.put(SQLStrings.COLUMN_ORGANIZER_MAPCOORDS, organizer1.getOrganizerCoords());
            values.put(SQLStrings.COLUMN_ORGANIZER_LOGO, organizer1.getOrganizerLogo());
            values.put(SQLStrings.COLUMN_ORGANIZER_IS_PUBLISHED, organizer1.getIs_published());
            long insertId = database.insert(SQLStrings.TABLE_ORGANIZER, null,
                    values);
        }

        close();
    }



    public Organizer createOrganizer(int id, String name, String ispublished,
                                 String address1, String address2,
                                 String city, String state, String country, String pincode,
                                 String mapcoords) {
        ContentValues values = new ContentValues();
        values.put(SQLStrings.COLUMN_ORGANIZER_ID, id);
        values.put(SQLStrings.COLUMN_ORGANIZER_NAME, name);
        values.put(SQLStrings.COLUMN_ORGANIZER_IS_PUBLISHED, ispublished);
        values.put(SQLStrings.COLUMN_ORGANIZER_ADDRESS1, address1);
        values.put(SQLStrings.COLUMN_ORGANIZER_ADDRESS2, address2);
        values.put(SQLStrings.COLUMN_ORGANIZER_CITY,city);
        values.put(SQLStrings.COLUMN_ORGANIZER_STATE, state);
        values.put(SQLStrings.COLUMN_ORGANIZER_COUNTRY, country);
        values.put(SQLStrings.COLUMN_ORGANIZER_PINCODE, pincode);
        values.put(SQLStrings.COLUMN_ORGANIZER_MAPCOORDS, mapcoords);

        long insertId = database.insert(SQLStrings.TABLE_ORGANIZER, null,
                values);
        Cursor cursor = database.query(SQLStrings.TABLE_ORGANIZER,
                SQLStrings.organzerAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Organizer newOrganizer = cursorToOrganizer(cursor);
        cursor.close();
        return newOrganizer;
    }

    public Organizer createOrganizer(int id, String name, String ispublished) {
        ContentValues values = new ContentValues();
        values.put(SQLStrings.COLUMN_ORGANIZER_ID, id);
        values.put(SQLStrings.COLUMN_ORGANIZER_NAME, name);
        values.put(SQLStrings.COLUMN_ORGANIZER_IS_PUBLISHED, ispublished);
        long insertId = database.insert(SQLStrings.TABLE_ORGANIZER, null,
                values);
        Cursor cursor = database.query(SQLStrings.TABLE_ORGANIZER,
                SQLStrings.organzerAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Organizer newOrganizer = cursorToOrganizer(cursor);
        cursor.close();
        return newOrganizer;
    }



    public void deleteOrganizer(Organizer organizer) {
        /*long id = Artiste.getMinutes();
        System.out.println("Topic deleted with id: " + minutes);
        database.delete(DBHelper.TABLE_ARTISTE, DBHelper.COLUMN_ALARM_BEFORE
                + " = " + id, null); */
        deleteAllOrganizer();
    }

    public void deleteAllOrganizer() {
        List<Organizer> allrows = getAllOrganizer();
        if(allrows != null && allrows.size() > 0) {
            int nrows = database.delete(SQLStrings.TABLE_ORGANIZER, "1", null);
            System.out.println(nrows + " Topics deleted");
        }
    }

    public List<Organizer> getAllOrganizer() {
        List<Organizer> organizer = new ArrayList<Organizer>();
        open();
        Cursor cursor = database.query(SQLStrings.TABLE_ORGANIZER,
                SQLStrings.organzerAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Organizer organizer1 = cursorToOrganizer(cursor);
            organizer.add(organizer1);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return organizer;
    }

    private Organizer cursorToOrganizer(Cursor cursor) {
        Organizer organizer = new Organizer();
        organizer.setOrganizerId(cursor.getInt(0));
        organizer.setOrganizerName(cursor.getString(1));
        organizer.setOrganizerDesc(cursor.getString(2));
        organizer.setOrganizerWebsite(cursor.getString(3));
        organizer.setOrganizerPhone(cursor.getString(4));
        organizer.setOrganizerEmail(cursor.getString(5));
        organizer.setOrganizerAddress1(cursor.getString(6));
        organizer.setOrganizerAddress2(cursor.getString(7));
        organizer.setOrganizerCity(cursor.getString(8));
        organizer.setOrganizerState(cursor.getString(9));
        organizer.setOrganizerCountry(cursor.getString(10));
        organizer.setOrganizerPincode(cursor.getString(11));
        organizer.setOrganizerCoords(cursor.getString(12));
        organizer.setOrganizerLogo(cursor.getString(13));
        organizer.setIs_published(cursor.getString(14));
        return organizer;
    }
}