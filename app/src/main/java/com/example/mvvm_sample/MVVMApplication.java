package com.example.mvvm_sample;

import android.app.Application;

import com.example.mvvm_sample.di.component.AppComponent;
import com.example.mvvm_sample.di.component.DaggerAppComponent;
import com.example.mvvm_sample.di.module.AppModule;
import com.google.firebase.FirebaseApp;

import io.realm.Realm;

public class MVVMApplication extends Application {
    private  AppComponent appComponent;

    public  AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        FirebaseApp.initializeApp(this);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


}
