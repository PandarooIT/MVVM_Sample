package com.example.mvvm_sample.di.component;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.di.module.AppModule;
import com.example.mvvm_sample.di.qualifier.Local;
import com.example.mvvm_sample.di.qualifier.Remote;
import com.example.mvvm_sample.ui.FeatureActivity;
import com.example.mvvm_sample.ui.NotificationActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application application();
//    @Remote
//    Repository remoteRepository();
//    @Local
//    Repository localRepository();

    ViewModelProvider.Factory viewModelFactory();

//    @Component.Factory
//    interface Factory {
//        AppComponent create(@BindsInstance Application application);
//    }
}
