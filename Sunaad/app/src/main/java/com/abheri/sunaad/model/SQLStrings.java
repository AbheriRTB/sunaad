package com.abheri.sunaad.model;

import java.util.Date;

/**
 * Created by prasanna.ramaswamy on 24/10/16.
 */
public class SQLStrings {

    //Settings table
    //Settings table
    public static final String TABLE_SETTINGS = "settings";
    public static final String COLUMN_ALARM_DAYS_BEFORE = "alarm_days_before";
    public static final String COLUMN_ALARM_AT_TIME = "alarm_at_time";
    public static final String COLUMN_SOUND_ALARM = "sound_alarm";
    public static final String COLUMN_ARTISTE_LAST_REFRESH = "artiste_last_refresh";
    public static final String COLUMN_ORGANIZER_LAST_REFRESH = "organizer_last_refresh";
    public static final String COLUMN_VENUE_LAST_REFRESH = "venue_last_refresh";
    public static final String COLUMN_PROGRAM_LAST_REFRESH = "program_last_refresh";


    public static final String drop_settings_table = "drop table if exists settings";
    public static final String create_settings_table = "create table "
            + TABLE_SETTINGS + "("
            + COLUMN_ALARM_DAYS_BEFORE
            + " integer not null,"
            + COLUMN_ALARM_AT_TIME
            + " text not null,"
            + COLUMN_SOUND_ALARM
            + " integer not null,"
            + COLUMN_ARTISTE_LAST_REFRESH
            + " datetime,"
            + COLUMN_ORGANIZER_LAST_REFRESH
            + " datetime,"
            + COLUMN_VENUE_LAST_REFRESH
            + " datetime"
            + COLUMN_PROGRAM_LAST_REFRESH
            + " datetime"
            + ");";

    public static final String[] settingsAllColumns = {
            SQLStrings.COLUMN_ALARM_DAYS_BEFORE,
            SQLStrings.COLUMN_ALARM_AT_TIME,
            SQLStrings.COLUMN_SOUND_ALARM,
            SQLStrings.COLUMN_ARTISTE_LAST_REFRESH,
            SQLStrings.COLUMN_ORGANIZER_LAST_REFRESH,
            SQLStrings.COLUMN_VENUE_LAST_REFRESH,
            SQLStrings.COLUMN_ORGANIZER_LAST_REFRESH};

    //Artiste table
    public static final String TABLE_ARTISTE = "artiste";
    public static final String COLUMN_ARTISTE_ID = "artiste_id";
    public static final String COLUMN_ARTISTE_NAME = "artiste_name";
    public static final String COLUMN_ARTISTE_DOB = "artiste_dob";
    public static final String COLUMN_ARTISTE_GENDER = "artiste_gender";
    public static final String COLUMN_ARTISTE_DESC = "artiste_desc";
    public static final String COLUMN_ARTISTE_WEBSITE = "artiste_website";
    public static final String COLUMN_ARTISTE_EMAIL = "artiste_email";
    public static final String COLUMN_ARTISTE_PHONE = "artiste_phone";
    public static final String COLUMN_ARTISTE_ART_TYPE = "artiste_art_type";
    public static final String COLUMN_ARTISTE_INSTRUMENT = "artiste_instrument";
    public static final String COLUMN_ARTISTE_ADDRESS1 = "address1";
    public static final String COLUMN_ARTISTE_ADDRESS2 = "address2";
    public static final String COLUMN_ARTISTE_CITY = "city";
    public static final String COLUMN_ARTISTE_STATE = "state";
    public static final String COLUMN_ARTISTE_COUNTRY = "country";
    public static final String COLUMN_ARTISTE_PINCODE = "pincode";
    public static final String COLUMN_ARTISTE_MAPCOORDS = "mapcoords";
    public static final String COLUMN_ARTISTE_IMAGE = "artiste_image";
    public static final String COLUMN_ARTISTE_AUDIOCLIP = "artiste_audioclip";
    public static final String COLUMN_ARTISTE_IS_PUBLISHED = "is_published";

    public static final String create_artiste_table = "create table "
            + TABLE_ARTISTE + "("
            + COLUMN_ARTISTE_ID + " integer not null,"
            + COLUMN_ARTISTE_NAME + " text not null,"
            + COLUMN_ARTISTE_DESC + " text,"
            + COLUMN_ARTISTE_GENDER + " text not null,"
            + COLUMN_ARTISTE_DOB + " date,"
            + COLUMN_ARTISTE_EMAIL + " text,"
            + COLUMN_ARTISTE_PHONE + " text,"
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
            + COLUMN_ARTISTE_AUDIOCLIP + " text,"
            + COLUMN_ARTISTE_IS_PUBLISHED + " text not null);";

    public static final String[] artisteAllColumns = {
            SQLStrings.COLUMN_ARTISTE_ID,
            SQLStrings.COLUMN_ARTISTE_NAME,
            SQLStrings.COLUMN_ARTISTE_DESC,
            SQLStrings.COLUMN_ARTISTE_GENDER,
            SQLStrings.COLUMN_ARTISTE_DOB,
            SQLStrings.COLUMN_ARTISTE_EMAIL,
            SQLStrings.COLUMN_ARTISTE_PHONE,
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
            SQLStrings.COLUMN_ARTISTE_AUDIOCLIP,
            SQLStrings.COLUMN_ARTISTE_IS_PUBLISHED };

