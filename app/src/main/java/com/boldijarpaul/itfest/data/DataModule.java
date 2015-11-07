package com.boldijarpaul.itfest.data;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;

import com.boldijarpaul.itfest.api.ApiModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(includes = {ApiModule.class})
public class DataModule {

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    /**
     * Provides a OkHttpClient dependency that has installed a disk cache
     *
     * @param app The application object
     * @return The custom client
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);
        client.setConnectTimeout(15, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);

        return client;
    }

    /**
     * Provides a Picasso dependency that uses the app's OkHttpClient for downloading images
     *
     * @param app    The application object
     * @param client The OkHttpClient to be used by Picasso for downloads
     * @return The custom Picasso
     */
    @Provides
    @Singleton
    Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Timber.i(e, "Failed to load image: %s", uri);
                    }
                })
                .build();
    }

    /**
     * Returns the ConnectivityManager system service
     *
     * @param app The application object
     * @return The ConnectivityManager system service
     */
    @Provides
    ConnectivityManager provideConnectivityManager(Application app) {
        return (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    /**
     * Provides a Gson dependency that plays nice with Realm.io, by skipping the fields
     * added by Realm.io to model classes, and converts custom Realm lists
     *
     * @return The custom Gson instance
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss 'T'ZZZZZ")
                .create();
    }


}
