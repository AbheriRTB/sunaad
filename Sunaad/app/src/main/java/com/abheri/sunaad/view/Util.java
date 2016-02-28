package com.abheri.sunaad.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by prasanna.ramaswamy on 25/11/15.
 * <p/>
 * use 10.0.3.2 for IP address if using localhost for servers and
 * client is Genymotion
 */
public class Util {

    //Constants
    public static final int HOW_OLD = 3;

    public static final String HOME_SCREEN="Home";
    public static final String PROGRAM_SCREEN="Program";
    public static final String SABHA_SCREEN="Sabha";
    public static final String ARTISTE_SCREEN="Artiste";

    public static final long SEVEN_DAYS = 7*24*5*60*1000;//7*24*60*60*1000;

    //-----------------


    public static String getServiceUrl(SunaadViews sview) {

        String url = "";

        switch (sview) {

            case HOME:
            case PROGRAM:
            case ARTISTE:
            case SABHA:
                url = "http://abheri.pythonanywhere.com/programs/";
                //url="http://10.0.3.2:9999/programs/";
                break;
            default:
                break;
        }

        return url;
    }

    public static String getPageUrl(SunaadViews sview) {

        String url = "";

        switch (sview) {

            case HOME:
            case PROGRAM:
            case ARTISTE:
            case SABHA:
                //url="http://10.0.3.2/sunaad/";
                url = "http://abheri.pythonanywhere.com/static/";
                break;
            default:
                break;
        }

        return url;
    }

    public static String getImageUrl() {

        String url = "";

        url = "http://abheri.pythonanywhere.com/static/images/";

        return url;
    }

    public void myToastMessage(android.content.Context context, String message) {

        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean networkAvailable =  activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        if(!networkAvailable)
            myToastMessage(context, "Network not available. Using stored data");

        return networkAvailable;

    }