    //Organizer table
    public static final String TABLE_ORGANIZER = "organizer";
    public static final String COLUMN_ORGANIZER_ID = "organizer_id";
    public static final String COLUMN_ORGANIZER_NAME = "organizer_name";
    public static final String COLUMN_ORGANIZER_DESC = "organizer_description";
    public static final String COLUMN_ORGANIZER_WEBSITE = "organizer_website";
    public static final String COLUMN_ORGANIZER_PHONE = "organizer_phone";
    public static final String COLUMN_ORGANIZER_EMAIL = "organizer_email";
    public static final String COLUMN_ORGANIZER_ADDRESS1 = "address1";
    public static final String COLUMN_ORGANIZER_ADDRESS2 = "address2";
    public static final String COLUMN_ORGANIZER_CITY = "city";
    public static final String COLUMN_ORGANIZER_STATE = "state";
    public static final String COLUMN_ORGANIZER_COUNTRY = "country";
    public static final String COLUMN_ORGANIZER_PINCODE = "pincode";
    public static final String COLUMN_ORGANIZER_MAPCOORDS = "mapcoords";
    public static final String COLUMN_ORGANIZER_LOGO = "organizer_logo";
    public static final String COLUMN_ORGANIZER_IS_PUBLISHED = "is_published";

    public static final String create_organizer_table = "create table "
            + TABLE_ORGANIZER + "("
            + COLUMN_ORGANIZER_ID + " integer not null,"
            + COLUMN_ORGANIZER_NAME + " text not null,"
            + COLUMN_ORGANIZER_DESC + " text,"
            + COLUMN_ORGANIZER_WEBSITE + " text,"
            + COLUMN_ORGANIZER_PHONE + " text,"
            + COLUMN_ORGANIZER_EMAIL + " text,"
            + COLUMN_ORGANIZER_ADDRESS1 + " text,"
            + COLUMN_ORGANIZER_ADDRESS2 + " text,"
            + COLUMN_ORGANIZER_CITY + " text,"
            + COLUMN_ORGANIZER_STATE + " text,"
            + COLUMN_ORGANIZER_COUNTRY + " text,"
            + COLUMN_ORGANIZER_PINCODE + " text,"
            + COLUMN_ORGANIZER_MAPCOORDS + " text,"
            + COLUMN_ORGANIZER_LOGO + " text,"
            + COLUMN_ORGANIZER_IS_PUBLISHED + " text not null);";

    public static final String[] organzerAllColumns = {
            SQLStrings.COLUMN_ORGANIZER_ID,
            SQLStrings.COLUMN_ORGANIZER_NAME,
            SQLStrings.COLUMN_ORGANIZER_DESC,
            SQLStrings.COLUMN_ORGANIZER_PHONE,
            SQLStrings.COLUMN_ORGANIZER_EMAIL,
            SQLStrings.COLUMN_ORGANIZER_ADDRESS1,
            SQLStrings.COLUMN_ORGANIZER_ADDRESS2,
            SQLStrings.COLUMN_ORGANIZER_CITY,
            SQLStrings.COLUMN_ORGANIZER_STATE,
            SQLStrings.COLUMN_ORGANIZER_COUNTRY ,
            SQLStrings.COLUMN_ORGANIZER_PINCODE,
            SQLStrings.COLUMN_ORGANIZER_MAPCOORDS,
            SQLStrings.COLUMN_ORGANIZER_WEBSITE,
            SQLStrings.COLUMN_ORGANIZER_LOGO,
            SQLStrings.COLUMN_ORGANIZER_IS_PUBLISHED };

    //Venue table
    public static final String TABLE_VENUE = "venue";
    public static final String COLUMN_VENUE_ID = "venue_id";
    public static final String COLUMN_VENUE_NAME = "venue_name";
    public static final String COLUMN_VENUE_DESCRIPTION = "venue_desc";
    public static final String COLUMN_VENUE_IMAGE = "image";
    public static final String COLUMN_VENUE_WEBSITE = "website";
    public static final String COLUMN_VENUE_PHONE = "phone";
    public static final String COLUMN_VENUE_EMAIL = "email";
    public static final String COLUMN_VENUE_ADDRESS1 = "address1";
    public static final String COLUMN_VENUE_ADDRESS2 = "address2";
    public static final String COLUMN_VENUE_CITY = "city";
    public static final String COLUMN_VENUE_STATE = "state";
    public static final String COLUMN_VENUE_COUNTRY = "country";
    public static final String COLUMN_VENUE_PINCODE = "pincode";
    public static final String COLUMN_VENUE_MAPCOORDS = "mapcoords";
    public static final String COLUMN_VENUE_PARKING = "parking";
    public static final String COLUMN_VENUE_EATARIES = "eataries";
    public static final String COLUMN_VENUE_IS_PUBLISHED = "is_published";

