package com.targetsmile.sugarintaketracker;

/**
 * Created by veetrag on 07/02/16.
 */

import android.app.Application;

import com.flurry.android.FlurryAgent;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // configure Flurry
        FlurryAgent.setLogEnabled(false);

        // init Flurry
        FlurryAgent.init(this, "RHN29T7WKZNMGQ7P36MK");
    }
}