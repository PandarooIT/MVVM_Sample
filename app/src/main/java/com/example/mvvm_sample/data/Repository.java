package com.example.mvvm_sample.data;

import androidx.lifecycle.LiveData;

import com.example.mvvm_sample.model.FeatureItem;

import java.util.List;

public interface Repository {
    LiveData<List<FeatureItem>> getFeatures();
    void insert(FeatureItem item);
    void update(long id, FeatureItem newItem);
    void delete(long id);
    void deleteAll();
}
