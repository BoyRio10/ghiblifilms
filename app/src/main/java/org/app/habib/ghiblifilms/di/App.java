package org.app.habib.ghiblifilms.di;

import android.app.Application;

import org.app.habib.ghiblifilms.rest.NetworkClient;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .networkClient(new NetworkClient())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
