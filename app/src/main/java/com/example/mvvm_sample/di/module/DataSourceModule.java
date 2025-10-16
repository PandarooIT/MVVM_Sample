package com.example.mvvm_sample.di.module;

import android.app.Application;

import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.data.FeatureDummyDataSource;
import com.example.mvvm_sample.data.FeatureRemoteDataSource;
import com.example.mvvm_sample.di.qualifier.Local;
import com.example.mvvm_sample.di.qualifier.Remote;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {
    @Provides
    @Singleton
    @Remote
    Repository provideRemote(Application app, FirebaseRemoteConfig rc) {
        return new FeatureRemoteDataSource(app, rc);
    }

    @Provides
    @Singleton
    @Local
    Repository provideLocal() {
        return new FeatureDummyDataSource();
    }
}
