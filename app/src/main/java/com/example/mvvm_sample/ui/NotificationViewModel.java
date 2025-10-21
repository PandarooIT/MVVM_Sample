package com.example.mvvm_sample.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_sample.data.FeatureRealmDataSource;
import com.example.mvvm_sample.data.Repository;
import com.example.mvvm_sample.di.qualifier.Local;
import com.example.mvvm_sample.di.qualifier.Remote;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.List;

import javax.inject.Inject;

public class NotificationViewModel extends ViewModel {
    public final LiveData<List<FeatureItem>> features;

    Repository repo;
    @Inject
    public NotificationViewModel(@Local Repository remote) {
        repo = remote;
        features = repo.getFeatures();
    }

    public void addFeature(FeatureItem item) {
        repo.insert(item);
    }

    public void updateFeature(long id, FeatureItem newItem) {
        repo.update(id, newItem);
    }

    public void deleteFeature(long id) {
        repo.delete(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }


}
