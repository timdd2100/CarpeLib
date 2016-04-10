package com.test.work.library.GCM;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public class GCMClientManager {

    public static final String TAG = "GCMClientManager";
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private GoogleCloudMessaging gcm;
    private String device_token = "";
    private String projectNumber;
    private Activity activity;
    private String errorMsg = "";

    ProgressDialog prgDialog;

    public static abstract class RegistrationCompletedHandler {
        public abstract void onSuccess(String registrationId, boolean isNewRegistration);

        public void onFailure(String ex) {
            Log.e(TAG, ex);
        }
    }

    public GCMClientManager(Activity activity, String projectNumber) {
        this.activity = activity;
        this.projectNumber = projectNumber;
        this.gcm = GoogleCloudMessaging.getInstance(activity);
    }

    /**
     * 判斷是否須註冊
     *
     * @param handler
     */
    public void registerIfNeeded(final RegistrationCompletedHandler handler) {
        if (checkPlayServices()) {
            device_token = getRegistrationId(getContext());

            if (device_token.isEmpty()) {
                registerInBackground(handler);
            } else { // got id from cache
                handler.onSuccess(device_token, false);
            }
        } else { // no play services
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    }


    /**
     * 註冊ＧＣＭ 取得token
     *
     * @param handler
     */
    private void registerInBackground(final RegistrationCompletedHandler handler) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    // 2015.06.22 timkao fix google 更新 ＧＣＭ註冊程序
                    InstanceID instanceID = InstanceID.getInstance(getContext());

                    device_token = instanceID.getToken
                            (projectNumber, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                    // 將token 存放
                    storeRegistrationId(getContext(), device_token);
                } catch (Exception ex) {
                    device_token = null;
                    errorMsg = ex.toString();
                }
                return device_token;
            }

            @Override
            protected void onPostExecute(String regId) {
                if (regId != null) {
                    handler.onSuccess(regId, true);
                } else {
                    handler.onFailure(errorMsg);
                }
            }
        }.execute(null, null, null);
    }


    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // 檢查版本 以便重新取得Google token
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    /**
     * 存放token
     *
     * @param context
     * @param regId
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public SharedPreferences getGCMPreferences(Context context) {
        return getContext().getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
    }

    /**
     * 取得DeviceToken
     *
     * @param context
     * @return
     */
    public String getGCMDevice_token(Context context) {
        try {
            final SharedPreferences prefs = getGCMPreferences(context);
            String token = prefs.getString(PROPERTY_REG_ID, "");
            return token;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 檢查有無PlayService
     *
     * @return
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
            }
            return false;
        }
        return true;
    }

    private Context getContext() {
        return activity;
    }

    private Activity getActivity() {
        return activity;
    }
}
