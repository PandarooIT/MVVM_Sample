package com.example.mvvm_sample.data;

import android.app.Application;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_sample.model.FeatureItem;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FeatureRemoteDataSource implements Repository {

    private final Application app;
    private final FirebaseRemoteConfig rc;

    @Inject
    public FeatureRemoteDataSource(Application app, FirebaseRemoteConfig rc) {
        this.app = app;
        this.rc = rc;
    }

    @Override
    public LiveData<List<FeatureItem>> getFeatures() {
        MutableLiveData<List<FeatureItem>> live = new MutableLiveData<>();
        // parse immediately from current cache
        live.setValue(parse(rc.getString("list_feature")));

        // then refresh from server and emit again
        rc.fetchAndActivate().addOnCompleteListener(t ->
                live.postValue(parse(rc.getString("list_feature")))
        );
        return live;
    }

    private List<FeatureItem> parse(String json) {
        List<FeatureItem> out = new ArrayList<>();
        if (json == null || json.isEmpty()) return out;

        try {
            Resources res = app.getResources();
            String pkg = app.getPackageName();

            // Root object -> get "features" array
            com.google.gson.JsonObject root =
                    com.google.gson.JsonParser.parseString(json).getAsJsonObject();

            com.google.gson.JsonArray arr = root.getAsJsonArray("features");
            if (arr == null) return out;

            for (com.google.gson.JsonElement el : arr) {
                com.google.gson.JsonObject obj = el.getAsJsonObject();
                String title = obj.get("title").getAsString();
                String iconName = obj.get("icon").getAsString();

                // lookup drawable id; fallback to 0 if not found
                int iconRes = res.getIdentifier(iconName, "drawable", pkg);
                out.add(new FeatureItem(iconRes, title));
            }
        } catch (Exception e) {
            // Log and return whatever we parsed so far
            android.util.Log.e("FeatureRemoteDataSource", "Failed to parse Remote Config JSON", e);
        }
        return out;
    }

//    public LiveData<List<FeatureItem>> getFeatures() {
//        MutableLiveData<List<FeatureItem>> mutableLiveData = new MutableLiveData<>();
//        List<FeatureItem> list = new ArrayList<>();
//        list.add(new FeatureItem(R.drawable.ic_car, "Road & Valet Assist"));
//        list.add(new FeatureItem(R.drawable.ic_car, "Co-Driver Alerts"));
//        list.add(new FeatureItem(R.drawable.ic_quick_car_valuation, "Quick Car Valuation"));
//        list.add(new FeatureItem(R.drawable.ic_insurance, "Insurance Quote"));
//        list.add(new FeatureItem(R.drawable.ic_car, "Used Vehicles"));
//        list.add(new FeatureItem(R.drawable.ic_traffic_camera, "Traffic Cameras"));
//        list.add(new FeatureItem(R.drawable.ic_car, "Road & Valet Assist"));
//        list.add(new FeatureItem(R.drawable.ic_car, "Co-Driver Alerts"));
//        list.add(new FeatureItem(R.drawable.ic_quick_car_valuation, "Quick Car Valuation"));
//        list.add(new FeatureItem(R.drawable.ic_insurance, "Insurance Quote"));
//        list.add(new FeatureItem(R.drawable.ic_car, "Used Vehicles"));
//        list.add(new FeatureItem(R.drawable.ic_traffic_camera, "Traffic Cameras"));
//        mutableLiveData.setValue(list);
//        return mutableLiveData;
//    }
}
