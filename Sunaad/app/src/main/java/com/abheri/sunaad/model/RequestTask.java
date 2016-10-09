package com.abheri.sunaad.model;

import com.abheri.sunaad.view.SunaadViews;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.abheri.sunaad.view.HandleServiceResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestTask extends AsyncTask<String, String, Object> {

    ProgressDialog progressDialog;
    HandleServiceResponse serviceResponseInterface;
    SunaadViews currentView;
    Context context;

    public RequestTask(HandleServiceResponse handleServiceResponse,
                       SunaadViews cview, Context ctx)
    {
        serviceResponseInterface = handleServiceResponse;
        currentView = cview;
        context = ctx;
    }

    @Override
    protected Object doInBackground(String... uri) {
        HttpURLConnection urlConnection;
        BufferedInputStream inputStream;
        String responseString = null;

        Object returnObj = null;

        int result=0;
        try {
             /* forming th java.net.URL object */
            URL url = new URL(uri[0]);
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
            if (statusCode ==  200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                responseString = convertInputStreamToString(inputStream);

                switch(currentView){

                    case HOME:
                    case PROGRAM:
                    case ARTISTE:
                    case SABHA:
                        ProgramDataHandler prgdata = new ProgramDataHandler();
                        List<Program> values = prgdata.parseProgramListFromJsonResponse(responseString);
                        ProgramListDataCache pldc = new ProgramListDataCache(context);
                        List<Program> mergedValues = pldc.mergeLocalDataWithServer(values);
                        returnObj = (Object)mergedValues;
                        break;
                    default:
                        break;
                }


                //parseResult(responseString);
                result = 1; // Successful


            }else{
                result = 0; //"Failed to fetch data!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            returnObj = e;
        }
        return returnObj;
    }

    @Override
    protected void onPostExecute(Object result) {

        if(null != result && !(result instanceof Exception))
            serviceResponseInterface.onSuccess(result);
        else
            serviceResponseInterface.onError(result);

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