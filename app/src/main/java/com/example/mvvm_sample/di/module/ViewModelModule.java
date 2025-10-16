package com.example.mvvm_sample.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_sample.ViewModelProviderFactory;
import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.di.qualifier.Local;
import com.example.mvvm_sample.di.qualifier.Remote;
import com.example.mvvm_sample.ui.FeatureViewModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    @Named("remoteFactory")
    ViewModelProvider.Factory provideRemoteFactory(@Remote Repository repo) {
        return new ViewModelProviderFactory<>(
                FeatureViewModel.class, () -> new FeatureViewModel(repo)
        );
    }

    @Provides
    @Singleton
    @Named("localFactory")
    ViewModelProvider.Factory provideLocalFactory(@Local Repository repo) {
        return new ViewModelProviderFactory<>(
                FeatureViewModel.class, () -> new FeatureViewModel(repo)
        );
    }

}
