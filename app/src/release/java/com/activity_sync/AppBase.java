package com.activity_sync;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class AppBase extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
    }
}
