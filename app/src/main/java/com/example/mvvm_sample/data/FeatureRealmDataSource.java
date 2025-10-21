package com.example.mvvm_sample.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class FeatureRealmDataSource implements Repository {

    private final RealmConfiguration config;
    private Realm realm;
    private RealmResults<FeatureEntity> results;
    private final MutableLiveData<List<FeatureItem>> liveData = new MutableLiveData<>();

    @Inject
    public FeatureRealmDataSource(RealmConfiguration config) {
        this.config = config;
        Realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(config);
        results = realm.where(FeatureEntity.class).sort("id", Sort.ASCENDING).findAllAsync();
        results.addChangeListener((res) -> postLiveData(realm.copyFromRealm(res)));
        if (results.isEmpty()) seedDemoData();
    }

    private void postLiveData(List<FeatureEntity> entities) {
        List<FeatureItem> list = new ArrayList<>();
        for (FeatureEntity e : entities) {
            list.add(new FeatureItem(e.getId(), e.getIconRes(), e.getTitle()));
        }
        liveData.postValue(list);
    }

    @Override
    public LiveData<List<FeatureItem>> getFeatures() {
        if (results.isEmpty()) seedDemoData();
        return liveData;
    }

    private void seedDemoData() {
        Realm bgRealm = Realm.getInstance(config);
        bgRealm.executeTransactionAsync(r -> {
            if (r.where(FeatureEntity.class).count() == 0) {
                insertInternal(r, "Smart Parking", R.drawable.ic_car);
                insertInternal(r, "Speed Alerts", R.drawable.ic_quick_car_valuation);
                insertInternal(r, "Claim Tracker", R.drawable.ic_insurance);
            }
        }, bgRealm::close, error -> bgRealm.close());
    }

    private long nextId(Realm r) {
        Number maxId = r.where(FeatureEntity.class).max("id");
        return (maxId == null) ? 1 : maxId.longValue() + 1;
    }

    private void insertInternal(Realm r, String title, int iconRes) {
        long id = nextId(r);
        FeatureEntity e = r.createObject(FeatureEntity.class, id);
        e.setTitle(title);
        e.setIconRes(iconRes);
    }

    public void insert(FeatureItem item) {
        Realm bgRealm = Realm.getInstance(config);
        bgRealm.executeTransactionAsync(
                r -> insertInternal(r, item.getTitle(), item.getIconRes()),
                bgRealm::close,
                error -> bgRealm.close()
        );
    }

    public void update(long id, FeatureItem newItem) {
        Realm bgRealm = Realm.getInstance(config);
        bgRealm.executeTransactionAsync(r -> {
            FeatureEntity e = r.where(FeatureEntity.class)
                    .equalTo("id", id)
                    .findFirst();
            if (e != null) {
                e.setTitle(newItem.getTitle());
                e.setIconRes(newItem.getIconRes());
            }
        }, bgRealm::close, error -> bgRealm.close());
    }

    public void delete(long id) {
        Realm bgRealm = Realm.getInstance(config);
        bgRealm.executeTransactionAsync(r -> {
            FeatureEntity e = r.where(FeatureEntity.class)
                    .equalTo("id", id)
                    .findFirst();
            if (e != null) e.deleteFromRealm();
        }, bgRealm::close, error -> bgRealm.close());
    }

    public void deleteAll() {
        Realm bgRealm = Realm.getInstance(config);
        bgRealm.executeTransactionAsync(r -> r.delete(FeatureEntity.class),
                bgRealm::close,
                error -> bgRealm.close());
    }

    public void close() {
        if (results != null) results.removeAllChangeListeners();
        if (!realm.isClosed()) realm.close();
    }
}
