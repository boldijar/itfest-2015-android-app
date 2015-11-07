package com.boldijarpaul.itfest;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DaggerApp extends Application {

    @Inject
    Timber.Tree mTimberTree;

    private DaggerGraph mGraph;


    @Override
    public void onCreate() {
        super.onCreate();


        // build the dependency graph
        mGraph = DaggerComponent.Initializer.init(this);
        mGraph.inject(this);

        Timber.plant(mTimberTree);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Regular.ttf")
                .setFontAttrId(com.boldijarpaul.itfest.R.attr.fontPath)
                .build());
    }

    /**
     * @return The application's dependency graph
     */
    public DaggerGraph graph() {
        return mGraph;
    }

    /**
     * @return The application class from a given context
     */
    public static DaggerApp get(Context context) {
        return (DaggerApp) context.getApplicationContext();
    }


}
