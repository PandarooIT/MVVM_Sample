package com.example.mvvm_sample.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_sample.MVVMApplication;
import com.example.mvvm_sample.di.component.ActivityComponent;
import com.example.mvvm_sample.di.component.AppComponent;
import com.example.mvvm_sample.di.component.DaggerActivityComponent;

import javax.inject.Inject;

public abstract class BaseActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    @Inject
    protected ViewModelProvider.Factory factory;
    protected B binding;
    protected VM viewModel;
    protected ActivityComponent activityComponent;
    protected abstract int layoutId();
    protected abstract Class<VM> viewModelClass();
    protected abstract void injectComponent(ActivityComponent activityComponent);

    private void initiativeBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId());
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }

    private void initiativeViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(viewModelClass());
    }
    private ActivityComponent getActivityComponent() {
        AppComponent appComponent =
                ((MVVMApplication) getApplication()).getAppComponent();
        return DaggerActivityComponent.factory()
                .create(appComponent, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = getActivityComponent();
        injectComponent(activityComponent);
        initiativeBinding();
        initiativeViewModel();
    }
}
