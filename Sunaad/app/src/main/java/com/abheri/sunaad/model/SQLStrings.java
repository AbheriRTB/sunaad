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
    protected static final String COLUMN_ARTISTE_WEBSITE = "artiste_website";
    protected static final String COLUMN_ARTISTE_ART_TYPE = "artiste_art_type";
    protected static final String COLUMN_ARTISTE_INSTRUMENT = "artiste_instrument";
    protected static final String COLUMN_ARTISTE_ADDRESS1 = "address1";
    protected static final String COLUMN_ARTISTE_ADDRESS2 = "address2";
    protected static final String COLUMN_ARTISTE_CITY = "city";
    protected static final String COLUMN_ARTISTE_STATE = "state";
    protected static final String COLUMN_ARTISTE_COUNTRY = "country";
    protected static final String COLUMN_ARTISTE_PINCODE = "pincode";
    protected static final String COLUMN_ARTISTE_MAPCOORDS = "mapcoords";
    protected static final String COLUMN_ARTISTE_IMAGE = "artiste_image";
    protected static final String COLUMN_ARTISTE_IS_PUBLISHED = "is_published";

    protected static final String create_artiste_table = "create table "
            + TABLE_ARTISTE + "("
            + COLUMN_ARTISTE_ID + " integer not null,"
            + COLUMN_ARTISTE_NAME + " text not null,"
            + COLUMN_ARTISTE_WEBSITE + " text,"
            + COLUMN_ARTISTE_ART_TYPE + " text not null,"
            + COLUMN_ARTISTE_INSTRUMENT + " text,"
            + COLUMN_ARTISTE_ADDRESS1 + " text,"
            + COLUMN_ARTISTE_ADDRESS2 + " text,"
            + COLUMN_ARTISTE_CITY + " text,"
            + COLUMN_ARTISTE_STATE + " text,"
            + COLUMN_ARTISTE_COUNTRY + " text,"
            + COLUMN_ARTISTE_PINCODE + " text,"
            + COLUMN_ARTISTE_MAPCOORDS + " text,"
            + COLUMN_ARTISTE_IMAGE + " text,"
            + COLUMN_ARTISTE_IS_PUBLISHED + " text not null);";

    protected static final String[] artisteAllColumns = {
            SQLStrings.COLUMN_ARTISTE_ID,
            SQLStrings.COLUMN_ARTISTE_NAME,
            SQLStrings.COLUMN_ARTISTE_WEBSITE,
            SQLStrings.COLUMN_ARTISTE_ART_TYPE,
            SQLStrings.COLUMN_ARTISTE_INSTRUMENT,
            SQLStrings.COLUMN_ARTISTE_ADDRESS1,
            SQLStrings.COLUMN_ARTISTE_ADDRESS2,
            SQLStrings.COLUMN_ARTISTE_CITY,
            SQLStrings.COLUMN_ARTISTE_STATE,
            SQLStrings.COLUMN_ARTISTE_COUNTRY ,
            SQLStrings.COLUMN_ARTISTE_PINCODE,
            SQLStrings.COLUMN_ARTISTE_MAPCOORDS,
            SQLStrings.COLUMN_ARTISTE_IMAGE,
            SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED };


    //Program table
    protected static final String TABLE_PROGRAM = "program";
    protected static final String COLUMN_PROGRAM_ID = "program_id";
    protected static final String COLUMN_PROGRAM_TITLE = "title";
    protected static final String COLUMN_PROGRAM_ART_TYPE = "art_type";
    protected static final String COLUMN_PROGRAM_DESCRIPTION = "description";
    protected static final String COLUMN_PROGRAM_ENTRY_FEE = "entry_fee";
    protected static final String COLUMN_PROGRAM_ORGANIZER_WEBSITE = "organizer_website";
    protected static final String COLUMN_PROGRAM_EVENT_DATE = "event_date";
    protected static final String COLUMN_PROGRAM_VENUE_NAME = "venue_name";
    protected static final String COLUMN_PROGRAM_ORGANIZER_NAME = "organizer_name";
    protected static final String COLUMN_PROGRAM_ARTISTE_NAME = "artiste_name";
    protected static final String COLUMN_PROGRAM_ACCOMPANISTS = "accompanists";
    protected static final String COLUMN_PROGRAM_ORGANIZER_PHONE = "organizer_phone";
    protected static final String COLUMN_PROGRAM_EVENT_START = "event_start";
    protected static final String COLUMN_PROGRAM_EVENT_END = "event_end";
    protected static final String COLUMN_PROGRAM_DURATION = "duration";
    protected static final String COLUMN_PROGRAM_VENUE_ADDRESS1 = "venue_address1";
    protected static final String COLUMN_PROGRAM_VENUE_ADDRESS2 = "venue_address2";
    protected static final String COLUMN_PROGRAM_VENUE_CITY = "venue_city";
    protected static final String COLUMN_PROGRAM_VENUE_STATE = "venue_state";
    protected static final String COLUMN_PROGRAM_VENUE_COUNTRY = "venue_country";
    protected static final String COLUMN_PROGRAM_VENUE_PINCODE = "venue_pincode";
    protected static final String COLUMN_PROGRAM_VENUE_MAPCOORDS = "venue_mapcoords";
    protected static final String COLUMN_PROGRAM_VENUE_PARKING = "venue_parking";
    protected static final String COLUMN_PROGRAM_VENUE_EATARIES = "venue_eataries";
    protected static final String COLUMN_PROGRAM_ARTISTE_IMAGE = "artiste_image";
    protected static final String COLUMN_PROGRAM_IS_FEATURED = "is_featured";
    protected static final String COLUMN_PROGRAM_SPLASH_URL = "splash_url";
    protected static final String COLUMN_PROGRAM_IS_PUBLISHED = "is_published";

    protected static final String create_program_table = "create table "
            + TABLE_PROGRAM + "("
            + COLUMN_PROGRAM_ID + "integer not null,"
            + COLUMN_PROGRAM_TITLE + " text,"
            + COLUMN_PROGRAM_ART_TYPE + " text not null,"
            + COLUMN_PROGRAM_DESCRIPTION + " text,"
            + COLUMN_PROGRAM_ENTRY_FEE + " text,"
            + COLUMN_PROGRAM_ORGANIZER_WEBSITE + " text,"
            + COLUMN_PROGRAM_EVENT_DATE + " text,"
            + COLUMN_PROGRAM_VENUE_NAME + " text,"
            + COLUMN_PROGRAM_ORGANIZER_NAME + " text,"
            + COLUMN_PROGRAM_ARTISTE_NAME + " text,"
            + COLUMN_PROGRAM_ACCOMPANISTS + " text,"
            + COLUMN_PROGRAM_ORGANIZER_PHONE + " text,"
            + COLUMN_PROGRAM_EVENT_START + " text,"
            + COLUMN_PROGRAM_EVENT_END + " text,"
            + COLUMN_PROGRAM_DURATION + "float,"
            + COLUMN_PROGRAM_VENUE_ADDRESS1 + " text,"
            + COLUMN_PROGRAM_VENUE_ADDRESS2 + " text,"
            + COLUMN_PROGRAM_VENUE_CITY + " text,"
            + COLUMN_PROGRAM_VENUE_STATE + " text,"
            + COLUMN_PROGRAM_VENUE_COUNTRY + " text,"
            + COLUMN_PROGRAM_VENUE_PINCODE + " text,"
            + COLUMN_PROGRAM_VENUE_MAPCOORDS + " text,"
            + COLUMN_PROGRAM_VENUE_PARKING + " text,"
            + COLUMN_PROGRAM_VENUE_EATARIES + " text,"
            + COLUMN_PROGRAM_ARTISTE_IMAGE + " text,"
            + COLUMN_PROGRAM_IS_FEATURED + " text,"
            + COLUMN_PROGRAM_SPLASH_URL + " text,"
            + COLUMN_PROGRAM_IS_PUBLISHED + " text)";

    protected static final String[] programAllColumns = {
            COLUMN_PROGRAM_ID ,
            COLUMN_PROGRAM_TITLE,
            COLUMN_PROGRAM_ART_TYPE ,
            COLUMN_PROGRAM_DESCRIPTION,
            COLUMN_PROGRAM_ENTRY_FEE ,
            COLUMN_PROGRAM_ORGANIZER_WEBSITE,
            COLUMN_PROGRAM_EVENT_DATE,
            COLUMN_PROGRAM_VENUE_NAME ,
            COLUMN_PROGRAM_ORGANIZER_NAME,
            COLUMN_PROGRAM_ARTISTE_NAME,
            COLUMN_PROGRAM_ACCOMPANISTS,
            COLUMN_PROGRAM_ORGANIZER_PHONE ,
            COLUMN_PROGRAM_EVENT_START,
            COLUMN_PROGRAM_EVENT_END,
            COLUMN_PROGRAM_DURATION,
            COLUMN_PROGRAM_VENUE_ADDRESS1,
            COLUMN_PROGRAM_VENUE_ADDRESS2,
            COLUMN_PROGRAM_VENUE_CITY,
            COLUMN_PROGRAM_VENUE_STATE,
            COLUMN_PROGRAM_VENUE_COUNTRY,
            COLUMN_PROGRAM_VENUE_PINCODE,
            COLUMN_PROGRAM_VENUE_MAPCOORDS,
            COLUMN_PROGRAM_VENUE_PARKING ,
            COLUMN_PROGRAM_VENUE_EATARIES ,
            COLUMN_PROGRAM_ARTISTE_IMAGE,
            COLUMN_PROGRAM_IS_FEATURED ,
            COLUMN_PROGRAM_SPLASH_URL,
            COLUMN_PROGRAM_IS_PUBLISHED };

}
