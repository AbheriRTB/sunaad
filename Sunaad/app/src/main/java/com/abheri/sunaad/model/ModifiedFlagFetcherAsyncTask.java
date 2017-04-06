package com.abheri.sunaad.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.abheri.sunaad.view.HandleModifiedFlagServiceResponse;
import com.abheri.sunaad.view.HandleServiceResponse;
import com.abheri.sunaad.view.SunaadViews;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static com.abheri.sunaad.view.SunaadViews.ARTISTE;
import static com.abheri.sunaad.view.SunaadViews.ARTISTE_DIR;
import static com.abheri.sunaad.view.SunaadViews.HOME;
import static com.abheri.sunaad.view.SunaadViews.ORGANIZER_DIR;
import static com.abheri.sunaad.view.SunaadViews.PROGRAM;
import static com.abheri.sunaad.view.SunaadViews.SABHA;
import static com.abheri.sunaad.view.SunaadViews.VENUE_DIR;
import static com.abheri.sunaad.view.Util.getServiceUrl;

public class ModifiedFlagFetcherAsyncTask extends AsyncTask<String, String, Object> {

    ProgressDialog progressDialog;
    HandleModifiedFlagServiceResponse serviceResponseInterface;
    String field;
    Context context;

    public ModifiedFlagFetcherAsyncTask(HandleModifiedFlagServiceResponse handleServiceResponse,
                                        String whichField, Context ctx)
    {
        serviceResponseInterface = handleServiceResponse;
        field = whichField;
        context = ctx;
    }

    @Override
    protected Object doInBackground(String... uri) {
        HttpURLConnection urlConnection;
        BufferedInputStream inputStream;
        String responseString = null;
        String urlpath="";
        String queryStringParam = "";

        Object returnObj = null;

        int result = 0;
        SettingsDBHelper sdh = new SettingsDBHelper(context);
        List<Settings> settings = sdh.getAllSettings();


        if (settings.size() > 0) {

            switch (field) {

                case SQLStrings.COLUMN_ARTISTE_LAST_REFRESH:
                    queryStringParam = settings.get(0).getArtiste_last_modified();
                    urlpath = getServiceUrl(SunaadViews.ARTISTE_MODIFIED);
                    break;

                case SQLStrings.COLUMN_ORGANIZER_LAST_REFRESH:
                    queryStringParam = settings.get(0).getOrganizer_last_modified();
                    urlpath = getServiceUrl(SunaadViews.ORGANIZER_MODIFIED);
                    break;

                case SQLStrings.COLUMN_VENUE_LAST_REFRESH:
                    queryStringParam = settings.get(0).getVenue_last_modified();
                    urlpath = getServiceUrl(SunaadViews.VENUE_MODIFIED);
                    break;

                case SQLStrings.COLUMN_PROGRAM_LAST_REFRESH:
                    queryStringParam = settings.get(0).getProgram_last_modified();
                    urlpath = getServiceUrl(SunaadViews.PROGRAM_MODIFIED);
                    break;
            }

            if (queryStringParam != null && queryStringParam.length() > 0) {

                try {
                    /* forming th java.net.URL object */
                    urlpath = urlpath + URLEncoder.encode(queryStringParam, "UTF-8");
                    Log.d("PRAS", "URLPath:" + urlpath);
                    URL url = new URL(urlpath);
                    Log.d("PRAS", url.toString());
                    urlConnection = (HttpURLConnection) url.openConnection();


                    /* optional request header */
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    /* optional request header */
                    urlConnection.setRequestProperty("Accept", "application/json");

                    /* for Get request */
                    urlConnection.setRequestMethod("GET");
                    int statusCode = urlConnection.getResponseCode();
                    System.out.println(statusCode);
                        /* 200 represents HTTP OK */
                    if (statusCode == 200) {
                        inputStream = new BufferedInputStream(urlConnection.getInputStream());
                        returnObj = convertInputStreamToString(inputStream);
                        result = 1; // Successful
                    } else {
                        result = 0; //"Failed to fetch data!";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    returnObj = e;
                }
            }
        }
        return returnObj;
    }

    @Override
    protected void onPostExecute(Object result) {

        if(null != result && !(result instanceof Exception))
            serviceResponseInterface.onModifiedFlagFetchSuccess(result);
        else
            serviceResponseInterface.onModifiedFlagFetchError(result);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate();
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }
        return result;
    }

}