package com.example.mvvm_sample.data;

import androidx.lifecycle.LiveData;

import com.example.mvvm_sample.model.FeatureItem;

import java.util.List;

public interface Repository {
    LiveData<List<FeatureItem>> getFeatures();
}
