package com.targetsmile.sugarintaketracker;

/**
 * Created by veetrag on 07/02/16.
 */

import android.app.Application;

import com.apptentive.android.sdk.Apptentive;
import com.flurry.android.FlurryAgent;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // configure Flurry
        FlurryAgent.setLogEnabled(false);

        // init Flurry
        FlurryAgent.init(this, "RHN29T7WKZNMGQ7P36MK");
        Apptentive.register(this, "50d218f5ea6c0f2d2d8e2832e8a761338b33898349062c3de9bf50e2db7cf5b3");
    }
}