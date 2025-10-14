package com.example.mvvm_sample.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_sample.data.FeatureRepository;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.List;

public class FeatureViewModel extends ViewModel {
    private final FeatureRepository repo = new FeatureRepository();
    // dummy data
    public LiveData<List<FeatureItem>> features = repo.getFeatures();
}
