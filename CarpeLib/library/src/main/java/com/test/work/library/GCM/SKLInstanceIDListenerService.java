package com.test.work.library.GCM;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;


public class SKLInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify of changes
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
