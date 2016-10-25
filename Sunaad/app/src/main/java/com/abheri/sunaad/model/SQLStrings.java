package com.abheri.sunaad.model;

/**
 * Created by prasanna.ramaswamy on 24/10/16.
 */
public class SQLStrings {

    //Settings table
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

    protected static final String[] settingsAllColumns = {
            SQLStrings.COLUMN_ALARM_DAYS_BEFORE,
            SQLStrings.COLUMN_ALARM_AT_TIME,
            SQLStrings.COLUMN_SOUND_ALARM };


    //Artiste table
    protected static final String TABLE_ARTISTE = "artiste";
    protected static final String COLUMN_ARTISTE_ID = "artiste_id";
    protected static final String COLUMN_ARTISTE_NAME = "artiste_name";
    protected static final String COLUMN_ARTISTE_IS_PUBLISHED = "is_published";

    protected static final String create_artiste_table = "create table "
            + TABLE_ARTISTE + "("
            + COLUMN_ARTISTE_ID
            + " integer not null,"
            + COLUMN_ARTISTE_NAME
            + " text not null,"
            + COLUMN_ARTISTE_IS_PUBLISHED
            + " text not null);";

    protected static final String[] artisteAllColumns = {
            SQLStrings.COLUMN_ARTISTE_ID,
            SQLStrings.COLUMN_ARTISTE_NAME,
            SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED };
}
