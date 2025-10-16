package com.example.mvvm_sample.data;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_sample.model.FeatureItem;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
        // refresh from server and emit again
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
            JsonObject root =
                    JsonParser.parseString(json).getAsJsonObject();

            JsonArray arr = root.getAsJsonArray("features");
            if (arr == null) return out;

            for (JsonElement el : arr) {
                JsonObject obj = el.getAsJsonObject();
                String title = obj.get("title").getAsString();
                String iconName = obj.get("icon").getAsString();
                // lookup drawable id; fallback to 0 if not found
                int iconRes = res.getIdentifier(iconName, "drawable", pkg);
                out.add(new FeatureItem(iconRes, title));
            }
        } catch (Exception e) {
            // Log and return whatever we parsed so far
            Log.e("FeatureRemoteDataSource", "Failed to parse Remote Config JSON", e);
        }
        return out;
    }
}
