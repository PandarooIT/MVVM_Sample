package com.example.mvvm_sample.di.component;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_sample.di.module.ActivityModule;
import com.example.mvvm_sample.di.scoped.ActivityScope;
import com.example.mvvm_sample.ui.FeatureActivity;
import com.example.mvvm_sample.ui.NotificationActivity;
import com.example.mvvm_sample.ui.WebviewActivity;

import org.checkerframework.checker.units.qual.A;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {ActivityModule.class}
)
public interface ActivityComponent {
    @Component.Factory
    interface Factory {
        ActivityComponent create(
                AppComponent appComponent,
                @BindsInstance AppCompatActivity activity);
    }
    void inject(NotificationActivity activity);
    void inject (FeatureActivity activity);
    void inject (WebviewActivity activity);
}
