package com.boldijarpaul.itfest.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by razvan on 10/27/15.
 */

@Module(includes = {DataModule.class})
public class DebugDataModule {

    /**
     * Provides the Timber.Tree dependency used for logging
     *
     * @return The application's Timber.Tree
     */
    @Provides
    @Singleton
    Timber.Tree provideTimberTree() {
        return new Timber.DebugTree();
    }

}
