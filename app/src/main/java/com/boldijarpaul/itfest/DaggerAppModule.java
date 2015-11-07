package com.boldijarpaul.itfest;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by razvan on 10/27/15.
 */
@Module
public class DaggerAppModule {

    private final DaggerApp mApp;

    public DaggerAppModule(DaggerApp app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApp;
    }
}

