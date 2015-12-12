package com.abheri.sunaad.view;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by prasanna.ramaswamy on 08/12/15.
 */
public class MyLogger {

    private Context context;
    private static MyLogger loggerInstance = null;
    private MyLogger(){

    }

    public static MyLogger getInstance(){

        synchronized (new Object()){
            if (null == loggerInstance) {
                loggerInstance = new MyLogger();
            }
        }

        return loggerInstance;
    }

    public void setContext(Context ct){
        context = ct;
    }

    public void showToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void v(String logtext){
        Log.v("sunaad", logtext);
    }
}
