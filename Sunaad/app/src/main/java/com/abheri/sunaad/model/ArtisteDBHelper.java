package com.abheri.sunaad.model;

/**
 * Created by prasanna.ramaswamy on 22/08/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArtisteDBHelper {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ArtisteDBHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArtisteDBHelper(Context context, SQLiteDatabase db) {
        dbHelper = new DBHelper(context);
        database = db;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Artiste createArtiste(int id, String name, String art_type, String ispublished,
                                 String instrument, String address1, String address2,
                                 String city, String state, String country, String pincode,
                                 String mapcoords, String artiste_image) {
        ContentValues values = new ContentValues();
        values.put(SQLStrings.COLUMN_ARTISTE_ID, id);
        values.put(SQLStrings.COLUMN_ARTISTE_NAME, name);
        values.put(SQLStrings.COLUMN_ARTISTE_ART_TYPE, art_type);
        values.put(SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED, ispublished);
        values.put(SQLStrings.COLUMN_ARTISTE_INSTRUMENT, instrument);
        values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS1, address1);
        values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS2, address2);
        values.put(SQLStrings.COLUMN_ARTISTE_CITY,city);
        values.put(SQLStrings.COLUMN_ARTISTE_STATE, state);
        values.put(SQLStrings.COLUMN_ARTISTE_COUNTRY, country);
        values.put(SQLStrings.COLUMN_ARTISTE_PINCODE, pincode);
        values.put(SQLStrings.COLUMN_ARTISTE_MAPCOORDS, mapcoords);
        values.put(SQLStrings.COLUMN_ARTISTE_IMAGE, artiste_image);

        long insertId = database.insert(SQLStrings.TABLE_ARTISTE, null,
                values);
        Cursor cursor = database.query(SQLStrings.TABLE_ARTISTE,
                SQLStrings.artisteAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Artiste newArtiste = cursorToArtiste(cursor);
        cursor.close();
        return newArtiste;
    }
    
    public void createArtiste(List<Artiste> artistes){
        open();
        
        for(int i = 0 ; i<artistes.size();i++){
            ContentValues values = new ContentValues();
            Artiste artiste = artistes.get(i);
            values.put(SQLStrings.COLUMN_ARTISTE_ID, artiste.getId());
            values.put(SQLStrings.COLUMN_ARTISTE_NAME, artiste.getArtisteName());
            values.put(SQLStrings.COLUMN_ARTISTE_GENDER, artiste.getArtisteGender());
            values.put(SQLStrings.COLUMN_ARTISTE_DESC, artiste.getArtiste_description());
            values.put(SQLStrings.COLUMN_ARTISTE_DOB, String.valueOf(artiste.getArtisteDOB()));
            values.put(SQLStrings.COLUMN_ARTISTE_EMAIL, artiste.getArtisteEmail());
            values.put(SQLStrings.COLUMN_ARTISTE_PHONE, artiste.getArtistePhone());
            values.put(SQLStrings.COLUMN_ARTISTE_WEBSITE, artiste.getArtistePhone());
            values.put(SQLStrings.COLUMN_ARTISTE_ART_TYPE, artiste.getArtisteArtType());
            values.put(SQLStrings.COLUMN_ARTISTE_INSTRUMENT, artiste.getArtisteInstrument());
            values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS1, artiste.getArtisteAddress1());
            values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS2, artiste.getArtisteAddress2());
            values.put(SQLStrings.COLUMN_ARTISTE_CITY, artiste.getArtisteCity());
            values.put(SQLStrings.COLUMN_ARTISTE_STATE, artiste.getArtisteState());
            values.put(SQLStrings.COLUMN_ARTISTE_COUNTRY, artiste.getArtisteCountry());
            values.put(SQLStrings.COLUMN_ARTISTE_PINCODE, artiste.getArtistePincode());
            values.put(SQLStrings.COLUMN_ARTISTE_MAPCOORDS, artiste.getArtisteCoords());
            values.put(SQLStrings.COLUMN_ARTISTE_IMAGE, artiste.getArtisteImage());
            values.put(SQLStrings.COLUMN_ARTISTE_AUDIOCLIP, artiste.getArtisteAudioClip());
            values.put(SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED, artiste.getIs_published());
            long insertId = database.insert(SQLStrings.TABLE_ARTISTE, null,
                    values);
        }
        
        close();
    }

    public Artiste createArtiste(int id, String name, String art_type, String ispublished, String artisteGender) {
        open();
        Log.d("ArtisteDBHelper","DBTableCheck :: " + isTableExists(SQLStrings.TABLE_ARTISTE,true));
        Log.d("ArtistDirectoryFrag::", "DBid ::" + id);
        Log.d("ArtistDirectoryFrag::", "DBArName ::" + name);
        Log.d("ArtistDirectoryFrag::", "DBArType ::" + art_type);
        Log.d("ArtistDirectoryFrag::", "DBArPub ::" +ispublished);
        Log.d("ArtistDirectoryFrag::", "DBArGender ::" + artisteGender);
        ContentValues values = new ContentValues();
        values.put(SQLStrings.COLUMN_ARTISTE_ID, id);
        values.put(SQLStrings.COLUMN_ARTISTE_NAME, name);
        values.put(SQLStrings.COLUMN_ARTISTE_GENDER, artisteGender);
        values.put(SQLStrings.COLUMN_ARTISTE_DESC, "DESC");
        values.put(SQLStrings.COLUMN_ARTISTE_GENDER, artisteGender);
        values.put(SQLStrings.COLUMN_ARTISTE_DOB, "DOB");
        values.put(SQLStrings.COLUMN_ARTISTE_EMAIL, "EMAIL");
        values.put(SQLStrings.COLUMN_ARTISTE_PHONE, "PHONE");
        values.put(SQLStrings.COLUMN_ARTISTE_WEBSITE, "WEBSITE");
        values.put(SQLStrings.COLUMN_ARTISTE_ART_TYPE, "ARTTYPE");
        values.put(SQLStrings.COLUMN_ARTISTE_INSTRUMENT, "INSTRT");
        values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS1, "ADDR1");
        values.put(SQLStrings.COLUMN_ARTISTE_ADDRESS2, "ADDR2");
        values.put(SQLStrings.COLUMN_ARTISTE_CITY, "CITY");
        values.put(SQLStrings.COLUMN_ARTISTE_STATE, "STATE");
        values.put(SQLStrings.COLUMN_ARTISTE_COUNTRY, "COUNTRY");
        values.put(SQLStrings.COLUMN_ARTISTE_PINCODE, "PINCODE");
        values.put(SQLStrings.COLUMN_ARTISTE_MAPCOORDS, "MAPCORDS");
        values.put(SQLStrings.COLUMN_ARTISTE_IMAGE, "IMAGE");
        values.put(SQLStrings.COLUMN_ARTISTE_AUDIOCLIP, "AUDIO");
        values.put(SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED, ispublished);
        long insertId = database.insert(SQLStrings.TABLE_ARTISTE, null,
                values);

        /*Cursor cursor = database.query(SQLStrings.TABLE_ARTISTE,
                SQLStrings.artisteAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Artiste newArtiste = cursorToArtiste(cursor);
        cursor.close();*/
        close();
        return new Artiste();
    }

    public boolean isTableExists(String tableName, boolean openDb) {
        if(openDb) {
            if(database != null || database.isOpen()) {
                Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
                if(cursor!=null) {
                    if(cursor.getCount()>0) {
                        cursor.close();
                        return true;
                    }
                    cursor.close();
                }
            }
        }
        return false;
    }


    public void deleteArtiste(Artiste Artiste) {
        /*long id = Artiste.getMinutes();
        System.out.println("Topic deleted with id: " + minutes);
        database.delete(DBHelper.TABLE_ARTISTE, DBHelper.COLUMN_ALARM_BEFORE
                + " = " + id, null); */
        deleteAllArtiste();
    }

    public void deleteAllArtiste() {
        List<Artiste> allrows = getAllArtiste();
        if(allrows != null && allrows.size() > 0) {
            int nrows = database.delete(SQLStrings.TABLE_ARTISTE, null, null);
            System.out.println(nrows + " Topics deleted");
        }
    }

    public List<Artiste> getAllArtiste() {
        List<Artiste> artiste = new ArrayList<Artiste>();
        open();
        /*Cursor cursor = database.query(SQLStrings.TABLE_ARTISTE,
                SQLStrings.artisteAllColumns, null, null, null, null, null);*/

        Cursor cursor = database.rawQuery("select * from "+SQLStrings.TABLE_ARTISTE,null );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Artiste artiste1 = cursorToArtiste(cursor);
            artiste.add(artiste1);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return artiste;
    }

    private Artiste cursorToArtiste(Cursor cursor) {
        Artiste Artiste = new Artiste();
        Artiste.setId(cursor.getInt(0));
        Artiste.setArtisteName(cursor.getString(1));
        Artiste.setArtiste_description(cursor.getString(2));
        Artiste.setArtisteGender(cursor.getString(3));
        Date date = getDateFromString(cursor.getString(4));
        Artiste.setArtisteDOB((date) == null ? new Date() : date);
        Artiste.setArtisteEmail(cursor.getString(5));
        Artiste.setArtistePhone(cursor.getString(6));
        Artiste.setArtisteWebsite(cursor.getString(7));
        Artiste.setArtisteArtType(cursor.getString(8));
        Artiste.setArtisteInstrument(cursor.getString(9));
        Artiste.setArtisteAddress1(cursor.getString(10));
        Artiste.setArtisteAddress2(cursor.getString(11));
        Artiste.setArtisteCity(cursor.getString(12));
        Artiste.setArtisteState(cursor.getString(13));
        Artiste.setArtisteCountry(cursor.getString(14));
        Artiste.setArtistePincode(cursor.getString(15));
        Artiste.setArtisteCoords(cursor.getString(16));
        Artiste.setArtisteImage(cursor.getString(17));
        Artiste.setArtisteAudioClip(cursor.getString(18));
        Artiste.setIs_published(cursor.getString(19));
        return Artiste;
    }

    private Date getDateFromString(String string) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date d = sdf.parse(string);
            return d;
        } catch (ParseException ex) {
            Log.d("ArtisteDBHelper","ParseExc::"+ex.toString());
            return null;
        }

    }
}