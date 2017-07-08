package com.abheri.sunaad.view;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.abheri.sunaad.R;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


/**
 * Created by prasanna.ramaswamy on 06/07/17.
 */

public class RegistrationService extends IntentService {
    public RegistrationService() {
        super("RegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID myID = InstanceID.getInstance(this);
        Log.d("Sunaad","Entered Intent Service" );

        android.os.Debug.waitForDebugger();
        try {
            String registrationToken = myID.getToken(
                    getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                    null
            );


            Log.d("Sunaad", registrationToken);

            GcmPubSub subscription = GcmPubSub.getInstance(this);
            subscription.subscribe(registrationToken, "/topics/my_little_topic", null);

        }catch (IOException e){
            System.out.println("Exception in onHandleIntent:" + e.getStackTrace());
        }


    }
}
