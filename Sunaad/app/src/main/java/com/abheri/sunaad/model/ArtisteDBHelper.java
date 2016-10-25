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

    public Artiste createArtiste(int id, String name, String ispublished) {
        ContentValues values = new ContentValues();
        values.put(SQLStrings.COLUMN_ARTISTE_ID, id);
        values.put(SQLStrings.COLUMN_ARTISTE_NAME, name);
        values.put(SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED, ispublished);
        long insertId = database.insert(SQLStrings.TABLE_ARTISTE, null,
                values);
        Cursor cursor = database.query(SQLStrings.TABLE_ARTISTE,
                SQLStrings.artisteAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Artiste newArtiste = cursorToArtiste(cursor);
        cursor.close();
        return newArtiste;
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
            int nrows = database.delete(SQLStrings.TABLE_ARTISTE, "1", null);
            System.out.println(nrows + " Topics deleted");
        }
    }

    public List<Artiste> getAllArtiste() {
        List<Artiste> artiste = new ArrayList<Artiste>();

        Cursor cursor = database.query(SQLStrings.TABLE_ARTISTE,
                SQLStrings.artisteAllColumns, null, null, null, null, null);

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
        Artiste.setArtiste_name(cursor.getString(1));
        Artiste.setIs_published(cursor.getString(2));

        return Artiste;
    }
}