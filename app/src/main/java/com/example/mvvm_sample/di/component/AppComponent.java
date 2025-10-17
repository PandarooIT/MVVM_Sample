package com.example.mvvm_sample.di.component;

import android.app.Application;

import com.example.mvvm_sample.di.module.AppModule;
import com.example.mvvm_sample.ui.FeatureActivity;
import com.example.mvvm_sample.ui.NotificationActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
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
