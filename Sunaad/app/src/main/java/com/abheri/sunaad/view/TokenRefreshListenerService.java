package com.abheri.sunaad.view;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by prasanna.ramaswamy on 07/07/17.
 */

public class TokenRefreshListenerService  extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent i = new Intent(this, RegistrationService.class);
        startService(i);
    }
}