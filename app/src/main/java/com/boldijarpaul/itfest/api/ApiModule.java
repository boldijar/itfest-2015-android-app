package com.boldijarpaul.itfest.api;

import com.boldijarpaul.itfest.BuildConfig;
import com.boldijarpaul.itfest.api.services.AnswersService;
import com.boldijarpaul.itfest.api.services.QuizesService;
import com.boldijarpaul.itfest.api.services.UserService;
import com.boldijarpaul.itfest.helper.QuizHelper;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module
public class ApiModule {

    public static final String IP = "192.168.0.34";
    private static final String API_APP_DATA_ENDPOINT = "http://" + IP + "/api/index.php/";


    /**
     * Provides the Endpoint dependency for the application's "app data" API
     *
     * @return The application's API endpoint
     */
    @Provides
    @Singleton
    Endpoint provideAppDataEndpoint() {
        return Endpoints.newFixedEndpoint(API_APP_DATA_ENDPOINT);
    }

    /**
     * Provides a Client dependency for executing API calls by Retrofit
     *
     * @param client The application's OkHttpClient
     * @return The custom Client
     */
    @Provides
    @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    /**
     * Provides a Converter dependency to be used by Retrofit
     *
     * @param gson The custom Gson dependency to be used for the converter
     * @return The custom Converter
     */
    @Provides
    @Singleton
    Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    /**
     * Provides a RestAdapter dependency set up for the application's "app data" API
     *
     * @param endpoint  Injected application's API endpoint
     * @param client    Injected Client for the RestAdapter
     * @param converter Injected API response converter
     * @return The application's API RestAdapter
     */
    @Provides
    @Singleton
    RestAdapter provideAppDataRestAdapter(Endpoint endpoint,
                                          Client client,
                                          Converter converter) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setConverter(converter)
                .setLogLevel(
                        BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }


    @Provides
    @Singleton
    QuizesService provideQuizesService(RestAdapter restAdapter) {
        return restAdapter.create(QuizesService.class);
    }

    @Provides
    @Singleton
    AnswersService provideAnswersService(RestAdapter restAdapter) {
        return restAdapter.create(AnswersService.class);
    }
    @Provides
    @Singleton
    UserService provideUserService(RestAdapter restAdapter) {
        return restAdapter.create(UserService.class);
    }
    @Provides
    @Singleton
    QuizHelper provideQuizHelper() {
        return new QuizHelper();
    }

}
