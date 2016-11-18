package com.solo.security;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Messi on 16-11-18.
 */

public class SecurityApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LeakCanary.install(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