    public static final String create_venue_table = "create table "
            + TABLE_VENUE + "("
            + COLUMN_VENUE_ID + " integer not null,"
            + COLUMN_VENUE_NAME + " text not null,"
            + COLUMN_VENUE_DESCRIPTION + " text not null,"
            + COLUMN_VENUE_WEBSITE + " text,"
            + COLUMN_VENUE_PHONE + " text,"
            + COLUMN_VENUE_EMAIL + " text,"
            + COLUMN_VENUE_ADDRESS1 + " text,"
            + COLUMN_VENUE_ADDRESS2 + " text,"
            + COLUMN_VENUE_CITY + " text,"
            + COLUMN_VENUE_STATE + " text,"
            + COLUMN_VENUE_COUNTRY + " text,"
            + COLUMN_VENUE_PINCODE + " text,"
            + COLUMN_VENUE_MAPCOORDS + " text,"
            + COLUMN_VENUE_IMAGE + " text,"
            + COLUMN_VENUE_PARKING + " text,"
            + COLUMN_VENUE_EATARIES + " text,"
            + COLUMN_VENUE_IS_PUBLISHED + " text not null);";

    public static final String[] venueAllColumns = {
            SQLStrings.COLUMN_VENUE_ID,
            SQLStrings.COLUMN_VENUE_NAME,
            SQLStrings.COLUMN_VENUE_DESCRIPTION,
            SQLStrings.COLUMN_VENUE_WEBSITE,
            SQLStrings.COLUMN_VENUE_PHONE,
            SQLStrings.COLUMN_VENUE_EMAIL,
            SQLStrings.COLUMN_VENUE_ADDRESS1,
            SQLStrings.COLUMN_VENUE_ADDRESS2,
            SQLStrings.COLUMN_VENUE_CITY,
            SQLStrings.COLUMN_VENUE_STATE,
            SQLStrings.COLUMN_VENUE_COUNTRY ,
            SQLStrings.COLUMN_VENUE_PINCODE,
            SQLStrings.COLUMN_VENUE_MAPCOORDS,
            SQLStrings.COLUMN_VENUE_IMAGE,
            SQLStrings.COLUMN_VENUE_PARKING,
            SQLStrings.COLUMN_VENUE_EATARIES,
            SQLStrings.COLUMN_VENUE_IS_PUBLISHED };


    //Program table --------- NOT USED RIGHT NOW ------------------
    public static final String TABLE_PROGRAM = "program";
    public static final String COLUMN_PROGRAM_ID = "program_id";
    public static final String COLUMN_PROGRAM_TITLE = "title";
    public static final String COLUMN_PROGRAM_ART_TYPE = "art_type";
    public static final String COLUMN_PROGRAM_DESCRIPTION = "description";
    public static final String COLUMN_PROGRAM_ENTRY_FEE = "entry_fee";
    public static final String COLUMN_PROGRAM_ORGANIZER_WEBSITE = "organizer_website";
    public static final String COLUMN_PROGRAM_EVENT_DATE = "event_date";
    public static final String COLUMN_PROGRAM_VENUE_NAME = "venue_name";
    public static final String COLUMN_PROGRAM_ORGANIZER_NAME = "organizer_name";
    public static final String COLUMN_PROGRAM_ARTISTE_NAME = "artiste_name";
    public static final String COLUMN_PROGRAM_ACCOMPANISTS = "accompanists";
    public static final String COLUMN_PROGRAM_ORGANIZER_PHONE = "organizer_phone";
    public static final String COLUMN_PROGRAM_EVENT_START = "event_start";
    public static final String COLUMN_PROGRAM_EVENT_END = "event_end";
    public static final String COLUMN_PROGRAM_DURATION = "duration";
    public static final String COLUMN_PROGRAM_VENUE_ADDRESS1 = "venue_address1";
    public static final String COLUMN_PROGRAM_VENUE_ADDRESS2 = "venue_address2";
    public static final String COLUMN_PROGRAM_VENUE_CITY = "venue_city";
    public static final String COLUMN_PROGRAM_VENUE_STATE = "venue_state";
    public static final String COLUMN_PROGRAM_VENUE_COUNTRY = "venue_country";
    public static final String COLUMN_PROGRAM_VENUE_PINCODE = "venue_pincode";
    public static final String COLUMN_PROGRAM_VENUE_MAPCOORDS = "venue_mapcoords";
    public static final String COLUMN_PROGRAM_VENUE_PARKING = "venue_parking";
    public static final String COLUMN_PROGRAM_VENUE_EATARIES = "venue_eataries";
    public static final String COLUMN_PROGRAM_ARTISTE_IMAGE = "artiste_image";
    public static final String COLUMN_PROGRAM_IS_FEATURED = "is_featured";
    public static final String COLUMN_PROGRAM_SPLASH_URL = "splash_url";
    public static final String COLUMN_PROGRAM_IS_PUBLISHED = "is_published";

    public static final String create_program_table = "create table "
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

    public static final String[] programAllColumns = {
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
