package com.example.mvvm_sample.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.di.qualifier.Local;
import com.example.mvvm_sample.di.qualifier.Remote;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.List;

import javax.inject.Inject;

public class NotificationViewModel extends ViewModel {
    public final LiveData<List<FeatureItem>> features;
    @Inject
    public NotificationViewModel(@Local Repository remote) {
        features = remote.getFeatures();
    }

    public void goToNotificationTab() {

    }

}
