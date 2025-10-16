package com.example.mvvm_sample.di.component;

import com.example.mvvm_sample.di.module.ActivityModule;
import com.example.mvvm_sample.di.scoped.ActivityScope;
import com.example.mvvm_sample.ui.FeatureActivity;

import org.checkerframework.checker.units.qual.A;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {ActivityModule.class}
)
public interface ActivityComponent {
}
