package com.example.mvvm_sample.di.component;

import android.app.Application;

import com.example.mvvm_sample.MVVMApplication;
import com.example.mvvm_sample.di.module.AppModule;
import com.example.mvvm_sample.di.module.DataSourceModule;
import com.example.mvvm_sample.di.module.ViewModelModule;
import com.example.mvvm_sample.ui.FeatureActivity;
import com.example.mvvm_sample.ui.NotificationActivity;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ViewModelModule.class, DataSourceModule.class})
public interface AppComponent {
    Application application();
    void inject(FeatureActivity activity);
    void inject(NotificationActivity activity);

    //
//    @Component.Factory
//    interface Factory {
//        AppComponent create(@BindsInstance Application application);
//    }
}
