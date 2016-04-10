package com.test.work.library.GoogleAnalytics;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by sklmac on 15/6/17.
 */
public class AppTrack {

    private static Tracker mTracker;
    private static String trackerID;
    private static Context context;

    /**
     * Tracker 區
     */
    public enum ActionTrackType {
        LOGIN, LOGOUT, OTHER
    }


    /**
     * 必要
     * 要先加入meta-data
     * android:name="com.google.android.gms.analytics.globalConfigResource"
     * android:resource="@xml/global_tracker" />
     * 並設定好global_tracker
     *
     * @param c_context
     * @param s_trackerID
     */
    public static void createInstance(Context c_context, String s_trackerID) {
        context = c_context;
        trackerID = s_trackerID;
        getAppTracker();
    }

    private static synchronized Tracker getAppTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
            mTracker = analytics.newTracker(trackerID); // Replace with your real tracker id
            mTracker.enableAutoActivityTracking(true);
            mTracker.enableExceptionReporting(true);
            mTracker.setSessionTimeout(-1);
        }
        return mTracker;
    }

    /**
     * GoogleAnalytics Screen name log
     *
     * @param name
     */
    public static void screenTrack(String name) {
        // 建立針對此應用程式的 tracker
        Tracker trackApp = getAppTracker();
        trackApp.setScreenName(name);
        trackApp.send(new HitBuilders.AppViewBuilder().build());
    }

    /**
     * GoogleAnalytics 例外狀況記錄
     */
    public static void exceptionTrack(Exception e) {
        StandardExceptionParser exceptionParser = new StandardExceptionParser(context, null);
        String ex = exceptionParser.getDescription(Thread.currentThread().getName(), e);
        ex = ex + ":" + e.getMessage();
        Tracker trackApp = getAppTracker();
        trackApp.send(new HitBuilders.ExceptionBuilder()
                .setDescription(Log.getStackTraceString(e))
                .setFatal(false)
                .build()
        );
    }

    /**
     * GoogleAnalytics 行為記錄 label為彈性
     *
     * @param label
     * @param actionTrackType
     */
    public static void actionTrack(String label, ActionTrackType actionTrackType) {
        Tracker trackApp = getAppTracker();

        String actionStr = "";
        switch (actionTrackType) {
            case LOGIN:
                actionStr = "登入相關";
                break;
            case LOGOUT:
                actionStr = "登出相關";
                break;
            case OTHER:
                actionStr = "Other";
                break;
        }

        // Build and send an Event.
        trackApp.send(new HitBuilders.EventBuilder()
                .setCategory(context.getPackageName())
                .setAction(actionStr)
                .setLabel(label)
                .build());
    }

    /**
     * GoogleAnalytics 行為記錄 category, action, label為彈性
     *
     * @param category
     * @param action
     * @param label
     */
    public static void actionTrack(String category, String action, String label) {
        Tracker trackApp = getAppTracker();
        // Build and send an Event.
        trackApp.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }

    public static Tracker getmTracker() {
        return mTracker;
    }
}
