package com.example.mvvm_sample.ui;

import androidx.lifecycle.ViewModel;

import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.di.qualifier.Local;

import javax.inject.Inject;

public class WebviewViewModel extends ViewModel {
    @Inject
    public WebviewViewModel() {
    }
}
