package com.example.mvvm_sample.di.module;

import android.app.Application;
import android.content.Context;

import com.example.mvvm_sample.R;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public class AppModule {
    private final Application app;
    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public static Context provideAppContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }
    @Provides @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    FirebaseRemoteConfig provideRemoteConfig() {
        FirebaseRemoteConfig rc = FirebaseRemoteConfig.getInstance();
        rc.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build());
        rc.setDefaultsAsync(R.xml.remote_config_defaults);
        return rc;
    }

    @Provides @Singleton
    RealmConfiguration provideRealmConfig(Application app) {
        return new RealmConfiguration.Builder()
                .name("app.realm")
                .schemaVersion(1)
                .build();
    }
}
