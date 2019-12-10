package org.app.habib.ghiblifilms.rest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



@Module
public class NetworkClient {
    public static final String BASE_URL = "https://ghibliapi.herokuapp.com/";

    @Provides
    @Singleton
    public Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public NetworkApi getApiInterface(Retrofit retrofit) {
        return retrofit.create(NetworkApi.class);
    }
}
