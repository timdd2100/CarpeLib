package com.test.work.library.GCM;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class RegistrationIntentService extends IntentService {
    // abbreviated tag name
    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = GCMConfiguration.PROJECT_NUMBER;
        try {
            // request token that will be used by the server to send push notifications
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);
            // pass along this data
            sendRegistrationToServer(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationToServer(String token) {
        final SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                getApplicationContext().getPackageName(),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GCMClientManager.PROPERTY_REG_ID, token);
    }
}

