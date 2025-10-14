package com.example.mvvm_sample.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.ArrayList;
import java.util.List;

public class FeatureRepository {
    public LiveData<List<FeatureItem>> getFeatures() {
        MutableLiveData<List<FeatureItem>> mutableLiveData = new MutableLiveData<>();
        List<FeatureItem> list = new ArrayList<>();
        list.add(new FeatureItem(R.drawable.ic_car, "Road & Valet Assist"));
        list.add(new FeatureItem(R.drawable.ic_car, "Co-Driver Alerts"));
        list.add(new FeatureItem(R.drawable.ic_car, "Quick Car Valuation"));
        list.add(new FeatureItem(R.drawable.ic_car, "Insurance Quote"));
        list.add(new FeatureItem(R.drawable.ic_car, "Used Vehicles"));
        list.add(new FeatureItem(R.drawable.ic_car, "Traffic Cameras"));
        list.add(new FeatureItem(R.drawable.ic_car, "Road & Valet Assist"));
        list.add(new FeatureItem(R.drawable.ic_car, "Co-Driver Alerts"));
        list.add(new FeatureItem(R.drawable.ic_car, "Quick Car Valuation"));
        list.add(new FeatureItem(R.drawable.ic_car, "Insurance Quote"));
        list.add(new FeatureItem(R.drawable.ic_car, "Used Vehicles"));
        list.add(new FeatureItem(R.drawable.ic_car, "Traffic Cameras"));
        mutableLiveData.setValue(list);
        return mutableLiveData;
    }
}
