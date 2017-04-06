package com.abheri.sunaad.view;

/**
 * Created by prasanna.ramaswamy on 06/04/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.abheri.sunaad.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    // Set Duration of the Splash Screen
    long Delay = 800;

    public static Context c;
    Activity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        System.out.println("Testing......");
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        c = this.getApplicationContext();
        // Get the view from splash_screen.xml
        setContentView(R.layout.splash_screen);
        self = SplashActivity.this;

        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                //Create database by invoking get topics
                /*TopicDataSource ts = new TopicDataSource(c);
                ts.open();
                ts.getAllTopics();
                ts.close(); */
                int i = 0;


                Intent myIntent;
                myIntent = new Intent(self, MainActivity.class);

                // Close SplashScreenActivity.class
                finish();


                // Start MainActivity.class
                startActivity(myIntent);
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }

    public static Context getAppContext()
    {
        return c;
    }
}