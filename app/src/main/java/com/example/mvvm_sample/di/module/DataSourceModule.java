package com.example.mvvm_sample.di.module;


import com.example.mvvm_sample.data.FeatureRealmDataSource;
import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.data.FeatureDummyDataSource;
import com.example.mvvm_sample.data.FeatureRemoteDataSource;
import com.example.mvvm_sample.di.qualifier.Local;
import com.example.mvvm_sample.di.qualifier.Remote;
import com.example.mvvm_sample.di.scoped.ActivityScope;


import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {
    @Provides
    @Remote
    Repository provideRemote(FeatureRemoteDataSource dataSource) {
        return dataSource;
    }

    @Provides
    @Local
    Repository provideLocal(FeatureRealmDataSource dataSource) {
        return dataSource;
    }

}
