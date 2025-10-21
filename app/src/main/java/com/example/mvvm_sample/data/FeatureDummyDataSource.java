package com.example.mvvm_sample.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.List;

import javax.inject.Inject;

public class FeatureDummyDataSource implements Repository {
    @Inject
    public FeatureDummyDataSource() {}

    @Override public LiveData<List<FeatureItem>> getFeatures() {
        MutableLiveData<List<FeatureItem>> live = new MutableLiveData<>();
//        live.setValue(List.of(
//                new FeatureItem(R.drawable.ic_insurance, "Dummy – Insurance Tools"),
//                new FeatureItem(R.drawable.ic_traffic_camera,    "Dummy – Traffic Cam"),
//                new FeatureItem(R.drawable.ic_quick_car_valuation,  "Dummy – Used Cars")
//        ));
        return live;
    }
}
