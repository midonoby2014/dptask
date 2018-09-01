package com.noby.dptask.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import java.util.Locale;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class DPApp extends Application {

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        setLocale();
    }
    public static void setLocale() {
        Locale locale = Locale.US;
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getAppContext().getResources().updateConfiguration(config,
                getAppContext().getResources().getDisplayMetrics());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
    public static Context getAppContext() {
        return appContext;
    }
}
