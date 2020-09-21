package com.fitness365.fitindia.app;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.fitness365.fitindia.helper.ConnectivityReceiverListener;

import java.util.Calendar;


/**
 * Created by CT13 on 2017-06-23.
 */

public class Fitness365App extends Application {

    private static Fitness365App mInstance;
    private static Application sApplication;
    public ConnectivityReceiverListener connectivityReceiverListener;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sApplication = this;
    }

    public static synchronized Fitness365App getInstance() {
            return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiverListener listener) {
       connectivityReceiverListener = listener;
    }


    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

}