    public static String staticJson =
            "[\n" +
                    "    {\n" +
                    "        \"id\": 9,\n" +
                    "        \"title\": \"Karthik Music Festival\",\n" +
                    "        \"event_type\": \"Carnatic_Vocal\",\n" +
                    "        \"description\": \"Flute by Prasanna\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.abheri.in\",\n" +
                    "        \"event_date\": \"2015-11-14\",\n" +
                    "        \"place\": \"Basaveshwaranagar\",\n" +
                    "        \"artiste\": \"Prasanna R\",\n" +
                    "        \"phone\": \"1234567890\",\n" +
                    "        \"event_start\": \"15:00:00\",\n" +
                    "        \"event_end\": \"16:00:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"Venkateshwara temple\",\n" +
                    "        \"location_address2\": null,\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560001\",\n" +
                    "        \"location_mapcoords\": \"0,0\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"0\",\n" +
                    "        \"artiste_image\": \"default music.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 10,\n" +
                    "        \"title\": \"Karthik Music Festival\",\n" +
                    "        \"event_type\": \"Carnatic_Vocal\",\n" +
                    "        \"description\": \"Vocal by Divyashree Manoj\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.abheri.in\",\n" +
                    "        \"event_date\": \"2015-11-15\",\n" +
                    "        \"place\": \"Basaveshwaranagar\",\n" +
                    "        \"artiste\": \"Divyashree Manoj\",\n" +
                    "        \"phone\": \"1234567890\",\n" +
                    "        \"event_start\": \"15:00:00\",\n" +
                    "        \"event_end\": \"16:00:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"Venkateshwara temple\",\n" +
                    "        \"location_address2\": null,\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560001\",\n" +
                    "        \"location_mapcoords\": \"0,0\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"0\",\n" +
                    "        \"artiste_image\": \"vocal.9.png\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 12,\n" +
                    "        \"title\": \"Indiranagar Sangeeta Sabha\",\n" +
                    "        \"event_type\": \"Carnatic_Viollin\",\n" +
                    "        \"description\": \"Violin by Akkarai Sisters\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.abheri.in\",\n" +
                    "        \"event_date\": \"2016-01-23\",\n" +
                    "        \"place\": \"Indiranagar\",\n" +
                    "        \"artiste\": \"Akkarai sisters\",\n" +
                    "        \"phone\": \"1234567890\",\n" +
                    "        \"event_start\": \"17:00:00\",\n" +
                    "        \"event_end\": \"19:00:00\",\n" +
                    "        \"duration\": 2.0,\n" +
                    "        \"location_address1\": \"Purandara Bhavan 8th\",\n" +
                    "        \"location_address2\": \"Main Road, HAL 2nd Stage\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560001\",\n" +
                    "        \"location_mapcoords\": \"0,0\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"0\",\n" +
                    "        \"artiste_image\": \"vocal.9.png\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": null\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 14,\n" +
                    "        \"title\": \"Vikasita Mukula - SurSagar\",\n" +
                    "        \"event_type\": \"Hindustani\",\n" +
                    "        \"description\": \"Musical programme by various scholars of SurSagar\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.sursagar.com\",\n" +
                    "        \"event_date\": \"2016-01-31\",\n" +
                    "        \"place\": \"Malleshwaram\",\n" +
                    "        \"artiste\": \"Sursagar scholars\",\n" +
                    "        \"phone\": \"1234567890\",\n" +
                    "        \"event_start\": \"16:15:00\",\n" +
                    "        \"event_end\": \"19:00:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"Canara Union\",\n" +
                    "        \"location_address2\": \"8th main rd, Malleshwaram\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560003\",\n" +
                    "        \"location_mapcoords\": \"0,0\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"sursagar.jpg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": null\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 15,\n" +
                    "        \"title\": \"Renaissance Temple Bells\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Tyagaraja Aaradhana\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.adda.com\",\n" +
                    "        \"event_date\": \"2016-02-14\",\n" +
                    "        \"place\": \"Rajajinagar\",\n" +
                    "        \"artiste\": \"RTB Musicians\",\n" +
                    "        \"phone\": \"1234567890\",\n" +
                    "        \"event_start\": \"16:15:00\",\n" +
                    "        \"event_end\": \"19:00:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"Canara Union\",\n" +
                    "        \"location_address2\": \"8th main rd, Malleshwaram\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560003\",\n" +
                    "        \"location_mapcoords\": \"13.0065554,77.5732855\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"music_image.jpg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 16,\n" +
                    "        \"title\": \"Mahamaya Festival\",\n" +
                    "        \"event_type\": \"Dance\",\n" +
                    "        \"description\": \"Dance Performance by Dr Vasundhara Tilak\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"\",\n" +
                    "        \"event_date\": \"2016-02-14\",\n" +
                    "        \"place\": \"Rajajinagar\",\n" +
                    "        \"artiste\": \"RTB Musicians\",\n" +
                    "        \"phone\": \"1234567890\",\n" +
                    "        \"event_start\": \"16:15:00\",\n" +
                    "        \"event_end\": \"19:00:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"Canara Union\",\n" +
                    "        \"location_address2\": \"8th main rd, Malleshwaram\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560003\",\n" +
                    "        \"location_mapcoords\": \"13.013234,77.5505220\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"mahamaya-festival.jpg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 17,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"G RaviKiran - Vocal, Charulatha Ramanujam - Violin, K V Prasad - Mridangam, B RAjasekhar - Ghatam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.gayanasamaja.org\",\n" +
                    "        \"event_date\": \"2016-02-08\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"G Ravikiran\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"default music.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 18,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Bombay Jayashree - Vocal,Embar Kannan - Violin, Patri Satish kumar - Mridangam, G Guruprasad - Ghatam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.gayanasamaja.org\",\n" +
                    "        \"event_date\": \"2016-02-07\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Bombay Jayashree\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"bombay_jayashree.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 19,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"T M Krishnan - vocal, R K Shriram kumar - Violin, K Arun Prakash Mridangam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-09\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"T M Krishnan\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"t m krishnan.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 20,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Priya Sisters - vocal, Mysore Shrikanth - Violin, Patri Satishkumar - Mridangam, B S Purushottam - Ghatam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-10\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Priya Sisters\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"priya_sisters.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 21,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Sudha Raghunathan - vocal, Embar Kannan - Violin, Neyveli Skanda Subramanyam - Mridangam, R Raman - Ghatam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-11\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Sudha Raghunathan\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"sudha raghunathan.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 22,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Ashwin Anand - vocal, H M Smitha - Violin, Rajkamal - Flute, B C Manjunath - Mridangam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-12\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Ashwin Anand\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"ashwin anand.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 23,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Ranjani & Gayatri - vocal, H N Bhaskar - Violin, Sai Giridhar - Mridangam, G S Ramanujam - Ghatam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-13\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Ranjani & Gayatri\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"raga.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 24,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Awards\",\n" +
                    "        \"description\": \"Presentation of G Vedanta Iyengar memorial Award\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-14\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Award Cermony\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"20:00:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"default music.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 25,\n" +
                    "        \"title\": \"Sri Rama Lalitha Kala Mandira\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Sanjay Subramanyam - Vocal, S Varadarajan - Violin, Neyveli Venkatesh - Mridangam, B Raja - Ghatam\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"www.srlkmandira.org\",\n" +
                    "        \"event_date\": \"2016-02-14\",\n" +
                    "        \"place\": \"Gayana Samaja\",\n" +
                    "        \"artiste\": \"Sanjay Subramanyam\",\n" +
                    "        \"phone\": \"7760907939\",\n" +
                    "        \"event_start\": \"17:30:00\",\n" +
                    "        \"event_end\": \"00:00:00\",\n" +
                    "        \"duration\": 0.0,\n" +
                    "        \"location_address1\": \"K R ROad\",\n" +
                    "        \"location_address2\": \"Basavanagudi\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560004\",\n" +
                    "        \"location_mapcoords\": \"12.9555563,77.5709553,16.94\",\n" +
                    "        \"location_parking\": \"Y\",\n" +
                    "        \"location_eataries\": \"N\",\n" +
                    "        \"artiste_image\": \"sanjay subramanyam.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 26,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Vocal by Smt Bombay Jayashree\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-08\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"Bombay Jayasree\",\n" +
                    "        \"phone\": \"080 26604031\",\n" +
                    "        \"event_start\": \"18:50:00\",\n" +
                    "        \"event_end\": \"21:50:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"21/1, 4th Main 2nd Cross,\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"flyer3_ramanavami_fort.html\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 27,\n" +
                    "        \"title\": \"Carnatic flute\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Flute by V K Raman & Party\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"\",\n" +
                    "        \"event_date\": \"2016-02-20\",\n" +
                    "        \"place\": \"Vishishta Banashankari Fine Arts\",\n" +
                    "        \"artiste\": \"V K Raman\",\n" +
                    "        \"phone\": \"1234\",\n" +
                    "        \"event_start\": \"18:00:00\",\n" +
                    "        \"event_end\": \"20:30:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"Shruti Sagar\",\n" +
                    "        \"location_address2\": \"723,1st Main Road, 7thBlock, 2nd Phase, BSK 3\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560085\",\n" +
                    "        \"location_mapcoords\": \"0,0\",\n" +
                    "        \"location_parking\": \"No\",\n" +
                    "        \"location_eataries\": \"No\",\n" +
                    "        \"artiste_image\": \"\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 28,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Vocal by Sanjay Subramanyam & Party\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-09\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"Sanjay Subramanyam\",\n" +
                    "        \"phone\": \"080 2660 4031\",\n" +
                    "        \"event_start\": \"18:30:00\",\n" +
                    "        \"event_end\": \"21:30:00\",\n" +
                    "        \"duration\": 3.0,\n" +
                    "        \"location_address1\": \"Old Fort High school\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"sanjay subramanyam.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 29,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Vocal by K V Krishnaprasad & Party\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-12\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"K V Krishnaprasad\",\n" +
                    "        \"phone\": \"080 2660 4031\",\n" +
                    "        \"event_start\": \"19:30:00\",\n" +
                    "        \"event_end\": \"21:45:00\",\n" +
                    "        \"duration\": 2.0,\n" +
                    "        \"location_address1\": \"Old Fort High school\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"krishnaprasad.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"No\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 30,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Vocal by Vignesh Ishwar & Party\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-11\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"Vigneshwar Iyer\",\n" +
                    "        \"phone\": \"080 2660 4031\",\n" +
                    "        \"event_start\": \"18:15:00\",\n" +
                    "        \"event_end\": \"19:15:00\",\n" +
                    "        \"duration\": 1.0,\n" +
                    "        \"location_address1\": \"Old Fort High school\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"vignesh ishwar.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 31,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Vocal by Sandeep Narayan\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-11\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"Sandeep Narayan\",\n" +
                    "        \"phone\": \"080 2660 4031\",\n" +
                    "        \"event_start\": \"19:30:00\",\n" +
                    "        \"event_end\": \"21:45:00\",\n" +
                    "        \"duration\": 2.0,\n" +
                    "        \"location_address1\": \"Old Fort High school\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"sandeep narayan.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 32,\n" +
                    "        \"title\": \"Carnatic flute\",\n" +
                    "        \"event_type\": \"Carnatic\",\n" +
                    "        \"description\": \"Flute by V K Raman & Party\",\n" +
                    "        \"entry_fee\": \"Free\",\n" +
                    "        \"website\": \"\",\n" +
                    "        \"event_date\": \"2016-02-22\",\n" +
                    "        \"place\": \"Vishishta Banashankari Fine Arts\",\n" +
                    "        \"artiste\": \"V K Raman\",\n" +
                    "        \"phone\": \"1234\",\n" +
                    "        \"event_start\": \"23:30:00\",\n" +
                    "        \"event_end\": \"23:55:00\",\n" +
                    "        \"duration\": 2.5,\n" +
                    "        \"location_address1\": \"Shruti Sagar\",\n" +
                    "        \"location_address2\": \"723,1st Main Road, 7thBlock, 2nd Phase, BSK 3\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560085\",\n" +
                    "        \"location_mapcoords\": \"0,0\",\n" +
                    "        \"location_parking\": \"No\",\n" +
                    "        \"location_eataries\": \"No\",\n" +
                    "        \"artiste_image\": \"\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 33,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Violin by N Kartik & M Sumanth\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-12\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"N Karthik\",\n" +
                    "        \"phone\": \"080 2660 4031\",\n" +
                    "        \"event_start\": \"18:15:00\",\n" +
                    "        \"event_end\": \"19:15:00\",\n" +
                    "        \"duration\": 1.0,\n" +
                    "        \"location_address1\": \"Old Fort High school\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"n karthik.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"No\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 34,\n" +
                    "        \"title\": \"Sri Ramanavami National Music Festival\",\n" +
                    "        \"event_type\": \"Classical\",\n" +
                    "        \"description\": \"Vocal by Saashwathi Prabhu & Party\",\n" +
                    "        \"entry_fee\": \"Tickets of 100/150/200/250\",\n" +
                    "        \"website\": \"www.ramanavami.org\",\n" +
                    "        \"event_date\": \"2016-04-13\",\n" +
                    "        \"place\": \"78th Ramanavami Music Festival\",\n" +
                    "        \"artiste\": \"Saashwathi\",\n" +
                    "        \"phone\": \"080 2660 4031\",\n" +
                    "        \"event_start\": \"18:15:00\",\n" +
                    "        \"event_end\": \"19:15:00\",\n" +
                    "        \"duration\": 2.0,\n" +
                    "        \"location_address1\": \"Old Fort High school\",\n" +
                    "        \"location_address2\": \"Chamrajpet\",\n" +
                    "        \"location_city\": \"Bangalore\",\n" +
                    "        \"location_state\": \"Karnataka\",\n" +
                    "        \"location_country\": \"India\",\n" +
                    "        \"location_pincode\": \"560018\",\n" +
                    "        \"location_mapcoords\": \"12.958239, 77.573582\",\n" +
                    "        \"location_parking\": \"Yes\",\n" +
                    "        \"location_eataries\": \"Yes\",\n" +
                    "        \"artiste_image\": \"saashwathi.jpeg\",\n" +
                    "        \"is_featured\": \"No\",\n" +
                    "        \"splash_url\": \"No\"\n" +
                    "    }\n" +
                    "]";
}
