package com.madden.anrjustcards;

import android.app.Application;

import timber.log.Timber;


public class JCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}